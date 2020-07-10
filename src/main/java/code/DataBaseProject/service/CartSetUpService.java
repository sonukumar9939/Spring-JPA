package code.DataBaseProject.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Cart checkIfSessionIsValid(String id) {

		return cartRepository.findCartById(id);
	}

	public void updateEntity(Cart cart, Cart cartObj, String id ) {
		cartObj.setCartHolder(cart.getCartHolder());
		cartObj.setCartSession(cart.getCartSession());
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
       Set<Items> items= new HashSet<Items>();
       for (Items sub : cart.getItems()) {
			items.add(new Items(sub.getItemName(), cartObj));
		}
		cartObj.getItems().addAll(items);
		this.saveCartDetails(cartObj);

	}

	public void deleteEntity(Cart studObject) {
		cartRepository.delete(studObject);

	}

	public List<String> findItemsInCart(String cartSession) {
		return cartRepository.findItemsInCart(cartSession);
	}

}
