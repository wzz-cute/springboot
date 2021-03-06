package com.wzz.es;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wzz.es.config.ElasticSearchConfig;
import com.wzz.util.Query;
import lombok.Data;
import lombok.ToString;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    private RestHighLevelClient esRestClient;

    /**
     * ????????????
     * ?????????json???????????? ??????????????????????????????????????????????????????
     */
    @org.junit.Test
    public void searchAllData() throws IOException {
        // ????????????????????????
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("newbank");

        //?????????????????????
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.matchAllQuery());//???json???????????? ????????????

//        searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));

        //trem??????  trem ???match????????????bool???????????????????????????
//        searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.termQuery("age","20")));

        //??????
//        searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.matchQuery("address","mill lane")));


        //????????????
//        searchRequest.source(new SearchSourceBuilder().fetchSource(new String[]{"address"},new String[]{}));//???????????????????????????

        //????????????
//        searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.boolQuery()
//                        .must(QueryBuilders.termQuery("age",28)) //?????????20
//                        .must(QueryBuilders.matchQuery("address","mill lane"))//??????address??????mill
////                                                                .mustNot(QueryBuilders.matchQuery("first_name","Forbes")) //???????????????
//
////                                                                .should(QueryBuilders.matchQuery("age", 20))//???????????? OR
////                                                                .should(QueryBuilders.matchQuery("age", 22))
//        ));

        //????????????
//        searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.rangeQuery("age").gte(15).lte(30)));

        //????????????

        //????????????
//        searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.rangeQuery("age").from("12").to("20")));//?????????
        //?????????
//        searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.rangeQuery("age").from("12", false).to("20", false)));

        //??????
//        searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.rangeQuery("age").lt("12")));
        //????????????
//        searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.rangeQuery("age").lte("12")));

        //????????????
//        searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.wildcardQuery("lastname","*du*")));

        //????????????
//        searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.regexpQuery("lastname","".toLowerCase())));

        //????????????????????????
//        searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.multiMatchQuery("Adams","lastname","firstname")));


        //????????????
//        searchRequest.source(new SearchSourceBuilder().from(0).size(30));

        //??????
//        searchRequest.source(new SearchSourceBuilder()
//                .query(QueryBuilders.termQuery("city","Brogan"))
//                .highlighter(new HighlightBuilder()
//                .preTags("<font color='red'>")
//                        .postTags("</font>")
//                        .field("city")
//        ));

        //??????
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//
//        AggregationBuilder aggregationBuilder = AggregationBuilders.terms("ageGroup").field("age");
//        builder.aggregation(aggregationBuilder);
//
//        searchRequest.source(builder);


        SearchResponse search = esRestClient.search(searchRequest, ElasticSearchConfig.COMMON_OPTIONS);

        SearchHits result = search.getHits();

//        System.out.println(JSON.toJSONString(result));
        System.out.println("?????????" + search.getTook());
        System.out.println("???????????????" + result.getTotalHits());
        SearchHit[] hits = result.getHits();
        for (SearchHit hit : hits) {
            Account bankBean = JSON.parseObject(hit.getSourceAsString(),Account.class);
            System.out.println(JSON.toJSONString(bankBean));
        }
    }

    /**
     * ????????????:???bank?????????address?????????mill????????????????????????????????????????????????????????????
     *
     * @throws IOException
     */
    @org.junit.Test
    public void searchData() throws IOException {
        //1. ??????????????????
        SearchRequest searchRequest = new SearchRequest();

        //1.1???????????????
        searchRequest.indices("bank");
        //1.2?????????????????????
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchQuery("address", "Mill"));


        //1.2.1)??????????????????????????????
        TermsAggregationBuilder ageAgg = AggregationBuilders.terms("ageAgg").field("age").size(10);
        sourceBuilder.aggregation(ageAgg);

        //1.2.2)??????????????????
        AvgAggregationBuilder ageAvg = AggregationBuilders.avg("ageAvg").field("age");
        sourceBuilder.aggregation(ageAvg);
        //1.2.3)??????????????????
        AvgAggregationBuilder balanceAvg = AggregationBuilders.avg("balanceAvg").field("balance");
        sourceBuilder.aggregation(balanceAvg);

        System.out.println("???????????????" + sourceBuilder);
        searchRequest.source(sourceBuilder);
        //2. ????????????
        SearchResponse searchResponse = esRestClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("???????????????" + searchResponse);

        //3. ????????????????????????Bean
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit searchHit : searchHits) {
            String sourceAsString = searchHit.getSourceAsString();
            Account account = JSON.parseObject(sourceAsString, Account.class);
            System.out.println(account);

        }

        //4. ??????????????????
        Aggregations aggregations = searchResponse.getAggregations();

        Terms ageAgg1 = aggregations.get("ageAgg");

        for (Terms.Bucket bucket : ageAgg1.getBuckets()) {
            String keyAsString = bucket.getKeyAsString();
            System.out.println("?????????" + keyAsString + " ==> " + bucket.getDocCount());
        }
        Avg ageAvg1 = aggregations.get("ageAvg");
        System.out.println("???????????????" + ageAvg1.getValue());

        Avg balanceAvg1 = aggregations.get("balanceAvg");
        System.out.println("???????????????" + balanceAvg1.getValue());
    }

    //?????????????????????es
    //?????????????????????
    @org.junit.Test
    public void indexData() throws IOException {
        IndexRequest indexRequest = new IndexRequest("users");
//        indexRequest.id("3");

//        indexRequest.source("userName","zhangsan","age",18,"gender","???");//???????????????

        //??????????????? ???json
//        User user = new User();
//        user.setUserName("hrh");
//        user.setAge(3);
//        user.setGender("???");
//        user.setXingQu("????????????");
//        String jsonString = JSON.toJSONString(user);

        Account Account = new Account();
        Account.setAccount_number(673);
        Account.setAddress("??????");
        Account.setAge(80);
        Account.setBalance(10);
        Account.setCity("??????");
        Account.setEmail("54sb@qq.com");
        Account.setEmployer("Scentric");
        Account.setFirstname("h");
        Account.setGender("???");
        Account.setLastname("rh");
        Account.setState("WA");
        String jsonString = JSON.toJSONString(Account);

        indexRequest.source(jsonString, XContentType.JSON);

        //????????????
        IndexResponse index = esRestClient.index(indexRequest, ElasticSearchConfig.COMMON_OPTIONS);

        System.out.println(index);
    }

    /**
     * ????????????
     *
     * @throws IOException
     */
    @org.junit.Test
    public void searchIndex() throws IOException {
        // ????????????
        // ????????????
        GetIndexRequest getIndexRequest = new GetIndexRequest("users");
        GetIndexResponse getIndexResponse = esRestClient.indices().get(getIndexRequest, ElasticSearchConfig.COMMON_OPTIONS);
        // ????????????
        System.out.println("???????????????Aliases ???" + getIndexResponse.getAliases());
        System.out.println("???????????????Mappings ???" + getIndexResponse.getMappings());
        System.out.println("???????????????Settings ???" + getIndexResponse.getSettings());
    }

    @org.junit.Test
    public void deleteIndex() throws IOException {
        // ????????????
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("users");
        AcknowledgedResponse response = esRestClient.indices().delete(deleteIndexRequest, ElasticSearchConfig.COMMON_OPTIONS);
        // ????????????
        System.out.println("???????????? ???" + response.isAcknowledged());
    }

    @org.junit.Test
    public void bulkAdd() throws IOException {
        //1.???es??????????????????????????????????????????doc/json/product-mapping.json???
        List<Account> Accounts = new ArrayList<>();
//        Accounts.add(new Account(1111, 13, "w1", "zz1", 13));
//        Accounts.add(new Account(2222, 18, "w2", "zz2", 13));
//        Accounts.add(new Account(3333, 18, "w34", "zz3", 13));
//        Accounts.add(new Account(4444, 18, "w5", "zz4", 13));

        //2. ???ES?????????????????????
        BulkRequest bulkRequest = new BulkRequest();
        for (Account bean : Accounts) {
            //??????????????????
//            IndexRequest indexRequest = new IndexRequest(EsConstant.PRODUCT_INDEX);
            IndexRequest indexRequest = new IndexRequest("newbank");
//            indexRequest.id(skuEsModel.getSkuId().toString());
            String jsonString = JSON.toJSONString(bean);
            indexRequest.source(jsonString, XContentType.JSON);
            bulkRequest.add(indexRequest);
        }


        BulkResponse bulk = esRestClient.bulk(bulkRequest, ElasticSearchConfig.COMMON_OPTIONS);

        //TODO ??????????????????
        boolean hasFailures = bulk.hasFailures();

        List<String> collect = Arrays.asList(bulk.getItems()).stream().map(item -> {
            return item.getId();
        }).collect(Collectors.toList());
        System.out.println(hasFailures);
        System.out.println(JSON.toJSONString(collect));
    }

    @org.junit.Test
    public void updateDocument() throws IOException {
        // ????????????
        UpdateRequest updateRequest = new UpdateRequest();
        //??????????????????
        updateRequest.index("newbank").id("hX5U5IAB88RfQVznxCer");
        //???????????????????????????????????????
        updateRequest.doc(XContentType.JSON, "first_name", "111");
        //?????????????????????????????????
        UpdateResponse updateResponse = esRestClient.update(updateRequest, ElasticSearchConfig.COMMON_OPTIONS);

        System.out.println("index:" + updateResponse.getIndex());
        System.out.println("id:" + updateResponse.getId());
        System.out.println("result:" + updateResponse.getResult());
    }

    /**
     * ????????????
     *
     * @throws IOException
     */
    @org.junit.Test
    public void searchDocument() throws IOException {
        GetRequest request = new GetRequest().index("newbank").id("hX5U5IAB88RfQVznxCer");
        //??????????????????????????????????????????
        GetResponse response = esRestClient.get(request, ElasticSearchConfig.COMMON_OPTIONS);
        System.out.println("index:" + response.getIndex());
        System.out.println("type:" + response.getType());
        System.out.println("id:" + response.getId());
        System.out.println(response.getSourceAsString());
    }

    /**
     * ????????????
     */
    @org.junit.Test
    public void deleteDocument() throws IOException {
        DeleteRequest request = new DeleteRequest();
        request.index("newbank").id("hX5U5IAB88RfQVznxCer");

        DeleteResponse response = esRestClient.delete(request, ElasticSearchConfig.COMMON_OPTIONS);
        System.out.println(response.toString());
    }

    /**
     * ????????????
     *
     * @throws IOException
     */
    @org.junit.Test
    public void bulkDeleteDocument() throws IOException {
        BulkRequest request = new BulkRequest();
        request.add(new DeleteRequest().index("user").id("02"));
        request.add(new DeleteRequest().index("user").id("03"));
        request.add(new DeleteRequest().index("user").id("04"));
        BulkResponse response = esRestClient.bulk(request, ElasticSearchConfig.COMMON_OPTIONS);
        System.out.println(response.getTook());
        System.out.println(response.getItems());
    }

    @ToString
    @Data
    static class Account {
        private int account_number;
        private int balance;
        private String firstname;
        private String lastname;
        private int age;
        private String gender;
        private String address;
        private String employer;
        private String email;
        private String city;
        private String state;
    }



    /**
     * ????????????????????????
     */
    @Data
    class User {
        private String userName;
        private String gender;
        private Integer age;
        private String xingQu;
    }


}
