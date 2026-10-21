import java.util.Scanner;

public class CityCabFareMeter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }
        int n = scanner.nextInt();
        double total = 0.0;

        for (int i = 0; i < n; i++) {
            String cab = scanner.next();
            double km = scanner.nextDouble();
            String time = scanner.next();

            double rate = 0.0;
            boolean allowsNight = true;

            switch (cab.toUpperCase()) {
                case "MINI":
                    rate = 10.0;
                    if (time.equalsIgnoreCase("NIGHT")) {
                        allowsNight = false;
                    }
                    break;
                case "SEDAN":
                    rate = 14.0;
                    break;
                case "SUV":
                    rate = 18.0;
                    break;
                default:
                    continue;
            }

            if (!allowsNight) {
                System.out.println(cab.toUpperCase() + ": night service not available");
                continue;
            }

            double fare = km * rate;
            if (fare < 100.0) {
                fare = 100.0;
            }

            if (time.equalsIgnoreCase("NIGHT")) {
                fare *= 1.20;
            }

            total += fare;
            System.out.printf("%s: %.2f\n", cab.toUpperCase(), fare);
        }
        System.out.printf("Total: %.2f\n", total);
        scanner.close();
    }
}