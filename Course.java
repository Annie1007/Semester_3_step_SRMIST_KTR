class Course {
    private String code;
    private String title;
    private int credits;
    private int labCredits;

    // Full constructor for courses with lab component
    public Course(String code, String title, int credits, int labCredits) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.labCredits = labCredits;
    }

    // Theory-only constructor chaining via this(...) with labCredits = 0
    public Course(String code, String title, int credits) {
        this(code, title, credits, 0);
    }

    // Calculates total credits (theory + lab)
    public int totalCredits() {
        return this.credits + this.labCredits;
    }

    // Instance method to display detailed course information
    public void printDetails() {
        System.out.println("Course Code : " + this.code);
        System.out.println("Title       : " + this.title);
        System.out.println("Structure   : " + this.credits + " Theory Credits + " + this.labCredits + " Lab Credits");
        System.out.println(this.code + " total credits: " + totalCredits());
        System.out.println("-------------------------------------------");
    }

    public static void main(String[] args) {
        // Theory-only course using 3-argument constructor
        Course theoryCourse = new Course("21CSC201J", "Data Structures", 4);

        // Course with lab component using 4-argument constructor
        Course labCourse = new Course("21CSC205L", "DSA Lab", 3, 1);

        // Output complete information for both
        theoryCourse.printDetails();
        labCourse.printDetails();
    }
}