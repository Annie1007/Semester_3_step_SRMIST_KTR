public class Locker {
    private final int lockerNumber;
    private String combinationCode; // Private: no getter provided

    public Locker(int lockerNumber, String initialCode) {
        this.lockerNumber = lockerNumber;
        this.combinationCode = initialCode;
    }

    public boolean changeCode(String currentCode, String newCode) {
        // Verify old code before making changes
        if (!this.combinationCode.equals(currentCode)) {
            System.out.println("Code change rejected: Incorrect current code.");
            return false;
        }

        this.combinationCode = newCode;
        System.out.println("Code change succeeded.");
        return true;
    }

    public int getLockerNumber() {
        return this.lockerNumber;
    }

    public static void main(String[] args) {
        Locker l = new Locker(101, "1234");
        l.changeCode("1234", "5678"); // Success
        l.changeCode("0000", "9999"); // Rejected
    }
}