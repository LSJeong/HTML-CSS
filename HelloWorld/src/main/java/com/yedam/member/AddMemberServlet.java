package com.yedam.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/AddMemberServlet")
public class AddMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddMemberServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");

		AddUserDAO dao = new AddUserDAO();
		List<UserVO> list = dao.getUserList();

		PrintWriter out = response.getWriter();

		Gson gson = new GsonBuilder().create();
		out.println(gson.toJson(list));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		AddUserDAO dao = new AddUserDAO();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String birth = request.getParameter("birth");

		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setName(name);
		vo.setEmail(email);
		vo.setPhone(phone);
		vo.setBirth(birth);

		dao.insertUserTable(vo);
		
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().create();
		out.println(gson.toJson(vo));
	}

}
