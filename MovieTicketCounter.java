import java.util.Scanner;

public class MovieTicketCounter {
    private static final double CONVENIENCE_FEE = 20.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }
        int n = scanner.nextInt();
        double totalCollected = 0.0;

        for (int i = 0; i < n; i++) {
            String seatType = scanner.next();
            int count = scanner.nextInt();

            double basePricePerTicket = 0.0;
            switch (seatType.toUpperCase()) {
                case "REGULAR":
                    basePricePerTicket = 150.0;
                    break;
                case "PREMIUM":
                    basePricePerTicket = 250.0;
                    break;
                case "RECLINER":
                    basePricePerTicket = 400.0;
                    break;
                default:
                    continue;
            }

            double amount = count * (basePricePerTicket + CONVENIENCE_FEE);
            totalCollected += amount;
            System.out.printf("%s: %.2f\n", seatType.toUpperCase(), amount);
        }
        System.out.printf("Total: %.2f\n", totalCollected);
        scanner.close();
    }
}