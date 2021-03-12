package gui.main_menu;

import java.io.IOException;

import control.GameState;
import gui.Controller;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import util.Resources;
import util.Window;

public class MenuController extends Controller{

	private int difficulty = 2; //Default
	
	public void btnStartOnAction(ActionEvent event) throws IOException{
		GameState.InitGame(difficulty);
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
	public void btnLeaderboardOnAction(ActionEvent event) throws IOException{
			
	}
	
	public void btnEasyOnAction(ActionEvent event) throws IOException{
		difficulty = 1;
		System.out.println("Difficulty set to easy");
	}
	public void btnNormalOnAction(ActionEvent event) throws IOException{
		difficulty = 2;
		System.out.println("Difficulty set to normal");
	}
	public void btnHardOnAction(ActionEvent event) throws IOException{
		difficulty = 3;
		System.out.println("Difficulty set to hard");
	}
	
	public void btnAboutOnAction(ActionEvent event) throws IOException{
		Window.createWindow("About", Resources.ABOUT_FXML, false);
	}
	
	
	public void btnExitOnAction(ActionEvent event) throws IOException{
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
	
}
