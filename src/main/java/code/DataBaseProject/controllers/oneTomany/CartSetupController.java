package code.DataBaseProject.controllers.oneTomany;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import code.DataBaseProject.Exception.FunctionalException;
import code.DataBaseProject.Response.SuccessRestResponse;
import code.DataBaseProject.models.Cart;
import code.DataBaseProject.service.CartSetUpService;

/**
 * @author sonu
 *
 */
@RestController
@RequestMapping(value = "/cart")
public class CartSetupController {
	private static Logger logger = LoggerFactory.getLogger(CartSetupController.class);

	@Autowired
	private CartSetUpService cartSetUpService;
	
	
	@RequestMapping(method=RequestMethod.GET,value="/{cartSession}")
	public ResponseEntity<SuccessRestResponse> getItemsInCart(@PathVariable ("cartSession") String cartSession) throws FunctionalException{
		SuccessRestResponse response = new SuccessRestResponse();
		List<String> items = cartSetUpService.findItemsInCart(cartSession);
		if(CollectionUtils.isEmpty(items)) {
			response.setMessage("Cart With Session No" + cartSession + "does not exist");
			response.setSuccess(false);
			response.setDate(LocalDateTime.now());
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.NOT_FOUND);
		}else {
			response.setMessage("Query Succeded");
			response.setSuccess(true);
			response.setDate(LocalDateTime.now());
			response.setData(items.toString());
;			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.OK);
		}
		
	}

	/**
	 * @param cart
	 * @return
	 * @throws FunctionalException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/save/cart", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SuccessRestResponse> saveCart(@RequestBody Cart cart) throws FunctionalException {
		SuccessRestResponse response = new SuccessRestResponse();

		logger.info("Cart save process initiated");
		Cart carts = cartSetUpService.checkIfSessionIsUnique(cart.getCartSession());
		if (carts != null) {
			response.setSuccess(false);
			response.setMessage("Cart With Session No" + carts.getCartSession() + " " + "already exists");
			response.setDate(LocalDateTime.now());
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		cartSetUpService.createEntity(new Cart(),cart);
		response.setSuccess(true);
		response.setMessage("Items Successfully Saved");
		response.setDate(LocalDateTime.now());
		return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.OK);

	}
	
	/**
	 * @param id
	 * @param cart
	 * @return
	 * @throws FunctionalException
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/update/cart/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<SuccessRestResponse> updateCart(@PathVariable ("id") String id,@RequestBody Cart cart) throws FunctionalException{
		
		SuccessRestResponse response = new SuccessRestResponse();

		logger.info("Cart update process initiated");
		Cart cartObject = cartSetUpService.checkIfSessionIsValid(id);
		if (cartObject == null) {
			response.setSuccess(false);
			response.setMessage("Cart With ID  " + id + " " + "does not exists");
			response.setDate(LocalDateTime.now());
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.NOT_FOUND);
		}
		else {
			cartSetUpService.updateEntity(cart,cartObject,id);
			response.setSuccess(true);
			response.setMessage("cart Successfully Updated");
			response.setDate(LocalDateTime.now());
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.OK);
		}
		
	}
	
	/**
	 * @param id
	 * @return
	 * @throws FunctionalException
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/cart/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<SuccessRestResponse> deleteCart(@PathVariable ("id") String id) throws FunctionalException{
		
		SuccessRestResponse response = new SuccessRestResponse();

		logger.info("Cart delete process initiated");
		Cart cartObj = cartSetUpService.checkIfSessionIsValid(id);
		if (cartObj == null) {
			response.setSuccess(false);
			response.setMessage("Student With ID  " + id + " " + "does not exists");
			response.setDate(LocalDateTime.now());
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.NOT_FOUND);
		}
		else {
			cartSetUpService.deleteEntity(cartObj);
			response.setSuccess(true);
			response.setMessage("cart Successfully deleted");
			response.setDate(LocalDateTime.now());
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.OK);
		}
		
	}
	
}
