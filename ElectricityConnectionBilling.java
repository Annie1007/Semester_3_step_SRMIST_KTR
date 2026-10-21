import java.util.Scanner;

public class ElectricityConnectionBilling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }
        int n = scanner.nextInt();
        double totalBilled = 0.0;

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double units = scanner.nextDouble();
            double bill = 0.0;

            switch (type.toUpperCase()) {
                case "HOME":
                    if (units <= 100) {
                        bill = units * 5.0;
                    } else {
                        bill = (100 * 5.0) + ((units - 100) * 7.0);
                    }
                    break;
                case "SHOP":
                    bill = (units * 8.0) + 100.0;
                    break;
                case "FACTORY":
                    bill = Math.max(units * 6.0, 1000.0);
                    break;
                default:
                    continue;
            }

            totalBilled += bill;
            System.out.printf("%s: %.2f\n", type.toUpperCase(), bill);
        }
        System.out.printf("Total: %.2f\n", totalBilled);
        scanner.close();
    }
}