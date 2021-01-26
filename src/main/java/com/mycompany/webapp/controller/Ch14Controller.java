package com.mycompany.webapp.controller;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Ch14Board;
import com.mycompany.webapp.dto.Ch14Employee;
import com.mycompany.webapp.service.BoardService;
import com.mycompany.webapp.service.Ch14EmployeeService;

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
	public String conntest(Model model) {
		
		try {
			// 커넥션풀에서 커넥션 대여
			Connection conn = dataSource.getConnection();
			model.addAttribute("result", "연결 성공");
			// 대여한 커넥션 반납
			conn.close();
		} catch (SQLException e) {
			model.addAttribute("result", "연결 실패");
			e.printStackTrace();
		}
		
		return "ch14/conntest";
	}
	
	@GetMapping("/jsonresponse1")
	public void jsonResponse1(HttpServletResponse response) throws Exception {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		// JSON: JavaScript Object Notations (자바스크립트 객체 표기법)
		
//		pw.println("{");
//		pw.println("\"name\": \"홍길동\",");
//		pw.println("\"age\": 30,");
//		pw.println("\"car\": {\"kind\": \"그랜저\", \"color\": \"흰색\"},");
//		pw.println("\"hobby\": [\"영화\", \"여행\", \"수영\"]");
//		pw.println("}");
		
		JSONObject root = new JSONObject();
		root.put("name", "홍길동");
		root.put("age", 30);
		
		JSONObject car = new JSONObject();
		car.put("kind", "그랜저");
		car.put("color", "흰색");
		root.put("car", car);
		
		JSONArray hobby = new JSONArray();
		hobby.put("영화");
		hobby.put("여행");
		hobby.put("수영");
		root.put("hobby", hobby);
		
		String jsonString = root.toString();
		pw.println(jsonString);
		
		pw.flush();
		pw.close();
		
	}
	
	@GetMapping("/jsonresponse2")
	public void jsonResponse2(HttpServletResponse response) throws Exception {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
//		pw.println("[");
//		pw.println("	{ \"bno\": 1, \"btitle\": \"제목1\", \"bwriter\": \"글쓴이1\" },");
//		pw.println("	{ \"bno\": 2, \"btitle\": \"제목2\", \"bwriter\": \"글쓴이2\" },");
//		pw.println("	{ \"bno\": 3, \"btitle\": \"제목3\", \"bwriter\": \"글쓴이3\" }");
//		pw.println("]");
		
		JSONArray root = new JSONArray();
		for (int i=1; i<=3; i++) {
			JSONObject board = new JSONObject();
			board.put("bno",  i);
			board.put("btitle",  "제목"+i);
			board.put("bwriter",  "글쓴이"+i);
			root.put(board);
		}
		
		String jsonString = root.toString();
		pw.println(jsonString);
		
		pw.flush();
		pw.close();
	}
	
	@Resource
	private Ch14EmployeeService employeeDaoService;
	
	@GetMapping("/employee")
	public void employee(int employee_id, HttpServletResponse response) throws Exception {
		
		Ch14Employee emp = employeeDaoService.getEmployee(employee_id);
		logger.info("employee_id: " + emp.getEmployee_id());
		logger.info("first_name: " + emp.getFirst_name());
		logger.info("last_name: " + emp.getLast_name());
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		JSONObject root = new JSONObject();
		root.put("eno", emp.getEmployee_id());
		root.put("first", emp.getFirst_name());
		root.put("last", emp.getLast_name());
		
		String jsonString = root.toString();
		pw.println(jsonString);
		pw.flush();
		pw.close();
	}
	
	@Resource
	private BoardService boardService;
	
	@GetMapping("/boardlist")
	public String boardList(Model model) {
		
		List<Ch14Board> list = boardService.getBoardList();
		model.addAttribute("list", list);
		
		return "ch14/boardlist";
	}
	
	@GetMapping("/boardsave")
	public String boardSave() {
		
//		for (int i=1; i<=100; i++) {
//			Ch14Board board = new Ch14Board();
//			board.setBtitle("제목" + i);
//			board.setBcontent("내용" + i);
//			board.setBwriter("winter");
//			boardService.saveBoard(board);
//		}
		
		return "redirect:/ch14/boardlist";
	}
}





















