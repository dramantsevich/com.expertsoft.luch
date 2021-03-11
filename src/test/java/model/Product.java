package model;

public class Product {
    private String productURL;
    private int count;

    public Product(String productURL, int count){
        this.productURL = productURL;
        this.count = count;
    }

    public String getProductURL() { return productURL; }
    public void setProductURL(String productURL) {
        this.productURL = productURL;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}
