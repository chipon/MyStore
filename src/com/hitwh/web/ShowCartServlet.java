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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//prodid#商品ID
//name(string)#商品名
//stoke(int)#商品存量
//buynum(int)#购买数量
//price(double)#商品价格
@WebServlet(urlPatterns = { "/ShowCartServlet" })
public class ShowCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdDao dao=new ProdDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<Integer,Integer> cartMap = (Map<Integer, Integer>) request.getSession().getAttribute("cartMap");
		if(cartMap==null) throw new RuntimeException("显示购物车信息错误!");
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		for (Map.Entry<Integer,Integer> entry : cartMap.entrySet()) {
			int prodid=entry.getKey();
			int buynum = entry.getValue();
			Product p=dao.findProdByID(prodid);
			JSONObject ob = new JSONObject();
			ob.put("prodid", p.getProductid());
			ob.put("name",p.getName());
			ob.put("stoke", p.getStoke());
			ob.put("buynum",buynum);
			ob.put("price", p.getPrice());
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
