<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%pageContext.setAttribute("basePath",request.getContextPath());%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Admin Console</title>
<meta name="menu" content="session" />
<link rel="stylesheet" type="text/css" href="${basePath}/styles/tablesorter/style.css" />
<script type="text/javascript" src="${basePath}/common/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${basePath}/common/jquery.tablesorter.js"></script>
</head>

<body>

<h1>Sessions</h1>

<table id="tableList" class="tablesorter" cellspacing="1">
	<thead>
		<tr>
			<th>Username</th>
			<th>Resource</th>
			<th>Status</th>
			<th>Presence</th>
			<th>Client IP</th>
			<th>Created</th>			
		</tr>
	</thead>
	<tbody>
		<s:iterator value="sessionList">
			<tr>
				<td>${username}</td>
				<td>${resource}</td>
				<td align="center">${status}</td>
				<td>
					<s:if test="presence eq 'Online'">
						<img src="${basePath}/images/user-online.png" />
					</s:if>
					<s:elseif test="presence eq 'Offline'">
						<img src="${basePath}/images/user-offline.png" />
					</s:elseif>
					<s:else>
						<img src="${basePath}/images/user-away.png" />
					</s:else>
					${presence}
				</td>
				<td>${clientIP}</td>
				<td align="center"><s:date name="createdDate" format="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</s:iterator>
	</tbody>
</table>

<script type="text/javascript">
//<![CDATA[
$(function() {
	$('#tableList').tablesorter();
	//$('#tableList').tablesorter( {sortList: [[0,0], [1,0]]} );
	//$('table tr:nth-child(odd)').addClass('odd');
	$('table tr:nth-child(even)').addClass('even');	 
});
//]]>
</script>

</body>
</html>
