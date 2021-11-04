package com.yedam.member;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@WebServlet("/ModifyMemberServlet")
public class ModifyMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ModifyMemberServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");  //응답정보
		response.setContentType("text/json;charset=UTF-8");
		
		String deleteId = request.getParameter("id");
		
		MemDAO dao = new MemDAO();
		
		Gson gson = new GsonBuilder().create();
		JsonObject jsonObj = new JsonObject();
		
		if(dao.deleteMember(deleteId)) {			
			jsonObj.addProperty("retCode", "OK");
			jsonObj.addProperty("userId", deleteId);
			
		}else {
			jsonObj.addProperty("retCode", "NG");
			jsonObj.addProperty("retMsg", "오류발생!!! \n 담당자에게 문의");
		}
		response.getWriter().println(gson.toJson(jsonObj));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");   //요청정보
		response.setCharacterEncoding("UTF-8");  //응답정보
		response.setContentType("text/json;charset=UTF-8");
		
		// 데이터 수정 및 화면 변경
		String userId = request.getParameter("id");
		String userName = request.getParameter("nm");
		String userAddr = request.getParameter("ad");
		String userPhone = request.getParameter("ph");
		String userBirth = request.getParameter("br");
		String userGen = request.getParameter("gn");
		
		MemberVO vo = new MemberVO();
		vo.setAddress(userAddr);
		vo.setBirthDate(userBirth);
		vo.setGender(userGen);
		vo.setPhone(userPhone);
		vo.setUserId(userId);
		vo.setUserName(userName);
		
		MemDAO dao = new MemDAO();
		Map<String, Object> resultMap = dao.updateMember(vo);
		
		Gson gson = new GsonBuilder().create();
		response.getWriter().println(gson.toJson(resultMap));
		
	}

}
