var borrowedBook = [];
var requestedBook = [];
var allBook = [];
var selectedBook = [];
var authorList = [];
var publisherList = [];
var searchKey = "";
var topDataArray = [{
        icons: 'content_copy',
        name: 'Total Books',
        data: 'allBooks',
        class: 'card-header-success'
    },
    {
        icons: 'store',
        name: 'Borrowed Book',
        data: 'allBorrowedBooks',
        class: 'card-header-danger'
    },
    {
        icons: 'content_copy',
        name: 'Requested Book',
        data: 'allRequestedBook',
        class: 'card-header-warning'
    }];
var currentDateObject = currentDate();

$(function () {

    __mainFunction();
    fetchOtherCall();
//    fetchDashboardStats();


    $(".current-date").html(currentDateObject);

    $("body").on('submit', '#updateOrSaveBook', function () {
        $.ajax({
            url: "../../EditOrSaveBook",
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
    })

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


    $("body").on("click", ".book-operation", function () {
        console.log($(this).attr("data-bookId"));
        console.log(allBook);
        selectedBook = allBook.filter(book => {
            if (book.bookId == $(this).attr("data-bookId")) {
                return book;
            }
        });
        var data = '';
        for (var a of authorList) {
            if (selectedBook[0].bookAuthorName == a.authorName) {
                data += ' <option value="' + a.authorId + '" selected>' + a.authorName + '</option>'
            } else {
                data += ' <option value="' + a.authorId + '">' + a.authorName + '</option>'
            }
        }


        $("#authorList").html(data);
        var data = '';
        for (var a of publisherList) {
            if (selectedBook[0].publishedBy == a.publisherName) {
                data += ' <option value="' + a.publisherId + '" selected>' + a.publisherName + '</option>'
            } else {
                data += ' <option value="' + a.publisherId + '">' + a.publisherName + '</option>'
            }
        }

        $("#publisherList").html(data);
        $("#booktitle").val(selectedBook[0].bookName);
        $("#publishedYear").val(selectedBook[0].publishedYear);
        $("#totalPages").val(selectedBook[0].totalPages)
        $("#description").val(selectedBook[0].description)
        $("#bookId").val(selectedBook[0].bookId)

    });


});




var renderData = function (bookArray, errorMessage, elementId, typeId) {
    var data = "";
    var counter = 0;

    if (typeId == 1) {
        data += '<thead class="text-warning">';
        data += '<th>Book Name</th>';
        data += '<th>Publisher</th>';
        data += '<th>Published On</th>';
        data += '<th>Total pages</th>';
        data += '<th>Book Status</th>';
        data += '<th>View Details</th>';
        data += '</thead>';
        data += '<tbody>';


        if (bookArray.length == 0) {
            data += "<tr>"
            data += "<td colspan='6' class='text-center'>" + errorMessage + "</td>";
            data += '</tr>';
        } else {
            for (var a of bookArray) {
                counter++;
                data += "<tr>"
                data += "<td>" + a.bookName + "</td>"
                data += "<td>" + a.publishedBy + "</td>"
                data += "<td>" + transformYear(a.publishedYear) + "</td>"
                data += "<td>" + a.totalPages + "</td>"

                data += "<td>" + a.bookStatusName;
                if (a.bookStatusId == 1) {
                    data += " ( " + a.loanDetails.userName + " ) ";
                }
                if (a.bookStatusId == 2) {
                    data += " ( ";
                    var name = "";
                    for (var loanDetail of a.loanDetails) {
                        name += loanDetail.userName + ", ";
                    }
                    name = name.trim();
                    name = name.substring(0, name.length - 1);
                    data += name + " ) ";

                }

                data += "</td>";
                data += '<td class="td-actions text-center">';
                data += '<a class="btn btn-primary book-operation" data-toggle="modal" data-target="#showandedit" data-bookId="' + a.bookId + '">Show and Edit </a>';
                data += '</td>';
                data += '</tr>';
                if (counter == 5)
                    break;
            }
        }


    } else if (typeId == 2) {
        data += '<thead class="text-warning">';
        data += '<th>Book Name</th>';
        data += '<th>User Name</th>';
        data += '<th>Email Id</th>';
        data += '<th>Published On</th>';
        data += '<th>Borrrowed On </th>';
//        data += '<th>View Details</th>';
        data += '</thead>';
        data += '<tbody>';

        if (bookArray.length == 0) {
            data += "<tr>"
            data += "<td colspan='5' class='text-center'>" + errorMessage + "</td>";
            data += '</tr>';
        } else {
            for (var a of bookArray) {
                counter++;
                data += "<tr>"
                data += "<td>" + a.bookName + "</td>";
                data += "<td>" + a.userName + "</td>";
                data += "<td>" + a.emailId + "</td>";
                data += "<td>" + transformYear(a.publishedYear) + "</td>";
                data += "<td>" + trasformDate(a.borrowedOn) + "</td>";

                data += '</tr>';
                if (counter == 5)
                    break;
            }
        }
    } else if (typeId == 3) {
        data += '<thead class="text-warning">';
        data += '<th>Book Name</th>';
        data += '<th>User Name</th>';
//        data += '<th>Email Id</th>';
//        data += '<th>Published On</th>';
        data += '<th>Requested On </th>';
        data += '<th>Approve</th>';
        data += '</thead>';
        data += '<tbody>';


        if (bookArray.length == 0) {
            data += "<tr>"
            data += "<td colspan='4' class='text-center'>" + errorMessage + "</td>";
            data += '</tr>';
        } else {
            for (var a of bookArray) {
                counter++;
                data += "<tr>"
                data += "<td>" + a.bookName + "</td>";
                data += "<td>" + a.userName + "</td>";
//                data += "<td>" + a.emailId + "</td>";
//                data += "<td>" + transformYear(a.publishedYear) + "</td>";
                data += "<td>" + trasformDate(a.requestedOn) + "</td>";
                data += '<td class="td-actions">';
                data += '  <a class="btn btn-primary approve-book" data-userId="' + a.userId + '" data-bookId="' + a.bookId + '">Approve</a>';
                data += '</td>';
                data += '</tr>';
                if (counter == 5)
                    break;
            }
        }
    }




    data += '</tbody>';

    $("#" + elementId).html(data);
    $(".current-date").html(currentDate())
}


var fetchAllBooks = function () {
    $.ajax({
        url: "../../FetchAllBooksDetails",
        data: {
            searchKey: searchKey
        },
        method: "POST",
        success: function (result) {
            if (result.responseCode == 1) {
                allBook = [...result.allAvailableBook];
                console.log(allBook);
                renderData(allBook, 'Kindly add book to view it', "all-book", 1);
            } else
                errorElsePart();
        },
        error: function (e, t, o) {
            errorBlock();
        }
    });
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
                borrowedBook = [...result.allBorrowedBook];
                renderData(borrowedBook, 'No one Borrowed Book', "borrowed-book", 2);
            } else
                errorElsePart();
        },
        error: function (e, t, o) {
            errorBlock();
        }
    });
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
                renderData(requestedBook, 'No one Request Book', "requested-book", 3);
            } else
                errorElsePart();
        },
        error: function (e, t, o) {
            errorBlock();
        }
    });
}

var fetchOtherCall = function () {
    $.ajax({
        url: "../../FetchAllAuthorList",
        method: "POST",
        success: function (result) {
            console.log(result);
            if (result.responseCode == 1) {
                authorList = [...result.allAuthorData];
            } else
                errorElsePart();
        },
        error: function (e, t, o) {
            errorBlock();
        }
    });
    $.ajax({
        url: "../../FetchAllPublisherList",
        method: "POST",
        success: function (result) {

            if (result.responseCode == 1) {
                publisherList = [...result.allPublisherData];
            } else
                errorElsePart();
        },
        error: function (e, t, o) {
            errorBlock();
        }
    });
}

var fetchDashboardStats = function () {
    $.ajax({
        url: "../../FetchDashboardStats",
        method: "POST",
        success: function (result) {
            if (result) {
                var data = "";
                for (var a of topDataArray) {
                    data += '<div class="col-xl-4 col-lg-4 col-md-4 col-sm-4">';
                    data += '<div class="card card-stats">';
                    data += '<div class="card-header ' + a.class + ' card-header-icon">';
                    data += '<div class="card-icon">';
                    data += '<i class="material-icons">' + a.icons + '</i>';
                    data += '</div>';
                    data += '<p class="card-category">' + a.name + '</p>';
                    data += '<h3 class="card-title" id="totalBooktaken">' + result[a.data] + '</h3>';
                    data += '</div>';
                    data += '<div class="card-footer">';
                    data += '<div class="stats">';
                    data += '<i class="material-icons">date_range</i> As on Date ' + currentDateObject + '</div>';
                    data += '</div>';
                    data += '</div>';
                    data += '</div>';
                }

                $("#top-data").html(data);
            } else
                errorElsePart();
        },
        error: function (e, t, o) {
            errorBlock();
        }
    });
};

var __mainFunction = function () {

    fetchAllBooks();
    borrowedAllBooks();
    requestAllBook();
    fetchDashboardStats();
};

$('.user-search').keyup(debounce(function () {
    searchKey = $(this).val();
    __mainFunction();
}, 500));