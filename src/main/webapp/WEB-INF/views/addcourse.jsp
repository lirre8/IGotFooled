<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

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
    	<h3>Add a new course</h3>
        <form:form role="form" modelAttribute="addCourseForm" method="POST" action="addcourse">
            <s:bind path="*">
                <c:choose>
                    <c:when test="${status.error}">
                        <div class="text-danger">
                            <strong>Unable to add the course. Please fix the errors below and resubmit.</strong>
                        </div>
                        <br>
                    </c:when>
                </c:choose>
            </s:bind>
            <div class="form-group">
                <label for="codeInput">Course code</label>
                <form:errors path="courseCode" cssClass="text-danger" />
                <form:input id="codeInput" name="courseCode" path="courseCode" class="form-control" />
            </div>
            <div class="form-group">
                <label for="nameInput">Course name</label>
                <form:errors path="courseName" cssClass="text-danger" />
                <form:input type="text" id="nameInput" path="courseName" class="form-control" />
            </div>
            <div class="form-group">
                <label for="university">University</label>
                <form:errors path="university" cssClass="text-danger" />
                <form:input type="text" id="university" path="university" class="form-control" />
            </div>
            <button type="submit" class="btn btn-default">Add course</button>
        </form:form>
    </div>
</body>
</html>