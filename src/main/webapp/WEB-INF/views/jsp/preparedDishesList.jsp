<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <title>JSP Page</title>
</head>
<body>
<%@include file="kitchenActions.jsp" %>
<br>
<div>
    <table style="margin: auto">
        <thead>
        <tr>
            <th>ID</th>
            <th>Order<br> number</th>
            <th>Dish<br> name</th>
            <th>Cook<br> name</th>
            <th>Cook<br> surname</th>
            <th>Prepared<br> time</th>
        </tr>
        </thead>

        <c:forEach var="preparedDish" items="${preparedDishes}">

            <tr>
                <td><c:out value="${preparedDish.id}"/>
                </td>
                <td><c:out value="${preparedDish.order.id}"/>
                </td>
                <td><c:out value="${preparedDish.dish.dishName}"/>
                </td>
                <td><c:out value="${preparedDish.cook.name}"/>
                </td>
                <td><c:out value="${preparedDish.cook.surname}"/>
                </td>
                <td><fmt:formatDate type="both" dateStyle="short" timeStyle="medium"
                                    value="${preparedDish.preparedTime}"/>
                </td>
                <td style="border: none;">
                </td>
                <td style="border: none;">
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
