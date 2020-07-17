package com.ustc.dianping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.ustc.dianping"})
@MapperScan("com.ustc.dianping.mapper")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableScheduling
public class SearchRecommendApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchRecommendApplication.class);
    }
}
