package com.wzz.es;

import com.alibaba.fastjson.JSON;
import com.wzz.es.dao.ProductDao;
import com.wzz.es.entity.Product;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.script.mustache.MultiSearchTemplateResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.ParsedRange;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataESSearchTest {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * term 查询
     * search(termQueryBuilder) 调用搜索方法，参数查询构建器对象
     */
    @Test
    public void termQuery() {
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", "小米");
        Iterable<Product> products = productDao.search(termQueryBuilder);
        for (Product product : products) {
            System.out.println(product);
        }
    }

    /**
     * term 查询加分页
     */
    @Test
    public void termQueryByPage() {
//        int currentPage = 0;
//        int pageSize = 5;
//        //设置查询分页
//        PageRequest pageRequest = PageRequest.of(currentPage, pageSize);
//        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", "小米");
        MatchQueryBuilder termQueryBuilder = QueryBuilders.matchQuery("title", "荣耀");
        Iterable<Product> products =
                productDao.search(termQueryBuilder);
//                productDao.search(termQueryBuilder, pageRequest);
        for (Product product : products) {
            System.out.println(product);
        }
    }

    @Test
    /** 搜索全部数据 , 分页显示 ， 按 balance字段降序 排序 */
    public void test1() {
        // 构建查询条件(搜索全部)
        MatchAllQueryBuilder queryBuilder1 = QueryBuilders.matchAllQuery();
        // 分页
        Pageable pageable = PageRequest.of(0, 5);
        // 排序
        FieldSortBuilder balance = new FieldSortBuilder("balance").order(SortOrder.DESC);
        // 执行查询
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder1)
                .withPageable(pageable)
                .withSort(balance)
                .build();
//        SearchHits<EsAccount> searchHits = elasticsearchRestTemplate.search(query, EsAccount.class);
//
//        //封装page对象
//        List<EsAccount> accounts = new ArrayList<>();
//        for (SearchHit<EsAccount> hit : searchHits) {
//            accounts.add(hit.getContent());
//        }
//        Page<EsAccount> page = new PageImpl<>(accounts,pageable,searchHits.getTotalHits());
//
//        //输出分页对象
//        System.out.println(page.getTotalPages());
//        System.out.println(page.getTotalElements());
    }

    @Test
    /** 条件搜索 */
    public void test2() {

        // 搜索出 account_number 为 20 的文档
        TermQueryBuilder builder = QueryBuilders.termQuery("account_number", 20);

        // 对于数值类型是精准匹配，对于文本类型是 模糊匹配,_score越高在前
        TermQueryBuilder builder1 = QueryBuilders.termQuery("address", "mill");

        // 搜索add字段同时包含 mill lane 的文档
        TermQueryBuilder builder2 = QueryBuilders.termQuery("address", "mill lane");

        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(builder1)
                .build();

//        SearchHits<EsAccount> searchHits = elasticsearchRestTemplate.search(query, EsAccount.class);
//
//        for (SearchHit<EsAccount> hit : searchHits) {
//            System.out.println(hit.getContent());
//        }
    }

    @Test
    /** 组合搜索 bool*/
    public void test3() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // must表示同时满足，should满足其中一个，must_not表示同时不满足
        boolQueryBuilder.must(QueryBuilders.matchQuery("address", "mill"));
        boolQueryBuilder.must(QueryBuilders.matchQuery("address", "lane"));

        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .build();

//        SearchHits<EsAccount> searchHits = elasticsearchRestTemplate.search(query, EsAccount.class);
//        for (SearchHit<EsAccount> hit : searchHits) {
//            System.out.println(hit.getContent());
//        }
    }

    @Test
    /** 过滤搜索 */
    public void test4() {
        // 构建条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        RangeQueryBuilder balance = QueryBuilders.rangeQuery("balance").gte(20000).lte(30000);
        boolQueryBuilder.filter(balance);

        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .build();

//        SearchHits<EsAccount> searchHits = elasticsearchRestTemplate.search(query, EsAccount.class);
//
//        for (SearchHit<EsAccount> hit : searchHits) {
//            System.out.println(hit.getContent());
//        }
    }

    @Test
    /** 聚合搜索 ，对state字段进行聚合*/
    public void test5() {

        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .addAggregation(AggregationBuilders.terms("count").field("state.keyword"))
                .build();

//        SearchHits<EsAccount> search = elasticsearchRestTemplate.search(query, EsAccount.class);

        //取出聚合结果
        Aggregations aggregations = null;
//       Aggregations aggregations = aggregations = searchHits.getAggregations();
        Terms terms = (Terms) aggregations.asMap().get("count");

        for (Terms.Bucket bucket : terms.getBuckets()) {
            String keyAsString = bucket.getKeyAsString();   // 聚合字段列的值
            long docCount = bucket.getDocCount();           // 聚合字段对应的数量
            System.out.println(keyAsString + " " + docCount);
        }
    }

    @Test
    /** 嵌套聚合，统计出相同state的文档数量，再统计出balance的平均值，降序排序 */
    public void test6() {
        // 创建聚合查询条件
        TermsAggregationBuilder stateAgg = AggregationBuilders.terms("count").field("state.keyword");
        AvgAggregationBuilder balanceAgg = AggregationBuilders.avg("avg_balance").field("balance");
        // 嵌套
        stateAgg.subAggregation(balanceAgg);
        // 按balance的平均值降序排序
        stateAgg.order(BucketOrder.aggregation("avg_balance", false));

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .addAggregation(stateAgg)
                .build();
        //执行查询
//        SearchHits<EsAccount> searchHits = elasticsearchRestTemplate.search(build, EsAccount.class);
        // 取出聚合结果
        Aggregations aggregations = null;
//        Aggregations aggregations = searchHits.getAggregations();
        Terms terms = (Terms) aggregations.asMap().get("count");

        for (Terms.Bucket bucket : terms.getBuckets()) {
            // state : count : avg
            ParsedAvg avg = bucket.getAggregations().get("avg_balance");
            System.out.println(bucket.getKeyAsString() + " " + bucket.getDocCount() + " " + avg.getValueAsString());
        }
    }


    @Test
    /** 按字段的范围进行分段聚合，按age字段[20,30],[30,40],[40,50],之后按gender统计文档个数和balance的平均值 */
    public void test7() {
        // 创建聚合查询条件
        RangeAggregationBuilder group_by_age =
                AggregationBuilders.range("group_by_age").field("age")
                        .addRange(20, 30).addRange(30, 40).addRange(40, 50);
        TermsAggregationBuilder count = AggregationBuilders.terms("count").field("gender.keyword");
        AvgAggregationBuilder balanceAgg = AggregationBuilders.avg("avg_balance").field("balance");

        //嵌套
        group_by_age.subAggregation(count);
        count.subAggregation(balanceAgg);

        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .addAggregation(group_by_age)
                .build();

//        SearchHits<EsAccount> searchHits = elasticsearchRestTemplate.search(query, EsAccount.class);

        ParsedRange parsedRange = null;
//        ParsedRange parsedRange = searchHits.getAggregations().get("group_by_age");

        for (Range.Bucket bucket : parsedRange.getBuckets()) {
            // "key" : "20.0-30.0",  "doc_count" : 451,
            System.out.println(bucket.getKeyAsString() + " ： " + bucket.getDocCount());

            Terms group_by_gender = bucket.getAggregations().get("count");
            for (Terms.Bucket genderBucket : group_by_gender.getBuckets()) {
                //  "key" : "M", "doc_count" : 232, "key" : "F", "doc_count" : 219,
                System.out.println(genderBucket.getKeyAsString() + " ： " + genderBucket.getDocCount());
                ParsedAvg balanceAvg = genderBucket.getAggregations().get("avg_balance");
                System.out.println(balanceAvg.getValueAsString());
            }
            System.out.println("-----------\n");
        }
    }


    //聚合
//    public Map<String, Integer> polymerizationQuery() {
//        String aggName = "popularBrand";
//        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
//        //聚合
//        queryBuilder.addAggregation(AggregationBuilders.terms("popularBrand").field("brand"));
//        //查询并返回带聚合结果
//        AggregatedPage<Item> result = elasticsearchRestTemplate.queryForPage(queryBuilder.build(), Item.class);
//        //解析聚合
//        Aggregations aggregations = result.getAggregations();
//        //获取指定名称的聚合
//        StringTerms terms = aggregations.get(aggName);
//        //获取桶
//        List<StringTerms.Bucket> buckets = terms.getBuckets();
//        //遍历打印
//        Map<String, Integer> map = new HashMap<>();
//        for (StringTerms.Bucket bucket : buckets) {
//            map.put(bucket.getKeyAsString(), (int) bucket.getDocCount());
//            System.out.println("key = " + bucket.getKeyAsString());
//            System.out.println("DocCount = " + bucket.getDocCount());
//        }
//        return map;
//    }

//    public void other() throws IOException {
//        //（1）统计某个字段的数量
//        ValueCountAggregationBuilder vcb = AggregationBuilders.count("自定义").field("uid");
////（2）去重统计某个字段的数量
//        CardinalityAggregationBuilder cb = AggregationBuilders.cardinality("distinct_count_uid").field("uid");
////（3）聚合过滤
//        FilterAggregationBuilder fab = AggregationBuilders.filter("uid_filter").filter(QueryBuilders.queryStringQuery("uid:001"));
////（4）按某个字段分组
//        TermsAggregationBuilder tb = AggregationBuilders.terms("group_name").field("name");
////（5）求和
//        SumAggregationBuilder sumBuilder = AggregationBuilders.sum("sum_price").field("price");
////（6）求平均
//        AvgAggregationBuilder ab = AggregationBuilders.avg("avg_price").field("price");
////（7）求最大值
//        MaxAggregationBuilder mb = AggregationBuilders.max("max_price").field("price");
////（8）求最小值
//        MinAggregationBuilder min = AggregationBuilders.min("min_price").field("price");
////（9）按日期间隔分组
//        DateHistogramAggregationBuilder dhb = AggregationBuilders.dateHistogram("dh").field("date");
////（10）获取聚合里面的结果
//        TopHitsBuilder thb = AggregationBuilders.topHits("top_result");
////（11）嵌套的聚合
//        NestedAggregationBuilder nb = AggregationBuilders.nested("negsted_path").path("quests");
////（12）反转嵌套
//        AggregationBuilders.reverseNested("res_negsted").path("kps ");
//
//        //指定查询的索引
//        SearchRequest searchRequest = new SearchRequest("indexName");
//
//        //.构建过滤条件
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//
//        //构建聚合条件:根据state字段进行分组
//        AggregationBuilder aggregationBuilder = AggregationBuilders.terms("state").field("state");
//
////        构建查询条件:查询未删除
//        BoolQueryBuilder filterQuery = QueryBuilders.boolQuery();
//        filterQuery.must(QueryBuilders.termQuery("isDelete", 0));
//
////        将聚合条件和查询条件放入过滤条件中
//        searchSourceBuilder.aggregation(aggregationBuilder);
//        searchSourceBuilder.query(QueryBuilders.boolQuery().filter(filterQuery));
//
////        将过滤条件放入指定索引中
//        searchRequest.source(searchSourceBuilder);
//
////        查询
//        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//        //map:key=分组的状态,value=每组的个数
//        Map<String, Integer> stateCountMap = new LinkedHashMap<>();
//        //拿到聚合结果
//        Terms terms = response.getAggregations().get(Fn.getName(EsTaskEntity::getState));
//        //遍历聚合结果
//        for (Terms.Bucket bucket : terms.getBuckets()) {
//            //getKeyAsString():分组之后每个详细的值
//            //bucket.getDocCount() 分组之后每个值得个数
//            stateCountMap.put(bucket.getKeyAsString(), Long.valueOf(bucket.getDocCount()).intValue());
//        }
//
//    }

//    public Map search(Map<String, String> searchMap) {
//
//        Map<String, Object> resultMap = new HashMap<>();
//
////构建查询
//        if (searchMap != null) {
//            //构建查询条件封装对象
//            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
//            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
//
//            //按照关键字查询
//            if (StringUtils.isNotEmpty(searchMap.get("keywords"))) {
//                boolQuery.must(QueryBuilders.matchQuery("name", searchMap.get("keywords")).operator(Operator.AND));
//            }
//
//            //按照品牌进行过滤查询
//            if (StringUtils.isNotEmpty(searchMap.get("brand"))) {
//                boolQuery.filter(QueryBuilders.termQuery("brandName", searchMap.get("brand")));
//            }
//
//            //按照规格进行过滤查询
//            for (String key : searchMap.keySet()) {
//                if (key.startsWith("spec_")) {
//                    String value = searchMap.get(key).replace("%2B", "+");
//                    //spec_网络制式
//                    boolQuery.filter(QueryBuilders.termQuery(("specMap." + key.substring(5) + ".keyword"), value));
//                }
//            }
//
//            //按照价格进行区间过滤查询
//            if (StringUtils.isNotEmpty(searchMap.get("price"))) {
//                String[] prices = searchMap.get("price").split("-");
//                // 0-500 500-1000
//                if (prices.length == 2) {
//                    boolQuery.filter(QueryBuilders.rangeQuery("price").lte(prices[1]));
//                }
//                boolQuery.filter(QueryBuilders.rangeQuery("price").gte(prices[0]));
//            }
//            nativeSearchQueryBuilder.withQuery(boolQuery);
//
//            //按照品牌进行分组(聚合)查询
//            String skuBrand = "skuBrand";
//            nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms(skuBrand).field("brandName"));
//
//            //按照规格进行聚合查询
//            String skuSpec = "skuSpec";
//            nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms(skuSpec).field("spec.keyword"));
//
//            //开启分页查询
//            String pageNum = searchMap.get("pageNum"); //当前页
//            String pageSize = searchMap.get("pageSize"); //每页显示多少条
//            if (StringUtils.isEmpty(pageNum)) {
//                pageNum = "1";
//            }
//            if (StringUtils.isEmpty(pageSize)) {
//                pageSize = "20";
//            }
//
//            //设置分页
//            //第一个参数:当前页 是从0开始
//            //第二个参数:每页显示多少条
//            nativeSearchQueryBuilder.withPageable(PageRequest.of(Integer.parseInt(pageNum) - 1, Integer.parseInt(pageSize)));
//
//            //按照相关字段进行排序查询
//            // 1.当前域 2.当前的排序操作(升序ASC,降序DESC)
//            if (StringUtils.isNotEmpty(searchMap.get("sortField")) && StringUtils.isNotEmpty(searchMap.get("sortRule"))) {
//                if ("ASC".equals(searchMap.get("sortRule"))) {
//                    //升序
//                    nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort((searchMap.get("sortField"))).order(SortOrder.ASC));
//                } else {
//                    //降序
//                    nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort((searchMap.get("sortField"))).order(SortOrder.DESC));
//                }
//            }
//
//            //设置高亮域以及高亮的样式
//            HighlightBuilder.Field field = new HighlightBuilder.Field("name")//高亮域
//                    .preTags("<span style='color:red'>")//高亮样式的前缀
//                    .postTags("</span>");//高亮样式的后缀
//            nativeSearchQueryBuilder.withHighlightFields(field);
//
//            //开启查询
//            /**
//             * 第一个参数: 条件构建对象
//             * 第二个参数: 查询操作实体类
//             * 第三个参数: 查询结果操作对象
//             */
//            //封装查询结果
//            AggregatedPage<SkuInfo> resultInfo = elasticsearchRestTemplate.queryForPage(nativeSearchQueryBuilder.build(), SkuInfo.class, new SearchResultMapper() {
//                @Override
//                public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
//                    //查询结果操作
//                    List<T> list = new ArrayList<>();
//
//                    //获取查询命中结果数据
//                    SearchHits hits = searchResponse.getHits();
//                    if (hits != null) {
//                        //有查询结果
//                        for (SearchHit hit : hits) {
//                            //SearchHit转换为skuinfo
//                            SkuInfo skuInfo = JSON.parseObject(hit.getSourceAsString(), SkuInfo.class);
//
//                            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
//                            if (highlightFields != null && highlightFields.size() > 0) {
//                                //替换数据
//                                skuInfo.setName(highlightFields.get("name").getFragments()[0].toString());
//                            }
//
//                            list.add((T) skuInfo);
//                        }
//                    }
//                    return new AggregatedPageImpl<T>(list, pageable, hits.getTotalHits(), searchResponse.getAggregations());
//                }
//            });
//
//            //封装最终的返回结果
//            //总记录数
//            resultMap.put("total", resultInfo.getTotalElements());
//            //总页数
//            resultMap.put("totalPages", resultInfo.getTotalPages());
//            //数据集合
//            resultMap.put("rows", resultInfo.getContent());
//
//            //封装品牌的分组结果
//            StringTerms brandTerms = (StringTerms) resultInfo.getAggregation(skuBrand);
//            List<String> brandList = brandTerms.getBuckets().stream().map(bucket -> bucket.getKeyAsString()).collect(Collectors.toList());
//            resultMap.put("brandList", brandList);
//
//            //封装规格分组结果
//            StringTerms specTerms = (StringTerms) resultInfo.getAggregation(skuSpec);
//            List<String> specList = specTerms.getBuckets().stream().map(bucket -> bucket.getKeyAsString()).collect(Collectors.toList());
//            resultMap.put("specList", this.formartSpec(specList));
//
//            //当前页
//            resultMap.put("pageNum", pageNum);
//            return resultMap;
//        }
//        return null;
//    }


    /**
     * 原有数据
     * [
     * "{'颜色': '黑色', '尺码': '平光防蓝光-无度数电脑手机护目镜'}",
     * "{'颜色': '红色', '尺码': '150度'}",
     * "{'颜色': '黑色', '尺码': '150度'}",
     * "{'颜色': '黑色'}",
     * "{'颜色': '红色', '尺码': '100度'}",
     * "{'颜色': '红色', '尺码': '250度'}",
     * "{'颜色': '红色', '尺码': '350度'}",
     * "{'颜色': '黑色', '尺码': '200度'}",
     * "{'颜色': '黑色', '尺码': '250度'}"
     * ]
     * <p>
     * 需要的数据格式
     * {
     * 颜色:[黑色,红色],
     * 尺码:[100度,150度]
     * }
     */
    public Map<String, Set<String>> formartSpec(List<String> specList) {
        Map<String, Set<String>> resultMap = new HashMap<>();
        if (specList != null && specList.size() > 0) {
            for (String specJsonString : specList) {
                //将json数据转换为map
                Map<String, String> specMap = JSON.parseObject(specJsonString, Map.class);
                for (String specKey : specMap.keySet()) {
                    Set<String> specSet = resultMap.get(specKey);
                    if (specSet == null) {
                        specSet = new HashSet<String>();
                    }
                    //将规格的值放入set中
                    specSet.add(specMap.get(specKey));
                    //将set放入map中
                    resultMap.put(specKey, specSet);
                }
            }
        }
        return resultMap;
    }


}




