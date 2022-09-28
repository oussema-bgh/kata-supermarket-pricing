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
        if (returnCart.containsKey(item)) {
        	returnCart.replace(item, numberToBuy +  returnCart.get(item));
        }
        else {
		returnCart.put(item, numberToBuy);
		}
	}

	public void removeFromCart(Item item, Integer numberToReturn)throws RuntimeException {
		// TODO Auto-generated method stub
		 if (returnCart.containsKey(item))
	        {
	            if(returnCart.get(item)<numberToReturn) {
	            	throw new RuntimeException("qte returned not existe in the Cart");
	            }
	            else if(returnCart.get(item)>numberToReturn){
	            	returnCart.replace(item, returnCart.get(item) -  numberToReturn);
	            }
	            else if (returnCart.get(item)==numberToReturn){
	            	returnCart.remove(item);
	            }
	        }
		 else {
         	throw new RuntimeException("Could not find the item");
		 }
	}

	public void emptyTheCart() {
		// TODO Auto-generated method stub
		returnCart.clear();
	}

}
