package com.mycompany.webapp.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.service.Ch13Service1;
import com.mycompany.webapp.service.Ch13Service2;
import com.mycompany.webapp.service.Ch13Service3;
import com.mycompany.webapp.service.Ch13Service4;
import com.mycompany.webapp.service.Ch13Service5;
import com.mycompany.webapp.service.Ch13Service6;
import com.mycompany.webapp.service.Ch13Service7;

@Controller
@RequestMapping("/ch13")
public class Ch13Controller {
	
	// Field
	private static final Logger logger = LoggerFactory.getLogger(Ch13Controller.class);
	
	// 방법 1
	@Resource
	private Ch13Service1 service1;
	@Resource
	private Ch13Service2 service2;
	
	private Ch13Service3 service3;
	private Ch13Service4 service4;
	
	@Resource
	private Ch13Service5 service5;
	
	@Resource
	private Ch13Service6 service6;
	
	@Resource
	private Ch13Service7 service7;
	
	// Constructor
	// 방법 2
//	@Autowired 
//	public Ch13Controller(Ch13Service1 service1, Ch13Service2 service2) { 
//		logger.info("실행");
//		this.service1 = service1;
//		this.service2 = service2;
//	}
	 
	
	// Method
	// 방법 3
//	@Autowired
//	public void setService1(Ch13Service1 service1) {
//		logger.info("실행");
//		this.service1 = service1;
//	}
//	
//	@Autowired
//	public void setService2(Ch13Service2 service2) {
//		logger.info("실행");
//		this.service2 = service2;
//	}
	
	public void setService3(Ch13Service3 service3) {
		logger.info("실행");
		this.service3 = service3;
	}
	
	public void setService4(Ch13Service4 service4) {
		logger.info("실행");
		this.service4 = service4;
	}
	
	@GetMapping("/content")
	public String content() {
		logger.info("실행");
		return "ch13/content";
	}
	
	@GetMapping("/service1")
	public String service1() {
		service1.method();
		return "redirect:/ch13/content";
	}
	
	@GetMapping("/service2")
	public String service2() {
		service2.method();
		return "redirect:/ch13/content";
	}
	
	@GetMapping("/service3")
	public String service3() {
		service3.method();
		return "redirect:/ch13/content";
	}
	
	@GetMapping("/service4")
	public String service4() {
		service4.method();
		return "redirect:/ch13/content";
	}
	
	@GetMapping("/service5")
	public String service5() {
		service5.method();
		return "redirect:/ch13/content";
	}
	
	@GetMapping("/service6")
	public String service6() {
		logger.info("실행");
		service6.method();
		return "redirect:/ch13/content";
	}
	
	@GetMapping("/fileupload")
	public String fileUpload(@Value("${fileupload}") String saveDirPath) {
		logger.info("실행");
		
		String saveFilePath = "";
		logger.info("fileupload: " + saveDirPath);
		service7.method();
		return "redirect:/ch13/content";
	}
}





















