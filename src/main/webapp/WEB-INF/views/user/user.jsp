<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>用户管理</title>
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
			url:'<%=path %>/user/pager_criteria',
			method:'post'">
    <thead>
    <tr>
        <th data-options="field:'id',width:80,checkbox:true">编号</th>
        <th data-options="field:'phone',width:100">电话号码</th>
        <th data-options="field:'realname',width:100">真实姓名</th>
        <th data-options="field:'regTime',width:150,formatter:formatDate">创建时间</th>
    </tr>
    </thead>
</table>

<div id="tb" style="height: auto">
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" onclick="openWin('addWin');">添加</a>
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" onclick="del();">删除</a>
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" onclick="openEditWin('editWin', 'list', 'editForm')">修改</a>
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" onclick="showUpdatePwd();">修改密码</a>
</div>

<div id="addWin" class="easyui-window normal_win" data-options="title:'注册用户', closed:true">
    <form id="addForm">
        <table>
            <tr>
                <td>手机号码</td>
                <td>
                    <input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入手机号',
						required:true,
						validType:['length[11,11]'],
						novalidate:true" name="phone"/>
                </td>
            </tr>
            <tr>
                <td>密码</td>
                <td>
                    <input type="password" class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入密码',
						required:true,
						validType:['length[2,20]'],
						novalidate:true" name="pwd"/>
                </td>
            </tr>
            <tr>
                <td>真实姓名</td>
                <td>
                    <input  class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入真实姓名',
						required:true,
						validType:['length[2,20]'],
						novalidate:true" name="realname"/>
                </td>
            </tr>
            <tr>
                <td>创建时间</td>
                <td>
                    <input class="easyui-datetimebox" name="regTime"
                           data-options="prompt:'请选择创建时间',required:true,novalidate:true,showSeconds:true"/>
                </td>
            </tr>
            <tr>
                <td><a class="easyui-linkbutton" onclick="save('/user/save', 'addForm', 'addWin', 'list');">确认</a></td>
            </tr>
        </table>
    </form>
</div>

<div id="editWin" class="easyui-window normal_win" data-options="title:'编辑用户', closed:true">
    <form id="editForm">
        <input type="hidden" name="id" />
        <table>
            <tr>
                <td>手机号码</td>
                <td>
                    <input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入手机号',
						required:true,
						validType:['length[11,11]'],
						novalidate:true" name="phone"/>
                </td>
            </tr>
            <tr>
                <td>真实姓名</td>
                <td>
                    <input  class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入真实姓名',
						required:true,
						validType:['length[2,20]'],
						novalidate:true" name="realname"/>
                </td>
            </tr>
            <tr>
                <td><a class="easyui-linkbutton" onclick="edit('/user/update', 'editForm', 'editWin', 'list');">确认</a></td>
            </tr>
        </table>
    </form>
</div>

<div id="editPwdWin" class="easyui-window site_win_small input_big"  data-options="title:'编辑用户密码', closed:true">
    <form id="editPwdForm" method="post">
        <input type="hidden" name="id" />
        <table>
            <tr>
                <td>旧密码</td>
                <td>
                    <input type="password" class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入密码',
						required:true,
						validType:['length[2,20]'],
						novalidate:true" name="oldpwd"/>
                </td>
            </tr>
            <tr>
                <td>新密码</td>
                <td>
                    <input  type="password" class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入新密码',
						required:true,
						validType:['length[2,20]'],
						novalidate:true"  name="newPwd" />
                </td>
            </tr>
            <tr>
                <td>确认密码</td>
                <td>
                    <input type="password" class="easyui-textbox easyui-validatebox" data-options="prompt:'请再次输入密码',
						required:true,
						validType:['length[2,20]'],
                        novalidate:true"   name="conPwd"/>
                </td>
            </tr>
            <tr>
                <td><a class="easyui-linkbutton" onclick="updatePwd();">确认</a></td>
            </tr>
        </table>
    </form>
</div>

</body>
<%@include file="../master/footer.jsp" %>

<script>


    //修改数据
    function openEditWin(winId, listId, formId) {
        var row = $("#" + listId).datagrid("getSelected");
        if (row) {
            row.regTime = formatDate(row.regTime);
            $("#" + formId).form("load", row); // 考虑时间字符串
            openWin(winId);
        } else {
            showInfoAlert("请选择需要修改的数据");
        }
    }

    //删除数据
    function del(){
        var row=$('#list').datagrid('getSelected');
        if(row){
            $.messager.confirm("系统提示","您确定要删除这条记录吗?",function(r){
                if(r){
                    $.post(contextPath+'/user/delete/'+row.id,function(data){
                        if(data.result==="ok"){
                            $.messager.alert("系统提示","已成功删除这条记录!");
                            $("#list").datagrid("reload");
                        }else{
                            $.messager.alert("系统提示",data.message);
                        }
                    },'json');
                }
            });
        }else{
            showInfoAlert("请选择要删除的行");
        }
    }

    //修改密码

    function showUpdatePwd() {
        var row = $('#list').datagrid('getSelected');
        if (row) {
            $("#editPwdForm").form("load", row);
            $("#newPwd").textbox("setValue", "");
            openWin("editPwdWin");
        } else {
            showInfoAlert("请选择要进行修改密码的行数")
        }
    }

    function updatePwd() {
        if (validateForm("editPwdForm")) {
            $.messager.confirm("提示", "更新该客户密码，是否继续?", function(r) {
                if (r) {
                    $.post(contextPath + "/user/updatePwd",
                        $('#editPwdForm').serialize(),
                        function (data) {
                            if (data.result === "ok") {
                                closeWin("editPwdWin");
                                $("#list").datagrid("reload");
                            }else {
                                showInfoAlert(data.message);
                            }
                        },
                        "json"
                    );
                }
            });
        }
    }
</script>

</html>
