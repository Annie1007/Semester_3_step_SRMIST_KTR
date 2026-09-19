class CompanyEmployee {
    private String empName;
    private double salary;
    
    // Shared static variables
    public static String companyName = "Bright Horizon Technologies";
    public static int employeeCount = 0;

    public CompanyEmployee(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++; // Increment static counter on every instantiation
    }

    // Static method: Company-level overview (No instance variables allowed)
    public static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }

    // Instance method: Individual employee details
    public void printEmployeeDetails() {
        System.out.println("Employee: " + empName + " | Salary: Rs " + salary);
    }

    public static void main(String[] args) {
        CompanyEmployee e1 = new CompanyEmployee("Aisha", 50000.0);
        CompanyEmployee e2 = new CompanyEmployee("Rahul", 60000.0);
        CompanyEmployee e3 = new CompanyEmployee("Karan", 55000.0);

        // Optional: Print individual details
        e1.printEmployeeDetails();
        e2.printEmployeeDetails();
        e3.printEmployeeDetails();
        System.out.println("-----------------------------------");

        // Required by Problem Statement: Call static method via Class Name
        CompanyEmployee.printCompanyInfo();
    }
}