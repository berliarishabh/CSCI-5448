package MRS.controllers;

//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;

//import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;

//import MRS.Model.Movie;
//import MRS.Common.DBProxy;
//import MRS.Model.Movie;
//import MRS.Model.User;

@Controller
public class PageController{
	
//	static protected User user;
	static private PageController pc = null;
//	protected DBProxy dbProxy;
	
//	public PageController(){
////		pc = PageControllerFactory.createPageController(user);
//		dbProxy = new DBProxy();
//	}
//	public void createPageController(User user){
//		setPc(PageControllerFactory.createPageController(user));
//		dbProxy = new DBProxy();
//	}
	
//	public void createPageController(User user){
//		this.setUser(user);
//		if(user.getUserRoleId() == 4)
//		{
////			System.out.println("Created UserController");
//			pc = new UserController(); 
//		}
//		else if(user.getUserRoleId() == 3)
//		{
////			System.out.println("Created CriticController");
//			pc = new CriticController(); 
//		}
//		else if(user.getUserRoleId() == 2)
//		{
////			System.out.println("Created CriticController");
//			pc = new ModeratorController();  
//		}
//		else if(user.getUserRoleId() == 1)
//		{
////			System.out.println("Created CriticController");
//			pc = new AdminController();  
//		}
//	}

//	public static User getUser() {
//		return user;
//	}
//	public static void setUser(User user) {
//		PageController.user = user;
//	}
	public static PageController getPc() {
		return pc;
	}
	public static void setPc(PageController pc) {
		PageController.pc = pc;
	}
	
	@GetMapping("/user")
	public String nameUser(Model model)
	{
		System.out.println("In name user PageController");
		String res = getPc().nameUser(model);
		return res;
	}
	
//	@GetMapping("/movies")
//	public @ResponseBody Map<String, List<Movie>> moviess(Model model)
//	{
//		Map<String, List<Movie>> map = new HashMap<String, List<Movie>>();
////		Movie mv = new Movie();
////		mv.setAggregateRating(7.4);
////		mv.setApprovalState('A');
////		mv.setGenre("Horror");
////		mv.setMovieName("Insidious");
////		mv.setNumberOfCriticsRated(99);
////		mv.setNumberOfUsersRated(99);
////		mv.setReleaseYear(2011);
//		List<Movie> mv = getPc().movies(model);
//		map.put("movieList", mv);
//		return map;	// name of the jsp file
//	}
	
//	public List<Movie> movies(Model model)
//	{
//		return null;
//	}
}
