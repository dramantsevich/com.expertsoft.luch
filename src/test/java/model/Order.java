package model;

public abstract class Order {
    public enum DeliveryService{
        BY_POST("By post in Belarus"),
        PERSONAL_DELIVERY("Personal delivery Minsk (Free of charge)"),
        PICKUP("Pickup in Minsk");

        private String value;

        DeliveryService(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum PaymentSystem{
        CREDIT_CART_AND_APPLE_PAY("Credit card & Apple Pay"),
        ERIP("SSIS (ERIP)");

        private String value;

        PaymentSystem(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
