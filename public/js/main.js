//de momento lo ponemos como localhost después se cambiará a http://147.83.7.206:8080/dsaApp
var BASE_URI="http://localhost:8080/dsaApp";

//solo cuando esté preparado documento html
$(document).ready(function(){
	var $users=$('#users');

	$.ajax({
		type: 'GET',
		url: BASE_URI.concat("/users/getUsers"),
		success: function(users){
			$.each(users, function(i,user){
				$users.append('<li>username: '+user.name+' mail: '+user.mail+' name: '+user.name+' lastname: '+user.lastname+' city: '+user.city+ '</li>');
			});
		},
		error: function(){
			alert('Error cargando todos los usuarios');
		}
	});
});