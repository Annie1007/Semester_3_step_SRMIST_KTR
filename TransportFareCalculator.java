import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class TransportJourney {
    protected double distance;

    public TransportJourney(double distance) {
        this.distance = distance;
    }

    public abstract String getTransportType();
    public abstract double calculateFare();
}

class BusJourney extends TransportJourney {
    public BusJourney(double distance) {
        super(distance);
    }

    @Override
    public String getTransportType() {
        return "BUS";
    }

    @Override
    public double calculateFare() {
        return Math.min(10.0, 2.0 + (0.10 * distance));
    }
}

class TrainJourney extends TransportJourney {
    public TrainJourney(double distance) {
        super(distance);
    }

    @Override
    public String getTransportType() {
        return "TRAIN";
    }

    @Override
    public double calculateFare() {
        return 3.0 + (0.15 * distance);
    }
}

class MetroJourney extends TransportJourney {
    private double peakHourFactor;

    public MetroJourney(double distance, double peakHourFactor) {
        super(distance);
        this.peakHourFactor = peakHourFactor;
    }

    @Override
    public String getTransportType() {
        return "METRO";
    }

    @Override
    public double calculateFare() {
        return (1.50 + (0.20 * distance)) * peakHourFactor;
    }
}

public class TransportFareCalculator {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            if (!sc.hasNextInt()) return;

            int N = sc.nextInt();
            List<TransportJourney> journeys = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                String type = sc.next();
                double distance = sc.nextDouble();

                if (type.equals("METRO")) {
                    double peakFactor = sc.nextDouble();
                    journeys.add(new MetroJourney(distance, peakFactor));
                } else if (type.equals("BUS")) {
                    journeys.add(new BusJourney(distance));
                } else if (type.equals("TRAIN")) {
                    journeys.add(new TrainJourney(distance));
                }
            }

            double grandTotal = 0.0;
            for (TransportJourney j : journeys) {
                double fare = j.calculateFare();
                grandTotal += fare;
                System.out.printf("%s: %.2f%n", j.getTransportType(), fare);
            }

            System.out.printf("Total: %.2f%n", grandTotal);
        }
    }
}