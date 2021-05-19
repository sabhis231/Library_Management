var borrowedBook = [];
var requestedBook = [];
var allBook = [];
var selectedBook = [];
var currentDateObject = currentDate();
var topDataArray = [
//        {
//            icons: 'content_copy',
//            name: 'Total Books',
//            data: 'totalBookAllot',
//            class: 'card-header-success'
//        },
    {
        icons: 'store',
        name: 'Borrowed Book',
        data: 'totalBorrowedBook',
        class: 'card-header-danger'
    },
    {
        icons: 'info_outline',
        name: 'Returned Book',
        data: 'totalReturnedBook',
        class: 'card-header-info'
    },
    {
        icons: 'content_copy',
        name: 'Requested Book',
        data: 'totalRequestedBook',
        class: 'card-header-warning'
    }];

$(function () {

    __mainFunction();

    $(".current-date").html(currentDateObject);

    $("body").on('click', '.book-operation', function () {

        var selectedBook = [];

        if ($(this).attr("data-operationId") == 1) {

        } else if ($(this).attr("data-operationId") == 2) {

            selectedBook = borrowedBook.filter(book => {
                if (book.bookId == $(this).attr("data-bookId")) {
                    return book;
                }
            });

            borrowedBook.find((data, i) => {
                if (data.bookId == selectedBook[0].bookId) {
                    index = i;
                    return i;
                }
            });
            borrowedBook.splice(index, 1);
            allBook.push(selectedBook[0]);

        } else if ($(this).attr("data-operationId") == 3) {

            selectedBook = allBook.filter(book => {
                if (book.bookId == $(this).attr("data-bookId")) {
                    return book;
                }
            });

            allBook.find((data, i) => {
                if (data.bookId == selectedBook[0].bookId) {
                    index = i;
                    return i;
                }
            });
            allBook.splice(index, 1);
            requestedBook.push(selectedBook[0]);
        } else if ($(this).attr("data-operationId") == 4) {
            selectedBook = requestedBook.filter(book => {
                if (book.bookId == $(this).attr("data-bookId")) {
                    return book;
                }
            });

            requestedBook.find((data, i) => {
                if (data.bookId == selectedBook[0].bookId) {
                    index = i;
                    return i;
                }
            });
            requestedBook.splice(index, 1);
            allBook.push(selectedBook[0]);
        } else {

        }


        $.ajax({
            url: "../../BookOperation",
            method: "POST",
            data: {
                bookId: $(this).attr("data-bookId"),
                operation: $(this).attr("data-operationId")
            },
            success: function (result) {
                if (result.responseCode == 1) {
                    templateNotification(result.msg, "info");
                    __mainFunction();
//                    renderData(allBook, 'Borrow Books', 3, 'Books are not available to borrow', "all-books");
//                    renderData(requestedBook, 'Cancel request', 4, 'No Book to in request list', "requested-book");
//                    renderData(borrowedBook, 'Borrow Book', 2, 'No Books borrowed By you', "borrowed-book");
                } else
                    errorElsePart();
            },
            error: function (e, t, o) {
                errorBlock();
            }
        });
    });
});



var renderData = function (bookArray, message, operationId, errorMessage, elementId) {
    var data = "";

    if (bookArray.length == 0) {
        data += "<tr>";
        if (operationId == 4) {
            data += '<td colspan="3" style="text-align: center;">' + errorMessage + '</td>';
        } else if (operationId == 2) {
            data += '<td colspan="4" style="text-align: center;">' + errorMessage + '</td>';
        } else {
            data += '<td colspan="5" style="text-align: center;">' + errorMessage + '</td>';
        }

    } else {
        var counter = 0;
        for (var a of bookArray) {
            counter++;
            data += "<tr>"
            data += "<td>" + a.bookName + "</td>"
            if (operationId == 3) {
                data += "<td>" + a.publishedBy + "</td>"
                data += "<td>" + transformYear(a.publishedYear) + "</td>"

                data += "<td>" + a.totalPages + "</td>"

                data += '<td class="td-actions text-center">';
                data += '<button type="button" class="btn btn-sm book-operation btn-primary" data-bookId="' + a.bookId + '" data-operationId="' + operationId + '" data-toggle="tooltip" data-placement="top" title="' + message + '">'
                data += 'borrow'
                data += '</button>'
                data += '</td>'
            }
            if (operationId == 4) {
                data += "<td>" + trasformDate(a.bookRequestedOn) + "</td>"
                data += '<td class="td-actions text-center">';
                data += '<button type="button" class="btn btn-sm book-operation btn-primary" data-bookId="' + a.bookId + '" data-operationId="' + operationId + '" data-toggle="tooltip" data-placement="top" title="' + message + '">'
                data += 'Cancel'
                data += '</button>'
                data += '</td>'
            }

            if (operationId == 2) {
                data += "<td>" + trasformDate(a.bookBorrowedOn) + "</td>"
            }
            if (operationId == 2) {
                data += "<td>" + trasformDate(a.dueDate) + "</td>"
                data += '<td class="td-actions text-center">';
                data += '<button type="button" class="btn btn-sm book-operation btn-primary" data-bookId="' + a.bookId + '" data-operationId="' + operationId + '" data-toggle="tooltip" data-placement="top" title="' + message + '">'
                data += 'Return'
                data += '</button>'
                data += '</td>'
            }
            data += "</tr>"
            if (counter == 5)
                break;
        }
    }
    $("#" + elementId).html(data);
    $(".current-date").html(currentDate())
}



var __mainFunction = function () {
    fetchAllbook();
    fetchMappedBook();
}
var fetchAllbook = function () {
    $.ajax({
        url: "../../FetchAllBookForUser",
        method: "POST",
        success: function (result) {
            console.log(result);
            if (result.responseCode == 1) {
                allBook = [...result.allAvailableBook];
                renderData(allBook, 'Borrow Book', 3, 'Books are not available to borrow', "all-books");
            } else
                errorElsePart();
        },
        error: function (e, t, o) {
            errorBlock();
        }
    });
}

var fetchMappedBook = function () {
    $.ajax({
        url: "../../FetchMappedBook",
        method: "GET",
        success: function (result) {
            if (result.responseCode == 1) {
                var data = "";
                for (var a of topDataArray) {
                    data += '<div class="col-xl-4 col-lg-4 col-md-4 col-sm-4">'
                    data += '<div class="card card-stats">'
                    data += '<div class="card-header ' + a.class + ' card-header-icon">'
                    data += '<div class="card-icon">'
                    data += '<i class="material-icons">' + a.icons + '</i>'
                    data += '</div>'
                    data += '<p class="card-category">' + a.name + '</p>'
                    data += '<h3 class="card-title" id="totalBooktaken">' + result[a.data] + '</h3>'
                    data += '</div>'
                    data += '<div class="card-footer">'
                    data += '<div class="stats">'
                    data += '<i class="material-icons">date_range</i> As on Date ' + currentDateObject + '</div>'
                    data += '</div>'
                    data += '</div>'
                    data += '</div>'
                }

                $("#top-data").html(data);
                borrowedBook = [...result.totalBorrowedBookList];
                renderData(borrowedBook, 'Borrow Book', 2, 'No Books borrowed By you', "borrowed-book");


                requestedBook = [...result.totalRequestedBookList];
                renderData(requestedBook, 'Cancel request', 4, 'No Book to in request list', "requested-book");

            } else
                errorElsePart();
        },
        error: function (e, t, o) {
            errorBlock();
        }
    });
}