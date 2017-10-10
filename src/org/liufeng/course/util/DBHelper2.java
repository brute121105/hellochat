package org.liufeng.course.util;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.SQLException;  
  
public class DBHelper2 {  
    public static final String url = "jdbc:mysql://127.0.0.1/db_weixin"; 
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "root";  
    public static final String password = "root";  
    
   /* public static final String url = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_beitong2"; 
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "woln1l2n3j";  
    public static final String password = "k5xjjkz5500h5hilmw3x1mywz2wy4104hy4hyywl";*/
  
    public Connection conn = null;  
    public PreparedStatement pst = null;  
  
    
    public String connectDB(){
    	String str = "";
		try {
			conn = DriverManager.getConnection(url, user, password);
			str = conn.toString();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("-----------d--------");
			return e.getLocalizedMessage();
		} // 获取连接
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 设置自动提交为false
		return str;
	}
}  