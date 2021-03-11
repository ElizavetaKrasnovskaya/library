<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" href="${pageContext.servletContext.contextPath}/image/download.jpg" type="image/x-icon">
    <meta charset="utf-8">
    <title>Sign in</title>
    <style>
        <%@include file="styles/w3.css" %>
    </style>
    <style>
        <%@include file="styles/signinStyles.css" %>
    </style>
    <style>
        <%@include file="styles/w3.css" %>
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
    <script type="text/javascript" src="//code.jquery.com/jquery-2.1.3.js"></script>
    <style>
        body {
            margin: 0;
            padding: 0;
            background-image: url(${pageContext.servletContext.contextPath}/image/loginBack.jpg);
            height: 100%;
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
            font-family: sans-serif;
        }
    </style>
</head>
<body>

<div class="w3-bar w3-border w3-grey w3-center">
    <a href="${pageContext.request.contextPath}/controller?command=main_page" style=" padding: 15px 100px"
       style="width:20%" class="w3-bar-item w3-button w3-mobile w3-grey">Home</a>
    <a href="${pageContext.request.contextPath}/controller?command=books" style=" padding: 15px 100px" style="width:20%"
       class="w3-bar-item w3-button w3-mobile w3-grey">Catalog</a>
    <c:if test="${sessionScope.user.role.type == null}">
        <a href="${pageContext.request.contextPath}/controller?command=sign_in" style=" padding: 15px 100px"
           style="width:20%" class="w3-bar-item w3-button w3-mobile w3-black">Sign in</a>
    </c:if>
    <c:if test="${sessionScope.user.role.type eq 'user'}">
        <a href="${pageContext.request.contextPath}/controller?command=profile" style=" padding: 15px 100px"
           style="width:20%" class="w3-bar-item w3-button w3-mobile w3-grey">Profile</a>
        <a href="${pageContext.request.contextPath}/controller?command=logout" style=" padding: 15px 100px"
           style="width:20%" class="w3-bar-item w3-button w3-mobile w3-grey">Log out</a>
    </c:if>
</div>

<div class="loginbox">
    <img src="${pageContext.servletContext.contextPath}/image/avatar.png" class="avatar">
    <h1>Login here</h1>
    <form method="post" action="${pageContext.request.contextPath}/controller?command=login">
        <p>Login</p>
        <input type="text" name="login" placeholder="Enter login">
        <p>Password</p>
        <input type="password" name="password" placeholder="Enter password">
        <input type="submit" name="" value="Login">
        <br>
        <a href="${pageContext.request.contextPath}/controller?command=sign_up">Don't have an account?</a>
    </form>
</div>
</div>
</body>
</html>

