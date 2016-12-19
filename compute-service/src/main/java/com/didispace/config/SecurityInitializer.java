package com.didispace.config;

import org.springframework.core.annotation.Order;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
@Order(100)
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
	 public SecurityInitializer() {
         super(SecurityConfig.class, RedisConfig.class);
         
 }
}
