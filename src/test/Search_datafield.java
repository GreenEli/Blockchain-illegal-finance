package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Search_datafield {
	//to require a chird key number of string in the area
	public static String chirdString1(String str) {
		int count=0;
		int temp=0;int temp1=0;
		char[] tempChar = str.toCharArray();
		for (int kk = 0; kk < tempChar.length; kk++)
	      {
			if(tempChar[kk]=='"') {
//				tempChar1[kk]='\n';
//				str1=String.valueOf(tempChar1);
				count++;
				if(count==11) {
					 temp=kk;
				}
				if (count==12) {
					temp1=kk;
					break;
				}			
			}
//			这里就不增加其他情况判断
//	         tempChar1[kk]=tempChar[kk];
	      }
		String str1;
		int i=0;
		char[] tempChar2=new char[temp1-temp-1];
		for(int kk = temp+1; kk < temp1; kk++) {
			
			tempChar2[i]=tempChar[kk];
			i++;
		}
		str1=String.valueOf(tempChar2);
		return str1;
	}
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
	public static void main(String[] args) {
	     //  ArrayList<String> arrayList = new ArrayList<>();
	        try {
	            File file = new File("C:\\Users\\18170\\Desktop\\json\\同花顺上市公司详细情况2020-04-20 09-53-57.json");
	            InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file),"GB2312");
	            BufferedReader bf = new BufferedReader(inputReader);
	            // 按行读取字符串
	            String str;
	            
	            while ((str = bf.readLine()) != null) {   
	       //         arrayList.add(str);
	                System.out.println(str);
	                String str1=str.substring(117, 144);//this is mean for 
	               // String str1=str.substring(61, 100);//this is for create jsonfile
	                String str1_1=str.substring(0, 244);
	                String str2=chirdString(str1);
	                String str3=chirdString1(str1_1);
	                //str2=str2+".json";
	                System.out.println(str2);
	                System.out.println(str3);
	            	//write file data to another file
	                //String path="G:/"+str2;
	                /*罪魁祸首是str2多了个换行结束符
	                  String temp1="G:/";
	                String temp2=".json";
	                String path_temp=temp1+str2;
	                String path=path_temp+temp2;*/
	                
/*	did not need to creat a file in windows
 *                 String path="I:/同花顺_20-04-29/block801-1300/"+str2+".json";
	                System.out.println(path);
	                File filewriter = new File(path);
	            	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filewriter),"GB2312"));
	            	bw.write(str);
					bw.flush();  */
	                
	                /*
	                 * test for string+string
	                String temp1="G:/";
	                String temp2=".json";
	                System.out.println(temp1+str2+temp2);*/
	            }
	            bf.close();
	            inputReader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        
	        /*
	         * test for function of char and string
	        char a='男';
	        System.out.println(a);
	        char[] s={'A','G','C','\n','T'};
	        char[] s1=new char[3];
	        s1[0]='A';
	        s1[1]='A';
	        s1[2]='A';
	        String st=String.valueOf(s1);
	        System.out.println("This is : "+st+"ok");*/
	}
}