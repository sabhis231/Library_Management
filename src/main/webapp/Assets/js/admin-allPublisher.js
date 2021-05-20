/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var publisherList = [];
var selectedPublisher = -1;
var searchKey = "";
$(function () {
    __mainFunction();

    $("body").on("click", ".publisher-operation", function () {

        $("#exampleModalLabel").html("Edit Publisher");
        $("#card-model").html("Edit Publisher");
        $("#form-button-book").html("Update Publisher");


        selectedPublisher = publisherList.filter(publisher => {
            if (publisher.publisherId == $(this).attr("data-publisherId")) {
                return publisher;
            }
        });

        $("#publisherName").val(selectedPublisher[0].publisherName);
        $("#description").val(selectedPublisher[0].publisherDescription);
        $("#publisherId").val(selectedPublisher[0].publisherId)
    });


    $("body").on('submit', '#updateOrSavePublisher', function () {
        $.ajax({
            url: "../../EditOrSavePublisher",
            method: "POST",
            data: $(this).serialize(),
            success: function (result) {
                $("#hideModel").click();
                if (result.responseCode == 1) {
                    __mainFunction();
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

    $("body").on('click', '.publisher-disabled-operation', function () {
        selectedPublisher = $(this).attr("data-publisherId");

//        return false;
    });
    $("body").on('click', '#publisher-disabled', function () {

        $.ajax({
            url: "../../DisablePublisher",
            method: "POST",
            data: {publisherId: selectedPublisher},
            success: function (result) {
                $(".hideModel").click();
                if (result.responseCode == 1) {
                    fetchAllPublisher();
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




var renderData = function (bookArray) {
    var data = "";
    if (bookArray.length == 0) {
        data += "<tr>";
        data += "<td colspan='5' style='text-align: center;'>Publisher are not available to borrow</td>";
    } else {
        for (var a of bookArray) {
            data += "<tr>";
            data += "<td>" + a.publisherName + "</td>";
            data += "<td>" + a.publisherDescription + "</td>";
            data += "<td>" + trasformDate(a.modifiedOn) + "</td>";

            data += "</td>";
            data += '<td class="td-actions text-center">';
            data += '  <a class="btn btn-primary publisher-operation" data-toggle="modal" data-target="#showandedit" data-publisherId="' + a.publisherId + '">Show and Edit </a>'
            data += '</td>'
            data += '<td class="td-actions text-center">';
            data += '  <a class="btn btn-primary publisher-disabled-operation" data-toggle="modal" data-target="#disabledPublisherConfirm" data-publisherId="' + a.publisherId + '">Delete Publisher</a>'
            data += '</td>'
            data += "</tr>"
        }
    }
    $("#all-books").html(data);
    $(".current-date").html(currentDate())
}


var __mainFunction = function () {
    fetchAllPublisher();
}

var fetchAllPublisher = function () {
    $.ajax({
        url: "../../FetchAllPublisherList",
        method: "POST",
        data: {
            searchKey: searchKey
        },
        success: function (result) {
            console.log(result);
            if (result.responseCode == 1) {
                publisherList = [...result.allPublisherData];
                renderData(publisherList);
            } else
                errorElsePart();
        },
        error: function (e, t, o) {
            errorBlock();
        }
    });
};




$('.user-search').keyup(debounce(function () {
    searchKey = $(this).val();
    __mainFunction();
}, 500));
