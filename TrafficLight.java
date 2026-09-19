public class TrafficLight {
    private final String lightId; // Fixed forever at creation
    private String color;         // Private state: state-machine progression only

    public TrafficLight(String lightId) {
        this.lightId = lightId;
        this.color = "RED"; // Default starting state
    }

    public void next() {
        switch (this.color) {
            case "RED" -> this.color = "GREEN";
            case "GREEN" -> this.color = "YELLOW";
            case "YELLOW" -> this.color = "RED";
        }
    }

    public String getColor() {
        return this.color;
    }

    public String getLightId() {
        return this.lightId;
    }

    public static void main(String[] args) {
        TrafficLight t = new TrafficLight("TL-9");

        System.out.println("Initial color: " + t.getColor()); // Output: RED

        t.next();
        System.out.println("Color after 1st next(): " + t.getColor()); // Output: GREEN

        t.next();
        System.out.println("Color after 2nd next(): " + t.getColor()); // Output: YELLOW

        t.next();
        System.out.println("Color after 3rd next(): " + t.getColor()); // Output: RED
    }
}