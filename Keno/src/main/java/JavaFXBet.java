import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class JavaFXBet extends Application{
	
	private EventHandler<ActionEvent> updateSpotNum;
	private EventHandler<ActionEvent> updateDrawingsNum;
	private EventHandler<ActionEvent> toStartScene;
	private EventHandler<ActionEvent> toRulesScene;
	private EventHandler<ActionEvent> toOddsWinScene;
	private EventHandler<ActionEvent> toBetCardScene;
	private EventHandler<ActionEvent> toResultsScene;
	private EventHandler<ActionEvent> exitGame;
	private EventHandler<ActionEvent> draw;
	private EventHandler<ActionEvent> newLookHandler;
	private int cycleCount = 0;
	private boolean oldLook = true;
	private int drawNum = 1;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		exitGame = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Platform.exit();
			}
		};
		
		Bet betGame = new Bet();	//object that uses backend to control game\
		
		Scene startScene;
		Scene betCardScene;
		Scene ruleScene;
		Scene oddsWinScene;
		Scene resultsScene;
		
		//create menuBar for the Start scene
		MenuItem rules = new MenuItem("display the rules of the game");
		MenuItem oddsWinningStart = new MenuItem("display the odds of winning");
		MenuItem exitStart = new MenuItem("exit the game");
		//exit game when 'exit' in start menuItem is clicked
		exitStart.setOnAction(exitGame);
		Menu menu1 = new Menu("Menu");
		menu1.getItems().add(rules);
		
        menu1.getItems().add(oddsWinningStart);
        menu1.getItems().add(exitStart);
		MenuBar menuB = new MenuBar(menu1);
		
		
		/*Rules Scene************/
		Text rulesTitle = new Text("Rules");
		rulesTitle.setFont(Font.font("Comic Sans Ms", FontWeight.BOLD, FontPosture.REGULAR,30));
		Text rulesTxt = new Text("1. Decide how many consecutive draws to play. Pick up to 4. \n2. Select how many numbers to match: 1, 4, 8, or 10. These 'numbers' are called Spots. The number of Spots you choose and the amount you play per draw will determine the amount you could win.\n3. Pick as many numbers as you did Spots. You can select numbers from 1 to 80 or choose Quick Pick and let the computer terminal randomly pick some or all of these numbers for you.\n\n");
		Text rulesTxt2 = new Text("See the Prize Chart, and the odds, in the odds of winning menu.");
		rulesTxt.setFont(new Font(20));
		rulesTxt2.setFont(new Font(20));
		rulesTxt2.setFill(Color.DARKRED);
		rulesTxt.setWrappingWidth(500);
		VBox rulesTxtVB = new VBox(rulesTitle, rulesTxt, rulesTxt2);
		rulesTxtVB.setSpacing(15);
		rulesTxtVB.setAlignment(Pos.CENTER);
		BorderPane ruleBp = new BorderPane(rulesTxtVB);
		
		//create menubar for rules scene
		MenuItem startRules = new MenuItem("return to the start");
		MenuItem oddsWinningRules = new MenuItem("display the odds of winning");
		MenuItem exitRules = new MenuItem("exit the game");
		exitRules.setOnAction(exitGame);
		
		Menu menuRules = new Menu("Menu");
		MenuBar menuRulesBar =  new MenuBar(menuRules);
		menuRules.getItems().add(startRules);
		menuRules.getItems().add(oddsWinningRules);
		menuRules.getItems().add(exitRules);
		ruleBp.setTop(menuRulesBar);
		
		ruleScene = new Scene(ruleBp, 700, 700);
		ruleBp.setStyle("-fx-background-color: #ffe4c4;");
		
		toRulesScene = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				primaryStage.setScene(ruleScene);
				primaryStage.show();
			}
		};
		rules.setOnAction(toRulesScene);
		////Rules Scene END*********//////
	
		
		/*OddsWinning Scene******/
		Text oddsTitle = new Text("Odds of Winning");
		oddsTitle.setFont(Font.font("Comic Sans Ms", FontWeight.BOLD, FontPosture.REGULAR,30));
		Text subOddsTitle = new Text("# Spot Game 		     	 Prize 			 	Odds"); 
		subOddsTitle.setFont(new Font(22));
		subOddsTitle.setFill(Color.DARKOLIVEGREEN);
		Text oddsOneSpotTxt = new Text("1 Spot Game:	  	  			 			     	1 in 4\nMatch: 1					$2");
		oddsOneSpotTxt.setFont(new Font(20));
		HBox oddsOneSpotTxtHB = new HBox(oddsOneSpotTxt);
		oddsOneSpotTxtHB.setAlignment(Pos.CENTER);
		oddsOneSpotTxtHB.setPadding(new Insets(0, 25, 0, 0));
		Text oddsFourSpotTxt = new Text("4 Spot Game:								     	1 in 3.86\nMatch 3:					$27\nMatch 2:					$2");
		oddsFourSpotTxt.setFont(new Font(20));
		Text oddsEightSpotTxt = new Text("8 Spot Game:								     	1 in 9.77\nMatch 8:					$10,000\nMatch 7:					$750\nMatch 6:					$50\nMatch 5:					$12\nMatch 4:					$2");
		oddsEightSpotTxt.setFont(new Font(20));
		Text oddsTenSpotTxt = new Text("10 Spot Game:								     	1 in 9.05\nMatch 10:					$100,000\nMatch 9:					$4,250\nMatch 8:					$450\nMatch 7:					$40\nMatch 6:					$15\nMatch 5:					$2\nMatch 0:					$5");
		oddsTenSpotTxt.setFont(new Font(20));
		VBox oddsWinVb = new VBox(oddsTitle, subOddsTitle, oddsOneSpotTxtHB, oddsFourSpotTxt, oddsEightSpotTxt, oddsTenSpotTxt);
		oddsWinVb.setSpacing(15);
		oddsWinVb.setAlignment(Pos.CENTER);
		BorderPane oddsWinBp = new BorderPane(oddsWinVb);
		
		
		//oddsWin menu Bar
		MenuItem startOdds = new MenuItem("return to the start");
		MenuItem rulesOdds = new MenuItem("display the rules of the game");
		rulesOdds.setOnAction(toRulesScene);
		MenuItem exitOdds = new MenuItem("exit the game");
		exitOdds.setOnAction(exitGame);
		
		Menu menuOdds = new Menu("Menu");
		MenuBar menuOddsBar =  new MenuBar(menuOdds);
		menuOdds.getItems().add(startOdds);
		menuOdds.getItems().add(rulesOdds);
		menuOdds.getItems().add(exitOdds);
		oddsWinBp.setTop(menuOddsBar);
	
		
		oddsWinBp.setStyle("-fx-background-color: #ffe4c4;");
		oddsWinScene = new Scene(oddsWinBp, 700, 700);
		
		toOddsWinScene = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				primaryStage.setScene(oddsWinScene);
				primaryStage.show();
			}
		};
		oddsWinningStart.setOnAction(toOddsWinScene);
		oddsWinningRules.setOnAction(toOddsWinScene);
		
		//Odds Winning Scene END*******
		
		//starting borderPane
		Text startTitle = new Text("KENO");
		startTitle.setFill(Color.KHAKI);
		startTitle.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 70));
		BorderPane bpStart = new BorderPane();
		
		
		//Play Button
		Button playB = new Button("PLAY");
		playB.setStyle("-fx-background-color: #ffdead;");
		playB.setShape(new Circle(40));
		playB.setMinSize(80, 80);
		playB.setMaxSize(80, 80);
		VBox startVb = new VBox(startTitle, playB);
		startVb.setAlignment(Pos.CENTER);
		startVb.setSpacing(200);
		bpStart.setCenter(startVb);
		
		
		//set menuBar to top of the screen
		VBox vb = new VBox(menuB);
		bpStart.setTop(vb);
		
		//startScene
		startScene = new Scene(bpStart, 700, 700);
		bpStart.setStyle("-fx-background-color: #e9967a;");
		toStartScene = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				primaryStage.setScene(startScene);
				primaryStage.show();
			}
		};
		startRules.setOnAction(toStartScene);
		startOdds.setOnAction(toStartScene);
		
		
		/*set up the betCardScene*/
		BorderPane bpCard = new BorderPane();
		
		Text errorMsg = new Text();	//Used te tell the user if they are trying to mark too many spots, etc
		errorMsg.setFont(Font.font("Verdana", 15));
		errorMsg.setFill(Color.DARKCYAN);
		
		//create grid of nodes
		GridPane gridpane = new GridPane();
		int num = 1;
		Button[] buttonArr = new Button[80];
		int ind = 0;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 10; j++) {
				Button node = new Button(String.valueOf(num));
				buttonArr[ind] = node;
				ind++;
				node.setStyle("-fx-background-color: #ffe4c4;");
				/*******************Grid NODE HANDLER******************/
				node.setOnAction(e->{
					Button b = (Button) e.getTarget();
					int buttonNum = Integer.valueOf(b.getText());
					int handler = betGame.mark(buttonNum);
					if(handler == -1 || handler == -2) {	//max number of nodes marked, or out of bounds (buttons marked 1-80, so shouldn't occur)
						errorMsg.setText("You've already marked the maximum amount of spots!");
					}
					//user hasn't chosen total Spots
					else if(handler == -3) {errorMsg.setText("You must choose a number of spots to play to mark before marking");}
					else if(handler == 0) {	//unmark the node
						errorMsg.setText("");
						b.setStyle("-fx-background-color: #ffe4c4;");
					}
					else {	//mark the node
						if(oldLook) {b.setStyle("-fx-background-color: #add8e6;");}
						else {b.setStyle("-fx-background-color: #cd5c5c;");}
						errorMsg.setText("");
						if(betGame.random) {errorMsg.setText("Random Button Reminder: we will choose all of your spots for you.\nThis may or may not include the ones you've already chosen");}
					}
					/**********************************************/
				});
				
				gridpane.add(node, j, i); // column=j row=i
				GridPane.setMargin(node, new Insets(5,5,5,5));
				num++;
			}
		}
		//done setting up grid
		
		//set up buttons representing number of spots user chooses to play
		Button oneSpotB = new Button("1");
		Button fourSpotB = new Button("4");
		Button eightSpotB = new Button("8");
		Button tenSpotB = new Button("10");
		oneSpotB.setStyle("-fx-background-color: #fafad2;");
		fourSpotB.setStyle("-fx-background-color: #fafad2;");
		eightSpotB.setStyle("-fx-background-color: #fafad2;");
		tenSpotB.setStyle("-fx-background-color: #fafad2;");
		Text spotTxtCard = new Text("Number of Spots: ");
		spotTxtCard.setFont(new Font(18));
		HBox spotNodesHB = new HBox(spotTxtCard, oneSpotB, fourSpotB, eightSpotB, tenSpotB);
		//when one of the buttons, representing the number of spots to play, gets clicked update backend
		updateSpotNum = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Button b = (Button) e.getTarget();
				int num = Integer.valueOf(b.getText());
				if(num < betGame.spotsMarked) {
					//do nothing, more spots marked than allowed, unmark
				}
				else {
					oneSpotB.setStyle("-fx-background-color: #fafad2;");
					fourSpotB.setStyle("-fx-background-color: #fafad2;");
					eightSpotB.setStyle("-fx-background-color: #fafad2;");
					tenSpotB.setStyle("-fx-background-color: #fafad2;");
					b.setStyle("-fx-background-color: #8fbc8f;");
					betGame.setTotalSpots(num);
				}
			}
		};
		oneSpotB.setOnAction(updateSpotNum);
		fourSpotB.setOnAction(updateSpotNum);
		eightSpotB.setOnAction(updateSpotNum);
		tenSpotB.setOnAction(updateSpotNum);
		
		//set up buttons representing number of drawings user chooses to play
		Button oneDrawingB = new Button("1");
		Button twoDrawingB = new Button("2");
		Button threeDrawingB = new Button("3");
		Button fourDrawingB = new Button("4");
		oneDrawingB.setStyle("-fx-background-color: #fafad2;");
		twoDrawingB.setStyle("-fx-background-color: #fafad2;");
		threeDrawingB.setStyle("-fx-background-color: #fafad2;");
		fourDrawingB.setStyle("-fx-background-color: #fafad2;");
		Text drawingsGameTxt = new Text("Number of Drawings");
		drawingsGameTxt.setFont(new Font(18));
		HBox drawingNodesHB = new HBox(drawingsGameTxt, oneDrawingB, twoDrawingB, threeDrawingB, fourDrawingB);
		drawingNodesHB.setAlignment(Pos.CENTER);
		//update number of drawings event handler
		updateDrawingsNum = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Button b = (Button) e.getTarget();
				int num = Integer.valueOf(b.getText());
				oneDrawingB.setStyle("-fx-background-color: #fafad2;");
				twoDrawingB.setStyle("-fx-background-color: #fafad2;");
				threeDrawingB.setStyle("-fx-background-color: #fafad2;");
				fourDrawingB.setStyle("-fx-background-color: #fafad2;");
				b.setStyle("-fx-background-color: #8fbc8f;");
				betGame.setTotalDrawings(num);
			}
		};
		oneDrawingB.setOnAction(updateDrawingsNum);
		twoDrawingB.setOnAction(updateDrawingsNum);
		threeDrawingB.setOnAction(updateDrawingsNum);
		fourDrawingB.setOnAction(updateDrawingsNum);
		
		Button randomSpotsB = new Button("Random");
		randomSpotsB.setStyle("-fx-background-color: #fafad2;");
		randomSpotsB.setPrefSize(100, 30);
		
		Button resetB = new Button("Clear Marked Spots!");
		resetB.setStyle("-fx-background-color: #e6e6fa;");
		resetB.setPrefSize(150, 30);
		
		HBox randomAndResetHb = new HBox(randomSpotsB, resetB);
		randomAndResetHb.setAlignment(Pos.CENTER);
		HBox.setMargin(randomSpotsB, new Insets(0, 10, 0, 0));
		HBox.setMargin(resetB, new Insets(0, 0, 0, 10));
		
		randomSpotsB.setOnAction(e->{
			errorMsg.setText("");
			if(betGame.random) {
				betGame.setRandom(false);
				if(oldLook) {randomSpotsB.setStyle("-fx-background-color: #fafad2;");}	//oldLook default randomB color
				else {randomSpotsB.setStyle("-fx-background-color: #ffe4b5;");}	//newLook default randomB color
			}
			else {
				betGame.setRandom(true);
				if(oldLook) {randomSpotsB.setStyle("-fx-background-color: #1e90ff;");}	//oldLook pressed randomB color
				else {randomSpotsB.setStyle("-fx-background-color: #66cdaa;");}	//newLook pressed randomB color
				if(betGame.spotsMarked > 0) {errorMsg.setText("Random Button Reminder: we will choose all of your spots for you.\nThis may or may not include the ones you've already chosen");}
			}
				
		});
		
		//reset Button event handler
		resetB.setOnAction(e->{
			errorMsg.setText("");
			for(int i = 0; i < 80; i++) {
				buttonArr[i].setStyle("-fx-background-color: #ffe4c4;");	//unmark all nodes
			}
			betGame.clearGrid();
			
		});
		
		
		//menu while in game (CARD)
		Menu menuOnCard = new Menu("Menu");
		MenuBar menuOnCardBar =  new MenuBar(menuOnCard);
		MenuItem oddsWinningPlay = new MenuItem("display the odds of winning");
		oddsWinningPlay.setOnAction(toOddsWinScene);
		MenuItem rulesCard = new MenuItem("display the rules of the game");
		MenuItem newLook = new MenuItem("New Look");
		
		//create menubar for rules Game scene*/
		Menu rulesGameMenu = new Menu("Menu");
		MenuBar rulesGameMenuBar = new MenuBar(rulesGameMenu);
		MenuItem rulesReturnToGame = new MenuItem("return to game");
		rulesGameMenu.getItems().add(rulesReturnToGame);
		
		
		rulesCard.setOnAction(e->{
			primaryStage.setScene(ruleScene);	//same as original rules scene, difference is it's menuBar
			primaryStage.show();
		});
		
	
		//create menuBar for odds Game Scene
		Menu oddsGameMenu = new Menu("Menu");
		MenuBar oddsGameMenuBar = new MenuBar(oddsGameMenu);
		MenuItem oddsReturnToGame = new MenuItem("return to game");
		oddsGameMenu.getItems().add(oddsReturnToGame);
		
		
		MenuItem exitPlay = new MenuItem("exit the game");
		exitPlay.setOnAction(exitGame);
		menuOnCard.getItems().add(rulesCard);
		menuOnCard.getItems().add(oddsWinningPlay);
		menuOnCard.getItems().add(exitPlay);
		menuOnCard.getItems().add(newLook);
		
		VBox betCardVB = new VBox(spotNodesHB, drawingNodesHB, randomAndResetHb, gridpane, errorMsg);
		spotNodesHB.setSpacing(10);
		drawingNodesHB.setSpacing(10);
		betCardVB.setSpacing(30);
		spotNodesHB.setAlignment(Pos.CENTER);
	
		bpCard.setTop(menuOnCardBar);
		bpCard.setCenter(betCardVB);
		betCardVB.setAlignment(Pos.CENTER);
		
		Button startDrawB = new Button("Draw");
		startDrawB.setPrefSize(70, 70);
		
		HBox drawHB = new HBox(startDrawB);
		bpCard.setBottom(drawHB);
		drawHB.setAlignment(Pos.BASELINE_RIGHT);
		startDrawB.setStyle("-fx-background-color: #00ced1;");
		gridpane.setAlignment(Pos.CENTER);
		
		
		
		//text and Buttons that go into resultsVB
		HBox randNumsHB = new HBox();
		HBox userMarkedHB = new HBox();
		HBox userMarkedCorrectlyHB = new HBox();
		randNumsHB.setAlignment(Pos.CENTER);
		userMarkedHB.setAlignment(Pos.CENTER);
		userMarkedCorrectlyHB.setAlignment(Pos.CENTER);
		Text twentyRandTxt = new Text("The Numbers to Match");
		twentyRandTxt.setFont(new Font(18));
		Text numbersMarkedTxt = new Text("Numbers you Marked");
		numbersMarkedTxt.setFont(new Font(18));
		Text numbersCorrectTxt = new Text();
		numbersCorrectTxt.setFont(new Font(18));
		Text howManyCorrectTxt = new Text();
		howManyCorrectTxt.setFont(new Font(18));
		
		Button nextDrawB = new Button("Next Draw");
		nextDrawB.setPrefSize(100, 70);
		nextDrawB.setStyle("-fx-background-color: #00ced1;");
		
		Button newBetCardB = new Button("Start a New Bet Card");
		newBetCardB.setStyle("-fx-background-color: #66cdaa;");
		newBetCardB.setPrefSize(150, 70);
		Text resultsTitle = new Text();
		resultsTitle.setFont(Font.font("Verdana", 30));
		
		VBox resultsVB = new VBox(resultsTitle, twentyRandTxt, randNumsHB, numbersMarkedTxt,userMarkedHB, numbersCorrectTxt, userMarkedCorrectlyHB, howManyCorrectTxt);
		resultsVB.setSpacing(20);
		resultsVB.setAlignment(Pos.CENTER);
		
		Text totalWinTxt = new Text();
		totalWinTxt.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
		Text drawWinTxt = new Text();
		drawWinTxt.setFont(Font.font("Verdana", 20));
		VBox winningsVb = new VBox(totalWinTxt, drawWinTxt);
		winningsVb.setPadding(new Insets(30, 0, 0, 10));
		
		BorderPane resultsBp = new BorderPane(resultsVB);
		resultsBp.getChildren().add(winningsVb);
		BorderPane.setAlignment(resultsVB, Pos.CENTER);
		BorderPane.setAlignment(winningsVb, Pos.CENTER_LEFT);
		resultsBp.setStyle("-fx-background-color: #e9967a;");
		resultsScene = new Scene(resultsBp, 700, 700);
		
		toResultsScene = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				primaryStage.setScene(resultsScene);
				primaryStage.show();
			}
		};
		
		//results menu
		Menu resultsMenu = new Menu("Menu");
		MenuItem ruleResults = new MenuItem("display the rules of the game");
		ruleResults.setOnAction(toRulesScene);
		MenuItem oddsWinResults = new MenuItem("display the odds of winning");
		oddsWinResults.setOnAction(toOddsWinScene);
		MenuItem exitResults = new MenuItem("exit the game");
		exitResults.setOnAction(exitGame);
		MenuItem newLookResults = new MenuItem("New Look");
		MenuBar resultsMenuBar = new MenuBar(resultsMenu); 
		resultsMenu.getItems().add(ruleResults);
		resultsMenu.getItems().add(oddsWinResults);
		resultsMenu.getItems().add(exitResults);
		resultsMenu.getItems().add(newLookResults);
		
		resultsBp.setTop(resultsMenuBar);
		
		Text noneCorrectTxt = new Text("You Didn't Match a Single One!");
		noneCorrectTxt.setFont(new Font(18));
		noneCorrectTxt.setFill(Color.MEDIUMSLATEBLUE);
		
		//rules Menu when called from results Scene
		Menu rulesResultsMenu = new Menu("Menu");
		MenuItem rulesResultsBack = new MenuItem("return to results");
		rulesResultsBack.setOnAction(toResultsScene);
		rulesResultsMenu.getItems().add(rulesResultsBack);
		MenuBar rulesResultsMenuBar = new MenuBar(rulesResultsMenu);
		
		//oddsWin menu when called from results Scene
		Menu oddsWinResultsMenu = new Menu("Menu");
		MenuBar oddsWinResultsMenuBar = new MenuBar(oddsWinResultsMenu);
		MenuItem oddsWinResultsBack = new MenuItem("return to results");
		oddsWinResultsBack.setOnAction(toResultsScene);
		oddsWinResultsMenu.getItems().add(oddsWinResultsBack);
		Button exitRB = new Button("Exit the Game");
		exitRB.setOnAction(exitGame);
		exitRB.setPrefSize(100, 50);
		exitRB.setStyle("-fx-background-color: #f0e68c;");
		HBox exitRBHb = new HBox(exitRB);
		exitRBHb.setAlignment(Pos.BASELINE_RIGHT);
		
		//user clicks DRAW
		draw = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				//check if valid
				if((betGame.random == false && betGame.spotsMarked != betGame.totalSpots) || betGame.totalDrawings == 0) {
					//do nothing
					errorMsg.setText("You still have remaining things to fill out before you can draw!");
				}
				else {
					errorMsg.setText("");
					numbersCorrectTxt.setText("");
					//resultsTitle telling user current draw out of totalDraws
					resultsTitle.setText("Draw 1" + " out of " + betGame.totalDrawings);
					howManyCorrectTxt.setText("");
					totalWinTxt.setText("Total Winnings: $0.00");
					drawWinTxt.setText("Amount Won in this Draw: (Drawing)");
					//change the menuBars of rules and oddsWin scenes
					ruleBp.setTop(rulesResultsMenuBar);
					oddsWinBp.setTop(oddsWinResultsMenuBar);
					
					//update back-end and get twenty random numbers
					int[] twentyRands = betGame.draw();
					int[] userMarked = betGame.getMarked();
					int[] userCorrectlyMarked = betGame.getCorrectMarked();
					
					//switch scene
					primaryStage.setScene(resultsScene);
					primaryStage.show();
					
					Button[] twentyRandB = new Button[20];
					int index = 0;
					
					for(int r: twentyRands) {
						Button n = new Button(String.valueOf(r));
						if(betGame.grid[r-1] == 1) {n.setStyle("-fx-background-color: #d8bfd8;");}	//numbers that match userMarked spots, are marked a different color
						twentyRandB[index++] = n;
					}
					
					cycleCount = 0;
					//implement delay of 1 second between showing each random number
					Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
						Button n = twentyRandB[cycleCount++];
						FadeTransition fade = new FadeTransition(Duration.seconds(1), n);
						fade.setToValue(1);
						fade.setFromValue(0);
						randNumsHB.getChildren().add(n);
						fade.play();
					}));
					timeline.setCycleCount(20);
					timeline.playFromStart();
					cycleCount = 0;

					for(int i: userMarked) {
						Button c = new Button(String.valueOf(i));
						c.setStyle("-fx-background-color: #d8bfd8;");
						FadeTransition fade = new FadeTransition(Duration.seconds(1), c);
						fade.setToValue(1);
						fade.setFromValue(0);
						fade.play();
						userMarkedHB.getChildren().add(c);
						HBox.setMargin(c, new Insets(0, 5, 0,0));
					}
					timeline.setOnFinished(event2->{	
						if(betGame.getNumCorrectlyMarked() == 0) {	//if none marked correctly
							resultsVB.getChildren().remove(5);	//userMarked HB removed
							resultsVB.getChildren().add(noneCorrectTxt);
							nextDrawB.setDisable(false);//allow nextDraw if no correct spots
							//text telling user how many they marked correctly
							if(betGame.getNumCorrectlyMarked() == 0) {howManyCorrectTxt.setText("0 Marked Correctly.");}
							else {howManyCorrectTxt.setText(betGame.getNumCorrectlyMarked() + " Marked Correctly");}
							//text telling user how much they've won in total and in this draw
							double totalWin = betGame.getTotalWinnings();
							totalWinTxt.setText("Total Winnings: $" + totalWin);
							double drawWin = betGame.amountWon;
							if(drawWin == 0) {drawWinTxt.setText("No Money Earned in this Draw");}
							else	{drawWinTxt.setText("Amount Won in this Draw: $" + drawWin);}
						}
						else{	//if more than 0 marked correctly
							
							//once done showing the random numbers, show all numbers marked correctly and enable nextDrawB
							numbersCorrectTxt.setText("Numbers you Marked Correctly");
								for(int j: userCorrectlyMarked) {
									Button l = new Button(String.valueOf(j));
									l.setStyle("-fx-background-color: #d8bfd8;");
									FadeTransition fade = new FadeTransition(Duration.seconds(1), l);
									fade.setToValue(1);
									fade.setFromValue(0);
									fade.play();
									userMarkedCorrectlyHB.getChildren().add(l);
									HBox.setMargin(l, new Insets(0, 5, 0, 0));
								}
								nextDrawB.setDisable(false);
								double drawWin = betGame.amountWon;
								//text telling user how many they marked correctly
								if(betGame.getNumCorrectlyMarked() == 0) {howManyCorrectTxt.setText("0 Marked Correctly.");}
								else {howManyCorrectTxt.setText(betGame.getNumCorrectlyMarked() + " Marked Correctly");}
								//text telling user how much they've won in total and in this draw
								double totalWin = betGame.getTotalWinnings();
								totalWinTxt.setText("Total Winnings: $" + totalWin);
								
								if(drawWin == 0) {drawWinTxt.setText("No Money Earned in this Draw");}
								else	{drawWinTxt.setText("Amount Won in this Draw: $" + drawWin);}
						}	
				
						if(betGame.drawsLeft == 0) {
							Text gameOver = new Text("GAME OVER");
							Text startOver = new Text("Click 'Start a New Bet Card' to start a New Card!");
							startOver.setFont(new Font(18));
							gameOver.setFont(Font.font("Verdana", FontPosture.ITALIC, 18));
							resultsVB.getChildren().add(gameOver);
							resultsVB.getChildren().add(startOver);
							resultsVB.getChildren().add(newBetCardB);
							resultsBp.setBottom(exitRBHb);
						}
						else {
							resultsVB.getChildren().add(nextDrawB);
						}
				});
					
				}
			}
		};
		startDrawB.setOnAction(draw);
		
		nextDrawB.setOnAction(e->{
			drawNum++;
			resultsTitle.setText("Draw " + drawNum + " out of " + betGame.totalDrawings);
			totalWinTxt.setText("Total Winnings: $" + betGame.totalWinnings);
			drawWinTxt.setText("Amount Won in this Draw: (Drawing)");
			numbersCorrectTxt.setText("");
			howManyCorrectTxt.setText("");
			noneCorrectTxt.setText("");
			randNumsHB.getChildren().clear();
			userMarkedCorrectlyHB.getChildren().clear();
			int[] twentyRands = betGame.draw();
			int[] userCorrectlyMarked = betGame.getCorrectMarked();
			double totalWin = betGame.getTotalWinnings();
			double drawWin = betGame.amountWon;
			
			//if no drawings after this one, hide the nextDrawButton
			if(betGame.noDrawingsLeft())	{nextDrawB.setVisible(false);}
			
			Button[] twentyRandB = new Button[20];
			int index = 0;
			nextDrawB.setDisable(true);
			
			for(int r: twentyRands) {
				Button n = new Button(String.valueOf(r));
				if(betGame.grid[r-1] == 1) {n.setStyle("-fx-background-color: #d8bfd8;");}	//numbers that match userMarked spots, are marked a different color
				twentyRandB[index++] = n;
			}
			cycleCount = 0;
			//implement delay of 1 second while showing each button
			Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
				Button n = twentyRandB[cycleCount++];
				FadeTransition fade = new FadeTransition(Duration.seconds(1), n);
				fade.setToValue(1);
				fade.setFromValue(0);
				randNumsHB.getChildren().add(n);
				fade.play();
			}));
			timeline.setCycleCount(20);
			timeline.playFromStart();
			
			timeline.setOnFinished(event2->{
				//update earnings text
				drawWinTxt.setText("Amount Won in this Draw: $" + drawWin);
				totalWinTxt.setText("Total Winnings: $" + totalWin);
				//text telling user how many they marked correctly
				if(betGame.getNumCorrectlyMarked() == 0) {howManyCorrectTxt.setText("0 Marked Correctly.");}
				else {howManyCorrectTxt.setText(betGame.getNumCorrectlyMarked() + " Marked Correctly");}
				if(betGame.getNumCorrectlyMarked() == 0) {	//if none marked correctly
					resultsVB.getChildren().clear();
					resultsVB.getChildren().add(resultsTitle);
					resultsVB.getChildren().add(twentyRandTxt);
					resultsVB.getChildren().add(randNumsHB);
					resultsVB.getChildren().add(numbersMarkedTxt);
					resultsVB.getChildren().add(userMarkedHB);
					resultsVB.getChildren().add(howManyCorrectTxt);
					resultsVB.getChildren().add(noneCorrectTxt);
					noneCorrectTxt.setText("You Didn't Match a Single One!");
					nextDrawB.setDisable(false);//allow nextDraw if no correct spots
				}
				else {	//marked at least 1 correctly
					numbersCorrectTxt.setText("Numbers you Marked Correctly");
					resultsVB.getChildren().clear();
					resultsVB.getChildren().add(resultsTitle);
					resultsVB.getChildren().add(twentyRandTxt);
					resultsVB.getChildren().add(randNumsHB);
					resultsVB.getChildren().add(numbersMarkedTxt);
					resultsVB.getChildren().add(userMarkedHB);
					resultsVB.getChildren().add(numbersCorrectTxt);
					resultsVB.getChildren().add(userMarkedCorrectlyHB);
					resultsVB.getChildren().add(howManyCorrectTxt);
					//once done showing the random numbers, show all numbers marked correctly and enable nextDrawB
					
						for(int j: userCorrectlyMarked) {
							Button l = new Button(String.valueOf(j));
							l.setStyle("-fx-background-color: #d8bfd8;");
							FadeTransition fade = new FadeTransition(Duration.seconds(1), l);
							fade.setToValue(1);
							fade.setFromValue(0);
							fade.play();
							userMarkedCorrectlyHB.getChildren().add(l);
							HBox.setMargin(l, new Insets(0, 5, 0, 0));
						}
						nextDrawB.setDisable(false);
				}
				if(betGame.drawsLeft == 0) {
					Text gameOver = new Text("GAME OVER");
					Text startOver = new Text("Click 'Start a New Bet Card' to start again!");
					startOver.setFont(new Font(18));
					gameOver.setFont(Font.font("Verdana", FontPosture.ITALIC, 18));
					resultsVB.getChildren().add(gameOver);
					resultsVB.getChildren().add(startOver);
					resultsVB.getChildren().add(newBetCardB);
					resultsBp.setBottom(exitRBHb);
				}
				else {
					resultsVB.getChildren().add(nextDrawB);
				}
			});
			
			
		});
		
		bpCard.setStyle("-fx-background-color: #e9967a;");
		BorderPane.setMargin(gridpane, new Insets(0, 0, 80, 0));
		
		betCardScene =  new Scene(bpCard, 700, 700);
		
		toBetCardScene = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				primaryStage.setScene(betCardScene);
				primaryStage.show();
			}
		};
		
		//User Presses New Card Game
		newBetCardB.setOnAction(e->{
			betGame.newCard();
			drawNum = 1;
			nextDrawB.setVisible(true);
			resultsBp.setBottom(null);//remove exit button from bottom
			//remove markings from betCardScene
			for(int i = 0; i < 80; i++) {
				buttonArr[i].setStyle("-fx-background-color: #ffe4c4;");
			}
			randomSpotsB.setStyle("-fx-background-color: #fafad2;");
			oneDrawingB.setStyle("-fx-background-color: #fafad2;");
			twoDrawingB.setStyle("-fx-background-color: #fafad2;");
			threeDrawingB.setStyle("-fx-background-color: #fafad2;");
			fourDrawingB.setStyle("-fx-background-color: #fafad2;");
			oneSpotB.setStyle("-fx-background-color: #fafad2;");
			fourSpotB.setStyle("-fx-background-color: #fafad2;");
			eightSpotB.setStyle("-fx-background-color: #fafad2;");
			tenSpotB.setStyle("-fx-background-color: #fafad2;");
			//change menus of scenes
			ruleBp.setTop(rulesGameMenuBar);
			oddsWinBp.setTop(oddsGameMenuBar);
			
			//clear VBoxes HBoxes 
			resultsVB.getChildren().clear();
			resultsVB.getChildren().addAll(resultsTitle, twentyRandTxt, randNumsHB, numbersMarkedTxt,userMarkedHB, numbersCorrectTxt, userMarkedCorrectlyHB);
			userMarkedHB.getChildren().clear();
			randNumsHB.getChildren().clear();
			userMarkedCorrectlyHB.getChildren().clear();
			primaryStage.setScene(betCardScene);
			primaryStage.show();
			
			
		});
		
		rulesReturnToGame.setOnAction(toBetCardScene);
		oddsReturnToGame.setOnAction(toBetCardScene);
		
		//play Button event Handler
		playB.setOnAction(e->{
			ruleBp.setTop(rulesGameMenuBar);
			oddsWinBp.setTop(oddsGameMenuBar);
			primaryStage.setScene(betCardScene);
			primaryStage.show();
		});
		
		
		//oldLook menu option handler
		newLookHandler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if(oldLook) {	//if on oldLook, change to newLook
					bpCard.setStyle("-fx-background-color: #f0ffff;");
					//changes color of random button depending on if it's pressed or not
					if(betGame.random)	{randomSpotsB.setStyle("-fx-background-color: #66cdaa;");}	//pressed
					else	{randomSpotsB.setStyle("-fx-background-color: #ffe4b5;");}	//not pressed
					
					//change marked spots color
					int[] markedNums = betGame.getMarked();
					for(int i = 0; i < markedNums.length; i++) {
						int index = markedNums[i] - 1;
						buttonArr[index].setStyle("-fx-background-color: #cd5c5c;");
					}
					
					//change reset Button color to new
					resetB.setStyle("-fx-background-color: #b0c4de;");
					
					//change rules scene style to new
					ruleBp.setStyle("-fx-background-color: #e0ffff;");
					
					//change the oddsWin scene style to new
					oddsWinBp.setStyle("-fx-background-color: #e0ffff;");
					
					//change the results scene to new
					resultsBp.setStyle("-fx-background-color: #e0ffff;");
					
					oldLook = false;
				}
				else {//change to old oldLook
					bpCard.setStyle("-fx-background-color: #e9967a;");
					//changes color of random depending on if it's pressed or not
					if(betGame.random)	{randomSpotsB.setStyle("-fx-background-color: #1e90ff;");}	//pressed
					else	{randomSpotsB.setStyle("-fx-background-color: #fafad2;");}	//not pressed
					
					//change marked spots color
					int[] markedNums = betGame.getMarked();
					for(int i = 0; i < markedNums.length; i++) {
						int index = markedNums[i] - 1;
						buttonArr[index].setStyle("-fx-background-color: #add8e6;");
					}
					
					//change reset Button color to original
					resetB.setStyle("-fx-background-color: #e6e6fa;");
					
					//change rules scene style to original
					ruleBp.setStyle("-fx-background-color: #ffe4c4;");
					
					//change the oddsWin scene style to original
					oddsWinBp.setStyle("-fx-background-color: #ffe4c4;");
					
					//change the results Scene style to original
					resultsBp.setStyle("-fx-background-color: #e9967a;");
					
					oldLook = true;
					
				}
			}
		};
		
		newLook.setOnAction(newLookHandler);
		newLookResults.setOnAction(newLookHandler);
		
	    //show start screen
		primaryStage.setScene(startScene);
		primaryStage.show();
	}
	
	
}
