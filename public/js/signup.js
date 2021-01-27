//de momento lo ponemos como localhost después se cambiará a http://147.83.7.206:8080/dsaApp
var BASE_URI="http://147.83.7.206:8080/dsaApp";

//solo cuando esté preparado documento html
$(document).ready(function(){
	//var users=$('#users');
	//aqui pondremos todas las variables que se necesitan para introducir un usuario en la API

	$('#signupbutton').on('click', function(){
		var username = $('#username').val();
        console.log(username);
        var password = $('#password').val();
        console.log(password);
        var mail = $('#mail').val();
        console.log(mail);
        var name = $('#name').val();
        console.log(name);
        var lastname = $('#lastname').val();
        console.log(lastname);
        var city = $('#city').val();
        console.log(city);
        var confirm = $('#confirmpassword').val();
        console.log(confirm);

        if(password=confirm){
        var user = {"username": username, "password": password, "mail": mail, "name": name, "lastname": lastname, "city": city};
		console.log(user);
		$.ajax({
		    type: 'POST',
		    url: BASE_URI.concat("/users/registerUser"),
		    headers: {'content-type': 'application/json',"x-kii-appid": "XXXXX","x-kii-appkey":"XXXXX"},
			data: JSON.stringify(user),
			dataType: 'json',
			success: function(data){
				console.log("Usuario creado");
				console.log(data);
				alert('¡Ya estás registrado en EETAC WARS!');
				//datos que nos llevamos al perfil
				var username = data.username;
				var name = data.name;
				var points = data.points;
				var enemiesKilled = data.enemiesKilled;
				var level = data.level;
				window.sessionStorage.setItem("username", username);
				window.sessionStorage.setItem("name", name);
				window.sessionStorage.setItem("points", points);
				window.sessionStorage.setItem("enemiesKilled", enemiesKilled);
				window.sessionStorage.setItem("level", level);
				//con esta última indicación redirigimos al usuario a la nueva pagina
                //con _self lo hacemos en la misma ventana, con _blank le llevaríamos a otra en blanco
                var url = "http://147.83.7.206:8080/html/myprofile.html"; //localhost
                window.open(url, "_self");
			},
			error: function(error){
			console.log(error);
			//aquí pondriamos que se quede en la página en la que está después de dar el alert
			//window.open("http://localhost:8080/html/signup.html", "_self");
			}
		});
		}
		else
		alert("The passwords are different\n");
	});
});
