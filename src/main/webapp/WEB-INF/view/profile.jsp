<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<link rel="icon" href="${pageContext.servletContext.contextPath}/image/download.jpg" type="image/x-icon">
<title>Profile</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
    <%@include file="styles/w3.css" %>
</style>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
    body, h1, h2, h3, h4, h5, h6 {
        font-family: "Raleway", sans-serif
    }
</style>
<body>

<div class="w3-bar w3-border w3-grey w3-center">
    <a href="${pageContext.request.contextPath}/controller?command=main_page" style=" padding: 15px 100px"
       style="width:20%" class="w3-bar-item w3-button w3-mobile w3-grey">Home</a>
    <a href="${pageContext.request.contextPath}/controller?command=books" style=" padding: 15px 100px" style="width:20%"
       class="w3-bar-item w3-button w3-mobile w3-grey">Catalog</a>
    <c:if test="${sessionScope.user.role.type == null}">
        <a href="${pageContext.request.contextPath}/controller?command=sign_in" style=" padding: 15px 100px"
           style="width:20%" class="w3-bar-item w3-button w3-mobile w3-grey">Sign in</a>
    </c:if>
    <c:if test="${sessionScope.user.role.type eq 'user'}">
        <a href="${pageContext.request.contextPath}/controller?command=profile" style=" padding: 15px 100px"
           style="width:20%" class="w3-bar-item w3-button w3-mobile w3-black">Profile</a>
        <a href="${pageContext.request.contextPath}/controller?command=logout" style=" padding: 15px 100px"
           style="width:20%" class="w3-bar-item w3-button w3-mobile w3-grey">Log out</a>
    </c:if>
</div>

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-collapse w3-white w3-animate-left" style="z-index:3;width:300px;" id="mySidebar"><br>
    <div class="w3-container">
        <a href="#" onclick="w3_close()" class="w3-hide-large w3-right w3-jumbo w3-padding w3-hover-grey"
           title="close menu">
            <i class="fa fa-remove"></i>
        </a>
        <h4><b>PROFILE</b></h4>
    </div>
    <div class="w3-bar-block">
        <p class="w3-bar-item w3-padding"><i
                class="fa fa-user fa-fw w3-margin-right"></i>${sessionScope.user.surname} ${sessionScope.user.firstName}
        </p>
        <p class="w3-bar-item w3-padding"><i class="fa fa-envelope fa-fw w3-margin-right"></i>${sessionScope.user.email}
        </p>
        <a href="${pageContext.request.contextPath}/controller?command=sign_up" onclick="w3_close()"
           class="w3-bar-item w3-button w3-padding"><i class="fa fa-pencil fa-fw w3-margin-right"></i>change
            password</a>
    </div>
</nav>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer"
     title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:300px">

    <!-- Header -->
    <header id="portfolio">
        <span class="w3-button w3-hide-large w3-xxlarge w3-hover-text-grey" onclick="w3_open()"><i
                class="fa fa-bars"></i></span>
        <div class="w3-container">
            <h1><b>Profile</b></h1>
        </div>
    </header>

    <c:if test="${not empty requestScope.books}">
        <c:forEach var="book" items="${books}">
            <div class="w3-row-padding">
                <div class="w3-third w3-container w3-margin-bottom">
                    <img src="${pageContext.request.contextPath}/image/books/${book.title}.png" alt="Book"
                         style="width:100%" class="w3-hover-opacity">
                    <div class="w3-container w3-white">
                        <p><b>${book.title}</b></p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </c:if>
</div>

<script>
    // Script to open and close sidebar
    function w3_open() {
        document.getElementById("mySidebar").style.display = "block";
        document.getElementById("myOverlay").style.display = "block";
    }

    function w3_close() {
        document.getElementById("mySidebar").style.display = "none";
        document.getElementById("myOverlay").style.display = "none";
    }
</script>

</body>
</html>
