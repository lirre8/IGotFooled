<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/spring-social/social/tags" prefix="social"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<nav id="header" class="col-md-12 navbar navbar-default navbar-fixed-top" role="navigation">
    <sec:authorize var="loggedIn" access="isAuthenticated()" />
    <a class="navbar-brand" href="/">
        <strong>Course Portal</strong><small> All courses in one place</small>
    </a>
    <div id="header-search-div" class="col-md-4 input-group input-group-sm navbar-text">
        <input type="text" id="header-search-input" placeholder="Search..." autocomplete="off" class="form-control search-query search-course">
        <span class="input-group-btn">
            <button id="search-course" type="button" class="btn btn-default">Search</button>
        </span> <input type="hidden" id="hiddenCourseId" name="hiddenCourseId">
    </div>
    <c:choose>
        <c:when test="${loggedIn}">
            <strong class="navbar-text pull-right"><a class="navbar-link" href="/signout">Logout</a></strong>
            <p class="navbar-text pull-right">Signed in as <strong><a href="/users/<sec:authentication property="principal.id" />" class="navbar-link"><sec:authentication property="principal.firstName" /></a></strong></p>
        </c:when>
        <c:otherwise>
            <strong class="navbar-text pull-right"><a class="navbar-link" href="/signin">Sign in</a></strong>
        </c:otherwise>
    </c:choose>

</nav>
