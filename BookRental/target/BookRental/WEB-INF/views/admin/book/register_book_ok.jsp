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
    <link rel="stylesheet" href="<c:url value="/resources/css/admin/create_account_result.css" />" type="text/css" >
</head>
<body>
<jsp:include page="../../include/header.jsp" />
<jsp:include page="../include/nav.jsp" />
<section>
    <div id="section_wrap">
        <div class="word">
            <h3>CREATE BOOK SUCCESS!</h3>
        </div>

        <div class="others">
            <a href="<c:url value="/book/admin/registerBookForm" />">registerBookForm</a>
        </div>
    </div>
</section>
<jsp:include page="../../include/footer.jsp" />
</body>
</html>
