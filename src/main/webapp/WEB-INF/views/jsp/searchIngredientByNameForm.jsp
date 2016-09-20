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
    <form action="/ingredients/findByName" method="post">
        <h1>Ingredient</h1>
        <p>To search an Ingredient enter it's name</p>
        <label>Name<span class="small">Enter name</span></label>
        <input type="text" name="ingredientName"/>
        <button type="submit">Search</button>
        <div class="spacer"></div>
    </form>
</div>
</body>
</html>
