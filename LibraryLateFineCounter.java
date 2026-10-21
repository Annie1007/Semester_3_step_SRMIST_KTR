import java.util.Scanner;

public class LibraryLateFineCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }
        int n = scanner.nextInt();
        double totalFines = 0.0;

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String title = scanner.next();
            int daysLate = scanner.nextInt();
            double fine = 0.0;

            switch (type.toUpperCase()) {
                case "BOOK":
                    fine = daysLate * 2.0;
                    break;
                case "DVD":
                    fine = Math.min(daysLate * 5.0, 50.0);
                    break;
                case "MAGAZINE":
                    fine = daysLate * 1.0;
                    break;
                default:
                    continue;
            }

            totalFines += fine;
            System.out.printf("%s: %.2f\n", title, fine);
        }
        System.out.printf("Total Fines: %.2f\n", totalFines);
        scanner.close();
    }
}