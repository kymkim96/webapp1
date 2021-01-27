package com.mycompany.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.dto.Ch14Board;
import com.mycompany.webapp.dto.Ch14Employee;
import com.mycompany.webapp.dto.Ch14Member;
import com.mycompany.webapp.dto.Ch14Order;
import com.mycompany.webapp.dto.Ch14OrderItem;
import com.mycompany.webapp.dto.Ch14Pager;
import com.mycompany.webapp.service.BoardService;
import com.mycompany.webapp.service.Ch14EmployeeService;
import com.mycompany.webapp.service.Ch14MemberService;
import com.mycompany.webapp.service.Ch14OrderService;

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
	
	@GetMapping("/boardlist2")
	public String boardList2(
			@RequestParam(defaultValue="1") int pageNo,
			Model model) {
		
		int totalRows = boardService.getTotalRows();
		Ch14Pager pager = new Ch14Pager(6, 10, totalRows, pageNo);
		
		List<Ch14Board> list = boardService.getBoardList(pager);
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
		
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
	
	@GetMapping("/boardwrite")
	public String boardWriteForm() {
		
		return "ch14/boardwrite";
	}
	
	@PostMapping("/boardwrite")
	public String boardWrite(Ch14Board board, HttpSession session) throws Exception {
		
		String mid = (String) session.getAttribute("sessionMid");
		
		// 파일 정보 얻기
		MultipartFile mf = board.getBattach();
		if (!mf.isEmpty()) {
			board.setBattachoname(mf.getOriginalFilename());
			String saveName = new Date().getTime() + "-" + mf.getOriginalFilename();
			board.setBattachsname(saveName);
			board.setBattachtype(mf.getContentType());
			
			// 파일 저장
			File saveFile = new File("D:/MyWorkspace/uploadfiles/boards/" + saveName);
			mf.transferTo(saveFile);
		}
		
		board.setBwriter(mid);
		
		boardService.saveBoard(board);
		return "redirect:/ch14/boardlist2";
	}
	
	@GetMapping("/join")
	public String joinForm() {
		
		return "ch14/join";
	}
	
	@Resource
	private Ch14MemberService memberService;
	
	@PostMapping("/join")
	public String join(Ch14Member member) throws Exception {
		
		// 파일 정보 얻기
		MultipartFile mf = member.getMphoto();
		if (!mf.isEmpty()) {
			member.setMphotooname(mf.getOriginalFilename());
			String saveName = new Date().getTime() + "-" + mf.getOriginalFilename();
			member.setMphotosname(saveName);
			member.setMphototype(mf.getContentType());
			
			// 파일 저장
			File saveFile = new File("D:/MyWorkspace/uploadfiles/members/" + saveName);
			mf.transferTo(saveFile);
		}
		
		// DB에 저장
		memberService.join(member);
		return "redirect:/ch14/boardlist2";
	}
	
	@GetMapping("/login")
	public String loginForm() {
		
		return "ch14/login";
	}
	
	@PostMapping("/login")
	public void login(Ch14Member member, HttpServletResponse response, HttpSession session) throws Exception {
		// success, wrongMid, wrongPassword
		String result = memberService.login(member);
		if (result.equals("success")) {
			session.setAttribute("sessionMid", member.getMid());
		}
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		// { "result": "success" }
		JSONObject root = new JSONObject();
		root.put("result", result);
		
		pw.println(root.toString());
		
		pw.flush();
		pw.close();
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/ch14/boardlist2";
	}
	
	@GetMapping("/mphoto")
	public void mphoto(String mid, HttpServletResponse response, HttpSession session) throws Exception {
		
		if (mid == null) {
			mid = (String) session.getAttribute("sessionMid");
		}
		
		Ch14Member member = memberService.getMember(mid);
		
		String filePath = null;
		if (member.getMphotosname() != null) {
			String mphotosname = member.getMphotosname();
			filePath = "D:/MyWorkspace/uploadfiles/members/" + mphotosname;
			
			response.setContentType(member.getMphototype());
			
			String mphotooname = member.getMphotooname();
			mphotooname = new String(mphotooname.getBytes("UTF-8"), "ISO-8859-1");	
			response.setHeader("Content-Disposition", "attachment; filename=\"" + mphotooname + "\"");
		} else {
			filePath = "D:/MyWorkspace/uploadfiles/members/defaultphoto.png";
			response.setContentType("image/png");
		}
		
		
		OutputStream os = response.getOutputStream();
		InputStream is = new FileInputStream(filePath);
		/*
		 * byte[] data = new byte[1024];
		 * 
		 * while (true) { int readByteNum = is.read(data); if (readByteNum == -1) break;
		 * os.write(data, 0, readByteNum); }
		 */
		FileCopyUtils.copy(is, os);
		
		os.flush();
		os.close();
		is.close();
	}
	
	@GetMapping("/boardread")
	public String boardread(int bno, Model model) {
		
		boardService.addHitcount(bno);
		Ch14Board board = boardService.getBoard(bno);
		
		model.addAttribute("board", board);
		
		return "ch14/boardread";
	}
	
	@GetMapping("/boardupdate")
	public String boardUpdateForm(int bno, Model model) {
		
		Ch14Board board = boardService.getBoard(bno);
		
		model.addAttribute("board", board);
		return "ch14/boardupdate";
	}
	
	@PostMapping("/boardupdate")
	public String boardUpdate(Ch14Board board) {
		
		boardService.updateBoard(board);
		
		return "redirect:/ch14/boardread?bno=" + board.getBno();
	}
	
	@GetMapping("/boarddelete")
	public String boardDelete(int bno) {
		
		boardService.deleteBoard(bno);
		return "redirect:/ch14/boardlist2";
	}
	
	@GetMapping("/battach")
	public void battach(int bno, HttpServletResponse response) throws Exception {
		
		Ch14Board board = boardService.getBoard(bno);
		
		String battachsname = board.getBattachsname();
		String filePath = "D:/MyWorkspace/uploadfiles/boards/" + battachsname;
		
		response.setContentType(board.getBattachtype());
		
		String battachoname = board.getBattachoname();
		battachoname = new String(battachoname.getBytes("UTF-8"), "ISO-8859-1");	
		response.setHeader("Content-Disposition", "attachment; filename=\"" + battachoname + "\"");
		
		OutputStream os = response.getOutputStream();
		InputStream is = new FileInputStream(filePath);
		FileCopyUtils.copy(is, os);
		
		os.flush();
		os.close();
		is.close();
	}
	
	@Resource
	private Ch14OrderService orderService;
	
	@GetMapping("/order")
	public String order() {
		
		// 주문 정보 얻기
		Ch14Order order = new Ch14Order();
		order.setMid("winter");
		order.setAddress("서울시 송파구");
		
		// 주문 상품 정보 얻기(장바구니에서 가져와야 됨)
		List<Ch14OrderItem> orderItems = new ArrayList<>();
		Ch14OrderItem oi1 = new Ch14OrderItem();
		oi1.setPid("다이아몬드");
		oi1.setAmount(100);
		orderItems.add(oi1);
		Ch14OrderItem oi2 = new Ch14OrderItem();
		oi2.setPid("루비");
		oi2.setAmount(50);
		orderItems.add(oi2);
		
		// 주문 처리
		orderService.order(order, orderItems);
		
		return "ch14/content";
	}
}





















