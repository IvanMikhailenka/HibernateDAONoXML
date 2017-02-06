<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 10.11.2016
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Table</title>
</head>
<body>
<h1>Table</h1>

<form action="/mainServlet" method="post">


    <label for="startDate">Start date</label>
    <input type="text" name="startDate" id="startDate">
    <br><br>
    <label for="endDate">End date</label>
    <input type="text" name="endDate" id="endDate">
    <br><br>

    <%--заполняем выплывающий списк performer--%>
    <label for="performer">Performer</label>
    <select name="Performer" id="Performer">
        <option>All performers</option>
        <c:forEach var="performer" items="${performerList}">
            <option>
                <c:out value="${performer}"/>
            </option>
        </c:forEach>
    </select>
    <br><br>

    <%--заполняем combo box "Time Period"
    и связываем его c полями startDate и endDate c помощью скрипта--%>
    <label for="selectPeriod">Time period</label>
    <select name="Time period" id="selectPeriod" onchange="myFunction()">
        <option></option>
        <option value="Last Qtr">Last Qtr</option>
        <option value="Last Month">Last Month</option>
        <option value="Last Calendar Year">Last Calendar Year</option>
        <option value="Current Year to Date">Current Year to Date</option>
        <option value="Current Qtr to Date">Current Qtr to Date</option>
        <option value="Current Month to Date">Current Month to Date</option>
    </select>
    <br></bt>

    <input type="submit" name="getLogin" value="Submit">


    <table>
        <tr>
            <th>${StartDate}</th>
            <th>${EndDate}</th>
            <th>${Performer}</th>
            <th>${Activity}</th>
        </tr>
        <c:forEach var="report" items="${reportsList}">
            <c:set var="startDate" value="${report.getStartDateInString()}"/>
            <c:set var="endDate" value="${report.getEndDateInString()}"/>
            <c:set var="performer" value="${report.getPerformer()}"/>
            <c:set var="activity" value="${report.getActivity()}"/>
            <tr>
                <td> <c:out value="${startDate}"/></td>
                <td> <c:out value="${endDate}"/></td>
                <td> <c:out value="${performer}"/></td>
                <td> <c:out value="${activity}"/></td>
            </tr>
        </c:forEach>
    </table>


</form>
</body>
</html>

<%--данный скрипт заполняет поля startDate и endDate
в зависимости от выбранного занчения а так же текущей даты--%>
<script>
    function myFunction() {
        var selectedPeriod = document.getElementById("selectPeriod").value;
        if (selectedPeriod == "Last Calendar Year") {
            document.getElementById("startDate").setAttribute("value", "${LastCalendarStartDate}");
            document.getElementById("endDate").setAttribute("value", "${LastCalendarEndDate}");
        }
        if (selectedPeriod == "Last Qtr") {
            document.getElementById("startDate").setAttribute("value", "${LastQtrStartDate}");
            document.getElementById("endDate").setAttribute("value", "${LastQtrEndDate}");
        }
        if (selectedPeriod == "Last Month") {
            document.getElementById("startDate").setAttribute("value", "${LastMonthStart}");
            document.getElementById("endDate").setAttribute("value", "${LastMonthEnd}");
        }
        if (selectedPeriod == "Current Year to Date") {
            document.getElementById("startDate").setAttribute("value", "${CurrentYear}");
            document.getElementById("endDate").setAttribute("value", "${CurrentDate}");
        }
        if (selectedPeriod == "Current Qtr to Date") {
            document.getElementById("startDate").setAttribute("value", "${CurrentQtr}");
            document.getElementById("endDate").setAttribute("value", "${CurrentDate}");
        }
        if (selectedPeriod == "Current Month to Date") {
            document.getElementById("startDate").setAttribute("value", "${CurrentMonth}");
            document.getElementById("endDate").setAttribute("value", "${CurrentDate}");
        }

    }
</script>