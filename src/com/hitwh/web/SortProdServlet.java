package com.hitwh.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hitwh.dao.ProdDao;
import com.hitwh.dao.ProdTypeDao;
import com.hitwh.domain.Product;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet(urlPatterns = { "/SortProdServlet" })
public class SortProdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProdDao proddao=new ProdDao();
	ProdTypeDao typedao=new ProdTypeDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeid=request.getParameter("typeid");
		if(typeid==null) throw new RuntimeException("请求格式错误");
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		String typename=typedao.findTypeByID(Integer.valueOf(typeid));
		List<Product> list=proddao.findProdByType(Integer.valueOf(typeid));
		if(list==null)	throw new RuntimeException("商品不存在");
		else{
			for(Product p:list){
				JSONObject ob = new JSONObject();
				ob.put("prodid", p.getProductid());
				ob.put("name",p.getName());
				ob.put("imgurl", p.getImgurl());
				ob.put("price",p.getPrice());
				ja.add(ob);
			}
			jo.put("Count", ja.size());
			jo.put("typename",typename);
			jo.put("ResultList",ja);
			response.getWriter().write(jo.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
