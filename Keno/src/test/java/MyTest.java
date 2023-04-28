import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MyTest {

	@Test
	void testConstructor() {
		Bet betGame = new Bet();
		assertEquals(0, betGame.getNumCorrectlyMarked());
		assertTrue(betGame.getFirstDraw());
		assertEquals(0, betGame.totalWinnings);
		assertEquals(0, betGame.drawsLeft);
		assertEquals(0, betGame.totalDrawings);
		assertEquals(0, betGame.spotsMarked);
		assertEquals(0, betGame.totalSpots);
		assertEquals(80, betGame.gridSize);
		//assert all values in grid are equal to 0
		for(int i = 0; i < betGame.gridSize; i++) {
			assertEquals(0, betGame.grid[i]);
		}
		assertFalse(betGame.random);
		assertEquals(0, betGame.amountWon);
	}
	
	@Test
	void testAddWinnings() {
		Bet betGame = new Bet();
		assertEquals(0, betGame.totalWinnings);
		betGame.addWinnings(20);
		assertEquals(20, betGame.totalWinnings);
		betGame.addWinnings(400.57);
		assertEquals(420.57, betGame.totalWinnings);
		betGame.addWinnings(0);
		assertEquals(420.57, betGame.totalWinnings);
	}
	
	@Test
	void testSetTotalDrawings() {
		Bet betGame = new Bet();
		betGame.setTotalDrawings(3);
		assertEquals(3, betGame.totalDrawings);
		betGame.setTotalDrawings(4);
		assertEquals(4, betGame.totalDrawings);
		betGame.setTotalDrawings(0);
		assertEquals(0, betGame.totalDrawings);
		betGame.setTotalDrawings(2);
		assertEquals(2, betGame.totalDrawings);
		betGame.setTotalDrawings(1);
		assertEquals(1, betGame.totalDrawings);
	}
	
	@Test
	void testNoDrawingsLeft() {
		Bet betGame = new Bet();
		betGame.setTotalDrawings(3);
		assertFalse(betGame.noDrawingsLeft());
		betGame.setTotalDrawings(0);
		assertTrue(betGame.noDrawingsLeft());
		betGame.setTotalDrawings(1);
		assertFalse(betGame.noDrawingsLeft());
	}
	
	@Test
	void testMark() {
		Bet betGame = new Bet();
		assertEquals(-3, betGame.mark(3));	//totalSpots = 0, -3 is returned when user hasn't chosen the amount of spots to mark
		betGame.setTotalSpots(4);
		//accepts numbers 1-80
		assertEquals(-2, betGame.mark(81));
		assertEquals(1, betGame.mark(3));
		assertEquals(1, betGame.mark(80));
		assertEquals(1, betGame.mark(1));
		assertEquals(1, betGame.mark(5));
		assertEquals(-1, betGame.mark(33));	//have already marked 4 spots out of 4, return -1
		//checked if marked on grid properly; 0-79, so we subtract one
		assertEquals(1, betGame.grid[3-1]);
		assertEquals(1, betGame.grid[80-1]);
		assertEquals(1, betGame.grid[1-1]);
		assertEquals(1, betGame.grid[5-1]);
	}
	
	@Test
	void testSpotsMarked() {
		Bet betGame = new Bet();
		assertEquals(-3, betGame.mark(3));	//totalSpots = 0, -3 is returned when user hasn't chosen the amount of spots to mark
		betGame.setTotalSpots(4);
		//accepts numbers 1-80
		assertEquals(-2, betGame.mark(81));
		assertEquals(0, betGame.spotsMarked);	//0 marked spots
		assertEquals(1, betGame.mark(3));
		assertEquals(1, betGame.spotsMarked);	//1 marked spot
		assertEquals(1, betGame.mark(80));
		assertEquals(2, betGame.spotsMarked);	//2 marked spots
		assertEquals(1, betGame.mark(1));
		assertEquals(3, betGame.spotsMarked);	//3 marked spots
		assertEquals(1, betGame.mark(5));
		assertEquals(4, betGame.spotsMarked);	//4 marked spots
		assertEquals(-1, betGame.mark(33));	//have already marked 4 spots, return -1
		assertEquals(4, betGame.spotsMarked); //no spot is marked, 'spotsMarked' remains at 4
	}
	
	@Test
	void testMarkUnMark() {
		Bet betGame = new Bet();
		assertEquals(-3, betGame.mark(3));	//totalSpots = 0, -3 is returned when user hasn't chosen the amount of spots to mark
		betGame.setTotalSpots(4);
		//accepts numbers 1-80
		assertEquals(-2, betGame.mark(81));
		assertEquals(1, betGame.mark(3));
		assertEquals(1, betGame.mark(80));
		assertEquals(1, betGame.mark(1));
		assertEquals(1, betGame.mark(5));
		//checked if marked on grid properly; 0-79, so we subtract one
		assertEquals(1, betGame.grid[3-1]);
		assertEquals(1, betGame.grid[80-1]);
		assertEquals(1, betGame.grid[1-1]);
		assertEquals(1, betGame.grid[5-1]);
		//start unMarking some: mark returns 0
		assertEquals(0, betGame.mark(3));
		assertEquals(0, betGame.mark(80));
		assertEquals(0, betGame.mark(1));
		//check if mark/unmarked on grid
		assertEquals(0, betGame.grid[3-1]);
		assertEquals(0, betGame.grid[80-1]);
		assertEquals(0, betGame.grid[1-1]);
		assertEquals(1, betGame.grid[5-1]);
	}
	
	@Test
	void testGetMarked() {
		Bet betGame = new Bet();
		assertEquals(-3, betGame.mark(3));	//totalSpots = 0, -3 is returned when user hasn't chosen the amount of spots to mark
		betGame.setTotalSpots(4);
		//accepts numbers 1-80
		assertEquals(1, betGame.mark(3));
		assertEquals(1, betGame.mark(80));
		assertEquals(1, betGame.mark(1));
		assertEquals(1, betGame.mark(5));
		int[] arr = betGame.getMarked();
		for(int spotNum: arr) {
			assertEquals(1, betGame.grid[spotNum-1]);
		}
	}
	
	@Test
	void testClearGrid() {
		Bet betGame = new Bet();
		betGame.setTotalSpots(4);
		assertEquals(1, betGame.mark(3));
		assertEquals(1, betGame.mark(80));
		assertEquals(1, betGame.mark(1));
		assertEquals(1, betGame.mark(5));
		betGame.clearGrid();
		for(int i = 0; i < betGame.gridSize; i++) {	//gridSize is equal to 80
			assertEquals(0, betGame.grid[i]);
		}
		//remark those spots
		assertEquals(1, betGame.mark(3));
		assertEquals(1, betGame.mark(80));
		assertEquals(1, betGame.mark(1));
		assertEquals(1, betGame.mark(5));
		
		//un-mark some of those spots
		assertEquals(0, betGame.mark(3));
		assertEquals(0, betGame.mark(80));
		assertEquals(0, betGame.mark(1));
		
		//check if mark/unmarked on grid
		assertEquals(0, betGame.grid[3-1]);
		assertEquals(0, betGame.grid[80-1]);
		assertEquals(0, betGame.grid[1-1]);
		assertEquals(1, betGame.grid[5-1]);
	}
	
	@Test
	void testMarkRandomUserSpots() {
		Bet betGame = new Bet();
		betGame.setTotalSpots(8);
		betGame.markRandomUserSpots();
		int[] arr = betGame.getMarked();
		assertEquals(8, arr.length);	//check if 8 spots were marked for the user
		//check that there are no duplicates
		for(int i = 0; i < arr.length; i++) {
			for(int j = i+1; j< arr.length; j++) {
				assertNotEquals(arr[i], arr[j]);
			}
		}
		//make sure no number is out of bounds (1-80) and that the spot is marked on the grid
		for(int spotNum: arr) {
			assertTrue(spotNum <= 80);
			assertTrue(spotNum >= 1);
			assertEquals(1, betGame.grid[spotNum-1]);
		}
	}
	
	@Test
	void testDrawOne() {
		Bet betGame = new Bet();
		betGame.setTotalSpots(8);
		assertEquals(null, betGame.draw());	//number of draws not set, returns null
	}
	
	@Test
	void testGetTwentyRandoms() {
		Bet betGame = new Bet();
		betGame.setTotalSpots(1);
		betGame.setTotalDrawings(1);
		int[] twentyRands = betGame.draw();
		//make sure no dups in twentyRands
		for(int i = 0; i < 20; i++) {
			for(int j = i+1; j < 20; j++) {
				assertNotEquals(twentyRands[i], twentyRands[j]);
			}
		}
	}
	
	@Test
	void testDrawTwo() {
		Bet betGame = new Bet();
		assertTrue(betGame.getFirstDraw());
		betGame.setTotalSpots(1);	//1 total spot
		betGame.setTotalDrawings(1);	//1 total drawing
		betGame.mark(5);				//marked spot 5
		
		int[] twentyRands = betGame.draw();
		assertFalse(betGame.getFirstDraw());
		int correctlyMarked = 0;
		for(int spotNum: twentyRands) {
			if(spotNum == 5) {
				correctlyMarked++;
			}
		}
		assertEquals(correctlyMarked, betGame.getNumCorrectlyMarked());
		int[] correctMarkedArr = betGame.getCorrectMarked();
		
		//check if correctMarked contains correct marks (spots we guessed correctly)
		for(int spotNum: correctMarkedArr) {
			assertEquals(1, betGame.grid[spotNum-1]);
		}
		//check that earnings were added Correctly
		if(betGame.getNumCorrectlyMarked() == 1) {assertEquals(2, betGame.totalWinnings);}
		else {assertEquals(0, betGame.totalWinnings);}
		assertEquals(null, betGame.draw());
	}
	
	@Test
	void testDrawThree() {
		Bet betGame = new Bet();
		assertTrue(betGame.getFirstDraw());
		betGame.setTotalSpots(1);	//1 total spot
		betGame.setTotalDrawings(2);	//2 total drawings
		betGame.mark(5);				//marked spot 5
		int numDraws = 0;	//used to check how many times while loop is executed
		while(!betGame.noDrawingsLeft()) {
			int[] twentyRands = betGame.draw();
			numDraws++;
			assertFalse(betGame.getFirstDraw());
			int correctlyMarked = 0;
			for(int spotNum: twentyRands) {
				if(spotNum == 5) {
					correctlyMarked++;
				}
			}
			assertEquals(correctlyMarked, betGame.getNumCorrectlyMarked());
			int[] correctMarkedArr = betGame.getCorrectMarked();
			//check if correctMarked contains correct marks (spots we guessed correctly)
			for(int spotNum: correctMarkedArr) {
				assertEquals(1, betGame.grid[spotNum-1]);
			}
			//check that earnings were added Correctly
			if(betGame.getNumCorrectlyMarked() == 1) {assertEquals(2, betGame.amountWon);}
			else {assertEquals(0, betGame.amountWon);}
		}
		assertEquals(2, numDraws);	//should've gone through while loop twice
	}
	
	@Test
	void testDrawFour() {
		Bet betGame = new Bet();
		assertTrue(betGame.getFirstDraw());
		betGame.setTotalSpots(1);	//1 total spot
		betGame.setTotalDrawings(4);	//4 total drawings
		betGame.mark(5);				//marked spot 5
		
		int numDraws = 0;	//used to check how many times while loop is executed
		
		while(!betGame.noDrawingsLeft()) {
			int[] twentyRands = betGame.draw();
			numDraws++;
			assertFalse(betGame.getFirstDraw());
			int correctlyMarked = 0;
			for(int spotNum: twentyRands) {
				if(spotNum == 5) {
					correctlyMarked++;
				}
			}
			assertEquals(correctlyMarked, betGame.getNumCorrectlyMarked());
			int[] correctMarkedArr = betGame.getCorrectMarked();
			//check if correctMarked contains correct marks (spots we guessed correctly)
			for(int spotNum: correctMarkedArr) {
				assertEquals(1, betGame.grid[spotNum-1]);
			}
			//check that earnings were added Correctly
			if(betGame.getNumCorrectlyMarked() == 1) {assertEquals(2, betGame.amountWon);}
			else {assertEquals(0, betGame.amountWon);}
		}
		assertEquals(4, numDraws);	//should've gone through while loop four times
	}
	
	@Test
	void testDrawFive() {
		Bet betGame = new Bet();
		assertTrue(betGame.getFirstDraw());
		betGame.setTotalSpots(4);		//4 total spots
		betGame.setTotalDrawings(1);	//1 total drawings
		betGame.mark(5);				//marked spot 5
		betGame.mark(80);				//marked spot 80
		betGame.mark(1);				//marked spot 1
		betGame.mark(23);				//marked spot 23
		
		int numDraws = 0;	//used to check how many times while loop is executed
		
		while(!betGame.noDrawingsLeft()) {
			int[] twentyRands = betGame.draw();
			numDraws++;
			assertFalse(betGame.getFirstDraw());
			int correctlyMarked = 0;
			for(int spotNum: twentyRands) {
				if(spotNum == 5 || spotNum == 80 || spotNum == 1 || spotNum == 23) {
					correctlyMarked++;
				}
			}
			assertEquals(correctlyMarked, betGame.getNumCorrectlyMarked());
			int[] correctMarkedArr = betGame.getCorrectMarked();
			//check if correctMarked contains correct marks (spots we guessed correctly)
			for(int spotNum: correctMarkedArr) {
				assertEquals(1, betGame.grid[spotNum-1]);
			}
			
			//check that earnings were added Correctly
			if(betGame.getNumCorrectlyMarked() == 2) {assertEquals(1, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 3) {assertEquals(5, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 4) {assertEquals(75, betGame.amountWon);}
			else {assertEquals(0, betGame.amountWon);}
		}
		assertEquals(1, numDraws);	//should've gone through while loop once
	}
	
	@Test
	void testDrawSix() {
		Bet betGame = new Bet();
		assertTrue(betGame.getFirstDraw());
		betGame.setTotalSpots(4);		//4 total spots
		betGame.setTotalDrawings(4);	//4 total drawings
		betGame.mark(5);				//marked spot 5
		betGame.mark(80);				//marked spot 80
		betGame.mark(1);				//marked spot 1
		betGame.mark(23);				//marked spot 23
		
		int numDraws = 0;	//used to check how many times while loop is executed
		
		while(!betGame.noDrawingsLeft()) {
			int[] twentyRands = betGame.draw();
			numDraws++;
			assertFalse(betGame.getFirstDraw());
			int correctlyMarked = 0;
			for(int spotNum: twentyRands) {
				if(spotNum == 5 || spotNum == 80 || spotNum == 1 || spotNum == 23) {
					correctlyMarked++;
				}
			}
			assertEquals(correctlyMarked, betGame.getNumCorrectlyMarked());
			int[] correctMarkedArr = betGame.getCorrectMarked();
			//check if correctMarked contains correct marks (spots we guessed correctly)
			for(int spotNum: correctMarkedArr) {
				assertEquals(1, betGame.grid[spotNum-1]);
			}
			
			//check that earnings were added Correctly
			if(betGame.getNumCorrectlyMarked() == 2) {assertEquals(1, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 3) {assertEquals(5, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 4) {assertEquals(75, betGame.amountWon);}
			else {assertEquals(0, betGame.amountWon);}
		}
		assertEquals(4, numDraws);	//should've gone through while loop four times
	}
	
	@Test
	void testDrawSeven() {
		Bet betGame = new Bet();
		assertTrue(betGame.getFirstDraw());
		betGame.setTotalSpots(8);		//8 total spots
		betGame.setTotalDrawings(1);	//1 total drawings
		betGame.mark(5);				//marked spot 5
		betGame.mark(80);				//marked spot 80
		betGame.mark(1);				//marked spot 1
		betGame.mark(23);				//marked spot 23
		betGame.mark(24);				//marked spot 24
		betGame.mark(25);				//marked spot 25
		betGame.mark(47);				//marked spot 47
		betGame.mark(61);				//marked spot 61
		
		int numDraws = 0;	//used to check how many times while loop is executed
		
		while(!betGame.noDrawingsLeft()) {
			int[] twentyRands = betGame.draw();
			numDraws++;
			assertFalse(betGame.getFirstDraw());
			int correctlyMarked = 0;
			for(int spotNum: twentyRands) {
				if(spotNum == 5 || spotNum == 80 || spotNum == 1 || spotNum == 23 || spotNum == 24 || spotNum == 25 || spotNum == 47 || spotNum == 61) {
					correctlyMarked++;
				}
			}
			assertEquals(correctlyMarked, betGame.getNumCorrectlyMarked());
			int[] correctMarkedArr = betGame.getCorrectMarked();
			//check if correctMarked contains correct marks (spots we guessed correctly)
			for(int spotNum: correctMarkedArr) {
				assertEquals(1, betGame.grid[spotNum-1]);
			}
			
			//check that earnings were added Correctly
			if(betGame.getNumCorrectlyMarked() == 4) {assertEquals(2, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 5) {assertEquals(12, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 6) {assertEquals(50, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 7) {assertEquals(750, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 8) {assertEquals(10000, betGame.amountWon);}
			else {assertEquals(0, betGame.amountWon);}
		}
		assertEquals(1, numDraws);	//should've gone through while loop once
	}
	
	@Test
	void testDrawEight() {
		Bet betGame = new Bet();
		assertTrue(betGame.getFirstDraw());
		betGame.setTotalSpots(8);		//8 total spots
		betGame.setTotalDrawings(4);	//4 total drawings
		betGame.mark(5);				//marked spot 5
		betGame.mark(80);				//marked spot 80
		betGame.mark(1);				//marked spot 1
		betGame.mark(23);				//marked spot 23
		betGame.mark(24);				//marked spot 24
		betGame.mark(25);				//marked spot 25
		betGame.mark(47);				//marked spot 47
		betGame.mark(61);				//marked spot 61
		
		int numDraws = 0;	//used to check how many times while loop is executed
		
		while(!betGame.noDrawingsLeft()) {
			int[] twentyRands = betGame.draw();
			numDraws++;
			assertFalse(betGame.getFirstDraw());
			int correctlyMarked = 0;
			for(int spotNum: twentyRands) {
				if(spotNum == 5 || spotNum == 80 || spotNum == 1 || spotNum == 23 || spotNum == 24 || spotNum == 25 || spotNum == 47 || spotNum == 61) {
					correctlyMarked++;
				}
			}
			assertEquals(correctlyMarked, betGame.getNumCorrectlyMarked());
			int[] correctMarkedArr = betGame.getCorrectMarked();
			//check if correctMarked contains correct marks (spots we guessed correctly)
			for(int spotNum: correctMarkedArr) {
				assertEquals(1, betGame.grid[spotNum-1]);
			}
			
			//check that earnings were added Correctly
			if(betGame.getNumCorrectlyMarked() == 4) {assertEquals(2, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 5) {assertEquals(12, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 6) {assertEquals(50, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 7) {assertEquals(750, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 8) {assertEquals(10000, betGame.amountWon);}
			else {assertEquals(0, betGame.amountWon);}
		}
		assertEquals(4, numDraws);	//should've gone through while loop four times
	}
	
	@Test
	void testDrawNine() {
		Bet betGame = new Bet();
		assertTrue(betGame.getFirstDraw());
		betGame.setTotalSpots(10);		//10 total spots
		betGame.setTotalDrawings(1);	//1 total drawings
		betGame.mark(5);				//marked spot 5
		betGame.mark(80);				//marked spot 80
		betGame.mark(1);				//marked spot 1
		betGame.mark(23);				//marked spot 23
		betGame.mark(24);				//marked spot 24
		betGame.mark(25);				//marked spot 25
		betGame.mark(47);				//marked spot 47
		betGame.mark(61);				//marked spot 61
		betGame.mark(42);				//marked spot 42
		betGame.mark(2);				//marked spot 2
		
		int numDraws = 0;	//used to check how many times while loop is executed
		
		while(!betGame.noDrawingsLeft()) {
			int[] twentyRands = betGame.draw();
			numDraws++;
			assertFalse(betGame.getFirstDraw());
			int correctlyMarked = 0;
			for(int spotNum: twentyRands) {
				if(spotNum == 2 || spotNum == 42 || spotNum == 5 || spotNum == 80 || spotNum == 1 || spotNum == 23 || spotNum == 24 || spotNum == 25 || spotNum == 47 || spotNum == 61) {
					correctlyMarked++;
				}
			}
			assertEquals(correctlyMarked, betGame.getNumCorrectlyMarked());
			int[] correctMarkedArr = betGame.getCorrectMarked();
			//check if correctMarked contains correct marks (spots we guessed correctly)
			for(int spotNum: correctMarkedArr) {
				assertEquals(1, betGame.grid[spotNum-1]);
			}
			
			//check that earnings were added Correctly
			if(betGame.getNumCorrectlyMarked() == 0) {assertEquals(5, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 5) {assertEquals(2, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 6) {assertEquals(15, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 7) {assertEquals(40, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 8) {assertEquals(450, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 9){assertEquals(4250, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 10){assertEquals(100000, betGame.amountWon);}
		}
		assertEquals(1, numDraws);	//should've gone through while loop once
	}
	
	@Test
	void testDrawTen() {
		Bet betGame = new Bet();
		assertTrue(betGame.getFirstDraw());
		betGame.setTotalSpots(10);		//10 total spots
		betGame.setTotalDrawings(4);	//4 total drawings
		betGame.mark(5);				//marked spot 5
		betGame.mark(80);				//marked spot 80
		betGame.mark(1);				//marked spot 1
		betGame.mark(23);				//marked spot 23
		betGame.mark(24);				//marked spot 24
		betGame.mark(25);				//marked spot 25
		betGame.mark(47);				//marked spot 47
		betGame.mark(61);				//marked spot 61
		betGame.mark(42);				//marked spot 42
		betGame.mark(2);				//marked spot 2
		
		int numDraws = 0;	//used to check how many times while loop is executed
		
		while(!betGame.noDrawingsLeft()) {
			int[] twentyRands = betGame.draw();
			numDraws++;
			assertFalse(betGame.getFirstDraw());
			int correctlyMarked = 0;
			for(int spotNum: twentyRands) {
				if(spotNum == 2 || spotNum == 42 || spotNum == 5 || spotNum == 80 || spotNum == 1 || spotNum == 23 || spotNum == 24 || spotNum == 25 || spotNum == 47 || spotNum == 61) {
					correctlyMarked++;
				}
			}
			assertEquals(correctlyMarked, betGame.getNumCorrectlyMarked());
			int[] correctMarkedArr = betGame.getCorrectMarked();
			//check if correctMarked contains correct marks (spots we guessed correctly)
			for(int spotNum: correctMarkedArr) {
				assertEquals(1, betGame.grid[spotNum-1]);
			}
			
			//check that earnings were added Correctly
			if(betGame.getNumCorrectlyMarked() == 0) {assertEquals(5, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 5) {assertEquals(2, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 6) {assertEquals(15, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 7) {assertEquals(40, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 8) {assertEquals(450, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 9){assertEquals(4250, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 10){assertEquals(100000, betGame.amountWon);}
		}
		assertEquals(4, numDraws);	//should've gone through while loop 4 times
	}
	
	
	@Test
	void testNewCard() {
		Bet betGame = new Bet();
		assertTrue(betGame.getFirstDraw());
		betGame.setTotalSpots(10);		//10 total spots
		betGame.setTotalDrawings(4);	//4 total drawings
		betGame.mark(5);				//marked spot 5
		betGame.mark(80);				//marked spot 80
		betGame.mark(1);				//marked spot 1
		betGame.mark(23);				//marked spot 23
		betGame.mark(24);				//marked spot 24
		betGame.mark(25);				//marked spot 25
		betGame.mark(47);				//marked spot 47
		betGame.mark(61);				//marked spot 61
		betGame.mark(42);				//marked spot 42
		betGame.mark(2);				//marked spot 2
		
		int numDraws = 0;	//used to check how many times while loop is executed
		
		while(!betGame.noDrawingsLeft()) {
			int[] twentyRands = betGame.draw();
			numDraws++;
			assertFalse(betGame.getFirstDraw());
			int correctlyMarked = 0;
			for(int spotNum: twentyRands) {
				if(spotNum == 2 || spotNum == 42 || spotNum == 5 || spotNum == 80 || spotNum == 1 || spotNum == 23 || spotNum == 24 || spotNum == 25 || spotNum == 47 || spotNum == 61) {
					correctlyMarked++;
				}
			}
			assertEquals(correctlyMarked, betGame.getNumCorrectlyMarked());
			int[] correctMarkedArr = betGame.getCorrectMarked();
			//check if correctMarked contains correct marks (spots we guessed correctly)
			for(int spotNum: correctMarkedArr) {
				assertEquals(1, betGame.grid[spotNum-1]);
			}
			
			//check that earnings were added Correctly
			if(betGame.getNumCorrectlyMarked() == 0) {assertEquals(5, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 5) {assertEquals(2, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 6) {assertEquals(15, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 7) {assertEquals(40, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 8) {assertEquals(450, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 9){assertEquals(4250, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 10){assertEquals(100000, betGame.amountWon);}
		}
		assertEquals(4, numDraws);	//should've gone through while loop 4 times
		
		//start new card
		betGame.newCard();
		
		//check if newCard reset values
		assertEquals(0, betGame.getNumCorrectlyMarked());
		assertTrue(betGame.getFirstDraw());
		assertEquals(0, betGame.totalWinnings);
		assertEquals(0, betGame.drawsLeft);
		assertEquals(0, betGame.totalDrawings);
		assertEquals(0, betGame.spotsMarked);
		assertEquals(0, betGame.totalSpots);
		assertEquals(80, betGame.gridSize);
		//assert all values in grid are equal to 0
		for(int i = 0; i < betGame.gridSize; i++) {
			assertEquals(0, betGame.grid[i]);
		}
		assertFalse(betGame.random);
		assertEquals(0, betGame.amountWon);
	}
	
	@Test
	void testStartNewCardOne() {
		Bet betGame = new Bet();
		assertTrue(betGame.getFirstDraw());
		betGame.setTotalSpots(10);		//10 total spots
		betGame.setTotalDrawings(4);	//4 total drawings
		betGame.mark(5);				//marked spot 5
		betGame.mark(80);				//marked spot 80
		betGame.mark(1);				//marked spot 1
		betGame.mark(23);				//marked spot 23
		betGame.mark(24);				//marked spot 24
		betGame.mark(25);				//marked spot 25
		betGame.mark(47);				//marked spot 47
		betGame.mark(61);				//marked spot 61
		betGame.mark(42);				//marked spot 42
		betGame.mark(2);				//marked spot 2
		
		int numDraws = 0;	//used to check how many times while loop is executed
		
		while(!betGame.noDrawingsLeft()) {
			int[] twentyRands = betGame.draw();
			numDraws++;
			assertFalse(betGame.getFirstDraw());
			int correctlyMarked = 0;
			for(int spotNum: twentyRands) {
				if(spotNum == 2 || spotNum == 42 || spotNum == 5 || spotNum == 80 || spotNum == 1 || spotNum == 23 || spotNum == 24 || spotNum == 25 || spotNum == 47 || spotNum == 61) {
					correctlyMarked++;
				}
			}
			assertEquals(correctlyMarked, betGame.getNumCorrectlyMarked());
			int[] correctMarkedArr = betGame.getCorrectMarked();
			//check if correctMarked contains correct marks (spots we guessed correctly)
			for(int spotNum: correctMarkedArr) {
				assertEquals(1, betGame.grid[spotNum-1]);
			}
			
			//check that earnings were added Correctly
			if(betGame.getNumCorrectlyMarked() == 0) {assertEquals(5, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 5) {assertEquals(2, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 6) {assertEquals(15, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 7) {assertEquals(40, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 8) {assertEquals(450, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 9){assertEquals(4250, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 10){assertEquals(100000, betGame.amountWon);}
		}
		assertEquals(4, numDraws);	//should've gone through while loop 4 times
		
		//start new card
		betGame.newCard();
		
		//check if newCard reset values
		assertEquals(0, betGame.getNumCorrectlyMarked());
		assertTrue(betGame.getFirstDraw());
		assertEquals(0, betGame.totalWinnings);
		assertEquals(0, betGame.drawsLeft);
		assertEquals(0, betGame.totalDrawings);
		assertEquals(0, betGame.spotsMarked);
		assertEquals(0, betGame.totalSpots);
		assertEquals(80, betGame.gridSize);
		//assert all values in grid are equal to 0
		for(int i = 0; i < betGame.gridSize; i++) {
			assertEquals(0, betGame.grid[i]);
		}
		assertFalse(betGame.random);
		assertEquals(0, betGame.amountWon);
		
		//start the new game
		assertTrue(betGame.getFirstDraw());
		betGame.setTotalSpots(8);		//8 total spots
		betGame.setTotalDrawings(1);	//1 total drawings
		betGame.mark(5);				//marked spot 5
		betGame.mark(80);				//marked spot 80
		betGame.mark(1);				//marked spot 1
		betGame.mark(23);				//marked spot 23
		betGame.mark(24);				//marked spot 24
		betGame.mark(25);				//marked spot 25
		betGame.mark(47);				//marked spot 47
		betGame.mark(61);				//marked spot 61
		
		numDraws = 0;	//used to check how many times while loop is executed
		
		while(!betGame.noDrawingsLeft()) {
			int[] twentyRands = betGame.draw();
			numDraws++;
			assertFalse(betGame.getFirstDraw());
			int correctlyMarked = 0;
			for(int spotNum: twentyRands) {
				if(spotNum == 5 || spotNum == 80 || spotNum == 1 || spotNum == 23 || spotNum == 24 || spotNum == 25 || spotNum == 47 || spotNum == 61) {
					correctlyMarked++;
				}
			}
			assertEquals(correctlyMarked, betGame.getNumCorrectlyMarked());
			int[] correctMarkedArr = betGame.getCorrectMarked();
			//check if correctMarked contains correct marks (spots we guessed correctly)
			for(int spotNum: correctMarkedArr) {
				assertEquals(1, betGame.grid[spotNum-1]);
			}
			
			//check that earnings were added Correctly
			if(betGame.getNumCorrectlyMarked() == 4) {assertEquals(2, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 5) {assertEquals(12, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 6) {assertEquals(50, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 7) {assertEquals(750, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 8) {assertEquals(10000, betGame.amountWon);}
			else {assertEquals(0, betGame.amountWon);}
		}
		assertEquals(1, numDraws);	//should've gone through while loop once
	}
	
	@Test
	void testStartNewCardTwo() {
		Bet betGame = new Bet();
		assertTrue(betGame.getFirstDraw());
		betGame.setTotalSpots(10);		//10 total spots
		betGame.setTotalDrawings(4);	//4 total drawings
		betGame.mark(5);				//marked spot 5
		betGame.mark(80);				//marked spot 80
		betGame.mark(1);				//marked spot 1
		betGame.mark(23);				//marked spot 23
		betGame.mark(24);				//marked spot 24
		betGame.mark(25);				//marked spot 25
		betGame.mark(47);				//marked spot 47
		betGame.mark(61);				//marked spot 61
		betGame.mark(42);				//marked spot 42
		betGame.mark(2);				//marked spot 2
		
		int numDraws = 0;	//used to check how many times while loop is executed
		
		while(!betGame.noDrawingsLeft()) {
			int[] twentyRands = betGame.draw();
			numDraws++;
			assertFalse(betGame.getFirstDraw());
			int correctlyMarked = 0;
			for(int spotNum: twentyRands) {
				if(spotNum == 2 || spotNum == 42 || spotNum == 5 || spotNum == 80 || spotNum == 1 || spotNum == 23 || spotNum == 24 || spotNum == 25 || spotNum == 47 || spotNum == 61) {
					correctlyMarked++;
				}
			}
			assertEquals(correctlyMarked, betGame.getNumCorrectlyMarked());
			int[] correctMarkedArr = betGame.getCorrectMarked();
			//check if correctMarked contains correct marks (spots we guessed correctly)
			for(int spotNum: correctMarkedArr) {
				assertEquals(1, betGame.grid[spotNum-1]);
			}
			
			//check that earnings were added Correctly
			if(betGame.getNumCorrectlyMarked() == 0) {assertEquals(5, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 5) {assertEquals(2, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 6) {assertEquals(15, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 7) {assertEquals(40, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 8) {assertEquals(450, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 9){assertEquals(4250, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 10){assertEquals(100000, betGame.amountWon);}
		}
		assertEquals(4, numDraws);	//should've gone through while loop 4 times
		
		//start new card
		betGame.newCard();
		
		//check if newCard reset values
		assertEquals(0, betGame.getNumCorrectlyMarked());
		assertTrue(betGame.getFirstDraw());
		assertEquals(0, betGame.totalWinnings);
		assertEquals(0, betGame.drawsLeft);
		assertEquals(0, betGame.totalDrawings);
		assertEquals(0, betGame.spotsMarked);
		assertEquals(0, betGame.totalSpots);
		assertEquals(80, betGame.gridSize);
		//assert all values in grid are equal to 0
		for(int i = 0; i < betGame.gridSize; i++) {
			assertEquals(0, betGame.grid[i]);
		}
		assertFalse(betGame.random);
		assertEquals(0, betGame.amountWon);
		
		//start the new game
		assertTrue(betGame.getFirstDraw());
		betGame.setTotalSpots(8);		//8 total spots
		betGame.setTotalDrawings(4);	//4 total drawings
		betGame.mark(5);				//marked spot 5
		betGame.mark(80);				//marked spot 80
		betGame.mark(1);				//marked spot 1
		betGame.mark(23);				//marked spot 23
		betGame.mark(24);				//marked spot 24
		betGame.mark(25);				//marked spot 25
		betGame.mark(47);				//marked spot 47
		betGame.mark(61);				//marked spot 61
		
		numDraws = 0;	//used to check how many times while loop is executed
		
		while(!betGame.noDrawingsLeft()) {
			int[] twentyRands = betGame.draw();
			numDraws++;
			assertFalse(betGame.getFirstDraw());
			int correctlyMarked = 0;
			for(int spotNum: twentyRands) {
				if(spotNum == 5 || spotNum == 80 || spotNum == 1 || spotNum == 23 || spotNum == 24 || spotNum == 25 || spotNum == 47 || spotNum == 61) {
					correctlyMarked++;
				}
			}
			assertEquals(correctlyMarked, betGame.getNumCorrectlyMarked());
			int[] correctMarkedArr = betGame.getCorrectMarked();
			//check if correctMarked contains correct marks (spots we guessed correctly)
			for(int spotNum: correctMarkedArr) {
				assertEquals(1, betGame.grid[spotNum-1]);
			}
			
			//check that earnings were added Correctly
			if(betGame.getNumCorrectlyMarked() == 4) {assertEquals(2, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 5) {assertEquals(12, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 6) {assertEquals(50, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 7) {assertEquals(750, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 8) {assertEquals(10000, betGame.amountWon);}
			else {assertEquals(0, betGame.amountWon);}
		}
		assertEquals(4, numDraws);	//should've gone through while loop four times
	}
	
	@Test
	void testStartThreeNewCards() {
		Bet betGame = new Bet();
		assertTrue(betGame.getFirstDraw());
		betGame.setTotalSpots(10);		//10 total spots
		betGame.setTotalDrawings(4);	//4 total drawings
		betGame.mark(5);				//marked spot 5
		betGame.mark(80);				//marked spot 80
		betGame.mark(1);				//marked spot 1
		betGame.mark(23);				//marked spot 23
		betGame.mark(24);				//marked spot 24
		betGame.mark(25);				//marked spot 25
		betGame.mark(47);				//marked spot 47
		betGame.mark(61);				//marked spot 61
		betGame.mark(42);				//marked spot 42
		betGame.mark(2);				//marked spot 2
		
		int numDraws = 0;	//used to check how many times while loop is executed
		
		while(!betGame.noDrawingsLeft()) {
			int[] twentyRands = betGame.draw();
			numDraws++;
			assertFalse(betGame.getFirstDraw());
			int correctlyMarked = 0;
			for(int spotNum: twentyRands) {
				if(spotNum == 2 || spotNum == 42 || spotNum == 5 || spotNum == 80 || spotNum == 1 || spotNum == 23 || spotNum == 24 || spotNum == 25 || spotNum == 47 || spotNum == 61) {
					correctlyMarked++;
				}
			}
			assertEquals(correctlyMarked, betGame.getNumCorrectlyMarked());
			int[] correctMarkedArr = betGame.getCorrectMarked();
			//check if correctMarked contains correct marks (spots we guessed correctly)
			for(int spotNum: correctMarkedArr) {
				assertEquals(1, betGame.grid[spotNum-1]);
			}
			
			//check that earnings were added Correctly
			if(betGame.getNumCorrectlyMarked() == 0) {assertEquals(5, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 5) {assertEquals(2, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 6) {assertEquals(15, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 7) {assertEquals(40, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 8) {assertEquals(450, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 9){assertEquals(4250, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 10){assertEquals(100000, betGame.amountWon);}
		}
		assertEquals(4, numDraws);	//should've gone through while loop 4 times
		
		//start new card
		betGame.newCard();
		
		//check if newCard reset values
		assertEquals(0, betGame.getNumCorrectlyMarked());
		assertTrue(betGame.getFirstDraw());
		assertEquals(0, betGame.totalWinnings);
		assertEquals(0, betGame.drawsLeft);
		assertEquals(0, betGame.totalDrawings);
		assertEquals(0, betGame.spotsMarked);
		assertEquals(0, betGame.totalSpots);
		assertEquals(80, betGame.gridSize);
		//assert all values in grid are equal to 0
		for(int i = 0; i < betGame.gridSize; i++) {
			assertEquals(0, betGame.grid[i]);
		}
		assertFalse(betGame.random);
		assertEquals(0, betGame.amountWon);
		
		//start the new game
		assertTrue(betGame.getFirstDraw());
		betGame.setTotalSpots(8);		//8 total spots
		betGame.setTotalDrawings(4);	//4 total drawings
		betGame.mark(5);				//marked spot 5
		betGame.mark(80);				//marked spot 80
		betGame.mark(1);				//marked spot 1
		betGame.mark(23);				//marked spot 23
		betGame.mark(24);				//marked spot 24
		betGame.mark(25);				//marked spot 25
		betGame.mark(47);				//marked spot 47
		betGame.mark(61);				//marked spot 61
		
		numDraws = 0;	//used to check how many times while loop is executed
		
		while(!betGame.noDrawingsLeft()) {
			int[] twentyRands = betGame.draw();
			numDraws++;
			assertFalse(betGame.getFirstDraw());
			int correctlyMarked = 0;
			for(int spotNum: twentyRands) {
				if(spotNum == 5 || spotNum == 80 || spotNum == 1 || spotNum == 23 || spotNum == 24 || spotNum == 25 || spotNum == 47 || spotNum == 61) {
					correctlyMarked++;
				}
			}
			assertEquals(correctlyMarked, betGame.getNumCorrectlyMarked());
			int[] correctMarkedArr = betGame.getCorrectMarked();
			//check if correctMarked contains correct marks (spots we guessed correctly)
			for(int spotNum: correctMarkedArr) {
				assertEquals(1, betGame.grid[spotNum-1]);
			}
			
			//check that earnings were added Correctly
			if(betGame.getNumCorrectlyMarked() == 4) {assertEquals(2, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 5) {assertEquals(12, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 6) {assertEquals(50, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 7) {assertEquals(750, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 8) {assertEquals(10000, betGame.amountWon);}
			else {assertEquals(0, betGame.amountWon);}
		}
		assertEquals(4, numDraws);	//should've gone through while loop four times
		
		//start a new card
		betGame.newCard();
		
		//check if newCard reset values
		assertEquals(0, betGame.getNumCorrectlyMarked());
		assertTrue(betGame.getFirstDraw());
		assertEquals(0, betGame.totalWinnings);
		assertEquals(0, betGame.drawsLeft);
		assertEquals(0, betGame.totalDrawings);
		assertEquals(0, betGame.spotsMarked);
		assertEquals(0, betGame.totalSpots);
		assertEquals(80, betGame.gridSize);
		//assert all values in grid are equal to 0
		for(int i = 0; i < betGame.gridSize; i++) {
			assertEquals(0, betGame.grid[i]);
		}
		assertFalse(betGame.random);
		assertEquals(0, betGame.amountWon);
		
		assertTrue(betGame.getFirstDraw());
		betGame.setTotalSpots(8);		//8 total spots
		betGame.setTotalDrawings(1);	//1 total drawings
		betGame.mark(5);				//marked spot 5
		betGame.mark(80);				//marked spot 80
		betGame.mark(1);				//marked spot 1
		betGame.mark(23);				//marked spot 23
		betGame.mark(24);				//marked spot 24
		betGame.mark(25);				//marked spot 25
		betGame.mark(47);				//marked spot 47
		betGame.mark(61);				//marked spot 61
		
		numDraws = 0;	//used to check how many times while loop is executed
		
		while(!betGame.noDrawingsLeft()) {
			int[] twentyRands = betGame.draw();
			numDraws++;
			assertFalse(betGame.getFirstDraw());
			int correctlyMarked = 0;
			for(int spotNum: twentyRands) {
				if(spotNum == 5 || spotNum == 80 || spotNum == 1 || spotNum == 23 || spotNum == 24 || spotNum == 25 || spotNum == 47 || spotNum == 61) {
					correctlyMarked++;
				}
			}
			assertEquals(correctlyMarked, betGame.getNumCorrectlyMarked());
			int[] correctMarkedArr = betGame.getCorrectMarked();
			//check if correctMarked contains correct marks (spots we guessed correctly)
			for(int spotNum: correctMarkedArr) {
				assertEquals(1, betGame.grid[spotNum-1]);
			}
			
			//check that earnings were added Correctly
			if(betGame.getNumCorrectlyMarked() == 4) {assertEquals(2, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 5) {assertEquals(12, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 6) {assertEquals(50, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 7) {assertEquals(750, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 8) {assertEquals(10000, betGame.amountWon);}
			else {assertEquals(0, betGame.amountWon);}
		}
		assertEquals(1, numDraws);	//should've gone through while loop once
	}
	
	@Test
	void testFourNewCards() {
		Bet betGame = new Bet();
		assertTrue(betGame.getFirstDraw());
		betGame.setTotalSpots(10);		//10 total spots
		betGame.setTotalDrawings(4);	//4 total drawings
		betGame.mark(5);				//marked spot 5
		betGame.mark(80);				//marked spot 80
		betGame.mark(1);				//marked spot 1
		betGame.mark(23);				//marked spot 23
		betGame.mark(24);				//marked spot 24
		betGame.mark(25);				//marked spot 25
		betGame.mark(47);				//marked spot 47
		betGame.mark(61);				//marked spot 61
		betGame.mark(42);				//marked spot 42
		betGame.mark(2);				//marked spot 2
		
		int numDraws = 0;	//used to check how many times while loop is executed
		
		while(!betGame.noDrawingsLeft()) {
			int[] twentyRands = betGame.draw();
			numDraws++;
			assertFalse(betGame.getFirstDraw());
			int correctlyMarked = 0;
			for(int spotNum: twentyRands) {
				if(spotNum == 2 || spotNum == 42 || spotNum == 5 || spotNum == 80 || spotNum == 1 || spotNum == 23 || spotNum == 24 || spotNum == 25 || spotNum == 47 || spotNum == 61) {
					correctlyMarked++;
				}
			}
			assertEquals(correctlyMarked, betGame.getNumCorrectlyMarked());
			int[] correctMarkedArr = betGame.getCorrectMarked();
			//check if correctMarked contains correct marks (spots we guessed correctly)
			for(int spotNum: correctMarkedArr) {
				assertEquals(1, betGame.grid[spotNum-1]);
			}
			
			//check that earnings were added Correctly
			if(betGame.getNumCorrectlyMarked() == 0) {assertEquals(5, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 5) {assertEquals(2, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 6) {assertEquals(15, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 7) {assertEquals(40, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 8) {assertEquals(450, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 9){assertEquals(4250, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 10){assertEquals(100000, betGame.amountWon);}
		}
		assertEquals(4, numDraws);	//should've gone through while loop 4 times
		
		//start new card
		betGame.newCard();
		
		//check if newCard reset values
		assertEquals(0, betGame.getNumCorrectlyMarked());
		assertTrue(betGame.getFirstDraw());
		assertEquals(0, betGame.totalWinnings);
		assertEquals(0, betGame.drawsLeft);
		assertEquals(0, betGame.totalDrawings);
		assertEquals(0, betGame.spotsMarked);
		assertEquals(0, betGame.totalSpots);
		assertEquals(80, betGame.gridSize);
		//assert all values in grid are equal to 0
		for(int i = 0; i < betGame.gridSize; i++) {
			assertEquals(0, betGame.grid[i]);
		}
		assertFalse(betGame.random);
		assertEquals(0, betGame.amountWon);
		
		//start the new game
		assertTrue(betGame.getFirstDraw());
		betGame.setTotalSpots(8);		//8 total spots
		betGame.setTotalDrawings(4);	//4 total drawings
		betGame.mark(5);				//marked spot 5
		betGame.mark(80);				//marked spot 80
		betGame.mark(1);				//marked spot 1
		betGame.mark(23);				//marked spot 23
		betGame.mark(24);				//marked spot 24
		betGame.mark(25);				//marked spot 25
		betGame.mark(47);				//marked spot 47
		betGame.mark(61);				//marked spot 61
		
		numDraws = 0;	//used to check how many times while loop is executed
		
		while(!betGame.noDrawingsLeft()) {
			int[] twentyRands = betGame.draw();
			numDraws++;
			assertFalse(betGame.getFirstDraw());
			int correctlyMarked = 0;
			for(int spotNum: twentyRands) {
				if(spotNum == 5 || spotNum == 80 || spotNum == 1 || spotNum == 23 || spotNum == 24 || spotNum == 25 || spotNum == 47 || spotNum == 61) {
					correctlyMarked++;
				}
			}
			assertEquals(correctlyMarked, betGame.getNumCorrectlyMarked());
			int[] correctMarkedArr = betGame.getCorrectMarked();
			//check if correctMarked contains correct marks (spots we guessed correctly)
			for(int spotNum: correctMarkedArr) {
				assertEquals(1, betGame.grid[spotNum-1]);
			}
			
			//check that earnings were added Correctly
			if(betGame.getNumCorrectlyMarked() == 4) {assertEquals(2, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 5) {assertEquals(12, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 6) {assertEquals(50, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 7) {assertEquals(750, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 8) {assertEquals(10000, betGame.amountWon);}
			else {assertEquals(0, betGame.amountWon);}
		}
		assertEquals(4, numDraws);	//should've gone through while loop four times
		
		//start a new card
		betGame.newCard();
		
		//check if newCard reset values
		assertEquals(0, betGame.getNumCorrectlyMarked());
		assertTrue(betGame.getFirstDraw());
		assertEquals(0, betGame.totalWinnings);
		assertEquals(0, betGame.drawsLeft);
		assertEquals(0, betGame.totalDrawings);
		assertEquals(0, betGame.spotsMarked);
		assertEquals(0, betGame.totalSpots);
		assertEquals(80, betGame.gridSize);
		//assert all values in grid are equal to 0
		for(int i = 0; i < betGame.gridSize; i++) {
			assertEquals(0, betGame.grid[i]);
		}
		assertFalse(betGame.random);
		assertEquals(0, betGame.amountWon);
		
		assertTrue(betGame.getFirstDraw());
		betGame.setTotalSpots(8);		//8 total spots
		betGame.setTotalDrawings(3);	//3 total drawings
		betGame.mark(5);				//marked spot 5
		betGame.mark(80);				//marked spot 80
		betGame.mark(1);				//marked spot 1
		betGame.mark(23);				//marked spot 23
		betGame.mark(24);				//marked spot 24
		betGame.mark(25);				//marked spot 25
		betGame.mark(47);				//marked spot 47
		betGame.mark(61);				//marked spot 61
		
		numDraws = 0;	//used to check how many times while loop is executed
		
		while(!betGame.noDrawingsLeft()) {
			int[] twentyRands = betGame.draw();
			numDraws++;
			assertFalse(betGame.getFirstDraw());
			int correctlyMarked = 0;
			for(int spotNum: twentyRands) {
				if(spotNum == 5 || spotNum == 80 || spotNum == 1 || spotNum == 23 || spotNum == 24 || spotNum == 25 || spotNum == 47 || spotNum == 61) {
					correctlyMarked++;
				}
			}
			assertEquals(correctlyMarked, betGame.getNumCorrectlyMarked());
			int[] correctMarkedArr = betGame.getCorrectMarked();
			//check if correctMarked contains correct marks (spots we guessed correctly)
			for(int spotNum: correctMarkedArr) {
				assertEquals(1, betGame.grid[spotNum-1]);
			}
			
			//check that earnings were added Correctly
			if(betGame.getNumCorrectlyMarked() == 4) {assertEquals(2, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 5) {assertEquals(12, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 6) {assertEquals(50, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 7) {assertEquals(750, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 8) {assertEquals(10000, betGame.amountWon);}
			else {assertEquals(0, betGame.amountWon);}
		}
		assertEquals(3, numDraws);	//should've gone through while loop three times
		
		//start a new card
		betGame.newCard();
		
		//check if all values are properly reset
		assertEquals(0, betGame.getNumCorrectlyMarked());
		assertTrue(betGame.getFirstDraw());
		assertEquals(0, betGame.totalWinnings);
		assertEquals(0, betGame.drawsLeft);
		assertEquals(0, betGame.totalDrawings);
		assertEquals(0, betGame.spotsMarked);
		assertEquals(0, betGame.totalSpots);
		assertEquals(80, betGame.gridSize);
		//assert all values in grid are equal to 0
		for(int i = 0; i < betGame.gridSize; i++) {
			assertEquals(0, betGame.grid[i]);
		}
		assertFalse(betGame.random);
		assertEquals(0, betGame.amountWon);
		
		assertTrue(betGame.getFirstDraw());
		betGame.setTotalSpots(4);		//4 total spots
		betGame.setTotalDrawings(4);	//4 total drawings
		betGame.mark(5);				//marked spot 5
		betGame.mark(80);				//marked spot 80
		betGame.mark(1);				//marked spot 1
		betGame.mark(23);				//marked spot 23
		
		numDraws = 0;	//used to check how many times while loop is executed
		
		while(!betGame.noDrawingsLeft()) {
			int[] twentyRands = betGame.draw();
			numDraws++;
			assertFalse(betGame.getFirstDraw());
			int correctlyMarked = 0;
			for(int spotNum: twentyRands) {
				if(spotNum == 5 || spotNum == 80 || spotNum == 1 || spotNum == 23) {
					correctlyMarked++;
				}
			}
			assertEquals(correctlyMarked, betGame.getNumCorrectlyMarked());
			int[] correctMarkedArr = betGame.getCorrectMarked();
			//check if correctMarked contains correct marks (spots we guessed correctly)
			for(int spotNum: correctMarkedArr) {
				assertEquals(1, betGame.grid[spotNum-1]);
			}
			
			//check that earnings were added Correctly
			if(betGame.getNumCorrectlyMarked() == 2) {assertEquals(1, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 3) {assertEquals(5, betGame.amountWon);}
			else if(betGame.getNumCorrectlyMarked() == 4) {assertEquals(75, betGame.amountWon);}
			else {assertEquals(0, betGame.amountWon);}
		}
		assertEquals(4, numDraws);	//should've gone through while loop four times
	}

}
