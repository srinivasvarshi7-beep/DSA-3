package backend;

public class MatrixChainDP {

    public MatrixResult optimize(String input) {

        // ---------------------------------------------
        // 1. VALIDATE AND PARSE INPUT
        // ---------------------------------------------

        MatrixInput matrixInput =
                new MatrixInput(input);

        int[] dimensions =
                matrixInput.getDimensions();

        int n =
                matrixInput.getMatrixCount();


        // ---------------------------------------------
        // 2. CREATE DP AND SPLIT TABLES
        // ---------------------------------------------

        long[][] dp =
                new long[n + 1][n + 1];

        int[][] split =
                new int[n + 1][n + 1];


        // ---------------------------------------------
        // 3. INTERVAL DYNAMIC PROGRAMMING
        // ---------------------------------------------

        for (int length = 2; length <= n; length++) {

            for (int i = 1;
                 i <= n - length + 1;
                 i++) {

                int j =
                        i + length - 1;

                dp[i][j] =
                        Long.MAX_VALUE;


                for (int k = i;
                     k < j;
                     k++) {

                    long cost =
                            dp[i][k]
                            +
                            dp[k + 1][j]
                            +
                            (long)
                            dimensions[i - 1]
                            *
                            dimensions[k]
                            *
                            dimensions[j];


                    if (cost < dp[i][j]) {

                        dp[i][j] =
                                cost;

                        split[i][j] =
                                k;
                    }
                }
            }
        }


        // ---------------------------------------------
        // 4. MINIMUM COST
        // ---------------------------------------------

        long minimumCost =
                n <= 1
                ? 0
                : dp[1][n];


        // ---------------------------------------------
        // 5. NAIVE LEFT-TO-RIGHT COST
        // ---------------------------------------------

        long naiveCost =
                calculateNaiveCost(dimensions);


        // ---------------------------------------------
        // 6. COST SAVING
        // ---------------------------------------------

        long costSaving =
                naiveCost - minimumCost;


        double savingPercentage = 0.0;

        if (naiveCost > 0) {

            savingPercentage =
                    ((double) costSaving
                    / naiveCost) * 100.0;
        }


        // ---------------------------------------------
        // 7. OPTIMAL PARENTHESIZATION
        // ---------------------------------------------

        String optimalOrder;

        if (n == 1) {

            optimalOrder = "A1";

        } else {

            optimalOrder =
                    buildParenthesization(
                            split,
                            1,
                            n
                    );
        }


        // ---------------------------------------------
        // 8. RETURN COMPLETE RESULT
        // ---------------------------------------------

        return new MatrixResult(

                dimensions,

                dp,

                split,

                minimumCost,

                naiveCost,

                costSaving,

                savingPercentage,

                optimalOrder,

                "O(n^3)",

                "O(n^2)"
        );
    }


    // =====================================================
    // NAIVE LEFT-TO-RIGHT COST
    // =====================================================

    private long calculateNaiveCost(
            int[] dimensions
    ) {

        int n =
                dimensions.length - 1;

        if (n <= 1) {
            return 0;
        }

        long total = 0;

        int rows =
                dimensions[0];

        int currentCols =
                dimensions[1];


        for (int i = 2;
             i <= n;
             i++) {

            total +=
                    (long)
                    rows
                    *
                    currentCols
                    *
                    dimensions[i];

            currentCols =
                    dimensions[i];
        }

        return total;
    }


    // =====================================================
    // BUILD OPTIMAL PARENTHESIZATION
    // =====================================================

    private String buildParenthesization(
            int[][] split,
            int i,
            int j
    ) {

        if (i == j) {
            return "A" + i;
        }

        int k =
                split[i][j];

        return "("
                + buildParenthesization(
                        split,
                        i,
                        k
                )
                + " X "
                + buildParenthesization(
                        split,
                        k + 1,
                        j
                )
                + ")";
    }
}