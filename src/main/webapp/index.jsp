<%@ page pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html lang="${language}">
    <head>
        <title><fmt:message key="authenticationHeader"/></title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>

    <form class="form-group lang-select">
        <label for="language"><fmt:message key="language"/></label>
        <select id="language" name="language" onchange="submit()" class="form-control">
            <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>
            <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        </select>
    </form>

    <form name="loginForm" method="POST" action="controller" class="form-reg">
        <h3 class="head"><fmt:message key="authenticationHeader"/></h3>
        <input type="hidden" name="command" value="login" />
        <div class="form-group">
            <label for="login"><fmt:message key="loginLabel" /></label>
            <input type="text" id="login" name="login" class="form-control">
        </div>
        <div class="form-group">
            <label for="password"><fmt:message key="passwordLabel" /></label>
            <input type="password" id="password" name="password" class="form-control">
        </div>
        <button type="submit" class="btn btn-primary"> <fmt:message key="enter"/></button>
    </form>

    </body>
</html>