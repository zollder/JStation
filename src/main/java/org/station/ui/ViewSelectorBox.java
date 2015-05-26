package org.station.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import org.springframework.stereotype.Component;

@Component
public class ViewSelectorBox extends FlowPane
{
	public ViewSelectorBox()
	{
		setPrefSize(80, 640);
		setPadding(new Insets(5, 0, 5, 0));
		setVgap(4);
		setHgap(4);
		addMembers();
	}

	private void addMembers()
	{
		// add icon members
		List<ImageView> pages = new ArrayList<ImageView>();
		pages.add(new ImageView(new Image(getClass().getResourceAsStream("images/radar-icon.png"))));
		pages.add(new ImageView(new Image(getClass().getResourceAsStream("images/chart-area-icon.png"))));
		pages.add(new ImageView(new Image(getClass().getResourceAsStream("images/chart-line-icon.png"))));
		pages.add(new ImageView(new Image(getClass().getResourceAsStream("images/chart-scatter-icon.png"))));
		this.getChildren().addAll(pages);
	}
}