function set_2dCodePosition() {
    if (!matchUrl()) {
        var e = $(".foot_top").parent().offset().top, o = $(document).scrollTop();
        o < e - 920 && codeKg ? ($(".twoD_barcodes_wrap").show(), $("#twoD_barcodes_wrap2").show()) : ($(".twoD_barcodes_wrap").hide(), $("#twoD_barcodes_wrap2").hide()), o < e - 920 && codeKg2 ? $("#twoD_barcodes_wrap2").show() : $("#twoD_barcodes_wrap2").hide()
    }
}
function matchUrl() {
    for (var e = window.location.href, o = ["special_detail", "article_details", "app", "pc", "topic_detail", "conc_news", "application", "download", "article", "ann", "weixin", bigdata_host], t = 0; t < o.length; t++)if (e.indexOf(o[t]) >= 0)return !0
}
function content_resize() {
    showFly()
}
function showFly() {
    var e = $(window).width(), o = $(window).height();
    e < 1312 ? ($(".open_ques").css("display", "none"), $("#toTop").css("display", "none")) : $(document).scrollTop() >= Math.round(.5 * o) ? ($(".open_ques").css("display", "block"), $("#toTop").css("display", "block")) : ($(".open_ques").css("display", "none"), $("#toTop").css("display", "none"))
}
var timer, timeout = 600;
$("#toTop").click(function () {
    $("html,body").animate({scrollTop: "0px"}, "slow")
});
var codeKg = !0, codeKg2 = !0;
$("#closeBtn").click(function () {
    codeKg = !1, $(".twoD_barcodes_wrap").hide()
}),
    $(window).scroll(function () {
    set_2dCodePosition()
}),
    showFly(),
    $(window).resize(content_resize),
    $(window).scroll(function () {
    var e = $(window).height();
    $(document).scrollTop() >= Math.round(.5 * e) ? ($(".open_ques").css("display", "block"),
        $("#toTop").css("display", "block")) : ($(".open_ques").css("display", "none"),
        $("#toTop").css("display", "none"))
});
var _hmt = _hmt || [];
!function () {
    var e = document.createElement("script");
    e.src = "//hm.baidu.com/hm.js?0290856f0dc753cd068b3e674ecfeff9";
    var o = document.getElementsByTagName("script")[0];
    o.parentNode.insertBefore(e, o);
    var t = document.createElement("script"), s = window.location.protocol.split(":")[0];
    t.src = "https" === s ? "https://zz.bdstatic.com/linksubmit/push.js" : "http://push.zhanzhang.baidu.com/push.js";
    var o = document.getElementsByTagName("script")[0];
    o.parentNode.insertBefore(t, o)
}(),
    function (e, o, t, s, n, c, a) {
    e.GoogleAnalyticsObject = n, e[n] = e[n] || function () {
        (e[n].q = e[n].q || []).push(arguments)
    },
        e[n].l = 1 * new Date, c = o.createElement(t), a = o.getElementsByTagName(t)[0], c.async = 1, c.src = "//cdn.upchinapro.com/js/analytics.js", a.parentNode.insertBefore(c, a)
}(window, document, "script", 0, "ga");
var url = window.location.href, gaId = "UA-84590325-1";
url.indexOf(bigdata_host) > -1 && (gaId = "UA-85616855-1"), ga("create", gaId, "auto"), ga("send", "pageview"), $(function () {
    $(".open_ques").click(function () {
        $(".hint").css({display: "block"}), $(".box_bg").css({display: "block"}), $(".text").show()
    }), $(".hint_btn").click(function () {
        $(this).parent().css({display: "none"}), $(".box_bg").css({display: "none"})
    }), $("#user_suggestion_submit").click(function () {
        var e = $("#user_suggestion").val(), o = $("#user_name").val(), t = $("#user_phone").val(), s = $("#user_email").val();
        e.length > 1e3 || o.length > 100 || t.length > 100 || s.length > 100 || e.length > 0 && $.ajax({
            url: "/feedback/question",
            type: "POST",
            data: {suggestion: e, userName: o, phone: t, email: s, userId: currentUser.cid || 0},
            success: function (e) {
                e.data && ($(this).addClass("active"), $(".hint input").val(""), $("#user_suggestion").val(""), $(".hint").css({display: "none"}), $(".box_bg").css({display: "none"}))
            }
        })
    }), $(".text").click(function () {
        $(".text").hide(), $(".suggest_textarea").focus(), $(".suggest_textarea").addClass("active")
    }), $(".suggest_textarea").focus(function () {
        $(".text").hide(), $(this).addClass("active")
    }), $(".suggest_textarea").blur(function () {
        $("#user_suggestion").val().length <= 0 && $(".text").show(), $(this).removeClass("active")
    }), $(".hint .info_s input").focus(function () {
        $(this).addClass("active")
    }).blur(function () {
        $(this).removeClass("active")
    }),
        $.when(loadAdvertisement).done(function () {
            var e = $("#twoD_barcodes_wrap"), o = $("#twoD_barcodes_wrap2");
            if (e.css("top")) {
                var t = $(".bg").height(), s = $(".banner").height(), n = Number(e.css("top").substring(0, 3)), c = "none" === $("#bigBanner").css("display") ? t : s;
                e.css("top", c + n + "px"), o.css("top", c + n + "px"), $(window).resize(function () {
                    var o = window.innerWidth;
                    o > 1540 ? e.css("margin-left", "-770px") : e.css("margin-left", "-" + o / 2 + "px")
                })
            }
            $("#close1").on("click", function () {
                return $(".banner").hide(), $("#twoD_barcodes_wrap").css("top", "129px"), $("#twoD_barcodes_wrap2").css("top", "129px"), !1
            }), $("#close2").on("click", function () {
                return $(".banner2").hide(), $("#twoD_barcodes_wrap").css("top", "129px"), $("#twoD_barcodes_wrap2").css("top", "129px"), !1
            }), $("#close3").on("click", function () {
                codeKg = !1, $("#twoD_barcodes_wrap").hide()
            }), $("#close4").on("click", function () {
                codeKg2 = !1, $("#twoD_barcodes_wrap2").hide()
            }), $("#closeBtn").click(function () {
                codeKg = !1, $(".twoD_barcodes_wrap").hide()
            })
        })
});