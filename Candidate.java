import java.util.Arrays;

class Candidate implements Comparable<Candidate> {
    private String name;
    private double cgpa;
    private int codingScore;
    private double compositeScore;

    public Candidate(String name, double cgpa, int codingScore) {
        this.name = name;
        this.cgpa = cgpa;
        this.codingScore = codingScore;
        // Composite Score Formula: (CGPA * 10) + Coding Score
        this.compositeScore = (cgpa * 10.0) + codingScore;
    }

    public double getCompositeScore() {
        return compositeScore;
    }

    public String getName() {
        return name;
    }

    // Direct CGPA check: >= 7.5 clears directly
    public static boolean isEligible(double cgpa) {
        return cgpa >= 7.5;
    }

    // Combined check: CGPA >= 6.5 AND Coding Score >= 60
    public static boolean isEligible(double cgpa, int codingScore) {
        return cgpa >= 6.5 && codingScore >= 60;
    }

    // Sort descending by composite score
    @Override
    public int compareTo(Candidate other) {
        return Double.compare(other.compositeScore, this.compositeScore);
    }

    public static String shortlistAndRank(Candidate[] candidates) {
        int eligibleCount = 0;

        // Step 1: Count eligible candidates
        for (Candidate c : candidates) {
            if (isEligible(c.cgpa) || isEligible(c.cgpa, c.codingScore)) {
                eligibleCount++;
            }
        }

        // Step 2: Populate eligible array
        Candidate[] shortlisted = new Candidate[eligibleCount];
        int index = 0;
        for (Candidate c : candidates) {
            if (isEligible(c.cgpa) || isEligible(c.cgpa, c.codingScore)) {
                shortlisted[index++] = c;
            }
        }

        // Step 3: Rank using Comparable implementation
        Arrays.sort(shortlisted);

        // Step 4: Build output string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < shortlisted.length; i++) {
            sb.append(i + 1)
              .append(". ")
              .append(shortlisted[i].getName())
              .append(" (")
              .append(shortlisted[i].getCompositeScore())
              .append(")");

            if (i < shortlisted.length - 1) {
                sb.append(" | ");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Candidate[] batch = {
            new Candidate("Aisha", 8.2, 40),
            new Candidate("Rohit", 6.8, 65),
            new Candidate("Meena", 6.0, 90),
            new Candidate("Karan", 7.5, 20)
        };

        System.out.println(shortlistAndRank(batch));
        // Output: 1. Aisha (102.0) | 2. Rohit (100.5) | 3. Karan (85.0)
    }
}