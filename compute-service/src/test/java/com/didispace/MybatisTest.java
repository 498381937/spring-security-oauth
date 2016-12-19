package com.didispace;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didispace.mapper.TestMapper;
import com.didispace.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ComputeServiceApplication.class)
public class MybatisTest {
	private Logger log = LoggerFactory.getLogger(MybatisTest.class);
	@Autowired
	private TestMapper userMapper;
	
	@Test
	@Rollback
	public void findByName(){
		List<User> user=new ArrayList<>();
		user=userMapper.findbyName("管理员");
		for(int i=0;i<user.size();i++){
//		System.out.println("ID:"+user.get(i).getId()+"Name:"+user.get(i).getName()+"Age:"+user.get(i).getAge());
			log.info("ID:"+user.get(i).getId()+"Name:"+user.get(i).getName()+"Age:"+user.get(i).getAge());
		}
	}
}
