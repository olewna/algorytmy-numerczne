import Jama.Matrix;

public class Main {
    public static void main(String[] args) {

        final int ROWS = 4;
        final int COLUMNS = 4;
        // Losowa macierz
        Matrix xPattern = Matrix.random(ROWS, COLUMNS);

        double[][] xGetTemp = xPattern.getArray();

        // {(row, col): value}
        MySparseMatrixDs1 ds1 = new MySparseMatrixDs1(ROWS, COLUMNS, xGetTemp);

        // {row: {col: value}}
        MySparseMatrixDs4 ds4 = new MySparseMatrixDs4(ROWS, COLUMNS, xGetTemp);

        //    Losowy wektor
        Matrix bPattern = Matrix.random(4, 1);
        double[] bGet = bPattern.getColumnPackedCopy();
        double[] bGetCopy = bGet.clone();

        Matrix patternSolution = xPattern.solve(bPattern);
        double[] solutionds4 = Gauss.solvePivoting(ds4,bGet);
        double[] solutionds1 = Gauss.solvePivoting(ds1,bGetCopy);

        System.out.println("Ds4: \n");
        for(double i: solutionds4) {
            System.out.println(i);
        }


        System.out.println("\nDs1: \n");
        for(double i : solutionds1) {
            System.out.println(i);
        }

        System.out.println("\nFunkcja wbudowana: ");
        patternSolution.print(4, 16);

    }
}
