package MRS.Model;

import javax.persistence.*;

@Entity
@Table(name = "Movie_tbl")
public class Movie {
	
	/* From class diagram:
	 * id: int
	 * name: String
	 * releaseYear: int
	 * genre: String
	 * aggregateRating: double
	 * numberOfUsersRated: int
	 * numberOfCriticsRated: int
	 * approvalState: char
	 * */

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movieId")
	private int movieId;
	
	@Column(name = "movieName")
	private String movieName;
	
	@Column(name = "releaseYear")
	private int releaseYear;
	
	@Column(name = "genre")
	private String genre;
	
	@Column(name = "aggregateRating")
	private double aggregateRating;
	
	@Column(name = "numberOfUsersRated")
	private int numberOfUsersRated;
	
	@Column(name = "numberOfCriticsRated")
	private int numberOfCriticsRated;
	
	@Column(name = "approvalState")
	private char approvalState;
	
	@Column(name = "imageLocation")
	private String imageLocation;

	@Column(name = "movieDescription")
	private String movieDescription;

	public String getMovieDescription() {
		return movieDescription;
	}
	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}
	public String getImageLocation() {
		return imageLocation;
	}
	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public double getAggregateRating() {
		return aggregateRating;
	}
	public void setAggregateRating(double aggregateRating) {
		this.aggregateRating = aggregateRating;
	}
	public int getNumberOfUsersRated() {
		return numberOfUsersRated;
	}
	public void setNumberOfUsersRated(int numberOfUsersRated) {
		this.numberOfUsersRated = numberOfUsersRated;
	}
	public int getNumberOfCriticsRated() {
		return numberOfCriticsRated;
	}
	public void setNumberOfCriticsRated(int numberOfCriticsRated) {
		this.numberOfCriticsRated = numberOfCriticsRated;
	}
	public char getApprovalState() {
		return approvalState;
	}
	public void setApprovalState(char approvalState) {
		this.approvalState = approvalState;
	}
	
	public void setElements(String mvName, int rYear, String genre, double aggRating, 
			String imgLoc, char approvalState, String mvDesc)
	{
		this.setAggregateRating(aggRating);
		this.setApprovalState(approvalState);
		this.setGenre(genre);
		this.setMovieName(mvName);
		this.setNumberOfCriticsRated(0);
		this.setNumberOfUsersRated(0);
		this.setReleaseYear(rYear);
		this.setImageLocation(imgLoc);
		this.setMovieDescription(mvDesc);
	}
	
}