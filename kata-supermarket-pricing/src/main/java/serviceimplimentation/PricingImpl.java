package serviceimplimentation;

import java.util.LinkedHashMap;

import model.Item;
import model.Offer;
import service.Pricing;

public class PricingImpl implements Pricing{

	public double calculatePrice(Item item, Float numberBought) {
		return (item.getUnitaryPrice() * numberBought);
	}

	public double calculatePricePackage(LinkedHashMap<Item, Offer> lstOffer, Item item, Float numberBought) {

		float numberForReduction = lstOffer.get(item).getQte();
		double reductionValue = lstOffer.get(item).getPrice();

		int timesReductionApplied = (int) (numberBought / numberForReduction);
		double numberOfFullPriceGoods = numberBought % numberForReduction;
		double reducedPrice = timesReductionApplied * reductionValue;
		double unreducedPrice = numberOfFullPriceGoods * item.getUnitaryPrice();
		return reducedPrice + unreducedPrice;
	}

}
