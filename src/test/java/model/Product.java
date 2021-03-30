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

    private String name;
    private int article;
    private int price;

    public Product(String name, int article, int price){
        this.name = name;
        this.article = article;
        this.price = price;
    }

    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }

    public int getArticle(){ return article; }
    public void setArticle(int article){ this.article = article; }

    public int getPrice(){ return price; }
    public void setPrice(int price){ this.price = price; }
}
