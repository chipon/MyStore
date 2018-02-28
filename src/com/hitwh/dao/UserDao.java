package com.hitwh.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hitwh.domain.Product;
import com.hitwh.domain.User;
import com.hitwh.util.DaoUtils;
//private int userid;
//private String nickname;
//private String password;
//private String email;
//private boolean role;
//private String address;
//private String telephone;
public class UserDao {
	public boolean AddUser(User u){
		try {
			String sql="insert into User(nickname,password,email,address,telephone) values(?,?,?,?,?)";
			QueryRunner runner=new QueryRunner(DaoUtils.getSource());
			if(runner.update(sql,u.getNickname(),u.getPassword(),u.getEmail(),u.getAddress(),u.getTelephone())>0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public User findUserByID(int userid){
		try {
			String sql="select * from User where userid=?";
			QueryRunner runner=new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<User>(User.class),userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public User findUser(String nickname,String password){
		try {
			String sql="select * from User where nickname=? and password=?";
			QueryRunner runner=new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<User>(User.class),nickname,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
//	public boolean updateUser(int id,User u){
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
}
//private String nickname;
//private String password;
//private String email;
//private boolean role;
//private String address;