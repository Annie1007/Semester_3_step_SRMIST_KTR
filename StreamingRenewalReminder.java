import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class SubscriptionPlan {
    protected String subscriberName;
    protected LocalDate startDate;

    public SubscriptionPlan(String subscriberName, LocalDate startDate) {
        this.subscriberName = subscriberName;
        this.startDate = startDate;
    }

    public String getSubscriberName() {
        return subscriberName;
    }

    public abstract LocalDate calculateRenewalDate();
}

class BasicPlan extends SubscriptionPlan {
    public BasicPlan(String subscriberName, LocalDate startDate) {
        super(subscriberName, startDate);
    }

    @Override
    public LocalDate calculateRenewalDate() {
        return startDate.plusDays(30);
    }
}

class StandardPlan extends SubscriptionPlan {
    public StandardPlan(String subscriberName, LocalDate startDate) {
        super(subscriberName, startDate);
    }

    @Override
    public LocalDate calculateRenewalDate() {
        return startDate.plusDays(90);
    }
}

class PremiumPlan extends SubscriptionPlan {
    public PremiumPlan(String subscriberName, LocalDate startDate) {
        super(subscriberName, startDate);
    }

    @Override
    public LocalDate calculateRenewalDate() {
        return startDate.plusDays(365);
    }
}

public class StreamingRenewalReminder {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            if (!sc.hasNextInt()) return;

            int N = sc.nextInt();
            List<SubscriptionPlan> subscriptions = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                String planType = sc.next();
                String name = sc.next();
                LocalDate startDate = LocalDate.parse(sc.next());

                switch (planType) {
                    case "BASIC":
                        subscriptions.add(new BasicPlan(name, startDate));
                        break;
                    case "STANDARD":
                        subscriptions.add(new StandardPlan(name, startDate));
                        break;
                    case "PREMIUM":
                        subscriptions.add(new PremiumPlan(name, startDate));
                        break;
                }
            }

            for (SubscriptionPlan plan : subscriptions) {
                System.out.println(plan.getSubscriberName() + ": " + plan.calculateRenewalDate());
            }
        }
    }
}