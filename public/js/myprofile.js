//de momento lo ponemos como localhost después se cambiará a http://147.83.7.206:8080/dsaApp
var BASE_URI="http://147.83.7.206:8080/dsaApp";

//solo cuando esté preparado documento html
$(document).ready(function(){
	var $items=$('#items');

	$.ajax({
		type: 'GET',
		url: BASE_URI.concat("/game/getItems"),
		success: function(items){
			$.each(items, function(i,item){
				$items.append('<li>name: '+item.name+' total: '+item.total+ '</li>');
			});
		},
		error: function(){
			alert('Error cargando todos los items del juego');
		}
	});
});
