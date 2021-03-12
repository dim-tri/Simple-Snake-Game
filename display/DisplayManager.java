package display;


import static gameplay.Grid.SIZE;

import gameplay.Grid;
import gameplay.Point;
import gameplay.elements.Snake;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class DisplayManager {

	//The Main draw cycle of the game
	   
	public static void paint(Grid grid, GraphicsContext gc) {
       //Draw Grid
    	gc.setFill(Grid.COLOR);
        gc.fillRect(0, 0, grid.getWidth(), grid.getHeight());


        //Draw Foods    
        for(int i = 0;i<grid.getFood().size();i++) {
        	paintImage(grid.getFood().get(i).getPoint(), grid.getFood().get(i).getSprite(), gc);
        }
        
        //Draw the Snake
        Snake snake = grid.getSnake();
        gc.setFill(Snake.COLOR);
        snake.getPoints().forEach(point -> paintPoint(point, gc));
        if (!snake.isSafe()) {
            gc.setFill(Snake.DEAD);
            paintPoint(snake.getHead(), gc);
        }

        //Draw The score
        gc.setFill(Color.BEIGE);
        gc.fillText("Score : " + grid.getScore().getScorePoints(), 10, 590);
    }
    
    //Fill point of a grid
    private static void paintPoint(Point point, GraphicsContext gc) {
        gc.fillRect(point.getX() * SIZE, point.getY() * SIZE, SIZE, SIZE);
        
    }
    //Draw element with sprite
    private static void paintImage(Point point,String sprite, GraphicsContext gc) {
    	Image apple = new Image(sprite);
    	gc.drawImage(apple, point.getX() * SIZE, point.getY() * SIZE, SIZE, SIZE);
    }

    public static void paintResetMessage(GraphicsContext gc) {
        gc.setFill(Color.AQUAMARINE);
        gc.fillText("Hit RETURN to reset.", 10, 10);
    }
}