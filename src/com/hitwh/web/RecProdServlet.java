package com.hitwh.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hitwh.dao.ProdDao;
import com.hitwh.domain.Product;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//{
//	Count(int)#推荐商品个数
//	ResultList[
//		{
//			prodid(int)#商品ID
//			name(string)#商品名称
//			imgurl(string)#图片路径
//			price(double)#价格
//		},
//		...
//	]
//}
@WebServlet(urlPatterns = { "/RecProdServlet" })
public class RecProdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdDao dao=new ProdDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int[] rec={10,11,13,15};
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		for(int i=0;i<rec.length;++i){
			Product p=dao.findProdByID(rec[i]);
			if(p==null) continue;
			JSONObject ob = new JSONObject();
			ob.put("prodid", p.getProductid());
			ob.put("name",p.getName());
			ob.put("imgurl", p.getImgurl());
			ob.put("price",p.getPrice());
			ja.add(ob);
		}
		jo.put("Count", ja.size());
		jo.put("ResultList",ja);
		response.getWriter().write(jo.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
