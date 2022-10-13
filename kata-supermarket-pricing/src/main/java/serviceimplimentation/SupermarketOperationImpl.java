package serviceimplimentation;

import java.util.LinkedHashMap;
import java.util.Map;

import Exceptions.DataException;
import model.Item;
import model.Offer;
import service.SupermarketOperation;

public class SupermarketOperationImpl implements SupermarketOperation {

    private LinkedHashMap<Item, Offer> reductionValueByNumber = new LinkedHashMap<>();

    public Map<Item, Offer> getReductionValueByNumber() {
        return reductionValueByNumber;
    }

    public void setReductionValueByNumber(Map<Item, Offer> reductionValueByNumber) {
        this.reductionValueByNumber = (LinkedHashMap<Item, Offer>) reductionValueByNumber;
    }

    public Boolean itemPromotionCheck(Item item) {

        if (!reductionValueByNumber.isEmpty()) {
            return reductionValueByNumber.containsKey(item);
        }
        return false;

    }

    public double calculateBill(Map<Item, Float> items, Map<Item, Offer> lstOffer) {
        Double bill = (double) 0;

        bill = processDefaultPricing((LinkedHashMap<Item, Float>) items, bill);
        bill = processPackagePricing((LinkedHashMap<Item, Float>) items, (LinkedHashMap<Item, Offer>) lstOffer, bill);

        return bill;
    }

    private Double processPackagePricing(LinkedHashMap<Item, Float> items, LinkedHashMap<Item, Offer> lstOffer,
            Double bill) {

        return items.entrySet().stream().filter(item -> itemPromotionCheck(item.getKey()))
                .map(item -> new PricingImpl().calculatePricePackage(lstOffer, item.getKey(), item.getValue()))
                .reduce(bill, (f1, f2) -> f1 + f2);
    }

    private Double processDefaultPricing(LinkedHashMap<Item, Float> items, Double bill) {
        return items.entrySet().stream().filter(item -> !itemPromotionCheck(item.getKey()))
                .map(item -> new PricingImpl().calculatePrice(item.getKey(), item.getValue()))
                .reduce(bill, (f1, f2) -> f1 + f2);
    }

    public void addReduction(Item item, Offer offer) throws DataException {
        checkItem(item);
        checkOffer(offer);
        reductionValueByNumber.put(item, offer);
    }

    public void removeReductions(Item item) {
        if (!reductionValueByNumber.isEmpty() && Boolean.TRUE.equals(itemPromotionCheck(item))) {
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
