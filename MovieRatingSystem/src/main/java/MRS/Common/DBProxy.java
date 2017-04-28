package MRS.Common;

import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import MRS.Model.*;

public class DBProxy implements DBProxyInterface {

	
	private  Session beginSession()
	{
		SessionFactory sessionFactory = HibernateInitializer.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		
		return session;
	}
	
	@Override
	public boolean validateUser(String username , String password)
	{
		Session session = beginSession();
		String queried = "from User where username = :username and password = :password";
		Query query = session.createQuery(queried);
		query.setParameter("username", username);
		query.setParameter("password", password);
		@SuppressWarnings("unchecked")
		List<User> ls = (List<User>)query.getResultList();
		
		if(ls==null || ls.isEmpty())
		{
			return false;
		}
		return true;
	}

	@Override
	public List<User> getUserDetails(String username, String password)
	{
		Session session = beginSession();
		String queried = "from User where username = :username and password = :password";
		Query query = session.createQuery(queried);
		query.setParameter("username", username);
		query.setParameter("password", password);
		@SuppressWarnings("unchecked")
		List<User> ls = (List<User>)query.getResultList();
		return ls;
	}
	
	@Override
	public Movie getMovie(String movieName)
	{
		Session session = beginSession();
		String queried = "from Movie where movieName = :movieName";
		Query query = session.createQuery(queried);
		query.setParameter("movieName", movieName);
//		@SuppressWarnings("unchecked")
		Movie mv= (Movie)query.getResultList().get(0);
		return mv;
	}
	
	@Override
	public List<Movie> getMovies(String genre, int releaseYear, double aggregateRating)
	{
		Session session = beginSession();
		String queried = "from Movie ";
		String checkConditions = new String("");
		if(genre != "")
		{
			checkConditions += "genre = :genre";
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
		if(checkConditions != "")
			queried += "where" + " " + checkConditions;
		queried += checkConditions;
		Query query = session.createQuery(queried);
		query.setParameter("genre", genre);
		query.setParameter("releaseYear", releaseYear);
		query.setParameter("aggregateRating", aggregateRating);
		@SuppressWarnings("unchecked")
		List<Movie> mv= (List<Movie>)query.getResultList();
		return mv;
	}
	
	@Override
	public List<Review> getMovieReviews(int movieId, int userId)
	{
		Session session = beginSession();
		String queried = "from Review where movieId = :movieId";
		String checkConditions = new String("");
		if(movieId != 0)
		{
			checkConditions += "movieId = :movieId";
		}
		if(userId !=0)
		{
			if(checkConditions!="")
				checkConditions += " and ";
			checkConditions += "userId = :userId";
		}
		queried += checkConditions;
		Query query = session.createQuery(queried);
		query.setParameter("movieId", movieId);
		query.setParameter("userId", userId);
		@SuppressWarnings("unchecked")
		List<Review> rv= (List<Review>)query.getResultList();
		return rv;
	}
	
}
