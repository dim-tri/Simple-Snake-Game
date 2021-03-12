package control;

import java.io.IOException;

import gui.Controller;
import javafx.application.Platform;
import javafx.stage.Stage;
import util.Init;
import util.Resources;
import util.Window;

//Manage the different Stages of the game

public class GameState {

	//When Starting a new game
	public static void InitGame(int difficulty) throws IOException {
		Init init = new Init();
		init.StartGame(difficulty);
	}
	//When snake collides
	public static void GameOver(int finalScore, Stage stage, int difficulty) {
		System.out.println("THE SNAKE IS DEAD");
		System.out.println("Score:" + finalScore);
		
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
				Controller controller = Window.createWindow("Game Over", Resources.GAME_OVER_FXML, false);
				controller.setScore(finalScore);
				controller.setDifficulty(difficulty);
				controller.update();
				stage.close();
				
		
		    }
		});

	}
	
	public static void PauseGame() {
		//when pressing ESC
		//return with ENTER
	}

}
