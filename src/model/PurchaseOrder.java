package model;

public class PurchaseOrder implements Comparable<PurchaseOrder> {
    private Client client;
    private Vehicle vehicle;
    private int priority;

    public PurchaseOrder(Client client, Vehicle vehicle, int priority) {
        this.client = client;
        this.vehicle = vehicle;
        this.priority = priority;
    }

    public Client getClient() { return client; }
    public Vehicle getVehicle() { return vehicle; }
    public int getPriority() { return priority; }

    @Override
    public int compareTo(PurchaseOrder other) {
        return Integer.compare(other.priority, this.priority); 
    }

    @Override
    public String toString() {
        return String.format("Order{Client=%s, VehicleVin=%s, Priority=%d}", client.getFullName(), vehicle.getVin(), priority);
    }
}
