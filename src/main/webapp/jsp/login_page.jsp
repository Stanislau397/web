<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fml" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="/property/text" />
<html lang="${language}">
<head>
    <title>Authorization</title>
    <link rel="stylesheet" href="../css/register.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-2.1.3.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/jquery.validate.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/additional-methods.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/validation.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
</head>
<body>
    <header>
        <ul class="nav-area">
            <li><a href="../index.jsp"><fmt:message key="text.label.main"/></a></li>
        </ul>
    </header>
<div class="form-box">
<div class="login-box">
    <h1><fmt:message key="login.label.authorize"/></h1>
    <form id="login" class="input-group" action="${pageContext.request.contextPath}/login_servlet" method="post">
        <div class="text-box">
            <i class="fa fa-user" aria-hidden="true"></i>
            <input type="text" name="username" placeholder="<fmt:message key="login.label.username"/>"/>
        </div>

        <div class="text-box" >
            <i class="fa fa-lock" aria-hidden="true"></i>
            <input type="password" name="password" placeholder="<fmt:message key="login.label.password"/>"/>
        </div>

        <input class="btn" type="submit" value="<fmt:message key="login.label.login"/>"/>
    </form>
</div>
</div>
</body>
</html>
