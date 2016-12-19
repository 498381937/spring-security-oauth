package com.didispace;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor extends HandlerInterceptorAdapter{
	//重写preHandle方法设置处理前的操作
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long startTime=System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		String uri=request.getRequestURI();
		System.out.println(uri);
		return true;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long startTime=(long) request.getAttribute("startTime");
		long endTime=System.currentTimeMillis();
		Method a=Interceptor.class.getMethod("preHandle", HttpServletRequest.class,HttpServletResponse.class,Object.class);
		System.out.println(a.getName());
		System.out.println("本次请求处理时间为："+new Long(endTime-startTime)+"ms");
		
	
	}
}
