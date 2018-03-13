package com.facs.agriculture.test.config;

import com.facs.basic.framework.core.props.BaseProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.nio.charset.StandardCharsets;

/**
 * 测试配置信息
 *
 * @author luke
 * 2017-12-14
 */
@Configuration
@MapperScan(basePackages={"com.facs.agriculture.dao"})
@ComponentScan({"com.facs.agriculture","com.facs.basic.framework","com.facs.framework.plugin"})
public class TestsConfiguration {

    @Bean
    BaseProperties baseProperties() {
        BaseProperties baseProperties = new BaseProperties();
        baseProperties.setLocation(new ClassPathResource("application-dev.properties"));
        baseProperties.setFileEncoding(StandardCharsets.UTF_8.name());
        return baseProperties;
    }
}
