public class Cart {
    private final String cartId;   // Fixed ID
    private final double[] prices; // Private array of raw item prices
    private int itemCount;

    public Cart(String cartId, int maxItems) {
        this.cartId = cartId;
        this.prices = new double[maxItems];
        this.itemCount = 0;
    }

    public void addItem(double price) {
        if (price <= 0) {
            System.out.println("Invalid price: must be positive.");
            return;
        }
        if (itemCount >= prices.length) {
            System.out.println("Cart full: cannot add item.");
            return;
        }
        prices[itemCount] = price;
        itemCount++;
    }

    public double getTotal() {
        double total = 0.0;
        for (int i = 0; i < itemCount; i++) {
            total += prices[i];
        }
        return total;
    }

    public int getItemCount() {
        return this.itemCount;
    }

    public String getCartId() {
        return this.cartId;
    }

    public static void main(String[] args) {
        Cart cart = new Cart("CART-5", 20);
        
        cart.addItem(250);
        cart.addItem(99);
        cart.addItem(151);

        System.out.println("Total: " + cart.getTotal());         // Output: 500.0
        System.out.println("Item Count: " + cart.getItemCount()); // Output: 3
    }
}