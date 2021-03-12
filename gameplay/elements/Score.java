package gameplay.elements;

//Keep a universal score for each game session

public class Score {
	private int score;
	
	public int getScorePoints(){
		return score;		
	}
	
	public void addScore(int add) {
		score = score + add;
	}
	
	public void resetScore() {
		score = 0;
	}
	public String getRating() {
		if(score<500) return "Bad";
		else if(score<1000)return "OK";
		else if(score<1500)return "Good";
		else if(score<2000)return "Very Good";
		else return "Excellent";
	}
	
}
