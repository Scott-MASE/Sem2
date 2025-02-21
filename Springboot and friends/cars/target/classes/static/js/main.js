

var rootURL = "http://localhost:8081/cars"

var findAll = function() {
	console.log("Find all")
	$.ajax({
		type: 'GET',
		url: rootURL,
		dataType: 'json',
		success: renderList,
		error: function(xhr, status, error) {
			$(".details").remove();
		}


	})
};

//make, model, manufactureryear
var renderList = function(data) {
	$('.list_cars_scroll').empty();
	$.each(data, function(index, car) {
		let img = 'images/cars/' + car.image;
		let htmlStr = '<div class="scroll_cars">';
		htmlStr += '<img src="' + img + '" alt="Car">';
		htmlStr += '<p> ' + car.manufacturerYear + ' ' + car.make + ' ' + car.model + ' </p>';
		htmlStr += '<a id=' + car.id + ' href = "">';
		htmlStr += '<span class ="fa fa-info-circle fa-2x"></span>';
		htmlStr += '</a>';
		htmlStr += '</div>';
		$('.list_cars_scroll').append(htmlStr);

	});

}

var findById = function(id) {
	$.ajax({
		type: 'GET',
		url: rootURL + '/' + id,
		dataType: "json",
		success: function(car) {
			showDetails(car);
		}
	});
}

var showDetails = function(car) {
	$('#modal-title-details').text('Details for ' + car.manufacturerYear + ' ' + car.make + ' ' + car.model);
	$('#pic').attr('src', 'images/cars/' + car.image);
	$('#color').val(car.color);  // Use .val() instead of .text()
	$('#year').val(car.manufacturerYear);
	$('#engine').val(car.litre + ' litres');
	$('#miles').val(car.mileage + ' miles');
	$('#condition').val(car.condition);
	$('#detailsModal').modal('show');
}

var findByYear = function(start, end) {
	console.log("finding by year")
	$.ajax({
		type: 'GET',
		url: rootURL + '/search/' + start + '/' + end,
		dataType: 'json',
		success: function(data) {
			console.log("successss: ", JSON.stringify(data, null, 2));
			renderList(data);
		},
		error: function(xhr, status, error) {
			console.log("here");
			$(".details").remove();
		}

	});
}



$(document).ready(function() {
	$(document).on("click", ".list_cars_scroll a", function(event) {
		event.preventDefault();  // Prevents default anchor behavior
		let carId = $(this).attr("id");  // Get the ID of the clicked car
		findById(carId);
	});
	$(document).on("click", "#searchButton", function(){
		$('#filterModal').modal('show');
	});
	$(document).on("click", "#submitButton", function() {
	    $('#filterModal').modal('hide');
	    let minYear = $('#minYear').val();
	    let maxYear = $('#maxYear').val();
		console.log("Filtering cars between " + minYear + " and " + maxYear);

	    if (minYear === "" || maxYear === "") {
	        findAll();
	    } else {
			
	        findByYear(minYear, maxYear);
	    }
	});
	findAll();
});










