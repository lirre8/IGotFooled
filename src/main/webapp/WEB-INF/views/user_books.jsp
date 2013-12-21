<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page session="false"%>

<h3>Book Market</h3>
<p>Buy and sell books used in this course</p>
<div class="panel panel-default">
    <div class="panel-heading">
        <h4 class="panel-title">
        <c:choose>
        <c:when test="${empty books}">
            <p>Need to add course books before beeing able to add books for sale. Add it <a href="/courses/${course.id}/${course.courseName}/edit"><strong>here</strong></a></p>
        </c:when>
        <c:otherwise>
            <form:form class="form" action="/courses/${course.id}/${course.courseName}/adduserbook" method="post" modelAttribute="userBook">
                <div class="form-group">
                    <div class="row">
                        <div class="col-xs-5">
                            <label class="text-primary" for="isbn">ISBN and Title</label>
                            <form:select class="form-control" path="isbn">
                                <c:forEach var="book" items="${books}">
                                <form:option value="${book.isbn}">
                                ${book.isbn}: ${book.title}
                                </form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-xs-3">
                            <label class="text-warning" for="quality">Condition</label>
                            <form:select class="form-control" path="quality">
                                <form:option value="Low" />
                                <form:option value="OK" />
                                <form:option value="Good" />
                            </form:select>
                        </div>
                        <div class="col-xs-2">
                            <label class="text-danger" for="price">Price</label>
                            <form:errors path="price" cssClass="text-danger" />
                            <form:input class="form-control" placeholder="Specify price" path="price"/>
                            <sec:authorize access="isAuthenticated()">
                                <sec:authentication var="principal" property="principal" />
                                <form:input type="hidden" class="form-control" value="${principal.username}" path="username"/>
                                <form:input type="hidden" class="form-control" value="${principal.id}" path="userId"/>
                                <form:input type="hidden" class="form-control" value="${principal.firstName} ${principal.lastName}" path="fullName"/>
                            </sec:authorize>
                        </div>

                        <div class="col-xs-1">
                        <label>&nbsp</label>
                        <button type="submit" class="btn btn-default">Add for sale</button>
                        </div>
                    </div>
                </div>
            </form:form>
        </c:otherwise>
        </c:choose>
            
        </h4>
    </div>
</div>
<div class="panel-group" id="accordion">
    <c:forEach var="book" items="${userBooks}">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" href="#collapse${book.id}"> 
                <p class="text-primary inline"><i class="fa fa-book"></i> ${book.isbn}: ${book.title}</p>
                </a>
                <sec:authorize access="isAuthenticated()">
                    <sec:authentication var="principal" property="principal" />
                    <c:if test="${book.userId == principal.id}">
                        <form:form class="pull-right inline" action="/courses/${course.id}/${course.courseName}/removeuserbook/${book.id}" method="post">
                            <button type="submit" class="btn btn-danger btn-sm">
                                <i class="fa fa-trash-o fa-lg"></i>
                            </button>
                        </form:form>
                    </c:if>
                </sec:authorize>
                <a data-toggle="collapse" data-parent="#accordion" href="#collapse${book.id}">
                <br/>
                <br/>
                <p class="text-info inline">${book.fullName}</p>
                <p class="text-warning inline">Condition: ${book.quality}</p>
                <p class="text-danger inline">Price: ${book.price}</p>
                <p class="text-muted inline pull-right">Added: ${book.added}</p>
                 </a>
            </h4>
        </div>
        <div id="collapse${book.id}" class="panel-collapse collapse">
            <div class="panel-body">
                <form:form class="form" action="/courses/${course.id}/${course.courseName}/senduserbookemail" method="post" modelAttribute="bookEmail">
                    <div class="form-group">
                        <label for="message">Send email to seller (Your email is going to be revealed to the seller) : </label>
                        <form:errors path="message" cssClass="text-danger" />
                        <form:textarea rows="7" maxlength="2000" class="form-control" path="message" />
                        <form:input type="hidden" class="form-control" value="${book.title}" path="bookTitle"/>
                        <sec:authorize access="isAuthenticated()">
                            <sec:authentication var="principal" property="principal" />
                            <form:input type="hidden" class="form-control" value="${principal.username}" path="fromEmail"/>
                        </sec:authorize>
                        <form:input type="hidden" class="form-control" value="${book.username}" path="toEmail"/>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-default">Send</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
    </c:forEach>

</div>