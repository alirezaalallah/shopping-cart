package co.ginigroup.entity.shop;

import co.ginigroup.entity.discount.Discount;
import co.ginigroup.entity.discount.Discountable;

import java.util.ArrayList;
import java.util.List;

public class CartItem implements Discountable {
    private Product product;
    private int quantity;
    private List<Discount> discounts = new ArrayList<>();

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public CartItem(Product product, int quantity, List<Discount> discounts) {
        this.product = product;
        this.quantity = quantity;
        this.discounts = discounts;
    }

    public double getTotalPriceWithDiscount() {
        return (product.getPrice() * quantity) - getDiscount();
    }

    public double getTotalPrice() {
        return (product.getPrice() * quantity);
    }

    private double getDiscount() {
        double totalDiscount = 0;
        for (Discount discount : discounts) {
            totalDiscount += discount.calculate(this);
        }
        return totalDiscount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }
}
