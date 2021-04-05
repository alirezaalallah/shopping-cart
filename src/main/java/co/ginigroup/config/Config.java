package co.ginigroup.config;

import java.util.Locale;
import java.util.ResourceBundle;

public class Config {
    private static final Config instance = new Config();
    private ResourceBundle bundle = ResourceBundle.getBundle("appConfig", Locale.US);

    public static Config getInstance() {
        return instance;
    }

    private Config() {
    }

    public double getCostPerDelivery() {
        return Double.parseDouble(bundle.getString("deliveryCost.deliveryCount.cost"));
    }

    public double getCostPerItems() {
        return Double.parseDouble(bundle.getString("deliveryCost.itemCount.cost"));
    }
}
