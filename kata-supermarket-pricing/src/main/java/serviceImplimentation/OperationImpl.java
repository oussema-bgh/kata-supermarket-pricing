package serviceImplimentation;

import java.util.LinkedHashMap;

import Exceptions.DataException;
import Exceptions.ItemNotFoundException;
import Exceptions.QuantityNotAvailableException;
import model.Item;
import service.Operation;

public class OperationImpl implements Operation {

	LinkedHashMap<Item, Float> returnCart = new LinkedHashMap<Item, Float>();

	public LinkedHashMap<Item, Float> returnCart() {
		// TODO Auto-generated method stub
		return returnCart;
	}

	public void addToCart(Item item, float numberToBuy) throws DataException {
		// TODO Auto-generated method stub
		
		if (!item.isByWeight() && numberToBuy- (int)numberToBuy  != 0) {
			throw new DataException("could not buy half an item ") ;
			}
		else {
			
		if (returnCart.containsKey(item)) {
			returnCart.replace(item, numberToBuy + returnCart.get(item));
		} else {
			returnCart.put(item, numberToBuy);
		}
		
		}
	}

	public void removeFromCart(Item item, float numberToReturn)
			throws QuantityNotAvailableException, ItemNotFoundException, DataException {
		// TODO Auto-generated method stub
		
		if (!item.isByWeight() && numberToReturn- (int)numberToReturn  != 0) {
			throw new DataException("could not remove half an item ") ;
			}
		
		else {
		
		if (returnCart.containsKey(item)) {
			
			if (returnCart.get(item) < numberToReturn) {
				throw new QuantityNotAvailableException("qte returned not existe in the Cart");
			} 
			else if (returnCart.get(item) > numberToReturn) {
				returnCart.replace(item, returnCart.get(item) - numberToReturn);
			} 
			else if (returnCart.get(item) == numberToReturn) {
				returnCart.remove(item);
			}
		} 
		else {
			throw new ItemNotFoundException("Could not find the item");
		}
		}
	}

	public void emptyTheCart() {
		// TODO Auto-generated method stub
		returnCart.clear();
	}

}
