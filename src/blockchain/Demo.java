package blockchain;

import java.sql.DriverManager;
import java.sql.ResultSet;  
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement; 

public class Demo {  
    
    static String sql = null;  
    static String sql1 = null;  
    static DBHelper db1 = null;  
    static ResultSet ret = null;  
    
    public static void main(String[] args) {  
    	Connection conn;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test01", "root", "qiangge0");
            conn.setAutoCommit(false);

            // 保存当前自动提交模式
            boolean autoCommit = conn.getAutoCommit();
            // 关闭自动提交
            conn.setAutoCommit(false);
             Statement stmt =(Statement) conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); 
             //this is invalid things.
             int num=0;
            for (int i = 1; i <= num; i++) {
                stmt.addBatch("update test_user set test_user.u_name = ('d"+i+"') where test_user.u_name = ('c"+i+"')"); 
            }

            stmt.executeBatch();   
            conn.commit(); 
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    	/*
        sql = "select *from test001 where block_id=1";//SQL语句  
        String a="hwhw";int i=0;
        sql1="update test001 set name1='heheda' where block_id=1";
        db1 = new DBHelper(sql1);//创建DBHelper对象
        
        try {  
           // ret = db1.pst.executeQuery()excuteUpdate(sqlString); ;//执行语句，得到结果集  
        	db1.pst.execute(sql1);
            /*
            while (ret.next()) {  
                String uid = ret.getString(1);  
                String ufname = ret.getString(2);  
                String ulname = ret.getString(3);  
                String udate = ret.getString(4);  
                System.out.println(uid + "\t" + ufname + "\t" + ulname + "\t" + udate );  
            }//显示数据  
            //ret.close();  
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  */
    }  
    
}
