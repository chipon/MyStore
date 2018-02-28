package com.hitwh.listener;

import java.util.LinkedHashMap;


import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.hitwh.domain.Product;

public class MySessionListener implements HttpSessionListener {
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		se.getSession().setAttribute("cartMap", new LinkedHashMap<Product,Integer>());
		se.getSession().setAttribute("userid", new Integer(1));
		//HttpSessionListener.super.sessionCreated(se);
	}
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSessionListener.super.sessionDestroyed(se);
	}
}
