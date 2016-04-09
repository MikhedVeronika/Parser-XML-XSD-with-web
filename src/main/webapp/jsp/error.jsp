<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
    <head>
        <title><fmt:message key="error" /></title>
    </head>
    <body>
        <h3><fmt:message key="error"/></h3>
        <jsp:expression>
            (request.getAttribute("errorMessage") != null) ? (String) request.getAttribute("errorMessage") : "unknown error"
        </jsp:expression>
        <br/>
        <a href="controller"><fmt:message key="return"/></a>
    </body>
</html>
