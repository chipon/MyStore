package com.hitwh.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hitwh.dao.UserDao;
import com.hitwh.domain.User;

import net.sf.json.JSONObject;
//userid(int)#用户ID
//nickname(string)#用户昵称
//email(string)#邮箱
//address(string)#地址
//role(bool)#角色(管理员或用户)
@WebServlet(urlPatterns = { "/ShowUserServlet" })
public class ShowUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao dao=new UserDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer userid=(Integer) request.getSession().getAttribute("userid");
		if(userid==0) throw new RuntimeException("请先登陆!");
		User user=dao.findUserByID(userid);
		if(user==null)	throw new RuntimeException("无此用户!");
		
		JSONObject ob = new JSONObject();
		ob.put("nickname", user.getNickname());
		ob.put("email", user.getEmail());
		ob.put("telephone", user.getTelephone());
		ob.put("address", user.getAddress());
		ob.put("role", user.isRole());
		response.getWriter().write(ob.toString());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
