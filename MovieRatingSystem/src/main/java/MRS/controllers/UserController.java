package MRS.controllers;

import org.springframework.ui.Model;
import MRS.Common.DBProxy;
import MRS.Model.User;

public class UserController extends PageController{
	
	private DBProxy dbProxy;
	public UserController(){
		dbProxy = new DBProxy();
	}
	
	public User getUser(Model model)
	{
		System.out.println("In name user UserController");
		User user = dbProxy.getUserDetails(PageController.getUser().getUsername(), 
				PageController.getUser().getPassword());
		return user;	// name of the jsp file
	}

}
