package com.hitwh.web;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hitwh.dao.ProdDao;
import com.hitwh.domain.Product;

import net.sf.json.JSONObject;

@WebServlet(urlPatterns = { "/AddCartServlet" })
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdDao dao=new ProdDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prodid=request.getParameter("prodid");
		String buynum=request.getParameter("buynum");
		if(prodid==null || buynum==null) throw new RuntimeException("请求格式错误");
		Product p=dao.findProdByID(Integer.valueOf(prodid));
		if(p==null)	throw new RuntimeException("商品不存在");
		int id=Integer.valueOf(prodid);
		int num=Integer.valueOf(buynum);
		Map<Integer,Integer> cartMap = (Map<Integer, Integer>) request.getSession().getAttribute("cartMap");
		cartMap.put(id, cartMap.containsKey(id)?cartMap.get(id)+num : num);
		request.getSession().setAttribute("cartMap", cartMap);
		request.getRequestDispatcher("/CartNumServlet").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
//	System.out.println(cartMap.containsKey(id) +" add id="+prodid+" value="+cartMap.get(id)+" size="+cartMap.size());
//	if(cartMap==null) throw new RuntimeException("获取购物车失败!");
//	for (Map.Entry<Integer,Integer> entry : cartMap.entrySet()) {
//		if(entry.getKey()==id){
//			entry.setValue(entry.getValue()+num);
//			flag=false;
//		}
//	}
//	if(flag) cartMap.put(id,num);
}
