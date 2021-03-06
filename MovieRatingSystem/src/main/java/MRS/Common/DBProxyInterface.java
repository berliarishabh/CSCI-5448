package MRS.Common;

import java.util.List;

import MRS.Model.Movie;
import MRS.Model.Review;
import MRS.Model.User;

public interface DBProxyInterface {
	
	public boolean validateUser(String username , String password);
	public Movie getMovie(String movieName);
	public User getUserDetails(String username, String password);
	public List<Movie> getMovies(String genre, int releaseYear, double aggregateRating, char approvalState);
	public List<Review> getReviews(int movieId, int userId, int flag);
	public boolean addMovie(Movie mv);
	public boolean addReview(Review rv);
	public boolean addUser(User user);
}
