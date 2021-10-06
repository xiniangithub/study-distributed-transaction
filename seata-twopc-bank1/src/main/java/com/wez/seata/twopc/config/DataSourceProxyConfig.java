package com.wez.seata.twopc.config;

import io.seata.rm.datasource.DataSourceProxy;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * DatabaseConfiguration
 *
 * @Author wez
 * @Time 2021/9/30 22:26
 */
@Configuration
public class DataSourceProxyConfig {

    @Primary
    @Bean
    public SqlSessionFactory dataSource(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(new DataSourceProxy(dataSource));
        return sqlSessionFactoryBean.getObject();
    }
}
