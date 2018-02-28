package com.hitwh.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hitwh.dao.ProdTypeDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
//typeid(int)#商品种类ID
//typename(string)#商品种类名
@WebServlet(urlPatterns = { "/ProdTypeServlet" })
public class ProdTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdTypeDao dao=new ProdTypeDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Map<String,Object>> list=dao.findAllType();
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		for(Map<String,Object> mp:list){
			JSONObject ob = new JSONObject();
			ob.put("typeid", mp.get("typeid"));
			ob.put("typename",mp.get("typename"));
			ja.add(ob);
		}
		jo.put("Count", ja.size());
		jo.put("ResultList",ja);
		response.getWriter().write(jo.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
