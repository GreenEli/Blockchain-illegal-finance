package blockchain.mysqlstep;
//比较全面的总概括型程序
import org.apache.hadoop.hbase.client.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.mysql.jdbc.DatabaseMetaData;

import hbase.ExampleForHbase;
import jsonfile.Filecreat;
import sha1.Sha_1;
import test.Search_datafield;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;

public class Creatchart {
    public static final String url = "jdbc:mysql://127.0.0.1:3306/test02?characterEncoding=utf8";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "root";  
    public static final String password = "qiangge0";  
  
    public static Connection conn = null;  
    public PreparedStatement pst = null;  
    
//    hbase connection
	public static Configuration configuration;
	public static org.apache.hadoop.hbase.client.Connection connection;
	public static Admin admin;
	
	
  // 连接的地方可以优化，后面再看
    public Creatchart(String sql,String tablename) {  
        try {  
            Class.forName(name);//指定连接类型  
            conn = DriverManager.getConnection(url,user,password);//获取连接  
            pst = conn.prepareStatement(sql);//准备执行语句  
      //      java.sql.DatabaseMetaData dbmd=conn.getMetaData();
            ResultSet tableRS = conn.getMetaData().getTables(null, null,  tablename, null );
            if (tableRS.next()) {
                 System.out.println(" the table is exsited");    
            }
            else {
            	System.out.print("wrong exits");
            	pst.execute();//执行语句，得到结果集
            }
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
	/**
	 * 向某一行的某一列插入数据
	 * 
	 * @param tableName
	 *            表名
	 * @param rowKey
	 *            行键
	 * @param colFamily
	 *            列族名
	 * @param col
	 *            列名（如果其列族下没有子列，此参数可为空）
	 * @param val
	 *            值
	 * @throws IOException
	 */
    public void close() {  
        try {  
            this.conn.close();  
            this.pst.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    } 
    public static void main(String[] args) throws IOException, URISyntaxException {
		//read jsonfile
        Search_datafield filecreat=new Search_datafield();
        File file = new File("C:\\Users\\18170\\Desktop\\json\\同花顺上市公司详细情况2020-04-20 09-53-57.json");
        InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file),"GBK");
        BufferedReader bf = new BufferedReader(inputReader);
        // 按行读取字符串
        String str;
        
		//agood place for see hdfs file : http://localhost:50070/
		Configuration conf = new Configuration();
        URI uri = new URI("hdfs://localhost:9000");//"hdfs://localhost:9000/hbase"
        FileSystem fs = FileSystem.get(uri,conf);
        
        while ((str = bf.readLine()) != null) {   
   //         arrayList.add(str);
            System.out.println(str);
            String str1=str.substring(117, 144);//this is mean for 
           // String str1=str.substring(61, 100);//this is for create jsonfile
            String str2=filecreat.chirdString(str1);
            String str1_1=str.substring(0, 244);
            String str3=filecreat.chirdString1(str1_1);
            //str2=str2+".json";
            System.out.println(str2);
            System.out.println(str3);
            
            String path="I:/TongHuaShun7/"+str2+".json";
            System.out.println(path);
            File filewriter = new File(path);
        	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filewriter),"GBK"));
        	bw.write(str);
        	bw.flush();  
        	bw.close();
        	
        	String hash1=Sha_1.getFileSha1(filewriter);
        	System.out.print(hash1);
        	String path1="I:/TongHuaShun7/"+hash1+".json";
        	filewriter.renameTo(new File(path1)); 
        	
//        	load file to hdfs		
            Path resP = new Path(path1);
            Path destP = new Path("/user");
            if(!fs.exists(destP)){
                fs.mkdirs(destP);
            }
            String name = "F://a.txt".substring("F://a.txt".lastIndexOf("/")+1, "F://a.txt".length());
            fs.copyFromLocalFile(resP, destP);
            System.out.println("name is " + name);
            
            //as for mysql
        	String Define="CREATE TABLE `test02`.`"+str2+"` (\r\n" + 
        			"  `name` VARCHAR(45) NOT NULL,\r\n" + 
        			"  `time` VARCHAR(45) NULL,\r\n" + 
        			"  `hash` VARCHAR(45) NULL,\r\n" + 
        			"  PRIMARY KEY (`name`));";
    		Creatchart creatchart=new Creatchart(Define,str2);
//            mean to insert the data to the chart
            String sql_1="INSERT INTO `test02`.`"+str2+"` (`name`,`time`,`hash`) VALUES ('"+str2+"','"+str3+"', '"+hash1+"')";
            try {
				creatchart.pst = conn.prepareStatement(sql_1);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            try {
				creatchart.pst.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//执行语句，得到结果集
//            insert to hbase 表
/*            ExampleForHbase.insertRow("Score", hash1, "sname", "", hash1+".db");
            ExampleForHbase.insertRow("Score", hash1, "content", "", hash1+".db");*/
            System.out.println("ok0");
            ExampleForHbase.insertRow("Economic_illegal", hash1, "FileData", "Title",hash1+".db" );
            System.out.println("ok1");
            ExampleForHbase.insertRow("Economic_illegal", hash1, "FileData", "Content", str);
            
            ExampleForHbase.getData("Economic_illegal", hash1, "FileData", "Title");
            ExampleForHbase.getData("Economic_illegal", hash1, "FileData", "Content");
            System.out.println("ok2");
            creatchart.close();
            }
        //close hdfs、reader-i/o流 connect
        fs.close();
        bf.close();
        inputReader.close();
	}
}

