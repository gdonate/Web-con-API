//de momento lo ponemos como localhost después se cambiará a http://147.83.7.206:8080/dsaApp
var BASE_URI="http://147.83.7.206:8080/dsaApp";

//solo cuando esté preparado documento html
$(document).ready(function signUpFunction(){
	var users=$('#users');
	//aqui pondremos todas las variables que se necesitan para introducir un usuario en la API
	var username = $('#username');
	console.log(username);
	var password = $('#password');
	console.log(password);
	var mail = $('#mail');
	console.log(mail);
	var name = $('#name');
	console.log(name);
	var lastname = $('#lastname');
	console.log(lastname);
	var city = $('#city');
	console.log(city);

	$('#signupbutton').on('click', function(){
		var user = {
			username: username.val(),
			password: password.val(),
			mail: mail.val(),
			name: name.val(),
			lastname: lastname.val(),
			city: city.val(),
		};
		console.log(user);
		$.ajax({
		    headers: {'content-type': 'application/json',"x-kii-appid": "XXXXX","x-kii-appkey":"XXXXX"},
			type: 'POST',
			url: BASE_URI.concat("/users/registerUser"),
			data: JSON.stringify(user),
			dataType: 'json',
			success: function(data){
				console.log("Usuario creado");
				console.log(data);
				alert('¡Ya estás registrado en EETAC WARS!');
				//con esta última indicación redirigimos al usuario a la nueva pagina
                //con _self lo hacemos en la misma ventana, con _blank le llevaríamos a otra en blanco
                window.open("http://147.83.7.206:8080/html/myprofile.html", "_self");
			},
			error: function(error){
			console.log("", error);
			if(error.status==500){
				alert('¡Este nombre de usuario ya existe! Prueba introducir otro');
				}
			else{
			alert('Error al registrar usuario. Pruebe otra vez');
			}
			//aquí pondriamos que se quede en la página en la que está después de dar el alert
			//window.open("http://localhost:8080/html/signup.html", "_self");
			}
		});
	});
});
