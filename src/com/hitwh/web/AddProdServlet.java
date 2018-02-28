package com.hitwh.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.hitwh.dao.ProdDao;
import com.hitwh.domain.Product;
import com.hitwh.domain.User;
import com.hitwh.service.ProductService;
import com.hitwh.util.IOUtils;
import com.hitwh.util.PicUtils;
import com.hitwh.util.UUIDUtils;
//name(string)#商品名
//price(double)#商品价格
//imgurl(file)#图片数据
//stoke(int)#商品存量
//intruduction(string)#商品简介
//description(string)#商品详情
@WebServlet(urlPatterns="/AddProdServlet")
public class AddProdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdDao dao=new ProdDao();
	final static int StandardHeight=140;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String encode = this.getServletContext().getInitParameter("encode");
		Map<String, String> paramMap = new HashMap<String,String>();
		//1.上传图片
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024*100);
		factory.setRepository(new File(this.getServletContext().getRealPath("temp")));
		
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		fileUpload.setHeaderEncoding(encode);
		fileUpload.setFileSizeMax(1024*1024*1);
		fileUpload.setSizeMax(1024*1024*10);
		
//		if(!fileUpload.isMultipartContent(request)){
//			throw new RuntimeException("请使用正确的表单进行上传!");
//		}
		try {
			List<FileItem> list = fileUpload.parseRequest(request);
			for(FileItem item : list){
				if(item.isFormField()){
					//普通字段
					String name = item.getFieldName();
					String value = item.getString(encode);
					paramMap.put(name, value);
				}else{
					//文件上传项
					String realname = item.getName();
					String uuidname = UUIDUtils.getUUID()+realname;

					String upload = this.getServletContext().getRealPath("upload");
					String dest=this.getServletContext().getRealPath("images");
					String imgurl = "images/"+uuidname;
					paramMap.put("imgurl", imgurl);
					
					InputStream in = item.getInputStream();
					OutputStream out = new FileOutputStream(new File(upload,uuidname));
					IOUtils.In2Out(in, out);
					IOUtils.close(in, out);
					item.delete();
					//--生成缩略图
					PicUtils.resizeByHeight(upload+'/'+uuidname,dest+'/'+uuidname,StandardHeight);
				}
			}
			Product prod = new Product();
			BeanUtils.populate(prod, paramMap);
			dao.addProd(prod);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

//String path=this.getClass().getClassLoader().getResource("../../upload").getPath();
//String path=this.getServletContext().getRealPath("images");
//System.out.println(path);
//String src=path+"pictest.jpg";
//String name=request.getParameter("name");
//String price=request.getParameter("price");
//String imgurl=request.getParameter("imgurl");
//String stoke=request.getParameter("stoke");
//String intruduction=request.getParameter("intruduction");
//String description=request.getParameter("description");
//if(name==null || price==null || imgurl==null || stoke==null || intruduction==null)
//	throw new RuntimeException("请求格式错误！");
//else{
//	Product p=new Product();
//	p.setName(name);
//	p.setPrice(Integer.valueOf(price));
//	p.setImgurl(imgurl);
//	p.setStoke(Integer.valueOf(stoke));
//	p.setIntruduction(intruduction);
//	p.setDescription(description);
//	dao.addProd(p);
//}
//prod.setName(request.getParameter("name"));
//prod.setPrice(request.getParameter("price"));
//user.setNickname(request.getParameter("nickname"));
//user.setPassword(request.getParameter("password"));
//user.setEmail(request.getParameter("email"));
//user.setAddress(request.getParameter("address"));
//user.setTelephone(request.getParameter("telephone"));
//Map<String,String> map=new HashMap<String,String>();
//map.put("prodname", "华为手机");
//map.put("price", "12");
//map.put("imgurl", "images/test.jpg");
//map.put("typeid", "5");
//map.put("stoke", "20");
//map.put("description", "test");
//Product prod=new Product();
//try {
//	//BeanUtils.populate(prod, map);
//	if(dao.addProd(prod))
//		response.getWriter().write("add ok!");
//	else
//		response.getWriter().write("add failed!");
//} catch (Exception e) {
//	e.printStackTrace();
//}