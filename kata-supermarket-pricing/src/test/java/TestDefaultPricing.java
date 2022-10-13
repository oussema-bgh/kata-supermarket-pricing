


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import model.Item;
import serviceimplimentation.PricingImpl;

public class TestDefaultPricing {
	private PricingImpl defaultPricing = new PricingImpl();;

	@Test
	void should_calculate_price() {
		// given
		Item anItem = new Item("potato", 5, true);
		float valueToBuy = 2;
		// when
		defaultPricing.calculatePrice(anItem, valueToBuy);
		// then
		assertEquals(10, defaultPricing.calculatePrice(anItem, valueToBuy), 0.001);

	}
}
