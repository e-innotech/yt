$(document).ready(function () {

    $("#search-form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();
        
    });
 
    $("#bth-add").click(function() {
    	 add_submit();
    });
});

function fire_ajax_submit() {

    var search = $("#id").val();

    $("#btn-search").prop("disabled", true);
 
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/user/"+search,
        cache: false,
        timeout: 600000,
        success: function (data) {
        	
            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);

        },
        error: function (e) {
        	 var json = "<h4>Ajax Response</h4><pre>"
                 + JSON.stringify(e, null, 4) + "</pre>";
             $('#feedback').html(json);
             console.log("ERROR : ", e);
        }
    });
}

function add_submit() {

    var search = {};
    search["userName"] = $("#userName").val();
    search["passWord"] = $("#passWord").val();

    $("#bth-add").prop("disabled", true);
    
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/user/add",
       // data: JSON.stringify(search),
        data :{
            "userName" : $("#userName").val(),
            "passWord" : $("#password").val()
        },
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4><pre>"
                + data.msg + "</pre>";
            $('#feedback').html(json);

        },
        error: function (e) {


        }
    });
}
