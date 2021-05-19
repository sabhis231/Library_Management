/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var searchKey = "";
var bookArray = [];
var selectedBook = [];
var operationId = -1;

var currentDateObject = currentDate();

$(function () {
    __mainFunction();

    $("body").on("click", ".book-operation", function () {
        operationId = $(this).attr("data-operationId");
        selectedBook = bookArray.filter(book => {
            if (book.bookId == $(this).attr("data-bookId")) {
                return book;
            }
        });
        $("#bookName").html(selectedBook[0].bookName);
        $("#publisherName").html(selectedBook[0].publishedBy);
        $("#publishedOn").html(transformYear(selectedBook[0].publishedYear));
        $("#authorName").html(selectedBook[0].bookAuthorName);
        $("#pages").html(selectedBook[0].totalPages);
        $("#pages").html(selectedBook[0].totalPages);
        $("#userDetails").html(selectedBook[0].userName + " ( " + selectedBook[0].emailId + " )");
        if (operationId == 1) {
            $(".highlight").html("Do you want to Return Book?");
        } else {
            $(".highlight").html("Do you want to Renew Book?");
        }
    });


    $("body").on("click", "#returnOrRenewBook", function () {
        console.log(selectedBook[0].bookId);
        console.log(selectedBook[0].userId);
        var index = -1
        if (selectedBook) {

            $.ajax({
                url: "../../RenewOrUpdateLoanStatus",
                method: "POST",
                data: {
                    bookId: selectedBook[0].bookId,
                    operation: operationId,
                    userId: selectedBook[0].userId,
                },
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
        }
    });
});




var renderData = function (bookArray, errorMessage, elementId) {
    var data = "";


    data += '<thead class="text-warning">';
    data += '<th>Book Name</th>';
    data += '<th>User Name</th>';
    data += '<th>Email Id</th>';
    data += '<th>Published On</th>';
    data += '<th>Borrrowed On </th>';
    data += '<th>Due On</th>';
    data += '<th colspan="2" class="text-center">Action</th>';
//        data += '<th>View Details</th>';
    data += '</thead>';
    data += '<tbody>';

    if (bookArray.length == 0) {
        data += "<tr>"
        data += "<td colspan='7' class='text-center'>" + errorMessage + "</td>";
        data += '</tr>';
    } else {
        for (var a of bookArray) {
            data += "<tr>"
            data += "<td>" + a.bookName + "</td>";
            data += "<td>" + a.userName + "</td>";
            data += "<td>" + a.emailId + "</td>";
            data += "<td>" + transformYear(a.publishedYear) + "</td>";
            data += "<td>" + trasformDate(a.borrowedOn) + "</td>";
            data += "<td>" + trasformDate(a.dueOn) + "</td>";
            data += '<td class="td-actions">';
            data += '<a class="btn btn-primary book-operation" data-toggle="modal" data-target="#exampleModal" data-bookId="' + a.bookId + '" data-userId="' + a.userId + '" data-operationId="2">Renew book</a>';
            data += '</td>';
            data += '<td class="td-actions">';
            data += '  <a class="btn btn-primary book-operation" data-toggle="modal" data-target="#exampleModal" data-bookId="' + a.bookId + '" data-userId="' + a.userId + '"  data-operationId="1">Return book </a>';
            data += '</td>';
            data += '</tr>';

        }
    }





    data += '</tbody>';

    $("#" + elementId).html(data);
    $(".current-date").html(currentDate())
}







var borrowedAllBooks = function () {
    $.ajax({
        url: "../../FetchAllBorrowedBookDetails",
        method: "POST",
        data: {
            searchKey: searchKey
        },
        success: function (result) {
            if (result.responseCode == 1) {
                bookArray = [...result.allBorrowedBook];
                renderData(bookArray, 'No one Borrowed Book', "borrowed-book");
            } else
                errorElsePart();
        },
        error: function (e, t, o) {
            errorBlock();
        }
    });
}

var __mainFunction = function () {
    borrowedAllBooks();
};

$('.user-search').keyup(debounce(function () {
    searchKey = $(this).val();
    __mainFunction()();
}, 500));

