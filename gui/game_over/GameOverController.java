package gui.game_over;

import java.io.IOException;

import control.GameState;
import gui.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.Resources;
import util.Window;

public class GameOverController extends Controller{
	@FXML
	private Label scoreText;
	
	private int score = 0;
	private int difficulty;
	
	public void btnRetryOnAction (ActionEvent event) throws IOException{
		GameState.InitGame(difficulty);	
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}	
	
	public void btnQuitOnAction (ActionEvent event) throws IOException{
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
	public void btnMenuOnAction (ActionEvent event) throws IOException{
		Window.createWindow("Snake Game", Resources.MAIN_MENU_FXML, false);
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
	
	public void setScore(int score) {
		this.score = score;
		
	}
	public int getScore() {
		return score;
	}
	
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	@Override
	public void update() {
		scoreText.setText(String.valueOf(this.getScore()));
	}
	
}
