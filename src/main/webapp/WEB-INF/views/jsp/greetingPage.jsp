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
        <p><b>The application is made by Ivan Ozerian</b><br/>
            <%=new Date()%> </br>  </br>
            <b>Please choose the section!</b><br/>
        <div>

        <form action="/employees/">
            <input type="submit" value="Employees"/>
        </form>
        <form action="/dishes/">
            <input type="submit" value="Dishes"/>
        </form>
        <form action="/menus/">
            <input type="submit" value="Menu"/>
        </form>
        <form action="/orders/">
            <input type="submit" value="Orders"/>
        </form>
        <form action="/kitchen/">
            <input type="submit" value="Kitchen"/>
        </form>
        <form action="/ingredients/">
            <input type="submit" value="Storage"/>
        </form>
        </p>
    </div>
</div>
</body>
</html>
