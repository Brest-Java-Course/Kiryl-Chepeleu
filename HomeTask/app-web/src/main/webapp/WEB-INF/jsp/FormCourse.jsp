<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
    <body>
        <form:form action="updateCourse" method="POST" modelAttribute="course">
        <table>
        <tr>
            <td><label path="name">Name:</label></td>
            <td><input type="text" name="name" value="${course.courseName}" /><td>
        </tr>
        <tr>
            <td><label path="lecturerid">lecturerid:</label></td>
            <td><input type="text" name="lecturerid" value="${course.lecturerId}" /><br/><td>
        </tr>
        <tr>
            <td><label path="hours">hours:</label></td>
            <td><input type="text" name="hours" value="${course.hours}" /><br/><td>
        </tr>
        <tr>
            <td><label path="listeners">listeners:</label></td>
            <td><input type="text" name="listeners" value="${course.listeners}" /><br/><td>
        </tr>
        <tr>
            <td><label path="startdate">startdate (format yyyy-mm-dd):</label></td>
            <td><input type="text" name="startdate" value="${course.startdate}" /><br/><td>
        </tr>
        </table>
        <input type="submit" name="Submit" value="Enter">
        </form:form>
</body>
</html>