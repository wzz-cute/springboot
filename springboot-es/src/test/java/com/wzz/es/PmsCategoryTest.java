package com.wzz.es;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzz.es.domain.PmsCategory;
import com.wzz.es.service.IPmsCategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PmsCategoryTest {

    @Autowired
    private IPmsCategoryService pmsCategoryService;

    @Test
    public void testAddMybatisPlus(){
//        PmsCategory pmsCategory = new PmsCategory();
//        pmsCategory.setName("娃娃八点法");
//        pmsCategory.setParentCid(2l);
//        pmsCategory.setCatLevel(1l);
//        System.out.println(pmsCategoryService.addPmsCategory(pmsCategory));

        IPage<PmsCategory> pmsCategory = pmsCategoryService.getPmsCategory(new PmsCategory());
        System.out.println(JSON.toJSONString(pmsCategory));
    }

}
