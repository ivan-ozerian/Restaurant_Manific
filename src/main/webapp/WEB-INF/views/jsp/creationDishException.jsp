<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <title>Error!</title>
</head>
<body>
<%@include file="greetingPage.jsp" %>
<div>
    <h2 style="border: 3px solid;
               background-color: bisque;
               margin: 50px auto auto;
               text-align: center;
               padding: 20px;
               width: 80%">
        Sorry! You have entered invalid parameters during form filling.
    </h2>
</div>
<div id="mystyle" style="border: none;
                         margin: 20px auto auto 50px;
                         text-align: center;
                         background: none">
    <form action="/dish/addForm/">
        <input style="float: none" type="submit" value="Back"/>
    </form>
</div>
</body>
</html>
