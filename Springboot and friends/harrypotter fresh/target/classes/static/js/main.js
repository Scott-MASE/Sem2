var rootUrl = "http://localhost:8081/books";

var findAll = function(){
	$.ajax({
		type: "GET",
		dataType: "json",
		url: rootUrl,
		success: renderList,
		error: function(error){
			console.log(error);
		}
	});
};

var renderList = function(data){
	$(".details").remove()
	let img = "/images/"+book.image;	
};