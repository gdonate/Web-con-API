//de momento lo ponemos como localhost después se cambiará a http://147.83.7.206:8080/dsaApp
var BASE_URI="http://localhost:8080/dsaApp";

//solo cuando esté preparado documento html
$(document).ready(function(){
    //aqui pondremos todas las variables que se necesitan para inciar sesión usuario en la API
    var username = $('#username');
	console.log("username", username);
	var password = $('#password');
	console.log("password", password);
    //Code if the user clicks log in button
    $("#loginbutton").click(function(){
      var user = {
        username: $username.val(),
        password: $password.val(),
      };
      $.ajax({
        headers: { 'content-type': 'application/json',"x-kii-appid": "XXXXX","x-kii-appkey":"XXXXX" },
        type: 'POST',
        url: BASE_URI.concat("/auth/login"),
        data: JSON.stringify(user),
        dataType: 'json',
        success: function(data) {
            console.log("Se ha iniciado sesión correctamente");
            console.log(data);
            console.log(url);
            alert('¡Te autentificaste correctamente');
            window.localStorage.setItem("user_id",data.user_id);
            window.localStorage.setItem("username",username);
            window.open("http://localhost:8080", "_self");
        },
        error: function(error){
          if(error.status==404){
            alert("¡Este nombre de usuario no existe en la API!");
          }
          if(error.status==500){
            alert("¡Contraseña no coincide con el nombre de usuario introducido!");
          }
        }
    });
  });
});