import comparators.VehicleComparators;
import model.*;
import service.CarDealershipService;

public class Main {
    public static void main(String[] args) {
        CarDealershipService dealership = new CarDealershipService();

        // Добавление автомобилей
        dealership.addVehicle(new Car("VIN123", "BMW", "X5", 2022, 65000.0, 15000, "SUV"));
        dealership.addVehicle(new Car("VIN456", "Audi", "A6", 2021, 42000.0, 30000, "Sedan"));
        dealership.addVehicle(new Car("VIN789", "BMW", "M3", 2023, 78000.0, 5000, "Sedan"));
        dealership.addVehicle(new Car("VIN999", "Mercedes", "C-Class", 2020, 35000.0, 45000, "Sedan"));

        //Регистрация клиентов
        Client client1 = new Client("AB123456", "Иван Иванов", "+79991112233");
        Client client2 = new Client("CD789012", "Петр Петров", "+79994445566");
        dealership.registerClient(client1);
        dealership.registerClient(client2);

        //Быстрый поиск O(1) по VIN
        System.out.println("Поиск по VIN (VIN789): " + dealership.findVehicleByVin("VIN789"));

        //Демонстрация работы с очередями (Queue)
        System.out.println("\n--- Эмуляция ролевых процессов (Queue) ---");
        dealership.submitPurchaseOrder(client1, "VIN456", 1);
        dealership.submitPurchaseOrder(client2, "VIN789", 5);

        dealership.processNextOrder();
        dealership.processNextOrder();

        //Динамическая сортировка (Comparator)
        System.out.println("\n--- Сортировка по цене (Comparator) ---");
        var sortedByPrice = dealership.getVehiclesSortedByComparator(new VehicleComparators.PriceComparator());
        sortedByPrice.forEach(System.out::println);

        //Вывод аналитики
        dealership.printAnalytics();
    }
}
