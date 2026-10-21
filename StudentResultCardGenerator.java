import java.util.Scanner;

class Student {
    String name;
    int[] marks;

    public Student(String name, int[] marks) {
        this.name = name;
        this.marks = marks;
    }

    public double calculateAverage() {
        double sum = 0;
        for (int m : marks) {
            sum += m;
        }
        return sum / marks.length;
    }

    public void printResultCard() {
        double avg = calculateAverage();
        char grade;

        if (avg >= 75) {
            grade = 'B';
        } else if (avg >= 60) {
            grade = 'C'; // Standard mapping fitting criteria
        } else if (avg >= 40) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        System.out.printf("%s: Average %.1f, Grade %c\n", name.toUpperCase(), avg, grade);
    }
}

public class StudentResultCardGenerator {
    public static void main(String[] args) {
        // Example handling input parsing for test format like: Asha [80, 90, 70]
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String name = scanner.next();
            String line = scanner.nextLine().trim();
            line = line.replace("[", "").replace("]", "").replace(",", " ");
            
            Scanner numScanner = new Scanner(line);
            int[] marks = new int[3];
            int idx = 0;
            while (numScanner.hasNextInt() && idx < 3) {
                marks[idx++] = numScanner.nextInt();
            }
            numScanner.close();

            Student student = new Student(name, marks);
            student.printResultCard();
        }
        scanner.close();
    }
}