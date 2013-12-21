<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page session="false"%>

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
<body data-spy="scroll" data-target="#sidebar" data-offset="62" data-target="#sidebar">
    <c:import url="header.jsp" />
    <div class="container">
        <div>
            <div class="col-md-2">
                <div id="sidebar">
                    <ul class="nav nav-list">
                        <li><a href="#information">Information</a></li>
                        <li><a href="#current-ranks">Score</a></li>
                        <li><a href="#discuss-rank">Rank and Discuss</a></li>
                        <li><a href="#books">Book market</a></li>
                        <!--  
                        <li><a href="#lecture-notes"><i class="icon-chevron-right"></i> Lecture notes</a></li>
                        <li><a href="#exams"><i class="icon-chevron-right"></i> Previous exams</a></li>
                        <li><a href="#examination"><i class="icon-chevron-right"></i>Examination</a></li>
                        -->
                    </ul>
                </div>
            </div>
            <div class="col-md-10">
                <div class="col-md-12" id="information">
                    <div class="row">
                        <h1>${course.courseName} <c:if test="${course.coursePoints != 0}"><small>${course.coursePoints} Points</small></c:if></h1>
                        <h3>${course.courseCode} at ${course.university.name}</h3>
                        <hr>
                        <div class="alert alert-info">
                            Missing some information or found something that is wrong? Help out and change it <a href="/courses/${course.id}/${course.courseName}/edit  "><strong>here</strong></a>
                        </div>
                        <div class="col-md-8">
                            <ul>
                                <li>
                                    <c:choose>
                                        <c:when test="${not empty course.teacherName}">
                                            Current teacher of the course is <strong>${course.teacherName}</strong>
                                        </c:when>
                                        <c:otherwise>
                                            There are no teachers added for this course yet
                                        </c:otherwise>
                                    </c:choose>
                                </li>
                                <li>
                                    <c:choose>
                                        <c:when test="${not empty course.courseLink}">
                                            The university's course web page can be found here: <a id="courseLink" href="${course.courseLink}">${course.courseLink}</a>
                                        </c:when>
                                        <c:otherwise>
                                            The university don't have a web page for this course or it is not added yet
                                        </c:otherwise>
                                    </c:choose>
                                </li>
                                <c:if test="${empty books}"> 
                                <li>
                                    No course books have been added yet
                                </li>
                                </c:if>
                            </ul>
                        </div>
                        <div class="col-md-4">
                            <canvas id="work-distribution-canvas" width="250" height="250"></canvas>
                        </div>
                        <c:if test="${not empty books}">
                        <p>Course books</p>
                        <table id="course-book-list" class="table table-striped table-bordered">
                            <tr>
                                <td>ISBN</td>
                                <td>Title</td>
                                <td>Author</td>
                                <td>Language</td>
                                <td>Released</td>
                            </tr>
                            <c:forEach items="${books}" var="book">
                                <tr>
                                    <td><c:out value="${book.isbn}" /></td>
                                    <td><c:out value="${book.title}" /></td>
                                    <td><c:out value="${book.creator}" /></td>
                                    <td><c:out value="${book.language}" /></td>
                                    <td><c:out value="${book.released}" /></td>
                                </tr>
                            </c:forEach>
                        </table>
                        </c:if>
                    </div>
                </div>
                <div class="col-md-12" id="current-ranks">
                    <h3>Overall score curve</h3>
                    <p>This is the avarage score of the course. A high number means that students overall liked the course. <span></span></p>
                    <canvas id="overall-score-canvas" width="900" height="200"></canvas>
                    <h3>Effort, structure and teachers curve</h3>
                    <p>Here is three other grades. 10 means very good or high. <span></span></p>
                    <dl class="dl-horizontal color-dot-list">
                        <dt><i class="color-dot color-dot-red"></i></dt>
                        <dd><p><abbr title="10 means that it was very good">Teachers/Professors</abbr></p></dd>
                        <dt><i class="color-dot color-dot-yellow"></i></dt>
                        <dd><p><abbr title="10 means that it was very good">Structure of the course</abbr></p></dd>
                        <dt><i class="color-dot color-dot-blue"></i></dt>
                        <dd><p><abbr title="10 means very much time spend and that the course was very hard">Effort put into the course</abbr></p></dd>
                    </dl>
                    <div id="carousel" class="carousel slide">
                        <ol class="carousel-indicators">
                            <li data-target="carousel" data-slide-to="0" class="active"></li>
                            <li data-target="carousel" data-slide-to="1"></li>
                            <li data-target="carousel" data-slide-to="2"></li>
                        </ol>

                        <div class="carousel-inner">
                            <div class="item active">
                                <canvas id="teachers-score-canvas" width="900" height="200"></canvas>
                            </div>
                            <div class="item">
                                <canvas id="structure-score-canvas" width="900" height="200"></canvas>
                            </div>
                            <div class="item">
                                <canvas id="effort-score-canvas" width="900" height="200"></canvas>
                            </div>
                        </div>

                        <a class="left carousel-control" href="#carousel" data-slide="prev"> 
                        <i class="fa carousel-icon fa-arrow-circle-o-left fa-2x"></i>
                        </a> 
                        <a class="right carousel-control" href="#carousel" data-slide="next"> 
                            <i class="fa carousel-icon fa-arrow-circle-o-right fa-2x"></i>
                        </a>
                    </div>

                    <div class="col-md-12" id="discuss-rank">
                    <h3>Rank and Discussion</h3>
                    <p>If you have been taking the course please help out and rank it. Or if you have questions feel free to ask them.</p>
                    <ul class="nav nav-tabs" id="course-page-tabs">
                        <li class="active"><a href="#ranks-tab" data-toggle="tab">Ranks</a></li>
                        <li><a href="#discussion-tab" data-toggle="tab">Discussion</a></li>
                    </ul>
                    <div class="tab-content pre-scrollable">
                        <div class="tab-pane active" id="ranks-tab">
                            <c:if test="${courseNotRanked}">
                            <form:form class="form" action="/courses/${course.id}/${course.courseName}/rank" method="post" modelAttribute="rankCourseForm">
                                    <div class="row">
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label for="avarageScore">Overall impression</label> <i class="fa fa-question-circle" title="Would you recommend the course?"></i>
                                                <form:errors path="avarageScore" cssClass="text-danger" />
                                                <br/>
                                                <p class="inline text-danger">Bad &nbsp &nbsp</p>
                                                <form:input class="slider form-control" data-slider-min="0" data-slider-max="10" data-slider-step="1" data-slider-value="5" value="5" data-slider-selection="after" path="avarageScore" />
                                                <p class="inline text-success">&nbsp &nbsp Good</p>
                                            </div>
                                            <div class="form-group">
                                                <label for="teachers">Teachers</label> <i class="fa fa-question-circle" title="Did the teachers care? Did they do their best? and where they good at teaching?"></i>
                                                <form:errors path="teachers" cssClass="text-danger" />
                                                <br/>
                                                <p class="inline text-danger">Bad &nbsp &nbsp</p>
                                                <form:input class="slider form-control" data-slider-min="0" data-slider-max="10" data-slider-step="1" data-slider-value="5" value="5" data-slider-selection="after" path="teachers" />
                                                <p class="inline text-success">&nbsp &nbsp Good</p>
                                            </div>
                                            
                                            <div class="form-group">
                                                <label for="structure">Structure</label> <i class="fa fa-question-circle" title="Was it clear what's needed from you? Was it easy to get in contact when having questions? and are the content relevant?"></i>
                                                <form:errors path="structure" cssClass="text-danger" />
                                                <br/>
                                                <p class="inline text-danger">Bad &nbsp &nbsp</p>
                                                <form:input class="slider form-control" data-slider-min="0" data-slider-max="10" data-slider-step="1" data-slider-value="5" value="5" data-slider-selection="after" path="structure" />
                                                <p class="inline text-success">&nbsp &nbsp Good</p>
                                            </div>
                                            
                                            <div class="form-group">
                                                <label for="workload">Effort</label> <i class="fa fa-question-circle" title="1.5 points is equals to 40 hours work (accourding to the Swedish system). How much time did you spend on the course and how hard was it?"></i>
                                                <form:errors path="workload" cssClass="text-danger" />
                                                <br/>
                                                <p class="inline text-warning">Low &nbsp &nbsp</p>
                                                <form:input class="slider form-control" data-slider-min="0" data-slider-max="10" data-slider-step="1" data-slider-value="5" value="5" data-slider-selection="after" path="workload" />
                                                <p class="inline text-warning">&nbsp &nbsp High</p>
                                            </div>
                                        </div>
                                        <div class="col-md-7">
                                            <div class="form-group">
                                                <label for="comment">Comment</label>
                                                <form:errors path="comment" cssClass="text-danger" />
                                                <form:textarea rows="7" class="form-control" path="comment" />
                                            </div>
                                            <button type="submit" class="btn btn-default">Rank it</button>
                                        </div>
                                    </div>
                            </form:form>
                            </c:if>
                            <c:if test="${not empty ranksArray}">
                            <table class="table table-striped table-bordered">
                                <tr>
                                    <td>Overall</td>
                                    <td>Teachers</td>
                                    <td>Structure</td>
                                    <td>Effort</td>
                                    <td>Comment</td>
                                    <td>Date</td>
                                </tr>
                                <c:forEach items="${ranksArray}" var="rank">
                                    <tr>
                                        <td><c:out value="${rank.avarageScore}" />/10</td>
                                        <td><c:out value="${rank.teachers}" />/10</td>
                                        <td><c:out value="${rank.structure}" />/10</td>
                                        <td><c:out value="${rank.workload}" />/10</td>
                                        <td><c:out value="${rank.comment}" /></td>
                                        <td><c:out value="${rank.date}" /></td>
                                    </tr>
                                </c:forEach>
                            </table>
                            </c:if>
                        </div>
                        <div class="tab-pane" id="discussion-tab">
                            <div class="col-md-12" id="discussion">
                                <c:forEach items="${comments}" var="comment">
                                    <div class="discussion-comment" id="${comment.id}">
                                        <h4 class="inline">
                                            <small> 
                                                <c:choose>
                                                    <c:when test="${comment.anonymous == false}">
                                                        <a href="/users/${comment.userId}" class="text-primary inline">${comment.name}</a>
                                                        <c:if test="${not empty comment.university}">
                                                            at ${comment.university}
                                                        </c:if>
                                                    </c:when>
                                                    <c:otherwise>
                                                        Anonymous
                                                    </c:otherwise>
                                                </c:choose> 
                                                ${comment.date}
                                            </small>
                                        </h4>

                                        <sec:authorize access="isAuthenticated()">
                                            <sec:authentication var="principal" property="principal" />
                                            <c:if test="${comment.userId == principal.id}">
                                                <form:form class="pull-right inline" action="/courses/${course.id}/${course.courseName}/removecomment/${comment.id}" method="post">
                                                    <button type="submit" class="btn btn-danger btn-sm">
                                                        <i class="fa fa-trash-o fa-lg"></i>
                                                    </button>
                                                </form:form>
                                            </c:if>
                                        </sec:authorize>
                                        
                                        <p>${comment.message}</p>
                                    </div>
                                </c:forEach>
                                <form:form class="form" action="/courses/${course.id}/${course.courseName}/comment" method="post" modelAttribute="addCommentForm">
                                    <div class="form-group">
                                        <label for="message">Comment</label>
                                        <form:errors path="message" cssClass="text-danger" />
                                        <form:textarea rows="7" maxlength="2000" class="form-control" path="message" />
                                    </div>
                                    <div class="row">
                                        <div class="span">
                                            <button type="submit" class="btn btn-default">Post comment</button>
                                        </div>
                                        <div class="span3">
                                        <label for="anonymous">Anonymous</label>
                                        <form:errors path="anonymous" cssClass="text-danger" />
                                        <form:checkbox id="anonymous" path="anonymous"/>
                                            <label class="checkbox"> 
                                            </label>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
                </div>
                <div class="col-md-12" id="books">
                <c:import url="user_books.jsp" />
                </div>
               <!--   
                <div class="col-md-12" id="lecture-notes">
                    <hr>
                    <h3>Lecture notes</h3>
                    This feature has not yet been implemented
                </div>
                <div class="col-md-12" id="exams">
                    <hr>
                    <h3>Previous exams</h3>
                    This feature has not yet been implemented
                </div>
                <div class="col-md-12" id="examination">
                    <hr>
                    <h3>Examination</h3>
                    This feature has not yet been implemented
                </div>
                -->
            </div>
        </div>
    </div>
</body>
<script>
	var courseLink = '${course.courseLink}';
	var courseId = '${course.id}';
    var courseCode = '${course.courseCode}';
    var numberOfAssignments = '${course.numberOfAssignments}';
    var numberOfExams = '${course.numberOfExams}';
    var numberOfProjects = '${course.numberOfProjects}';
    
    var ranksArray = [], rank;
    <c:forEach var="rank" items="${ranksArray}">
    rank = {
            workload: '${rank.workload}',
            structure: '${rank.structure}',
            teachers: '${rank.teachers}',
            avarageScore: '${rank.avarageScore}',
            comment: '${rank.comment}',
            date: '${rank.date}',
            userId: '${rank.userId}',
    };
    ranksArray.push(rank);                                  
	</c:forEach>
	<c:if test="${empty ranksArray}">
	     ranksArray = null;
	</c:if>
</script>
</html>