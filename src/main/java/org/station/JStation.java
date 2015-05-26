package org.station;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.station.configuration.SpringApplicationConfig;
import org.station.ui.DefaultLayout;

public class JStation extends Application
{
	private AnnotationConfigApplicationContext appContext;

	protected static final Logger logger = LoggerFactory.getLogger(JStation.class);
	private String defaultCss = getClass().getResource("css/application.css").toString();

	@Override
	public void start(Stage primaryStage)
	{
		appContext = new AnnotationConfigApplicationContext(SpringApplicationConfig.class);
		showUi(primaryStage);
	}

    @Override
    public void stop()
    {
        if (appContext != null)
            appContext.close();

        try { super.stop(); }
        catch (Exception ex)
        {
        	logger.error("Failed to close context. Message: " + ex.getLocalizedMessage());
        }
    }

	private void showUi(final Stage primaryStage)
	{
		DefaultLayout appLayout = appContext.getBean(DefaultLayout.class);

		primaryStage.setTitle("map");

		Scene scene = new Scene(appLayout, 800, 600);
		scene.getStylesheets().add(defaultCss);

		primaryStage.setScene(scene);
		primaryStage.show();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>()
        		{
        			public void handle(KeyEvent event)
        			{
        				if (isShortcutPressed(event))
        				{
        					primaryStage.close();
        					stop();
        				}
        			}
        		});
	}

    private static boolean isShortcutPressed(final KeyEvent keyEvent)
    {
        return keyEvent.isAltDown() && keyEvent.isControlDown();
    }

	public static void main(String[] args)
	{
		launch(args);
	}
}