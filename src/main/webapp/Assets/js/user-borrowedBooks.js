/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var bookArray = [];
var selectedBook = [];
var operationId = -1;
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
        if (operationId == 1) {
            $(".highlight").html("Do you want to Return Book?");
        } else {
            $(".highlight").html("Do you want to Renew Book?");
        }
    });

    $("body").on("click", "#returnOrRenewBook", function () {
        var index = -1
        if (selectedBook) {
            if (operationId == 1) {
                $.ajax({
                    url: "../../BookOperation",
                    method: "POST",
                    data: {
                        bookId: selectedBook[0].bookId,
                        operation: 2,
                    },
                    success: function (result) {
                        $("#hideModel").click();
                        if (result.responseCode == 1) {

                            bookArray.find((data, i) => {
                                if (data.bookId == selectedBook[0].bookId) {
                                    index = i;
                                    return i;
                                }
                            });
                            bookArray.splice(index, 1);
                            renderData(bookArray);
                            templateNotification(result.msg, 'info');

                        } else
                            errorElsePart();
                    },
                    error: function (e, t, o) {
                        errorBlock();
                    }
                });
            } else if (operationId == 2) {
                $.ajax({
                    url: "../../RenewBook",
                    method: "POST",
                    data: {
                        bookId: selectedBook[0].bookId,
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
        }
    });
});

var renderData = function (bookArray) {
    var data = "";
    if (bookArray.length == 0) {
        data += "<tr>";
        data += "<td colspan='7' style='text-align: center;'>No Books borrowed By you</td>";
    } else {
        for (var a of bookArray) {

            data += "<tr>";
            data += "<td>" + a.bookName + "</td>";
            data += "<td>" + transformYear(a.publishedBy) + "</td>";
            data += "<td>" + a.publishedYear + "</td>";
            data += "<td>" + a.totalPages + "</td>";
            data += "<td>" + trasformDate(a.borrowedOn) + "</td>";
            data += "<td>" + trasformDate(a.dueOn) + "</td>";
            data += '<td class="td-actions">';
            data += '  <a class="btn btn-primary book-operation" data-toggle="modal" data-target="#exampleModal" data-bookId="' + a.bookId + '" data-operationId="1">Return book</a>';
            data += '</td>';

            if ((new Date(a.dueOn).getTime() - new Date().getTime()) < (24 * 60 * 60 * 1000)) {
                data += '<td class="td-actions">';
                data += '  <a class="btn btn-primary book-operation" data-toggle="modal" data-target="#exampleModal" data-bookId="' + a.bookId + '" data-operationId="2">Renew book</a>';
                data += '</td>';
            }
            
            data += "</tr>";
        }
    }
    $("#all-books").html(data);
    $(".current-date").html(currentDate());
};


var __mainFunction = function () {
    fetchAllBorrowedBook();
}
var fetchAllBorrowedBook = function () {
    $.ajax({
        url: "../../FetchAllBorrowedBook",
        method: "POST",
        success: function (result) {
            console.log(result);
            if (result.responseCode == 1) {
                bookArray = [...result.allBorrowedBook];
                renderData(bookArray);
            } else
                errorElsePart();
        },
        error: function (e, t, o) {
            errorBlock();
        }
    });
}
