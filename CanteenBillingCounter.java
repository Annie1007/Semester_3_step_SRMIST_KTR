import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Customer {
    protected double amount;

    public Customer(double amount) {
        this.amount = amount;
    }

    public abstract String getCustomerType();
    public abstract double calculateFinalAmount();
}

class StudentCustomer extends Customer {
    public StudentCustomer(double amount) {
        super(amount);
    }

    @Override
    public String getCustomerType() {
        return "STUDENT";
    }

    @Override
    public double calculateFinalAmount() {
        return amount * 0.90;
    }
}

class StaffCustomer extends Customer {
    public StaffCustomer(double amount) {
        super(amount);
    }

    @Override
    public String getCustomerType() {
        return "STAFF";
    }

    @Override
    public double calculateFinalAmount() {
        return amount * 0.95;
    }
}

class GuestCustomer extends Customer {
    public GuestCustomer(double amount) {
        super(amount);
    }

    @Override
    public String getCustomerType() {
        return "GUEST";
    }

    @Override
    public double calculateFinalAmount() {
        return amount + 10.0;
    }
}

public class CanteenBillingCounter {
    public static void main(String[] args) {
        // try-with-resources closes the scanner automatically and removes the orange warning
        try (Scanner sc = new Scanner(System.in)) {
            if (!sc.hasNextInt()) return;

            int N = sc.nextInt();
            List<Customer> customers = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                String type = sc.next();
                double amount = sc.nextDouble();

                switch (type) {
                    case "STUDENT":
                        customers.add(new StudentCustomer(amount));
                        break;
                    case "STAFF":
                        customers.add(new StaffCustomer(amount));
                        break;
                    case "GUEST":
                        customers.add(new GuestCustomer(amount));
                        break;
                }
            }

            double grandTotal = 0.0;
            for (Customer c : customers) {
                double finalAmount = c.calculateFinalAmount();
                grandTotal += finalAmount;
                System.out.printf("%s: %.2f%n", c.getCustomerType(), finalAmount);
            }

            System.out.printf("Total: %.2f%n", grandTotal);
        }
    }
}