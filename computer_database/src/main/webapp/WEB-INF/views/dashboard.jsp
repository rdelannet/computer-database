
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="ListServlet"> Application - Computer Database </a>
        </div>
    </header>
    
    <c:set var="valueSearch" value="" />
	<c:if test="${search != null && search != ''}">
		<c:set var="valueSearch" value="&search=${search}" />
	</c:if>
	
	<c:set var="nbByPageValue" value="" />
	<c:if test="${nbByPage != null && nbByPage != ''}">
		<c:set var="nbByPageValue" value="&nbByPage=${nbByPage}" />
	</c:if>
	<c:set var="orderValue" value="" />
	<c:if test="${order != null && order != ''}">
		<c:set var="orderValue" value="&order=${order}" />
	</c:if>
	
	<c:set var="ascendingValue" value="" />
	<c:if test="${ascending != null && ascending != ''}">
		<c:set var="ascendingValue" value="&ascending=${ascending}" />
	</c:if>

    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
                ${nbComputers} Computers found
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="#" method="GET" class="form-inline">

                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="AddServlet">Add Computer</a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
                </div>
            </div>
        </div>
        <form id="deleteForm" action="ListServlet" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th>
                            <c:if test="${order == 'computer.name' && ascending == 'DESC'}"><a href="ListServlet?order=computer.name&ascending=ASC${nbByPageValue}${searchValue}" >Computer name</a></c:if>
                            <c:if test="${order == 'computer.name' && ascending == 'ASC'}"><a href="ListServlet?order=computer.name&ascending=DESC${nbByPageValue}${searchValue}" >Computer name</a></c:if>
                        	<c:if test="${order != 'computer.name'}"><a href="ListServlet?order=computer.name&ascending=ASC${nbByPageValue}${searchValue}" >Computer name</a></c:if>
                        </th>
                        <th>
                            <c:if test="${order == 'computer.introduced' && ascending == 'DESC'}"><a href="ListServlet?order=computer.introduced&ascending=ASC${nbByPageValue}${searchValue}" >Introduced date</a></c:if>
                            <c:if test="${order == 'computer.introduced' && ascending == 'ASC'}"><a href="ListServlet?order=computer.introduced&ascending=DESC${nbByPageValue}${searchValue}" >Introduced date</a></c:if>
                        	<c:if test="${order != 'computer.introduced'}"><a href="ListServlet?order=computer.introduced&ascending=ASC${nbByPageValue}${searchValue}" >Introduced date</a></c:if>
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            <c:if test="${order == 'computer.discontinued' && ascending == 'DESC'}"><a href="ListServlet?order=computer.discontinued&ascending=ASC${nbByPageValue}${searchValue}" >Discontinued date</a></c:if>
                            <c:if test="${order == 'computer.discontinued' && ascending == 'ASC'}"><a href="ListServlet?order=computer.discontinued&ascending=DESC${nbByPageValue}${searchValue}" >Discontinued date</a></c:if>
                        	<c:if test="${order != 'computer.discontinued'}"><a href="ListServlet?order=computer.discontinued&ascending=ASC${nbByPageValue}${searchValue}" >Discontinued date</a></c:if>
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            <c:if test="${order == 'c.name' && ascending == 'DESC'}"><a href="ListServlet?order=c.name&ascending=ASC${nbByPageValue}${searchValue}" >Company </a></c:if>
                            <c:if test="${order == 'c.name' && ascending == 'ASC'}"><a href="ListServlet?order=c.name&ascending=DESC${nbByPageValue}${searchValue}" >Company </a></c:if>
                        	<c:if test="${order != 'c.name'}"><a href="ListServlet?order=c.name&ascending=ASC${nbByPageValue}${searchValue}" >Company </a></c:if>
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
               
                <c:forEach var="computer" items="${computers}" >
                		<tr>
                			<td class="editMode">
                            	<input type="checkbox" name="cb" class="cb" value="${computer.id}">
                        	</td>
                        	<td>
                            	<a href="EditServlet?id=${computer.id}" onclick="">${computer.name}</a>
                        	</td>
                        	<td><c:if test="${not empty computer.introduced}">${computer.introduced} </c:if></td>
                        	<td><c:if test="${not empty computer.discontinued}">${computer.discontinued} </c:if></td>
                        	<td><c:if test="${not empty computer.companyDTO}">${computer.companyDTO} </c:if></td>
                		</tr>
                	</c:forEach>
               
                    
                </tbody>
            </table>
        </div>
    </section>

    <footer class="navbar-fixed-bottom">
       <div class="container text-center">
            <ul class="pagination">
                <li>
                	<c:if test="${page.currentPage > 1}">
                    	<a href="/computer_database/ListServlet?page=${page.currentPage-1}${nbByPageValue}${valueSearch}${orderValue}${ascendingValue}" aria-label="Previous">
                      		<span aria-hidden="true">&laquo;</span>
                  		</a>
                	</c:if>
              	</li>
              	<c:forEach var="i" begin="1" end="2">
              		<c:if test="${page.currentPage-(3-i) >= 1}">
              			<li><a href="/computer_database/ListServlet?page=${page.currentPage-(3-i)}${nbByPageValue}${valueSearch}${orderValue}${ascendingValue}"><c:out value="${page.currentPage-(3-i)}"/></a></li>
              		</c:if>
              	</c:forEach>
              	<c:forEach var="i" begin="0" end="2">
              		<c:if test="${page.currentPage+i <= nbComputers}">
              			<c:set var="active" value=""/>
                        <c:if test = "${page.currentPage+i ==  page.currentPage}">
                          <c:set var="active" value="active"/>
                         </c:if>
              			<li class="${active}"><a href="/computer_database/ListServlet?page=${page.currentPage+i}${nbByPageValue}${valueSearch}${orderValue}${ascendingValue}"><c:out value="${page.currentPage+i}"/></a></li>
              		</c:if>
              	</c:forEach>
              	<li>
              		<c:if test="${page.currentPage < nbComputers}">
                		<a href="/computer_database/ListServlet?page=${page.currentPage+1}${nbByPageValue}${valueSearch}${orderValue}${ascendingValue}" aria-label="Next">
                    		<span aria-hidden="true">&raquo;</span>
                		</a>
                	</c:if>
            	</li>
        	</ul>
        	<div class="btn-group btn-group-sm pull-right" role="group" >
        	<c:set var="active" value=""/>
            <c:if test = "${page.itemsByPage == 10}">
               <c:set var="active" value="active"/>
            </c:if>
            <a href="/computer_database/ListServlet?nbByPage=10${valueSearch}${orderValue}${ascendingValue}"><button type="button" class="btn btn-default ${active}">10</button></a>
            <c:set var="active" value=""/>
            <c:if test = "${page.itemsByPage == 50}">
               <c:set var="active" value="active"/>
            </c:if>
            <a href="/computer_database/ListServlet?nbByPage=50${valueSearch}${orderValue}${ascendingValue}"><button type="button" class="btn btn-default ${active}">50</button></a>
            <c:set var="active" value=""/>
            <c:if test = "${page.itemsByPage == 100}">
               <c:set var="active" value="active"/>
            </c:if>
            <a href="/computer_database/ListServlet?nbByPage=100${valueSearch}${orderValue}${ascendingValue}"><button type="button" class="btn btn-default ${active}">100</button></a>
        	</div>
        </div>

    </footer>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/dashboard.js"></script>

</body>
</html>