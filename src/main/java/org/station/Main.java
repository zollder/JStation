package org.station;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends Application
{
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		logger.debug("[MAIN] Current Date : {}", getCurrentDate());
		System.out.println(getCurrentDate());
		
		launch(args);
	}
	
	private static Date getCurrentDate()
	{
		return new Date();
	}
}