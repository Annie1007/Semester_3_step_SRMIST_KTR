import java.util.Scanner;

public class TravelBooking {
    private static final double BOOKING_FEE = 50.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            String mode = scanner.next();
            double distanceKm = scanner.nextDouble();
            double baseFare = 0.0;

            switch (mode.toUpperCase()) {
                case "BUS":
                    baseFare = distanceKm * 2.0;
                    break;
                case "TRAIN":
                    baseFare = distanceKm * 1.5;
                    break;
                case "FLIGHT":
                    baseFare = 2500.0 + (distanceKm * 4.0);
                    break;
                default:
                    continue;
            }

            double totalFare = baseFare + BOOKING_FEE;
            System.out.printf("%s: %.2f\n", mode.toUpperCase(), totalFare);
        }
        scanner.close();
    }
}