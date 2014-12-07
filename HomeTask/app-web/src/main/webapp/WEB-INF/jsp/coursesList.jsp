<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <body>
        <style type="text/css">
        TABLE {
        width: 300px;
        border-collapse: collapse;
        }
        TD, TH {
        padding: 3px;
        border: 1px solid black;
        }
        TH {
        background: #b0e0e6;
        }
        </style>

        <form:form method="get" modelAttribute="courses">
        <h1> List of courses </h1>
        <ul>
            <table>
                <th>
                    <td>courseId</td>
                    <td>courseName</td>
                    <td>lecturerId</td>
                    <td>hours</td>
                    <td>listeners</td>
                    <td>startdate</td>
                </th>
                <c:forEach items="${courses}" var="ob">
                    <tr>
                        <td></td>
                        <td>${ob.courseId}</td>
                        <td>${ob.courseName}</td>
                        <td>${ob.lecturerId}</td>
                        <td>${ob.hours}</td>
                        <td>${ob.listeners}</td>
                        <td>${ob.startdate}</td>
                    </tr>
                </c:forEach>
            </table>
        </ul>
        </form:form>
    </body>
</html>