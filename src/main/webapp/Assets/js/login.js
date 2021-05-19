
var contextPath = null;
$(function () {
    contextPath = $("[name=contextPath]").val();
    $("#login").submit(function () {
        $(".panel-sign-in").addClass("hidden"), $(".panel-sign-in-loader").removeClass("hidden");
        var ts = $(this);

        $.ajax({
            url: "DoAuth",
            type: "POST",
            data: ts.serialize(),
            success: function (res) {
                console.log(res);
                if (1 === res.responseCode && !0 === res.doauth) {
//                    $.notify("Redirecting", "success");
                    window.location.href = contextPath + "/views/" + res.role + "/index.jsp";
                } else {
//                    $.notify("Invalid Credentials", "error");
                    $(".panel-sign-in").removeClass("hidden");
                    $(".panel-sign-in-loader").addClass("hidden");
                    $(".login-error").html("Invalid Credentials");

                }
            }
        });
        return false;
    });
});