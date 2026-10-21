import java.util.Scanner;

public class PrimeNumberChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int num = scanner.nextInt();
            if (num <= 1) {
                System.out.println(num + " is not prime");
                continue;
            }

            boolean isPrime = true;
            int limit = (int) Math.sqrt(num);
            for (int i = 2; i <= limit; i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                System.out.println(num + " is prime");
            } else {
                System.out.println(num + " is not prime");
            }
        }
        scanner.close();
    }
}