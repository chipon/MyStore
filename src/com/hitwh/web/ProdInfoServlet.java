package com.hitwh.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hitwh.dao.ProdDao;
import com.hitwh.domain.Product;

import net.sf.json.JSONObject;
//name(string)#商品名
//price(double)#商品价格
//imgurl(string)#图片路径
//stoke(int)#商品存量
//intruduction(string)#商品简介
//description(string)#商品详情
@WebServlet(urlPatterns = { "/ProdInfoServlet" })
public class ProdInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdDao dao=new ProdDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prodid=request.getParameter("prodid");
		if(prodid==null) throw new RuntimeException("请求格式错误");
		int id=Integer.valueOf(prodid);
		Product p=dao.findProdByID(id);
		if(p==null)	throw new RuntimeException("商品不存在");
		else{
			JSONObject ob = new JSONObject();
			ob.put("prodid", p.getProductid());
			ob.put("name",p.getName());
			ob.put("price",p.getPrice());
			ob.put("imgurl", p.getImgurl());
			ob.put("stoke", p.getStoke());
			ob.put("param", p.getParam());
			ob.put("intruduction",p.getIntruduction());
			ob.put("description",p.getDescription());
			response.getWriter().write(ob.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
