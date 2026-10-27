import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QuizApplication {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@//localhost:1521/FREE";
        String user = "SYSTEM";
        String password = "Bunny@007";

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to Oracle Database successfully via localhost!");

            Statement stmt = conn.createStatement();

            // 1. Automatically create table (drops old one to keep it clean)
            try { stmt.execute("DROP TABLE quiz_attempts CASCADE CONSTRAINTS"); } catch (SQLException e) {}
            
            stmt.execute("CREATE TABLE quiz_attempts (" +
                         "student_id VARCHAR2(30), " +
                         "quiz_name VARCHAR2(50), " +
                         "total_questions NUMBER, " +
                         "score NUMBER)");
            System.out.println("Table 'quiz_attempts' created successfully.");

            // 2. Insert Quiz Results using PreparedStatement
            String insertSql = "INSERT INTO quiz_attempts (student_id, quiz_name, total_questions, score) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);

            // Sample data entries
            Object[][] records = {
                {"RA2511003010789", "Java JDBC Quiz", 20, 20}, // 100% (Above 80%)
                {"RA2511003010790", "Java JDBC Quiz", 20, 14}, // 70%
                {"RA2511003010791", "Java JDBC Quiz", 20, 18}, // 90% (Above 80%)
                {"RA2511003010789", "Database Quiz", 10, 7}     // 70%
            };

            for (Object[] row : records) {
                pstmt.setString(1, (String) row[0]);
                pstmt.setString(2, (String) row[1]);
                pstmt.setInt(3, (Integer) row[2]);
                pstmt.setInt(4, (Integer) row[3]);
                pstmt.executeUpdate();
            }
            System.out.println("Quiz results inserted successfully.\n");

            // 3. Display Highest Score
            System.out.println("=== HIGHEST SCORE IN DATABASE ===");
            String highSql = "SELECT student_id, quiz_name, score, total_questions FROM quiz_attempts ORDER BY score DESC FETCH FIRST 1 ROWS ONLY";
            ResultSet rsHigh = stmt.executeQuery(highSql);
            if (rsHigh.next()) {
                System.out.println("Student ID    : " + rsHigh.getString("student_id"));
                System.out.println("Quiz Name     : " + rsHigh.getString("quiz_name"));
                System.out.println("Highest Score : " + rsHigh.getInt("score") + "/" + rsHigh.getInt("total_questions"));
            }
            rsHigh.close();

            // 4. List Students Scoring Above 80%
            System.out.println("\n=== STUDENTS SCORING ABOVE 80% ===");
            String above80Sql = "SELECT student_id, quiz_name, score, total_questions FROM quiz_attempts WHERE (score * 100.0 / total_questions) > 80";
            ResultSet rsAbove = stmt.executeQuery(above80Sql);
            boolean found = false;
            while (rsAbove.next()) {
                found = true;
                double percentage = (rsAbove.getDouble("score") / rsAbove.getDouble("total_questions")) * 100;
                System.out.println("Student ID : " + rsAbove.getString("student_id") + 
                                   " | Quiz: " + rsAbove.getString("quiz_name") + 
                                   " | Score: " + rsAbove.getInt("score") + "/" + rsAbove.getInt("total_questions") + 
                                   " (" + String.format("%.1f", percentage) + "%)");
            }
            if (!found) {
                System.out.println("No records found above 80%.");
            }
            rsAbove.close();

            // Clean up
            pstmt.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}