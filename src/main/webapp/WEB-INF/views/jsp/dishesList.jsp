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
<%@include file="dishesActions.jsp" %>

<div>
    <table style="margin: auto">
        <thead>
        <tr>
            <th>Dish ID</th>
            <th>Name</th>
            <th>Category</th>
            <th>Price</th>
            <th>Weight</th>
        </tr>
        </thead>

        <c:forEach var="dish" items="${dishes}">
            <tr>
                <td><c:out value="${dish.id}"/>
                </td>
                <td><c:out value="${dish.dishName}"/>
                </td>
                <td><c:out value="${dish.category}"/>
                </td>
                <td><c:out value="${dish.price}"/>
                </td>
                <td><c:out value="${dish.weight}"/>
                </td>
                <td style="border: none;">
                </td>
                <td style="border: none;">
                    <div>
                        <form method="post" action="/dishes/delete">
                            <input type="hidden" name="dishId" value="${dish.id}"/>
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
