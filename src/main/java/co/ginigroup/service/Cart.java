package co.ginigroup.service;

import co.ginigroup.entity.discount.Discount;
import co.ginigroup.entity.discount.Discountable;
import co.ginigroup.entity.shop.*;

import java.util.*;
import java.util.stream.Collectors;

public class Cart implements Discountable {
    private List<CartItem> cartItems = new ArrayList<>();
    private List<Discount> cartDiscounts = new ArrayList<>();
    private DeliveryCostCalculator deliveryCostCalculator = new DefaultDeliveryCalculator();
    private Map<Product, List<Discount>> discountProducts = new HashMap<>();
    private Map<Category, CategoryCartItem> discountCategories = new HashMap<>();

    public Cart(DeliveryCostCalculator deliveryCostCalculator) {
        if (Optional.ofNullable(deliveryCostCalculator).isPresent()) {
            this.deliveryCostCalculator = deliveryCostCalculator;
        }
    }

    public Cart() {
    }

    public void addCartDiscount(Discount discount) {
        Objects.requireNonNull(discount);

        cartDiscounts.add(discount);
    }

    public void addCategoryDiscount(Discount discount, Category category) {
        Objects.requireNonNull(discount);
        Objects.requireNonNull(category);

        if (discountCategories.containsKey(category)) {
            discountCategories.get(category).addDiscount(discount);
        } else {
            discountCategories.put(category,
                    new CategoryCartItem(new ArrayList<>() {{
                        add(discount);
                    }}));
        }
    }

    public void addProductDiscount(Discount discount, Product product) {
        Objects.requireNonNull(discount);
        Objects.requireNonNull(product);

        if (discountProducts.containsKey(product)) {
            discountProducts.get(product).add(discount);
        } else {
            discountProducts.put(product,
                    new ArrayList<>() {{
                        add(discount);
                    }});
        }
    }

    public void addProductToCart(Product product, int quantity) {
        Objects.requireNonNull(product);

        List<Discount> discountList = new ArrayList<>();
        if (discountProducts.containsKey(product)) {
            discountList = discountProducts.get(product);
        }

        CartItem item = new CartItem(product, quantity, discountList);
        cartItems.add(item);
        updateDiscountCategories(item);
    }

    private void updateDiscountCategories(CartItem item) {
        Category category = item.getProduct().getCategory();

        if (discountCategories.containsKey(category)) {
            discountCategories.get(category).incrementPriceWith(item);
        } else {
            CategoryCartItem categoryCartItem = new CategoryCartItem();
            categoryCartItem.incrementPriceWith(item);

            discountCategories.put(category, categoryCartItem);
        }
    }

    @Override
    public double getTotalPrice() {
        double totalAmount = 0;

        for (CartItem item : cartItems) {
            totalAmount += item.getTotalPrice();
        }

        return totalAmount;
    }

    @Override
    public double getTotalPriceWithDiscount() {
        double totalCartDiscount = calculateTotalCartDiscount();
        double totalCategoryDiscount = calculateTotalCategoryDiscount();
        double totalTotalCartItemPriceWithDiscount = calculateTotalCartItemPriceWithDiscount();

        return totalTotalCartItemPriceWithDiscount - totalCartDiscount - totalCategoryDiscount;
    }

    private double calculateTotalCartItemPriceWithDiscount() {
        double totalAmount = 0;

        for (CartItem item : cartItems) {
            totalAmount += item.getTotalPriceWithDiscount();
        }

        return totalAmount;
    }

    private double calculateTotalCategoryDiscount() {
        double totalDiscount = 0;
        for (Map.Entry<Category, CategoryCartItem> itemEntry : discountCategories.entrySet()) {
            totalDiscount += itemEntry.getValue().getTotalPriceWithDiscount();
        }
        return totalDiscount;
    }

    private double calculateTotalCartDiscount() {
        double totalDiscount = 0;
        for (Discount discount : cartDiscounts) {
            totalDiscount += discount.calculate(this);
        }
        return totalDiscount;
    }

    public double getDeliveryCost() {
        return deliveryCostCalculator.calculateDeliveryCost(this);
    }

    public int getTotalItems() {
        return cartItems.stream().collect(Collectors.groupingBy(r -> r.getProduct().getTitle())).size();
    }

    public int getNumberOfProducts() {
        int productCounts = 0;
        for (CartItem item : cartItems) {
            productCounts += item.getQuantity();
        }
        return productCounts;
    }

    public int getNumberOfDelivery() {
        return cartItems.stream().collect(
                Collectors.groupingBy(r -> r.getProduct().getCategory())
        ).size();
    }
}
