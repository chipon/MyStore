package com.hitwh.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;

import org.junit.Test;

public class UUIDUtils {
	public static String getMD5(String str) {
	    try {
	        // 生成一个MD5加密计算摘要
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        // 计算md5函数
	        md.update(str.getBytes());
	        // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
	        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
	        return new BigInteger(1, md.digest()).toString(16);
	    } catch (Exception e) {
	    	return e.getMessage();
	    }
	}
	@Test
	public void test() throws ClassNotFoundException {
//		Timestamp t = new Timestamp(System.currentTimeMillis());
//        Date d = new Date(t.getTime());
        
        //System.out.println((1970+d.getYear())+"/"+d.getMonth()+"/"+d.getDay());
//		Map<String,Integer> map=new LinkedHashMap<String,Integer>();
//		map.put("hello", 12);
//		map.put("hello", 16);
//		System.out.println(map.size());
//		System.out.println(map.get("hello"));
//		String s="123.jpg"+"\\"+"456.jpg";
//		StringTokenizer st = new StringTokenizer(s, "\\");
//		 while(st.hasMoreElements()){  
//			 System.out.println("Token:" + st.nextToken());
//		 }  
		//System.out.println(s);
//		Connection con=null;
//		PreparedStatement ps=null;
//		ResultSet rs=null;
//		try {
//			con=JDBCUtils.getConnection();
//			ps=con.prepareStatement("select * from ProductType");
//			rs=ps.executeQuery();
//			int id;
//			String name;
//			while(rs.next()){
//				id=rs.getInt("typeid");
//				name=rs.getString("typename");
//				System.out.println(id+":"+name);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			JDBCUtils.close(rs, ps, con);
//		}
//		String str=getUUID();
//		String hashstr=Integer.toHexString(str.hashCode());
//		System.out.println(str);
		//System.out.println(hashstr);
		String str="test";
		System.out.println(getMD5(str));
	}
	public static String getUUID() {  
        UUID uuid = UUID.randomUUID();  
        String str = uuid.toString();  
        // 去掉"-"符号  
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);  
        return str+"\n"+temp+'\n'+temp.length();
    } 

//	@Test
//	public void test(){
//		String s="a12";
//		try {
//			int a=Integer.valueOf(s);
//			System.out.println(a);
//		} catch (Exception e) {
//			System.out.println("输入格式错误!");
//		}
//	}
	
//	int[] a={1,2,3,4};
//	System.out.println(a.length);
}
