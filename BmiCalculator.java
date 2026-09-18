import java.util.Random;

public class BmiCalculator {

    public static String getBmiStatus(double bmi) {
        if (bmi < 18.5) return "Underweight";
        if (bmi <= 24.9) return "Normal";
        if (bmi <= 29.9) return "Overweight";
        return "Obese";
    }

    public static void printWellnessReport(double[] heights, double[] weights) {
        System.out.printf("%-8s | %-11s | %-11s | %-8s | %-12s%n", 
                "Person", "Height (m)", "Weight (kg)", "BMI", "Status");
        System.out.println("-------------------------------------------------------------");

        for (int i = 0; i < heights.length; i++) {
            double bmi = weights[i] / (heights[i] * heights[i]);
            String status = getBmiStatus(bmi);
            
            System.out.printf("Person %-2d | %-11.2f | %-11.2f | %-8.2f | %-12s%n", 
                    (i + 1), heights[i], weights[i], bmi, status);
        }
    }

    public static void main(String[] args) {
        int numPeople = 10;
        double[] heights = new double[numPeople];
        double[] weights = new double[numPeople];
        
        Random rand = new Random();

        // Populate random sample data for demonstration
        for (int i = 0; i < numPeople; i++) {
            heights[i] = 1.50 + (1.95 - 1.50) * rand.nextDouble(); // Heights between 1.50m - 1.95m
            weights[i] = 45.0 + (100.0 - 45.0) * rand.nextDouble(); // Weights between 45kg - 100kg
        }

        printWellnessReport(heights, weights);
    }
}