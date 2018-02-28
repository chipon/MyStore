package com.hitwh.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.hitwh.domain.Product;
import com.hitwh.util.DaoUtils;
//private String prodname;
//private double price;
//private String imgurl;
//private byte typeid;
//private int stoke;
//private String description;
public class ProdDao {
	public boolean addProd(Product p){
		try {
			String sql="insert into Product(name,price,typeid,stoke,imgurl,introduction,param,description) values(?,?,?,?,?,?,?,?)";
			QueryRunner runner=new QueryRunner(DaoUtils.getSource());
			if(runner.update(sql,p.getName(),p.getPrice(),p.getTypeid(),p.getStoke(),p.getImgurl(),
				p.getIntruduction(),p.getParam(),p.getDescription())>0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public Product findProdByID(int id){
		try {
			String sql="select * from Product where productid=?";
			QueryRunner runner=new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<Product>(Product.class),id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean reduceProd(int prodid,int num){
		try {
			String sql="update Product set stoke=stoke-? where productid=?";
			QueryRunner runner=new QueryRunner(DaoUtils.getSource());
			if(runner.update(sql,num,prodid)>0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean delProd(int id){
		try {
			String sql="delete from Product where productid=?";
			QueryRunner runner=new QueryRunner(DaoUtils.getSource());
			if(runner.update(sql,id)>0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public List<Product> findProdByType(int typeid){
		try {
			String sql="select * from Product where typeid=?";
			QueryRunner runner=new QueryRunner(DaoUtils.getSource());
			return runner.query(sql,new BeanListHandler<Product>(Product.class),typeid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<Product> findAllProd(){
	try {
		String sql="select * from Product";
		QueryRunner runner=new QueryRunner(DaoUtils.getSource());
		return runner.query(sql,new BeanListHandler<Product>(Product.class));
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
//	public boolean updateProd(int id,Product u){
//		try {
//			String sql="insert into User(nickname,password,email,address) values(?,?,?,?)";
//			QueryRunner runner=new QueryRunner(DaoUtils.getSource());
//			if(runner.update(sql,u.getNickname(),u.getPassword(),u.getEmail(),u.getAddress())>0)
//				return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//	public boolean decStoke(int id){
//		try {
//			String sql="update Product set stoke=stoke-1 where productid=?";
//			QueryRunner runner=new QueryRunner(DaoUtils.getSource());
//			if(runner.update(sql,id)>0)
//				return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
}
