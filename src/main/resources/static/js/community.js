/**
 * 回复功能（包括回复问题、回复评论）
 */
function reply(parentId, content, type) {
    if (!content) {
        alert("回复内容不能为空~~~");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType:"application/json",
        data: JSON.stringify ({
            "parentId": parentId,
            "content": content,
            "type": type
        }),
        dataType: "json",
        success: function (data) {
            // 请求成功
            if (data.code == 200) {
                window.location.reload();
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

/**
 * 回复问题
 */
function replyQuestion() {
    var parentId = $("#parentId").val();
    var content = $("#content").val();
    reply(parentId, content, 1);
}

/**
 * 回复评论
 */
function replyComment(e) {
    var parentId = e.getAttribute("data-id");
    var content = $("#content-" + parentId).val();
    reply(parentId, content, 2);
}


/**
 * 展开二级评论
 */
function collapseComments(e) {
    var commentId = e.getAttribute("data-id");
    var comments = $("#comment-" + commentId);
    // 获取二级评论的展开状态
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        // 折叠二级评论
        e.classList.remove("active");
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
    } else {
        // 展开二级评论
        e.classList.add("active"); // 高亮评论图标
        comments.addClass("in");
        e.setAttribute("data-collapse", "in");
    }
}