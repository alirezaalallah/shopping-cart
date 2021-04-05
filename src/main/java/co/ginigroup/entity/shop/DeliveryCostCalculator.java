package co.ginigroup.entity.shop;

import co.ginigroup.service.Cart;

public interface DeliveryCostCalculator {
    double calculateDeliveryCost(Cart cart);
}
