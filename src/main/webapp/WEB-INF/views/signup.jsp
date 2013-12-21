<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
    <title>Course Portal</title>
    <meta name="google-site-verification" content="RcIg0lj1O-SzAsp2WaoI71z9R4308S5woMHeyxyPlEQ" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="<c:url value="/resources/favicon.ico?v=4" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-responsive.css" />"></link>
    <link rel="stylesheet" href="<c:url value="/resources/font-awesome-4.0.3/css/font-awesome.css" />"></link>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />"></link>
    <link rel="stylesheet" href="<c:url value="/resources/css/slider.css" />"></link>
    <link rel="stylesheet" href="<c:url value="/resources/css/zocial.css" />"></link>
    <link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />"></link>
    <script src="<c:url value="/resources/js/jquery-2.0.3.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <script src="<c:url value="/resources/js/hogan-2.0.0.js" />"></script>
    <script src="<c:url value="/resources/js/typeahead.min.js" />"></script>
    <script src="<c:url value="/resources/js/scripts.js" />"></script>
    <script src="<c:url value="/resources/js/Chart.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap-slider.js" />"></script>
</head>
<body>
    <c:import url="header.jsp" />
    <div class="col-md-6 col-md-offset-3 well">
        <h3>Sign up</h3>
        <c:if test="${not empty message}">
            <div class="${message.type.cssClass}">${message.text}</div>
        </c:if>

        <c:url value="/signup" var="signupUrl" />
        <form:form class="form" id="signup" action="${signupUrl}" method="post" modelAttribute="signupForm">
            <div class="formInfo">
                <s:bind path="*">
                    <c:choose>
                        <c:when test="${status.error}">
                            <div class="text-danger">Unable to sign up. Please fix the errors below and resubmit.</div>
                        </c:when>
                    </c:choose>
                </s:bind>
            </div>
            <div class="form-group">
                <form:label path="username">Email * <form:errors path="username" cssClass="text-danger" /> </form:label>
                <form:input class="form-control" path="username" />
            </div>
            <div class="form-group">
                <form:label path="firstName">First Name * <form:errors path="firstName" cssClass="text-danger" /> </form:label>
                <form:input class="form-control" path="firstName" />
            </div>
            <div class="form-group">
                <form:label for="last-name" path="lastName">Last Name * <form:errors path="lastName" cssClass="text-danger" /> </form:label>
                <form:input class="form-control" id="last-name" path="lastName" />
            </div>
            <div class="form-group">
                <form:label path="university.name">University * <form:errors path="university.name" cssClass="text-danger" /> </form:label>
                <form:input class="form-control" path="university.name" />
            </div>
            <div class="form-group">
                <form:label path="password">Password * (at least 6 characters) <form:errors path="password" cssClass="text-danger" />
                </form:label>
                <form:password class="form-control" path="password" />
            </div>
            <div class="form-group">
                <form:label path="repeatedPassword">Repeat password * <form:errors element="repeatedPassword" path="repeatedPassword" cssClass="text-danger" />
                </form:label>
                <form:password class="form-control" path="repeatedPassword" />
            </div>
            <div class="form-group">
                <p>* Required</p>
                <button type="submit" class="btn btn-default">Sign Up</button>
            </div>
        </form:form>
    </div>
</body>