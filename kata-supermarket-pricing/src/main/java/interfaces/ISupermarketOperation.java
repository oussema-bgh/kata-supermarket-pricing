package interfaces;

import java.util.LinkedHashMap;
import java.util.Optional;

import model.Item;

public interface ISupermarketOperation {

	public Optional<Integer> itemPromotion(Item item) ;
	public double calculateBill(LinkedHashMap<Item, Integer> data);
	public void addReduction(Item item, int numberToBuy, double reduction);
	public void removeReductions(Item item);
}
