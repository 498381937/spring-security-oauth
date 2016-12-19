package com.didispace;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogDemo {
	final static Logger logger = LoggerFactory.getLogger(LogDemo.class);  
	public static void main(String[] args) {
		logger.trace("======trace");  
		logger.debug("======debug");  
		logger.info("======info");  
		logger.warn("======warn");  
		logger.error("======error");
		BigDecimal a=new BigDecimal(0.623012);
		BigDecimal b=new BigDecimal(0.6232342);
		Double sum=0.0;
		sum=a.divide(b, 5, RoundingMode.HALF_UP).doubleValue();
		System.out.println(sum);

	}

}
