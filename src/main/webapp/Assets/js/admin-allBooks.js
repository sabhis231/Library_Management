/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var bookArray = [];
var selectedBook = [];
var authorList = [];
var publisherList = [];
var selectedBookId = -1;
var searchKey = "";
$(function () {
    fetchAllBooks();
    $.ajax({
        url: "../../FetchAllAuthorList",
        method: "POST",
        data: {
            searchKey: ""
        },
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
        data: {
            searchKey: ""
        },
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
    $("body").on("click", ".book-operation", function () {

        $("#exampleModalLabel").html("Edit Book");
        $("#card-model").html("Edit Book");
        $("#form-button-book").html("Update Details");
        $("#deleteButton").show();
        selectedBook = bookArray.filter(book => {
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
        selectedBookId = selectedBook[0].bookId;

    });
    $("body").on('submit', '#updateOrSaveBook', function () {
        $.ajax({
            url: "../../EditOrSaveBook",
            method: "POST",
            data: $(this).serialize(),
            success: function (result) {
                $("#hideModel").click();
                if (result.responseCode == 1) {
                    fetchAllBooks();
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

    $("body").on("click", ".add-book", function () {
        $("#exampleModalLabel").html("Add New Book");
        $("#card-model").html("Add New Book");
        $("#form-button-book").html("Add Book");
        $("#deleteButton").hide();
        var data = '';
        data += '<option selected disabled>Select Author</option>'
        for (var a of authorList) {

            data += ' <option value="' + a.authorId + '">' + a.authorName + '</option>'

        }


        $("#authorList").html(data);
        var data = '';
        data += '<option selected disabled>Select Author</option>'

        for (var a of publisherList) {

            data += ' <option value="' + a.publisherId + '">' + a.publisherName + '</option>'

        }

        $("#publisherList").html(data);
        $("#booktitle").val('');
        $("#publishedYear").val('');
        $("#totalPages").val('')
        $("#description").val('')
        $("#bookId").val(0)
    })
});
$('body').on('click', ".request-list", function () {
    $("#list-model-title").html("Request List");
    $("#list-model-card-title").html("Request User List");
    $("#list-model-card-sub-title").html("Request List  as on");
    selectedBook = $(this).attr("data-bookId");
    $.ajax({
        url: "../../FetchRequestUserList",
        method: "POST",
        data: {bookId: selectedBook},
        success: function (result) {
            console.log(result)
//            $("#hideModel").click();
            if (result.responseCode == 1) {
                var requestUser = [...result.allRequestedUser]

                renderUserData(requestUser, 1);
            } else
                errorElsePart();
        },
        error: function (e, t, o) {
            errorBlock();
        }
    });
//    return false;
});
$('body').on('click', ".reader-list", function () {
    $("#list-model-title").html("Reader List");
    $("#list-model-card-title").html("Reader User List");
    $("#list-model-card-sub-title").html("Reader List  as on");
    selectedBook = $(this).attr("data-bookId");
    $.ajax({
        url: "../../FetchReaderUserList",
        method: "POST",
        data: {bookId: selectedBook},
        success: function (result) {
            console.log(result)
//            $("#hideModel").click();
            if (result.responseCode == 1) {
                var requestUser = [...result.allReader]
                console.log(requestUser)
                renderUserData(requestUser, 2);
            } else
                errorElsePart();
        },
        error: function (e, t, o) {
            errorBlock();
        }
    });
//    return false;
});
$('body').on('click', '.approve-book', function () {
    var userId = $(this).attr("data-bookId");
    $.ajax({
        url: "../../ApproveBorrowBook",
        method: "POST",
        data: {bookId: selectedBook, userId: userId},
        success: function (result) {
            console.log(result);
            $(".hideModel").click();
            if (result.responseCode == 1) {
                fetchAllBooks();
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
$('body').on('click', '#deleteButton', function () {
    var userId = $(this).attr("data-bookId");
//    selectedBook = $(this).attr("data-bookId");
    $.ajax({
        url: "../../DisableBook",
        method: "POST",
        data: {bookId: selectedBookId},
        success: function (result) {
            console.log(result);
            $(".hideModel").click();
            if (result.responseCode == 1) {
                fetchAllBooks();
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

var renderUserData = function (userArray, listType) {

    var data = "";
    data += ' <thead class="text-warning">';
    data += ' <th>UserName</th>';
    data += ' <th>EmailId</th>';
    data += ' <th>Created On</th>';
    if (listType == 1) {
        data += ' <th>Action</th>';
    }
    data += ' </thead>';
    data += ' <tbody id="user-List">';
    if (userArray.length == 0 && listType == 1) {
        data += "<tr>";
        data += "<td colspan='4' class='text-center'>No request list</td>"
        data += "</tr>";
    }

    if (userArray.length == 0 && listType == 2) {
        data += "<tr>";
        data += "<td colspan='3' class='text-center'>No reader list</td>"
        data += "</tr>";
    }

    for (var a of userArray) {
        data += "<tr>";
        data += "<td>" + a.userName;
        if (a.activeUser) {
            data += "(Active reader)";
        }
        data += "</td>";
        data += "<td>" + a.emailId + "</td>";
        data += "<td>" + trasformDate(a.requestOn) + "</td>";
        if (listType == 1) {
            data += '<td class="td-actions">';
            data += '  <a class="btn btn-primary approve-book" data-bookId="' + a.userId + '">Approve</a>';
            data += '</td>';
        }

        data += "</tr>";
    }
    data += ' </tbody>';
    $("#user-List").html(data);
};
var renderData = function (bookArray) {
    var data = "";
    if (bookArray.length == 0) {
        data += "<tr>";
        data += "<td colspan='5' style='text-align: center;'>Books are not available to borrow</td>";
    } else {
        for (var a of bookArray) {
            data += "<tr>";
            data += "<td>" + a.bookName + "</td>";
            data += "<td>" + (((a.publishedBy.length) > 40) ? (a.publishedBy.substring(0,40)+"...") :  (a.publishedBy)) + "</td>";
            data += "<td>" + transformYear(a.publishedYear) + "</td>";
            data += "<td>" + a.totalPages + "</td>";
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
            data += '<td class="td-actions text-right">';
            data += '  <a class="btn btn-primary book-operation" data-toggle="modal" data-target="#showandedit" data-bookId="' + a.bookId + '">Show and Edit </a>'
            data += '</td>'
            data += '<td class="td-actions text-right">';
            data += '  <a class="btn btn-primary request-list" data-toggle="modal" data-target="#viewList" data-bookId="' + a.bookId + '">View Requested User</a>'
            data += '</td>'
            data += '<td class="td-actions text-right">';
            data += '  <a class="btn btn-primary reader-list" data-toggle="modal" data-target="#viewList" data-bookId="' + a.bookId + '">Reader List</a>'
            data += '</td>'
            data += "</tr>"
        }
    }
    $("#all-books").html(data);
    $(".current-date").html(currentDate())
}


var fetchAllBooks = function () {
    $.ajax({
        url: "../../FetchAllBooksDetails",
        method: "POST",
        data: {
            searchKey: searchKey
        },
        success: function (result) {
            console.log(result);
            if (result.responseCode == 1) {
                bookArray = [...result.allAvailableBook];
                console.log(bookArray);
                renderData(bookArray);
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
    fetchAllBooks()();
}, 500));
