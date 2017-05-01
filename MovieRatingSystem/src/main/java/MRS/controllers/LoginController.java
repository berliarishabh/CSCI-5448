package MRS.controllers;

import MRS.Common.DBProxy;
//import java.util.List;
//
//import MRS.Model.*;
import MRS.Model.User;

//import javax.validation.OverridesAttribute.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping("/login")
public class LoginController {
	private DBProxy dbProxy;
	private static User user = null;
	private boolean loginState = false;
	
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
		this.loginState = loginState;
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
			String page = PageControllerFactory.createPageController(user);
			if(page == "")
			{
				System.out.println("Failed to create pageController");
				model.addAttribute("errorMessage", "Invalid Credentials");
				return "joinus";
			}
			this.setLoginState(true);
			retVal = "redirect:" + "/movies";	// name of the jsp file
		}
		else
		{
			model.addAttribute("errorMessage", "Invalid Credentials");
			this.setLoginState(false);
			retVal = "joinus";
		}
		return retVal;
	}
}