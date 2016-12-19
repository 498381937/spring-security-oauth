package com.didispace.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.didispace.mapper.TestMapper;
import com.didispace.model.SecuTrade;
import com.didispace.model.arg;

@RestController
public class ComputeController {

    private final Logger logger = Logger.getLogger(getClass());
    @Autowired
    private TestMapper mapper;
    @Autowired 
    private DiscoveryClient client;
    @Autowired
    PushService pushService;
  
    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        ServiceInstance instance = client.getLocalServiceInstance();
        Integer r = a + b;
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
        return r;
    }
    @RequestMapping(value = "/upload" ,method = RequestMethod.POST)
    public void upload(@RequestParam("files") MultipartFile file) {
        if(file!=null){
    	try{
        	FileUtils.writeByteArrayToFile(new File("E:/upload/"+file.getOriginalFilename()), file.getBytes());
        	
        }catch(IOException e){
        	e.printStackTrace();
        	
        }
        }else{
        	System.out.println("文件为空！");
        }
    }
    @RequestMapping(value = "/time" ,method = RequestMethod.POST)
    public void time(HttpServletRequest req,HttpServletResponse response) {
    	String sss=pushService.getAsyncUpdate();
    	response.setHeader("Access-Control-Allow-Origin", "*");	
    	try {
			PrintWriter out=response.getWriter();
			out.write(sss);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//    	SecuTrade a =new SecuTrade();
//    	
//    	if(sss!=null){
//    	a.setEndDateJson(sss);
//    	}else{
//    	a.setEndDateJson("时间未确定");
//    	}
//    	return a;
    }
    
    @RequestMapping(value = "/trade" ,method = RequestMethod.POST)
    public  List<SecuTrade> Trading(arg args){
//    	@RequestParam String startdate,@RequestParam String endDate, @RequestParam Integer page,@RequestParam Integer pagesize
    	
    	String startdate=args.getStartdate();
    	String endDate=args.getEndDate();
    	int page=args.getPage();
    	int pagesize=args.getPagesize();
    	List<SecuTrade> list=new ArrayList<SecuTrade>();
    	Date StartDate=null;
    	Date EndDate=null;
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	try {
			StartDate=sdf.parse(startdate);
			EndDate=sdf.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	list=mapper.Trade(StartDate, EndDate);
    	int size=list.size();
    	int pageCount = (size + pagesize -1) / pagesize;
    	if(page==(pageCount-1)){
    		list=list.subList((pageCount-1)*pagesize, size);
    	}else{
    	list=list.subList(page*pagesize, page*pagesize+pagesize);
    	}
    	for(SecuTrade temp:list){
    		String[] a=temp.getEndDateJson().split(" ");
    		temp.setEndDateJson(a[0]);
    		temp.setSize(size);
    		if(temp.getEntrustDirection().endsWith("1")){
    			temp.setEntrustDirection("买入");
    		}else if(temp.getEntrustDirection().equals("2")){
    			temp.setEntrustDirection("卖出");
    		}
    	}
    	return list;
    }
}