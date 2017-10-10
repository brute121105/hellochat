package org.liufeng;

import static org.junit.Assert.fail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Test;
import org.liufeng.course.service.CoreService;
import org.liufeng.course.util.DBHelper;
import org.liufeng.course.util.DBHelper2;

public class TestDB {

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws SQLException {
		//insert();
		//query();
		//CoreService.query();
		DBHelper2 db = new DBHelper2();
		System.out.println(db.connectDB());
	}
	public static String query(){
		String str = "";
		String sql = "select * from tbl_car_person";//SQL语句  
		DBHelper db1 = new DBHelper(sql);//创建DBHelper对象  
		 try {  
			 SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
			 ResultSet ret = db1.pst.executeQuery();//执行语句，得到结果集  
	            while (ret.next()) {  
	                String uid = sDateFormat.format(ret.getTimestamp(1));
	                String ufname = ret.getString(2);  
	                String ulname = ret.getString(3);  
	                str = str+ ufname+" "+ulname+" time:"+uid+"\n";
	              //System.out.println(uid + "\t" + ufname + "\t" + ulname  );  
	            }//显示数据  
	            ret.close();  
	            db1.close();//关闭连接  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }  
		 System.out.println(str);
		 return str;
		
	}
	public static void insert(){
		String insert = "insert into tbl_car_person(info,insert_date) values('ttte好的e',now())";
		DBHelper db1 = new DBHelper(insert);//创建DBHelper对象  
		 try {  
			 db1.pst.execute();
	         db1.close();//关闭连接  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }  
	}

}
