package org.meteor.sams;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("org.meteor.sams.mapper")
@EnableCaching
@EnableScheduling
//public class SamsApplication extends SpringBootServletInitializer {
public class SamsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SamsApplication.class, args);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(SamsApplication.class);
//    }
}
