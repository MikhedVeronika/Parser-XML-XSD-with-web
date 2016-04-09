<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<html>
    <head>
        <title><fmt:message key="xmlParsing"/></title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
        <h3 class="head">${res}</h3>

        <h4 class="flower-type"><fmt:message key="one"/></h4>
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th rowspan="2"><fmt:message key="idF"/></th>
                    <th rowspan="2"><fmt:message key="nameF"/></th>
                    <th rowspan="2"><fmt:message key="soilF"/></th>
                    <th rowspan="2"><fmt:message key="originF"/></th>
                    <th colspan="3"><fmt:message key="visualParametersF"/></th>
                    <th colspan="3"><fmt:message key="growingTipsF"/></th>
                    <th rowspan="2"><fmt:message key="multiplyingF"/></th>
                    <th rowspan="2"><fmt:message key="floweringDaysCountF"/></th>
                </tr>
                <tr>
                    <th><fmt:message key="leafColorF"/></th>
                    <th><fmt:message key="flowerColorF"/></th>
                    <th><fmt:message key="averageSizeCmF"/></th>
                    <th><fmt:message key="requiredTemperatureF"/></th>
                    <th><fmt:message key="lighteningF"/></th>
                    <th><fmt:message key="weekWateringMlF"/></th>
                </tr>
            </thead>
            <c:forEach var="flower" items="${oneYearFlowers}">
                <tr>
                    <td>${flower.getId()}</td>
                    <td>${flower.getName()}</td>
                    <td>${flower.getSoil()}</td>
                    <td>${flower.getOrigin()}</td>
                    <td>${flower.getVisualParameters().getLeafColor()}</td>
                    <td>${flower.getVisualParameters().getFlowerColor()}</td>
                    <td>${flower.getVisualParameters().getAverageSizeCm()}</td>
                    <td>${flower.getGrowingTips().getRequiredTemperature()}</td>
                    <td>${flower.getGrowingTips().getLightening()}</td>
                    <td>${flower.getGrowingTips().getWeekWateringMl()}</td>
                    <td>${flower.getMultiplying()}</td>
                    <td>${flower.getFloweringDaysCount()}</td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <br/>

        <h4 class="flower-type"><fmt:message key="multi"/></h4>
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th rowspan="2"><fmt:message key="idF"/></th>
                    <th rowspan="2"><fmt:message key="nameF"/></th>
                    <th rowspan="2"><fmt:message key="soilF"/></th>
                    <th rowspan="2"><fmt:message key="originF"/></th>
                    <th colspan="3"><fmt:message key="visualParametersF"/></th>
                    <th colspan="3"><fmt:message key="growingTipsF"/></th>
                    <th rowspan="2"><fmt:message key="multiplyingF"/></th>
                    <th rowspan="2"><fmt:message key="ageYearsF"/></th>
                </tr>
                <tr>
                    <th><fmt:message key="leafColorF"/></th>
                    <th><fmt:message key="flowerColorF"/></th>
                    <th><fmt:message key="averageSizeCmF"/></th>
                    <th><fmt:message key="requiredTemperatureF"/></th>
                    <th><fmt:message key="lighteningF"/></th>
                    <th><fmt:message key="weekWateringMlF"/></th>
                </tr>
            </thead>
            <c:forEach var="flower" items="${multiYearFlowers}">
                <tr>
                    <td>${flower.getId()}</td>
                    <td>${flower.getName()}</td>
                    <td>${flower.getSoil()}</td>
                    <td>${flower.getOrigin()}</td>
                    <td>${flower.getVisualParameters().getLeafColor()}</td>
                    <td>${flower.getVisualParameters().getFlowerColor()}</td>
                    <td>${flower.getVisualParameters().getAverageSizeCm()}</td>
                    <td>${flower.getGrowingTips().getRequiredTemperature()}</td>
                    <td>${flower.getGrowingTips().getLightening()}</td>
                    <td>${flower.getGrowingTips().getWeekWateringMl()}</td>
                    <td>${flower.getMultiplying()}</td>
                    <td>${flower.getAgeYears()}</td>
                </tr>
            </c:forEach>
        </table>

        <div class="back">
            <button type="button" name="back" onclick="history.back()" class="btn btn-info back-btn"><fmt:message key="back"/></button>
        </div>
    </body>

</html>