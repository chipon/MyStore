package com.hitwh.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hitwh.dao.OrderDao;
import com.hitwh.dao.ProdDao;
import com.hitwh.domain.Product;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet(urlPatterns = {"/ShowProdServlet"})
public class ShowProdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDao orderdao=new OrderDao();
	private ProdDao proddao=new ProdDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer userid=(Integer) request.getSession().getAttribute("userid");
		if(userid==0) throw new RuntimeException("请先登陆!");
		List<Map<String, Object>> list=orderdao.findAllOrderProd(userid);
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		for(Map<String,Object> mp:list){
			int prodid=(int) mp.get("productid");
			JSONObject ob = new JSONObject();
			ob.put("prodid", prodid);
			ob.put("time", mp.get("time"));
			ob.put("buynum", mp.get("number"));
			
			Product p=proddao.findProdByID(prodid);
			ob.put("name", p.getName());
			ob.put("price", p.getPrice());
			ja.add(ob);
		}
		jo.put("Count", ja.size());
		jo.put("ResultList", ja);
		response.getWriter().write(jo.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
