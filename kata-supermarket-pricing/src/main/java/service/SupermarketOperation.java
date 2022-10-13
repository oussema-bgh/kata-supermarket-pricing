package service;

import java.util.Map;

import Exceptions.DataException;
import model.Item;
import model.Offer;

public interface SupermarketOperation {

    public Boolean itemPromotionCheck(Item item);

    public double calculateBill(Map<Item, Float> items, Map<Item, Offer> lstOffer);

    public void addReduction(Item item, Offer offer) throws DataException;

    public void removeReductions(Item item);
}
