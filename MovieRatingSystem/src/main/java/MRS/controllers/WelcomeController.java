package MRS.controllers;

import MRS.Common.DBProxy;
import java.util.List;

import MRS.Model.*;

//import javax.validation.OverridesAttribute.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController
{
	private DBProxy dbProxy;
	
	private WelcomeController()
	{
		dbProxy = new DBProxy();
	}
	
	@GetMapping("/welcome")
	public String welcome(Model model)
	{
		Boolean result = false;
		result= dbProxy.validateUser("ribe3261", "Huddar");
		model.addAttribute("firstName", result);
		return "welcomeMessage";	// name of the jsp file
	}
	
	@GetMapping("/user")
	public String user(Model model)
	{
		List<User> ls;
		ls = dbProxy.getUserDetails("ribe3261", "Huddar");
		String result = ls.get(0).getName();
		model.addAttribute("firstName", result);
		return "welcomeMessage";	// name of the jsp file
	}
	
	@GetMapping("/movie")
	public String movie(Model model)
	{
		Movie mv;
		mv = dbProxy.getMovie("Bahubali");
		model.addAttribute("movieName", mv.getMovieName());
		model.addAttribute("releaseYear", mv.getReleaseYear());
		return "movies";	// name of the jsp file
	}
}
