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
        <p>
        <div style="margin-left: 350px">
            <form action="${pageContext.request.contextPath}/kitchen/chooseOrder">
                <input type="submit" value="Add prepared dish"/>
            </form>
            <form action="${pageContext.request.contextPath}/kitchen/showAll">
                <input type="submit" value="View all prepared dishes"/>
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
