
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import model.Item;
import model.Offer;
import serviceimplimentation.PricingImpl;
import serviceimplimentation.SupermarketOperationImpl;

class TestPackagePricing {
    private PricingImpl packagePricing = new PricingImpl();;
    private SupermarketOperationImpl superMarkt = new SupermarketOperationImpl();

    @Test
    void should_apply_reduction_when_package_number_needed_reached() {
        // given
        float inputNumberToBuy = 6;
        // LinkedHashMap<Integer, Double>
        Item item = new Item("apple", 14, true);
        Offer offer = new Offer(4, 20);
        superMarkt.getReductionValueByNumber().put(item, offer);
        // when
        double expectedPrice = packagePricing.calculatePricePackage((LinkedHashMap<Item, Offer>) superMarkt.getReductionValueByNumber(), item,
                inputNumberToBuy);
        // then
        assertEquals(48, expectedPrice, 0.001);

    }

}
