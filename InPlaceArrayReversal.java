import java.util.Scanner;
import java.util.Arrays;

public class InPlaceArrayReversal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextLine()) {
            scanner.close();
            return;
        }
        String input = scanner.nextLine();
        input = input.replace("[", "").replace("]", "").replace(",", " ").trim();
        
        if (input.isEmpty()) {
            System.out.println("[]");
            scanner.close();
            return;
        }

        String[] parts = input.split("\\s+");
        int[] arr = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }

        // Two-pointer in-place reversal
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }

        System.out.println(Arrays.toString(arr));
        scanner.close();
    }
}