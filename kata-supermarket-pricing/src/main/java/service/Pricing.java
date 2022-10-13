package service;

import java.util.LinkedHashMap;

import model.Item;
import model.Offer;

public interface Pricing {
    public double calculatePrice(Item item, Float numberBought);

    public double calculatePricePackage(LinkedHashMap<Item, Offer> lstOffer, Item item, Float numberBought);

}
