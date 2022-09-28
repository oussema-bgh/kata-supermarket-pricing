package service;

import java.util.Map;
import java.util.Optional;

import model.Item;

public class PackagePricing extends Pricing {

	@Override
	public double calculatePrice(Item item, Integer numberBought) {
		// TODO Auto-generated method stub
		 
		Optional<Integer> numberForReductionn = item.getReductionValueByNumber().entrySet().stream().map(Map.Entry::getKey)
		  .findFirst();
		Optional<Double> reductionValuee = item.getReductionValueByNumber().entrySet().stream().map(Map.Entry::getValue)
		  .findFirst();
		  
		  int numberForReduction =   numberForReductionn.get();
		  double reductionValue = reductionValuee.get();
		  
	        int timesReductionApplied = (int) (numberBought / numberForReduction);
	        double numberOfFullPriceGoods = numberBought % numberForReduction;
	        double reducedPrice = timesReductionApplied *  reductionValue;
	        double unreducedPrice = numberOfFullPriceGoods * item.getUnitaryPrice();
	        return reducedPrice + unreducedPrice;
}

}
