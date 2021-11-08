package com.yedam.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetScheduleServlet2
 */
@WebServlet("/GetScheduleServlet2")
public class GetScheduleServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public GetScheduleServlet2() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");
		
		String title = request.getParameter("title");
		//System.out.println(title);
		
		MemDAO dao = new MemDAO();
		
		boolean removeOK = dao.remSchedule(title);
		if(removeOK) {
			// {"retCode":"OK"}
			response.getWriter().println("{\"retCode\":\"OK\"}");	
		}else {
			// {"retCode":"NG"}
			response.getWriter().println("{\"retCode\":\"NG\"}");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
