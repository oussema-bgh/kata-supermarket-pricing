package serviceImplimentation;

import java.util.LinkedHashMap;

import Exceptions.DataException;
import model.Item;
import model.Offer;
import service.SupermarketOperation;

public class SupermarketOperationImpl implements SupermarketOperation {

	private LinkedHashMap<Item, Offer> reductionValueByNumber = new LinkedHashMap<Item, Offer>();;

	public LinkedHashMap<Item, Offer> getReductionValueByNumber() {
		return reductionValueByNumber;
	}

	public void setReductionValueByNumber(LinkedHashMap<Item, Offer> reductionValueByNumber) {
		this.reductionValueByNumber = reductionValueByNumber;
	}

	public Boolean itemPromotionCheck(Item item) {

		if (!reductionValueByNumber.isEmpty()) {
			return reductionValueByNumber.containsKey(item);
		}
		return false;
		// boolean result = reductionValueByNumber.entrySet().stream().anyMatch(obj ->
		// obj.getKey().equals(item));
//		return result;
	}

	public double calculateBill(LinkedHashMap<Item, Float> items, LinkedHashMap<Item, Offer> lstOffer) {
		// TODO Auto-generated method stub
		Double bill = (double) 0;

		bill = processDefaultPricing(items, lstOffer, bill);
		bill = processPackagePricing(items, lstOffer, bill);

		return bill;
	}

	private Double processPackagePricing(LinkedHashMap<Item, Float> items, LinkedHashMap<Item, Offer> lstOffer,
			Double bill) {

		return items.entrySet().stream().filter(item -> itemPromotionCheck(item.getKey()))
				.map(item -> new PricingImpl().calculatePricePackage(lstOffer, item.getKey(), item.getValue()))
				.reduce(bill, (f1, f2) -> f1 + f2);
	}

	private Double processDefaultPricing(LinkedHashMap<Item, Float> items, LinkedHashMap<Item, Offer> lstOffer,
			Double bill) {
		return items.entrySet().stream().filter(item -> !itemPromotionCheck(item.getKey()))
				.map(item -> new PricingImpl().calculatePrice(item.getKey(), item.getValue()))
				.reduce(bill, (f1, f2) -> f1 + f2);
	}

	public void addReduction(Item item, Offer offer) throws DataException {
		// TODO Auto-generated method stub
		try {
			checkItem(item);
			checkOffer(offer);
			reductionValueByNumber.put(item, offer);
		} catch (DataException e) {
			throw e ;
			
		}
	}

	public void removeReductions(Item item) {
		// TODO Auto-generated method stub
		if (!reductionValueByNumber.isEmpty() && itemPromotionCheck(item)) {
			reductionValueByNumber.remove(item);
		}

	}

	public void checkItem(Item item) throws DataException {
		if (item.getName() == null || item.getName().trim().equals("") || item.getUnitaryPrice() == 0) {
			throw new DataException("could you check the Item value ");
		}
	}

	public void checkOffer(Offer offer) throws DataException {
		if (offer.getPrice() == 0 || offer.getQte() == 0) {
			throw new DataException("could you check the Offer value ");
		}
	}

}
