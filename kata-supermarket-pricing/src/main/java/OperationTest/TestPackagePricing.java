package OperationTest;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;

import org.junit.Test;

import model.Item;
import service.PackagePricing;

public class TestPackagePricing {
    private PackagePricing aPackagePricing;
    
    @Test
    public void should_apply_reduction_when_package_number_needed_reached() {
        //given
    	Integer inputNumberToBuy=6;
    	
        //LinkedHashMap<Integer, Double>

        Item anItem = new Item("item", 14);
        aPackagePricing = new PackagePricing();
        anItem.getReductionValueByNumber().put(4, (double) 20);

        //when
        double expectedPrice = aPackagePricing.calculatePrice(anItem, inputNumberToBuy);

        //then
        assertEquals(48,expectedPrice,0.001);

    }

}
