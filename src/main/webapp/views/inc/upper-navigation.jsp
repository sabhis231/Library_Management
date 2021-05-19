<%-- 
    Document   : upper-navigation.jsp
    Created on : 15-May-2021, 19:19:28
    Author     : sabhis231
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<input type="hidden" value="${contextPath}" name="contextPath" id="contextpath"/>
<nav class="navbar navbar-expand-lg navbar-transparent navbar-absolute fixed-top " id="navigation-example">
    <div class="container-fluid">
        <div class="navbar-wrapper">
            <c:if test="${not empty sessionScope.Role}">
                <a class="navbar-brand" data-email="${sessionScope.PrimaryEmail}" href="#">${sessionScope.UserName}</a>
            </c:if>
        </div>
        <c:if test="${sessionScope.Role=='admin'}">
            <div class="justify-content-end">
                <ul class="">
                    <!--<form class="navbar-form ml-auto">-->
                    <div class="input-group no-border">
                        <input type="text" value="" class="form-control user-search" placeholder="Search...">
                        <button type="submit" class="btn btn-default btn-round btn-just-icon">
                            <i class="material-icons">search</i>
                            <div class="ripple-container"></div>
                        </button>
                    </div>
                    <!--</form>-->
                </ul>
            </div>
        </c:if>

        <button class="navbar-toggler" type="button" data-toggle="collapse" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation" data-target="#navigation-example">
            <span class="sr-only">Toggle navigation</span>
            <span class="navbar-toggler-icon icon-bar"></span>
            <span class="navbar-toggler-icon icon-bar"></span>
            <span class="navbar-toggler-icon icon-bar"></span>
        </button>
    </div>
</nav>
