import java.util.Random;

public class BetCard extends BetData{
	public int spotsMarked;
	protected int[] grid;	//size 80: 0 = unmarked; 1 = marked
	protected int gridSize = 80;
	protected int totalSpots;
	protected boolean random;
	
	
	//constructor
	public BetCard() {
		this.spotsMarked = 0;
		this.grid = new int[this.gridSize];	//default all zeros
		this.totalSpots = 0;
		this.random = false;
	}
	
	
	
	/*mark/unmark selected number: 1-80 (inclusive), only if haven't marked enough
	 * 
	 * -1: error -- not between 1-80 or max number of spots marked
	 * 0: unmarked
	 * 1: marked
	 * 
	 */
	public int mark(int num) {
		num--;
		if(num > 79 || num < 0) {	//return if invalid
			return -2;
		}
		if(grid[num] == 1) {	//if already marked, unmark it
			unMark(num);
			return 0;
		}
		
		//user hasn't chosen an amount of spots
		if(totalSpots == 0) {
			return -3;
		}
		
		if(spotsMarked == totalSpots) {
			return -1;
		}
		
		this.grid[num] = 1;
		++this.spotsMarked;
		return 1;
	}
	
	//unmark selected number: 1-80
	private void unMark(int num) {
		if(num > 79 || num < 0)	//return if invalid
			return;
		
		this.grid[num] = 0;
		--this.spotsMarked;
	}
	
	//returns array of marked numbers
	public int[] getMarked() {
		
		int[] marked = new int[this.spotsMarked];
		int arrIndex = 0;
		for(int i = 0; i < this.gridSize; i++) {
			if(this.grid[i] == 1) {
				marked[arrIndex] = i+1;
				arrIndex++;
			}
		}
		return marked;
	}
	
	//sets 'totalSpots' to 'num'
	public void setTotalSpots(int num) {
		this.totalSpots = num;
	}
	
	//sets 'random' to 'val'
	public void setRandom(boolean val) {
		this.random = val;
	}
	
	
	//returns array of random spots chosen for the user, marks grid (no dups)
	protected void markRandomUserSpots() {
		
		if(this.totalSpots == 0) {//if no number of spots selected, return
			return;				
		}
		clearGrid();	//clear the grid marked spots
		Random rand = new Random();
		int r;
		for(int i = 0; i < this.totalSpots; i++) {
			r = rand.nextInt(80)+1;	//random number 1-80
			
			if(this.grid[r-1] == 1)	//if already marked, get a new random integer
				i--;
			else {
				mark(r);	//mark on grid as well
			}
		}
		
		return;
	}
	
	protected void clearGrid() {
		spotsMarked = 0;
		for(int i = 0; i < gridSize; i++) {
			grid[i] = 0;
		}
	}
	
}
