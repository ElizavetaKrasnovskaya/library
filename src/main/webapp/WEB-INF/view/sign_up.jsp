<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Sign up</title>
    <style>
        <%@include file="styles/signupStyles.css" %>
    </style>
    <style>
        <%@include file="styles/w3.css" %>
    </style>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
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
    <script>
        function togglePW() {
            document.querySelector('.eye').classList.toggle('slash');
            let password = document.querySelector('[name=password]');
            if (password.getAttribute('type') === 'password') {
                password.setAttribute('type', 'text');
            } else {
                password.setAttribute('type', 'password');
            }
        }
    </script>
</head>
<body>
<div class="w3-bar w3-border w3-grey w3-center">
    <a href="${pageContext.request.contextPath}/controller?command=main_page" style=" padding: 15px 100px"
       style="width:20%" class="w3-bar-item w3-button w3-mobile w3-grey">Home</a>
    <a href="${pageContext.request.contextPath}/controller?command=books" style=" padding: 15px 100px" style="width:20%"
       class="w3-bar-item w3-button w3-mobile w3-grey">Catalog</a>
    <a href="${pageContext.request.contextPath}/controller?command=sign_in" style=" padding: 15px 100px"
       style="width:20%" class="w3-bar-item w3-button w3-mobile w3-black">Sign in</a>

</div>

<div class="loginbox">
    <img src="${pageContext.servletContext.contextPath}/image/avatar.png" class="avatar">
    <h1>Registration</h1>
    <form method="post" action="${pageContext.request.contextPath}/controller?command=new_user">
        <input type="text" name="surname" placeholder="Surname">
        <input type="text" name="firstName" placeholder="First name">
        <input type="email" name="email" placeholder="Email">
        <input type="text" name="login" placeholder="Login">
        <div>
            <input type="password" name="password" placeholder="Password">
            <div class="eye slash" onclick="togglePW()">
                <div></div>
                <div></div>
            </div>
        </div>
        <input type="submit" name="" value="Register">
    </form>
</div>


</body>
</html>

