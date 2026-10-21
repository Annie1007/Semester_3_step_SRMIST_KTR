import java.util.Scanner;

public class CollegeFeeCounter {
    private static final double TRANSPORT_FEE = 12000.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }
        int n = scanner.nextInt();
        double totalCollected = 0.0;

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String name = scanner.next();

            double fee = 0.0;
            boolean usesBus = false;

            switch (type.toUpperCase()) {
                case "DAY_SCHOLAR":
                    fee = 40000.0;
                    usesBus = true;
                    break;
                case "HOSTELLER":
                    fee = 40000.0 + 60000.0;
                    usesBus = false;
                    break;
                case "SCHOLAR":
                    fee = 20000.0;
                    usesBus = true;
                    break;
                default:
                    continue;
            }

            if (usesBus) {
                fee += TRANSPORT_FEE;
            }

            totalCollected += fee;
            System.out.printf("%s: %.2f\n", name, fee);
        }
        System.out.printf("Total Collected: %.2f\n", totalCollected);
        scanner.close();
    }
}