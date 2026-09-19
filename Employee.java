class Employee {
    private String empId;
    private String empName;
    private double salary;
    private boolean isIntern;

    // Permanent employee constructor
    public Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
        this.isIntern = false;
    }

    // Intern constructor chaining via this(...)
    public Employee(String empId, String empName) {
        this(empId, empName, 0.0);
        this.isIntern = true;
    }

    public void printProfile() {
        System.out.println(empId + " | " + empName + " | Rs " + salary + " | Intern: " + isIntern);
    }

    public static void main(String[] args) {
        Employee permEmp = new Employee("E-101", "Divya", 65000.0);
        Employee internEmp = new Employee("E-102", "Arjun");

        permEmp.printProfile();
        internEmp.printProfile();
    }
}