<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>现金流水管理</title>
    <%@include file="../master/header.jsp" %>
</head>
<body>
<!--
http://www.biqugezw.com/7_7047/1574634.html
-->
<table id="list" class="easyui-datagrid"
       data-options="
			toolbar:'#tb',
			rownumbers:true,
			border:false,
			singleSelect:true,
			pagination:true,
			pageSize:10,
			url:'<%=path %>/cash/pager_criteria',
			method:'post'">
    <thead>
    <tr>
        <th data-options="field:'id',width:80,checkbox:true">编号</th>
        <th data-options="field:'money',width:100">金额</th>
        <th data-options="field:'cashCustomer',width:100">客户名称</th>
        <th data-options="field:'cashType',width:100">收支类型</th>
        <th data-options="field:'payType',width:100">支付方式</th>
        <th data-options="field:'cashTime',width:150,formatter:formatDate">支付时间</th>
        <th data-options="field:'createTime',width:150,formatter:formatDate">创建时间</th>
    </tr>
    </thead>
</table>

<div id="tb" style="height: auto">
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" onclick="openWin('addWin');">添加</a>
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit"
       onclick="openEditWin('editWin', 'list', 'editForm')">修改</a>
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove"
       onclick="del();">删除</a>
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit"
       onclick="exportExcel('/cash/export', 'searchForm')">导出Excel</a>
    <div>
        <form id="searchForm">
            <input class="easyui-numberbox easyui-validatebox" data-options="prompt:'请输入最大金额',
						required:false,
						novalidate:true,
						precision:2" name="money"/>
            <input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入客户名称',
						required:false,
						novalidate:true" name="cashCustomer"/>
            <input class="easyui-datetimebox" name="cashTime"
                   data-options="prompt:'请选择最大支付时间',required:false,novalidate:true,showSeconds:true"/>
            <input class="easyui-datetimebox" name="createTime"
                   data-options="prompt:'请选择最大创建时间',required:false,novalidate:true,showSeconds:true"/>
            <br/>
            <input class="easyui-combobox easyui-validatebox cashTypeParent" data-options="prompt:'请选择收支分类',
						required:false,
						novalidate:true" name="pid"/>
            <input class="easyui-combobox easyui-validatebox cashType" data-options="prompt:'请选择收支子分类',
						required:false,
						novalidate:true" name="cashType"/>
            <input class="easyui-combobox easyui-validatebox payType" data-options="prompt:'请选择支付方式',
						required:false,
						novalidate:true" name="payType"/>

            <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search"
               onclick="doSearch('list', 'searchForm');">搜索</a>
            <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search"
               onclick="doSearchAll('list', 'searchForm');">搜索所有</a>
        </form>
    </div>
</div>

<div id="addWin" class="easyui-window normal_win" data-options="title:'添加现金流水', closed:true">
    <form id="addForm">
        <table>
            <tr>
                <td>客户名称</td>
                <td>
                    <input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入客户名称',
						required:true,
						validType:['length[2,20]'],
						novalidate:true" name="cashCustomer"/>
                </td>
            </tr>
            <tr>
                <td>收入或支出</td>
                <td>
                    <input class="easyui-combobox easyui-validatebox cashTypeParent" data-options="prompt:'请选择收支分类',
						required:true,
						novalidate:true" name="pid"/>
                </td>
            </tr>
            <tr>
                <td>收支子分类</td>
                <td>
                    <input class="easyui-combobox easyui-validatebox cashType" data-options="prompt:'请选择收支子分类',
						required:true,
						novalidate:true" name="cashType"/>
                </td>
            </tr>
            <tr>
                <td>金额</td>
                <td>
                    <input class="easyui-numberbox easyui-validatebox" data-options="prompt:'请输入金额',
						required:true,
						validType:['length[2,20]'],
						novalidate:true,
						precision:2" name="money"/>
                </td>
            </tr>
            <tr>
                <td>支付方式</td>
                <td>
                    <input class="easyui-combobox easyui-validatebox payType" data-options="prompt:'请选择支付方式',
						required:true,
						novalidate:true" name="payType"/>
                </td>
            </tr>
            <tr>
                <td>收支时间</td>
                <td>
                    <input class="easyui-datetimebox" name="cashTime"
                           data-options="prompt:'请选择最大创建时间',required:true,novalidate:true,showSeconds:true"/>
                </td>
            </tr>
            <tr>
                <td><a class="easyui-linkbutton" onclick="save('/cash/save', 'addForm', 'addWin', 'list');">确认</a></td>
            </tr>
        </table>
    </form>
</div>

<div id="editWin" class="easyui-window normal_win" data-options="title:'编辑现金流水', closed:true">
    <form id="editForm">
        <input type="hidden" name="id"/>
        <table>
            <tr>
                <td>客户名称</td>
                <td>
                    <input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入客户名称',
						required:true,
						validType:['length[2,20]'],
						novalidate:true" name="cashCustomer"/>
                </td>
            </tr>
            <tr>
                <td>收入或支出</td>
                <td>
                    <input id="cashTypeParent" class="easyui-combobox easyui-validatebox cashTypeParent" data-options="prompt:'请选择收支分类',
						required:true,
						novalidate:true" name="pid"/>
                </td>
            </tr>
            <tr>
                <td>收支子分类</td>
                <td>
                    <input id="cashType" class="easyui-combobox easyui-validatebox cashType" data-options="prompt:'请选择收支子分类',
						required:true,
						novalidate:true" name="cashType"/>
                </td>
            </tr>
            <tr>
                <td>金额</td>
                <td>
                    <input class="easyui-numberbox easyui-validatebox" data-options="prompt:'请输入金额',
						required:true,
						validType:['length[2,20]'],
						novalidate:true,
						precision:2" name="money"/>
                </td>
            </tr>
            <tr>
                <td>支付方式</td>
                <td>
                    <input id="payType" class="easyui-combobox easyui-validatebox payType" data-options="prompt:'请选择支付方式',
						required:true,
						novalidate:true" name="payType"/>
                </td>
            </tr>
            <tr>
                <td>收支时间</td>
                <td>
                    <input class="easyui-datetimebox" name="cashTime"
                           data-options="prompt:'请选择最大创建时间',required:true,novalidate:true,showSeconds:true"/>
                </td>
            </tr>
            <tr>
                <td><a class="easyui-linkbutton" onclick="edit('/cash/update', 'editForm', 'editWin', 'list');">确认</a>
                </td>
            </tr>
        </table>
    </form>
</div>

</body>
<%@include file="../master/footer.jsp" %>

<script>
    //删除数据
    function del() {
        var row = $('#list').datagrid('getSelected');
        if (row) {
            $.messager.confirm("系统提示", "您确定要删除这条记录吗?", function (r) {
                if (r) {
                    $.post(contextPath + '/cash/delete/' + row.id, function (data) {
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


    $(function () {
        setPagination("list");
        $(".cashTypeParent").combobox(
            {
                url: contextPath + '/cash_type/get/0',
                valueField: 'id',
                textField: 'text',
                onSelect: function (record) {
                    $(".cashType").combobox(
                        {
                            url: contextPath + '/cash_type/get/' + record.id,
                            valueField: 'id',
                            textField: 'text'
                        }
                    );
                }
            }
        );

        $(".payType").combobox(
            {
                url: contextPath + '/pay_type/all',
                valueField: 'id',
                textField: 'text'
            }
        );
    });

    function openEditWin(winId, listId, formId) {
        var row = $("#" + listId).datagrid("getSelected");
        if (row) {
            row.cashTime = formatDate(row.cashTime);
            $("#" + formId).form("load", row); // 考虑时间字符串
            $("#cashTypeParent").combobox("select", row.cashTypePid);
            $("#cashType").combobox("select", row.cashTypeId);
            $("#payType").combobox("select", row.payTypeId);
            openWin(winId);
        } else {
            showInfoAlert("请选择需要修改的数据");
        }
    }

    function exportExcel(url, formId) {
        var form = $("#" + formId);
        form.attr('action', contextPath + url);
        form.submit();
    }

</script>

</html>