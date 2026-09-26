import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class PaymentMethod {
    protected double amount;

    public PaymentMethod(double amount) {
        this.amount = amount;
    }

    public abstract String getPaymentType();
    public abstract double calculateAdjustedAmount();
}

class CardPayment extends PaymentMethod {
    public CardPayment(double amount) {
        super(amount);
    }

    @Override
    public String getPaymentType() {
        return "CARD";
    }

    @Override
    public double calculateAdjustedAmount() {
        return amount * 1.02; // 2% fee
    }
}

class WalletPayment extends PaymentMethod {
    public WalletPayment(double amount) {
        super(amount);
    }

    @Override
    public String getPaymentType() {
        return "WALLET";
    }

    @Override
    public double calculateAdjustedAmount() {
        return amount * 1.01; // 1% fee
    }
}

class BankTransferPayment extends PaymentMethod {
    public BankTransferPayment(double amount) {
        super(amount);
    }

    @Override
    public String getPaymentType() {
        return "BANKTRANSFER";
    }

    @Override
    public double calculateAdjustedAmount() {
        return amount; // No fee
    }
}

public class PaymentSystem {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            if (!sc.hasNextInt()) return;

            int N = sc.nextInt();
            List<PaymentMethod> transactions = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                String type = sc.next();
                double amount = sc.nextDouble();

                switch (type) {
                    case "CARD":
                        transactions.add(new CardPayment(amount));
                        break;
                    case "WALLET":
                        transactions.add(new WalletPayment(amount));
                        break;
                    case "BANKTRANSFER":
                        transactions.add(new BankTransferPayment(amount));
                        break;
                }
            }

            double grandTotal = 0.0;
            for (PaymentMethod pm : transactions) {
                double adjusted = pm.calculateAdjustedAmount();
                grandTotal += adjusted;
                System.out.printf("%s: %.2f%n", pm.getPaymentType(), adjusted);
            }

            System.out.printf("Total: %.2f%n", grandTotal);
        }
    }
}