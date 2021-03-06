package MRS.controllers;

import MRS.Model.Movie;
import MRS.Model.Review;
import MRS.Common.*;
import MRS.controllers.LoginController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
			@RequestParam String comment, @RequestParam String movieName) {

		String retval = "joinus";	// return to the login page

		if (LoginController.isLoggedIn() == true) {				// check if the user is logged in

			double rating 	= Double.parseDouble(ratingStr);
			int userId	 	= LoginController.getUser().getUserId();
			String nameUser = LoginController.getUser().getName();
			
			Movie mv = dbProxy.getMovie(movieName);
			int movieId = mv.getMovieId();
			
			// check if the user has already reviewed
			List<Review> reviewList = dbProxy.getReviews(movieId, userId, 0);

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
	public String deleteReview(Model model, @RequestParam String movieName) {

		String retval = "joinus";	// return to the same page

		System.out.println("Delete review button pressed");
		
		if (LoginController.isLoggedIn() == true) {				// check if the user is logged in
		
			Movie mv = dbProxy.getMovie(movieName);
			int movieId = mv.getMovieId();
			int userId = LoginController.getUser().getUserId();

			// check if the user has already reviewed
			List<Review> reviewList = dbProxy.getReviews(movieId, userId, 0);
	
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
		Review review = (Review)dbProxy.getReviews(movieId, userId, 1).get(0);

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
	public String flagReview(Model model, @RequestParam String flagStr,
			@RequestParam String movieIdStr, @RequestParam String userIdStr) {
		
		String retval = "joinus";
		
		if (LoginController.isLoggedIn() == true) {				// check if the user is logged in
		
			int movieId = Integer.parseInt(movieIdStr);
			int userId = Integer.parseInt(userIdStr);
			int flag = 1;
			int checkFlag = 0;
			if(flagStr == "false"){
				flag = 0; 
				checkFlag = 1;
			}
			else if(flagStr == "true"){
				flag = 1;
				checkFlag = 0;
			}

			// get the review
			Review review = (Review)dbProxy.getReviews(movieId, userId, checkFlag).get(0);
			System.out.println("ReviewId is " + review.getReviewId());
			review.setFlag(flag);

			dbProxy.updateReview(review);
			
			retval = "redirect:" + "/review.html";
		}
		
		return retval;
	}
	
	@RequestMapping("/reviews")
	public @ResponseBody Map<String, Object> reviews(Model model, @RequestParam String flagStr,
			@RequestParam String movieIdStr, @RequestParam String userIdStr) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (LoginController.isLoggedIn() == true) {				// check if the user is logged in
		
			int movieId = Integer.parseInt(movieIdStr);
			int userId = Integer.parseInt(userIdStr);
			int flag = 0;
			if(flagStr == "false")
				flag = 0; 
			else if(flagStr == "true")
				flag = 1;

			// get the review
			
			List<Review> review = (List<Review>)dbProxy.getReviews(movieId, userId, flag);
			System.out.println("ReviewId is " + review.get(0).getReviewId());
			
			map.put("reviewList", review);
		}
		
		return map;
	}
}
