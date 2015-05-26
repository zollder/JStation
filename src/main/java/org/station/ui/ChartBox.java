package org.station.ui;

import java.util.concurrent.ConcurrentLinkedQueue;

import javafx.animation.AnimationTimer;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ChartBox
{
	@Resource
	ConcurrentLinkedQueue<Number> dataQueue;

    @Value("${chart.maxdatapoints}")
    private int maxDataPoints;

    private LineChart<Number, Number> lineChart;
	private NumberAxis xAxis;
	private NumberAxis yAxis;
	private Series<Number, Number> series;

	private int xSeriesData = 0;

	// constructor
	public ChartBox()
	{
		xAxis = buildXAxis();
		yAxis = buildYAxis();
		series = buildSeries();
		setLineChart(buildChart(xAxis, yAxis, series));
		prepareTimeline();
	}


	private NumberAxis buildXAxis()
	{
		NumberAxis xAxis = new NumberAxis(0, maxDataPoints, maxDataPoints / 10);
		xAxis.setForceZeroInRange(false);
		xAxis.setAutoRanging(false);
		return xAxis;
	}

	private NumberAxis buildYAxis()
	{
		NumberAxis yAxis = new NumberAxis();
		yAxis.setAutoRanging(true);
		return yAxis;
	}

	private Series<Number, Number> buildSeries()
	{
		// TODO: must be a generic series that can be used in any chart type, refactor please
		Series<Number, Number> series = new LineChart.Series<Number, Number>();
		series.setName("Line Chart Series");
		return series;
	}

	/**
	 * Initializes and configures AreaChart.
	 * Adds data series to created chart.
	 * @return configured LineChart
	 */
	private LineChart<Number, Number> buildChart(NumberAxis x, NumberAxis y, Series<Number, Number> series)
	{
		LineChart<Number, Number> chart = new LineChart<Number, Number>(x, y)
		{
			// Override to remove symbols on each data point
			@Override protected void dataItemAdded(Series<Number, Number> series, int itemIndex, Data<Number, Number> item) {}
		};

		// configure chart
		chart.setAnimated(false);
		chart.setId("liveLineChart");
		chart.setTitle("Sensor Readings");
		chart.getData().add(series);

		return chart;
	}

	/**
	 * Is called in the JavaFX main thread.
	 */
	public void prepareTimeline()
	{
		// Every frame to take any data from queue and add to chart
		new AnimationTimer()
		{
			@Override
			public void handle(long now)
			{
				addDataToSeries();
			}
		}.start();
	}

	public void addDataToSeries()
	{
		for (int i = 0; i < 20; i++)
		{ // -- add 20 numbers to the plot+
			if (dataQueue.isEmpty())
				break;
			series.getData().add(new Data<Number, Number>(xSeriesData++, dataQueue.remove()));
		}

		// remove points to keep us at no more than MAX_DATA_POINTS
		if (series.getData().size() > maxDataPoints)
		{
			series.getData().remove(0, series.getData().size() - maxDataPoints);
		}

		// update
		xAxis.setLowerBound(xSeriesData - maxDataPoints);
		xAxis.setUpperBound(xSeriesData - 1);
	}

	public void setMaxDataPoints(int maxValue)
	{
		this.maxDataPoints = maxValue;
	}

	public LineChart<Number, Number> getLineChart()
	{
		return lineChart;
	}

	public void setLineChart(LineChart<Number, Number> lineChart)
	{
		this.lineChart = lineChart;
	}

	public static class ChartBuilder
	{

	}
}