package org.liufeng.course.util;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.SQLException;  
  
public class DBHelper {  
    /*public static final String url = "jdbc:mysql://127.0.0.1/db_weixin"; 
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "root";  
    public static final String password = "root";  */
    
    public static final String url = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_beitong2"; 
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "woln1l2n3j";  
    public static final String password = "k5xjjkz5500h5hilmw3x1mywz2wy4104hy4hyywl";
  
    public Connection conn = null;  
    public PreparedStatement pst = null;  
  
    public DBHelper(String sql) {  
        try {  
            Class.forName(name);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接  
            pst = conn.prepareStatement(sql);//准备执行语句  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void close() {  
        try {  
            this.conn.close();  
            this.pst.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
    public Connection connectDB() throws SQLException
	{
		conn = DriverManager.getConnection(url, user, password); // 获取连接
		conn.setAutoCommit(false); // 设置自动提交为false
		return conn;
	}
}  