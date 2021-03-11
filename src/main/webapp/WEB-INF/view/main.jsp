<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="icon" href="${pageContext.servletContext.contextPath}/image/download.jpg" type="image/x-icon">
    <meta charset="UTF-8">
    <title>Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
    <style>
        <%@include file="styles/mainStyles.css" %>
    </style>
    <style>
        <%@include file="styles/w3.css" %>
    </style>
</head>
<body>
<div class="w3-bar w3-border w3-grey w3-center">
    <a href="${pageContext.request.contextPath}/controller?command=main_page" style=" padding: 15px 100px"
       style="width:20%" class="w3-bar-item w3-button w3-mobile w3-black">Home</a>
    <a href="${pageContext.request.contextPath}/controller?command=books" style=" padding: 15px 100px" style="width:20%"
       class="w3-bar-item w3-button w3-mobile w3-grey">Catalog</a>
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

<br>
<br>

<div class="tools">
    <!-- Library Description -->
    <section class="w3-container w3-center" style="margin: auto;width: 50%; max-width:600px">
        <h2 class="w3-wide">Library</h2>
        <p class="w3-opacity"><i>We love books</i></p>
        <p class="w3-justify">Here, the best sound is silence, reading can only be inspirational and exciting,
            regular readers are treated to tea, and selection of literature is a special thoughtful process,
            since the store employees are sure that the immersion in the book should be complete and give
            food for thought.</p>
    </section>
    <div class="moving-mouse-holder">
        <div class="mouse">
            <div class="mouse-button">&nbsp;</div>
        </div>
        <div class="text">SCROLL DOWN <br> TO SEE CONTACTS</div>
    </div>
</div>

<br>
<br>

<!-- Slide Show -->
<section>
    <img class="mySlides" src="${pageContext.request.contextPath}/image/slide1.png"
         style="width:100%">
    <img class="mySlides" src="${pageContext.request.contextPath}/image/slide2.png"
         style="width:100%">
    <img class="mySlides" src="${pageContext.request.contextPath}/image/slide3.png"
         style="width:100%">
</section>

<!-- Footer -->
<footer class="w3-container w3-padding-32 w3-center w3-black w3-xlarge">
    <a href="#" onclick="this.href='https://www.instagram.com/lizka_original_k/?hl=ru'" target="_blank"><i
            class="fa fa-instagram"></i></a>
    <a href="#" onclick="this.href='https://www.linkedin.com/in/elizabeth-krasnovskaya-4505671b6'" target="_blank">
        <i class="fa fa-linkedin"></i>
    </a>
    <a href="#" onclick="this.href='https://vk.com/id265198458'" target="_blank">
        <i class="fa fa-vk"></i>
    </a>
</footer>

<button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>

<script>
    // Automatic Slideshow - change image every 3 seconds
    var myIndex = 0;
    carousel();

    function carousel() {
        var i;
        var x = document.getElementsByClassName("mySlides");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        myIndex++;
        if (myIndex > x.length) {
            myIndex = 1
        }
        x[myIndex - 1].style.display = "block";
        setTimeout(carousel, 4000);
    }
</script>
<script>
    //Get the button
    var mybutton = document.getElementById("myBtn");

    // When the user scrolls down 20px from the top of the document, show the button
    window.onscroll = function () {
        scrollFunction()
    };

    function scrollFunction() {
        if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
            mybutton.style.display = "block";
        } else {
            mybutton.style.display = "none";
        }
    }

    // When the user clicks on the button, scroll to the top of the document
    function topFunction() {
        document.body.scrollTop = 0;
        document.documentElement.scrollTop = 0;
    }
</script>
</body>
</html>