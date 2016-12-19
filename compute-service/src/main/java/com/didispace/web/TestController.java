package com.didispace.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller

public class TestController {

 
 @RequestMapping(value = "/login")
 public String login() {
 	return "Login";
 }
 @RequestMapping(value = "/newfile")
 public String newfile() {
 	
 	return "NewFile";
 }
}
