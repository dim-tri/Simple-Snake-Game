package gameplay.elements;
import gameplay.Point;

//A Class for the food items
public class Food {

    private Point point;
    private int value;
    private String sprite;
    private boolean bonus;
    
    private int life = 40;

    public Food(Point point, int value, String sprite) {
        this.point = point;
        this.value = value;
        this.sprite = sprite;
        
        bonus = false;
    }
    
    public int getValue() {
    	return value;
    }
    public Point getPoint() {
        return point;
    }
    public String getSprite() {
    	return sprite;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
    //Set as bonus to disappear after life<0
    public void setAsBonusFood() {
    	bonus = true;
    }
    public boolean isBonus() {
    	return bonus;
    }
    //Run every cycle of the grid
	public void tick() {
		if(isBonus()) {
			life--;
		}
		
	}
	//How many frames before disappearing. Don't use on main apple!
	public int getLife() {
		return life;
	}
}