<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
    <head>
        <title><fmt:message key="chooseParser" /></title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <form name="choose" action="controller" method="POST" class="form-choose">
            <input type="hidden" name="command" value="parse" />
            <h3 class="head"><fmt:message key="chooseParser"/></h3>
            <br/>
            <input type="submit" name="parser" value="DOM" class="btn btn-choose btn-primary"/>
            <input type="submit" name="parser" value="SAX" class="btn btn-choose btn-primary"/>
            <input type="submit" name="parser" value="StAX" class="btn btn-choose btn-primary"/>
            <br/>
            <a href="controller" class="return"><fmt:message key="return"/></a>
        </form>
        <br/>
    </body>
</html>