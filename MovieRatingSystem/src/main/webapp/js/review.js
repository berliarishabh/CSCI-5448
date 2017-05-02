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
				xobj.open('POST', endpoint, true);
				xobj.onreadystatechange = function() {
					if (xobj.readyState == 4 && xobj.status == "200") {
						callback(xobj.responseText);
					}
				}
				xobj.send(null);
			}

			// Add your API endpoint instead of movies.json file
			loadJSON('http://localhost:8080/MovieRatingSystem/movies?movieName&releaseYear&aggregateRating', function(response) {
				// Do Something with the response e.g.
				var object = JSON.parse(response);
				console.log(object)

				// Construct an array from the JSON object
				// val is going to represent each movie object
				var items = [];
				$.each(object["movieList"], function(key, val) {

					// check this out in the console to see what I'm saying
					console.log("Movie " + key, val);

					// Create a data structure out of each movie object and append to items array
					items.push(
						'<div class=movie> '
						+ '<div class="movie-title">' + '<a href=single.html>' + val.name + '</a></div>'
						+ '<figure class="movie-poster">' + '<img src= ' + val.image + '></figure>'
						+ '<div class=year>' + 'Year: ' + val.year + '</div>'
						+ '<div class=genre>' + 'Genre: ' + val.genre + '</div>'
            + '<div class=star-rating> <span style=width:' + val.rating + '><strong class="rating"></strong> </span></div>'
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
