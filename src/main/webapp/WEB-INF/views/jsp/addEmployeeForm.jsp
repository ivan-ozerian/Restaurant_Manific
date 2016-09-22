<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <title>New Employee</title>
</head>
<body>
<%@include file="employeesActions.jsp" %>
<div id="mystyle" class="myform">
    <form:form action="${pageContext.request.contextPath}/employees/addSubmit" method="post" commandName="employee">
        <h1>Employee</h1>
        <p>To add new Employee enter following information</p>
        <form:label path="name">Name<span class="small">Enter name</span></form:label>
        <form:input path="name"/>
        <form:label path="surname">Surname<span class="small">Enter surname</span></form:label>
        <form:input path="surname"/>
        <form:label path="birthDate">Date of Birth<span class="small">Enter the date of Birth</span></form:label>
        <form:input path="birthDate"/>
        <form:label path="phoneNumber">Phone<span class="small">Enter phone number</span></form:label>
        <form:input path="phoneNumber"/>
        <form:label path="salary">Salary<span class="small">Enter salary</span></form:label>
        <form:input path="salary"/>
        <form:label path="position">Position<span class="small">Enter position</span></form:label>
        <form:select path="position" items="${positions}"/>
        <form:button type="submit">Add New Employee</form:button>
        <div class="spacer"></div>
    </form:form>
</div>
</body>
</html>