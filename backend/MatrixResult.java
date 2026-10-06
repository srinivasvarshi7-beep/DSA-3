package backend;

public class MatrixResult {

    private final int[] dimensions;
    private final long[][] dp;
    private final int[][] split;

    private final long minimumCost;
    private final long naiveCost;
    private final long costSaving;

    private final double savingPercentage;

    private final String optimalOrder;

    private final String timeComplexity;
    private final String spaceComplexity;

    public MatrixResult(
            int[] dimensions,
            long[][] dp,
            int[][] split,
            long minimumCost,
            long naiveCost,
            long costSaving,
            double savingPercentage,
            String optimalOrder,
            String timeComplexity,
            String spaceComplexity
    ) {

        this.dimensions = dimensions;
        this.dp = dp;
        this.split = split;

        this.minimumCost = minimumCost;
        this.naiveCost = naiveCost;
        this.costSaving = costSaving;

        this.savingPercentage = savingPercentage;

        this.optimalOrder = optimalOrder;

        this.timeComplexity = timeComplexity;
        this.spaceComplexity = spaceComplexity;
    }

    public int[] getDimensions() {
        return dimensions.clone();
    }

    public long[][] getDp() {
        return dp;
    }

    public int[][] getSplit() {
        return split;
    }

    public long getMinimumCost() {
        return minimumCost;
    }

    public long getNaiveCost() {
        return naiveCost;
    }

    public long getCostSaving() {
        return costSaving;
    }

    public double getSavingPercentage() {
        return savingPercentage;
    }

    public String getOptimalOrder() {
        return optimalOrder;
    }

    public String getTimeComplexity() {
        return timeComplexity;
    }

    public String getSpaceComplexity() {
        return spaceComplexity;
    }
}