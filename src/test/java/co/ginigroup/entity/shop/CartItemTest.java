package co.ginigroup.entity.shop;

import co.ginigroup.AbstractShoppingCartTest;
import co.ginigroup.entity.discount.AmountDiscount;
import co.ginigroup.entity.discount.Discount;
import co.ginigroup.entity.discount.PercentDiscount;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CartItemTest extends AbstractShoppingCartTest {

    @Test
    public void testAddProductWithoutDiscount() {
        CartItem item = new CartItem(egg, 10);
        assertEquals(10, item.getQuantity());
        assertEquals(10 * 10000, item.getTotalPrice(), 0.0d);
        assertEquals(10 * 10000, item.getTotalPriceWithDiscount(), 0.0d);
        assertTrue(item.getDiscounts().isEmpty());
    }

    @Test
    public void testAddProductWithDiscount() {
        List<Discount> discounts = new ArrayList<>() {{
            add(new PercentDiscount(10, 100));
        }};

        CartItem item = new CartItem(egg, 10, discounts);
        assertEquals(10, item.getQuantity());
        assertEquals(10 * 10000, item.getTotalPrice(), 0.0d);
        assertEquals(90000, item.getTotalPriceWithDiscount(), 0.0d);
    }

    @Test
    public void testAddProductWithMultipleDiscounts() {
        List<Discount> discounts = new ArrayList<>() {{
            add(new PercentDiscount(10, 100));
            add(new AmountDiscount(100, 100));
        }};

        CartItem item = new CartItem(egg, 10, discounts);
        assertEquals(10, item.getQuantity());
        assertEquals(10 * 10000, item.getTotalPrice(), 0.0d);
        assertEquals(89900, item.getTotalPriceWithDiscount(), 0.0d);
    }

    @Test
    public void testAddProductWithMultipleDiscountsButAppliedOnlyAmountDiscountBecauseOfMinimumValue() {
        List<Discount> discounts = new ArrayList<>() {{
            add(new PercentDiscount(10, 100001));
            add(new AmountDiscount(100, 100));
        }};

        CartItem item = new CartItem(egg, 10, discounts);
        assertEquals(10, item.getQuantity());
        assertEquals(10 * 10000, item.getTotalPrice(), 0.0d);
        assertEquals(99900, item.getTotalPriceWithDiscount(), 0.0d);
    }

}