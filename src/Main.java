import model.*;
import service.CarDealershipService;
import comparators.VehicleComparators;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CarDealershipService dealership = new CarDealershipService();
        Scanner scanner = new Scanner(System.in);

        // Заполняем автосалон начальными данными
        dealership.addVehicle(new Car("VIN123", "BMW", "X5", 2022, 65000.0, 15000, "SUV"));
        dealership.addVehicle(new Car("VIN456", "Audi", "A6", 2021, 42000.0, 30000, "Sedan"));
        dealership.addVehicle(new Car("VIN789", "BMW", "M3", 2023, 78000.0, 5000, "Sedan"));
        dealership.addVehicle(new Car("VIN999", "Mercedes", "C-Class", 2020, 35000.0, 45000, "Sedan"));

        Client currentClient = null;

        while (true) {
            if (currentClient != null) {
                System.out.println("Текущий пользователь: " + currentClient.getFullName() + " (" + currentClient.getPassportId() + ")");
            } else {
                System.out.println("Текущий пользователь: [НЕ ЗАРЕГИСТРИРОВАН / НЕ ВЫБРАН]");
            }
            System.out.println("1. Зарегистрировать / Сменить клиента");
            System.out.println("2. Посмотреть все доступные автомобили");
            System.out.println("3. Сделать заказ автомобиля (по VIN)");
            System.out.println("4. Обработать следующий заказ в очереди (Менеджер)");
            System.out.println("5. Вывести аналитический отчет");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println(" Некорректный ввод. Введите число!");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Введите ФИО клиента: ");
                    String name = scanner.nextLine();
                    System.out.print("Введите номер паспорта (уникальный ID): ");
                    String passport = scanner.nextLine();
                    System.out.print("Введите номер телефона: ");
                    String phone = scanner.nextLine();

                    currentClient = new Client(passport, name, phone);
                    dealership.registerClient(currentClient);
                    System.out.println("Клиент успешно зарегистрирован и выбран!");
                    break;

                case 2:
                    System.out.println("\nСписок автомобилей в наличии ");
                    var vehicles = dealership.getVehiclesSortedByComparator(new VehicleComparators.PriceComparator());
                    if (vehicles.isEmpty()) {
                        System.out.println("Нет доступных автомобилей.");
                    } else {
                        vehicles.forEach(System.out::println);
                    }
                    break;

                case 3:
                    if (currentClient == null) {
                        System.out.println("Сначала зарегистрируйтесь (Пункт 1)!");
                        break;
                    }
                    System.out.print("Введите VIN автомобиля для заказа: ");
                    String vin = scanner.nextLine().trim();

                    dealership.submitPurchaseOrder(currentClient, vin);
                    break;

                case 4:
                    System.out.println("\nОБРАБОТКА ОЧЕРЕДИ МЕНЕДЖЕРОМ");
                    dealership.processNextOrder();
                    break;

                case 5:
                    dealership.printAnalytics();
                    break;

                case 0:
                    System.out.println("Завершение работы программы.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Неверный пункт меню!");
            }
        }
    }
}