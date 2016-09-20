<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <title>New Employee</title>
</head>
<body>
<%@include file="ingredientsActions.jsp" %>
<br>
<div id="mystyle" class="myform">
    <form:form action="/ingredients/addSubmit" method="post" commandName="ingredient">
        <h1>Ingredient</h1>
        <p>To add new Ingredient enter following information</p>
        <form:label path="name">Name<span class="small">Enter the ingredient name</span></form:label>
        <form:input path="name"/>
        <form:label path="quantity">Quantity<span class="small">Enter the ingredient quantity</span></form:label>
        <form:input path="quantity"/>
        <form:button type="submit">Add Ingredient</form:button>
        <div class="spacer"></div>
    </form:form>
</div>
</body>
</html>
