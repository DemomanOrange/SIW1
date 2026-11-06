package service;

import model.*;
import java.util.*;

public class CarDealershipService {
    private final List<Vehicle> vehicleList = new ArrayList<>();
    private final Set<Client> registeredClients = new HashSet<>();
    private final Map<String, Vehicle> vinIndexMap = new HashMap<>();
    private final Map<String, List<Vehicle>> brandCategoryMap = new HashMap<>();
    private final Queue<PurchaseOrder> orderQueue = new PriorityQueue<>();

    public boolean addVehicle(Vehicle vehicle) {
        if (vinIndexMap.containsKey(vehicle.getVin())) {
            return false;
        }
        vehicleList.add(vehicle);
        vinIndexMap.put(vehicle.getVin(), vehicle);
        brandCategoryMap.computeIfAbsent(vehicle.getBrand(), k -> new ArrayList<>()).add(vehicle);
        return true;
    }

    public boolean removeVehicle(String vin) {
        Vehicle vehicle = vinIndexMap.get(vin);
        if (vehicle == null) return false;

        vehicleList.remove(vehicle);
        vinIndexMap.remove(vin);

        List<Vehicle> brandList = brandCategoryMap.get(vehicle.getBrand());
        if (brandList != null) {
            brandList.remove(vehicle);
            if (brandList.isEmpty()) {
                brandCategoryMap.remove(vehicle.getBrand());
            }
        }
        return true;
    }

    public void registerClient(Client client) {
        registeredClients.add(client);
    }

    public void submitPurchaseOrder(Client client, String vin) {
        Vehicle vehicle = findVehicleByVin(vin);
        if (vehicle != null) {
            PurchaseOrder order = new PurchaseOrder(client, vehicle);
            orderQueue.offer(order);
            System.out.println("[Успешно] Заявка создана: " + order);
        } else {
            System.out.println("Автомобиль с VIN " + vin + " не найден.");
        }
    }

    public void processNextOrder() {
        PurchaseOrder order = orderQueue.poll();
        if (order != null) {
            System.out.println("[Менеджер] Обработка заявки: " + order);
            removeVehicle(order.getVehicle().getVin());
            System.out.println("[Менеджер] Автомобиль продан и снят с учета.");
        } else {
            System.out.println("[Менеджер] Очередь заявок пуста.");
        }
    }

    public Vehicle findVehicleByVin(String vin) {
        return vinIndexMap.get(vin);
    }

    public List<Vehicle> getVehiclesByBrand(String brand) {
        return brandCategoryMap.getOrDefault(brand, Collections.emptyList());
    }

    public List<Vehicle> filterByMaxPrice(double maxPrice) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle v : vehicleList) {
            if (v.getPrice() <= maxPrice) {
                result.add(v);
            }
        }
        return result;
    }

    public List<Vehicle> getVehiclesSortedByComparator(Comparator<Vehicle> comparator) {
        List<Vehicle> sortedList = new ArrayList<>(vehicleList);
        sortedList.sort(comparator);
        return sortedList;
    }

    public void printAnalytics() {
        System.out.println("\nАНАЛИТИЧЕСКИЙ ОТЧЕТ АВТОСАЛОНА");
        System.out.println("Всего доступно машин: " + vehicleList.size());
        System.out.println("Всего зарегистрированных клиентов: " + registeredClients.size());
        System.out.println("Активных заявок в очереди: " + orderQueue.size());
        
        double totalInventoryCost = vehicleList.stream().mapToDouble(Vehicle::getPrice).sum();
        System.out.printf("Общая стоимость складских запасов: %.2f$\n", totalInventoryCost);
    }
}
