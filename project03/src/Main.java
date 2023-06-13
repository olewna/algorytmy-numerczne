import java.io.*;
import java.util.ArrayList;
interface Function {
    double call(double x);
}

public class Main {
    public static double A1(Function f, double start, double end, int steps) {
        double step = (end - start) / steps;
        double result = f.call(start) + f.call(end);
        for (int i = 1; i < steps; i++) {
            result += 2 * f.call(start + i * step);
        }
        result *= step / 2;
        return result;
    }

    public static double A2(Function f, double start, double end, int steps) {
        double step = (end - start) / steps;

        double result = f.call(start) + f.call(end);
        for (int i = 1; i < steps; i++) {
            if (i % 2 == 0) {
                result += 2 * f.call(start + i * step);
            } else {
                result += 4 * f.call(start + i * step);
            }
        }
        result *= step / 3;
        return result;
    }

    public static double integralTest(Function f, double start, double end) {
        return f.call(end) - f.call(start);
    }

    public static void main(String[] args) throws IOException {
        // f(x) = cos(3x)
        Function f = (x) -> Math.cos(3*x);
        // F(x) = sin(3x)/3
        Function fPrime = (x) -> Math.sin(3*x) / 3;

        // g(x) = e^3x
        Function g = (x) -> Math.exp(3 * x);
        // G(x) = (e^3x)/3
        Function gPrime = (x) -> Math.exp(3 * x) / 3;

        // h(x) = sin(√x)
        Function h = (x) -> Math.sin(Math.sqrt(x));
        // H(x) = 2*sin(√x)-2*√x*cos(√x)
        Function hPrime = (x) -> 2 * Math.sin(Math.sqrt(x)) - 2 * Math.sqrt(x) * Math.cos(Math.sqrt(x));

        FileWriter fileWriterF = new FileWriter("f.txt");
        fileWriterF.write("Błędy metody Trapezów,Błędy metody Simpsona\n");
        FileWriter fileWriterG = new FileWriter("g.txt");
        fileWriterG.write("Błędy metody Trapezów,Błędy metody Simpsona\n");
        FileWriter fileWriterH = new FileWriter("h.txt");
        fileWriterH.write("Błędy metody Trapezów,Błędy metody Simpsona\n");

        ArrayList<Double> resultsA1F = new ArrayList<>();
        ArrayList<Double> resultsA2F = new ArrayList<>();

        ArrayList<Double> resultsA1G = new ArrayList<>();
        ArrayList<Double> resultsA2G = new ArrayList<>();

        ArrayList<Double> resultsA1H = new ArrayList<>();
        ArrayList<Double> resultsA2H = new ArrayList<>();

        for (int i = 10; i < 1000; i += 10) {
            resultsA1F.add(Math.abs(A1(f, 0, 5, i) - integralTest(fPrime, 0, 5)));
            resultsA2F.add(Math.abs(A2(f, 0, 5, i) - integralTest(fPrime, 0, 5)));

            resultsA1G.add(Math.abs(A1(g, -1, 1, i) - integralTest(gPrime, -1, 1)));
            resultsA2G.add(Math.abs(A2(g, -1, 1, i) - integralTest(gPrime, -1, 1)));

            resultsA1H.add(Math.abs(A1(h, 0, 5, i) - integralTest(hPrime, 0, 5)));
            resultsA2H.add(Math.abs(A2(h, 0, 5, i) - integralTest(hPrime, 0, 5)));
        }
        System.out.println(resultsA1F);
        System.out.println(resultsA2F);

        for (int i = 0; i < resultsA2F.size(); i++) {
            fileWriterF.write(resultsA1F.get(i) + "," + resultsA2F.get(i) + "\n");
            fileWriterG.write(resultsA1G.get(i) + "," + resultsA2G.get(i) + "\n");
            fileWriterH.write(resultsA1H.get(i) + "," + resultsA2H.get(i) + "\n");
        }

        fileWriterF.close();
        fileWriterG.close();
        fileWriterH.close();
    }
}