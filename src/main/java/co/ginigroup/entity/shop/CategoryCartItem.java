package co.ginigroup.entity.shop;

import co.ginigroup.entity.discount.Discount;
import co.ginigroup.entity.discount.Discountable;

import java.util.ArrayList;
import java.util.List;

public class CategoryCartItem implements Discountable {
    private List<Discount> discounts = new ArrayList<>();

    private double totalPrice;

    @Override
    public double getTotalPrice() {
        return totalPrice;
    }

    public void incrementPriceWith(CartItem item) {
        this.totalPrice += item.getTotalPriceWithDiscount();
    }

    public void addDiscount(Discount discount) {
        this.discounts.add(discount);
    }

    @Override
    public double getTotalPriceWithDiscount() {
        double totalDiscount = 0;
        for (Discount discount : discounts) {
            totalDiscount += discount.calculate(this);
        }
        return totalDiscount;
    }

    public CategoryCartItem() {

    }

    public CategoryCartItem(List<Discount> discounts) {
        this.discounts = discounts;
    }
}
