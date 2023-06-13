import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MySparseMatrixDs4 implements MySparseMatrix {
    private Map<Integer, Map<Integer, Double>> ds;
    private final int numRows;
    private final int numCols;

    public int getNumRows() {
        return numRows;
    }
    public int getNumCols() {
        return numCols;
    }

    public MySparseMatrixDs4(int numRows, int numCols, double[][] matrix) {
        this.ds = new HashMap<>();
        this.numRows = numRows;
        this.numCols = numCols;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                double value = matrix[row][col];
                if (value != 0) {
                    set(row, col, value);
                }
            }
        }
    }

    public void set(int row, int col, double value) {
        if (!ds.containsKey(row)) {
            ds.put(row, new HashMap<>());
        }
        ds.get(row).put(col, value);
    }
    public double get(int row, int col) {
        if (ds.containsKey(row) && ds.get(row).containsKey(col)) {
            return ds.get(row).get(col);
        }
        return 0.0;
    }
    public void swapRows(int row1, int row2) {
        if (row1 == row2) {
            return;
        }

        Map<Integer, Double> tempRow = ds.getOrDefault(row1, new HashMap<>());
        if (ds.containsKey(row2)) {
            ds.put(row1, ds.get(row2));
        } else {
            ds.remove(row1);
        }

        if (!tempRow.isEmpty()) {
            ds.put(row2, tempRow);
        } else {
            ds.remove(row2);
        }
    }
}
