package co.ginigroup.entity.discount;

public class AmountDiscount implements Discount {
    private double minimumValue;
    private double discountValue;

    @Override
    public double getMinimumValue() {
        return minimumValue;
    }

    @Override
    public double getDiscountValue() {
        return discountValue;
    }

    @Override
    public double calculate(Discountable discountable) {
        return discountable.getTotalPrice() > minimumValue ? discountValue : 0;
    }

    public AmountDiscount(double discountValue, double minimumValue) {
        this.minimumValue = minimumValue;
        this.discountValue = discountValue;
    }
}
