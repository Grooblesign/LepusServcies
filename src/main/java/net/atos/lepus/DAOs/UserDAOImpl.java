package net.atos.lepus.DAOs;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.atos.lepus.models.User;

import org.jboss.logging.Logger;

@Stateless
public class UserDAOImpl implements UserDAO {

	static Logger logger = Logger.getLogger(UserDAOImpl.class);  

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public int insert(User user) {
		logger.info("insert in");
		
		entityManager.persist(user);

		logger.info("insert out");

		return user.getId();
	}

	@Override
	public boolean delete(int id) {
		entityManager.remove(find(id));
		return false;
	}

	@Override
	public User find(int id) {
		logger.info("find in");
		
		User user = entityManager.find(User.class, id);

		logger.info("user = " + user);

		logger.info("find out");
		
		return user;
	}

	@Override
	public boolean update(User user) {
		logger.info("update in");
		
		entityManager.merge(user);

		logger.info("update out");

		return true;
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
			
			if (user != null && !user.getName().equals(name)) {
				user = null;
			}
		} catch (NoResultException exception) {
			user = null;
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
			
			if (user != null && !user.getWibble().equals(wibble)) {
				user = null;
			}
		} catch (NoResultException exception) {
			user = null;
		}

		logger.info("findByWibble out");
		
		return user;
	}
}
