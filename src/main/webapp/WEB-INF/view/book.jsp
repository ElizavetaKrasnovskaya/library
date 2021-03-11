<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="icon" href="${pageContext.servletContext.contextPath}/image/download.jpg" type="image/x-icon">
    <c:if test="${not empty requestScope.book}">
        <title>${book.title}</title>
    </c:if>
    <style>
        <%@include file="styles/bookStyles.css" %>
    </style>
    <style>
        <%@include file="styles/w3.css" %>
    </style>
    <%--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">--%>
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

<div class="w3-main">

    <c:if test="${not empty requestScope.book}">
        <div class="w3-row-padding w3-padding-32">
            <img src="${pageContext.servletContext.contextPath}/image/books/${requestScope.book.title}.png"
                 alt="Book" align="left" style="width:25%; padding-right: 20px">
            <h4 style="text-align: center"><b>${book.title} by ${book.author.firstName} ${book.author.surname}</b></h4>
            <p align="justify" style="color: black; font-size: medium; padding-right: 20px">${book.specification}</p>
            <div class="w3-bar">
                <br>
                <br>
                <a href="${pageContext.request.contextPath}/controller?command=books"
                   class="w3-button w3-black w3-bar-item w3-margin-right"
                   style=" padding: 14px 100px">Back</a>
                <c:choose>
                    <c:when test="${sessionScope.user.role.type eq 'user'}">
                        <form method="post"
                              action="${pageContext.request.contextPath}/controller?command=basket&id=${book.id}">
                            <input type="submit" name="" value="Add to basket" class="w3-button w3-black w3-bar-item"
                                   style="padding: 14px 100px">
                                <%--                            <a href="${pageContext.request.contextPath}/controller?command=profile"--%>
                                <%--                               class="w3-button w3-black w3-bar-item"--%>
                                <%--                               style=" padding: 14px 100px">Add to basket</a>--%>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/controller?command=sign_in"
                           class="w3-button w3-black w3-bar-item"
                           style=" padding: 14px 100px">Add to basket</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </c:if>
</div>
</body>
</html>
