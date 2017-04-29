package MRS.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import MRS.Common.DBProxy;
import MRS.Model.User;

@Controller
public class PageController implements PageControllerFactory{
	
	protected User user;
	static private PageController pc = null;
	protected DBProxy dbProxy;
	
	public PageController(){
		dbProxy = new DBProxy();
	}
	
	public void createPageController(User user){
		this.setUser(user);
		if(user.getUserRoleId() == 4)
		{
			System.out.println("Created userController");
			pc = new UserController(); 
		}
//		else if(user.getUserRoleId() == 3)
//		{
//			return new CriticController(); 
//		}
//		else if(user.getUserRoleId() == 2)
//		{
//			return new ModeratorController(); 
//		}
//		else if(user.getUserRoleId() == 1)
//		{
//			return new AdminController(); 
//		}
//		return null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@GetMapping("/user")
	public String nameUser(Model model)
	{
		System.out.println("In name user PageController");
		String res = pc.nameUser(model);
		return res;
	}
}
