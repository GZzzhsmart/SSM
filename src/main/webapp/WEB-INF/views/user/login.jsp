<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>登录</title>
    <%@include file="../master/header.jsp"%>
</head>
<body>
<div style="position: absolute;width: 100%;height: 100%;z-index: -1;left: 0;top: 0">
    <img src="<%=path%>/static/images/login_bg.jpg" width="100%" height="100%" style="left: 0;top: 0;">
</div>
<div class="easyui-window" title="用户登录页面" data-options="modal:true,closable:false,collapsible:false,minimizable:false,maximizable:false" style="width: 400px;height: 280px;padding: 10px">
    <form id="loginForm" >
        <table cellpadding="6px" align="center">
            <tr align="center">
                <th colspan="2" style="padding-bottom: 10px"><big>用户登录</big></th>
            </tr>
            <tr>
                <td>手机号码：</td>
                <td>
                    <input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入手机号',
						required:true,
						validType:['length[11,11]'],
						novalidate:true" name="phone"/>
                </td>
            </tr>
            <tr>
                <td>密码：</td>
                <td>
                    <input type="password" class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入密码',
						required:true,
						validType:['length[2,20]'],
						novalidate:true" name="pwd"/>
                </td>
            </tr>
            <tr>
                <td>验证码：</td>
                <td>
                    <input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入验证码',
						required:true,
						validType:['length[2，20]'],
						novalidate:true" name="code"/>
                    <img src="<%=path%>/code" onclick="this.src='<%=path%>/code?r=' + Math.random();"/>
                </td>
            </tr>
            <tr>
                <td colspan="2"></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <a class="easyui-linkbutton" onclick="login();">登录</a>
                    <a class="easyui-linkbutton" onclick="reset();">重置</a>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
<%@ include file="../master/footer.jsp"%>
<script src="<%=path %>/static/js/user/user.js"></script>
</html>
