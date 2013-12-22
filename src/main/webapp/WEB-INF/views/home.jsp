<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>

<!DOCTYPE html>
<html>
<head>
<script src="<c:url value="/resources/js/jquery-2.0.3.min.js" />"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />"></link>
<link rel="stylesheet" href="http://cdn.leafletjs.com/leaflet-0.7.1/leaflet.css" />
<script src="http://cdn.leafletjs.com/leaflet-0.7.1/leaflet.js"></script>
<script src="<c:url value="/resources/js/scripts.js" />"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />"></link>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-responsive.css" />"></link>
<meta charset=utf-8 />
<title>IGotFooled</title>
</head>
<body>
    <nav id="header" class="col-md-12 navbar navbar-default navbar-fixed-top" role="navigation">
        <a class="navbar-brand" href="/">
            <strong>IGotFooled</strong><small> Scam registration service</small>
        </a>
    </nav>
    <div class="row">
        <div class="col-md-12" id="map-form-div">
            <div class="col-md-9">
                <div id="map"></div>
            </div>
            <div class="col-md-3">
                <h4>Did something happened to you or your freinds? Report it here.</h4>
                <form:form role="form" modelAttribute="addScamForm" method="POST" action="addscam">
    
                    <div class="form-group">
                        <label for="title">Title</label>
                        <form:errors path="title" cssClass="text-danger" /> 
                        <input type="text" class="form-control" name="title" id="title" placeholder="Title">
                    </div>
                    <div class="form-group">
                        <label for="heigh">Description</label>
                        <textarea type="text" class="form-control" name="description" id="description" placeholder="Description"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="category">Category</label> <select type="text" class="form-control" name="category" id="category" placeholder="category">
                            <option>Tourist attraction</option>
                            <option>Auctions and online selling</option>
                            <option>Rental scams</option>
                            <option>Lottery scams</option>
                            <option>Romance scams</option>
                            <option>Loan scams</option>
                            <option>Employment scams</option>
                            <option>Recovery scams</option>
                            <option>Charity scams</option>
                            <option>Fake sites used for fraud</option>
                            <option>Email scams</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="country">Address</label> 
                        <input type="text" class="form-control" name="country" id="country" placeholder="Country"> 
                        <input type="text" class="form-control" name="city" id="city" placeholder="City">
                        <input type="text" class="form-control" name="lat" id="latitude" placeholder="Latitude">
                        <input type="text" class="form-control" name="lng" id="longitude" placeholder="Longitude">
                        
                    </div>
                    <div class="form-group">
                        <label for="money">Impact</label><br /> 
                        <label class="checkbox-inline"> 
                            <form:errors path="trustcb" cssClass="text-danger" /> 
                            <form:checkbox path="trustcb" id="trust-checkbox" value="trust"/>
                            Trust
                        </label> 
                            <label class="checkbox-inline"> 
                            <form:errors path="moneycb" cssClass="text-danger" /> 
                            <form:checkbox path="moneycb" id="money-checkbox" value="money"/> 
                            Money
                        </label> 
                        <label class="checkbox-inline"> 
                            <form:errors path="violencecb" cssClass="text-danger" /> 
                            <form:checkbox path="violencecb" id="violence-checkbox" value="violence"/> 
                            Violence
                        </label> 
                        <label class="checkbox-inline"> 
                            <form:errors path="propertycb" cssClass="text-danger" /> 
                            <form:checkbox path="propertycb" id="property-checkbox" value="property"/> 
                            Property
                        </label>
                    </div>
                    <div class="form-group">
                        <div id="money-div">
                            <label for="money">Money lost</label> <input type="number" class="form-control" name="money" id="money" placeholder="Money lost" hidden=>
                        </div>
                        <div id="violence-div">
                            <label for="violence">Violence</label> <input type="text" class="form-control" name="violence" id="violence" placeholder="Violence">
                        </div>
                        <div id="property-div">
                            <label for="property">Property</label> <input type="text" class="form-control" name="property" id="property" placeholder="Property">
                        </div>
                    </div>
                    <button class="btn btn-default">Add</button>
                </form:form>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="col-md-12">
                <table class="table table-striped">
                    <tr>
                        <td>Title</td>
                        <td>Description</td>
                        <td>Category</td>
                        <td>Date</td>
                        <td>Country</td>
                        <td>City</td>
                        <td>Reported</td>
                        <td>Impact</td>
                    </tr>
                    <script> var cordinateArray =[],longAndLat;</script>
                    
                    <c:forEach items="${scams}" var="scam">
                        <tr>
                            <td><c:out value="${scam.title}" /></td>
                            <td><c:out value="${scam.description}" /></td>
                            <td><c:out value="${scam.category}" /></td>
                            <td><c:out value="${scam.dateString}" /></td>
                            <td><c:out value="${scam.city.country}" /></td>
                            <td><c:out value="${scam.city.name}" /></td>
                            <td><c:out value="${scam.reported}" /></td>
                            <td><c:out value="${scam.damage}"  /></td>
                             
                            <script>
                                longAndLat = {
                                        longitude: "${scam.adress.lng}",
                                        latitude: "${scam.adress.lat}",
                                        title: "${scam.title}",
                                };
                                cordinateArray.push(longAndLat);
                            </script>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</body>
</html>











