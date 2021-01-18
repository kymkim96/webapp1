package com.mycompany.webapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.controller.Ch13Controller;

@Service
public class Ch13Service7 {
	private static final Logger logger = LoggerFactory.getLogger(Ch13Service7.class);
	
	@Value("${fileupload}")
	private String saveDirPath;
	
	public void method() {
		logger.info("실행");
		
		logger.info("fileupload: " + saveDirPath);
	}
	
}
