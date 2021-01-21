package com.mycompany.webapp.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mycompany.webapp.dto.Ch14Employee;

@Repository
public class Ch14EmployeeDao {

	private static final Logger logger = LoggerFactory.getLogger(Ch14EmployeeDao.class);
	
	@Resource
	private SqlSessionTemplate sst;
	
	public Ch14Employee selectByPk(int employee_id) {
		
		Ch14Employee emp = sst.selectOne("mybatis.mapper.employees.selectByPk", employee_id);
		return emp;
	}
	
}
