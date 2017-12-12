<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    String path = request.getContextPath();
    %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>日志管理</title>
	<%@include file="../master/header.jsp" %>
</head>
<body>
	<table id="list" class="easyui-datagrid"
		data-options="
			toolbar:'#tb',
			rownumbers:true,
			border:false,
			singleSelect:true,
			pagination:true,
			pageSize:10,
			url:'<%=path %>/login_log/pager_criteria',
			method:'post'">
		<thead>
			<tr>
				<th data-options="field:'id',width:80,checkbox:true">编号</th>
				<th data-options="field:'realname',width:100">用户姓名</th>
				<th data-options="field:'phone',width:100">手机号码</th>
				<th data-options="field:'loginTime',width:150,formatter:formatDate">登录时间</th>
				<th data-options="field:'loginIp',width:100">登录IP</th>
				<th data-options="field:'isOnline',width:100,formatter:formatLoginStatus">登录状态</th>
				<th data-options="field:'logoutTime',width:150,formatter:formatDate">退出时间</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="height: auto">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove"
		   onclick="del();">删除</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit"
		   onclick="exportExcel('/login_log/export', 'searchForm')">导出Excel</a>
		<div>
			<form id="searchForm">
				<input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入用户姓名',
						required:false,
						novalidate:true" name="realname"/>
				<input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入手机号',
						required:false,
						novalidate:true" name="phone"/>
				<input class="easyui-datetimebox easyui-validatebox" data-options="prompt:'请输入最大登录时间',
						required:false,
						novalidate:true" name="loginTime"/>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch('list', 'searchForm');">搜索</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearchAll('list', 'searchForm');">搜索所有</a>
			</form>
		</div>
	</div>
</body>
<%@include file="../master/footer.jsp" %>

<script>
	$(function() {
        setPagination("list");
    });

	function formatLoginStatus(value, row, index) {
		return value === 0 ? "离线" : "在线";
    }

    function exportExcel(url, formId) {
        var form = $("#" + formId);
        form.attr('action', contextPath + url);
        form.submit();
    }

    //删除数据
    function del() {
        var row = $('#list').datagrid('getSelected');
        if (row) {
            $.messager.confirm("系统提示", "您确定要删除这条记录吗?", function (r) {
                if (r) {
                    $.post(contextPath + '/login_log/delete/' + row.id, function (data) {
                        if (data.result === "ok") {
                            $.messager.alert("系统提示", "已成功删除这条记录!");
                            $("#list").datagrid("reload");
                        } else {
                            $.messager.alert("系统提示", data.message);
                        }
                    }, 'json');
                }
            });
        } else {
            showInfoAlert("请选择要删除的行");
        }
    }
</script>

</html>