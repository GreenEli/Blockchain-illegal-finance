<%@ page language="java" import="java.sql.*" pageEncoding="UTF-8"  errorPage="error.jsp"%>

<html>
<head>
<script type="text/javascript">
</script>
<meta charset="UTF-8">
<title>详细资料</title>
<link rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
    integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
    crossorigin="anonymous">
</head> 
<body> 
<div style="width:100%;height:100%;float:left">
<div style="width:80%;height:100%;float:left" >
<table border="1" width=100% height=100% style="background-image:url(images/Black.png);background-size:100% 100%;background-attachment:fixed;" align="center" cellspacing="1" cellpadding="1">
<tr  width=100% height=10% align="center"><td><font color="white"><b>@辽宁大学信息学院</b></font></td></tr>
<tr  width=100% height=30%  >
            <td width="100%" >
<%
try{
Connection conn = null;
PreparedStatement ps = null;
Class.forName("com.mysql.jdbc.Driver");
String url = "jdbc:mysql://localhost:3306/test01?useUnicode=true&characterEncoding=UTF8";
String username = "root";
String password = "qiangge0";
conn = DriverManager.getConnection(url, username, password);
request.setCharacterEncoding("UTF-8");//设置编码方式，防止中文乱码
	String name = request.getParameter("name");
	String pwd = request.getParameter("pwd");
	String pwd_End = request.getParameter("repwd");
String sql="select * from `test01`.`"+name+"` where time>='"+pwd+"' and time<='"+pwd_End+"' order by time ASC";
ps = conn.prepareStatement(sql);
ResultSet rs=ps.executeQuery(sql);
%>
                <table width=100% height=33% border="1"   align="left" cellpadding="0" cellspacing="0">
                    <tr><td align="center"><font color="white" size="6"><%=name%></font></td></tr>
                </table>
<%
String uri="http://desktop-ov3fhpb:50075/webhdfs/v1/user/04126e93a4e29d1c73aaaa9c73e9520a5c2745e4.json?op=OPEN&namenoderpcaddress=localhost:9000&offset=0";
int i=0;
while(i++<10){
if(rs.next()){
%>
	 <table width=9% height=50% border="10" style="background-image:url(images/TieLianSmall.jpg);background-attachment:fixed;margin-left:5px;margin-right:5px;margin-top:15px;margin-bottom:5px;" bgcolor="#f60"  align="left" cellspacing="0" >
                     <tr><td align="center"><font color="white" ><a href="<%=uri%>" target="_blank"><%=rs.getObject(3) %></a></font></td></tr>
                </table>
<%
	}
else{
%>
	 <table width=9% height=50% border="10" style="background-image:url(images/TieLianSmall.jpg);background-attachment:fixed;margin-left:5px;margin-right:5px;margin-top:15px;margin-bottom:5px;" bgcolor="#f60"  align="left" cellspacing="0" >
                    <tr><td align="center"><font color="white" ><b>Null Block</b></font></td></tr>
                </table>
<%
      }	
}
rs.close();
ps.close();
conn.close();
}catch(Exception e){
e.printStackTrace();
}
%> 
            </td>
        </tr>

<tr  width=100% height=30%  >
            <td width="100%" >
<%
try{
Connection conn = null;
PreparedStatement ps = null;
Class.forName("com.mysql.jdbc.Driver");
String url = "jdbc:mysql://localhost:3306/test01?useUnicode=true&characterEncoding=UTF8";
String username = "root";
String password = "qiangge0";
conn = DriverManager.getConnection(url, username, password);
request.setCharacterEncoding("UTF-8");//设置编码方式，防止中文乱码
	String name = request.getParameter("name");
	String pwd = request.getParameter("pwd");
	String pwd_End = request.getParameter("repwd");
String sql="select * from `"+name+"` where time>='"+pwd+"' and time<='"+pwd_End+"'";
ps = conn.prepareStatement(sql);
ResultSet rs=ps.executeQuery(sql);
if(rs.next()){
	String name_Line=rs.getString(5);
	String sql_1="select * from `test01`.`"+name_Line+"` where time>='"+pwd+"' and time<='"+pwd_End+"' order by time ASC";
	ps = conn.prepareStatement(sql_1);
	ResultSet rs_1=ps.executeQuery(sql_1);
%>
                <table width=100% height=33% border="1"   align="left" cellpadding="0" cellspacing="0">
                    <tr><td align="center"><font color="white" size="6"><%=name_Line%></font></td></tr>
                </table>
<%
	String uri="http://desktop-ov3fhpb:50075/webhdfs/v1/user/04126e93a4e29d1c73aaaa9c73e9520a5c2745e4.json?op=OPEN&namenoderpcaddress=localhost:9000&offset=0";
	int i=0;
	while(i++<10){
	if(rs_1.next()){
%>
	 <table width=9% height=50% border="10" style="background-image:url(images/TieLianSmall.jpg);background-attachment:fixed;margin-left:5px;margin-right:5px;margin-top:15px;margin-bottom:5px;" bgcolor="#f60"  align="left" cellspacing="0" >
                    <tr><td align="center"><font color="white" ><a href="<%=uri%>" target="_blank"><%=rs_1.getObject(3) %></a></font></td></tr>
                </table>
<%
	}
else{
%>
	 <table width=9% height=50% border="10" style="background-image:url(images/TieLianSmall.jpg);background-attachment:fixed;margin-left:5px;margin-right:5px;margin-top:15px;margin-bottom:5px;" bgcolor="#f60"  align="left" cellspacing="0" >
                    <tr><td align="center"><font color="white" ><b>NULL BLOCK</b></font></td></tr>
                </table>
<%
      }	
}
	}

rs.close();
ps.close();
conn.close();
}catch(Exception e){
e.printStackTrace();
}
%> 
            </td>
        </tr>
<tr  width=100% height=10% align="right"><td><font color="white"><b><br></br>@creator 大罐头强<br>@email:18170097254@163.com</br></b></font></td></tr>
</table>
</div>
<div style="width:20%;height:100%;background-image:url(images/BlockChain_Right.gif);background-size:100% 100%;float:left">
</div>
</div>
</body>
</html>