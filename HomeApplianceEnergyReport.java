import java.util.Scanner;

public class HomeApplianceEnergyReport {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }
        int n = scanner.nextInt();
        double totalCost = 0.0;

        for (int i = 0; i < n; i++) {
            String appliance = scanner.next();
            double hours = scanner.nextDouble();
            
            String lineRest = scanner.nextLine().trim();
            boolean hasSaver = lineRest.equalsIgnoreCase("SAVER");

            double power = 0.0;
            boolean supportsSaver = false;

            switch (appliance.toUpperCase()) {
                case "FRIDGE":
                    power = 150.0;
                    supportsSaver = false;
                    break;
                case "AC":
                    power = 1500.0;
                    supportsSaver = true;
                    break;
                case "TV":
                    power = 100.0;
                    supportsSaver = false;
                    break;
                case "WASHER":
                    power = 500.0;
                    supportsSaver = true;
                    break;
                default:
                    continue;
            }

            if (hasSaver && !supportsSaver) {
                System.out.println(appliance.toUpperCase() + ": saver mode not supported");
                continue;
            }

            double units = (power * hours) / 1000.0;
            if (hasSaver && supportsSaver) {
                units *= 0.75; 
            }

            double cost = units * 8.0;
            totalCost += cost;

            System.out.printf("%s: Units=%.2f Cost=%.2f\n", appliance.toUpperCase(), units, cost);
        }
        System.out.printf("Total Cost: %.2f\n", totalCost);
        scanner.close();
    }
}