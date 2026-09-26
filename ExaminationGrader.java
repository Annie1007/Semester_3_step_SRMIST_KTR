import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class Question {
    protected String questionText;
    protected String correctAnswer;
    protected String studentAnswer;
    protected double points;

    public Question(String questionText, String correctAnswer, String studentAnswer, double points) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.studentAnswer = studentAnswer;
        this.points = points;
    }

    public abstract String getQuestionType();
    public abstract double evaluateScore();
}

class MCQQuestion extends Question {
    public MCQQuestion(String questionText, String correctAnswer, String studentAnswer, double points) {
        super(questionText, correctAnswer, studentAnswer, points);
    }

    @Override
    public String getQuestionType() {
        return "MCQ";
    }

    @Override
    public double evaluateScore() {
        return correctAnswer.equalsIgnoreCase(studentAnswer) ? points : 0.0;
    }
}

class TFQuestion extends Question {
    public TFQuestion(String questionText, String correctAnswer, String studentAnswer, double points) {
        super(questionText, correctAnswer, studentAnswer, points);
    }

    @Override
    public String getQuestionType() {
        return "TF";
    }

    @Override
    public double evaluateScore() {
        return correctAnswer.equalsIgnoreCase(studentAnswer) ? points : 0.0;
    }
}

class EssayQuestion extends Question {
    public EssayQuestion(String questionText, String correctAnswer, String studentAnswer, double points) {
        super(questionText, correctAnswer, studentAnswer, points);
    }

    @Override
    public String getQuestionType() {
        return "ESSAY";
    }

    @Override
    public double evaluateScore() {
        String[] keywords = correctAnswer.split(",");
        String studentAnsLower = studentAnswer.toLowerCase();
        int matchedCount = 0;

        for (String kw : keywords) {
            if (studentAnsLower.contains(kw.trim().toLowerCase())) {
                matchedCount++;
            }
        }

        if (matchedCount >= 2) {
            return points * 0.75;
        } else if (matchedCount == 1) {
            return points * 0.50;
        } else {
            return 0.0;
        }
    }
}

public class ExaminationGrader {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            if (!sc.hasNextInt()) return;

            int N = sc.nextInt();
            sc.nextLine(); // Clear newline

            List<Question> questions = new ArrayList<>();
            Pattern pattern = Pattern.compile("^(\\S+)\\s+\"(.*?)\"\\s+\"(.*?)\"\\s+\"(.*?)\"\\s+(\\d+)$");

            for (int i = 0; i < N; i++) {
                if (!sc.hasNextLine()) break;
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    i--;
                    continue;
                }

                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String type = matcher.group(1);
                    String qText = matcher.group(2);
                    String cAns = matcher.group(3);
                    String sAns = matcher.group(4);
                    double pts = Double.parseDouble(matcher.group(5));

                    switch (type) {
                        case "MCQ":
                            questions.add(new MCQQuestion(qText, cAns, sAns, pts));
                            break;
                        case "TF":
                            questions.add(new TFQuestion(qText, cAns, sAns, pts));
                            break;
                        case "ESSAY":
                            questions.add(new EssayQuestion(qText, cAns, sAns, pts));
                            break;
                    }
                }
            }

            double overallScore = 0.0;
            for (Question q : questions) {
                double score = q.evaluateScore();
                overallScore += score;
                System.out.printf("%s: %.2f%n", q.getQuestionType(), score);
            }

            System.out.printf("Total Score: %.2f%n", overallScore);
        }
    }
}