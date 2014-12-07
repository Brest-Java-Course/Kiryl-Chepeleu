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

        <p>
                <form action="submitCourse" method="POST">
                <table>
                            <tr><td><label path="name">Name:</label></td><td><input type="text" name="name"/><br/><td></tr>
                            <tr><td><label path="lecturerid">lecturerid:</label></td><td><input type="text" name="lecturerid"/><br/><td></tr>
                            <tr><td><label path="hours">hours:</label></td><td><input type="text" name="hours"/><br/><td></tr>
                            <tr><td><label path="listeners">listeners:</label></td><td><input type="text" name="listeners"/><br/><td></tr>
                            <tr><td><label path="startdate">startdate (format yyyy-mm-dd):</label></td><td><input type="text" name="startdate"/><br/><td></tr>
                </table>
                            <input type="submit" name="Submit" value="Enter">


                </form>
    </body>
</html>