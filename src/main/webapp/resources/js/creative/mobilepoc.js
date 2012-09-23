/**
 * Created with IntelliJ IDEA.
 * User: mohan
 * Date: 5/05/12
 * Time: 6:12 PM
 * To change this template use File | Settings | File Templates.
 */

//Entry Point for all our Java Script On Load
//globalCache

//Enterprise JQueryNameSpace aka Module PAttern as
// defined in  http://enterprisejquery.com/2010/10/how-good-c-habits-can-encourage-bad-javascript-habits-part-1/
(function (creativegeoAPI, $, undefined) {
    //public function
    creativegeoAPI.main = function () {
        $(document).ready(function () {
            geoCityAutoComplete.initAutoComplete();
            geoCityAutoComplete.bindButtonValidation();

        });
    };
    creativegeoAPI.initGMap = function (latitude, longitude) {
        // Just some canned map for now
        var height = $(document).height()/4;
        $("#map").css('height',height);
        var latlng = new google.maps.LatLng(latitude, longitude);
        var myOptions = {
            zoom:6,
            center:latlng,
            mapTypeId:google.maps.MapTypeId.TERRAIN,
            panControl:true,
            zoomControl:true,
            mapTypeControl:true,
            scaleControl:true,
            streetViewControl:true,
            overviewMapControl:true,
            scrollwheel:true
        };
        var map = new google.maps.Map($('#map')[0], myOptions);
        var marker = new google.maps.Marker({
            position:latlng,
            map:map,
            title:$('#autocomplete').val()
        });
        return map;
        $(window).resize(function () {
            var height = $(document).height()/4;
            $("#map").css('height',height);
            google.maps.event.trigger(map, 'resize');

        });
    };
    creativegeoAPI.animateMainContentOnLoad = function (data) {
        switch (data.status) {
            case "begin":
                break;
            case "complete":
                break;
            case "success":
                animateMainContentRight();
                break;
        }
    };

    function animateMainContentRight() {
        console.log("Animating Main Content Right");
        $('#mainContent').show("slide", { direction:"right" }, 700);
    }
}(window.creativegeoAPI = window.creativegeoAPI || {}, jQuery));


//AutoComplete Modules

(function (geoCityAutoComplete, $, undefined) {
    //private
    var GEO_CITY_ID_COOKIE = "GEO_CITY_ID";

    //public method
    geoCityAutoComplete.initAutoComplete = function () {
        $("#autocomplete").autocomplete({
            source:function (request, response) {
                getData(request, response);
            },
            minLength:2,
            cacheLength:5000,
            delay:1000,
            select:function (event, ui) {
                $.cookie(GEO_CITY_ID_COOKIE, null);
                console.log("Putting id in cookie" + ui.item.id);
                $.cookie(GEO_CITY_ID_COOKIE, ui.item.id);
            },
            open:function () {
                $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
            },
            close:function () {
                $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
            }
        })
            .data("autocomplete")._renderItem = function (ul, item) {
            return $("<li></li>")
                .data("item.autocomplete", item)
                .append("<a>" + item.label + "<br>" + "</a>")
                .appendTo(ul);
        };
    };

    geoCityAutoComplete.bindButtonValidation = function () {
        $('#autoCompleteForm .btn[type="submit"]').click(function () {
            if (!$('#autocomplete').val()) {
                console.log("Input value is empty TODO:Validation");
                clearCookie();
            }
        });
    }

    function clearCookie() {
        $.cookie(GEO_CITY_ID_COOKIE, null);
    }

    //private methods
    function getData(request, response) {
        var key = request.term;
        if (geoCacheAPI.doesValueExist(key)) {
            return response(geoCacheAPI.getValueFromCache(key));
        }
        else {
            return getDataFromAjax(request, response);
        }
    }

    function getDataFromAjax(request, response) {
        var key = request.term;
        $.ajax({
            type:"GET",
            url:"resources/geocity/names",
            dataType:"json",
            cache:true,
            data:{
                cityStartsWith:key
            },
            success:function (data) {
                autoCompleteObj = $.map(data, function (item) {
                    return {
                        label:item.label,
                        value:item.label,
                        id:item.id
                    }
                });
                geoCacheAPI.putInCache(key, autoCompleteObj);
                response(autoCompleteObj);
            }
        });
    }
}(window.geoCityAutoComplete = window.geoCityAutoComplete || {}, jQuery));


//Geo CacheAPI module
(function (geoCacheAPI, undefined) {
//private Property
    var globalCache = {};
//private functions
    geoCacheAPI.putInCache = function (key, value) {
        console.log("Putting in cache", key);
        globalCache[key] = value;
    };
    geoCacheAPI.removeFromCache = function (key) {
        console.log("Removing from cache", key);
        delete globalCache[key];
    };
    geoCacheAPI.doesValueExist = function (key) {
        if (globalCache[key]) {
            return true;
        }
        else {
            return false;
        }
    };
    geoCacheAPI.getValueFromCache = function (key) {
        console.log("Getting value from cache for :" + key);
        return globalCache[key];
    };
}(window.geoCacheAPI = window.geoCacheAPI || {}));


