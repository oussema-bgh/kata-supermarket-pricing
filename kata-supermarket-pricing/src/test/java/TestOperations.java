
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import Exceptions.DataException;
import Exceptions.ItemNotFoundException;
import Exceptions.QuantityNotAvailableException;
import model.Item;
import serviceimplimentation.OperationImpl;

class TestOperations {

    OperationImpl operation = new OperationImpl();
    Item aChoc, aChips, aWater;

    @Test
    void checkReturnCart() {
        assertEquals(new HashMap<Item, Float>(), operation.returnCart());
    }

    @Test
    void check_to_buy_half_item() throws DataException {
        aChoc = new Item("water", 50, false);
        try {
            operation.addToCart(aChoc, (float) 20.5);
            // operation.removeFromCart(aChoc, 10);
        } catch (DataException e) {
            String expected = "could not buy half an item ";

            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    void should_add_to_cart_when_added() throws DataException {
        // given
        aWater = new Item("water", 50, false);
        aChips = new Item("chips", 5, false);
        aChoc = new Item("chocolate", 100, true);

        operation.addToCart(aChoc, 20);
        operation.addToCart(aChoc, 5);

        assertEquals("{" + aChoc.toString() + "=25.0}", operation.returnCart().toString());

    }

    @Test
    void should_update_value_in_cart_when_added_twice() throws DataException {
        aWater = new Item("water", 50, false);
        aChips = new Item("chips", 5, false);
        aChoc = new Item("chocolate", 100, true);

        operation.addToCart(aChoc, 20);
        operation.addToCart(aChoc, 20);

        assertEquals("40.0", operation.returnCart().get(aChoc).toString());

    }

    @Test
    void should_update_cart_after_remove() throws QuantityNotAvailableException, ItemNotFoundException, DataException {
        aWater = new Item("water", 50, false);
        aChips = new Item("chips", 5, false);
        aChoc = new Item("chocolate", 100, true);

        operation.addToCart(aChoc, 20);
        operation.addToCart(aChoc, 20);

        operation.removeFromCart(aChoc, 10);
        assertEquals("30.0", operation.returnCart().get(aChoc).toString());

    }

    @Test
    void should_remove_from_cart_when_not_existe()
            throws ItemNotFoundException, QuantityNotAvailableException, DataException {
        aWater = new Item("water", 50, false);
        aChips = new Item("chips", 5, false);
        aChoc = new Item("chocolate", 100, true);

        operation.addToCart(aChoc, 20);
        try {
            operation.removeFromCart(aChips, 10);
            // operation.removeFromCart(aChoc, 10);
        } catch (ItemNotFoundException e) {
            String expected = "Could not find the item";

            assertEquals(expected, e.getMessage());
        }

    }

    @Test
    void should_remove_from_cart_when_qte_not_existe()
            throws QuantityNotAvailableException, ItemNotFoundException, DataException {

        aChoc = new Item("chocolate", 50, true);

        operation.addToCart(aChoc, 1);
        try {
            operation.removeFromCart(aChoc, 40);
            // operation.removeFromCart(aChoc, 10);
        } catch (QuantityNotAvailableException e) {
            String expected = "qte returned not existe in the Cart";

            assertEquals(expected, e.getMessage());
        }

    }

    @Test
    void check_to_remove_half_item() throws DataException, QuantityNotAvailableException, ItemNotFoundException {
        aChoc = new Item("water", 50, false);
        try {
            operation.addToCart(aChoc, (float) 20);
            operation.removeFromCart(aChoc, (float) 10.5);

            // operation.removeFromCart(aChoc, 10);
        } catch (DataException e) {
            String expected = "could not remove half an item ";

            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    void should_remove_item_from_cart() throws QuantityNotAvailableException, ItemNotFoundException, DataException {

        aChoc = new Item("chocolate", 50, true);

        operation.addToCart(aChoc, 1);
        operation.removeFromCart(aChoc, 1);
        assertEquals("{}", operation.returnCart().toString());
    }

    @Test
    void should_remove_qte_of_item_from_cart()
            throws QuantityNotAvailableException, ItemNotFoundException, DataException {

        aChoc = new Item("chocolate", 50, true);

        operation.addToCart(aChoc, 5);
        operation.removeFromCart(aChoc, 1);
        assertEquals("{" + aChoc.toString() + "=4.0}", operation.returnCart().toString());

    }

    @Test
    void returnCart() throws DataException {

        aWater = new Item("water", 50, false);
        aChips = new Item("chips", 5, false);
        aChoc = new Item("chocolate", 50, true);

        operation.addToCart(aChoc, 20);
        operation.addToCart(aChips, 20);

        operation.returnCart();

        assertEquals("{" + aChoc.toString() + "=20.0, " + aChips.toString() + "=20.0}",
                operation.returnCart().toString());

    }

    @Test
    void checkAfterEmptingTheCart() {
        operation.emptyTheCart();
        assertEquals("{}", operation.returnCart().toString());

    }

    @Test
    void removeItemFromCart() throws QuantityNotAvailableException, ItemNotFoundException, DataException {
        aChoc = new Item("chocolate", 50, true);
        operation.addToCart(aChoc, 20);
        operation.removeFromCart(aChoc, 20);

        operation.emptyTheCart();
        assertEquals("{}", operation.returnCart().toString());

    }
}
