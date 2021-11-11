package com.yedam.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@WebServlet("/GetDataTableServerServlet")
public class GetDataTableServerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public GetDataTableServerServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json;charset=UTF-8");
		
		MemDAO dao = new MemDAO();
		List<DataTable> list = dao.getDataTables();
		JsonArray outer = new JsonArray();
		
		JsonObject obj = new JsonObject();
		obj.addProperty("draw",1);
		obj.addProperty("recordsTotal", 57);
		obj.addProperty("recordsFiltered", 57);
		
		for(DataTable dt : list) {
			JsonArray inner = new JsonArray();
			inner.add(dt.getName()); 
			inner.add(dt.getPosition());
			inner.add(dt.getOffice());
			inner.add(dt.getExtn());
			inner.add(dt.getStartDate());
			inner.add(dt.getSalary());
			
			outer.add(inner);
			obj.add("data",outer);
		}
		Gson gson = new GsonBuilder().create();
		
		response.getWriter().print(gson.toJson(obj));
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
