<%-- 
    Document   : header
    Created on : Mar 27, 2016, 7:48:53 PM
    Author     : 1041737
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<input type="hidden" value="${contextPath}" name="contextPath" id="contextpath"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Library Management</title>
        <%@include file="include.jsp" %>
    </head>
    <body draggable="false"
          ondragover="" >


