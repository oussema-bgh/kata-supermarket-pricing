package interfaces;

import java.util.LinkedHashMap;

import model.Item;

public interface IOperation {

	LinkedHashMap<Item, Integer> returnCart();
	
	void addToCart(Item item, Integer numberToBuy) ;

	void removeFromCart(Item item, Integer numberToBuy) throws Exception;
	
	void emptyTheCart () ;
}
