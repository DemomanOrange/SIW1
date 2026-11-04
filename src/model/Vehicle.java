package model;

import java.util.Objects;

public abstract class Vehicle implements Comparable<Vehicle> {
    private String vin;       // Unique business key (Primary Key)
    private String brand;
    private String model;
    private int year;
    private double price;
    private int mileage;

    public Vehicle(String vin, String brand, String model, int year, double price, int mileage) {
        this.vin = vin;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.mileage = mileage;
    }

    public String getVin() { return vin; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public double getPrice() { return price; }
    public int getMileage() { return mileage; }

    public void setPrice(double price) { this.price = price; }
    public void setMileage(int mileage) { this.mileage = mileage; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(vin, vehicle.vin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vin);
    }

    @Override
    public int compareTo(Vehicle other) {
        return this.vin.compareTo(other.vin);
    }

    @Override
    public String toString() {
        return String.format("Vehicle[VIN='%s', %s %s, Year=%d, Price=%.2f$, Mileage=%d km]", 
                vin, brand, model, year, price, mileage);
    }
}
