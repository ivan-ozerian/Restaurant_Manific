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
        <form action="${pageContext.request.contextPath}/kitchen/addSubmit" method="post">
            <h1>Order #<c:out value="${orderId}"/></h1>
            <p>Choose the order</p>
            <div>
                <label>Dish<span class="small">Choose a dish</span></label>
                <select name="dishId">
                    <c:forEach var="dish" items="${dishes}">
                        <option value="${dish.id}"><c:out value="${dish.dishName}"/></option>
                    </c:forEach>
                </select>
            </div>
            <br>
            <div>
                <label>Cook<span class="small">Choose a cook</span></label>
                <select name="cookId">
                    <c:forEach var="cook" items="${cooks}">
                        <option value="${cook.id}"><c:out value="${cook.name}"/></option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit">Add prepared dish</button>
            <input type="hidden" name="orderId" value="${orderId}"/>
            <div class="spacer"></div>
        </form>
    </div>
</body>
</html>
