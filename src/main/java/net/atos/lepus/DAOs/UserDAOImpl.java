package net.atos.lepus.DAOs;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.atos.lepus.models.User;

import org.jboss.logging.Logger;

public class UserDAOImpl implements UserDAO {

	static Logger logger = Logger.getLogger(UserDAOImpl.class);  

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public int insert(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User find(int id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public boolean update(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<User> findAll() {

		Query query = entityManager.createQuery("from User u");
	    
		@SuppressWarnings("unchecked")
		Collection<User> users = (Collection<User>) query.getResultList();

	    return users;
	}

	@Override
	public User findByName(String name) {
		
		logger.info("findByName in");
		
		String queryString = "from User u where u.name = (:name)";

		Query query = entityManager.createQuery(queryString)
				.setParameter("name", name);
		
		User user = null;
		
		try {
			user = (User) query.getSingleResult();
			
			if (user != null) {
				if (!(user.getName().equals(name))) {
					user = null;
				}
			}
		} catch (NoResultException exception) {
		
		}

		logger.info("findByName out");
		
		return user;
	}

	@Override
	public User findByWibble(String wibble) {
		
		logger.info("findByWibble in");
		
		String queryString = "from User u where u.wibble = (:wibble)";

		Query query = entityManager.createQuery(queryString)
				.setParameter("wibble", wibble);
		
		User user = null;
		
		try {
			user = (User) query.getSingleResult();
			
			if (user != null) {
				if (!(user.getWibble().equals(wibble))) {
					user = null;
				}
			}
		} catch (NoResultException exception) {
		
		}

		logger.info("findByWibble out");
		
		return user;
	}
}
