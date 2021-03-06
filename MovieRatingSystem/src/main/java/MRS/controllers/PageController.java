package MRS.controllers;

import java.util.HashMap;
import java.util.Map;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import MRS.Model.User;

@Controller
public class PageController{
	
	static protected User user;
	static private PageController pc = null;

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
