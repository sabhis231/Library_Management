/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var allUser = [];
var selectedUser;
var searchKey = "";

var currentDateObject = currentDate();

$(function () {
    __mainFunction();

    $("body").on("click", ".user-operation", function () {

        $("#exampleModalLabel").html("Edit User");
        $("#card-model").html("Edit User");
        $("#form-button-book").html("Update User");


        selectedUser = allUser.filter(user => {
            if (user.userId == $(this).attr("data-userId")) {
                return user;
            }
        });
        console.log(selectedUser);

        $("#userId").val(selectedUser[0].userId);
        $("#userName").val(selectedUser[0].name);
        $("#email").val(selectedUser[0].emailId)
    });


    $("body").on('submit', '#updateOrSaveUser', function () {
        console.log($(this).serialize())
        $.ajax({
            url: "../../EditOrSaveUser",
            method: "POST",
            data: $(this).serialize(),
            success: function (result) {
                $("#hideModel").click();
                if (result.responseCode == 1) {
                    returnedUserList();
                    templateNotification(result.msg, 'info');

                } else
                    errorElsePart();
            },
            error: function (e, t, o) {
                errorBlock();
            }
        });
        return false;
    });

    $("body").on('click', '.user-disabled-operation', function () {
        selectedUser = $(this).attr("data-userId");

//        return false;
    });
    $("body").on('click', '#user-disabled', function () {

        $.ajax({
            url: "../../DisableUser",
            method: "POST",
            data: {userId: selectedUser},
            success: function (result) {
                $(".hideModel").click();
                if (result.responseCode == 1) {
                    returnedUserList();
                    templateNotification(result.msg, 'info');

                } else
                    errorElsePart();
            },
            error: function (e, t, o) {
                errorBlock();
            }
        });
        return false;
    });


    $("body").on('click', '#user-reset', function () {

        $.ajax({
            url: "../../ResetPassword",
            method: "POST",
            data: {userId: selectedUser},
            success: function (result) {
                $(".hideModel").click();
                if (result.responseCode == 1) {
                    returnedUserList();
                    templateNotification(result.msg, 'info');

                } else
                    errorElsePart();
            },
            error: function (e, t, o) {
                errorBlock();
            }
        });
        return false;
    });


    $("body").on("click", ".add-publisher", function () {
        $("#exampleModalLabel").html("Add New Publisher");
        $("#card-model").html("Add New Publisher");
        $("#form-button-book").html("Add Publisher");
        $("#publisherName").val('');
        $("#description").val('');
        $("#publisherId").val(0)
    });


});
var renderData = function (bookArray, errorMessage, elementId) {
    var data = "";


    data += '<thead class="text-warning">';
    data += '<th>Name</th>';
    data += '<th>Email Id</th>';

    data += '<th colspan="2" style="text-align:center" >Action</th>';
    data += '</thead>';
    data += '<tbody>';

    if (bookArray.length == 0) {
        data += "<tr>"
        data += "<td colspan='3' class='text-center'>" + errorMessage + "</td>";
        data += '</tr>';
    } else {
        for (var a of bookArray) {
            data += "<tr>"
            data += "<td>" + a.name + "</td>";
            data += "<td>" + a.emailId + "</td>";


            data += '<td class="td-actions text-center">';
            data += '  <a class="btn btn-primary user-disabled-operation" data-toggle="modal" data-target="#disabledUserConfirm" data-userId="' + a.userId + '">Delete User</a>'
            data += '</td>'
            data += '<td class="td-actions text-center">';
            data += '  <a class="btn btn-primary user-disabled-operation" data-toggle="modal" data-target="#resetPassword" data-userId="' + a.userId + '">Reset password </a>'
            data += '</td>'



            data += '</tr>';
        }
    }
    data += '</tbody>';

    $("#" + elementId).html(data);
    $(".current-date").html(currentDate())
}







var returnedUserList = function () {
    $.ajax({
        url: "../../FetchUserList",
        method: "POST",
        data: {
            searchKey: searchKey
        },
        success: function (result) {
            if (result.responseCode == 1) {
                allUser = [...result.allUserList];
                renderData(allUser, 'Please add user', "user-list");
            } else
                errorElsePart();
        },
        error: function (e, t, o) {
            errorBlock();
        }
    });
}

var __mainFunction = function () {
    returnedUserList();
};



$('.user-search').keyup(debounce(function () {
    searchKey = $(this).val();
    __mainFunction();
}, 500));