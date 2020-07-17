package com.ustc;

import com.ustc.dianping.SearchRecommendApplication;
import com.ustc.dianping.mapper.CategoryModelMapper;
import com.ustc.dianping.mapper.CouponMapper;
import com.ustc.dianping.model.CategoryModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = SearchRecommendApplication.class)
public class SearchRecommendApplicationTests {

	@Autowired
	private CouponMapper couponMapper;

	@Test
	public void contextLoads() {
		System.out.println(couponMapper.selectByPrimaryKey(1).getEndAt());
	}
}
