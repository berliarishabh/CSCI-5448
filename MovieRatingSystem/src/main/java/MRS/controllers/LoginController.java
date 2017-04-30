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
	private LoginController()
	{
		dbProxy = new DBProxy();
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
			User user = dbProxy.getUserDetails(name, password);
			String page = PageControllerFactory.createPageController(user);
			if(page == "")
			{
				System.out.println("Failed to create pageController");
				model.addAttribute("errorMessage", "Invalid Credentials");
				return "joinus";
			}
			retVal = "redirect:" + "/user";	// name of the jsp file
		}
		else
		{
			model.addAttribute("errorMessage", "Invalid Credentials");
			retVal = "joinus";
		}
		return retVal;
	}
}
