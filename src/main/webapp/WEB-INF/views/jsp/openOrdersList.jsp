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

<div>
    <table style="margin: auto">
        <thead>
        <tr>
            <th>Order<br> ID</th>
            <th>Waiter<br> name</th>
            <th>Waiter<br> surname</th>
            <th>Order<br> time</th>
            <th>Table<br> number</th>
            <th>Status</th>
        </tr>
        </thead>

        <c:forEach var="order" items="${orders}">

            <tr>
                <td><c:out value="${order.id}"/>
                </td>
                <td><c:out value="${order.waiter.name}"/>
                </td>
                <td><c:out value="${order.waiter.surname}"/>
                </td>
                <td><fmt:formatDate type="both" dateStyle="short" timeStyle="medium" value="${order.orderTime}"/>
                </td>
                <td><c:out value="${order.tableNumber}"/>
                </td>
                <td><c:if test="${order.status==true}">Open</c:if>
                </td>
                <td style="border: none;">
                </td>
                <td style="border: none;">
                    <div>
                        <form action="/orders/orderDishes" method="post">
                            <input type="hidden" name="orderId" value="${order.id}"/>
                            <input type="submit" value="See order dishes"/>
                        </form>
                    </div>
                </td>
                <td style="border: none;">
                    <div>
                        <form action="/orders/addDishForm" method="post">
                            <input type="hidden" name="orderId" value="${order.id}"/>
                            <input type="submit" value="Add dish"/>
                        </form>
                    </div>
                </td>
                <td style="border: none;">
                    <div>
                        <form action="/orders/close" method="post">
                            <input type="hidden" name="orderId" value="${order.id}"/>
                            <input type="submit" value="Make close"/>
                        </form>
                    </div>
                </td>
                <td style="border: none;">
                    <div>
                        <form action="/orders/delete" method="post">
                            <input type="hidden" name="orderId" value="${order.id}"/>
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
