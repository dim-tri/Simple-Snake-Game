package gameplay;

import control.GameState;
import display.DisplayManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class GameLoop implements Runnable {
    private static final int EASY_FRAME_RATE = 6;
    private static final int NORMAL_FRAME_RATE = 12;
    private static final int HARD_FRAME_RATE = 18;
	
	private final Grid grid;
    private final GraphicsContext context;
    private int frameRate;
    private float interval;
    private boolean running;
    private boolean paused;
    private boolean keyIsPressed;
    private Stage primaryStage;
    
    public GameLoop(final Grid grid, final GraphicsContext context) {
        this.grid = grid;
        this.context = context;
        setFrameRate(); //sets based on difficulty
        interval = 1000.0f / frameRate;
        running = true;
        paused = false;
        keyIsPressed = false;
    }
    
    //Tha main game loop
    @Override
    public void run() {
        while (running && !paused) {
            float time = System.currentTimeMillis();

            keyIsPressed = false;
            grid.update();
            DisplayManager.paint(grid, context);
            
            //Check if snake died
            if (!grid.getSnake().isSafe()) {
                pause();
                GameState.GameOver(grid.getScore().getScorePoints()*grid.getDifficulty(), getStage(), grid.getDifficulty()); // calculate final score
                System.out.println("Difficulty " + grid.getDifficulty());
                break;
            }

            time = System.currentTimeMillis() - time;

            // Timing. Effects snake speed!
            if (time < interval) {
                try {
                    Thread.sleep((long) (interval - time));
                } catch (InterruptedException ignore) {
                }
            }
        }
    }
    
    public void stop() {
        running = false;
    }

    public boolean isKeyPressed() {
        return keyIsPressed;
    }

    public void setKeyPressed() {
        keyIsPressed = true;
    }

    public void resume() {
        paused = false;
    }

    public void pause() {
        paused = true;
    }

    public boolean isPaused() {
        return paused;
    }

    public int getFrameRate() {
        return frameRate;
    }

    public void setFrameRate() {
        
    	if(grid.getDifficulty() == 1) this.frameRate = EASY_FRAME_RATE;
    	else if(grid.getDifficulty() == 2) this.frameRate = NORMAL_FRAME_RATE;
    	else this.frameRate = HARD_FRAME_RATE;
    	
    	
    }
    public void setStage(Stage stage) {
    	primaryStage = stage;
    }
    public Stage getStage() {
    	return primaryStage;
    }
}