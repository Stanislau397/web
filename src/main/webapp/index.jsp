<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fml" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="/property/text" />
<html lang="${language}">
<head>
    <title>Main Page</title>
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
    <header>
        <form>
            <select class="select" id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}><fmt:message key="label.en"/> </option>
                <option value="ru" ${language == 'ru' ? 'selected' : ''}><fmt:message key="label.ru"/> </option>
            </select>
        </form>
        <ul class="nav-area">
            <li><a href="jsp/upload_file.jsp"><fmt:message key="label.file"/></a></li>
            <li><a href="index.jsp"><fmt:message key="text.label.main"/></a></li>
            <li><a href="jsp/register_page.jsp"><fmt:message key="login.label.register"/></a></li>
            <li><a href="jsp/login_page.jsp"><fmt:message key="login.label.login"/></a></li>
        </ul>
    </header>
</body>
</html>
