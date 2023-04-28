import java.util.Random;


public class Bet extends BetCard{
	public double amountWon;
	private int numCorrectlyMarked;
	private int[] correctMarked;
	private boolean firstDraw;

	
	//constructor
	public Bet() {
		this.amountWon = 0;
		this.numCorrectlyMarked = 0;
		this.firstDraw = true;
	}
	
	//returns random twenty integers in an array; returns null if no drawings left
	public int[] draw() {
		
		if(noDrawingsLeft()) {	//if no more drawingsLeft, return null
			return null;
		}
		this.amountWon = 0;
		numCorrectlyMarked = 0;
		this.drawsLeft--;
		
		if(random == true && firstDraw) 	//set random user spots, if user selected random
			markRandomUserSpots();
		
		firstDraw = false;
		
		int[] nums;

		nums = getTwentyRandoms();	//picks twenty random nums 1-80
		
		//set up counter of numbers user correctly marked
		for(int i: nums) {
			if(this.grid[i-1] == 1)
				this.numCorrectlyMarked++;
		}
		
		//fill up array of correctly marked numbers
		this.correctMarked = new int[this.numCorrectlyMarked];
		int index = 0;
		for(int i: nums) {
			if(this.grid[i-1] == 1)
				this.correctMarked[index++] = i;
		}
		this.amountWon = calculateAddEarnings();
		return nums;	//return the twenty random numbers we picked
	}
	
	//calculates earnings based on number of spots marked correctly and total number of spots
	//called from 'draw' method -- updates earnings
	private double calculateAddEarnings() {
		
		//case 1: totalSpots = 1
		if(totalSpots == 1) {
			if(numCorrectlyMarked == 1) {
				addWinnings(2);
				return 2;
			}
		}
		
		//case 2: totalSpots = 4
		else if(totalSpots == 4) {
			if(numCorrectlyMarked == 2) {
				addWinnings(1);
				return 1;
			}
			else if(numCorrectlyMarked == 3) {
				addWinnings(5);
				return 5;
			}
			else if(numCorrectlyMarked == 4) {
				addWinnings(75);
				return 75;
			}
		}
		
		//case 3: totalSpots = 8
		else if(totalSpots == 8) {
			if(numCorrectlyMarked == 4) {
				addWinnings(2);
				return 2;
			}
			else if(numCorrectlyMarked == 5) {
				addWinnings(12);
				return 12;
			}
			else if(numCorrectlyMarked == 6) {
				addWinnings(50);
				return 50;
			}
			else if(numCorrectlyMarked == 7) {
				addWinnings(750);
				return 750;
			}
			else if(numCorrectlyMarked == 8) {
				addWinnings(10000);
				return 10000;
			}
		}
		//case 4: totalSpots = 10
		else if(totalSpots == 10){
			if(numCorrectlyMarked == 0) {
				addWinnings(5);
				return 5;
			}
			else if(numCorrectlyMarked == 5) {
				addWinnings(2);
				return 2;
			}
			else if(numCorrectlyMarked == 6) {
				addWinnings(15);
				return 15;
			}
			else if(numCorrectlyMarked == 7) {
				addWinnings(40);
				return 40;
			}
			else if(numCorrectlyMarked == 8) {
				addWinnings(450);
				return 450;
			}
			else if(numCorrectlyMarked == 9) {
				addWinnings(4250);
				return 4250;
			}
			else if(numCorrectlyMarked == 10) {
				addWinnings(100000);
				return 100000;
			}
		}
		return 0;
	}
	
	public int[] getCorrectMarked() {
		return this.correctMarked;
	}
	
	public int getNumCorrectlyMarked() {
		return this.numCorrectlyMarked;
	}
	
	//resets everything (new game)
	public void newCard() {
		this.spotsMarked = 0;
		this.totalSpots = 0;
		this.random = false;
		clearGrid();
		this.totalWinnings = 0;
		this.drawsLeft = 0;
		this.totalDrawings = 0;
		this.firstDraw = true;
		this.numCorrectlyMarked = 0;
		this.amountWon = 0;
	}
	
	
//helper function for checking for dups in array 'arr'
	private boolean isDup(int num, int[] arr) {
		int length = arr.length;
		for(int i = 0; i < length; i++) {
			if(num == arr[i])
				return true;
		}
		return false;
	}
	
	//returns an array of twenty random integers between 1-80 (inclusive) (no dups)
	private int[] getTwentyRandoms() {
		Random rand = new Random();
		int[] nums = new int[20];
		int r;
		for(int i = 0; i < 20; i++) {
			r = rand.nextInt(80)+1;	//random number 1-80
			
			if(isDup(r, nums))	//if is dup in arr 'nums', get a new random integer
				i--;
			else
				nums[i] = r;	//else, proceed as normal
		}
		return nums;
	}
	
	//used for debugging and making sure firstDraw is working properly
	public boolean getFirstDraw() {
		return this.firstDraw;
	}
	
	
	
}
