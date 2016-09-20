<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
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
    <form name="add" action="/orders/addDishSubmit" method="post">
        <h1>Order #<c:out value="${orderId}"/></h1>
        <p>Choose a dish from list for addition to order</p>
        <label>Name<span class="small">Choose the dish</span></label>
        <select name="dishId">
            <c:forEach var="dish" items="${dishes}">
                <option value="${dish.id}"><c:out value="${dish.dishName}"/></option>
            </c:forEach>
        </select>
        <button type="submit">Add dish to order</button>
        <input type="hidden" name="orderId" value="${orderId}">
        <div class="spacer"></div>
    </form>
</div>
</body>
</html>
