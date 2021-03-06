package MRS.controllers;

import MRS.Common.DBProxy;
import MRS.Model.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	private DBProxy dbProxy;
	private static User user = null;
	private static boolean loginState = false; 
	
	private LoginController()
	{
		dbProxy = new DBProxy();
	}
	
	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		LoginController.user = user;
	}

	public void setLoginState(boolean loginState) {
		LoginController.loginState = loginState;
	}
	
	public static boolean isLoggedIn()
	{
		return loginState;
	}
	
	@PostMapping("/login")
	public String login(Model model, @RequestParam String name,
			@RequestParam String password)
	{
		Boolean result = false;
		result= dbProxy.validateUser(name, password);
		System.out.println("validated user is " + result);
		String retVal = "";
		if(result == true)
		{
			LoginController.setUser(dbProxy.getUserDetails(name, password));
			PageController.setUser(dbProxy.getUserDetails(name, password));
			String page = PageControllerFactory.createPageController(user);
			if(page == "")
			{
				System.out.println("Failed to create pageController");
				model.addAttribute("errorMessage", "Invalid Credentials");
				return "joinus";
			}
			this.setLoginState(true);
			retVal = "redirect:" + "/review.html";	// name of the jsp file
		}
		else
		{
			System.out.println("Invalid Credentials");
			model.addAttribute("errorMessage", "Invalid Credentials");
			this.setLoginState(false);
			retVal = "joinus";
		}
		return retVal;
	}

	@RequestMapping("/logout")
	public String logout(Model model)
	{
		LoginController.setUser(null);
		PageController.setUser(null);
		System.out.println("User logged out");
		this.setLoginState(false);
		return "joinus";
	}	
}
