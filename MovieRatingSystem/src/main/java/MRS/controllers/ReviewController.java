package MRS.controllers;

import MRS.Model.Review;
import MRS.Common.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {
	
	private DBProxy dbProxy;
	private ReviewController()
	{
		dbProxy = new DBProxy();
//		this.review();
	}
	
//	@PostMapping("/review")
	@GetMapping("/review")
	public void review(Model model)/*, @RequestParam String ratingStr, @RequestParam String comment, 
			 @RequestParam String movieIdStr) */{ //@RequestParam String userIdStr,
		
//		if(!LoginController.isLoggedIn())
//			return;
		
//		double rating 	= Double.parseDouble(ratingStr);
//		int userId	 	= PageController.getUser().getUserId();//Integer.parseInt(userIdStr);
//		int movieId		= Integer.parseInt(movieIdStr);
		
		double rating 	= 7.6;//Double.parseDouble(ratingStr);
		int userId	 	= 1;//PageController.getUser().getUserId();//Integer.parseInt(userIdStr);
		int movieId		= 1;//Integer.parseInt(movieIdStr);
		String comment = "Good movie";
		
		Review newReview = new Review(rating, comment, userId, movieId);

		boolean addReviewReturnCode = dbProxy.addReview(newReview);
		
		if (addReviewReturnCode == true) {
			System.out.println("\nAdding review successful");
			
		}
		else {
			System.out.println("\nAdding review failed");
		}
	}
	
}
