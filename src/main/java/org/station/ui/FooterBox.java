package org.station.ui;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

import org.springframework.stereotype.Component;

@Component
public class FooterBox extends HBox
{
	public FooterBox()
	{
		setPrefSize(800, 60);
		setSpacing(10);
		setPadding(new Insets(15, 12, 15, 12));
		setStyle("-fx-background-color: #336699;");
	}
}