package util;

import java.io.IOException;

import gui.Controller;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Window {
	//Create a new window based on an  fxml and return its controller instance.
	public static Controller createWindow(String title, String fxml, boolean wait){
		try {
			Platform.setImplicitExit(false);
			Stage window = new Stage();				
			
			System.out.println("Loading " + fxml);
			FXMLLoader fxmlLoader = new FXMLLoader(Window.class.getResource(fxml));
			BorderPane layout = fxmlLoader.load();
			
			Scene scene = new Scene(layout);
			scene.getStylesheets().add(Window.class.getResource(Resources.MAIN_CSS).toExternalForm());//use the css
			
			window.setTitle(title);			
			window.setScene(scene);
			window.setResizable(false);
			window.initModality(Modality.APPLICATION_MODAL);// Can not go out of focus
			
			if(wait) {
				window.showAndWait();
				return fxmlLoader.getController();
			}else {
				window.show();
				return fxmlLoader.getController();
			}									
			
		} catch (IOException e) {			
			e.printStackTrace();			
			return null;
		}
	}
}