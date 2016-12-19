package com.didispace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class MybatisConfig {
	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    driverManagerDataSource.setUrl("jdbc:sqlserver://10.1.5.149:1433;DatabaseName=FAISDB");
	    driverManagerDataSource.setUsername("jdbc");
	    driverManagerDataSource.setPassword("jdbc");
	    return driverManagerDataSource;
	}
}
