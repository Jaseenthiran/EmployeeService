<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@page import="model.employee"%>
<%
//Save---------------------------------
if (request.getParameter("itemCode") != null)
{
	employee eObj = new employee();
	String stsMsg = "";
//Insert--------------------------
if (request.getParameter("hidItemIDSave") == "")
{
	stsMsg = eObj.insertEmployee(request.getParameter("txtName"),
	request.getParameter("txtType"),
	request.getParameter("txtPhone"),
	request.getParameter("txtEmail"),
	request.getParameter("txtPass"));
}
else//Update----------------------
{
	stsMsg = eObj.updateEmployee(request.getParameter("hidItemIDSave"),
	request.getParameter("txtName"),
	request.getParameter("txtType"),
	request.getParameter("txtPhone"),
	request.getParameter("txtEmail"),
	request.getParameter("txtPass"));
}
session.setAttribute("statusMsg", stsMsg);
}
//Delete-----------------------------
if (request.getParameter("hidItemIDDelete") != null)
{
	employee eObj = new employee();
	String stsMsg =
	eObj.deleteEmployee(request.getParameter("hidItemIDDelete"));
	session.setAttribute("statusMsg", stsMsg);
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="views/bootstrap.min.css">

<script src="components/jquery-3.2.1.min.js"></script>
<script src="components/employee.js"></script>
<title>Employee Management</title>
</head>
<body>
<h1>Employee Management</h1>

<form id="formEmployee" name="formEmployee" method="post" action="items.jsp">
 Employee Name:
<input id="txtName" name="txtName" type="text"
 class="form-control form-control-sm">
<br> Employee Type:
<input id="txtType" name="txtType" type="text"
 class="form-control form-control-sm">
<br> Employee Phone:
<input id="txtPhone" name="txtPhone" type="text"
 class="form-control form-control-sm">
<br> Employee E-mail:
<input id="txtEmail" name="txtEmail" type="text"
 class="form-control form-control-sm">
<br> Employee Password:
<input id="txtPass" name="txtPass" type="text"
 class="form-control form-control-sm">
 <br>
<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

<br>
<div id="divItemsGrid">

<%
 employee eObj = new employee();
 out.print(eObj.readEmployee());
%>
</div>
</body>
</html>