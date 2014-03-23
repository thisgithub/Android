<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%pageContext.setAttribute("basePath",request.getContextPath());%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>在线 用户</title>
<meta name="menu" content="user" />
<link rel="stylesheet" type="text/css" href="${basePath}/styles/tablesorter/style.css" />
<script type="text/javascript" src="${basePath}/common/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${basePath}/common/jquery.tablesorter.js"></script>
</head>

<body>
<jsp:include page="/includes/header.jsp"></jsp:include>
<h1>在线用户</h1>
<table id="tableList1">
	<tr>
		<th colspan="5"></th>
		<th align="center"><a href="${basePath}/manage/listAll.action" >发送广播</a></th>
	</tr>
</table>
<table id="tableList" class="tablesorter" cellspacing="1">
	<thead>
		<tr>
			<th>状态</th>
			<th>用户名</th>
			<th>userId</th>
			<th>标签</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<form id="gotoListAll" action="${basePath}/manage/listSingle.action" method="post">
		<input type="hidden" name="id" id="id"/>
		<input type="hidden" name="username" id="username"/>
		<s:iterator value="userList" status="st" >
			<s:if test="online == 1">
				<tr>
					<td align="center">
						<img src="${basePath}/styles/user-online.png" />
					</td>
					<td>${username}</td>
					<td>${password}</td>
					<td>${createDate}</td>
					<td>
						<!-- <a href="${basePath}/android/notificationAction_listSingle.action?username=${username}&userId=${userId}&appTagId=${appTagId}">通知</a> -->
						<a onclick="gotoListAll('${id}','${username}');" href="#">通知</a>
					</td>
		       </tr>
		  </s:if>
		</s:iterator>
		</form>
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

function gotoListAll(userId,userName){
	document.getElementById("id").value = userId ;
	document.getElementById("username").value = userName ;
	$("#gotoListAll").submit();
	return false ;
}
//]]>

</script>
<jsp:include page="/includes/footer.jsp"></jsp:include>
</body>
</html>
