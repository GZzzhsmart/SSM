function login() {
    $.post(contextPath + '/user/login',
        $('#loginForm').serialize(),
        function (data) {
            if (data.result === 'ok' || data.result === 'logined') {
                window.location.href = contextPath + "/user/home";
            } else {
                showInfoAlert(data.message);
            }
        },
        'json'
    );
}


function reset() {
    $('#loginForm').form("reset");
}