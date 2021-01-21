package com.mycompany.webapp.controller;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dao.Ch14EmployeeDao;
import com.mycompany.webapp.dto.Ch14Employee;

@Controller
@RequestMapping("/ch14")
public class Ch14Controller {
	
	// Field
	private static final Logger logger = LoggerFactory.getLogger(Ch14Controller.class);
	
	@GetMapping("/content")
	public String content() {
		logger.info("실행");
		return "ch14/content";
	}
	
	@Resource
	private DataSource dataSource;
	
	@GetMapping("/conntest")
	public String conntest() {
		try {
			// 커넥션풀에서 커넥션 대여
			Connection conn = dataSource.getConnection();
			logger.info("연결 성공");
			// 대여한 커넥션 반납
			conn.close();
		} catch (SQLException e) {
			logger.info("연결 실패");
			e.printStackTrace();
		} finally {
			
		}
		return "ch14/content";
	}
	
	@Resource
	private Ch14EmployeeDao employeeDao;
	
	@GetMapping("/employee")
	public String employee(int employee_id) {
		
		Ch14Employee emp = employeeDao.selectByPk(employee_id);
		logger.info("employee_id: " + emp.getEmployee_id());
		logger.info("first_name: " + emp.getFirst_name());
		logger.info("last_name: " + emp.getLast_name());
		
		return "ch14/content";
	}
	
}





















