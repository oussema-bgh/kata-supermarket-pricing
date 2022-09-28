package service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import interfaces.ISupermarketOperation;
import model.Item;

public class SupermarketOperation implements ISupermarketOperation{


	
	public Boolean itemPromotionCheck(Item item) {


        return !item.getReductionValueByNumber().isEmpty();
	}
	
	public double calculateBill(LinkedHashMap<Item, Integer> data) {
		// TODO Auto-generated method stub
		Double bill = (double) 0 ;

	        bill = processDefaultPricing(data, bill);
	        bill = processPackagePricing(data, bill);

	        return bill;	}
	
    private Double processPackagePricing(LinkedHashMap<Item, Integer> inputMap, Double bill)
    {
    	for (Item iterable_element : inputMap.keySet()) {
    			System.out.println(iterable_element.toString()+"  "+ itemPromotionCheck(iterable_element));
		}
        return inputMap.entrySet().stream()
                .filter(item -> itemPromotionCheck(item.getKey()))
                .map(item -> new PackagePricing().calculatePrice(item.getKey(), item.getValue()))
                .reduce(bill, (f1, f2) -> f1 + f2);
    }

    private Double processDefaultPricing(LinkedHashMap<Item, Integer> data, Double bill)
    {
    	for (Item iterable_element : data.keySet()) {
			System.out.println(iterable_element.toString()+"  "+ itemPromotionCheck(iterable_element));
	}
        return data.entrySet().stream()
                .filter(item -> !itemPromotionCheck(item.getKey()))
                .map(item -> new DefaultPricing().calculatePrice(item.getKey(), item.getValue()))
                .reduce(bill, (f1, f2) -> f1 + f2);
    }

	public void addReduction(Item item, int numberToBuy, double reduction) {
		// TODO Auto-generated method stub
        Item anItem = new Item("item", 14);
        LinkedHashMap<Integer, Double> data= new LinkedHashMap<Integer, Double>();
        data.put(numberToBuy,  reduction);
        item.setReductionValueByNumber(data);

	}

	public void removeReductions(Item item) {
		// TODO Auto-generated method stub
        item.setReductionValueByNumber( new LinkedHashMap<Integer, Double>());

	}

}
