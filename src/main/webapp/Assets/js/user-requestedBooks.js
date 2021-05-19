/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function () {
    var bookArray = [];
    var selectedBook = [];
    $.ajax({
        url: "../../FetchAllRequestedBook",
        method: "POST",
        success: function (result) {
            console.log(result);
            if (result.responseCode == 1) {
                bookArray = [...result.allRequestedBook];
                renderData(bookArray);
            } else
                errorElsePart();
        },
        error: function (e, t, o) {
            errorBlock();
        }
    });


    $("body").on("click", ".book-operation", function () {
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
    });

    $("body").on("click", "#cancelRequest", function () {
        var index = -1
        if (selectedBook) {
            $.ajax({
                url: "../../BookOperation",
                method: "POST",
                data: {
                    bookId: selectedBook[0].bookId,
                    operation: 4
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
        }
    });
});

var renderData = function (bookArray) {
    var data = "";
    if (bookArray.length == 0) {
        data += "<tr>";
        data += "<td colspan='6' style='text-align: center;'>No Book to in request list</td>";
    } else {
        for (var a of bookArray) {
            data += "<tr>";
            data += "<td>" + a.bookName + "</td>";
            data += "<td>" + a.publishedBy + "</td>";
            data += "<td>" + transformYear(a.publishedYear) + "</td>";
            data += "<td>" + a.totalPages + "</td>";
            data += "<td>" + trasformDate(a.requestedOn) + "</td>";
            data += '<td class="td-actions">';
            data += '  <a class="btn btn-primary book-operation" data-toggle="modal" data-target="#exampleModal" data-bookId="' + a.bookId + '">Cancel Request</a>'
            data += '</td>'
            data += "</tr>"
        }
    }
    $("#all-books").html(data);
    $(".current-date").html(currentDate())
}

