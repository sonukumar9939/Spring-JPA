package code.DataBaseProject.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code.DataBaseProject.Repository.UserRepository;
import code.DataBaseProject.models.User;

@Service
public class UserSetupService {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	public void saveUserDetails(User user) {
		userRepository.save(user);

	}

	public User findById(int id) {
		return userRepository.findUserById(id);
	}

	public User saveEntity(User userDetails, User user) {
		userDetails.setPassword(user.getPassword());
		userDetails.setUsername(user.getUsername());
		userDetails.getUserDetails().setEmailId(user.getUserDetails().getEmailId());
		userDetails.getUserDetails().setFirstName(user.getUserDetails().getFirstName());
		userDetails.getUserDetails().setLastName(user.getUserDetails().getLastName());
		userDetails.getUserDetails().setMobileNo(user.getUserDetails().getMobileNo());
		userDetails.getUserDetails().setUser(userDetails);
		return userDetails;
	}

	public List<User> findUsersByName(String userName) {
		return userRepository.findUsersByName(userName);
	}

	public void deleteEntity(User user) {
		userRepository.delete(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> findUsersByNameAndCreator(String userName, String createdBy) {
		Query q = entityManager.createNamedQuery("User.findUsersByNameAndCreator");
		q.setParameter(1, userName);
		q.setParameter(2, createdBy);

		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<User> findByGivenCaracters(String userName) {
		Query query = entityManager.createNamedQuery("User.findUserNameWithGivenCharacters");
//		query.setFirstResult(6);
		query.setParameter(1, userName);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<User> findByCreationDate(String userName) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		Query query = entityManager.createNamedQuery("User.findUsersByCreatedDate");
		query.setParameter(1, format.parse(userName), TemporalType.TIMESTAMP);
		return query.getResultList();
	}
}
