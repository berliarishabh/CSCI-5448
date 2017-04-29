package MRS.controllers;

import java.util.List;

//import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;

//import MRS.Common.DBProxy;
import MRS.Model.Movie;
import MRS.Model.User;

//@Controller
public class UserController extends PageController{
	
//	@PostMapping("/welcome")
//	public String welcome(Model model, @RequestParam String name,
//			@RequestParam String password)
//	{
//		Boolean result = false;
//		result= dbProxy.validateUser(name, password);
//		if(result == true)
//		{
//			model.addAttribute("firstName", name);
//			return "welcomeMessage";	// name of the jsp file
//		}
//		else
//		{
//			return "movies";
//		}
//	}
	
	public String nameUser(Model model)
	{
		System.out.println("In name user UserController");
		User user = dbProxy.getUserDetails("ribe3261", "Huddar");;
		String result = user.getName();
		model.addAttribute("firstName", result);
		return "welcomeMessage";	// name of the jsp file
	}
	
	@GetMapping("/movie")
	public String movie(Model model)
	{
		List<Movie> mv;
		mv = dbProxy.getMovies("", 0, 0, 'A');
		model.addAttribute("movieName", mv.get(1).getMovieName());
		model.addAttribute("releaseYear", mv.get(0).getReleaseYear());
		return "movies";	// name of the jsp file
	}

//	@Override
//	public String login(Model model, String name, String password) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
