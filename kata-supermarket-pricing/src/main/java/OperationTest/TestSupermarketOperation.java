package OperationTest;


import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.Optional;

import org.junit.Test;

import model.Item;
import service.Operation;
import service.SupermarketOperation;

public class TestSupermarketOperation {
	 private SupermarketOperation aSupermarket = new SupermarketOperation();
	 private Operation aCustomer = new Operation();

	 	@Test
	 	public void item_in_Promotion_Check() {
	        Item item = new Item("sweep", 50);
	        aSupermarket.addReduction(item, 2, 10);
			assertEquals(aSupermarket.itemPromotionCheck(item), true );
	 	}

	    @Test
	    public void item_should_correctly_be_updated_when_reduction_set() {
	        //given
	    	
	    	
	        Item item = new Item("sweep", 50);

	        //when
	        aSupermarket.addReduction(item, 2, 10);
			Optional<Integer> numberForReductionn = item.getReductionValueByNumber().entrySet().stream().map(Map.Entry::getKey)
					  .findFirst();
			Optional<Double> reductionValuee = item.getReductionValueByNumber().entrySet().stream().map(Map.Entry::getValue)
					  .findFirst();
	        //then
			assertEquals(numberForReductionn.get(), 2,0.001);
			assertEquals(reductionValuee.get(), 10,0.001);
	    }
	    
	    @Test
	    public void item_reduction_should_correctly_be_replaced_when_new_reduction_set() {
	        //given
 	        Item anItem = new Item( "sweep", 50);
	        aSupermarket.addReduction(anItem, 10, 10.5);

	        //when
	        aSupermarket.addReduction(anItem, 2, 14);

			Optional<Integer> numberForReductionn = anItem.getReductionValueByNumber().entrySet().stream().map(Map.Entry::getKey)
					  .findFirst();
			Optional<Double> reductionValuee = anItem.getReductionValueByNumber().entrySet().stream().map(Map.Entry::getValue)
					  .findFirst();
	        //then
			assertEquals(numberForReductionn.get(), 2,0.001);
			assertEquals(reductionValuee.get(), 14,0.001);
	    }


	    @Test
	    public void item_should_correctly_be_updated_when_reduction_removed() {
	        //given
	        //LinkedHashMap<Integer, Double>
	        Item anItem = new Item("item", 14);
	        anItem.getReductionValueByNumber().put(4, (double) 20);

	        //when
	        aSupermarket.removeReductions(anItem);

	        //then
	        Optional<Integer> numberForReductionn = anItem.getReductionValueByNumber().entrySet().stream().map(Map.Entry::getKey)
					  .findFirst();
			Optional<Integer> reductionValuee = anItem.getReductionValueByNumber().entrySet().stream().map(Map.Entry::getKey)
					  .findFirst();
	        //then
			assertEquals(numberForReductionn.isPresent(), false );
			assertEquals(reductionValuee.isPresent(), false );
	    }
 
	    @Test
	    public void price_should_be_correct_when_calculating_bill() {
	        //given
 	        Item aSweep = new Item(  "sweep", 50);
	        Item aPotato = new Item( "potato", 5);
	        Item aSoap = new Item( "soap", 10);
	        
	        aCustomer.addToCart(aSweep, 4);
	        aCustomer.addToCart(aPotato, 10);
	        aCustomer.addToCart(aSoap, 3);
	        
	        aSupermarket.addReduction(aSoap, 3, 10);

	        //when
	        aSupermarket.calculateBill(aCustomer.returnCart());
	        //then
	        assertEquals(aSupermarket.calculateBill(aCustomer.returnCart()),(260),0.001);
	    } 
}
