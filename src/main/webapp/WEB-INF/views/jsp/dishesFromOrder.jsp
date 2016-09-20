<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <title>JSP Page</title>
</head>
<body>
<%@include file="ordersActions.jsp" %>
<h2 style="padding-top: 20px; text-align: center;">Order #<c:out value="${orderId}"/></h2>
<br>
<div style="margin: auto; text-align: center; padding-bottom: 10px">
    <form action="/orders/addDishForm" method="post">
        <input type="hidden" name="orderId" value="${orderId}"/>
        <input type="submit" value="Add dish"/>
    </form>
</div>
<div>
    <table style="margin: auto">
        <thead>
        <tr>
            <th>Dish ID</th>
            <th>Name</th>
            <th>Category</th>
            <th>Price</th>
        </tr>
        </thead>

        <c:forEach var="dish" items="${orderDishes}">
            <tr>
                <td><c:out value="${dish.id}"/>
                </td>
                <td><c:out value="${dish.dishName}"/>
                </td>
                <td><c:out value="${dish.category}"/>
                </td>
                <td><c:out value="${dish.price}"/>
                </td>
                </td>
                <td style="border: none;">
                </td>
                <td style="border: none;">
                    <div>
                        <form action="/orders/deleteFromOrder" method="post">
                            <input type="hidden" name="dishId" value="${dish.id}"/>
                            <input type="hidden" name="orderId" value="${orderId}"/>
                            <input type="submit" value="Delete"/>
                        </form>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
