import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public interface MySparseMatrix {
    void set(int row, int col, double value);
    double get(int row, int col);
    int getNumRows();
    int getNumCols();
    void swapRows(int row1, int row2);
}


