package com.hitwh.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hitwh.dao.ProdDao;
import com.hitwh.domain.Product;

@WebServlet(urlPatterns = { "/DecCartServlet" })
public class DecCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdDao dao=new ProdDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prodid=request.getParameter("prodid");
		if(prodid==null) throw new RuntimeException("请求格式错误!");
		Map<Integer,Integer> cartMap = (Map<Integer, Integer>) request.getSession().getAttribute("cartMap");
		if(cartMap==null) throw new RuntimeException("获取购物车失败!");
		int id=Integer.valueOf(prodid);
		Product p=dao.findProdByID(id);
		if(p==null) throw new RuntimeException("找不到该商品!");
		if(!cartMap.containsKey(id)) throw new RuntimeException("购物车中没有该商品!");
		if(cartMap.get(id)==1)
			throw new RuntimeException("商品只剩下一件，如需移除请点移除按钮!");
		else
			cartMap.put(id, cartMap.get(id)-1);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
