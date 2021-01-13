package com.mycompany.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ch02")	// Controller에는 반드시 RequestMapping으로 해야 함
public class Ch02Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch02Controller.class);
	
	@GetMapping("/content")
	public String content() {
		logger.info("실행");
		return "ch02/content";
	}
	
	// @RequestMapping("/getMethod")	// GET, POST 둘 다 지원
	// @RequestMapping(value="/getMethod", method=RequestMethod.GET)
	// @RequestMapping(value="/getMethod", method=RequestMethod.POST)
	@GetMapping("/getMethod")
	public String getMethod() {
		logger.info("실행");
		return "ch02/content";
	}
	
	// @RequestMapping(value="/postMethod", method=RequestMethod.POST)
	@PostMapping("/postMethod")
	public String postMethod() {
		logger.info("실행");
		return "ch02/content";
	}
	
	// @RequestMapping(value="/join", method=RequestMethod.GET)
	@GetMapping("/join")
	public String joinForm() {
		logger.info("회원가입 양식을 보냄");
		return "ch02/joinForm";
	}
	
	// @RequestMapping(value="/joinAsync", method=RequestMethod.GET)
	@GetMapping("/joinAsync")
	public String joinFormAsync() {
		logger.info("회원가입 양식을 보냄");
		return "ch02/joinFormAsync";
	}
	
	// @RequestMapping(value="/join", method=RequestMethod.POST)
	@PostMapping("/join")
	public String join() {
		logger.info("회원가입 처리함");
		return "ch02/content";
	}
}










