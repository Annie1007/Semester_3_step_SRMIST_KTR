import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Vehicle {
    protected int hours;

    public Vehicle(int hours) {
        this.hours = hours;
    }

    public abstract String getVehicleType();
    public abstract double calculateCharge();
}

class Bike extends Vehicle {
    public Bike(int hours) {
        super(hours);
    }

    @Override
    public String getVehicleType() {
        return "BIKE";
    }

    @Override
    public double calculateCharge() {
        return hours * 10.0;
    }
}

class Car extends Vehicle {
    public Car(int hours) {
        super(hours);
    }

    @Override
    public String getVehicleType() {
        return "CAR";
    }

    @Override
    public double calculateCharge() {
        return 30.0 + (hours - 1) * 20.0;
    }
}

class Truck extends Vehicle {
    public Truck(int hours) {
        super(hours);
    }

    @Override
    public String getVehicleType() {
        return "TRUCK";
    }

    @Override
    public double calculateCharge() {
        return Math.max(100.0, hours * 50.0);
    }
}

public class ParkingChargeCalculator {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            if (!sc.hasNextInt()) return;

            int N = sc.nextInt();
            List<Vehicle> vehicles = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                String type = sc.next();
                int hours = sc.nextInt();

                switch (type) {
                    case "BIKE":
                        vehicles.add(new Bike(hours));
                        break;
                    case "CAR":
                        vehicles.add(new Car(hours));
                        break;
                    case "TRUCK":
                        vehicles.add(new Truck(hours));
                        break;
                }
            }

            double grandTotal = 0.0;
            for (Vehicle v : vehicles) {
                double charge = v.calculateCharge();
                grandTotal += charge;
                System.out.printf("%s: %.2f%n", v.getVehicleType(), charge);
            }

            System.out.printf("Total: %.2f%n", grandTotal);
        }
    }
}