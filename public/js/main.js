$(function(){
	var $users=$('#users');
	//aqui pondremos todas las variables que se necesitan para introducir un usuario en la API
	var $username = $('#username');
	var $password = $('#password');
	var $mail = $('#mail');
	var $name = $('#name');
	var $lastname = $('#lastname');
	var $city = $('#city');


	$.ajax({
		type: 'GET',
		url: '/dsaApp/users/getUsers',
		success: function(users){
			$.each(users, function(i,user){
				$users.append('<li>username: '+user.name+' mail: '+user.mail+' name: '+user.name+' lastname: '+user.lastname+' city: '+user.city+ '</li>');
			});
		},
		error: function(){
			alert('Error cargando todos los usuarios');
		}
	});
	$('#signupbutton').on('click', function(){
		var user = {
			username: $username.val(),
			password: $password.val(),
			mail: $mail.val(),
			name: $name.val(),
			lastname: $lastname.val(),
			city: $city.val(),
		};
		$.ajax({
			type: 'POST',
			url: '/dsaApp/users/registerUser',
			data: user,
			success: function(newUser){
				$users.append('<li>username: '+newUser.name+' mail: '+newUser.mail+' name: '+newUser.name+' lastname: '+newUser.lastname+' city: '+newUser.city+ '</li>');
			},
			error: function(){
				alert('Error al registrar el usuario');
			}
		});
	});
});