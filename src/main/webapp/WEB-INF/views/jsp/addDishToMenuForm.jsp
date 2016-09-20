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
<%@include file="menusActions.jsp" %>
    <div id="mystyle" class="myform">
        <form name="add" action="/menus/addDishSubmit" method="post">
            <h1><c:out value="${menuName}"/></h1>
            <p>To add new Menu enter following information</p>
            <label>Name<span class="small">Choose the dish</span></label>
            <select name="dishId">
                <c:forEach var="dish" items="${dishes}">
                    <option value="${dish.id}"><c:out value="${dish.dishName}"/></option>
                </c:forEach>
            </select>
            <button type="submit">Add dish to menu</button>
            <input type="hidden" name="menuId" value="${menuId}"/>
            <input type="hidden" name="menuName" value="${menuName}"/>
            <div class="spacer"></div>
        </form>
    </div>
</body>
</html>
