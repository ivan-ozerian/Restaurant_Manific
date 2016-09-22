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
<%@include file="menusActions.jsp" %>

<div>
    <table style="margin: auto">
        <thead>
        <tr>
            <th>Menu ID</th>
            <th>Menu name</th>
        </tr>
        </thead>


        <tr>
            <td><c:out value="${menu.id}"/>
            </td>
            <td><c:out value="${menu.menuName}"/>
            </td>
            <td style="border: none;">
            </td>
            <td style="border: none;">
                <div>
                    <form method="post" action="${pageContext.request.contextPath}/menus/addDishForm">
                        <input type="hidden" name="menuId" value="${menu.id}"/>
                        <input type="hidden" name="menuName" value="${menu.menuName}"/>
                        <input type="submit" value="Add dish"/>
                    </form>
                </div>
            </td>
            <td style="border: none;">
                <div>
                    <form action="${pageContext.request.contextPath}/menus/showMenuDishes">
                        <input type="hidden" name="menuId" value="${menu.id}"/>
                        <input type="hidden" name="menuName" value="${menu.menuName}"/>
                        <input type="submit" value="See dishes"/>
                    </form>
                </div>
            </td>
            <td style="border: none;">
                <div>
                    <form method="post" action="${pageContext.request.contextPath}/menus/deleteMenu">
                        <input type="hidden" name="menuId" value="${menu.id}"/>
                        <input type="submit" value="Delete"/>
                    </form>
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
