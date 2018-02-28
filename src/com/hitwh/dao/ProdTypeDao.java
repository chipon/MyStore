package com.hitwh.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.hitwh.domain.Product;
import com.hitwh.util.DaoUtils;

public class ProdTypeDao {

	public List<Map<String,Object>> findAllType(){
		try {
			String sql="select * from ProductType";
			QueryRunner runner=new QueryRunner(DaoUtils.getSource());
			return runner.query(sql,new MapListHandler());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String findTypeByID(int typeid){
		try {
			String sql="select typename from ProductType where typeid=?";
			QueryRunner runner=new QueryRunner(DaoUtils.getSource());
			return (String) runner.query(sql, new ScalarHandler(),typeid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
