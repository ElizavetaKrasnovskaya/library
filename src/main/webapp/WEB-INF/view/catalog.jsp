<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="icon" href="${pageContext.servletContext.contextPath}/image/download.jpg" type="image/x-icon">
    <title>Catalog</title>
    <%--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">--%>

    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700' rel='stylesheet' type='text/css'>
    <style>
        <%@include file="styles/w3.css" %>
    </style>
    <style>
        <%@include file="styles/reset.css" %>
    </style>
    <style>
        <%@include file="styles/style.css" %>
    </style>
    <script>
        <%@include file="js/modernizr.js" %>
    </script>
</head>
<body>

<div class="w3-bar w3-border w3-grey w3-center">
    <a href="${pageContext.request.contextPath}/controller?command=main_page" style=" padding: 15px 100px"
       style="width:20%" class="w3-bar-item w3-button w3-mobile w3-grey">Home</a>
    <a href="${pageContext.request.contextPath}/controller?command=books" style=" padding: 15px 100px" style="width:20%"
       class="w3-bar-item w3-button w3-mobile w3-black">Catalog</a>
    <c:if test="${sessionScope.user.role.type == null}">
        <a href="${pageContext.request.contextPath}/controller?command=sign_in" style=" padding: 15px 100px"
           style="width:20%" class="w3-bar-item w3-button w3-mobile w3-grey">Sign in</a>
    </c:if>
    <c:if test="${sessionScope.user.role.type eq 'user'}">
        <a href="${pageContext.request.contextPath}/controller?command=profile" style=" padding: 15px 100px"
           style="width:20%" class="w3-bar-item w3-button w3-mobile w3-grey">Profile</a>
        <a href="${pageContext.request.contextPath}/controller?command=logout" style=" padding: 15px 100px"
           style="width:20%" class="w3-bar-item w3-button w3-mobile w3-grey">Log out</a>
    </c:if>
</div>

<main class="cd-main-content">
    <section class="cd-gallery">
        <ul>
            <c:if test="${not empty requestScope.books}">
                <c:forEach var="book" items="${books}">
                    <li class="mix ${book.title} ${book.genre}">
                        <a href="${pageContext.request.contextPath}/controller?command=book&id=${book.id}">
                            <img src="${pageContext.request.contextPath}/image/books/${book.title}.png">
                        </a>
                        <a class="text" class="w3-button"
                           href="${pageContext.request.contextPath}/controller?command=book&id=${book.id}">
                            <figcaption>${book.title}</figcaption>
                        </a>
                    </li>
                </c:forEach>
            </c:if>
        </ul>
        <div class="cd-fail-message">No results found</div>
    </section>

    <div class="cd-filter">
        <form>
            <div class="cd-filter-block">

                <div class="cd-filter-content">
                    <input type="search" placeholder="Enter title...">
                </div>
            </div>

            <div class="cd-filter-block">
                <h4>Genres</h4>

                <ul class="cd-filter-content cd-filters list">
                    <li>
                        <input class="filter" data-filter=".novel" type="checkbox" id="checkbox1">
                        <label class="checkbox-label" for="checkbox1">Novel</label>
                    </li>

                    <li>
                        <input class="filter" data-filter=".fantasy" type="checkbox" id="checkbox2">
                        <label class="checkbox-label" for="checkbox2">Fantasy</label>
                    </li>

                    <li>
                        <input class="filter" data-filter=".tragedy" type="checkbox" id="checkbox3">
                        <label class="checkbox-label" for="checkbox3">Tragedy</label>
                    </li>
                </ul>
            </div>
        </form>
        <a href="#0" class="cd-close">Close</a>
    </div>
    <a href="#0" class="cd-filter-trigger">Filters</a>
</main>
<script>
    <%@include file="js/jquery-2.1.1.js" %>
</script>
<script>
    <%@include file="js/jquery.mixitup.min.js" %>
</script>
<script>
    <%@include file="js/main.js" %>
</script>
</body>
</html>
