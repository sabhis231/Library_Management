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

var returnedBook = [];
var requestedBook = [];
var allBook = [];
var selectedBook = [];
var authorList = [];
var publisherList = [];
var searchKey = "";

var currentDateObject = currentDate();

$(function () {
    __mainFunction();

});
var renderData = function (bookArray, errorMessage, elementId) {
    var data = "";


    data += '<thead class="text-warning">';
    data += '<th>Book Name</th>';
    data += '<th>User Name</th>';
    data += '<th>Email Id</th>';
    data += '<th>Published On</th>';
    data += '<th>Returned On </th>';
//        data += '<th>View Details</th>';
    data += '</thead>';
    data += '<tbody>';

    if (bookArray.length == 0) {
        data += "<tr>"
        data += "<td colspan='5' class='text-center'>" + errorMessage + "</td>";
        data += '</tr>';
    } else {
        for (var a of bookArray) {
            data += "<tr>"
            data += "<td>" + a.bookName + "</td>";
            data += "<td>" + a.userName + "</td>";
            data += "<td>" + a.emailId + "</td>";
            data += "<td>" + transformYear(a.publishedYear) + "</td>";
            data += "<td>" + trasformDate(a.returnedOn) + "</td>";

            data += '</tr>';
        }
    }





    data += '</tbody>';

    $("#" + elementId).html(data);
    $(".current-date").html(currentDate())
}







var returnedAllBooks = function () {
    $.ajax({
        url: "../../FetchAllReturnedBookDetails",
        method: "POST",
        data: {
            searchKey: searchKey
        },
        success: function (result) {
            if (result.responseCode == 1) {
                returnedBook = [...result.allReturnedBook];
                renderData(returnedBook, 'No Data for Returned Book', "returned-book");
            } else
                errorElsePart();
        },
        error: function (e, t, o) {
            errorBlock();
        }
    });
}

var __mainFunction = function () {
    returnedAllBooks();
};


$('.user-search').keyup(debounce(function () {
    searchKey = $(this).val();
    __mainFunction()();
}, 500));
