<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    String path = request.getContextPath();
    %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页</title>
	<%@include file="master/header.jsp" %>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height: 60px;">
		<div id="logo"><img src="<%=path %>/static/images/girl.jpg" /></div>
		<div id="wel_msg">欢迎您：${sessionScope.user.realname}&nbsp;&nbsp;
			<a href="<%=path %>/user/logout">安全退出</a>
		</div>
		<p class="clear"></p>
	</div>
	
	<div data-options="region:'south',split:true" style="height: 50px;">Copyright&copy;WGS</div>
	<div data-options="region:'west',split:true" title="主菜单" style="width: 250px;">
		<div class="easyui-accordion">
			<div title="现金流水管理" data-options="iconCls:'icon-ok'" style="padding: 10px;">
				<ul>
					<li><a href="javascript:void(0);" src="<%=path%>/cash/page"
						class="site-navi-tab">所有流水</a></li>
				</ul>
			</div>
			<div title="用户管理" data-options="iconCls:'icon-ok'" style="padding: 10px;">
				<ul>
					<li><a href="javascript:void(0);" src="<%=path%>/user/page"
						   class="site-navi-tab">用户管理</a></li>
				</ul>
			</div>
			<div title="日志管理" data-options="iconCls:'icon-ok'" style="padding: 10px;">
				<ul>
					<li><a href="javascript:void(0);" src="<%=path%>/login_log/page"
						   class="site-navi-tab">登录日志</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="tabs" class="easyui-tabs" data-options="region:'center'">
			<div title="主页" style="padding: 10px">
				<div class="easyui-panel my-panel" title="系统状态">
					系统状态
				</div>
				<div class="easyui-panel my-panel" title="待办任务">
					待办任务
				</div>
			</div>
		</div>
		
		<div id="mm" class="easyui-menu">
		    <div id="mm-tabupdate">刷新</div>
		    <div class="menu-sep"></div>
		    <div id="mm-tabclose">关闭</div>
		    <div id="mm-tabcloseother">关闭其他</div>
		    <div id="mm-tabcloseall">关闭全部</div>
		</div>
		
</body>
<%@include file="master/footer.jsp" %>
</html>