package MRS.Common;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;

public class HibernateInitializer {
	
	public HibernateInitializer()
	{
		
	}
	
	final static StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
			.configure().build();
	
	private static SessionFactory sessionFactory = null;
	
	private static SessionFactory buildSessionFactory()
	{
		try
		{
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			return sessionFactory;
		}
		catch(Exception e){
			StandardServiceRegistryBuilder.destroy( registry );
	        throw new ExceptionInInitializerError(e);
		}
	}
	
	public static SessionFactory getSessionFactory()
	{
		if(sessionFactory==null)
		{
			buildSessionFactory();
		}
		
		return sessionFactory;
	}

}
