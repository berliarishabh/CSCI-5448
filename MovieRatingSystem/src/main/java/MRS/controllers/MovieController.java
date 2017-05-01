package MRS.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import MRS.Common.DBProxy;
import MRS.Model.Movie;
import MRS.Model.User;

@Controller
public class MovieController {
	private User user = null;
	
	private DBProxy dbProxy = null;
	
	public MovieController(){
		dbProxy = new DBProxy();
		if(LoginController.isLoggedIn())
			user = LoginController.getUser();
	}
	
	@GetMapping("/movies")
	public @ResponseBody Map<String, List<Movie>> moviess(Model model, @RequestParam String movieName,
			@RequestParam String releaseYear, @RequestParam String aggregateRating)
	{
		if(!LoginController.isLoggedIn())
			return null;
		Map<String, List<Movie>> map = new HashMap<String, List<Movie>>();
//		Movie mv = new Movie();
//		mv.setAggregateRating(7.4);
//		mv.setApprovalState('A');
//		mv.setGenre("Horror");
//		mv.setMovieName("Insidious");
//		mv.setNumberOfCriticsRated(99);
//		mv.setNumberOfUsersRated(99);
//		mv.setReleaseYear(2011);
		int rYear = 0; 
		double aggRating = 0;
		char approvalState = 0;
		if(releaseYear != "")
			rYear =	Integer.parseInt(releaseYear);
		if(aggregateRating != "")
			aggRating = Double.parseDouble(aggregateRating);
		if(user.getUserRoleId() == 4){
			approvalState = 'A';
		}
		else if(user.getUserRoleId() == 3){
			approvalState = 'A';
		}
		List<Movie> mv = dbProxy.getMovies(movieName, rYear, aggRating, approvalState);
		map.put("movieList", mv);
		return map;	// name of the jsp file
	}
	
}