import java.util.Arrays;

public class ScoreBooster {

    public static void curveScores(int[] scores, int bonus) {
        for (int i = 0; i < scores.length; i++) {
            scores[i] += bonus; // Modifies the original array directly
        }
    }

    public static void main(String[] args) {
        int[] scores = {70, 85, 60};
        curveScores(scores, 10);
        System.out.println(Arrays.toString(scores)); // Output: [80, 95, 70]
    }
}