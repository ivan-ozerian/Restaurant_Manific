<%@page import="java.util.Date" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <title>JSP Page</title>
</head>
<body>
<div style="text-align: center;">
    <div id="mystyle" style="border: none;">
        <h1>Restaurant Manific admin page</h1>
        <b>The application is made by Ivan Ozerian</b><br/>
                <%=new Date()%> </br>  </br>
        <p><div style="margin-left: 120px">
            <form action="${pageContext.request.contextPath}/ingredients/addForm">
                <input type="submit" value="Add new ingredient"/>
            </form>
            <form name="show" action="${pageContext.request.contextPath}/ingredients/showAll">
                <input type="submit" value="View all ingredients"/>
            </form>
            <form action="${pageContext.request.contextPath}/ingredients/searchForm">
                <input type="submit" value="Search Ingredient by name"/>
            </form>
        <form action="${pageContext.request.contextPath}/ingredients/showExpiring">
            <input type="submit" value="Show expiring ingredients"/>
        </form>
            <form action="${pageContext.request.contextPath}/greetingPage">
                <input type="submit" value="Back to start page"/>
            </form>
        </div>
        </p>
    </div>
</div>
</body>
</html>
