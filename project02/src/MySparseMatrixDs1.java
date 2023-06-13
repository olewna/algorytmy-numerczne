import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MySparseMatrixDs1 implements MySparseMatrix {

    private Map<Key, Double> ds;
    private final int numRows;
    private final int numCols;

    public int getNumRows() {
        return numRows;
    }
    public int getNumCols() {
        return numCols;
    }

    public MySparseMatrixDs1(int numRows, int numCols, double[][] matrix) {
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
        Key key = new Key(row, col);
        if (value != 0.0) {
            ds.put(key, value);
        } else {
            ds.remove(key);
        }
    }
    public double get(int row, int col) {
        if (ds.containsKey(new Key(row,col))) {
            return ds.get(new Key(row,col));
        }
        return 0.0;
    }

    public void swapRows(int row1, int row2) {
        for (int col = 0; col < numCols; col ++){
            Key temp1 = new Key(row1, col);
            Key temp2 = new Key(row2, col);
            double val1 = get(row1, col);
            double val2 = get(row2, col);
            if (val2 != 0) {
                ds.remove(temp1);
                ds.put(temp1,val2);
            }
            if (val1 != 0){
                ds.remove(temp2);
                ds.put(temp2,val1);
            }
        }
    }
}
