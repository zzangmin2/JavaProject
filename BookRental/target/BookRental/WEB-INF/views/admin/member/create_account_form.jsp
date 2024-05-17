<%--
  Created by IntelliJ IDEA.
  User: kimjungmin
  Date: 2024/05/17
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="../../include/title.jsp" />
    <link rel="stylesheet" href="<c:url value="/resources/css/admin/create_account_form.css" />" type="text/css" >
</head>
<body>
    <jsp:include page="../../include/header.jsp" />
    <jsp:include page="../include/nav.jsp" />
    <section>
        <div id="section_wrap">
            <div class="word">
                <h3>CREATE ACCOUNT FORM</h3>
            </div>
            <div class="create_account_form">
                <form action="<c:url value="/admin/member/createAccountConfirm"/>" name="create_account_form" method="post">
                    <input type="text" name="id" placeholder="input admin id"/><br/>
                    <input type="password" name="password" placeholder="input admin password"/><br/>
                    <input type="password" name="password_again" placeholder="input admin password again"/><br/>
                    <input type="text" name="name" placeholder="input admin name"/><br/>
                    <select name="gender">
                        <option value="">select admin gender</option>
                        <option value="M">Man</option>
                        <option value="W">Woman</option>
                    </select><br/>
                    <input type="text" name="part" placeholder="input admin part"/><br/>
                    <input type="text" name="position" placeholder="input admin position"/><br/>
                    <input type="text" name="email" placeholder="input admin email"/><br/>
                    <input type="text" name="phone" placeholder="input admin phone"/><br/>
                    <input type="button" value="create account" onclick="createAccountForm()">
                    <input type="reset" value="reset">
                </form>
            </div>
            <div class="login">
                <a href="<c:url value="/admin/member/loginForm" />">login</a>
            </div>
        </div>
    </section>
    <jsp:include page="../../include/footer.jsp" />

    <script type="text/javascript">
        function createAccountForm(){
            console.log("createAccountForm() CALLED!!");
            //1. form validation
            let form = document.create_account_form;
            if(form.id.value === '') {
                alert("input admin id");
                form.id.focus();
            }else if(form.password.value === ""){
                alert("input admin password again");
                form.password_again.focus();
            } else if(form.password.value !== form.password_again.value){
                alert("please check your password again");
                form.password_again.focus();
            }else if(form.name.value === ""){
                alert("input admin name again");
                form.name.focus();
            } else if(form.email.value === ""){
                alert("input admin email again");
                form.email.focus();
            }
            //2. form submit

            else{
                form.submit();
            }
        }
    </script>
</body>
</html>
