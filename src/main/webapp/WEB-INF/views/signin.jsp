<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<body>
    <c:import url="header.jsp" />
    <div class="row signin-container">
        <div class="col-md-2 col-md-offset-1 well">
            <form id="signin" action="<c:url value="/signin/authenticate" />" method="post">
                <fieldset class="form-group">
                    <label for="login">Email</label> 
                    <input id="login" class="form-control" name="j_username" type="text" size="25" <c:if test="${not empty signinErrorMessage}">value="${SPRING_SECURITY_LAST_USERNAME}"</c:if> /> 
                    <label for="password">Password</label> 
                    <input id="password" class="form-control" name="j_password" type="password" size="25" />
                </fieldset>
                <button type="submit" class="btn btn-default">Sign In</button>
            </form>
            <div class="formInfo">
                <c:if test="${param.error == 'bad_credentials'}">
                    <span class="help-block">
                        <p class="text-danger">
                            Your sign in information was incorrect. Please try again or <a href="<c:url value="/signup" />">sign up</a>.
                        </p>
                    </span>
                </c:if>
                <c:if test="${param.error eq 'multiple_users'}">
                    <div class="text-error">Multiple local accounts are connected to the provider account. Try again with a different provider or with your email
                        and password.</div>
                </c:if>
            </div>
        </div>
        <div class="col-md-3 well">
            <p> Do you have an account on the following sites? Sign In an get started.</p>
            <p> Or you can <a href="<c:url value="/signup"/>">signup</a> with a new account.</p>
            <hr>
            <!-- FACEBOOK SIGNIN -->
            <form class="form" name="fb_signin" id="fb_signin" action="<c:url value="/signin/facebook"/>" method="POST">
                <div class="form-group">
                    <input type="hidden" name="scope" value="publish_stream,user_photos,offline_access" />
                    <button type="submit" class='zocial facebook'>Sign In with Facebook</button>
                </div>
            </form>
            <!-- TWITTER SIGNIN -->
            <form class="form" id="tw_signin" action="<c:url value="/signin/twitter"/>" method="POST">
                <div class="form-group">
                    <button type="submit" class='zocial twitter'>Sign In with Twitter</button>
                </div>
            </form>
            <!-- LINKEDIN SIGNIN -->
            <form class="form" name="li_signin" id="li_signin" action="<c:url value="/signin/linkedin"/>" method="POST">
                <div class="form-group">
                    <button type="submit" class='zocial linkedin'>Sign In with LinkedIn</button>
                </div>
            </form>
        </div>
        <div class="col-md-2 well">
            <h4>Signing in with social networks</h4>
            <p>By signing in to social networks you save yourself a lot of work. Course Portal will help you fill in information that are available from LinkedIn,
                Facebook and Twitter for you. aAnd we will of course not send any messages on your behalf.</p>
        </div>
    </div>
</body>
</html>

