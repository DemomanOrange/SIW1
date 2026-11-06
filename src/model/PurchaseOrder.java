package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PurchaseOrder implements Comparable<PurchaseOrder> {
    private Client client;
    private Vehicle vehicle;
    private long orderTimestamp;

    public PurchaseOrder(Client client, Vehicle vehicle) {
        this.client = client;
        this.vehicle = vehicle;
        this.orderTimestamp = System.currentTimeMillis();
    }

    public Client getClient() { return client; }
    public Vehicle getVehicle() { return vehicle; }
    public long getOrderTimestamp() { return orderTimestamp; }


    @Override
    public int compareTo(PurchaseOrder other) {
        return Long.compare(this.orderTimestamp, other.orderTimestamp);
    }

    @Override
    public String toString() {
        return String.format("Заказ{Клиент='%s', VIN='%s %s' (%s), Время=%d}",
                client.getFullName(), vehicle.getBrand(), vehicle.getModel(), vehicle.getVin(), orderTimestamp);
    }
}