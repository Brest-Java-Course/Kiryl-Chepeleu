<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
    <body>

    <form action="coursesbetweendates" method="GET">
        <table>
            <tr><td><label path="firstdate">startdate (format yyyy-mm-dd):</label></td><td><input type="text" name="firstdate"/><br/><td></tr>
            <tr><td><label path="seconddate">startdate (format yyyy-mm-dd):</label></td><td><input type="text" name="seconddate"/><br/><td></tr>
        </table>
        <input type="submit" name="Submit" value="Enter">
    </form>
    </body>
</html>