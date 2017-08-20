<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <s:url value="inputvideo" var="video"/>
        <s:url value="miner" var="miner"/>
        <s:url value="loginCodeAny" var="loginCodeAny"/>
         <s:url value="loginCodenvy" var="loginCodenvy"/>
        <a href="${video}"> buff view</a>
        <br/>
        <br/>
        <br/>
        <br/>
        <a href="${miner}"> miner</a>
         <br/>
        <br/>
        <br/>
        <br/>
        <a href="${loginCodeAny}"> loginCodeAny</a>
         <br/>
        <br/>
        <br/>
        <br/>
        <a href="${loginCodenvy}"> loginCodenvy</a>
        
    </body>
</html>
