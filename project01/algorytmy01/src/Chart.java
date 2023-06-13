import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Chart extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final Color[] COLORS = {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE};
    private static final String[] LEGENDS = {"V1", "V2", "V3", "V4"};
    public Chart(double[][] data, double[] osX, String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1920, 1080));
        setLocationRelativeTo(null);

        XYSeriesCollection dataset = new XYSeriesCollection();
        for (int i = 0; i < data.length; i++) {
            XYSeries series = new XYSeries(LEGENDS[i]);
            for (int j = 0; j < data[i].length; j++) {
                series.add(osX[j], data[i][j]);
            }
            dataset.addSeries(series);
        }

        JFreeChart chart1 = ChartFactory.createXYLineChart("Wykres średniego błędu" +
                        " sin x obliczanego przy pomocy szeregu Taylora",
                "radiany", "Średni błąd", dataset,
                PlotOrientation.VERTICAL, true, true, true);

        XYPlot plot = (XYPlot) chart1.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setDomainGridlinePaint(Color.BLACK);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setAutoRange(false);
        rangeAxis.setTickLabelPaint(Color.BLACK);
        rangeAxis.setLabelPaint(Color.BLACK);

        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setAutoRangeIncludesZero(false);
        domainAxis.setTickLabelPaint(Color.BLACK);
        domainAxis.setLabelPaint(Color.BLACK);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        for (int i = 0; i < COLORS.length; i++) {
            renderer.setSeriesPaint(i, COLORS[i]);
            renderer.setSeriesShapesVisible(i, true);
            renderer.setSeriesLinesVisible(i, false);
            renderer.setSeriesShape(i, new Ellipse2D.Double(-1.0, -1.0, 1.0, 1.0));
        }
        plot.setRenderer(renderer);



        ChartPanel chartPanel = new ChartPanel(chart1);
        chartPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setContentPane(chartPanel);
    }


    public static void rysujWykres(double[][] data, double[] osX, String title) {
        Chart wykres = new Chart(data, osX, title);
        wykres.setVisible(true);
    }
}
