package com.yedam.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetMemberServlet
 */
@WebServlet({ "/GetMemberServlet", "/get_member" })  //https://localhost/HelloWorld/뒤 주소가 될수있음
public class GetMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMemberServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath()).append("hello"); // getContextPath()는 내가 열고있는 프로젝트 이름, 화면출력
//		response.setContentType("text/html;charset=UTF-8");
		response.setContentType("text/xml;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		MemDAO dao = new MemDAO();
		List<MemberVO> list = dao.getMemberList();
//		out.println("<ul>");
//		for(MemberVO member: list) {
//			out.println("<li>" + member.getUserId() + ", " + member.getUserName() + ", " + member.getBirthDate() + ", " + member.getGender() + "</li>");
//		}
//		out.println("</ul>");
		
		out.println("<record>");
		out.println("<h3>Hello, 이소정입니다.</h3>");
		for(MemberVO member: list) {
			out.println("<row>" 
					+ "<id>" + member.getUserId() + "</id>"
					+ "<name>" + member.getUserName() + "</name>"
					+ "<birth>" + member.getBirthDate() + "</birth>" 
					+ "<gender>" + member.getGender() + "</gender>" 
					+ "</row>");
		}
		out.println("</record>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //form post방식 제외 모두 get방식
		doGet(request, response);
	}

}
