public class PiggyBank {
    private final String piggyBankId; // Locked forever once created
    private double savings;          // Private: cannot be accessed directly

    public PiggyBank(String piggyBankId) {
        this.piggyBankId = piggyBankId;
        this.savings = 0.0; // Starts at 0
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        this.savings += amount;
        System.out.println("Deposited: " + amount + " | Current savings: " + this.savings);
    }

    public void withdraw(double amount) {
        if (amount > this.savings) {
            System.out.println("Withdrawal rejected: insufficient funds | Current savings stays: " + this.savings);
            return;
        }
        this.savings -= amount;
        System.out.println("Withdrew: " + amount + " | Current savings: " + this.savings);
    }

    public double getSavings() {
        return this.savings;
    }

    public String getPiggyBankId() {
        return this.piggyBankId;
    }

    public static void main(String[] args) {
        PiggyBank pb = new PiggyBank("PB-1");
        pb.deposit(100);
        pb.withdraw(30);
        pb.withdraw(500); // Rejected
    }
}