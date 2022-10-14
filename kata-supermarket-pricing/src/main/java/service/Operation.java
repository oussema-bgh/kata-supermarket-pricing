package service;

import java.util.Map;

import Exceptions.DataException;
import Exceptions.ItemNotFoundException;
import Exceptions.QuantityNotAvailableException;
import model.Item;

public interface Operation {

    Map<Item, Float> returnCart();

    void addToCart(Item item, float numberToBuy) throws DataException;

    void removeFromCart(Item item, float numberToBuy)
            throws QuantityNotAvailableException, ItemNotFoundException, DataException;

    void emptyTheCart();
}
