class PayrollAccount {
    private double basicSalary;
    private double bonus;

    public PayrollAccount(double openingSalary) {
        if (openingSalary < 0) {
            System.out.println("Warning: Opening salary cannot be negative. Setting to 0.0.");
            this.basicSalary = 0.0;
        } else {
            this.basicSalary = openingSalary;
        }
        this.bonus = 0.0;
    }

    public void creditBonus(double amount) {
        if (amount <= 0) {
            System.out.println("Bonus amount must be greater than zero.");
            return;
        }
        this.bonus += amount;
        System.out.println("Bonus credited: Rs " + amount);
    }

    public void deductTax(double percent) {
        if (percent < 0 || percent > 100) {
            System.out.println("Invalid tax percentage.");
            return;
        }
        this.basicSalary -= (this.basicSalary * percent / 100.0);
        System.out.println("Tax deducted: " + (int) percent + "%");
    }

    public double getNetSalary() {
        return this.basicSalary + this.bonus;
    }

    public static void main(String[] args) {
        PayrollAccount account = new PayrollAccount(50000.0);
        account.creditBonus(5000.0);
        account.deductTax(10);
        System.out.println("Net salary: Rs " + account.getNetSalary());
    }
}