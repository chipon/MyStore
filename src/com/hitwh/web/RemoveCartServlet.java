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

@WebServlet(urlPatterns = { "/RemoveCartServlet" })
public class RemoveCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdDao dao=new ProdDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prodid=request.getParameter("prodid");
		if(prodid==null) throw new RuntimeException("请求格式错误");
		int id=Integer.valueOf(prodid);
		Product p=dao.findProdByID(id);
		if(p==null)	throw new RuntimeException("商品不存在");
		else{
			Map<Integer,Integer> cartMap = (Map<Integer, Integer>) request.getSession().getAttribute("cartMap");
			if(cartMap.containsKey(id))
				cartMap.remove(id);
			else throw new RuntimeException("商品不存在");
			request.getRequestDispatcher("/CartNumServlet").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
