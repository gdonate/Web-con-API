//de momento lo ponemos como localhost después se cambiará a http://147.83.7.206:8080/dsaApp
var BASE_URI="http://localhost:8080/dsaApp";

//solo cuando esté preparado documento html
$(document).ready(function(){
	var $users=$('#users');
	//aqui pondremos todas las variables que se necesitan para introducir un usuario en la API
	var $username = $('#username');
	console.log(username);
	var $password = $('#password');
	console.log(password);
	var $mail = $('#mail');
	console.log(mail);
	var $name = $('#name');
	console.log(name);
	var $lastname = $('#lastname');
	console.log(lastname);
	var $city = $('#city');
	console.log(city);

	$('#signupbutton').on('click', function(){
		var user = {
			username: $username.val(),
			password: $password.val(),
			mail: $mail.val(),
			name: $name.val(),
			lastname: $lastname.val(),
			city: $city.val(),
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
				console.logo(data);
				alert('¡Ya estás registrado en EETAC WARS!');
				window.open("http://localhost:8080", "_self");
			},
			error: function(error){
			if(error.status==500){
				alert('¡Este nombre de usuario ya existe! Prueba introducir otro');
				}
			}
		});
	});
});