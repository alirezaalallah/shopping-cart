package co.ginigroup;

import co.ginigroup.entity.shop.Category;
import co.ginigroup.entity.shop.Product;

public abstract class AbstractShoppingCartTest {
    protected Category food = new Category("Food");
    protected Category electronic = new Category("Electronic");

    protected Product egg = new Product("egg", 10000, food);
    protected Product bread = new Product("bread", 25000, food);
    protected Product chicken = new Product("chicken", 69500, food);

    protected Product tv = new Product("tv", 500000, electronic);
    protected Product mobile = new Product("mobile", 258000, electronic);
}
