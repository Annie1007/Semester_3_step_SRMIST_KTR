import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ExamTransactionChallenge {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@//localhost:1521/FREE";
        String user = "SYSTEM";
        String password = "Bunny@007";

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to Oracle Database successfully via localhost!");
            
            // 1. CRITICAL FOR TRANSACTIONS: Disable auto-commit
            conn.setAutoCommit(false);
            System.out.println("Transaction started (Auto-commit disabled).");

            Statement stmt = conn.createStatement();
            
            // Step 0: Automatically create tables so we never get ORA-00942
            try { stmt.execute("DROP TABLE students CASCADE CONSTRAINTS"); } catch (SQLException e) { /* ignore if not exists */ }
            try { stmt.execute("DROP TABLE departments CASCADE CONSTRAINTS"); } catch (SQLException e) { /* ignore if not exists */ }

            stmt.execute("CREATE TABLE departments (" +
                         "dept_id NUMBER PRIMARY KEY, " +
                         "dept_name VARCHAR2(50))");
            
            stmt.execute("CREATE TABLE students (" +
                         "student_id NUMBER PRIMARY KEY, " +
                         "student_name VARCHAR2(50), " +
                         "dept_id NUMBER, " +
                         "CONSTRAINT fk_dept FOREIGN KEY (dept_id) REFERENCES departments(dept_id))");
            
            System.out.println("Tables 'departments' and 'students' created successfully.");

            // Step A: Insert Parent Record (Department)
            stmt.executeUpdate("INSERT INTO departments VALUES (101, 'Computer Science')");
            System.out.println("Department inserted successfully.");

            // Step B: Use PreparedStatement for Child Inserts (Students)
            String sql = "INSERT INTO students (student_id, student_name, dept_id) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // First Student
            pstmt.setInt(1, 1);
            pstmt.setString(2, "Ananya");
            pstmt.setInt(3, 101);
            pstmt.executeUpdate();

            // Second Student
            pstmt.setInt(1, 2);
            pstmt.setString(2, "Rahul");
            pstmt.setInt(3, 101);
            pstmt.executeUpdate();
            
            System.out.println("All students inserted successfully.");

            // 2. COMMIT the transaction if everything succeeds
            conn.commit();
            System.out.println("Transaction COMMITTED successfully!");

            // Clean up resources
            pstmt.close();
            stmt.close();

        } catch (SQLException e) {
            System.out.println("⚠️ Error occurred! Rolling back transaction...");
            try {
                if (conn != null) {
                    // 3. Rollback if any error occurs
                    conn.rollback();
                    System.out.println("Transaction successfully rolled back. No partial data saved.");
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true); // Always restore auto-commit
                    conn.close();
                }
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
    }
}