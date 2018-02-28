package com.hitwh.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hitwh.dao.ProdDao;
import com.hitwh.domain.Product;

@WebServlet(urlPatterns = { "/FindProdServlet" })
public class FindProdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdDao dao=new ProdDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=3;
		Product p=dao.findProdByID(id);
		System.out.println("查询成功! "+p.getName()+' '+p.getPrice()+' '+p.getDescription());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
