package com;

import javafx.application.Application;
import javafx.stage.Stage;
import util.Resources;
import util.Window;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
		//Create Main Menu Window
    	Window.createWindow("Snake Game", Resources.MAIN_MENU_FXML, false);
    }

}