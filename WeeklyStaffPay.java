import java.util.Scanner;

public class WeeklyStaffPay {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }
        int n = scanner.nextInt();
        double totalPayroll = 0.0;

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String name = scanner.next();
            double pay = 0.0;

            switch (type.toUpperCase()) {
                case "FULLTIME":
                    pay = scanner.nextDouble();
                    break;
                case "HOURLY":
                    double hours = scanner.nextDouble();
                    double rate = scanner.nextDouble();
                    if (hours <= 40) {
                        pay = hours * rate;
                    } else {
                        pay = (40 * rate) + ((hours - 40) * 1.5 * rate);
                    }
                    break;
                case "INTERN":
                    pay = scanner.nextDouble();
                    break;
                default:
                    continue;
            }

            totalPayroll += pay;
            System.out.printf("%s: %.2f\n", name, pay);
        }
        System.out.printf("Total Payroll: %.2f\n", totalPayroll);
        scanner.close();
    }
}