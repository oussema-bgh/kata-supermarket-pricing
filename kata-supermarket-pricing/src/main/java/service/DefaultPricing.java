package service;

import model.Item;

public class DefaultPricing extends Pricing{

	@Override
	public double calculatePrice(Item item, Integer numberBought) {
		// TODO Auto-generated method stub
        return (item.getUnitaryPrice() * numberBought);
	        }

}
