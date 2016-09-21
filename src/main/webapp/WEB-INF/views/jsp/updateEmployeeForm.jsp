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
<%@include file="ingredientsActions.jsp" %>

<div id="mystyle" class="myform">
    <form name="add" action="/employees/updateSubmit" method="post">
        <h1>${employee.name} ${employee.surname}</h1>
        <p>Update employee info</p>
        <label>Phone number<span class="small">Enter phone number</span></label>
        <input type="text" name="phone" value="<c:out value='${employee.phoneNumber}'/>"/>
        <label>Salary<span class="small">Update salary</span></label>
        <input type="text" name="salary" value="<c:out value='${employee.salary}'/>"/>
        <label>Position<span class="small">Update position</span></label>
        <select name="position">
            <c:forEach var="position" items="${positions}">
                <option value="${position.name()}">${position.name()}</option>
            </c:forEach>
        </select>
        <input type="hidden" name="employeeId" value="${employeeId}"/>
        <button type="submit">Update employee</button>
        <div class="spacer"></div>
    </form>
</div>

</body>
</html>
