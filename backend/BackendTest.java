package backend;

public class BackendTest {

    public static void main(String[] args) {

        MatrixChainDP algorithm =
                new MatrixChainDP();


        MatrixResult result =
                algorithm.optimize(
                        "10 30 5 60 20"
                );


        System.out.println(
                "================================"
        );

        System.out.println(
                "       MATRIXMIND BACKEND"
        );

        System.out.println(
                "================================"
        );


        System.out.println(
                "Minimum Cost    : "
                + result.getMinimumCost()
        );


        System.out.println(
                "Naive Cost      : "
                + result.getNaiveCost()
        );


        System.out.println(
                "Cost Saving     : "
                + result.getCostSaving()
        );


        System.out.println(
                "Saving %        : "
                + String.format(
                        "%.2f%%",
                        result.getSavingPercentage()
                )
        );


        System.out.println(
                "Optimal Order   : "
                + result.getOptimalOrder()
        );


        System.out.println(
                "Time Complexity : "
                + result.getTimeComplexity()
        );


        System.out.println(
                "Space Complexity: "
                + result.getSpaceComplexity()
        );


        System.out.println(
                "================================"
        );
    }
}