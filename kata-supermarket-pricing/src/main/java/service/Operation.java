package service;

import java.util.LinkedHashMap;

import interfaces.IOperation;
import model.Item;

public class Operation implements IOperation {

	LinkedHashMap<Item, Integer> returnCart = new LinkedHashMap<Item, Integer> ();
	
	public LinkedHashMap<Item, Integer> returnCart() {
		// TODO Auto-generated method stub
		return returnCart;
	}

	public void addToCart(Item item, Integer numberToBuy) {
		// TODO Auto-generated method stub
	}

	public void removeFromCart(Item item, Integer numberToReturn)throws Exception {
		// TODO Auto-generated method stub
	}

	public void emptyTheCart() {
		// TODO Auto-generated method stub
	}

}
