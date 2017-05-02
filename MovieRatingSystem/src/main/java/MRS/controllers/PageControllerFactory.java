package MRS.controllers;

import MRS.Model.User;

public class PageControllerFactory {
	
	public static String createPageController(User user){
		String page = "";
		if(user.getUserRoleId() == 4)
		{
			System.out.println("Created UserController");
			PageController.setPc(new UserController());
			page = "/user";
		}
		else if(user.getUserRoleId() == 3)
		{
			System.out.println("Created CriticController");
			PageController.setPc(new CriticController());
			page = "/critic";
		}
		else if(user.getUserRoleId() == 2)
		{
			System.out.println("Created ModeratorController");
			PageController.setPc(new ModeratorController());
			page = "/moderator";
		}
		else if(user.getUserRoleId() == 1)
		{
			System.out.println("Created AdminController");
			PageController.setPc(new AdminController());
			page = "/admin";
		}
		return page;
	}
}
