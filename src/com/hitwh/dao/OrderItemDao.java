package com.hitwh.dao;

import org.apache.commons.dbutils.QueryRunner;

import com.hitwh.domain.OrderItem;
import com.hitwh.util.DaoUtils;
//	private int orderid;
//	private int productid;
//	private int number;
public class OrderItemDao {
	public boolean addOrderItem(OrderItem item){
		try {
			String sql="insert into OrderItem(orderid,productid,number) values(?,?,?)";
			QueryRunner runner=new QueryRunner(DaoUtils.getSource());
			if(runner.update(sql,item.getOrderid(),item.getProductid(),item.getNumber())>0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
