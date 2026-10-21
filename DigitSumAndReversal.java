import java.util.Scanner;

public class DigitSumAndReversal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }
        int n = scanner.nextInt();
        int sum = 0;
        int reverse = 0;

        while (n > 0) {
            int digit = n % 10;
            sum += digit;
            reverse = (reverse * 10) + digit;
            n /= 10;
        }

        System.out.println("Sum of digits: " + sum);
        System.out.println("Reverse: " + reverse);
        scanner.close();
    }
}