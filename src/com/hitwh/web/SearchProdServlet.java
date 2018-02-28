package com.hitwh.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hitwh.dao.ProdDao;
import com.hitwh.domain.Product;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet(urlPatterns = { "/SearchProdServlet" })
public class SearchProdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdDao dao=new ProdDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search=request.getParameter("search");
		if(search==null) throw new RuntimeException("请求格式错误");
		if(search.length()==0) throw new RuntimeException("未指定查找条件!");
		List<Product> list=dao.findAllProd();
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		boolean flag;
		for(Product p:list){
			flag=false;
			String name=p.getName();
			String intro=p.getIntruduction();
			String desc=p.getDescription();
			if(name.indexOf(search)>=0) flag=true;
			if(intro.indexOf(search)>=0) flag=true;
			if(desc.indexOf(search)>=0) flag=true;
			if(flag){
				JSONObject ob = new JSONObject();
				ob.put("prodid", p.getProductid());
				ob.put("name",p.getName());
				ob.put("imgurl", p.getImgurl());
				ob.put("price",p.getPrice());
				ja.add(ob);
			}
		}
		jo.put("Count", ja.size());
		jo.put("ResultList", ja);
		response.getWriter().write(jo.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
