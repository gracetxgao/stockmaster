package ui.components;

import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import ui.StockPanel;

// represents a panel used to hold a graph showing stock price history
public class GraphPanel {
    private XYSeries series;
    private ChartPanel cp;
    private StockPanel sp;
    private JFreeChart chart;
    private XYSeriesCollection dataset;
    public static final int GRAPH_WIDTH = 150;
    public static final int GRAPH_HEIGHT = 200;

    // EFFECTS: constructs graph and adds to stock panel
    public GraphPanel(List<BigDecimal> data, StockPanel sp) {
        series = new XYSeries("price");
        for (int i = 0; i < data.size(); i++) {
            series.add(i, data.get(i));
        }
        dataset = new XYSeriesCollection(series);
        chart = ChartFactory.createXYLineChart(null, "day", "price", dataset,
                PlotOrientation.VERTICAL, false, true, true);
        cp = new ChartPanel(chart);
        cp.setPreferredSize(new Dimension(GRAPH_WIDTH, GRAPH_HEIGHT));
        this.sp = sp;
        sp.add(cp);
    }

    // MODIFIES: this
    // EFFECTS: adds data point to graph
    public void updateGraph(List<BigDecimal> data) {
        for (int i = 0; i < data.size(); i++) {
            series.add(i, data.get(i));
        }
    }

    // MODIFIES: this
    // EFFECTS: resets and adds all data points to graph
    public void loadGraph(List<BigDecimal> data) {
        series.clear();
        for (int i = 0; i < data.size(); i++) {
            series.add(i, data.get(i));
        }
    }
}
