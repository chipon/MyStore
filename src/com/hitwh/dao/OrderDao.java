package com.hitwh.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.hitwh.domain.Order;
import com.hitwh.domain.OrderItem;
import com.hitwh.util.DaoUtils;
//private long orderid;
//private int userid;
//private double money;
//private Timestamp time;
//private boolean paystate;
//private String message;
//private String address;
public class OrderDao {
	public boolean addOrder(Order order){
		try {
			
			String sql="insert into Orders(orderid,userid,money,paystate,message,address) values(?,?,?,?,?,?)";
			QueryRunner runner=new QueryRunner(DaoUtils.getSource());
			if(runner.update(sql,order.getOrderid(),order.getUserid(),order.getMoney(),order.isPaystate(),
				order.getMessage(),order.getAddress())>0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
//	prodid#商品ID
//	time(date)#购买时间
//	name(string)#商品名
//	buynum(int)#购买数量
//	price(double)#商品价格
	//获得商品ID，购买数量，购买时间
	public List<Map<String, Object>> findAllOrderProd(int userid){
		try {
			String sql="select productid,time,number from Orders od inner join OrderItem oi"
					+ " on od.userid=? and od.orderid=oi.orderid";
			QueryRunner runner=new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new MapListHandler(),userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<Order> findAllOrder(int userid){
		try {
			String sql="select * from Orders where userid=?";
			QueryRunner runner=new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Order>(Order.class),userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public Order findOrder(int orderid){
		try {
			String sql="select * from Orders where orderid=?";
			QueryRunner runner=new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<Order>(Order.class),orderid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
