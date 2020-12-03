package jsonfile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import sha1.Sha_1;

public class Filecreat {
	//to require a chird key number of string in the area
	public static String chirdString(String str) {
		int count=-1; //to count filename's length
		String str1=null;
		char[] tempChar = str.toCharArray();
		char[] tempChar1=new char[50];
		for (int kk = 0; kk < tempChar.length; kk++)
	      {
			count++;
			if(tempChar[kk]=='"') {
				tempChar1[kk]='\n';
				str1=String.valueOf(tempChar1);
				break;
			}
	         tempChar1[kk]=tempChar[kk];
	      }
		//mean to get a fit length filename
		char[] temp=new char[count];
		for (int kk = 0; kk < count; kk++)
	      {
	         temp[kk]=tempChar1[kk];
	      }
		str1=String.valueOf(temp);
		return str1;
	}
	public static void main(String[] args) throws IOException, URISyntaxException {
	     //  ArrayList<String> arrayList = new ArrayList<>();
	        try {
	            File file = new File("C:\\Users\\18170\\Desktop\\json\\同花顺上市公司详细情况2020-04-20 09-53-57.json");
	            InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file),"GB2312");
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
	                String str2=chirdString(str1);
	                //str2=str2+".json";
	                System.out.println(str2);
	            	//write file data to another file
	                //String path="G:/"+str2;
/*//	                罪魁祸首是str2多了个换行结束符
	                  String temp1="G:/";
	                String temp2=".json";
	                String path_temp=temp1+str2;
	                String path=path_temp+temp2;*/
	                
	                String path="I:/TongHuaShun5/"+str2+".json";
	                System.out.println(path);
	                File filewriter = new File(path);
	            	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filewriter),"GB2312"));
	            	bw.write(str);
	            	bw.flush();  
	            	bw.close();
	            	
	            	String hash1=Sha_1.getFileSha1(filewriter);
	            	System.out.print(hash1);
	            	String path1="I:/TongHuaShun5/"+hash1+".json";
	            	filewriter.renameTo(new File(path1)); 
//	            	load file to hdfs		
	                Path resP = new Path(path1);
	                Path destP = new Path("/liuqiang");
	                if(!fs.exists(destP)){
	                    fs.mkdirs(destP);
	                }
	                String name = "F://a.txt".substring("F://a.txt".lastIndexOf("/")+1, "F://a.txt".length());
	                fs.copyFromLocalFile(resP, destP);
	                System.out.println("name is " + name);
	                
	                
/*	                 * test for string+string
	                String temp1="G:/";
	                String temp2=".json";
	                System.out.println(temp1+str2+temp2);*/
/*//	                delete file in local
	                filewriter.delete();*/
	                
	            }
	            //close hdfs connect
	            fs.close();

		


        
/*        //test for download file from hdfs
        String MASTER_URI = "hdfs://localhost:9000";
        String DEST_PATH = "/aaaaaab/block1-100/东方电子股份有限公司.json";
        Configuration conf1 = new Configuration();

        FileSystem fs1 = FileSystem.get(new URI(MASTER_URI), conf1);

        InputStream in = fs1.open(new Path(DEST_PATH));

        OutputStream out = new FileOutputStream("I:\\同花顺_20-04-29\\new1\\hehe.txt");

        IOUtils.copyBytes(in, out, conf1);
        System.out.print("ok");
        fs1.close();*/
	            bf.close();
	            inputReader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        
	        
	}
}
