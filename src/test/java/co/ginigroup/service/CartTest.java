package co.ginigroup.service;

import co.ginigroup.AbstractShoppingCartTest;
import co.ginigroup.entity.discount.AmountDiscount;
import co.ginigroup.entity.discount.PercentDiscount;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CartTest extends AbstractShoppingCartTest {

    @Test
    public void testAddProductsToShoppingCartWithoutDiscounts() {
        Cart cart = new Cart();

        cart.addProductToCart(egg, 10);
        cart.addProductToCart(bread, 50);

        assertEquals(60, cart.getNumberOfProducts());
        assertEquals(1, cart.getNumberOfDelivery());
        assertEquals(2, cart.getTotalItems());
        assertEquals(1350000.0, cart.getTotalPrice(), 0.0d);
        assertEquals(1350000.0, cart.getTotalPriceWithDiscount(), 0.0d);
    }

    @Test
    public void testAddProductsToShoppingCartWithDiscountBasedOnAmount() {
        Cart cart = new Cart();

        cart.addCategoryDiscount(new AmountDiscount(100, 50), food);

        cart.addProductToCart(egg, 10);
        cart.addProductToCart(bread, 50);
        cart.addProductToCart(tv, 1);

        assertEquals(61, cart.getNumberOfProducts());
        assertEquals(2, cart.getNumberOfDelivery());
        assertEquals(3, cart.getTotalItems());
        assertEquals(1850000, cart.getTotalPrice(), 0.0d);
        assertEquals(1849900, cart.getTotalPriceWithDiscount(), 0.0d);
    }

    @Test
    public void testAddProductsToShoppingCartWithDiscountBasedOnPercentage() {
        Cart cart = new Cart();

        cart.addCategoryDiscount(new PercentDiscount(10, 50), food);

        cart.addProductToCart(egg, 10);
        cart.addProductToCart(bread, 50);
        cart.addProductToCart(tv, 1);

        assertEquals(61, cart.getNumberOfProducts());
        assertEquals(2, cart.getNumberOfDelivery());
        assertEquals(3, cart.getTotalItems());
        assertEquals(1850000, cart.getTotalPrice(), 0.0d);
        assertEquals(1715000, cart.getTotalPriceWithDiscount(), 0.0d);
    }

    @Test
    public void testAddProductsToShoppingCartWithDiscountBasedOnAmountAndDoNotApply() {
        Cart cart = new Cart();

        cart.addCategoryDiscount(new AmountDiscount(100, 2000000), food);

        cart.addProductToCart(egg, 10);
        cart.addProductToCart(bread, 50);
        cart.addProductToCart(tv, 1);

        assertEquals(61, cart.getNumberOfProducts());
        assertEquals(2, cart.getNumberOfDelivery());
        assertEquals(3, cart.getTotalItems());
        assertEquals(1850000.0, cart.getTotalPrice(), 0.0d);
        assertEquals(1850000.0, cart.getTotalPriceWithDiscount(), 0.0d);
    }

    @Test
    public void testAddProductsToShoppingCartWithDiscountBasedOnPercentageAndDoNotApply() {
        Cart cart = new Cart();

        cart.addCategoryDiscount(new PercentDiscount(10, 2000000), food);

        cart.addProductToCart(egg, 10);
        cart.addProductToCart(bread, 50);
        cart.addProductToCart(tv, 1);

        assertEquals(61, cart.getNumberOfProducts());
        assertEquals(2, cart.getNumberOfDelivery());
        assertEquals(3, cart.getTotalItems());
        assertEquals(1850000.0, cart.getTotalPrice(), 0.0d);
        assertEquals(1850000.0, cart.getTotalPriceWithDiscount(), 0.0d);
    }

    @Test
    public void testAddProductsToShoppingCartWithDiscountOnCartBasedOnAmount() {
        Cart cart = new Cart();

        cart.addCartDiscount(new AmountDiscount(100, 100));

        cart.addProductToCart(egg, 10);
        cart.addProductToCart(bread, 50);
        cart.addProductToCart(tv, 1);

        assertEquals(61, cart.getNumberOfProducts());
        assertEquals(2, cart.getNumberOfDelivery());
        assertEquals(3, cart.getTotalItems());
        assertEquals(1850000.0, cart.getTotalPrice(), 0.0d);
        assertEquals(1849900.0, cart.getTotalPriceWithDiscount(), 0.0d);
    }

    @Test
    public void testAddProductsToShoppingCartWithDiscountOnCartBasedOnPercentage() {
        Cart cart = new Cart();

        cart.addCartDiscount(new PercentDiscount(10, 100));

        cart.addProductToCart(egg, 10);
        cart.addProductToCart(bread, 50);
        cart.addProductToCart(tv, 1);

        assertEquals(61, cart.getNumberOfProducts());
        assertEquals(2, cart.getNumberOfDelivery());
        assertEquals(3, cart.getTotalItems());
        assertEquals(1850000.0, cart.getTotalPrice(), 0.0d);
        assertEquals(1665000.0, cart.getTotalPriceWithDiscount(), 0.0d);
    }

    @Test
    public void testAddProductsToShoppingCartWithDiscountOnCartBasedOnAmountAndDoNotApply() {
        Cart cart = new Cart();

        cart.addCartDiscount(new AmountDiscount(100, 2000000));

        cart.addProductToCart(egg, 10);
        cart.addProductToCart(bread, 50);
        cart.addProductToCart(tv, 1);

        assertEquals(61, cart.getNumberOfProducts());
        assertEquals(2, cart.getNumberOfDelivery());
        assertEquals(3, cart.getTotalItems());
        assertEquals(1850000.0, cart.getTotalPrice(), 0.0d);
        assertEquals(1850000.0, cart.getTotalPriceWithDiscount(), 0.0d);
    }

    @Test
    public void testAddProductsToShoppingCartWithDiscountOnCartBasedOnPercentageAndDoNotApply() {
        Cart cart = new Cart();

        cart.addCartDiscount(new PercentDiscount(10, 2000000));

        cart.addProductToCart(egg, 10);
        cart.addProductToCart(bread, 50);
        cart.addProductToCart(tv, 1);

        assertEquals(61, cart.getNumberOfProducts());
        assertEquals(2, cart.getNumberOfDelivery());
        assertEquals(3, cart.getTotalItems());
        assertEquals(1850000.0, cart.getTotalPrice(), 0.0d);
        assertEquals(1850000.0, cart.getTotalPriceWithDiscount(), 0.0d);
    }

    @Test
    public void testAddProductToShoppingCartWithAllTypeOfDiscount() {
        Cart cart = new Cart();

        cart.addCartDiscount(new AmountDiscount(50000, 100));
        cart.addProductDiscount(new PercentDiscount(10, 100), egg);
        cart.addCategoryDiscount(new AmountDiscount(500, 100), electronic);

        cart.addProductToCart(egg, 10);
        cart.addProductToCart(bread, 50);
        cart.addProductToCart(tv, 1);

        assertEquals(61, cart.getNumberOfProducts());
        assertEquals(2, cart.getNumberOfDelivery());
        assertEquals(3, cart.getTotalItems());
        assertEquals(1850000, cart.getTotalPrice(), 0.0d);
        assertEquals(1789500, cart.getTotalPriceWithDiscount(), 0.0d);
    }

    @Test(expected = NullPointerException.class)
    public void testIfAddProductCartWithNullParameters() {
        Cart cart = new Cart();
        cart.addProductToCart(null, 10);
    }

    @Test
    public void testIfAddProductCartAndCalculateDeliveryCost() {
        Cart cart = new Cart();

        cart.addProductToCart(egg, 10);
        cart.addProductToCart(bread, 50);

        assertEquals(60, cart.getNumberOfProducts());
        assertEquals(1, cart.getNumberOfDelivery());
        assertEquals(2, cart.getTotalItems());
        assertEquals(1350000.0, cart.getTotalPrice(), 0.0d);
        assertEquals(1350000.0, cart.getTotalPriceWithDiscount(), 0.0d);
        assertEquals(300, cart.getDeliveryCost(), 0.0d);
    }
}