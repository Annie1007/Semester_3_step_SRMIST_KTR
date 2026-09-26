import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class DeliveryRequest {
    protected double weight;
    protected double distance;

    public DeliveryRequest(double weight, double distance) {
        this.weight = weight;
        this.distance = distance;
    }

    public abstract String getDeliveryType();
    public abstract double calculateFee();
}

class StandardDelivery extends DeliveryRequest {
    public StandardDelivery(double weight, double distance) {
        super(weight, distance);
    }

    @Override
    public String getDeliveryType() {
        return "STANDARD";
    }

    @Override
    public double calculateFee() {
        return 5.0 + (0.50 * weight) + (0.10 * distance);
    }
}

class ExpressDelivery extends DeliveryRequest {
    public ExpressDelivery(double weight, double distance) {
        super(weight, distance);
    }

    @Override
    public String getDeliveryType() {
        return "EXPRESS";
    }

    @Override
    public double calculateFee() {
        return 15.0 + (1.00 * weight) + (0.20 * distance);
    }
}

class InternationalDelivery extends DeliveryRequest {
    private double customsFee;

    public InternationalDelivery(double weight, double distance, double customsFee) {
        super(weight, distance);
        this.customsFee = customsFee;
    }

    @Override
    public String getDeliveryType() {
        return "INTERNATIONAL";
    }

    @Override
    public double calculateFee() {
        return 25.0 + (2.00 * weight) + (0.50 * distance) + customsFee;
    }
}

public class DeliveryFeeCalculator {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            if (!sc.hasNextInt()) return;

            int N = sc.nextInt();
            List<DeliveryRequest> requests = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                String type = sc.next();
                double weight = sc.nextDouble();
                double distance = sc.nextDouble();

                if (type.equals("INTERNATIONAL")) {
                    double customsFee = sc.nextDouble();
                    requests.add(new InternationalDelivery(weight, distance, customsFee));
                } else if (type.equals("STANDARD")) {
                    requests.add(new StandardDelivery(weight, distance));
                } else if (type.equals("EXPRESS")) {
                    requests.add(new ExpressDelivery(weight, distance));
                }
            }

            double grandTotal = 0.0;
            for (DeliveryRequest req : requests) {
                double fee = req.calculateFee();
                grandTotal += fee;
                System.out.printf("%s: %.2f%n", req.getDeliveryType(), fee);
            }

            System.out.printf("Total: %.2f%n", grandTotal);
        }
    }
}