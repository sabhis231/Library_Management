/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var borrowedBook = [];
var requestedBook = [];
var allBook = [];
var selectedBook = [];
var authorList = [];
var publisherList = [];
var searchKey = "";

var currentDateObject = currentDate();

$(function () {
    __mainFunction();

    $('body').on('click', '.approve-book', function () {

        $.ajax({
            url: "../../ApproveBorrowBook",
            method: "POST",
            data: {
                bookId: $(this).attr("data-bookId"),
                userId: $(this).attr("data-userId")
            },
            success: function (result) {
                if (result.responseCode == 1) {
                    templateNotification(result.msg, "info");
                    __mainFunction();
                } else if (result.responseCode == 2) {
                    templateNotification(result.msg, "info");
                } else
                    errorElsePart();

            },
            error: function (e, t, o) {
                errorBlock();
            }
        });
    });

})




var renderData = function (bookArray, errorMessage, elementId) {
    var data = "";

    data += '<thead class="text-warning">';
    data += '<th>Book Name</th>';
    data += '<th>User Name</th>';
    data += '<th>Email Id</th>';
    data += '<th>Published On</th>';
    data += '<th>Requested On </th>';
    data += '<th>Approve</th>';
    data += '</thead>';
    data += '<tbody>';


    if (bookArray.length == 0) {
        data += "<tr>"
        data += "<td colspan='6' class='text-center'>" + errorMessage + "</td>";
        data += '</tr>';
    } else {
        for (var a of bookArray) {
            data += "<tr>"
            data += "<td>" + a.bookName + "</td>";
            data += "<td>" + a.userName + "</td>";
            data += "<td>" + a.userId + "</td>";
            data += "<td>" + transformYear(a.publishedYear) + "</td>";
            data += "<td>" + trasformDate(a.requestedOn) + "</td>";
            data += '<td class="td-actions">';
            data += '  <a class="btn btn-primary approve-book" data-userId="' + a.userId + '" data-bookId="' + a.bookId + '">Approve</a>';
            data += '</td>';
            data += '</tr>';
        }
    }





    data += '</tbody>';

    $("#" + elementId).html(data);
    $(".current-date").html(currentDate())
}






var requestAllBook = function () {
    $.ajax({
        url: "../../FetchAllRequestedBookDetails",
        method: "POST",
        data: {
            searchKey: searchKey
        },
        success: function (result) {
            if (result.responseCode == 1) {
                requestedBook = [...result.allRequestedBook];
                renderData(requestedBook, 'No one Request Book', "requested-book");
            } else
                errorElsePart();
        },
        error: function (e, t, o) {
            errorBlock();
        }
    });
}

var __mainFunction = function () {
    requestAllBook();
};

$('.user-search').keyup(debounce(function () {
    searchKey = $(this).val();
    __mainFunction()();
}, 500));
