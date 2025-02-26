
var RootURl = "http://localhost:8081/books"

var findAll = function(){
	$.ajax({
		type: "GET",
		data: "json",
		url: RootURl,
		success: renderList,
		error: function(xhr,status,error){
			console.log(error);
		}
		
	});
	
};

var renderList = function(data){
	$(".details").remove();
	$.each(data, function(index, book){
		let img = '"images/' + book.image +'"';
		let htmlStr = '<div id=' + book.id + ' class="details">';
		htmlStr+='<img src=' + img + 'height="150">';
		htmlStr+='<h1>'+book.title+'</h1>';
		htmlStr+='<h2>By ' + book.author + '</h2>';
		htmlStr+= '<h2>Illustrated by ' + book.illustrated + '</h2>';
		htmlStr+='<input id="' + book.id + '" class="infoButton" type="button" value="More Info">';
		htmlStr+='</div>';
		$("#bookList").append(htmlStr);
	});
};

var findById = function(id){
	$.ajax({
		type:"GET",
		data:"json",
		url: RootURl + '/' + id,
		success: moreInfo,	
		error: function(error){
			console.log(error);
		}
	});
};

var moreInfo = function(){
	let modal = '#detailsModal';
	
	modal.find('.modal-title').text(book.title);
	modal.find('#pic').attr('src', 'images/' + book.imageModal);
	
	let rrp = book.rrp;
	let onprice = book.online;
	let savings = rrp - onprice;
	
	modal.find('#rrp').text(rrp)
	
	modal.find('#rrp').text('')
	
	
}

$(document).ready(function(){
	findAll();
	
});

