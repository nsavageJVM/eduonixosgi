package eduonix.osgi.maingui.impl;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import org.apache.felix.dm.annotation.api.Component;
import org.apache.felix.dm.annotation.api.ServiceDependency;
import org.apache.felix.dm.annotation.api.Start;

import eduonix.osgi.democode.OsgiDemoService;

@Component
public class BootStrapGui {

	@ServiceDependency
	private volatile OsgiDemoService m_osgiService;
	
	private volatile VBox pane;
	
	@Start
	public void start() {
		Platform.runLater(() -> {

			Stage primaryStage = m_osgiService.getRootNode();
			primaryStage.setTitle("Osgi Javafx example!");
			pane = new VBox();
			pane.getChildren().add( new Label("Osgi Javafx example!"));

			primaryStage.setScene(new Scene(pane, 300, 250));
			primaryStage.show();

		});
	}
	
	
	

}
