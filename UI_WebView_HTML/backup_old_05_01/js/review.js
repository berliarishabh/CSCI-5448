(function($, document, window) {

	$(document).ready(function() {

		// Cloning main navigation for mobile menu
		$(".mobile-navigation").append($(".main-navigation .menu").clone());

		// Mobile menu toggle
		$(".menu-toggle").click(function(){
			$(".mobile-navigation").slideToggle();
		});
		$(".search-form button").click(function(){
			$(this).toggleClass("active");
			var $parent = $(this).parent(".search-form");

			$parent.find("input").toggleClass("active").focus();
		});


		$(".slider").flexslider({
			controlNav: false,
			prevText:'<i class="fa fa-chevron-left"></i>',
			nextText:'<i class="fa fa-chevron-right"></i>',
		});

		if( $(".map").length ) {
			$('.map').gmap3({
				map: {
					options: {
						maxZoom: 14
					}
				},
				marker:{
					address: "40 Sibley St, Detroit",
				}
			},
			"autofit" );

	    }

			function loadJSON(endpoint, callback) {
				var xobj = new XMLHttpRequest();
				xobj.overrideMimeType("application/json");
				xobj.open('GET', endpoint, true); //method, url, async
				xobj.onreadystatechange = function() {
					if (xobj.readyState == 4 && xobj.status == "200") {
						callback(xobj.responseText);
					}
				}
				xobj.send(null);
			}

			// Add your API endpoint instead of movies.json file
			  //loadJSON('http://localhost:8080/MovieRatingSystem/movies?genre=&releaseYear=&aggregateRating=', function(response) {
				loadJSON('movies.json', function(response) {

				// Do Something with the response e.g.
				var object = JSON.parse(response);
				//console.log(object)

				// Construct an array from the JSON object
				// val is going to represent each movie object
				var items = [];
				$.each(object["movieList"], function(key, val) {

					// check this out in the console to see what I'm saying
					//console.log("Movie " + key, val);

					// Create a data structure out of each movie object and append to items array
					items.push(
						'<div class=movie> '
						+ '<div class="movie-id" id='+ val.movieId + '> </div>'
						+ '<ul class="movie-title">' + '<a href="single.html" onclick="doalert(this);">' + val.movieName + '</a></ul>'
						+ '<figure class="movie-poster">' + '<img src= ' + val.imageLocation + '></figure>'
						+ '<div class=year>' + 'Year: ' + val.releaseYear + '</div>'
						+ '<div class=genre>' + 'Genre: ' + val.genre + '</div>'
            + '<div class=star-rating> <span style=width:' + val.aggregateRating + '%><strong class="rating"></strong> </span></div>'
						+ '</div>'
					);
				});

				// Wrapped everything inside of an unordered list and append items as a child to the <body> element
				$('<div>', {
					'class': 'movieList',


					html: items.join('')
				}).prependTo("div.movie-list");
			});

	});

	$(window).load(function() {

	});

})(jQuery, document, window);

//Create the Selection to a JSON object array
$('#go').on("click", function() {
var objVal = {};
$('select').each(function() {
var arr = $(':selected', this).map(function() {
	return this.value;
}).get();
objVal[$(this).attr("name")] = arr;
});
//Parse the String
var string = encodeQueryData(objVal);
var querystring = 'http://localhost:8080/MovieRatingSystem/movies?' + string;
console.log(querystring);

function loadJSON(endpoint, callback) {
	var xobj = new XMLHttpRequest();
	xobj.overrideMimeType("application/json");
	xobj.open('GET', endpoint, true); //method, url, async
	xobj.onreadystatechange = function() {
		if (xobj.readyState == 4 && xobj.status == "200") {
			callback(xobj.responseText);
		}
	}
	xobj.send(null);
}

//loadJSON('movies-search.json', function(response) {
	loadJSON(querystring, function(response) {

// Do Something with the response e.g.
var object = JSON.parse(response);
console.log(object)

// Construct an array from the JSON object
// val is going to represent each movie object
var items = [];
$.each(object["movieList"], function(key, val) {

	// check this out in the console to see what I'm saying
	//console.log("Movie " + key, val);

	// Create a data structure out of each movie object and append to items array
	items.push(
		'<div class=movie> '
		+ '<div class="movie-id" id='+ val.movieId + '> </div>'
		+ '<ul class="movie-title">' + '<a href="single.html" onclick="doalert(this);">' + val.movieName + '</a></ul>'
		+ '<figure class="movie-poster">' + '<img src= ' + val.imageLocation + '></figure>'
		+ '<div class=year>' + 'Year: ' + val.releaseYear + '</div>'
		+ '<div class=genre>' + 'Genre: ' + val.genre + '</div>'
		+ '<div class=star-rating> <span style=width:' + val.aggregateRating + '%><strong class="rating"></strong> </span></div>'
		+ '</div>'
	);
});

// Wrapped everything inside of an unordered list and append items as a child to the <body> element
$('<div>', {
	'class': 'movieList',


	html: items.join('')
}).prependTo("div.movie-list");
});

});

//JSON to URL String
function encodeQueryData(data) {
   let ret = [];
   for (let d in data)
     ret.push(encodeURIComponent(d) + '=' + encodeURIComponent(data[d]));
   return ret.join('&');
}

//TEST
// var data = { 'first name': ['George'], 'last name': ['Jetson'], 'age': [110] };
// var querystring = encodeQueryData(data);
// console.log(data);
// console.log(querystring);

//Function to Fetch Data (Movie Name), contruct the GET URL and Pass to Single.html as 'url'
    function doalert(obj) {
        console.log(obj.innerHTML);
				var movieName = obj.innerHTML;
				localStorage.setItem('movieName', movieName)
				var string = 'http://localhost:8080/MovieRatingSystem/singleMovie?movieName='+ movieName;
				alert(string);
				localStorage.setItem('url', string)
    }
