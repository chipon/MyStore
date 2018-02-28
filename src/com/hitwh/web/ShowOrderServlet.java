package com.hitwh.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hitwh.dao.OrderDao;
import com.hitwh.dao.ProdDao;
import com.hitwh.domain.Order;
import com.hitwh.domain.Product;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
//prodid#商品ID
//time(date)#购买时间
//name(string)#商品名
//buynum(int)#购买数量
//price(double)#商品价格
@WebServlet(urlPatterns = { "/ShowOrderServlet" })
public class ShowOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDao dao=new OrderDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer userid=(Integer) request.getSession().getAttribute("userid");
		if(userid==0) throw new RuntimeException("请先登陆!");
		List<Order> list=dao.findAllOrder(userid);
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		for(Order order:list){
			Timestamp stamp=order.getTime();
			JSONObject ob = new JSONObject();
			ob.put("time", sdf.format(stamp.getTime()));
			ob.put("orderid", order.getOrderid());
			ob.put("address", order.getAddress());
			ob.put("money", order.getMoney());
			ob.put("paystate", order.isPaystate());
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
