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
                        <td></td>
                        <td> ${ob.lecturerId} </td>
                        <td> ${ob.lecturerName} </td>
                    </tr>
                </c:forEach>
            </table>
        </ul>
        </form:form>
    </body>
</html>