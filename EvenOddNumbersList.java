import java.util.Scanner;

public class EvenOddNumbersList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Expecting input like: [3, 8, 12, 5, 7, 10] or space/comma separated numbers
        String input = scanner.nextLine();
        
        // Remove brackets and commas
        input = input.replace("[", "").replace("]", "").replace(",", " ");
        Scanner numScanner = new Scanner(input);

        int evenCount = 0;
        int oddCount = 0;

        while (numScanner.hasNextInt()) {
            int num = numScanner.nextInt();
            if (num % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }
        numScanner.close();
        scanner.close();

        System.out.println("Even: " + evenCount);
        System.out.println("Odd: " + oddCount);
    }
}