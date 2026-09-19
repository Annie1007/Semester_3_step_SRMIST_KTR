public class PasswordChecker {
    private final String password; // Read-only, no getter provided

    public PasswordChecker(String password) {
        this.password = password;
    }

    public String getStrength() {
        int length = password.length();

        if (length < 6) {
            return "Weak";
        } else if (length <= 9) {
            return "Medium";
        } else {
            return "Strong";
        }
    }

    public static void main(String[] args) {
        PasswordChecker pc1 = new PasswordChecker("abcd");
        System.out.println("abcd strength: " + pc1.getStrength()); // Output: Weak

        PasswordChecker pc2 = new PasswordChecker("abcdefghij");
        System.out.println("abcdefghij strength: " + pc2.getStrength()); // Output: Strong
    }
}