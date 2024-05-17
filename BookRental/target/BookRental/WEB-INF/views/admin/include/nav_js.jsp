<%--
  Created by IntelliJ IDEA.
  User: kimjungmin
  Date: 2024/05/03
  Time: 4:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    function searchBookForm(){
        console.log("searchBookForm() called!!");

        let form = document.search_book_form;

        if(form.b_name.value=== ''){
            alert("Enter the name of the book you are looking for");
            form.b_name.focus();
        }else{
            form.submit();
        }
    }
</script>