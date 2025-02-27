 //JavaScript Document
$(document).ready(function(){
	$('input').on('click', function(){
	var price=$('<p>From $399.99</p>');	
	$('#destinations').append(price);
	$('input').remove();
	 });
});


	