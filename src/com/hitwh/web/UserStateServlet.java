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

@WebServlet(urlPatterns = { "/UserStateServlet" })
public class UserStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao dao=new UserDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer userid=(Integer) request.getSession().getAttribute("userid");
		if(userid==null){
			userid=new Integer(0);
			request.getSession().setAttribute("userid", userid);
		}
		JSONObject ob = new JSONObject();
		ob.put("islogin",userid!=0);
		if(userid!=0){
			User user=dao.findUserByID(userid);
			ob.put("nickname", user.getNickname());
		}
		response.getWriter().write(ob.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
