package com.shop.dubbo;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication(scanBasePackages = "com.shop.dubbo")
@MapperScan(basePackages = "com.shop.dubbo.dao")
public class ShopUserProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopUserProviderApplication.class, args);
    }

}
