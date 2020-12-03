package sha1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import blockchain.DBHelper;
//4c71671b4079ff6904fbc1786088ae850d03f6ad
//5cc8473bb7af278a208be454888f879136b72ddb

//未解之谜，为什莫更新到数据库是如此的有序化
public class Paixu {
	//mean to change file to sha-1
	public static String getFileSha1(File file) 
	{
	    FileInputStream in = null;
	    try
	    {
	        in = new FileInputStream(file);
	        MessageDigest digest = MessageDigest.getInstance("SHA-1");
	        byte[] buffer = new byte[1024 * 1024 * 10];

	        int len = 0;
	        while ((len = in.read(buffer)) > 0)
	        {
	            digest.update(buffer, 0, len);
	        }
	        String sha1 = new BigInteger(1, digest.digest()).toString(16);
	        int length = 40 - sha1.length();
	        if (length > 0)
	        {
	            for (int i = 0; i < length; i++)
	            {
	                sha1 = "0" + sha1;
	            }
	        }
	        return sha1;
	    }
	    catch (IOException e)
	    {
	        System.out.println(e);
	    }
	    catch (NoSuchAlgorithmException e)
	    {
	        System.out.println(e);
	    }
	    finally
	    {
	        try
	        {
	            if (in != null)
	            {
	                in.close();
	            }
	        }
	        catch (IOException e)
	        {
	            System.out.println(e);
	        }
	    }
		return null;
	}

	public static void main(String[] args) {
		/*  单个数据文件进行处理
		 File file=new File("C:\\Users\\18170\\Desktop\\刘强-陆家嘴-人人贷\\人人贷\\1-人人贷_刘强_企业工商数据.xlsx");
		 String sha;
		 sha=getFileSha1(file);
		 System.out.print(sha);*/
		//目录下全部文件进行处理
    	/*/Connection conn;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test01", "root", "qiangge0");
            conn.setAutoCommit(false);

            // 保存当前自动提交模式
            boolean autoCommit = conn.getAutoCommit();
            // 关闭自动提交
            conn.setAutoCommit(false);
             Statement stmt =(Statement) conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); 
            File file = new File("C:\\Users\\18170\\Desktop\\刘强-陆家嘴-人人贷\\人人贷");
     		File[] files = file.listFiles();
            for (int i = 1; i < files.length; i++) {
            	File temp = files[i];
   			 String sha= getFileSha1(temp);
   			 System.out.println(sha);
   			 int count=i+1;
                stmt.addBatch("update test001 set test001.hash = (temp.getName()) where test001.name =('qiang')"); 
            }

            stmt.executeBatch();   
            conn.commit(); 
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
//		String a="hahahahahaha的话";
//		System.out.println(a);
	File file = new File("I:/同花顺_20-04-29/block801-1300");
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
	        
			File temp = files[i];
			 	String buffer=temp.getName();
			 	buffer=buffer.substring(0,buffer.length()-5);
		        //String sql = "update test001 set filestore=temp.getName(),hash=sha where block_id=i+1";//SQL语句  
			 	//String sql = "update 同花顺上市公司详细情况2020_04_20_09_53_57 set firestore='I:/同花顺_20-04-29/block101-200/"+temp.getName()+"',hash='"+sha+"' where block_id="+String.valueOf(i+101);//SQL语句  
			 	String sql1 = "update 同花顺上市公司详细情况2020_04_20_09_53_57 set corporationbackground_industrycommerceimformation_name='"+buffer+"' where block_id="+String.valueOf(i+801);//SQL语句  
			 	
			 	//update test001 set hash="111" where block_id=1;
			 	//String sql = "UPDATE `test01`.`test001` SET `hash` = 'G:\\\\mysql-5.6.47-winx64哈啊哈' WHERE (`block_id` = '10')";//SQL语句
			 	System.out.println(buffer);//中文乱码？？
		        DBHelper db1 = new DBHelper(sql1);//创建DBHelper对象
		        ResultSet ret = null;
		        try {
		        	db1.pst.execute();//执行语句，得到结果集   
					db1.close();//关闭连接  
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		
		}
		//prove the jsonfile is right or inright
/*		File file = new File("I:/同花顺_20-04-29/阿尔特汽车技术股份有限公司.json");
		String sha= getFileSha1(file);
		System.out.println(sha);*/
	}
}
