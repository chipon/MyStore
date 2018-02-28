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
//private int userid;
//private String nickname;
//private String password;
//private String email;
//private boolean role;
//private String address;
//private String telephone;
@WebServlet(urlPatterns = { "/RegisterServlet" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao dao=new UserDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=new User();
		user.setNickname(request.getParameter("nickname"));
		user.setPassword(request.getParameter("password"));
		user.setEmail(request.getParameter("email"));
		user.setAddress(request.getParameter("address"));
		user.setTelephone(request.getParameter("telephone"));
		if(!dao.AddUser(user))
			throw new RuntimeException("用户添加失败!");
		else{
			//System.out.println(user.getAddress());
			JSONObject ob = new JSONObject();
			ob.put("nickname",user.getNickname());
			response.getWriter().write(ob.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
