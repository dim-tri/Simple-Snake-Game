package util;

import java.io.IOException;

import display.DisplayManager;
import gameplay.GameLoop;
import gameplay.Grid;
import gameplay.elements.Score;
import gameplay.elements.Snake;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Init {

	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;

	private GameLoop gameLoop;
	private Grid grid;
	private GraphicsContext context;
	private int difficulty;

	public void StartGame(int difficulty) throws IOException {
		this.difficulty = difficulty;
		StackPane gameStackPane = new StackPane();
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		context = canvas.getGraphicsContext2D();

		canvas.setFocusTraversable(true);
		
		canvas.setOnKeyPressed(e -> {
            Snake snake = grid.getSnake();
            if (gameLoop.isKeyPressed()) {
                return;
            }
            gameLoop.setKeyPressed(); // Prevent double movement of snake
            switch (e.getCode()) {
                case UP:
                    snake.setUp();
                    break;
                case DOWN:
                    snake.setDown();
                    break;
                case LEFT:
                    snake.setLeft();
                    break;
                case RIGHT:
                    snake.setRight();
                    break;
                case ENTER:
                    if (gameLoop.isPaused()) {
                        reset();
                        (new Thread(gameLoop)).start();
                    }
            }
        });
			
		reset();

		gameStackPane.getChildren().add(canvas);
		

		
		Scene scene = new Scene(gameStackPane);

		Stage primaryStage = new Stage();
		primaryStage.setResizable(false);
		primaryStage.setTitle("Snake");
		primaryStage.setOnCloseRequest(e -> System.exit(0));
		primaryStage.setScene(scene);
		primaryStage.show();

		(new Thread(gameLoop)).start();
		gameLoop.setStage(primaryStage);
		
	}

	private void reset() {
		grid = new Grid(WIDTH, HEIGHT, difficulty);
		gameLoop = new GameLoop(grid, context);
		DisplayManager.paint(grid, context);
	}
	
}
