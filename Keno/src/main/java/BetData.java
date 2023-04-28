
public class BetData {
	
	protected double totalWinnings;
	public int drawsLeft;
	protected int totalDrawings;
	
	public BetData() {
		this.totalWinnings = 0;
		this.drawsLeft = 0;
		this.totalDrawings = 0;
	}
	
	
	public double getTotalWinnings() {
		return this.totalWinnings;
	}
	
	public void addWinnings(double winnings) {
		this.totalWinnings += winnings;
	}
	
	public void setTotalDrawings(int num) {
		this.totalDrawings = num;
		this.drawsLeft = num;
	}
	
	public boolean noDrawingsLeft() {
		if(this.drawsLeft == 0 || this.totalDrawings == 0)
			return true;
		return false;
	}
	
}
