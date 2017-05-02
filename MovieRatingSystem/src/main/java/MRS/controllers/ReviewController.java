package MRS.controllers;

import MRS.Model.Review;
import MRS.Common.*;
import MRS.controllers.LoginController;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
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
	@RequestMapping("/addreview")
	public String addReview(Model model, @RequestParam String ratingStr, 
			@RequestParam String comment, @RequestParam String movieIdStr) {

		String retval = "joinus";	// return to the login page

		if (LoginController.isLoggedIn() == true) {				// check if the user is logged in

			double rating 	= Double.parseDouble(ratingStr);
			int userId	 	= LoginController.getUser().getUserId();
			int movieId		= Integer.parseInt(movieIdStr);
			String nameUser = LoginController.getUser().getName();
			
			// check if the user has already reviewed
			List<Review> reviewList = dbProxy.getReviews(movieId, userId);

			if (reviewList.isEmpty() == true) {
				// user has not reviewed
				
				// add the comment to database
				System.out.println("\nAdding review");
				Review newReview = new Review();
				newReview.setElements(rating, comment, userId, 
						movieId, nameUser);
				boolean dbAccessRC = dbProxy.addReview(newReview);

				if (dbAccessRC == true) {
					System.out.println("\nAdding review successful");
				}
				else {
					System.out.println("\nAdding review failed");
				}
			}

			retval = "redirect:" + "/review.html";		// reload the page
		}

		return retval;
	}

	// delete users own review
	@RequestMapping("/deletereview")
	public String deleteReview(Model model, @RequestParam String movieIdStr) {

		String retval = "joinus";	// return to the same page

		System.out.println("Delete review button pressed");
		
		if (LoginController.isLoggedIn() == true) {				// check if the user is logged in
		
			int movieId = Integer.parseInt(movieIdStr);
			int userId = LoginController.getUser().getUserId();

			// check if the user has already reviewed
			List<Review> reviewList = dbProxy.getReviews(movieId, userId);
	
			if (reviewList.isEmpty() == false) {
				// User has reviewed
	
				boolean dbAccessRC = dbProxy.deleteReview(reviewList.get(0));
				if (dbAccessRC == true) {
					System.out.println("\nDeleting review successful");
				}
				else {
					System.out.println("\nDeleting review failed");
				}
			}

			retval = "redirect:" + "/review.html";
		}

		return retval;
	}

	// deleting a flagged review; Only Moderator and Admin can do this
	@RequestMapping("/deleteflaggedreview")
	public String deleteFlaggedReview(Model model, @RequestParam String userIdStr,
			@RequestParam String movieIdStr) {

		String retval = "joinus";

		int movieId = Integer.parseInt(movieIdStr);
		int userId = Integer.parseInt(userIdStr);

		// get the review
		Review review = (Review)dbProxy.getReviews(movieId, userId).get(0);

		if (LoginController.isLoggedIn() == true) {				// check if the user is logged in

			// check if the user with appropriate privileges is logged in
			if ((PageController.user.getUserRoleId() == 1)
					|| (PageController.user.getUserRoleId() == 2))
			{
				// delete a review using the above function
				boolean dbAccessRC = dbProxy.deleteReview(review);
				if (dbAccessRC == true) {
					System.out.println("\nDeleting flagged review successful");
				}
				else {
					System.out.println("\nDeleting flagged review failed");
				}
			}
			else
			{
				System.out.println("User is not authorized to delete the flagged review");
			}

			retval = "redirect:" + "/review.html";	// should actually return to the flagged reviews page
		}		
		
		return retval;
	}

	@RequestMapping("/flagreview")
	public String flagReview(Model model, 
			@RequestParam String movieIdStr, @RequestParam String userIdStr) {
		
		String retval = "joinus";
		
		if (LoginController.isLoggedIn() == true) {				// check if the user is logged in
		
			int movieId = Integer.parseInt(movieIdStr);
			int userId = Integer.parseInt(userIdStr);

			// get the review
			Review review = (Review)dbProxy.getReviews(movieId, userId).get(0);
			System.out.println("ReviewId is " + review.getReviewId());
			review.setFlag(true);

			dbProxy.updateReview(review);
			
			retval = "redirect:" + "/review.html";
		}
		
		return retval;
	}
}
