package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Item;
import service.Operation;

public class TestOperations {
	
	Operation operation = new Operation();
	Item aChoc,aChips,aWater ;
	
    @Test 
    public void should_add_to_cart_when_added()  {
        //given
    	aWater = new Item("water", 50);
        aChips = new Item("chips", 5);
        aChoc = new Item("chocolate", 100);

        operation.addToCart(aChoc, 20 );

        assertEquals("{" + aChoc.toString() + "=20}" , operation.returnCart().toString());

    }
    
   
    
    @Test
    public void should_update_value_in_cart_when_added_twice() {
    	aWater = new Item("water", 50);
        aChips = new Item("chips", 5);
        aChoc = new Item("chocolate", 50);

        operation.addToCart(aChoc, 20 );
        operation.addToCart(aChoc, 20 );

        assertEquals("40" , operation.returnCart().get(aChoc).toString());

    }
    

    @Test
    public void should_remove_from_cart_after_remove() {
    	aWater = new Item("water", 50);
        aChips = new Item("chips", 5);
        aChoc = new Item("chocolate", 50);

        operation.addToCart(aChoc, 20 );
        operation.addToCart(aChoc, 20 );
        try {
        operation.removeFromCart(aChoc, 10);
        assertEquals("30" , operation.returnCart().get(aChoc).toString());

        }catch(RuntimeException e) {
            String expected = "Could not find the item";

            assertEquals( expected, e.getMessage());
        }

    }
    
    @Test
    public void should_remove_from_cart_when_not_existe() {
    	aWater = new Item("water", 50);
        aChips = new Item("chips", 5);
        aChoc = new Item("chocolate", 50);

        operation.addToCart(aChoc, 20 );
        operation.addToCart(aChoc, 20 );
        try {
        operation.removeFromCart(aChips, 10);
        operation.removeFromCart(aChoc, 10);
        }catch(Exception e) {
            String expected = "Could not find the item";

            assertEquals( expected, e.getMessage());
        }


    }
    
    @Test
    public void returnCart() {
    	
    	aWater = new Item("water", 50);
        aChips = new Item("chips", 5);
        aChoc = new Item("chocolate", 50);

        operation.addToCart(aChoc, 20 );
        operation.addToCart(aChips, 20 );
    	
    	operation.returnCart();
    	
        assertEquals("{" + aChoc.toString() + "=20, "+aChips.toString()+"=20}" , operation.returnCart().toString());

    }
}
