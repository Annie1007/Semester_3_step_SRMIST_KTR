public class BusiestBusRow {

    /**
     * Finds the row with the largest total number of students.
     * If there is a tie, returns the row with the smaller index.
     * 
     * @param grid 2D array representing the bus seats
     * @return an int array where index 0 is the row index and index 1 is the total
     */
    public static int[] busiestRow(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return new int[] { -1, 0 };
        }

        int bestRowIndex = 0;
        int maxTotal = -1;

        for (int i = 0; i < grid.length; i++) {
            int currentRowTotal = 0;
            for (int j = 0; j < grid[i].length; j++) {
                currentRowTotal += grid[i][j];
            }

            // Update only when the total is strictly larger to handle ties 
            // by keeping the smaller index.
            if (currentRowTotal > maxTotal) {
                maxTotal = currentRowTotal;
                bestRowIndex = i;
            }
        }

        return new int[] { bestRowIndex, maxTotal };
    }

    public static void main(String[] args) {
        int[][] grid = {
            {2, 0, 1},
            {3, 3, 1},
            {1, 1, 1}
        };

        int[] result = busiestRow(grid);
        System.out.println("Row " + result[0] + ", Total " + result[1]);

        /*
         * COMPLEXITY REQUIREMENTS:
         * - Time Complexity: O(r * c), where r is the number of rows and c is the number of columns, 
         *   because we visit every element in the 2D grid exactly once.
         * - Additional Space Complexity: O(1), as we only use a few tracking variables 
         *   (bestRowIndex, maxTotal, currentRowTotal) regardless of the grid size.
         */
    }
}