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
		List<User> ls = (List<User>)query.getResultList();
		
		if(ls==null || ls.isEmpty())
		{
			return false;
		}
		return true;
	}

	
}
