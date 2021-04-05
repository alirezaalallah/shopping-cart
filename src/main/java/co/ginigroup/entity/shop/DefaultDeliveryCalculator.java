package co.ginigroup.entity.shop;

import co.ginigroup.config.Config;
import co.ginigroup.service.Cart;

public class DefaultDeliveryCalculator implements DeliveryCostCalculator {

    public double calculateDeliveryCost(Cart cart) {
        return getCostForNumberOfDelivery(cart) + getCostForNumberOfItems(cart);
    }

    private double getCostForNumberOfDelivery(Cart cart) {
        double costPerDeliveries = Config.getInstance().getCostPerDelivery();
        return cart.getNumberOfDelivery() * costPerDeliveries;
    }

    private double getCostForNumberOfItems(Cart cart) {
        double costPerItems = Config.getInstance().getCostPerItems();
        return cart.getTotalItems() * costPerItems;
    }


}

