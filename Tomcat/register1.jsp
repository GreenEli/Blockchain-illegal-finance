<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查询金融链</title>
<link rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
    integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
    crossorigin="anonymous">
</head>
<body style="background-image:url(images/Black.png);background-size:100% 100%;background-attachment:fixed;" >
<div class="container" >
	<img src="images/QiangGe.jpg" width=10% height=10% />
</div>
    <div class="container">
        <div class="row" style="margin-top: 100px">
            <div class="col-md-4 col-md-offset-4">
                <div class="panel panel-primary" style="background-image:url(images/Black.png);background-size:100% 100%;background-attachment:fixed;">
                    <div class="panel-heading">
                       <span class="glyphicon glyphicon-plus"></span>金融区块链回溯查询系统
                    </div>
                    <div class="panel-body">
                        <form action="test1.jsp" method="POST" name="registerForm" >
                            <div class="form-group">
                                   <label for="">请输入公司名称</label><input type="text"
                                    class="form-control" name="name" placeholder="如：北京百度科技股份有限公司"
                                    autofocus="autofocus">
                            </div>
                            <div class="form-group">
                                <label for="">起始日期 </label> <input type="text"
                                    class="form-control" name="pwd" placeholder=" 格式：yyyy-MM-DD 如：2008-01-01">
                            </div>
                            <div class="form-group">
                                <label for="">截止日期</label> <input type="text"
                                    class="form-control" name="repwd" placeholder="格式：yyyy-MM-DD 如：2008-12-31">
                            </div>
                            <button type="submit" class="btn btn-primary"
                                onclick="return checkForm()">开始查询</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
    <script type="text/javascript">
        function checkForm() {
            var name = registerForm.name.value;
            var pwd = registerForm.pwd.value;
            var repwd = registerForm.repwd.value;
            //alert(name + pwd + repwd);
            if (name == "" || name == null) {
                alert("please input user name");
                registerForm.name.focus();
                return false;
            } else if (pwd == "" || pwd == null) {
                alert("please input pwd");
                registerForm.pwd.focus();
                return false;
            } else if (repwd == "" || repwd == null) {
                alert("please input repassword");
                registerForm.repwd.focus();
                return false;
            }
            alert('Waiting');
            return true;
        }
    </script>
</body>
</html>