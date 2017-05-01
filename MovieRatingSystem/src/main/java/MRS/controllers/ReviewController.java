package MRS.controllers;

import MRS.Model.Review;
import MRS.Common.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/review")
public class ReviewController {

	private String ratingStr = "89";
	private String userIdStr = "2";
	private String movieIdStr= "4";
	private String comment	 = "This is a test comment";
	
	private DBProxy dbProxy;
	private ReviewController()
	{
		dbProxy = new DBProxy();
		this.review();
	}
	
	//@PostMapping("/review")
//	public void review(Model model, @RequestParam String ratingStr, @RequestParam String comment, 
//			@RequestParam String userIdStr, @RequestParam String movieIdStr) {

	public void review() {
		
		double rating 	= Double.parseDouble(ratingStr);
		int userId	 	= Integer.parseInt(userIdStr);
		int movieId		= Integer.parseInt(movieIdStr);
		
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