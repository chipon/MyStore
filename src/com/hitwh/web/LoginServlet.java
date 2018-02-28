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

@WebServlet(urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao dao=new UserDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickname=request.getParameter("nickname");
		String password=request.getParameter("password");
		User user=dao.findUser(nickname, password);
		if(user==null) throw new RuntimeException("找不到此用户!");
		else{
			request.getSession().setAttribute("userid", user.getUserid());
			JSONObject ob = new JSONObject();
			ob.put("nickname",user.getNickname());
			ob.put("role",user.isRole());
			response.getWriter().write(ob.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
