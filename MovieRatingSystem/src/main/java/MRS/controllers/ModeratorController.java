package MRS.controllers;

import java.util.List;

//import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;

import MRS.Common.DBProxy;
//import MRS.Common.DBProxy;
import MRS.Model.Movie;

public class ModeratorController extends PageController{
	
	private DBProxy dbProxy;
	public ModeratorController(){
		dbProxy = new DBProxy();
	}
	
	public String nameUser(Model model)
	{
		System.out.println("In name user ModeratorController");
		String result = user.getName();
		model.addAttribute("firstName", result);
		return "welcomeMessage";	// name of the jsp file
	}
	
	@GetMapping("/movie")
	public String movie(Model model)
	{
		List<Movie> mv;
		mv = dbProxy.getMovies("", 0, 0, 'A');
		model.addAttribute("moviesList", mv);
//		model.addAttribute("movieName", mv.get(1).getMovieName());
//		model.addAttribute("releaseYear", mv.get(0).getReleaseYear());
		return "movies";	// name of the jsp file
	}
}