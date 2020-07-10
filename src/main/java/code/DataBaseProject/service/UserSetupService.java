package code.DataBaseProject.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import code.DataBaseProject.models.User;

@Service
public class UserSetupService {
	
	private static Logger logger= LoggerFactory.getLogger(UserSetupService.class);

	@Autowired
	@PersistenceContext
	private EntityManager entityManager;

	
	@Transactional
	public void saveUserDetails(User user) {
		entityManager.persist(user);
		entityManager.detach(user); 
	    user.setUsername("room");
	}
	
	@Transactional
	public User findById(int id) {
		TypedQuery<User> query = entityManager.createQuery("Select u from User u where u.Id=?1", User.class);
		query.setParameter(1, id);
		return query.getSingleResult();
	}
	@Transactional
	public User updateEntity(User userDetails, User user) {
		userDetails.setPassword(user.getPassword());
		userDetails.setUsername(user.getUsername());
		userDetails.getUserDetails().setEmailId(user.getUserDetails().getEmailId());
		userDetails.getUserDetails().setFirstName(user.getUserDetails().getFirstName());
		userDetails.getUserDetails().setLastName(user.getUserDetails().getLastName());
		userDetails.getUserDetails().setMobileNo(user.getUserDetails().getMobileNo());
		userDetails.getUserDetails().setUser(userDetails);
		return userDetails;
	}
	@Transactional
	public List<User> findUsersByName(String userName) {
		TypedQuery<User> query = entityManager.createQuery("Select u from User u where u.username=:userName", User.class);
		query.setParameter("userName", userName);
		return query.getResultList();
	}
	
	@Transactional
	public void deleteEntity(User user) {
		entityManager.remove(user);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> findUsersByNameAndCreator(String userName, String createdBy) {
		TypedQuery<User> q = entityManager.createNamedQuery("User.findUsersByNameAndCreator", User.class);
		q.setParameter(1, userName);
		q.setParameter(2, createdBy);

		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> findByGivenCaracters(String userName) {
		Query query = entityManager.createQuery("Select u from User u where u.username LIKE CONCAT('%',?1,'%')");
//		query.setFirstResult(6);
		query.setParameter(1, userName);
		return query.getResultList();
	}
	public void updateUserDetails(User updateEntity) {
		entityManager.merge(updateEntity);
		
	}
}