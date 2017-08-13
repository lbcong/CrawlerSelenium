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
        <s:url value="CodeAny" var="CodeAny"/>
        <h1>login</h1>
        <form:form action="${CodeAny}" method="get" >
            <table border="1" width="1" cellspacing="1">

                <tbody>
                    <tr>
                        <td>username</td>
                        <td>
                            <input name="user" placeholder="user" type="text"/>
                        </td>
                    </tr>
                    <tr>
                        <td>pass</td>
                        <td>
                            <input name="pass" placeholder="pass" type="password"/>
                        </td>
                    </tr>
                    <tr>
                        <td>id</td>
                        <td>
                            <input name="id" placeholder="pass" type="text"/>
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
