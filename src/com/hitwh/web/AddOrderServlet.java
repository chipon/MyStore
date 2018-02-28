package com.hitwh.web;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hitwh.dao.OrderDao;
import com.hitwh.dao.OrderItemDao;
import com.hitwh.dao.ProdDao;
import com.hitwh.domain.Order;
import com.hitwh.domain.OrderItem;
import com.hitwh.domain.Product;

@WebServlet(urlPatterns = { "/AddOrderServlet" })
public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdDao proddao=new ProdDao();
	private OrderDao orderdao=new OrderDao();
	private OrderItemDao itemdao=new OrderItemDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userid=(int) request.getSession(false).getAttribute("userid");
		if(userid==0) throw new RuntimeException("请先登陆!");
		String money=request.getParameter("money");
		String address=request.getParameter("address");
		String message=request.getParameter("message");
		if(money==null || address==null) throw new RuntimeException("请求格式错误");
		
		//更新数据库商品存量
		Map<Integer,Integer> cartMap = (Map<Integer, Integer>) request.getSession().getAttribute("cartMap");
		for (Map.Entry<Integer,Integer> entry : cartMap.entrySet()) {
			if(!proddao.reduceProd(entry.getKey(), entry.getValue()))
				throw new RuntimeException("生成订单失败!请检查购买量是否超出存量");
		}

		//添加订单
		Order order=new Order();
		order.setPaystate(true);
		order.setOrderid(UUID.randomUUID().toString());
		order.setMoney(Double.valueOf(money));
		order.setAddress(address);
		order.setMessage(message);
		order.setUserid(userid);
		order.setPaystate(true);
		orderdao.addOrder(order);
		
		//添加数据项
		for (Map.Entry<Integer,Integer> entry : cartMap.entrySet()) {
			OrderItem item=new OrderItem();
			item.setOrderid(order.getOrderid());
			item.setProductid(entry.getKey());
			item.setNumber(entry.getValue());
			itemdao.addOrderItem(item);
		}
		
		//清空购物车
		cartMap.clear();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
