import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Employee {
    protected String name;
    protected double monthlySalary;

    public Employee(String name, double monthlySalary) {
        this.name = name;
        this.monthlySalary = monthlySalary;
    }

    public String getName() {
        return name;
    }

    public abstract double calculateBonus();
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name, double monthlySalary) {
        super(name, monthlySalary);
    }

    @Override
    public double calculateBonus() {
        return monthlySalary * 0.10;
    }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name, double monthlySalary) {
        super(name, monthlySalary);
    }

    @Override
    public double calculateBonus() {
        return monthlySalary * 0.05;
    }
}

class InternEmployee extends Employee {
    public InternEmployee(String name, double monthlySalary) {
        super(name, monthlySalary);
    }

    @Override
    public double calculateBonus() {
        return 2000.00;
    }
}

public class FestivalBonusCalculator {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            if (!sc.hasNextInt()) return;

            int N = sc.nextInt();
            List<Employee> employees = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                String type = sc.next();
                String name = sc.next();
                double salary = sc.nextDouble();

                switch (type) {
                    case "FULLTIME":
                        employees.add(new FullTimeEmployee(name, salary));
                        break;
                    case "PARTTIME":
                        employees.add(new PartTimeEmployee(name, salary));
                        break;
                    case "INTERN":
                        employees.add(new InternEmployee(name, salary));
                        break;
                }
            }

            double totalBonus = 0.0;
            for (Employee e : employees) {
                double bonus = e.calculateBonus();
                totalBonus += bonus;
                System.out.printf("%s: %.2f%n", e.getName(), bonus);
            }

            System.out.printf("Total Bonus: %.2f%n", totalBonus);
        }
    }
}