import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Savepoint;

public class ExamSavepointChallenge {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@//localhost:1521/FREE";
        String user = "SYSTEM";
        String password = "Bunny@007";

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to Oracle Database successfully via localhost!");
            
            // 1. Disable auto-commit for transaction control
            conn.setAutoCommit(false);
            System.out.println("\n--- Transaction Started (Auto-commit: OFF) ---");

            Statement stmt = conn.createStatement();
            
            // Step 0: Clean up and recreate tables
            try { stmt.execute("DROP TABLE order_items CASCADE CONSTRAINTS"); } catch (SQLException e) {}
            try { stmt.execute("DROP TABLE orders CASCADE CONSTRAINTS"); } catch (SQLException e) {}

            stmt.execute("CREATE TABLE orders (" +
                         "order_id NUMBER PRIMARY KEY, " +
                         "customer_name VARCHAR2(50))");
            
            stmt.execute("CREATE TABLE order_items (" +
                         "item_id NUMBER PRIMARY KEY, " +
                         "order_id NUMBER, " +
                         "item_name VARCHAR2(50), " +
                         "CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders(order_id))");
            
            System.out.println("Tables 'orders' and 'order_items' created successfully.");

            // Step 1: Insert Main Order (Parent)
            stmt.executeUpdate("INSERT INTO orders VALUES (501, 'Ananya')");
            System.out.println("-> Order 501 inserted for customer Ananya.");

            // Step 2: Insert First Item (Valid)
            stmt.executeUpdate("INSERT INTO order_items VALUES (1, 501, 'Gaming Laptop')");
            System.out.println("-> Item 1 ('Gaming Laptop') added successfully.");

            // Step 3: CREATE A SAVEPOINT HERE
            // If anything fails after this point, we can roll back *only* to here, keeping the laptop!
            Savepoint sp = conn.setSavepoint("BeforeSecondItem");
            System.out.println("📌 Savepoint 'BeforeSecondItem' created.");

            try {
                // Step 4: Try inserting a valid item first
                stmt.executeUpdate("INSERT INTO order_items VALUES (2, 501, 'Wireless Mouse')");
                System.out.println("-> Item 2 ('Wireless Mouse') added successfully.");

                // Step 5: Intentionally trigger an error (Duplicate primary key '2' or invalid insert)
                // Let's force an error by reusing item_id 2 or violating a constraint
                System.out.println("-> Attempting to insert faulty item (forcing an error)...");
                stmt.executeUpdate("INSERT INTO order_items VALUES (2, 501, 'Broken Keyboard')"); // Error: primary key violation!

            } catch (SQLException innerEx) {
                System.out.println("⚠️ Error caught during item insertion: " + innerEx.getMessage());
                System.out.println("🔄 Rolling back ONLY to the Savepoint (keeping Laptop and Mouse safe)...");
                
                // Roll back strictly to the savepoint
                conn.rollback(sp);
                System.out.println("✓ Rollback to Savepoint successful. Faulty item discarded.");
            }

            // Step 6: Commit the remaining valid data (Order + Laptop)
            conn.commit();
            System.out.println("\n🎉 Transaction COMMITTED successfully! Order and valid items are saved.");

            stmt.close();

        } catch (SQLException e) {
            System.out.println("⚠️ Major error occurred! Rolling back entire transaction...");
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("Full transaction rolled back.");
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
    }
}