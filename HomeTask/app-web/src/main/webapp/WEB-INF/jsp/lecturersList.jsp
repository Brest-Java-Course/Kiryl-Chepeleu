<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
        <p>
        <form:form method="get" modelAttribute="lecturers">
        <h1> List of lecturers </h1>
        <ul>
            <table>
                <th>
                    <td>id</td>
                    <td>name</td>
                </th>
                <c:forEach items="${lecturers}" var="ob">
                    <tr>
                        <td>
                            <a href='<spring:url value="/deleteLecturer" ><spring:param name="lecturerid" value="${ob.lecturerId}" /> </spring:url>'>del</a>
                        </td>
                        <td> ${ob.lecturerId} </td>
                        <td> ${ob.lecturerName} </td>
                    </tr>
                </c:forEach>
            </table>
        </ul>
        </form:form>
        <p>
        <form action="submitLecturer" method="POST">
                    <label path="name">Name:</label>
                    <input type="text" name="name"/><br/>
                    <input type="submit" name="Submit" value="Enter">
         </form>
    </body>
</html>