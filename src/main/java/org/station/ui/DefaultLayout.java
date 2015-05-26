package org.station.ui;

import javafx.scene.layout.BorderPane;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultLayout extends BorderPane implements InitializingBean
{
	@Autowired
	private HeaderBox headerBox;

	@Autowired
	private FooterBox footerBox;

	@Autowired
	private LeftMenuBox leftMenuBox;

	@Autowired
	private ChartBox chartBox;

	@Autowired
	private ViewSelectorBox viewSelector;

	public DefaultLayout() {}

	private void buildLayout()
	{
		setTop(headerBox);
		setBottom(footerBox);
		setLeft(leftMenuBox);
		setCenter(chartBox.getLineChart());
	}

	public void afterPropertiesSet() throws Exception
	{
		buildLayout();
	}

	public HeaderBox getHeaderBox()
	{
		return headerBox;
	}

	public void setHeaderBox(HeaderBox headerBox)
	{
		this.headerBox = headerBox;
	}

	public FooterBox getFooterBox()
	{
		return footerBox;
	}

	public void setFooterBox(FooterBox footerBox)
	{
		this.footerBox = footerBox;
	}

	public LeftMenuBox getLeftMenuBox()
	{
		return leftMenuBox;
	}

	public void setLeftMenuBox(LeftMenuBox leftMenuBox)
	{
		this.leftMenuBox = leftMenuBox;
	}

	public ChartBox getChartBox()
	{
		return chartBox;
	}

	public void setChartBox(ChartBox chartBox)
	{
		this.chartBox = chartBox;
	}
}