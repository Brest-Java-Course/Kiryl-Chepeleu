<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
    <body>
        <a href=".">Home</a><br>
        <form action="updateLecturer" method="POST" modelAttribute="lecturer">
        <table>
        <tr>
            <td><label path="id">id:</label></td>
            <td><input type="text" name="id" value="${lecturer.lecturerId}" /><td>
        </tr>
        <tr>
            <td><label path="name">Name:</label></td>
            <td><input type="text" name="name" value="${lecturer.lecturerName}" /><td>
        </tr>
        </table>
        <input type="submit" name="Submit">
        </form>
</body>
</html>