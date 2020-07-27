<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="ListServlet"> <spring:message code="title"/></a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1><spring:message code="addComputer.title"/></h1>
                    <form action="AddServlet" method="POST" onsubmit="return verifForm(this)">
                        <fieldset>
                         
                            <div class="form-group">
                                <label for="computerName"><spring:message code="form.input.name"/></label>
                                <input type="text" class="form-control" id="computerName" name="computerName" placeholder="Computer name"  onblur="verifName(this)">
                            </div>
                            <div class="form-group">
                                <label for="introduced"><spring:message code="form.input.introduced"/></label>
                                <input type="date" class="form-control" id="introduced" name="introduced" placeholder="Introduced date" onblur="verifDates(this.introduced, this.discontinued)">
                            </div>
                            <div class="form-group">
                                <label for="discontinued"><spring:message code="form.input.discontinued"/></label>
                                <input type="date" class="form-control" id="discontinued" name="discontinued" placeholder="Discontinued date" onblur="verifDates(this.introduced, this.discontinued)">
                            </div>
                            <div class="form-group">
                                <label for="companyId"><spring:message code="form.input.company"/></label>
                                <select class="form-control" id="companyId" name="companyId" >
                                	<c:forEach items="${company}" var="companies">
                                    	<option value="${companies.id}">${companies.name}</option>
                                    </c:forEach>
                                </select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="<spring:message code="form.button.add"/>" class="btn btn-primary">
                            
                            or
                            <a href="ListServlet" class="btn btn-default"><spring:message code="form.button.cancel"/></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
     <script src="js/validator.js"></script>
</body>
</html>