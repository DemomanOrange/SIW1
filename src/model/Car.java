package model;

public class Car extends Vehicle {
    private String bodyType;

    public Car(String vin, String brand, String model, int year, double price, int mileage, String bodyType) {
        super(vin, brand, model, year, price, mileage);
        this.bodyType = bodyType;
    }

    public String getBodyType() { return bodyType; }

    @Override
    public String toString() {
        return super.toString().replace("]", ", Body=" + bodyType + "]");
    }
}
