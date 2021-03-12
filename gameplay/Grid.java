package gameplay;


import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import control.FoodPopulator;
import gameplay.elements.Food;
import gameplay.elements.Score;
import gameplay.elements.Snake;

//A Basic potision system based on points
public class Grid {

    public static final int SIZE = 20;
    public static final Color COLOR = new Color(0.1, 0.1, 0.1, 1);
    public static final int NUM_OF_FOODS = 2;

    private final int cols;
    private final int rows;  

    private Snake snake;
    private FoodPopulator foodPopulator;
    private List<Food> foodList;
    private Score score;
    private int difficulty;

    public Grid(final double width, final double height, int difficulty) {
        rows = (int) width / SIZE;
        cols = (int) height / SIZE;
        this.difficulty = difficulty;
        foodList = new ArrayList<Food>();
        
        // Spawn the snake at the center of the screen
        snake = new Snake(this, new Point(rows / 2, cols / 2));
        
        score = new Score();
        foodPopulator = new FoodPopulator(difficulty, this);        
        

        // put the food at a random location
       // food = new Food(getRandomPoint(), 500, Resources.GOLDEN_APPLE_SPRITE);

    }

    public Point wrap(Point point) {
        int x = point.getX();
        int y = point.getY();
        if (x >= rows) x = 0;
        if (y >= cols) y = 0;
        if (x < 0) x = rows - 1;
        if (y < 0) y = cols - 1;
        return new Point(x, y);
    }

    public Point getRandomPoint() {
        Random random = new Random();
        Point point;
        do {
            point = new Point(random.nextInt(rows), random.nextInt(cols));
        } while (point.equals(snake.getHead()));
        return point;
    }

    //Called every frame
    boolean flag = true;
    public void update() {

        //Tick all food items
        for(int i = 0;i<foodList.size();i++) { 
	    	foodList.get(i).tick();
        	if(foodList.get(i).getLife() < 1) {
	    		foodList.remove(i);
	    	}

        }
    	
        flag = true;
        //Cycle all foods of the grid
        for(int i = 0;i<foodList.size();i++) {       	
        	if (foodList.get(i).getPoint().equals(snake.getHead())) {
	            snake.extend(); 
	            score.addScore(foodList.get(i).getValue());
	            
	            //Check if it is a bonus food
	            if(foodList.get(i).isBonus()) {
	            	foodList.remove(i);
	            }else {
	            	foodList.get(i).setPoint(getRandomPoint());
	            	foodPopulator.randomBonusFood();
	            }
	            flag = false;
	        }	        
        }
        if(flag) snake.move();
        
    }

    public void addFood(Food food) {
    	foodList.add(food);
    }
    public double getWidth() {
        return rows * SIZE;
    }

    public double getHeight() {
        return cols * SIZE;
    }
    
    public Score getScore() {
    	return score;
    }
    public Snake getSnake() {
        return snake;
    }
    public List<Food> getFood() {
    	return foodList;
    }

	public int getDifficulty() {
		return difficulty;
	}

}