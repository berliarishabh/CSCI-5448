package MRS.controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import MRS.Common.DBProxy;
import MRS.Model.Movie;
import MRS.Model.Review;
import MRS.Model.User;

class CustomComparator implements Comparator<Movie> {
	
	private int order = 1;
	public CustomComparator(int order) {
		this.order = order;
	}
	
    @Override
    public int compare(Movie m1, Movie m2) {
        return order * (int)(m1.getAggregateRating() - m2.getAggregateRating());
    }
}

@Controller
public class MovieController {
	
	private User user = null;
	
	private DBProxy dbProxy = null;
	
	public MovieController(){
		dbProxy = new DBProxy();
	}
	
	@RequestMapping("/movies")
	public @ResponseBody Map<String, List<Movie>> movies(Model model, @RequestParam String genre,
			@RequestParam String releaseYear, @RequestParam String aggregateRating, 
			@RequestParam String approvalState)
	{
		char appState = 'A';
		if(approvalState != "")
			approvalState.charAt(0);
		if(!LoginController.isLoggedIn())
			return null;
		user = LoginController.getUser();
		if(user.getUserRoleId() == 4){
			appState = 'A';
		}
		else if(user.getUserRoleId() == 3){
			appState = 'A';
		}
		Map<String, List<Movie>> map = new HashMap<String, List<Movie>>();
		int rYear = 0; 
		double aggRating = 0;
		if(releaseYear != "")
			rYear =	Integer.parseInt(releaseYear);
		if(genre == "")
			genre = "";
		System.out.println(genre + ":" + releaseYear + ":"  + aggregateRating); 
		List<Movie> mv = dbProxy.getMovies(genre, rYear, aggRating, appState);
		if(aggregateRating == "L")
			Collections.sort(mv, new CustomComparator(1));
		else if(aggregateRating == "H")
			Collections.sort(mv, new CustomComparator(-1));
		map.put("movieList", mv);
		return map;	// name of the jsp file
	}
	
	// add a movie by the user
	@RequestMapping("/addmovie")
	public String addMovie(Model model, @RequestParam String movieName, 
			@RequestParam String imageLocation, @RequestParam String releaseYear, 
			@RequestParam String genre, @RequestParam String movieDescription) {

		String retval = "joinus";	// return to the login page

		if (LoginController.isLoggedIn() == true) {				// check if the user is logged in
			if(PageController.getUser().getUserId()== 4 )
				return retval;
			double aggRating = 0;
			int rYear	= Integer.parseInt(releaseYear);
			char approvalState = 'P';
			System.out.println("\nAdding Movie");
			Movie newMovie = new Movie();
			newMovie.setElements(movieName, rYear, genre, aggRating, imageLocation, approvalState, movieDescription);

			boolean dbAccessRC = dbProxy.addMovie(newMovie);

			if (dbAccessRC == true) {
				System.out.println("\nAdding Movie successful");
			}
			else {
				System.out.println("\nAdding Movie failed");
			}
			retval = "redirect:/review.html";		// reload the page
		}

		return retval;
	}
	
	@RequestMapping("/approveMovie")
	public String approveMovie(Model model, @RequestParam String movieName, 
			@RequestParam String movieIdStr ){
		
		String retval = "redirect:/review.html";
		
		if (LoginController.isLoggedIn() == true) {				// check if the user is logged in
		
			if(PageController.getUser().getUserId()== 3 || PageController.getUser().getUserId()== 4)
				return retval;
			int movieId = Integer.parseInt(movieIdStr);
			// get the movie
			Movie movie = dbProxy.getMovie(movieName);
			if(movieId != movie.getMovieId())
				return retval;
			movie.setApprovalState('A');
			dbProxy.updateMovie(movie);
			System.out.println("Movie Approved");
			retval = "redirect:/review.html";
		}
		return retval;
	}
	
	@RequestMapping("/singleMovie")
	public @ResponseBody Map<String, Object> getSingleMovie(Model model, 
			@RequestParam String movieName){
		Map<String, Object> map = new HashMap<String, Object>();
		if (LoginController.isLoggedIn() == true) {				// check if the user is logged in
			// get the movie
			Movie movie = dbProxy.getMovie(movieName);
			List<Review> reviews = dbProxy.getReviews(movie.getMovieId(), 0, 0);
			System.out.println("MovieName is " + movie.getMovieName());
			map.put("reviewList", reviews);
			map.put("movieId", movie.getMovieId());
			map.put("releaseYear", movie.getReleaseYear());
			map.put("aggregateRating", movie.getAggregateRating());
			map.put("movieName", movie.getMovieName());
			map.put("movieDescription", movie.getMovieDescription());
			map.put("genre", movie.getGenre());
			map.put("imageLocation", movie.getImageLocation());
			System.out.println("Single Movie Sent");
		}
		else
			map = null;
		return map;
	}
}
