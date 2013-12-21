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
<script>
    bookCounter = 0;
</script>
</head>
<body>
    <c:import url="header.jsp" />
    <div class="col-md-6 col-md-offset-3 well">
        <h3>Edit course</h3>
        <c:url value="edit" var="editcourseUrl" />
        <form:form class="form" action="${editcourseUrl}" method="post" modelAttribute="editCourseForm">
            <s:bind path="*">
                <c:choose>
                    <c:when test="${status.error}">
                        <div class="text-danger">
                            <strong>Unable to change profile. Please fix the errors below and resubmit.</strong>
                        </div>
                        <br>
                    </c:when>
                </c:choose>
            </s:bind>
            <div class="form-group">
                <label for="courseName">Course name</label>
                <form:errors path="courseName" cssClass="text-danger" />
                <form:input class="form-control" path="courseName" />
            </div>
            <div class="form-group">
                <label for="courseCode">Course code</label>
                <form:errors path="courseCode" cssClass="text-danger" />
                <form:input class="form-control" path="courseCode" />
            </div>
            <div class="form-group">
                <label for="university.name">University</label>
                <form:errors path="university.name" cssClass="text-danger" />
                <form:input class="form-control" path="university.name" />
            </div>
            <div class="form-group">
                <label for="teacherName">Teachers name</label>
                <form:errors path="teacherName" cssClass="text-danger" />
                <form:input class="form-control" path="teacherName" />
            </div>
            <div class="form-group">
                <label for="courseLink">Course web page</label>
                <form:errors path="courseLink" cssClass="text-danger" />
                <form:input class="form-control" path="courseLink" />
            </div>
            <div class="form-group">
                <label for="coursePoints">Course points</label>
                <form:errors path="coursePoints" cssClass="text-danger" />
                <form:select class="form-control" path="coursePoints">
                    <form:option value="0"/>
                    <c:forEach begin="0" end="39" varStatus="val">
                        <form:option value="${val.count}" />
                    </c:forEach>
                </form:select>
            </div>
            <div class="form-group">
                <label for="numberOfAssignments">Number of assignments</label>
                <form:errors path="numberOfAssignments" cssClass="text-danger" />
                <form:select class="form-control" path="numberOfAssignments">
                    <form:option value="0"/>
                    <form:option value="1"/>
                    <form:option value="2"/>
                    <form:option value="3"/>
                    <form:option value="4"/>
                    <form:option value="5"/>
                </form:select>
            </div>
            <div class="form-group">
                <label for="numberOfProjects">Number of projects</label>
                <form:errors path="numberOfProjects" cssClass="text-danger" />
                <form:select class="form-control" path="numberOfProjects">
                    <form:option value="0"/>
                    <form:option value="1"/>
                    <form:option value="2"/>
                    <form:option value="3"/>
                    <form:option value="4"/>
                    <form:option value="5"/>
                </form:select>
            </div>
            <div class="form-group">
                <label for="numberOfExams">Number of exams</label>
                <form:errors path="numberOfExams" cssClass="text-danger" />
                <form:select class="form-control" path="numberOfExams">
                    <form:option value="0"/>
                    <form:option value="1"/>
                    <form:option value="2"/>
                    <form:option value="3"/>
                    <form:option value="4"/>
                    <form:option value="5"/>
                </form:select>
            </div>
            <label>Course books</label>
            <table id="course-book-list" class="table table-striped table-bordered">
                <tr>
                    <td>ISBN*</td>
                    <td>Title</td>
                    <td>Author</td>
                    <td>Language</td>
                    <td>Released</td>
                </tr>
                <tr>
                    <td>                
                        <div class="form-group">
                            <label class="sr-only" for="courseBooks[0].isbn">ISBN</label>
                            <form:errors path="courseBooks[0].isbn" cssClass="text-danger" />
                            <form:input class="form-control" path="courseBooks[0].isbn" />
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <label class="sr-only" for="courseBooks[0].title">Title</label>
                            <form:errors path="courseBooks[0].title" cssClass="text-danger" />
                            <form:input class="form-control" path="courseBooks[0].title" />
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <label class="sr-only" for="courseBooks[0].creator">Creator</label>
                            <form:errors path="courseBooks[0].creator" cssClass="text-danger" />
                            <form:input class="form-control" path="courseBooks[0].creator" />
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <label class="sr-only" for="courseBooks[0].language">Language</label>
                            <form:errors path="courseBooks[0].language" cssClass="text-danger" />
                            <form:input class="form-control" path="courseBooks[0].language" />
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <label class="sr-only" for="courseBooks[0].released">Release</label>
                            <form:errors path="courseBooks[0].released" cssClass="text-danger" />
                            <form:input class="form-control" path="courseBooks[0].released" />
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>                
                        <div class="form-group">
                            <label class="sr-only" for="courseBooks[1].isbn">ISBN</label>
                            <form:errors path="courseBooks[1].isbn" cssClass="text-danger" />
                            <form:input class="form-control" path="courseBooks[1].isbn" />
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <label class="sr-only" for="courseBooks[1].title">Title</label>
                            <form:errors path="courseBooks[1].title" cssClass="text-danger" />
                            <form:input class="form-control" path="courseBooks[1].title" />
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <label class="sr-only" for="courseBooks[1].creator">Creator</label>
                            <form:errors path="courseBooks[1].creator" cssClass="text-danger" />
                            <form:input class="form-control" path="courseBooks[1].creator" />
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <label class="sr-only" for="courseBooks[1].language">Language</label>
                            <form:errors path="courseBooks[1].language" cssClass="text-danger" />
                            <form:input class="form-control" path="courseBooks[1].language" />
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <label class="sr-only" for="courseBooks[1].released">Release</label>
                            <form:errors path="courseBooks[1].released" cssClass="text-danger" />
                            <form:input class="form-control" path="courseBooks[1].released" />
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>                
                        <div class="form-group">
                            <label class="sr-only" for="courseBooks[2].isbn">ISBN</label>
                            <form:errors path="courseBooks[2].isbn" cssClass="text-danger" />
                            <form:input class="form-control" path="courseBooks[2].isbn" />
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <label class="sr-only" for="courseBooks[2].title">Title</label>
                            <form:errors path="courseBooks[2].title" cssClass="text-danger" />
                            <form:input class="form-control" path="courseBooks[2].title" />
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <label class="sr-only" for="courseBooks[2].creator">Creator</label>
                            <form:errors path="courseBooks[2].creator" cssClass="text-danger" />
                            <form:input class="form-control" path="courseBooks[2].creator" />
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <label class="sr-only" for="courseBooks[2].language">Language</label>
                            <form:errors path="courseBooks[2].language" cssClass="text-danger" />
                            <form:input class="form-control" path="courseBooks[2].language" />
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <label class="sr-only" for="courseBooks[2].released">Release</label>
                            <form:errors path="courseBooks[2].released" cssClass="text-danger" />
                            <form:input class="form-control" path="courseBooks[2].released" />
                        </div>
                    </td>
                </tr>
            </table>
            <p>* ISBN needs to be filled in to add new books</p>
            <div class="form-group">
                <button type="submit" class="btn btn-default">Save course</button>
            </div>
        </form:form>
    </div>    
</body>
</html>