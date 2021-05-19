/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    $.ajax({
        url: "../../FetchUserProfile",
        method: "GET",
        success: function (result) {
            console.log(result)
            if (result.responseCode == 1) {
                var userData = result.user;
                $("#about").val(userData.about)
                $("#city").val(userData.city)
                $("#country").val(userData.country)
                $("#emailId").val(userData.emailId)
                $("#name").val(userData.name);

                $("#zipCode").val(userData.zipCode)
                $("#gender").val(userData.gender);
                $("#dob").val(userData.dob);

                $("#userName").html(userData.name);
                $("#aboutDesc").html(userData.about);


            } else {
                errorElsePart();
            }
        }, error: function (error) {
            errorBlock();
        }

    });

    $("#updateProfile").submit(function () {
        console.log($(this).serialize());
        var ts = $(this);
        $.ajax({
            url: "../../UpdateProfile",
            type: "POST",
            data: ts.serialize(),
            success: function (result) {
                if (result.responseCode == 1) {

                    $("#aboutDesc").html($("#about").val());
                    $("#userName").html($("#name").val());
                    DataSaved();
                } else {
                    errorElsePart();
                }
            },
            error: function (error) {
                errorBlock();
            }
        })
        return false;

    });

    $("#deactiveButton").click(function () {
        $.ajax({
            url: "../../DeactivateAccount",
            type: "GET",
            success: function (result) {
                $("#hideModel").click();
                if (result.responseCode == 1) {
                    window.location.href = result.path;
                } else {
                    templateNotification(result.msg, 'info');
                }
            },
            error: function (error) {
                errorBlock();
            }
        })
        return false;
    });
    $("#name").val();
    $("#name").focus();
});