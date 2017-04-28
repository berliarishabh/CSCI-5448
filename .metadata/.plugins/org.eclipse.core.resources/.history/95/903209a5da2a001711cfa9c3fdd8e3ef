package MRS.controllers;

import MRS.Common.DBProxy;
import MRS.Model.*;

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
		result= dbProxy.validateUser("ssss", "ssss");
		model.addAttribute("firstName", result);
		return "welcomeMessage";	// name of the jsp file
	}
	
	
}
