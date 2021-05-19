var contextPath = null;
var socketPath = null;

$(function() {
    contextPath = $("[name=contextPath]").val();
    socketPath = "ws://" + window.location.host + contextPath + "/socket"
});

function templatetohtml(templatename, params) {
    console.log(params);
    console.log(templatename);
    var template = $("#tmpl-" + templatename).html();
    return Mustache.render(template, params);
}

function refreshsvgelements() {
    $('img[src$=".svg"').each(function() {
        console.log("trst");
        var $img = jQuery(this);
        var imgID = $img.attr('id');
        var imgClass = $img.attr('class');
        var imgURL = $img.attr('src');

        jQuery.get(imgURL, function(data) {
            // Get the SVG tag, ignore the rest
            var $svg = jQuery(data).find('svg');

            // Add replaced image's ID to the new SVG
            if (typeof imgID !== 'undefined') {
                $svg = $svg.attr('id', imgID);
            }
            // Add replaced image's classes to the new SVG
            if (typeof imgClass !== 'undefined') {
                $svg = $svg.attr('class', imgClass + ' replaced-svg');
            }

            // Remove any invalid XML tags as per http://validator.w3.org
            $svg = $svg.removeAttr('xmlns:a');

            // Replace image with new SVG
            $img.replaceWith($svg);

        }, 'xml');
    });
}

function formatXml(xml) {
    xml = xml.replace(/  +/g, "");
    var formatted = '';
    var reg = /(>)(<)(\/*)/g;
    xml = xml.replace(reg, '$1\r\n$2$3');
    var pad = 0;
    jQuery.each(xml.split('\r\n'), function(index, node) {
        var indent = 0;
        if (node.match(/.+<\/\w[^>]*>$/)) {
            indent = 0;
        } else if (node.match(/^<\/\w/)) {
            if (pad != 0) {
                pad -= 1;
            }
        } else if (node.match(/^<\w[^>]*[^\/]>.*$/)) {
            indent = 1;
        } else {
            indent = 0;
        }

        var padding = '';
        for (var i = 0; i < pad; i++) {
            padding += '    ';
        }

        formatted += padding + node + '\r\n';
        pad += indent;
    });

    return formatted;
}

setTimeout(function() {
    $(".autohide").fadeOut(1000);
}, 3000);