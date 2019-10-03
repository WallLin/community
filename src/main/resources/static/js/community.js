/**
 * 回复问题
 */
function reply() {
    var parentId = $("#parentId").val();
    var content = $("#content").val();
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType:"application/json",
        data: JSON.stringify ({
            "parentId": parentId,
            "content": content,
            "type": 1
        }),
        dataType: "json",
        success: function (data) {
            // 请求成功
            if (data.code == 200) {
                $("#comment").hide();
            } else if (data.code == 2003) {
                // 未登录
                var isAccepted = confirm(data.message);
                if (isAccepted) { // 说明用户点击了确认按钮
                    window.open("https://github.com/login/oauth/authorize?client_id=c047774e5c90ab0d8ce3&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                    window.localStorage.setItem("closable", "true");
                }
            } else {
                alert(data.message);
            }
        }
    });
}