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
					address: "1111 Engineering Drive, Boulder",
				}
			},
			"autofit" );

	    }

			function loadJSON(endpoint, callback) {
				var xobj = new XMLHttpRequest();
				xobj.overrideMimeType("application/json");
				xobj.open('GET', endpoint, true);
				xobj.onreadystatechange = function() {
					if (xobj.readyState == 4 && xobj.status == "200") {
						callback(xobj.responseText);
					}
				}
				xobj.send(null);
			}

			var url = localStorage.getItem('url');
			var movieName = localStorage.getItem('movieName');
			console.log(url);
			// Add your API endpoint instead of movies.json file
			//loadJSON(url, function(response) {
				loadJSON('movies-single.json', function(response) {

				// Do Something with the response e.g.
				var object = JSON.parse(response);
				console.log(object)

        var moviedata = [];
        moviedata.push(

          '<div class="col-md-4">'
          + '<figure class="movie-poster">' + '<img src= ' + object.imageLocation + '></figure> </div>'
          + '<div class="col-md-4"> <h2 class=movie-title>'+ object.movieName +'</h2>'
          + '<div class="movie-summary"> <p>' + object.movieDescription + '</p>'
          + '<div class=year>' + 'Year: ' + object.releaseYear + '</div>'
          + '<div class=genre>' + 'Genre: ' + object.genre + '</div>'
          + '<div class=star-rating> <span style=width:' + object.aggregateRating + '%><strong class="rating"></strong> </span></div>'
          + '</div>'
        );

        console.log(moviedata);
				// Construct an array from the JSON object
				// val is going to represent each movie object
				var items = [];
				$.each(object["reviewList"], function(key, val) {

					// check this out in the console to see what I'm saying
					console.log("Review " + key, val);
					localStorage.setItem('movieId', val.movieId);
					localStorage.setItem('userId', val.userId);
				//	Create a data structure out of each review object and append to items array
					items.push(
						'<ul class=movie-meta> '
						+ '<p><b>Movie Id:  </b>' + val.movieId + '</p>'
            + '<p><b>User:  </b>' + val.nameUser + '</p>'
						+ '<p><b>User Id: </b>' + val.userId + '</p>'
            + '<li><strong>Rating: </strong>'
            + '<div class=star-rating> <span style=width:' + val.rating + '%><strong class=rating></strong> </span></div> </li>'
            + '<p><b>Review:</b>  "'+ val.comment + '"</p>'
						+ '<td><input type="checkbox" onclick="myFunction() "name="checkboxG1" id="checkboxG1" class="css-checkbox" /><label for="checkboxG1" class="css-label">Flag Review</label></td>'
						+ '<br><br><br>'




					);

				});

				// Wrapped everything inside of an unordered list and append items as a child to the <div.entry-content> element
        $('<div>', {
					'class': 'movieSingle',


					html: moviedata.join('')
				}).prependTo("div.row");

				$('<div>', {
					'class': 'reviewList',


					html: items.join('')
				}).prependTo("div.entry-content");
			});

	});

	$(window).load(function() {

	});

console.log(localStorage.getItem('url'));
//console.log(localStorage.getItem('movieName'));


})(jQuery, document, window);

var movieName = localStorage.getItem('movieName');
console.log(movieName);
$("#movieName1").val(movieName);
$("#movieName2").val(movieName);
// assign function to onclick property of checkbox
function myFunction() {
		var x = document.getElementById("checkboxG1");
		var flag = x.checked;

		var movieId = localStorage.getItem('movieId');
		var userId = localStorage.getItem('userId');

		var string = 'http://localhost:8080/MovieRatingSystem/flagreview?';
		var queryString = string+'flagStr='+flag+'&movieIdStr='+movieId+'&userIdStr='+userId;
		alert(queryString);
		var xmlHttp = new XMLHttpRequest();
		xmlHttp.open( "GET", queryString, false ); // false for synchronous request
		xmlHttp.send( null );
		}
