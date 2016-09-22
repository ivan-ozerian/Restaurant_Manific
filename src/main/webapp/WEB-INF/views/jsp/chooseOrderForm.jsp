<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <title>New Employee</title>
</head>
<body>
<%@include file="kitchenActions.jsp" %>
<div id="mystyle" class="myform">
    <form action="${pageContext.request.contextPath}/kitchen/chooseDishAndCook" method="post">
        <h1>Order</h1>
        <p>Choose the order</p>
        <div>
            <label>Order<span class="small">Choose an order</span></label>
            <select name="orderId">
                <c:forEach var="order" items="${orders}">
                    <option value="${order.id}"><c:out value="${order.id}"/></option>
                </c:forEach>
            </select>
            <button type="submit">Submit</button>
            <div class="spacer"></div>
        </div>
    </form>
</div>
</body>
</html>
