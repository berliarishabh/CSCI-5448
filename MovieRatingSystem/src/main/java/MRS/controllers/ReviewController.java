package MRS.controllers;

import MRS.Model.Review;
import MRS.Common.*;
import MRS.controllers.LoginController;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {

	private DBProxy dbProxy;
	private ReviewController()
	{
		dbProxy = new DBProxy();
	}

	// add a review by the user
	@PostMapping("/addreview")
	public String addReview(Model model, @RequestParam String ratingStr, @RequestParam String comment, 
			@RequestParam String userIdStr, @RequestParam String movieIdStr) {

		String retval = "joinus";	// return to the login page

		if (LoginController.isLoggedIn() == true) {				// check if the user is logged in

			double rating 	= Double.parseDouble(ratingStr);
			int userId	 	= Integer.parseInt(userIdStr);
			int movieId		= Integer.parseInt(movieIdStr);
			
			// check if the user has already reviewed
			List<Review> reviewList = dbProxy.getReviews(movieId, userId);

			if (reviewList.isEmpty() == true) {
				// user has not reviewed
				
				// add the comment to database
				Review newReview = new Review(rating, comment, userId, movieId);

				boolean dbAccessRC = dbProxy.addReview(newReview);

				if (dbAccessRC == true) {
					System.out.println("\nAdding review successful");
				}
				else {
					System.out.println("\nAdding review failed");
				}
			}

			retval = "review";		// reload the page
		}

		return retval;
	}

	// delete users own review
	@RequestMapping("/deletereview")
	public String deleteReview(Model model, @RequestParam String movieIdStr, @RequestParam String userIdStr) {

		String retval = "joinus";	// return to the same page

		if (LoginController.isLoggedIn() == true) {				// check if the user is logged in
		
			int movieId = Integer.parseInt(movieIdStr);
			int userId = Integer.parseInt(userIdStr);
			
			// check if the user has already reviewed
			List<Review> reviewList = dbProxy.getReviews(movieId, userId);
	
			if (reviewList.isEmpty() == false) {
				// User has reviewed
	
				boolean dbAccessRC = dbProxy.deleteReview(movieId, userId);
	
				if (dbAccessRC == true) {
					System.out.println("\nDeleting review successful");
				}
				else {
					System.out.println("\nDeleting review failed");
				}
			}
			
			retval = "review";
		}

		return retval;
	}

	// deleting a flagged review; Only Moderator and Admin can do this
	@RequestMapping("/deleteflaggedreview")
	public String deleteFlaggedReview(Model model) {

		String retval = "joinus";
		
		if (LoginController.isLoggedIn() == true) {				// check if the user is logged in

			// delete a comment
			
			retval = "review";	// should actually return to the flagged reviews page
		}		
		
		return retval;
	}

	@RequestMapping("/flagreview")
	public String flagReview(Model model, @RequestParam String movieIdStr, 
			@RequestParam String userIdStr, @RequestParam String commentIdStr) {
		
		String retval = "review";
		
		if (LoginController.isLoggedIn() == true) {				// check if the user is logged in

			// TODO: Need to have a function in the DB that updates specific fields;
			//	something like this updateObject(NULL, NULL, 23, NULL, NULL); Updates only the non-NULL fields

			retval = "review";
		}
		
		return retval;
	}
}