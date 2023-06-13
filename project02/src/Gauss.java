import java.util.Arrays;
public class Gauss {
    public static double[] solve(MySparseMatrix matrix, double[] b) {
        int n = b.length;

        double[] bCopy = Arrays.copyOf(b, n);
        for (int i = 0; i < n; i++) {
            for (int k = i + 1; k < n; k++) {
                double c = -matrix.get(k, i) / matrix.get(i, i);
                for (int j = i; j < n; j++) {
                    if (i == j) {
                        matrix.set(k, j, 0);
                    } else {
                        matrix.set(k, j, (c * matrix.get(i, j) + matrix.get(k, j)));
                    }
                }
                bCopy[k] += c * bCopy[i];
            }
        }
        // wsteczna substytucja
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            x[i] = bCopy[i] / matrix.get(i, i);
            for (int k = i - 1; k >= 0; k--) {
                bCopy[k] -= matrix.get(k, i) * x[i];
            }
        }
        return x;
    }

    public static double[] solvePivoting(MySparseMatrix matrix, double[] b){
        int n = b.length;

        for (int i = 0; i < n; i++) {
            if (matrix.get(i, i) == 0) {
                throw new RuntimeException("Element na przekątnej jest równy zero. Wybór elementu podstawowego jest wymagany.");
            }

            int maxRowIndex = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(matrix.get(k, i)) > Math.abs(matrix.get(maxRowIndex, i))) {
                    maxRowIndex = k;
                }
            }

            // Zamiana wierszy
            if (maxRowIndex != i) {
                matrix.swapRows(i, maxRowIndex);
                double tempValue = b[i];
                b[i] = b[maxRowIndex];
                b[maxRowIndex] = tempValue;
            }

            // Eliminacja Gaussa
            for (int j = i + 1; j < n; j++) {
                double factor = matrix.get(j, i) / matrix.get(i, i);
                for (int k = i; k < n; k++) {
                    double newValue = matrix.get(j, k) - factor * matrix.get(i, k);
                    matrix.set(j, k, newValue);
                }
                b[j] = b[j] - factor * b[i];
            }
        }

        // Rozwiązanie układu równań
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum += matrix.get(i, j) * x[j];
            }
            x[i] = (b[i] - sum) / matrix.get(i, i);
        }
        return x;
    }
}
