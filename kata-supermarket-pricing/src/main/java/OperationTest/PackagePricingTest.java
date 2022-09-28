package OperationTest;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;

import org.junit.Test;

import model.Item;
import service.PackagePricing;

public class PackagePricingTest {
    private PackagePricing aPackagePricing;
    
    @Test

    public void should_apply_reduction_when_package_number_needed_reached() {
        //given
    	Integer inputNumberToBuy=6;
    	
        //LinkedHashMap<Integer, Double>
        LinkedHashMap<Integer, Double>  inputReduction =  new LinkedHashMap<Integer, Double>();
        inputReduction.put(4, (double) 20);
        Item anItem = new Item("item", 14);
        aPackagePricing = new PackagePricing();

        //when
        double expectedPrice = aPackagePricing.calculatePrice(anItem, inputNumberToBuy);

        //then
        assertEquals("44",expectedPrice);
    }

}
