package comparators;

import model.Vehicle;
import java.util.Comparator;

public class VehicleComparators {

    public static class PriceComparator implements Comparator<Vehicle> {
        @Override
        public int compare(Vehicle v1, Vehicle v2) {
            return Double.compare(v1.getPrice(), v2.getPrice());
        }
    }

    public static class YearComparator implements Comparator<Vehicle> {
        @Override
        public int compare(Vehicle v1, Vehicle v2) {
            return Integer.compare(v2.getYear(), v1.getYear());
        }
    }

    public static class BrandAndPriceComparator implements Comparator<Vehicle> {
        @Override
        public int compare(Vehicle v1, Vehicle v2) {
            int brandCompare = v1.getBrand().compareTo(v2.getBrand());
            if (brandCompare != 0) {
                return brandCompare;
            }
            return Double.compare(v1.getPrice(), v2.getPrice());
        }
    }
}
