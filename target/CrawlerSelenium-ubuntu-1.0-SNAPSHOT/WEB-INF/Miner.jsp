<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <s:url value="start" var="start"/>
        <h1>login</h1>
        <form:form action="${start}" method="get" >
            <table border="1" width="1" cellspacing="1">

                <tbody>
                    <tr>
                        <td>index</td>
                        <td>
                            <input name="index" placeholder="index" type="number"/>
                        </td>
                    </tr>
                    <tr>

                        <td colspan="2">  <input type="submit" value="submit"/></td>
                    </tr>
                </tbody>
            </table>
        </form:form>

    </body>
</html>
