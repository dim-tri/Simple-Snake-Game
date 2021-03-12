package control;

import gameplay.Grid;
import gameplay.elements.Food;
import util.Resources;

public class FoodPopulator {
	
	private static final int appleValue = 100; //Points
	private static final int goldenAppleValue = 200;
	private static final double bonusChance = 0.5; //Percentage of a bonus apple appearing
	
	private int difficulty;
	private Grid grid;
	private Food food;
	private Food bonusFood;
	
	private double chance;
	
	public FoodPopulator(int difficulty, Grid grid) {
		this.difficulty = difficulty;
		this.grid = grid;
		

		food = new Food(grid.getRandomPoint(), appleValue*difficulty, Resources.APPLE_SPRITE);
		grid.addFood(food);

		randomBonusFood();
		
	}
	
	//Create bonus apple at a certain percentage
	public void randomBonusFood() {
		chance = Math.random();
		if(chance < bonusChance) {
			bonusFood = new Food(grid.getRandomPoint(), goldenAppleValue*difficulty, Resources.GOLDEN_APPLE_SPRITE);
			bonusFood.setAsBonusFood(); 
			grid.addFood(bonusFood);
		}
	}
}
