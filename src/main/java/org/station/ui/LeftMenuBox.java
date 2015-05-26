package org.station.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import org.springframework.stereotype.Component;

@Component
public class LeftMenuBox extends VBox
{
	public LeftMenuBox()
	{
		setPrefSize(160, 640);
		setPadding(new Insets(10)); // Set all sides to 10
		setSpacing(8); // Gap between nodes

		buildTitle();
		buildMenuContent();
	}

	private void buildTitle()
	{
		// set title text and font props
		Text title = new Text("Options");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		getChildren().add(title);
	}

	private void buildMenuContent()
	{
		// create and configure hyperlinks to pages
		Hyperlink options[] = new Hyperlink[]
				{
					new Hyperlink("Radar view"),
					new Hyperlink("IMU stats"),
					new Hyperlink("IMU 3D view"),
					new Hyperlink("Camera view")
				};

		// add configured menu options to the layout
		for (int i = 0; i < 4; i++)
		{
			VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
			getChildren().add(options[i]);
		}
	}
}