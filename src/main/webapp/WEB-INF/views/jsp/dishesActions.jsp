<%@page import="java.util.Date" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <title>JSP Page</title>
</head>
<body>
<div style="text-align: center;">
    <div id="mystyle" style="border: none;">
        <h1>Restaurant Manific admin page</h1>
        <b>The application is made by Ivan Ozerian</b><br/>
        <%=new Date()%> </br>  </br>
        <p><div style="margin-left: 220px">
            <form action="/dishes/addForm">
                <input type="submit" value="Add new dish"/>
            </form>
            <form name="show" action="/dishes/showAll">
                <input type="submit" value="View all dishes"/>
            </form>
            <form action="/dishes/searchForm">
                <input type="submit" value="Search Dish by Name"/>
            </form>
            <form action="/greetingPage">
                <input type="submit" value="Back to start page"/>
            </form>
        </div>
        </p>
    </div>
</div>
</body>
</html>
