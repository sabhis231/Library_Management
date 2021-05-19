/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var authorList = [];
var selectedAuthor = -1;
var searchKey = "";
$(function () {
    __mainFunction();

    $("body").on("click", ".author-operation", function () {

        $("#exampleModalLabel").html("Edit Author");
        $("#card-model").html("Edit Author");
        $("#form-button-book").html("Edit Author");
        $(".button-submit").html("Update Author");


        selectedAuthor = authorList.filter(author => {
            if (author.authorId == $(this).attr("data-authorId")) {
                return author;
            }
        });

        $("#authorName").val(selectedAuthor[0].authorName);
        $("#description").val(selectedAuthor[0].publisherDescription);
        $("#authorId").val(selectedAuthor[0].authorId)
    });


    $("body").on('submit', '#updateOrSaveAuthor', function () {
        console.log($(this).serialize());
        $.ajax({
            url: "../../EditOrSaveAuthor",
            method: "POST",
            data: $(this).serialize(),
            success: function (result) {
                $("#hideModel").click();
                if (result.responseCode == 1) {
                    fetchAllAuthor();
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

    $("body").on('click', '.author-disabled-operation', function () {
        selectedAuthor = $(this).attr("data-authorId");
        console.log(selectedAuthor);

    });
    $("body").on('click', '#author-disabled', function () {

        $.ajax({
            url: "../../DisableAuthor",
            method: "POST",
            data: {authorId: selectedAuthor},
            success: function (result) {
                $(".hideModel").click();
                if (result.responseCode == 1) {
                    fetchAllAuthor();
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

    $("body").on("click", ".add-author", function () {
        $("#exampleModalLabel").html("Add New Author");
        $("#card-model").html("Add New Author");
        $("#form-button-book").html("Add Author");
        $(".button-submit").html("Add Author");
        $("#authorName").val('');
        $("#description").val('');
        $("#authorId").val(0)
    });
});




var renderData = function (arrayData) {
    var data = "";
    if (arrayData.length == 0) {
        data += "<tr>";
        data += "<td colspan='5' style='text-align: center;'>Please add author</td>";
    } else {
        for (var a of arrayData) {
            data += "<tr>";
            data += "<td>" + a.authorName + "</td>";
            data += "<td>" + a.authorDescription + "</td>";
            data += "<td>" + trasformDate(a.modifiedOn) + "</td>";

            data += "</td>";
            data += '<td class="td-actions text-center">';
            data += '  <a class="btn btn-primary author-operation" data-toggle="modal" data-target="#showandedit" data-authorId="' + a.authorId + '">Show and Edit </a>'
            data += '</td>'
            data += '<td class="td-actions text-center">';
            data += '  <a class="btn btn-primary author-disabled-operation" data-toggle="modal" data-target="#disabledAuthorConfirm" data-authorId="' + a.authorId + '">Delete Publisher</a>'
            data += '</td>'
            data += "</tr>"
        }
    }
    $("#all-author").html(data);
    $(".current-date").html(currentDate())
}


var __mainFunction = function () {
    fetchAllAuthor();
}

var fetchAllAuthor = function () {
    $.ajax({
        url: "../../FetchAllAuthorList",
        method: "POST",
        data: {
            searchKey: searchKey
        },
        success: function (result) {
            if (result.responseCode == 1) {
                authorList = [...result.allAuthorData];
                renderData(authorList);
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


