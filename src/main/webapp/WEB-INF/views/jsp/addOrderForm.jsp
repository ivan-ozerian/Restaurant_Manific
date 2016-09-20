<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <title>New Employee</title>
</head>
<body>
<%@include file="ordersActions.jsp" %>
<div id="mystyle" class="myform">
    <form:form action="/orders/addSubmit" method="post" commandName="order">
        <h1>Order</h1>
        <p>To add new Order enter following information</p>
        <div>
            <form:label path="waiter">Waiter<span class="small">Choose a waiter</span></form:label>
            <form:select path="waiter" items="${waiters}" itemValue="id"/>
        </div>
        <br>
        <div>
            <form:label path="tableNumber">Table number<span class="small">Enter the table number</span></form:label>
            <form:input path="tableNumber"/>
            <form:button type="submit">Add new order</form:button>
        </div>
        <div class="spacer"></div>
    </form:form>
</div>
</body>
</html>
