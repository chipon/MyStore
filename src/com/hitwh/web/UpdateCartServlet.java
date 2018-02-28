package com.hitwh.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hitwh.dao.ProdDao;

@WebServlet(urlPatterns = { "/UpdateCartServlet" })
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdDao dao=new ProdDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prodid=request.getParameter("prodid");
		String buynum=request.getParameter("buynum");
		if(prodid==null || buynum==null) throw new RuntimeException("请求参数格式错误!");
//		Product p=dao.findProdByID(Integer.valueOf(prodid));
//		if(p==null)	throw new RuntimeException("商品不存在");
		int id=Integer.valueOf(prodid);
		int num=Integer.valueOf(buynum);
		Map<Integer,Integer> cartMap = (Map<Integer, Integer>) request.getSession().getAttribute("cartMap");
		if(!cartMap.containsKey(id)) throw new RuntimeException("购物车中没有此商品!");
		cartMap.put(id,num);
		request.getSession().setAttribute("cartMap", cartMap);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
