public class DuplicatePlayerChecker {

    public static String findDuplicatePick(String[] playerNames) {
        // Outer loop selects the current player
        for (int i = 0; i < playerNames.length; i++) {
            // Inner loop compares against all subsequent players to avoid duplicate checks
            for (int j = i + 1; j < playerNames.length; j++) {
                if (playerNames[i].equals(playerNames[j])) {
                    return "Duplicate Found: " + playerNames[i];
                }
            }
        }
        return "No Duplicates Found";
    }

    public static void main(String[] args) {
        String[] lineup1 = {"Kohli", "Bumrah", "Kohli", "Rohit"};
        System.out.println(findDuplicatePick(lineup1)); // Output: Duplicate Found: Kohli

        String[] lineup2 = {"Kohli", "Bumrah", "Rohit"};
        System.out.println(findDuplicatePick(lineup2)); // Output: No Duplicates Found
    }
}