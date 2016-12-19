package com.didispace.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;
@Service
public class PushService {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String a="";
	public  String getAsyncUpdate(){
		return a;
	}
	
	@Scheduled(cron="0/1 * *  * * ? ")
	public void refresh(){
		if(a!=null){
			Date date=new Date();
			 a=sdf.format(date);
			
		}
	}
}
