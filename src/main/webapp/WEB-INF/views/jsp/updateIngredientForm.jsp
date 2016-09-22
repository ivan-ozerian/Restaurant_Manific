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
<%@include file="ingredientsActions.jsp" %>

<div id="mystyle" class="myform">
    <form name="add" action="${pageContext.request.contextPath}/ingredients/updateSubmit" method="post">
        <h1>Ingredient</h1>
        <p>Update ingredient info</p>
        <label>Quantity<span class="small">Enter the ingredient quantity</span></label>
        <input type="hidden" name="ingredientId" value="${ingredientId}"/>
        <input type="text" name="newQuantity" value="<c:out value='${currentQuantity}'/>"/>
        <button type="submit">Update ingredient</button>
        <div class="spacer"></div>
    </form>
</div>

</body>
</html>
