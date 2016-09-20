<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <title>New Employee</title>
</head>
<body>
<%@include file="dishesActions.jsp" %>
<div id="mystyle" class="myform">
    <form:form action="/dishes/addSubmit" method="post" commandName="dish">
        <h1>Dish</h1>
        <p>To add new Dish enter following information</p>
        <form:label path="dishName">Name<span class="small">Enter the dish name</span></form:label>
        <form:input path="dishName"/>
        <form:label path="price">Price<span class="small">Enter the dish price</span></form:label>
        <form:input path="price"/>
        <form:label path="weight">Weight<span class="small">Enter the dish weight</span></form:label>
        <form:input path="weight"/>
        <form:label path="category">Category<span class="small">Enter the dish category</span></form:label>
        <form:select path="category" items="${categories}"/>
        <form:button type="submit">Add new Dish</form:button>
        <div class="spacer"></div>
    </form:form>
</div>
</body>
</html>
