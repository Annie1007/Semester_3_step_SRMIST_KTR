import java.util.Scanner;

public class GardenPlotAreaReport {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }
        int n = scanner.nextInt();
        double totalArea = 0.0;

        for (int i = 0; i < n; i++) {
            String shape = scanner.next();
            String owner = scanner.next();
            double area = 0.0;

            switch (shape.toUpperCase()) {
                case "CIRCLE":
                    double radius = scanner.nextDouble();
                    area = Math.PI * radius * radius;
                    break;
                case "RECTANGLE":
                    double length = scanner.nextDouble();
                    double width = scanner.nextDouble();
                    area = length * width;
                    break;
                case "TRIANGLE":
                    double base = scanner.nextDouble();
                    double height = scanner.nextDouble();
                    area = 0.5 * base * height;
                    break;
                default:
                    continue;
            }

            totalArea += area;
            System.out.printf("%s (%s): %.2f\n", owner, shape.toUpperCase(), area);
        }
        System.out.printf("Total Area: %.2f\n", totalArea);
        scanner.close();
    }
}