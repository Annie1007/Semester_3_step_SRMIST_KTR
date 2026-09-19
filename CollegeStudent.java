class CollegeStudent {
    private String name;
    private double attendance;

    // Shared static variables
    public static String collegeName = "SRM Institute of Science and Technology";
    public static int studentCount = 0;

    public CollegeStudent(String name, double attendance) {
        this.name = name;
        this.attendance = attendance;
        studentCount++; // Increment static counter on every instantiation
    }

    // Instance method: Display individual student details
    public void printStudentDetails() {
        System.out.println("Student: " + name + " | Attendance: " + attendance + "%");
    }

    // Static method: College-level overview (No instance variables allowed)
    public static void printCollegeInfo() {
        System.out.println("\n--- College Summary ---");
        System.out.println(collegeName);
        System.out.println("Students created: " + studentCount);
    }

    public static void main(String[] args) {
        CollegeStudent s1 = new CollegeStudent("Ravi", 85.0);
        CollegeStudent s2 = new CollegeStudent("Anitha", 92.5);

        // Print individual student details
        s1.printStudentDetails();
        s2.printStudentDetails();

        // Call static method directly using the class name
        CollegeStudent.printCollegeInfo();
    }
}