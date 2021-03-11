package service;

import model.Product;

public class ProductCreator {
    public static final String PRODUCT_URL = "test.data.product.first.url";

    public static Product product(){
        return new Product(TestDataReader.getTestData(PRODUCT_URL), 0);
    }
}
