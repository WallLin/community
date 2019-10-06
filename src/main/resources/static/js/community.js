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
        // 获取二级评论内容容器
        var subCommentContainer = $("#comment-" + commentId);
        // 判断二级评论容器内容是否大于1，大于1说明已经获取到数据，避免重复请求数据
        if (subCommentContainer.children().length != 1) {
            // 展开二级评论
            e.classList.add("active"); // 高亮评论图标
            comments.addClass("in");
            // 标记二级评论展开状态
            e.setAttribute("data-collapse", "in");
        } else {
            $.getJSON("/comment/" + commentId, function (data) {
                // JS DOM 拼接
                $.each(data.data.reverse(), function (index, comment) {

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "class": "menu"
                    }).append($("<h5/>", {
                        "class": "media-heading comment-content",
                        "html": comment.content
                    })).append($("<span/>", {
                        "class": "pull-right datetime",
                        "html": moment(comment.gmtCreated).format('YYYY-MM-DD')
                    })));

                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object media-object-community",
                        "src": comment.user.avatarUrl
                    }));

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 single"
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                });
            });
            // 展开二级评论
            e.classList.add("active"); // 高亮评论图标
            comments.addClass("in");
            // 标记二级评论展开状态
            e.setAttribute("data-collapse", "in");
        }
    }
}