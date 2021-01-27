//de momento lo ponemos como localhost después se cambiará a http://147.83.7.206:8080/dsaApp
var BASE_URI="http://147.83.7.206:8080/dsaApp";

//ingreso este comentario para que me reconozca el login

//solo cuando esté preparado documento html
$(document).ready(function(){
    //Code if the user clicks log in button
    $("#loginbutton").click(function(){
    //aqui pondremos todas las variables que se necesitan para inciar sesión usuario en la API
    var username = $('#username').val();
    console.log(username);
    var password = $('#password').val();
    console.log(password);
    console.log("login BUTTON");
    var user = {"username": username, "password": password};
    console.log(user);
      $.ajax({
        type: 'POST',
        url: BASE_URI.concat("/auth/login"),
        headers: { 'content-type': 'application/json',"x-kii-appid": "XXXXX","x-kii-appkey":"XXXXX" },
        data: JSON.stringify(user),
        dataType: 'json',
        success: function(data) {
            console.log("Se ha iniciado sesión correctamente");
            console.log(data);
            console.log(url);
            var username = data.username;
            var name = data.name;
            var points = data.points;
            var enemiesKilled = data.enemiesKilled;
            var level = data.level;
            //nos llevamos los siguientes datos del usuario
            window.sessionStorage.setItem("username", username);
            window.sessionStorage.setItem("name", name);
            window.sessionStorage.setItem("points", points);
            window.sessionStorage.setItem("enemiesKilled", enemiesKilled);
            window.sessionStorage.setItem("level", level);
            alert('¡Te autentificaste correctamente');
            //window.localStorage.setItem("username",username);
            //window.localStorage.setItem("password",password);
            //con esta última indicación redirigimos al usuario a la nueva pagina
            //con _self lo hacemos en la misma ventana, con _blank le llevaríamos a otra en blanco
            var url = "http://147.83.7.206:8080/html/myprofile.html"; //localhost
            window.open(url, "_self");
        },
        error: function(error){
          /*if(error.status==404){
            alert("¡Este nombre de usuario no existe en la API!");
          }*/
          console.log(error.message);
        }
    });
  });
});