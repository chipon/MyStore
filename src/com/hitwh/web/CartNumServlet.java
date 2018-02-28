package com.hitwh.web;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hitwh.domain.Product;

import net.sf.json.JSONObject;

@WebServlet(urlPatterns = { "/CartNumServlet" })
public class CartNumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<Integer,Integer> cartMap = (Map<Integer, Integer>) request.getSession().getAttribute("cartMap");
		if(cartMap==null){
			cartMap=new LinkedHashMap<Integer,Integer>();
			request.getSession().setAttribute("cartMap", cartMap);
		}
		JSONObject ob = new JSONObject();
		ob.put("count",cartMap.size());
		response.getWriter().write(ob.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
