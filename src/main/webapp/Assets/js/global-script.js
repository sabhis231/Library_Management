/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var contextPath = null;
var searchInput = -1;
$(function () {
    contextPath = $("[name=contextPath]").val();

    var pathName = window.location.pathname;

    pathName = pathName.split("/");
    pathName = pathName[pathName.length - 1];
    pathName = pathName.split(".");
    pathName = pathName[0];


    if (pathName) {
        $("[data-Link=" + pathName + "]").addClass("active");
    }

});
var templatetohtml = function (templatename, params) {
    var template = $("#tmpl-" + templatename).html();
    return Mustache.render(template, params);
};
var templateNotification = function (message, type) {
    $.notify(message, type);
};
var DataSaved = function () {
    $.notify("Data Saved", "success");
};
var sessionExpired = function () {
    $.notify("Session Expired", "error");
};
var nullValues = function () {
    $.notify("Please fill all the fields", "warn");
};
var errorInSaving = function () {
    $.notify("Some Error Occured in Saving", "warn");
};
var nonIntegerValue = function () {
    $.notify("Don't Debug Code", "warn");
};
var errorElsePart = function () {
    $.notify("Some Error Occured While Fetching", "warning");
};
var errorElsePartMessage = function (msg) {
    $.notify("Some Error Occured While Fetching", "warning");
};
var errorBlock = function () {
    $.notify("Some Error Occured", "error");
};
var dataNotFound = function () {
    $.notify("Data Not Found", "info");
};

var currentDate = function () {
    return new Date().toString().split(' ').slice(0, 4).join(' ');
};

var trasformDate = function (date) {
    return new Date(date).toString().split(' ').slice(0, 4).join(' ');
};
var transformYear = function (date) {
    return date.toString().split('-')[0];
};










