<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <form:form action="${pageContext.request.contextPath}/menus/addSubmit" method="post" commandName="menu">
        <h1>Menu</h1>
        <p>To add new Menu enter following information</p>
        <form:label path="menuName">Name<span class="small">Enter the menu name</span></form:label>
        <form:input path="menuName" type="text"/>
        <form:button type="submit">Add new Menu</form:button>
        <div class="spacer"></div>
    </form:form>
</div>
</body>
</html>
