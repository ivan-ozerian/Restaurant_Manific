<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <title>Login page</title>
</head>
<body>

<div id="mystyle" class="myform">

    <form name="form_login" action="/j_spring_security_check" method="post">
        <h2>Authentication page</h2>
        <h1><c:if test="${not empty error}">${error}</c:if></h1><br>
        <label>Login<span class="small">Enter the login</span></label>
        <input type="text" name="user_login" value=""/>
        <label>Password<span class="small">Enter the password</span></label>
        <input type="password" name="password_login"/>
        <button name="submit" type="submit" value="submit">Login</button>
        <br>
        <a href="/">Back to the client page</a>
        <div class="spacer"></div>
    </form>


</div>

</body>
</html>
