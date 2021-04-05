package co.ginigroup.entity.discount;

public class PercentDiscount implements Discount {
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
        return discountable.getTotalPrice() > minimumValue ? (discountable.getTotalPrice() * discountValue) / 100 : 0;
    }

    public PercentDiscount(double discountValue, double minimumValue) {
        this.minimumValue = minimumValue;
        this.discountValue = discountValue;
    }
}
