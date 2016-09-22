<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <title>New Employee</title>
</head>
<body>
<%@include file="menusActions.jsp" %>
<div id="mystyle" class="myform">
    <form id="form" name="form" action="${pageContext.request.contextPath}/menus/findByName" method="post">
        <h1>Menu</h1>
        <p>To search a Menu enter it's name</p>
        <label>Name<span class="small">Enter name</span></label>
        <input type="text" name="menuName"/>
        <button type="submit">Search</button>
        <div class="spacer"></div>
    </form>
</div>
</body>
</html>
