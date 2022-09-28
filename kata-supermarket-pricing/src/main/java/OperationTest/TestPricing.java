package OperationTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Item;
import service.DefaultPricing;
import service.Pricing;

public class TestPricing {
    private Pricing aDefaultPricing;

    @Test
    public void should_calculate_price() {
        //given
        Item anItem = new Item("potato", 5);
        aDefaultPricing = new DefaultPricing();
        int valueToBuy = 2;

        //when
        aDefaultPricing.calculatePrice(anItem, valueToBuy);

        //then
        assertEquals("10",aDefaultPricing.calculatePrice(anItem, valueToBuy));

    }
}
