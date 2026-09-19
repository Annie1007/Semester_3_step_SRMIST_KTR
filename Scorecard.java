public class Scorecard {
    private final boolean[] results; // Private: hidden from outside
    private final int totalQuestions;
    private int recordedCount;

    public Scorecard(int totalQuestions) {
        this.totalQuestions = totalQuestions;
        this.results = new boolean[totalQuestions];
        this.recordedCount = 0;
    }

    public void recordAnswer(boolean isCorrect) {
        if (recordedCount >= totalQuestions) {
            System.out.println("Record rejected: all " + totalQuestions + " questions have already been recorded.");
            return;
        }
        results[recordedCount] = isCorrect;
        recordedCount++;
    }

    public int getScore() {
        int correctCount = 0;
        for (int i = 0; i < recordedCount; i++) {
            if (results[i]) {
                correctCount++;
            }
        }
        return correctCount;
    }

    public static void main(String[] args) {
        Scorecard sc = new Scorecard(4);
        sc.recordAnswer(true);
        sc.recordAnswer(true);
        sc.recordAnswer(false);
        sc.recordAnswer(true);

        System.out.println("Final Score: " + sc.getScore()); // Output: 3
    }
}