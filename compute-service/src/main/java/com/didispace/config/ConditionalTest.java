package com.didispace.config;

import java.lang.reflect.Method;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ConditionalTest extends SpringBootCondition{

	@Override
	public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
	
		
		Boolean flag=null;
		Conditional con=null;
		Method method=null;
		String name=null;
		Class a=null;
//			try {
//				method=SpringConfiguration.class.getMethod("multipartResolver");
//				name=method.getAnnotation(Configuration.class).annotationType().getName();
//				flag = metadata.isAnnotated(name);
//			} catch (NoSuchMethodException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (SecurityException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		flag=metadata.isAnnotated(SpringConfiguration.class.getAnnotation(Conditional.class).annotationType().getName());
		if(flag==true){
		return  ConditionOutcome.match("xixihaha");
		}else{
			return ConditionOutcome.noMatch("xixihaha");
		}
	}
		
}
