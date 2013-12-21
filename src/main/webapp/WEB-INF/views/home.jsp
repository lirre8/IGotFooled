<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <style>
        body  {
            background: url(resources/image/background.jpg) no-repeat center center fixed;
            box-shadow: inset 0 0 0 2000px rgba(0,0,0,0.2);
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
            background-size: cover;
        }
    </style>
    <script>
        function focusRemoveSearch() {
            $(".search-course").focus();
            $('#header-search-div').remove();
        }
    </script>
</head>
<body onload="focusRemoveSearch()">
    <c:import url="header.jsp" />
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="row">
            <div class="well" id="search-well">
                <p class="lead">Enter what course you want to search for!</p>
                <div class="input-group">
                    <input type="text" placeholder="Search..." autocomplete="off" class="search-course form-control search-query">
                    <span class="input-group-btn">
                        <button type="button" class="search-course-button btn btn-default">Search</button>
                    </span> 
                    <input type="hidden" id="hiddenCourseId" name="hiddenCourseId">
                </div>
                <strong><em style="padding: 3px 0 0 10px">Add new course <a href="/addcourse">here</a></em></strong>
                </div>
            </div>
        </div>
    </div>
    <div class="row well" id="info-well">
        <div class="col-md-2 col-md-offset-1">
            <h3>Book market</h3>
            <p>It's <strong>easy to sell and buy books</strong>. Just find the course it's used in and add your book.</p>
        </div>
        <div class="col-md-2">
            <h3>Discussions</h3>
            <p>Have you ever wanted to speak with someone that took the course you planning to take? You can easily go in and read other student's experiences and ask
                questions.</p>
        </div>
        <div class="col-md-2">
            <h3>Hesitating over a course?</h3>
            <p>Are you hesitating over a course you planning to take? You can easily see what other students though, if they spend much time on it, if the teachers
                are good and if the exercises are good.</p>
        </div>
        <div class="col-md-2">
            <h3>Comparison</h3>
            <p>Where are the <strong>best educations</strong> in the world? Where does students <strong>work hardest?</strong> Where are the best teachers? Read about all this on Course Portal</p>
        </div>
        <div class="col-md-2">
            <h3>Growing community</h3>
            <p>As Course Portal growing we are implementing more features. Some of them are the ability to upload and discuss exams, see lecture notes, see how your
                favorite course is ranked globally in the world and so on. Keep on using Course Portal and we keep on developing Course Portal.</p>
        </div>
    </div>
</body>
</html>
