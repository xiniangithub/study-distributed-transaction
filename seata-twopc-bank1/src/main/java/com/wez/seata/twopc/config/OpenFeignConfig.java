package com.wez.seata.twopc.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenFeignConfig
 *
 * @Author wez
 * @Time 2021/10/1 9:24
 */
@Configuration
public class OpenFeignConfig {

    @Bean
    public Logger.Level openFeignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
