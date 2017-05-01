package MRS.controllers;

import java.util.List;

//import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import MRS.Common.DBProxy;
//import MRS.Common.DBProxy;
import MRS.Model.Movie;

public class AdminController extends PageController{
	
	private DBProxy dbProxy;
	public AdminController(){
		dbProxy = new DBProxy();
	}
	
	public String nameUser(Model model)
	{
		System.out.println("In name user AdminController");
		String result = user.getName();
		model.addAttribute("firstName", result);
		return "welcomeMessage";	// name of the jsp file
	}
	
//	@GetMapping("/movies")
	public List<Movie> movies(Model model)
	{
		List<Movie> mv;
		System.out.println("In movies AdminController");
		mv = dbProxy.getMovies("", 0, 0, 'A');
//		model.addAttribute("moviesList", mv);
//		model.addAttribute("movieName", mv.get(1).getMovieName());
//		model.addAttribute("releaseYear", mv.get(0).getReleaseYear());
//		return "movies";	// name of the jsp file
		return mv;	// name of the jsp file
	}
}
