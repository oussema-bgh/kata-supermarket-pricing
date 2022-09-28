package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Item;
import service.DefaultPricing;
import service.Pricing;

public class TestDefaultPricing {
    private Pricing aDefaultPricing = new DefaultPricing();;

    @Test
    public void should_calculate_price() {
        //given
        Item anItem = new Item("potato", 5);
        int valueToBuy = 2;
        //when
        aDefaultPricing.calculatePrice(anItem, valueToBuy);
        //then
        assertEquals(10,aDefaultPricing.calculatePrice(anItem, valueToBuy),0.001);

    }
}
