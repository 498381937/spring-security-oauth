package com.didispace.config;

import javax.swing.text.html.HTML;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.didispace.Interceptor;

@Configuration
@EnableScheduling
@Conditional(ConditionalTest.class)
public class SpringConfiguration extends WebMvcConfigurerAdapter {
	@Bean
	public Interceptor interceptor() {
		return new Interceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor()).addPathPatterns("/time", "/trade","/add","/login");
	}
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(1000000);
		multipartResolver.setDefaultEncoding("UTF-8");
		return multipartResolver;
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/login").setViewName("/Login");
//		registry.addViewController("/newfile").setViewName("/NewFile");
		registry.addViewController("/").setViewName("index");
//		registry.addViewController("/login").setViewName("Login");
	}
}
