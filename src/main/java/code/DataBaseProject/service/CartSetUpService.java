package code.DataBaseProject.service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import code.DataBaseProject.Repository.CartRepository;
import code.DataBaseProject.Repository.ItemsRepository;
import code.DataBaseProject.models.Cart;
import code.DataBaseProject.models.Items;

@Service
public class CartSetUpService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ItemsRepository itemsRepository;
	

	public void saveCartDetails(Cart cart) {
		cartRepository.save(cart);
	}

	public Cart checkIfSessionIsUnique(String session) {
		return cartRepository.checkIfSessionAlreadyExists(session);
	}

	public Cart checkIfSessionIsValid(int id) {

		return cartRepository.findCartById(id);
	}

	public void updateEntity(Cart cart, Cart cartObj, int id ) {
		cartObj.setCartHolder(cart.getCartHolder());
		cartObj.setCartSession(cart.getCartSession());
		 cartObj.setIsActive(cart.getIsActive());
		Set<Items> items = itemsRepository.findItemsByItemId(Integer.valueOf(id));
		items.removeAll(items);
		for (Items sub : cart.getItems()) {
			items.add(new Items(sub.getItemName(), cartObj));
		}
		cartObj.getItems().addAll(items);
		this.saveCartDetails(cartObj);

	}

	public void createEntity(Cart cartObj, Cart cart) {
      cartObj.setCartHolder(cart.getCartHolder());
      cartObj.setCartSession(cart.getCartSession());
      cartObj.setIsActive(cart.getIsActive());
      Set<Items> items = new LinkedHashSet<Items>();
       for(Items item : cart.getItems()) {
    	   items.add(new Items(item.getItemName(), cartObj));
       }
       cartObj.getItems().addAll(items);
		this.saveCartDetails(cartObj);

	}

	public void deleteEntityById(int id) {
		cartRepository.deleteById(id);

	}

	public List<String> findItemsInCart(String cartSession) {
		return cartRepository.findItemsInCart(cartSession);
	}

	public Page<Cart> findCartDetailsByUser(PageRequest request) {
		return cartRepository.findAll(request);
	}

}
