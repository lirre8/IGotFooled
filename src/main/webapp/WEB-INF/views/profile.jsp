<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/spring-social/social/tags" prefix="social"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

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
        <h3 class="inline">Profile</h3>
        <c:if test="${isProfileOwner == true}">
            <em><a href="${user.id}/edit"> edit</a></em>
        </c:if>

        <ul class="list-group">
            <li class="list-group-item">
                <p>Email: <strong>${user.username}</strong></p>    
            </li>
            <li class="list-group-item">
                <p>First name: <strong>${user.firstName}</strong></p>    
            </li>
            <li class="list-group-item">
                <p>Last name: <strong>${user.lastName}</strong></p>
            </li>
            <li class="list-group-item">
                <p>University: <strong>${user.university.name}</strong></p>
            </li>
            <c:if test="${isProfileOwner == true}">
            <li class="list-group-item">
                <social:notConnected provider="facebook">
                    <form action="<c:url value="/connect/facebook" />" method="POST">
                        <input type="hidden" name="scope" value="publish_stream,user_photos,offline_access" />
                        <p>You aren't connected to Facebook yet. Click the button to connect Course Portal with your Facebook account.</p>
                        <button type="submit" class='zocial facebook'>Connect</button>
                    </form>
                </social:notConnected>
                <social:connected provider="facebook">
                    <p>Facebook connected</p>
                </social:connected>
            </li>
            <li class="list-group-item">
                <social:notConnected provider="linkedin">
                    <form action="<c:url value="/connect/linkedin" />" method="POST">
                        <input type="hidden" name="scope" value="r_basicprofile r_emailaddress r_network r_fullprofile rw_nus" />
                        <p>You haven't created any connections with LinkedIn yet. Click the button to connect Course Portal with your LinkedIn account. (You'll be
                            redirected to LinkedIn where you'll be asked to authorize the connection.)</p>
                        <button type="submit" class='zocial linkedin'>Connect</button>
                    </form>
                </social:notConnected>
                <social:connected provider="linkedin">
                    <p class="inline">Linkedin connected</p>
                </social:connected>
                
            </li>
            <li class="list-group-item">
                <social:notConnected provider="twitter">
                    <form action="<c:url value="/connect/twitter" />" method="POST">
                        <p>You haven't created any connections with Twitter yet. Click the button to connect Course Portal with your Twitter account. (You'll be
                            redirected to Twitter where you'll be asked to authorize the connection.)</p>
                        <button type="submit" class='zocial twitter'>Connect</button>
                    </form>
                </social:notConnected>
                <social:connected provider="twitter">
                    <p>Twitter connected</p>
            </social:connected>
            </li>
            </c:if>
        </ul>
    </div>
</body>
</html>