package com.hitwh.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hitwh.dao.ProdDao;
import com.hitwh.dao.ProdTypeDao;
import com.hitwh.domain.Product;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet(urlPatterns = { "/ProdListServlet" })
public class ProdListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdTypeDao typedao=new ProdTypeDao();
	private ProdDao proddao=new ProdDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		List<Map<String,Object>> list=typedao.findAllType();
		for(Map<String,Object> mp:list){
			int typeid=(int) mp.get("typeid");
			String typename=(String) mp.get("typename");
			List<Product> prods=proddao.findProdByType(Integer.valueOf(typeid));
			
			if(prods!=null && !prods.isEmpty()){
				JSONObject job = new JSONObject();
				JSONArray jar = new JSONArray();
				for(Product p:prods){
					JSONObject ob = new JSONObject();
					ob.put("prodid", p.getProductid());
					ob.put("name",p.getName());
					ob.put("imgurl", p.getImgurl());
					ob.put("price",p.getPrice());
					jar.add(ob);
				}
				job.put("typename", typename);
				job.put("Count",jar.size());
				job.put("ResultList", jar);
				ja.add(job);
			}
		}
		jo.put("TypeCount", ja.size());
		jo.put("TypeList",ja);
		response.getWriter().write(jo.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
