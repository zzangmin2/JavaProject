<%@ page import="daelim.book.rental.admin.member.AdminMemberVo" %><%--
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
    <link rel="stylesheet" href="<c:url value="/resources/css/admin/modify_account_form.css" />" type="text/css" >
</head>
<body>
<jsp:include page="../../include/header.jsp" />
<jsp:include page="../include/nav.jsp" />
<section>
    <div id="section_wrap">
        <div class="word">
            <h3>MODIFY ACCOUNT FORM</h3>
        </div>

        <%
            AdminMemberVo loginedAdminMemberVo = (AdminMemberVo) session.getAttribute("loginedAdminMemberVo");

        %>
        <div class="modify_account_form">
            <form action="<c:url value='/admin/member/modifyAccountConfirm' />" name="modify_account_form" method="post">

                <input type="hidden" 	name="no"		value="<%= loginedAdminMemberVo.getNo() %>">

                <input type="text" value="<%= loginedAdminMemberVo.getId()%>" name="id" placeholder="input admin id" readonly disabled/><br/>
                <input type="text" value="<%= loginedAdminMemberVo.getId()%>" name="name" placeholder="input admin name"/><br/>
                <select name="gender">
                    <option value="">select admin gender</option>
                    <option value="M" <% if (loginedAdminMemberVo.getGender().equals("M")) {%> selected <%}%>>Man</option>
                    <option value="W" <% if (loginedAdminMemberVo.getGender().equals("W")) {%> selected <%}%>>Woman</option>
                </select><br/>
                <input type="text" name="part" value="<%= loginedAdminMemberVo.getPart() %>" placeholder="input admin part"/><br/>
                <input type="text" name="position" value="<%= loginedAdminMemberVo.getPosition() %>" placeholder="input admin position"/><br/>
                <input type="email" name="email" value="<%= loginedAdminMemberVo.getEmail() %>" placeholder="INPUT USER MAIL." ><br>
                <input type="text" name="phone" value="<%= loginedAdminMemberVo.getPhone() %>" placeholder="input admin phone"/><br/>
                <input type="button" value="modify account" onclick="modifyAccountForm()">
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
    function modifyAccountForm(){
        console.log("modifyAccountForm() CALLED!!");
        //1. form validation
        let form = document.modify_account_form;
        if(form.name.value === ""){
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
