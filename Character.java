public class Character {
    private final int maxHealth; // Fixed at creation, never changes
    private int currentHealth;   // Encapsulated state: no direct setter

    public Character(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth; // Character starts at full health
    }

    public void takeDamage(int amount) {
        if (amount <= 0) return;
        
        // Calculate and clamp at 0 (floored)
        this.currentHealth = Math.max(0, this.currentHealth - amount);
    }

    public void heal(int amount) {
        if (amount <= 0) return;
        
        // Calculate and clamp at maxHealth (capped)
        this.currentHealth = Math.min(this.maxHealth, this.currentHealth + amount);
    }

    public int getCurrentHealth() {
        return this.currentHealth;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public static void main(String[] args) {
        Character c = new Character(100);
        
        c.takeDamage(30);
        System.out.println("Health after 30 damage: " + c.getCurrentHealth()); // Output: 70

        c.heal(50);
        System.out.println("Health after 50 heal: " + c.getCurrentHealth());     // Output: 100 (capped)

        c.takeDamage(150);
        System.out.println("Health after 150 damage: " + c.getCurrentHealth());  // Output: 0 (floored)
    }
}