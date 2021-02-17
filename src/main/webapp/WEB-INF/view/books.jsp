<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Books</title>
</head>
<body>
<h2>Books list</h2>
<c:if test="${not empty requestScope.books}">
    <h2>Columns</h2>
    <ul>
        <c:forEach var="book" items="${books}">
            <li>${book.title} published on ${book.yearOfPublishing}</li>
        </c:forEach>
    </ul>
</c:if>
</body>
</html>
