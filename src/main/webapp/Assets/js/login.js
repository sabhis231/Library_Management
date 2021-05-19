
var contextPath = null;
var isSingUp = false;
//$("#confimPassword").hide();
$(function () {
    contextPath = $("[name=contextPath]").val();
    $("#login").submit(function () {
        $(".login-error").html("");
        $(".login-error").removeClass("login-success");

        $(".panel-sign-in").addClass("hidden"), $(".panel-sign-in-loader").removeClass("hidden");
        var ts = $(this);
        if (isSingUp) {
            console.log("Sing Up Form ");
            $.ajax({
                url: "DoRegister",
                type: "POST",
                data: ts.serialize(),
                success: function (res) {
                    console.log(res);
                    if (1 === res.responseCode) {
                        $(".login-error").html(res.msg);
                        $(".login-error").addClass("login-success");
                        $("#button2").click();
//                    $.notify("Redirecting", "success");
//                        window.location.href = contextPath + "/views/" + res.role + "/index.jsp";
                    } else {
                        $(".login-error").html(res.msg);
////                    $.notify("Invalid Credentials", "error");
//                        $(".panel-sign-in").removeClass("hidden");
//                        $(".panel-sign-in-loader").addClass("hidden");
//                        $(".login-error").html("Invalid Credentials");

                    }
                }
            });
        } else {
            var ts = $(this);
            console.log("Login   Form ");
            console.log(ts.serialize());
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
        }

        return false;
    });

    $("#button2").click(function () {
        if (!isSingUp) {
            $("#title").html("SignUp to Library");
            $("#button2").html("Already have Account");
            $("#button1").html("SingIn");
            $("#confimPassword").removeClass('hidden');
        } else {
            $("#title").html("LogIn to Library");
            $("#button2").html("Create a Account");
            $("#button1").html("LogIn");
            $("#confimPassword").addClass('hidden');
        }

        isSingUp = !isSingUp;

    });

    $("#button1").click(function () {

    });
});