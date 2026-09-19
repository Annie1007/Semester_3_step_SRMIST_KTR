public class AttendanceSheet {
    private final String[] presentStudents; // Private: internal array
    private int presentCount;

    public AttendanceSheet(int maxCapacity) {
        this.presentStudents = new String[maxCapacity];
        this.presentCount = 0;
    }

    public void markPresent(String studentName) {
        // Check for duplicates
        if (isPresent(studentName)) {
            return;
        }

        // Check array capacity
        if (presentCount >= presentStudents.length) {
            System.out.println("Attendance full: cannot mark " + studentName);
            return;
        }

        presentStudents[presentCount] = studentName;
        presentCount++;
    }

    public boolean isPresent(String studentName) {
        for (int i = 0; i < presentCount; i++) {
            if (presentStudents[i].equals(studentName)) {
                return true;
            }
        }
        return false;
    }

    public int getPresentCount() {
        return this.presentCount;
    }

    public static void main(String[] args) {
        AttendanceSheet sheet = new AttendanceSheet(30);
        sheet.markPresent("Ana");
        sheet.markPresent("Ben");
        sheet.markPresent("Ana"); // Duplicate, ignored

        System.out.println("Present count: " + sheet.getPresentCount()); // Output: 2
        System.out.println("Is Ben present? " + sheet.isPresent("Ben"));   // Output: true
        System.out.println("Is Chen present? " + sheet.isPresent("Chen")); // Output: false
    }
}