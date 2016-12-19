package com.didispace.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=3600*6)
public class RedisConfig {	
	@Autowired
	JedisConnectionFactory jedis;
	
//	@Autowired
//     public JedisConnectionFactory connectionFactory() {
//    	 return jedis;
//     }
}
