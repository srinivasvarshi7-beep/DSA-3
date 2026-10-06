package backend;

public class MatrixInput {

    private final int[] dimensions;

    public MatrixInput(String input) {

        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Please enter matrix dimensions."
            );
        }

        String[] parts = input.trim().split("\\s+");

        if (parts.length < 2) {
            throw new IllegalArgumentException(
                    "Enter at least two dimensions."
            );
        }

        dimensions = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {

            try {
                dimensions[i] =
                        Integer.parseInt(parts[i]);
            }
            catch (NumberFormatException e) {

                throw new IllegalArgumentException(
                        "Please enter only positive integer dimensions."
                );
            }

            if (dimensions[i] <= 0) {

                throw new IllegalArgumentException(
                        "Matrix dimensions must be positive."
                );
            }
        }
    }

    public int[] getDimensions() {
        return dimensions.clone();
    }

    public int getMatrixCount() {
        return dimensions.length - 1;
    }
}