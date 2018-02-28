package com.hitwh.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodeFilter implements Filter {
	private String encode;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encode=filterConfig.getServletContext().getInitParameter("encode");
		if(encode=="") encode="utf-8";
		Filter.super.init(filterConfig);
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding(encode);
		response.setContentType("text/html;charset="+encode);
		//System.out.println("ok");
		chain.doFilter(new MyHttpServletRequest((HttpServletRequest) request), response);
	}
	class MyHttpServletRequest extends HttpServletRequestWrapper{
		//boolean isNotEncoded=true;
		private HttpServletRequest request;
		public MyHttpServletRequest(HttpServletRequest request) {
			super(request);
			this.request=request;
			// TODO Auto-generated constructor stub
		}
		@Override
		public String getParameter(String name) {
			String[] s= getParameterValues(name);
			if(s==null) return null;
			return s[0];
		}
		@Override
		public String[] getParameterValues(String name) {
			return getParameterMap().get(name);
		}
		@Override
		public Map<String, String[]> getParameterMap() {
			try {
				if (request.getMethod().equalsIgnoreCase("POST")) {
					request.setCharacterEncoding(encode);
					return super.getParameterMap();
				}else {
					return super.getParameterMap();
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

}
//else if (request.getMethod().equalsIgnoreCase("GET")) {
//	//System.out.println("correctly enter filter! "+encode);
//	Map<String, String[]> map = request.getParameterMap();
//	if(isNotEncoded){
//		for (Map.Entry<String, String[]> entry : map.entrySet()) {
//			String[] value = entry.getValue();
//			for (int i = 0; i < value.length; ++i) {
//				System.out.println(value[i]);
//				System.out.println(new String(value[i].getBytes("ISO8859-1"), "UTF-8"));
//				value[i] = URLDecoder.decode((new String(value[i].getBytes("ISO8859-1"), "UTF-8")), "UTF-8");
//				System.out.println(value[i]);
//			}
//		}
//		isNotEncoded=false;
//	}
//	return map;
//} 