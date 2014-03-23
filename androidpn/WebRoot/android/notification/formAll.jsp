<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%pageContext.setAttribute("basePath",request.getContextPath());%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Admin Console</title>
<meta name="menu" content="notification" />
<link rel="stylesheet" type="text/css" href="${basePath}/styles/tablesorter/style.css" />
<script type="text/javascript" src="${basePath}/common/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${basePath}/common/jquery.tablesorter.js"></script>
</head>

<body>

<h1>发送广播</h1>

<%--<div style="background:#eee; margin:20px 0px; padding:20px; width:500px; border:solid 1px #999;">--%>
<div style="margin:20px 0px;">
<form action="${basePath}/manage/sendAll.action" method="post" style="margin: 0px;">
<table width="600" cellpadding="4" cellspacing="0" border="0">

<tr>
	<td>标题:</td>
	<td><input type="text" id="title" name="title" style="width:380px;" /></td>
</tr>
<tr>
	<td>内容:</td>
	<td><textarea id="message" name="message" style="width:380px; height:80px;" ></textarea></td>
</tr>
<%--
<tr>
	<td>Ticker:</td>
	<td><input type="text" id="ticker" name="ticker" value="" style="width:380px;" /></td>
</tr>
--%>
<tr>
	<td>URI:</td>
	<td><input type="text" id="uri" name="uri" value="" style="width:380px;" />
	    <br/><span style="font-size:0.8em">ex) http://www.dokdocorea.com, geo:37.24,131.86, tel:111-222-3333</span>
	</td>
</tr>
<tr>
	<td>&nbsp;</td>
	<td><input type="submit" value="发送" />&nbsp;&nbsp;
		<input type="button" value="返回" onclick="location='${basePath}/manage/apnlist.action'" />
	</td>
</tr>
</table> 
</form>
</div>

</body>
</html>
