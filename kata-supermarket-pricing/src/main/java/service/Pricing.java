package service;

import java.util.Map;

import model.Item;
import model.Offer;

public interface Pricing {
    public double calculatePrice(Item item, Float numberBought);

    public double calculatePricePackage(Map<Item, Offer> lstOffer, Item item, Float numberBought);

}
