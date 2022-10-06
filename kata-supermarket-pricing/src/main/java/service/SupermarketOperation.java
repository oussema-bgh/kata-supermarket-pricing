package service;

import java.util.LinkedHashMap;
import java.util.Optional;

import Exceptions.DataException;
import model.Item;
import model.Offer;

public interface SupermarketOperation {

	public Boolean itemPromotionCheck(Item item);

	public double calculateBill(LinkedHashMap<Item, Float> items,LinkedHashMap<Item, Offer> lstOffer);

	public void addReduction(Item item, Offer offer)  throws DataException;

	public void removeReductions(Item item);
}
