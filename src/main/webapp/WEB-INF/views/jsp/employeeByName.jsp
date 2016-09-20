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
<%@include file="employeesActions.jsp" %>

<div style="margin-left: 350px">
    <table>
        <thead>
        <tr>
            <th>Emp ID</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Date of Birth</th>
            <th>Phone number</th>
            <th>Salary</th>
            <th>Position</th>
        </tr>
        </thead>

        <c:forEach var="employee" items="${foundEmployees}">
            <tr>
                <td><c:out value="${employee.id}"/>
                </td>
                <td><c:out value="${employee.name}"/>
                </td>
                <td><c:out value="${employee.surname}"/>
                </td>
                <td><fmt:formatDate type="date" value="${employee.birthDate}"/>
                </td>
                <td><c:out value="${employee.phoneNumber}"/>
                </td>
                <td><c:out value="${employee.salary}"/>
                </td>
                <td><c:out value="${employee.position}"/>
                </td>
                <td style="border: none;">
                </td>
                <td style="border: none;">
                    <div>
                        <form method="post" action="/employees/delete">
                            <input type="hidden" name="empId" value="<c:out value="${employee.id}"/>"/>
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
