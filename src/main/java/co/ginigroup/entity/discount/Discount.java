package co.ginigroup.entity.discount;

public interface Discount {
    double getMinimumValue();

    double getDiscountValue();

    double calculate(Discountable discountable);
}
