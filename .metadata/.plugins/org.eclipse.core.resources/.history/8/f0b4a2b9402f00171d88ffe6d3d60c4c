package MRS.controllers;

import java.util.HashMap;
import java.util.Map;

//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;

//import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseBody;

//import MRS.Model.Movie;
//import MRS.Common.DBProxy;
//import MRS.Model.Movie;
import MRS.Model.User;

@Controller
public class PageController{
	
	static protected User user;
	static private PageController pc = null;
//	protected DBProxy dbProxy;
//	
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

	public static User getUser() {
		return user;
	}
	public static void setUser(User user) {
		PageController.user = user;
	}
	public static PageController getPc() {
		return pc;
	}
	public static void setPc(PageController pc) {
		PageController.pc = pc;
	}
	
	@RequestMapping("/user")
	public @ResponseBody Map<String, User> getUserDetails(Model model)
	{
		Map<String, User> map = new HashMap<String, User>();
		System.out.println("In getUserDetails PageController");
		if(PageController.getUser() == null)
			return null;
		map.put("userDetails", user);
		return map;
	}
	
}
