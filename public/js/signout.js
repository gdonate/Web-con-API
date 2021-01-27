//de momento lo ponemos como localhost después se cambiará a http://147.83.7.206:8080/dsaApp
var BASE_URI="http://147.83.7.206:8080/dsaApp";

//solo cuando esté preparado documento html
$(document).ready(function(){
$("#exitButton").click(function () {
        $.ajax({
            type: 'DELETE',
            url: BASE_URI.concat("/signout?token=" + token.toString()),
            headers: {'content-type': 'application/json', "x-kii-appid": "XXXXX", "x-kii-appkey": "XXXXX"},
            success: function () {
                console.log("hola1")
                window.sessionStorage.clear();
                var url = "http://147.83.7.203:8080/login.html";
                window.open(url, "_self");
            },
            error: function (e) {
                console.log(e.message);
            }
        });
    });
});
