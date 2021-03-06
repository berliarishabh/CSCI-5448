package MRS.Common;

import javax.persistence.Query;

import java.util.List;

import org.hibernate.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.springframework.format.number.money.CurrencyUnitFormatter;

import MRS.Model.*;

public class DBProxy implements DBProxyInterface {

	
	private  Session beginSession()
	{
		SessionFactory sessionFactory = HibernateInitializer.getSessionFactory();
		Session session = sessionFactory.openSession();
		return session;
	}
	
	public boolean validateUser(String username , String password)
	{
		Session session = beginSession();
		String queried = "from User where username = :username and password = :password";
		Query query = session.createQuery(queried);
		query.setParameter("username", username);
		query.setParameter("password", password);
		@SuppressWarnings("unchecked")
		List<User>ls = (List<User>)query.getResultList();
		session.close();
		if(ls==null || ls.isEmpty())
		{
			return false;
		}
		return true;
	}

	public User getUserDetails(String username, String password)
	{
		Session session = beginSession();
		String queried = "from User where username = :username and password = :password";
		Query query = session.createQuery(queried);
		query.setParameter("username", username);
		query.setParameter("password", password);
		User ls = (User)query.getSingleResult();
		session.close();
		return ls;
	}
	
	public Movie getMovie(String movieName)
	{
		Session session = beginSession();
		String queried = "from Movie where movieName = :movieName";
		Query query = session.createQuery(queried);
		query.setParameter("movieName", movieName);
		Movie mv= (Movie)query.getResultList().get(0);
		session.close();
		return mv;
	}
	
	public List<Movie> getMovies(String genre, int releaseYear, double aggregateRating, char approvalState)
	{
		Session session = beginSession();
		String queried = "from Movie";
		String checkConditions = "";
		if(genre != "")
		{
			checkConditions = "genre = :genre";
		}
		if(releaseYear != 0)
		{
			if(checkConditions!="")
				checkConditions += " and ";
			checkConditions += "releaseYear = :releaseYear";
		}
		if(aggregateRating != 0)
		{
			if(checkConditions!="")
				checkConditions += " and ";
			checkConditions += "aggregateRating = :aggregateRating";
		}
		queried += " where approvalState = :approvalState";
		if(checkConditions != "")
			queried += " and ";
		queried += checkConditions;
		System.out.println("query is " + queried);
		Query query = session.createQuery(queried);
		query.setParameter("approvalState", approvalState);
		if(releaseYear != 0)
			query.setParameter("releaseYear", releaseYear);
		if(genre != "")
			query.setParameter("genre", genre);
		if(aggregateRating != 0)
			query.setParameter("aggregateRating", aggregateRating);
		@SuppressWarnings("unchecked")
		List<Movie> mv= (List<Movie>)query.getResultList();
		session.close();
		return mv;
	}
	
	public List<Review> getReviews(int movieId, int userId, int flag)
	{
		Session session = beginSession();
		String queried = "from Review where flag = :flag";
		String checkConditions = new String("");
		if(movieId != 0)
		{
			checkConditions += " and ";
			checkConditions += "movieId = :movieId";
		}
		if(userId !=0)
		{
			checkConditions += " and ";
			checkConditions += "userId = :userId";
		}
		queried += checkConditions;
		Query query = session.createQuery(queried);
		if(movieId != 0)
			query.setParameter("movieId", movieId);
		if(userId != 0)
			query.setParameter("userId", userId);
		query.setParameter("flag", flag);
		System.out.println("");
		@SuppressWarnings("unchecked")
		List<Review> rv= (List<Review>)query.getResultList();
		session.close();
		return rv;
	}
	
	public boolean addMovie(Movie mv)
	{
		Session session = beginSession();
		mv.setNumberOfCriticsRated(0);
		mv.setNumberOfUsersRated(0);
		mv.setAggregateRating(0);
		Transaction tx = session.beginTransaction();
		try{
			session.save(mv);
			tx.commit();
		}
		catch(Exception e){
			System.out.println("Adding Movie - exception is " + e.getMessage());
			session.close();
			return false;
		}
		session.close();
		return true;
	}
	
	public boolean addReview(Review rv)
	{
		Session session = beginSession();
		Transaction tx = session.beginTransaction();
		try{
			session.save(rv);
			tx.commit();
		}
		catch(Exception e){
			System.out.println("Adding Review - exception is " + e.getMessage());
			session.close();
			return false;
		}
		session.close();
		this.updateRating(rv, true);
		return true;
	}

	public boolean deleteReview(Review rv)
	{
		// update the rating stats for the movie before removing the comment
		this.updateRating(rv, false);

		Session session = beginSession();
		Transaction tx = session.beginTransaction();
		try{
			session.delete(rv);
			tx.commit();
		}
		catch(Exception e){
			System.out.println("Adding Review - exception is " + e.getMessage());
			session.close();
			return false;
		}
		session.close();
		return true;
	}
	
	public boolean updateReview(Review rv)
	{
		Session session = beginSession();
		Transaction tx = session.beginTransaction();
		try{
			session.update(rv);
			tx.commit();
		}
		catch(Exception e){
			System.out.println("Updating review - exception is " + e.getMessage());
			session.close();
			return false;
		}
		session.close();
		return true;		
	}
	
	public boolean addUser(User user)
	{
		Session session = beginSession();
		Transaction tx = session.beginTransaction();
		try{
			session.save(user);
			tx.commit();
		}
		catch(Exception e){
			System.out.println("Adding User - exception is " + e.getMessage());
			session.close();
			return false;
		}
		session.close();
		return true;
	}
	
	public boolean updateMovie(Movie mv)
	{
		Session session = beginSession();
		Transaction tx = session.beginTransaction();
		try{
			session.update(mv);
			tx.commit();
		}
		catch(Exception e){
			System.out.println("Updating movie - exception is " + e.getMessage());
			session.close();
			return false;
		}
		session.close();
		return true;
	}

	public boolean updateRating(Review rv, boolean newReview)
	{
		Session session = beginSession();
		String queried = "from Movie where movieId = :movieId";
		System.out.println("rv.getMovieId() is " + rv.getMovieId());
		Query query = session.createQuery(queried);
		query.setParameter("movieId", rv.getMovieId());
		@SuppressWarnings("unchecked")
		List<Movie> mvLs= (List<Movie>)query.getResultList();
		Movie mv = null;
		if(!mvLs.isEmpty())
			mv = mvLs.get(0);
		else
			return false;
		double userRating = rv.getRating();
		double currRating = mv.getAggregateRating();
		int numOfUsers = mv.getNumberOfUsersRated();
		int numOfCritics = mv.getNumberOfCriticsRated();
		double userWeightedRating = 0;	// this will be weighted
		
		System.out.println("currRating is " + currRating);
		System.out.println("numOfCritics is " + numOfCritics);
		System.out.println("numOfUsers is " + numOfUsers);
		System.out.println("movieName is " + mv.getMovieName());
		
		queried = "from User where userId = :userId";
		query = session.createQuery(queried);
		query.setParameter("userId", rv.getUserId());
		User user= (User)query.getResultList().get(0);
		session.close();

		double currWeightedSum = currRating * ((numOfCritics * 0.6) + (numOfUsers * 0.4));
		System.out.println("currWeightedSum is " + currWeightedSum);
		System.out.println("newReview is " + newReview);

		if (newReview == true)			// add new review
		{
			if(user.getUserRoleId() == 4)
			{
				userWeightedRating = userRating * (0.4);
				numOfUsers++;
				mv.setNumberOfUsersRated(numOfUsers);
			}
			else if(user.getUserRoleId() == 3)
			{
				userWeightedRating = userRating * (0.6);
				numOfCritics++;
				mv.setNumberOfCriticsRated(numOfCritics);
			}
		}
		else							// deleting an existing review
		{
			if(user.getUserRoleId() == 4)
			{
				userWeightedRating = (-1) * userRating * (0.4);
				numOfUsers--;
				mv.setNumberOfUsersRated(numOfUsers);
			}
			else if(user.getUserRoleId() == 3)
			{
				userWeightedRating = (-1) * userRating * (0.6);
				numOfCritics--;
				mv.setNumberOfCriticsRated(numOfCritics);
			}
		}
		double newAggregateRating = 0;
		if((numOfCritics!=0) || (numOfUsers!=0)){
			newAggregateRating = (currWeightedSum + userWeightedRating) / ((numOfCritics * 0.6) + (numOfUsers * 0.4));
			System.out.println("calc newAggRating is " + newAggregateRating);
		}
		System.out.println("userWeightedRating is " + userWeightedRating);
		System.out.println("newAggRating is " + newAggregateRating);
		System.out.println("numOfCritics is " + numOfCritics);
		System.out.println("numOfUsers is " + numOfUsers);
		mv.setAggregateRating(newAggregateRating);
		this.updateMovie(mv);
		return true;

	}
	
}
