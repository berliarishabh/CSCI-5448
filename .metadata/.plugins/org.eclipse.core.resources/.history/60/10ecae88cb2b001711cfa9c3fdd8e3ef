package MRS.Model;

import javax.persistence.*;

@Entity
@Table(name = "Review_tbl")
public class Review {

	/* From class diagram:
	 * id: int
	 * rating: double
	 * comment: String
	 * flag: boolean
	 * userId: int
	 * movieId: int
	 * */

	@Id
	@GeneratedValue
	@Column(name = "reviewId")
	private int reviewId;

	@Column(name = "rating")
	private double rating;

	@Column(name = "comment")
	private String comment;	// do we need a limit on the length?

	@Column(name = "flag")
	private boolean flag;

	@Column(name = "userId")
	private int userId;	// user Id? from user table?

	@Column(name = "movieId")
	private int movieId;

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
	
}
