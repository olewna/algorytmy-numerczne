import java.io.FileWriter;
import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException {
        double minKat = -89;
        double minRadian = Math.toRadians(minKat);
        double maxKat = 89;
        double maxRadian = Math.toRadians(maxKat);
        int skladniki = 40;

        int argumenty = 1000000;
        double[][] bladBwzg = new double[4][argumenty];
        double[][] bladWzg = new double[4][argumenty];

        FileWriter wyniki = new FileWriter("wyniki.txt");
        for (int i=0; i<argumenty; i++){
            double elem = minRadian + ((maxRadian - minRadian)/(argumenty - 1)) * i;
            try {
                wyniki.write("Math.sin(elem): "+ Math.sin(elem) + " ");
                wyniki.write("V1: " + sinus1(elem,skladniki) + " ");
                wyniki.write("V2: " + sinus2(elem,skladniki) + " ");
                wyniki.write("V3: " + sinus3(elem,skladniki) + " ");
                wyniki.write("V4: " + sinus4(elem,skladniki) + "\n");

            } catch (IOException e) {
                System.out.println("error");
                e.printStackTrace();
            }
            //System.out.println("Ilość składników dla " + elem + " radianów: " + q2(elem));

            bladBwzg[0][i] = Math.abs(sinus1(elem,skladniki)-Math.sin(elem));
            bladBwzg[1][i] = Math.abs(sinus2(elem,skladniki)-Math.sin(elem));
            bladBwzg[2][i] = Math.abs(sinus3(elem,skladniki)-Math.sin(elem));
            bladBwzg[3][i] = Math.abs(sinus4(elem,skladniki)-Math.sin(elem));
            for (int k=0; k<4; k++){
                bladWzg[k][i] = Math.abs(bladBwzg[k][i]/Math.sin(elem));
            }
        }
        wyniki.close();


        int iloscElementowWSredniej = 1000;
        int iloscPrzedzialow = argumenty/iloscElementowWSredniej;
        double[][] sredniBladBwzg = new double[4][iloscPrzedzialow];
        double[][] sredniBladWzg = new double[4][iloscPrzedzialow];
        double[] osX = new double[iloscPrzedzialow];

        for (int i=0; i<iloscPrzedzialow; i++) {
            double elem = minRadian + ((maxRadian - minRadian)/(argumenty - 1)) * i * iloscElementowWSredniej;
            osX[i] = elem;
            double[] bladBwzgPrzedzial = new double[4];
            double[] bladWzgPrzedzial = new double[4];
            int poczatek = i * iloscElementowWSredniej;
            int koniec = (i+1) * iloscElementowWSredniej;

            for (int j=poczatek; j<koniec; j++) {
                for (int k=0; k<4; k++){
                    bladBwzgPrzedzial[k] += bladBwzg[k][j];
                    bladWzgPrzedzial[k] += bladWzg[k][j];
                }
            }

            for (int j=0; j<4; j++) {
                sredniBladBwzg[j][i] = bladBwzgPrzedzial[j] / iloscElementowWSredniej * 1E14;
                sredniBladWzg[j][i] = bladWzgPrzedzial[j] / iloscElementowWSredniej * 1E14;
            }

        }


        Chart.rysujWykres(sredniBladBwzg, osX, "Wykres średniego błędu bezwzględnego dla sin x");
        Chart.rysujWykres(sredniBladWzg, osX,"Wykres średniego błędu względnego dla sin x");



        //sredniBladBwzg,osX
        //sredniBladWzg,osX

    }

    static double potega(double liczba, double potega){
        double result = liczba;
        if (potega==0){
            return 1;
        } else {
            for (double i = 1; i<potega; i++){
                result *= liczba;
            }
        }
        return result;
    }

    static double silnia(double k){
        double result = 1.0;
        for (double i = 1; i<=k; i++){
            result *=  i;
        }
        return result;
    }

    static double sinus1(double k, int skladniki){
        double suma = 0.0;
        for (double i=0; i<skladniki ; i++){
            double sinus = potega(-1,i)*(potega(k,(i*2+1))/silnia(i*2+1));
            suma += sinus;
        }
        return suma;
    }

    static double sinus2(double k,int skladniki){
        double suma = 0.0;
        for (double i=skladniki; i>0; i--){
            double sinus = potega(-1,i-1)*(potega(k,(i*2-1))/silnia(i*2-1));
            suma += sinus;
        }
        return suma;
    }

    static double sinus3(double k,int skladniki){
        double poprzednik = k;
        double suma = k;
        for (double i=1; i<=skladniki; i++){
            poprzednik = -((poprzednik*k*k)/(2*i*(2*i+1)));
            suma += poprzednik;
        }
        return suma;
    }

    static double sinus4(double k,int skladniki){
        double suma = k;
        double poprzednik = k;
        double[] szereg = new double[skladniki];
        szereg[0] = poprzednik;
        for (int i=1; i<=skladniki-1; i++){
            poprzednik = -((poprzednik*k*k)/(2*i*(2*i+1)));
            szereg[i] = poprzednik;
        }
        for (int i=skladniki-1; i>0; i--){
            suma += szereg[i];
        }
        return suma;
    }

    static double q2(double radiany){
        double sinus = Math.sin(radiany);
        double suma = 0;
        double poprzednik = radiany;
        double skladniki = 1;
        double blad = 1;
        while (blad > 1E-6) {
            suma += poprzednik;
            skladniki++;
            poprzednik *= -1*radiany*radiany/((2*skladniki-1)*(2*skladniki-2));
            blad = Math.abs(sinus - suma);
        }
        return skladniki;
    }
}