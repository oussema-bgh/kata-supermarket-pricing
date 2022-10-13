import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.junit.jupiter.api.Test;

import Exceptions.DataException;
import model.Item;
import model.Offer;
import serviceimplimentation.OperationImpl;
import serviceimplimentation.SupermarketOperationImpl;

class TestSupermarketOperation {
    private SupermarketOperationImpl supermarket = new serviceimplimentation.SupermarketOperationImpl();
    private OperationImpl aCustomer = new OperationImpl();

    @Test
    void checkInsertingOffer() {
        Item item = new Item("sweep", 50, false);
        Offer offer = new Offer(0, 10);
        try {

            supermarket.addReduction(item, offer);
            // operation.removeFromCart(aChoc, 10);
        } catch (DataException exception) {
            String expected = "could you check the Offer value ";

            assertEquals(expected, exception.getMessage());
        }
    }

    @Test
    void checkInsertingItem() {
        Item item = new Item("", 0, false);
        Offer offer = new Offer(5, 10);
        try {
            supermarket.addReduction(item, offer);
        } catch (DataException exception) {
            String expected = "could you check the Item value ";
            assertEquals(expected, exception.getMessage());
        }
    }

    @Test
    void checkReturnItem() {
        Item item = new Item("chocolat", 10, false);
        Offer offer = new Offer(2, 10);
        LinkedHashMap<Item, Offer> reductionValueByNumberTest = new LinkedHashMap<>();
        try {
            supermarket.addReduction(item, offer);
            supermarket.getReductionValueByNumber().replace(item, offer);
            reductionValueByNumberTest.put(item, offer);
            assertEquals(reductionValueByNumberTest, supermarket.getReductionValueByNumber());

        } catch (DataException exception) {
            System.err.println(exception.toString());
        }
    }

    @Test
    void checkUpdatingItem() {
        Item item = new Item("chocolat", 10, false);
        Offer offer = new Offer(2, 10);
        try {
            supermarket.addReduction(item, offer);
            item.setName("banana");
            item.setUnitaryPrice(5);
            item.setByWeight(true);
            supermarket.getReductionValueByNumber().replace(item, offer);
            // operation.removeFromCart(aChoc, 10);
            assertEquals("[" + item.toString() + "]", supermarket.getReductionValueByNumber().keySet().toString());

        } catch (DataException exception) {
            System.err.println(exception.toString());
        }
    }

    @Test
    void checkUpdatingOffer() {
        Item item = new Item("chocolat", 10, true);
        Offer offer = new Offer(2, 10);
        try {
            supermarket.addReduction(item, offer);
            item.setName("banana");
            item.setUnitaryPrice(5);
            offer.setPrice(3);
            offer.setQte(12);
            supermarket.getReductionValueByNumber().replace(item, offer);
            // operation.removeFromCart(aChoc, 10);

            assertEquals("[" + offer.toString() + "]", supermarket.getReductionValueByNumber().values().toString());
        } catch (DataException exception) {
            System.err.println(exception.toString());
        }
    }

    @Test
    void item_in_Promotion_Check() throws DataException {
        Item item = new Item("sweep", 50, true);
        Offer offer = new Offer(2, 10);
        supermarket.addReduction(item, offer);
        assertEquals(Boolean.TRUE ,supermarket.itemPromotionCheck(item));
    }

    @Test
    void item_not_in_Promotion_Check() throws DataException {
        Item item = new Item("sweep", 50, true);
        Item aPotato = new Item("potato", 5, true);

        Offer offer = new Offer(2, 10);
        supermarket.addReduction(item, offer);
        assertEquals(Boolean.FALSE,supermarket.itemPromotionCheck(aPotato) );
    }

    @Test
    void noOfferFound() throws DataException {
        Item aPotato = new Item("potato", 5, true);
        assertEquals(Boolean.FALSE,supermarket.itemPromotionCheck(aPotato) );
    }

    @Test
    void item_should_correctly_be_updated_when_reduction_set() throws DataException {
        // given

        Item item = new Item("sweep", 50, Boolean.TRUE);
        Offer offer = new Offer(2, 10);

        // when
        supermarket.addReduction(item, offer);
        float numberForReductionn = supermarket.getReductionValueByNumber().get(item).getQte();
        Double reductionValuee = supermarket.getReductionValueByNumber().get(item).getPrice();
        // then
        assertEquals(2, numberForReductionn, 0.001);
        assertEquals(10,reductionValuee, 0.001);
    }

    @Test
    void item_reduction_should_correctly_be_replaced_when_new_reduction_set() throws DataException {
        // given
        Item item = new Item("sweep", 50, true);
        Offer offer1 = new Offer(10, 10.5);

        supermarket.addReduction(item, offer1);
        Offer offer2 = new Offer(2, 14);

        // when
        supermarket.addReduction(item, offer2);

        float numberForReductionn = supermarket.getReductionValueByNumber().get(item).getQte();
        Double reductionValuee = supermarket.getReductionValueByNumber().get(item).getPrice();
        // then
        assertEquals(2, numberForReductionn, 0.001);
        assertEquals(14, reductionValuee, 0.001);
    }

    @Test
    void item_should_correctly_not_be_updated() throws DataException {
        // given
        // LinkedHashMap<Integer, Double>
        Item sweep = new Item("sweep", 50, true);

        Item item = new Item("item", 14, false);
        Offer offer = new Offer(4, 20);

        supermarket.addReduction(item, offer);

        // when
        supermarket.removeReductions(sweep);

        // using stream and lambda to search
        Optional<Offer> offerSearched = supermarket.getReductionValueByNumber().entrySet().stream()
                .filter(obj -> obj.getKey().equals(item)).map(Map.Entry::getValue).findFirst();

        // then
        assertEquals(Boolean.TRUE, offerSearched.isPresent());
    }
    
    @Test
    void item_should_not_be_updated() throws DataException {
        // given
        // LinkedHashMap<Integer, Double>
        Item sweep = new Item("sweep", 50, true);

        Item item = new Item("item", 14, false);

        // when
        supermarket.removeReductions(sweep);

        // using stream and lambda to search
        Optional<Offer> offerSearched = supermarket.getReductionValueByNumber().entrySet().stream()
                .filter(obj -> obj.getKey().equals(item)).map(Map.Entry::getValue).findFirst();

        // then
        assertEquals(Boolean.FALSE, offerSearched.isPresent());
    }
    @Test
    void item_should_correctly_be_updated_when_reduction_removed() throws DataException {
        // given
        // LinkedHashMap<Integer, Double>
        Item item = new Item("item", 14, false);
        Offer offer = new Offer(4, 20);

        supermarket.addReduction(item, offer);

        // when
        supermarket.removeReductions(item);

        // using stream and lambda to search
        Optional<Offer> offerSearched = supermarket.getReductionValueByNumber().entrySet().stream()
                .filter(obj -> obj.getKey().equals(item)).map(Map.Entry::getValue).findFirst();

        // then
        assertEquals(Boolean.FALSE, offerSearched.isPresent());
    }

    @Test
    void price_should_be_correct_when_calculating_bill() throws DataException {
        // given
        Item aSweep = new Item("sweep", 50, true);
        Item aPotato = new Item("potato", 5, true);
        Item aSoap = new Item("soap", 10, false);

        aCustomer.addToCart(aSweep, 4);
        aCustomer.addToCart(aPotato, 10);
        aCustomer.addToCart(aSoap, 3);

        Offer offer = new Offer(3, 10);
        // when
        supermarket.addReduction(aSoap, offer);

        // then
        assertEquals(260.0,supermarket.calculateBill(aCustomer.returnCart(), (LinkedHashMap<Item, Offer>) supermarket.getReductionValueByNumber()), 0.001);
    }
}
