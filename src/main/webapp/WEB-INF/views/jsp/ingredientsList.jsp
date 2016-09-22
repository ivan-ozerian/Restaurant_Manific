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
<%@include file="ingredientsActions.jsp" %>

<div>
    <table style="margin: auto">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Quantity</th>

        </tr>
        </thead>

        <c:forEach var="ingredient" items="${ingredients}">
            <tr>
                <td><c:out value="${ingredient.id}"/>
                </td>
                <td><c:out value="${ingredient.name}"/>
                </td>
                <td><c:out value="${ingredient.quantity}"/>
                </td>
                <td style="border: none;">
                </td>
                <td style="border: none;">
                    <div>
                        <form method="post" action="${pageContext.request.contextPath}/ingredients/updateForm">
                            <input type="hidden" name="ingredientId"
                                   value="<c:out value="${ingredient.id}"/>"/>
                            <input type="hidden" name="currentQuantity"
                                   value="<c:out value="${ingredient.quantity}"/>"/>
                            <input type="submit" value="Update"/>
                        </form>
                    </div>
                </td>
                <td style="border: none;">
                    <div>
                        <form method="post" action="${pageContext.request.contextPath}/ingredients/delete">
                            <input type="hidden" name="ingredientId"
                                   value="<c:out value="${ingredient.id}"/>"/>
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
