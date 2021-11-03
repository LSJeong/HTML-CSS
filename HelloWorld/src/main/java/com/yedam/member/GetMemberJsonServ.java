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

/**
 * Servlet implementation class GetMemberJsonServ
 */
@WebServlet("/GetMemberJsonServ")
public class GetMemberJsonServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMemberJsonServ() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		// {"name":"Hongkildong", "age":20, "phone":"010-1234-5678"}
//		out.println("{\"name\":\"Hongkildong\", \"age\":20, \"phone\":\"010-1234-5678\"}");
		// "[{\"id\":\"?\", \"name\":? },{},{}]"
		MemDAO dao = new MemDAO();
		List<MemberVO> list = dao.getMemberList();
//		int cnt = list.size();
//		out.println("[");
//		for(int i=0;i<cnt;i++) {
//			out.println("{\"id\": \"" + list.get(i).getUserId() 
//					+"\", \"name\": \"" + list.get(i).getUserName() 
//					+"\", \"birth\": \"" + list.get(i).getBirthDate().substring(0,10) 
//					+"\", \"gender\": \"" + list.get(i).getGender() 
//					+"\"}");
//			if(i != cnt-1) { //마지막 데이터에는 , 안찍어주기위해서
//				out.println(",");
//			}
//		}
//		out.println("]");
		
		//gson-2.8.2.jar 추가하면 간단히 쓸수있다
		Gson gson = new GsonBuilder().create();
		out.println(gson.toJson(list));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		MemDAO dao = new MemDAO();
		String userId=request.getParameter("id");
		String userName=request.getParameter("name");
		String address = request.getParameter("addr");
		MemberVO vo = new MemberVO();
		vo.setUserId(userId);
		vo.setUserName(userName);
		vo.setAddress(address);
		
		dao.insertMember(vo);
		
		response.getWriter().println("OK");
	}

}
