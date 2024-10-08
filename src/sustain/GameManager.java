package sustain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Sets up and coordinates entire game play.
 */
public class GameManager {

	/**
	 * The minimum number of players permitted to play the game.
	 */
	private static final int MIN_PLAYERS = 2;

	/**
	 * The maximum number of players permitted to play the game.
	 */
	private static final int MAX_PLAYERS = 4;

	/**
	 * The quantity of resources each player picks up at the start square at the
	 * start of the game.
	 */
	private static final int STARTING_RESOURCES = 1500;

	/**
	 * The position on the board — represented as an index value — from which
	 * players start the game (i.e. the start square).
	 */
	private static final int STARTING_POSITION = 0;

	/**
	 * The default development level that each area starts at.
	 */
	private static final int STARTING_DEVELOPMENT = 0;

	/**
	 * The quantity of resources each player receives when they pass the start
	 * square.
	 */
	private static final int PASS_GO_PAY = 200;

	/**
	 * Comparator for determining results at end of game.
	 */
	private static final PlayerBalanceComparatorDesc PLAYER_BALANCE_COMPARATOR = new PlayerBalanceComparatorDesc();

	/**
	 * The number of rounds the game is limited to, after which the game must end.
	 */
	private static final int ROUND_LIMIT = 50;

	private static final int MAX_REGIONLESS_DEV = 2;

	private static final int MAX_REGION_OWNED_DEV = 5;

	private static final int AUCTION_INCREMENT = 50;

	/**
	 * The list of players for the game.
	 */
	private List<Player> playerList;

	/**
	 * The player whose turn it is.
	 */
	private int currentPlayer;

	/**
	 * The board the game is played on.
	 */
	private Board gameBoard;

	/**
	 * The first dice.
	 */
	private Dice dice1;

	/**
	 * The second dice.
	 */
	private Dice dice2;

	/**
	 * Constructor with arguments to set Dice fields to assigned values and
	 * initialise list of players as ArrayList on object creation.
	 * 
	 * @param dice1Sides
	 * @param dice2Sides
	 */
	public GameManager(int dice1Sides, int dice2Sides) {

		this.playerList = new ArrayList<Player>();
		this.dice1 = new Dice(dice1Sides);
		this.dice2 = new Dice(dice2Sides);
	}

	/**
	 * Default constructor to initialise list of players as ArrayList and set other
	 * fields to null values on object creation.
	 */
	public GameManager() {
		this.playerList = new ArrayList<Player>();
	}

	/**
	 * Constructor with arguments to set all fields to assigned values on object
	 * creation.
	 * 
	 * @param playerList
	 * @param currentPlayer
	 * @param gameBoard
	 * @param dice1
	 * @param dice2
	 */
	public GameManager(List<Player> playerList, int currentPlayer, Board gameBoard, Dice dice1, Dice dice2) {

		super();
		this.playerList = playerList;
		this.currentPlayer = currentPlayer;
		this.gameBoard = gameBoard;
		this.dice1 = dice1;
		this.dice2 = dice2;
	}

	/**
	 * Gets the list of players.
	 * 
	 * @return list of players
	 */
	public List<Player> getPlayerList() {
		return playerList;
	}

	/**
	 * Sets the list of players.
	 * 
	 * @param playerList
	 */
	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}

	/**
	 * Gets player whose turn it is.
	 * 
	 * @return the player whose turn it is
	 */
	public int getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Sets player whose turn it is.
	 * 
	 * @param currentPlayer
	 */
	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * Gets the game board.
	 * 
	 * @return game board
	 */
	public Board getGameBoard() {
		return gameBoard;
	}

	/**
	 * Sets the game board.
	 * 
	 * @param gameBoard
	 */
	public void setGameBoard(Board gameBoard) {
		this.gameBoard = gameBoard;
	}

	/**
	 * Gets the first dice.
	 * 
	 * @return Dice object.
	 */
	public Dice getDice1() {
		return dice2;
	}

	/**
	 * Sets the first dice.
	 * 
	 * @param dice2
	 */
	public void setDice1(Dice dice1) {
		this.dice2 = dice1;
	}

	/**
	 * Gets the second dice.
	 * 
	 * @return Dice object.
	 */
	public Dice getDice2() {
		return dice2;
	}

	/**
	 * Sets the second dice.
	 * 
	 * @param dice2
	 */
	public void setDice2(Dice dice2) {
		this.dice2 = dice2;
	}

	/**
	 * Calls methods to register players, set up game board and play the game.
	 */
	public static void main(String[] args) {

		GameBanner.printGameBanner("SUSTAINOPOLY");
		// modified constructor to use 2d4 instead of 2d2
		GameManager gameManager = new GameManager(4, 4);

		Scanner sc = new Scanner(System.in);

		// handle board creation
		gameManager.createGameBoard();

		// handle player config
		gameManager.gamePlayersConfig(sc);

//		helper method for quickly setting up a game board

//		gameManager.gamePlayersConfigFast();

		// test
		// handy test set up code for quickly assigning first region to first player -
		// comment out for now

		// dear god this needs abstracted into a function before i lose my mind!

//		Player tarPlayer = gameManager.getPlayerList().get(0);
//		// this toString() REEEEEEEE
////		System.out.println(tarPlayer.toString());
//
//		gameManager.getGameBoard().getRegionList().get(0).setOwner(tarPlayer);
//		tarPlayer.incrementOwnedRegions();
//		List<Area> ba = gameManager.getGameBoard().getRegionList().get(0).getAreasWithinRegion();
//
//		ba.forEach((x) -> {
//			x.setOwner(tarPlayer);
//			tarPlayer.incrementOwnedAreas();
//		});

		// play game
		gameManager.playGame(sc);

	}

	/**
	 * Prompts user to input the number of players, the names of each player and
	 * adds them to a shuffled player list to randomise the order in which players
	 * take turns throughout the game.
	 */
	protected void gamePlayersConfig(Scanner sc) {

		List<String> availableColors = initializeColorList();

		System.out.println("Welcome to Sustainopoly!");

		int numberOfPlayers = getNumberOfPlayersFromUser(sc);

		System.out.println("You have selected " + numberOfPlayers + " players");

		for (int i = 1; i <= numberOfPlayers; i++) {
			String playerName = getPlayerNameFromUser(i);
			String chosenColor = chooseColour(i, availableColors);
			Player playerToAdd = new Player(playerName, chosenColor, STARTING_RESOURCES, STARTING_POSITION);
			playerList.add(playerToAdd);
		}
		System.out.println("Current player list:");
		this.displayPlayerList();

		System.out.println("Randomizing turn order...");
		this.randomizePlayerListOrder();

		this.displayPlayerList();
	}

	/**
	 * Gets the chosen number of players from the user and checks for invalid
	 * inputs.
	 * 
	 * @param scanner object
	 * 
	 * @return valid number of players (2 to 4)
	 */
	private static int getNumberOfPlayersFromUser(Scanner sc) {

		int numberOfPlayersInput;

		do {
			System.out.println(
					"Please select the number of players for this game (" + MIN_PLAYERS + "-" + MAX_PLAYERS + ")");

			try {
				numberOfPlayersInput = Integer.valueOf(sc.nextLine());
				;
			} catch (Exception e) {
				numberOfPlayersInput = -1000;
			}

			if (numberOfPlayersInput < MIN_PLAYERS || numberOfPlayersInput > MAX_PLAYERS) {
				System.out.println("invalid input!");
			}

		} while (numberOfPlayersInput < MIN_PLAYERS || numberOfPlayersInput > MAX_PLAYERS);

		while (true) {
			System.out.println("You selected : " + numberOfPlayersInput + " - is this ok? - y or n");
			String userInput = sc.nextLine();

			if (userInput.equalsIgnoreCase("y")) {
				System.out.println("Starting game with : " + numberOfPlayersInput + " players");
				break;
			} else if (userInput.equalsIgnoreCase("n")) {
				System.out.println("Restarting selection");
				return getNumberOfPlayersFromUser(sc);
			} else {
				System.out.println("invalid input detected");

			}
		}

		return numberOfPlayersInput;
	}

	/**
	 * Gets names for each player from user and checks if name is already
	 * registered.
	 * 
	 * @param i
	 * @return name of player
	 */
	private String getPlayerNameFromUser(int i) {

		Scanner sc = new Scanner(System.in);
		String playerName;
		boolean playerNameAccepted;

		do {
			System.out.println("Input player name for player " + i + "!");
			playerName = sc.nextLine();

			// validate is not empty string
			if (playerName.length() < 1) {
				System.out.println("Player name input invalid - name must not be blank!");
				playerNameAccepted = false;
				continue;
			}

			if (playerName.length() > 9) {
				System.out.println("Player name input too long! - 9 Characters maximum");
				playerNameAccepted = false;
				continue;
			}

			// validate does not contain invalid characters
			if (!PlayerInputValidator.isValidPlayerName(playerName)) {
				System.out.println("Player name input invalid - contains non-alphabetic characters");
				playerNameAccepted = false;
				continue;
			}

			// validate not already in list

			if (playerNameAlreadyInList(playerName)) {
				playerNameAccepted = false;
				System.out.println("Player name already in list!");
				continue;
			}

			System.out.println("You input : " + playerName + " ...is this correct? (y to accept)");

			String userAcceptPlayerNameInput = sc.nextLine();

			if (userAcceptPlayerNameInput.equalsIgnoreCase("y")) {
				playerNameAccepted = true;
			} else {
				playerNameAccepted = false;
			}

		} while (!playerNameAccepted);

		return playerName;
	}

	/**
	 * Checks if name input by user is the name of another player.
	 * 
	 * @param playerName
	 * @return true/false
	 */
	private boolean playerNameAlreadyInList(String playerName) {

		for (Player player : playerList) {
			String playerFromListName = player.getName();

			String sanitizedPlayerNameFromList = playerFromListName.replaceAll("\u001B\\[[;\\d]*m", "");

			if (sanitizedPlayerNameFromList.trim().equalsIgnoreCase(playerName.trim())) {
//				
				return true;
			}
		}
		return false;
	}

	// This will be printed on set up - colours from the list, gets and removes
	private static String chooseColour(int i, List<String> availableColors) {
		System.out.println("Choose a colour for player " + i + ":");
		int chosenColorIndex = displayColourOptions(availableColors);
		String chosenColor = availableColors.get(chosenColorIndex);

		availableColors.remove(chosenColorIndex);
		return chosenColor;
	}

	// Colours being added to the list - Leaving red and green out due to their use
	// as a signifier
	private static List<String> initializeColorList() {
		List<String> colours = new ArrayList<>();
		colours.add("\u001B[33m"); // Yellow
		colours.add("\u001B[34m"); // Blue
		colours.add("\u001B[35m"); // Magenta
		colours.add("\u001B[36m"); // Cyan
		return colours;
	}

	// displays the colours to the users - As long as it is less than size I.E 4 due
	// to max players - scalable with increased users
	private static int displayColourOptions(List<String> availableColours) {

		Scanner sc = new Scanner(System.in);
		int chosenIndex = -1; // Initialize chosenIndex to an invalid value

		while (chosenIndex < 0 || chosenIndex >= availableColours.size()) {
			System.out.println("Available Colours :");
			System.out.println();
			for (int i = 0; i < availableColours.size(); i++) {
				System.out.println((i + 1) + ". " + availableColours.get(i) + "colour");
				System.out.println("\u001B[0m");
			}

			System.out.print("\u001B[0m"); // Resets colour to plain
			System.out.print("Enter the number of your chosen colour: ");

			try {
				chosenIndex = Integer.parseInt(sc.nextLine()) - 1;
				if (chosenIndex < 0 || chosenIndex >= availableColours.size()) {
					System.out.println("Invalid choice. Please enter a valid colour number.");
				}
			} catch (NumberFormatException e) {
				// Handle the exception gracefully
				System.out.println("Invalid input. Please enter a valid number.");
			}
		}

		return chosenIndex;

	}

	/**
	 * Randomises order of players
	 */
	private void randomizePlayerListOrder() {
		Collections.shuffle(playerList);

	}

	/**
	 * Prints list of players to console.
	 */
	public void displayPlayerList() {
		for (Player player : playerList) {
			System.out.println(player.getName());
		}
	}

	/**
	 * Sets up the game board.
	 */
	public void createGameBoard() {

		/// eventually handle via a file parse - quite complex - do later
		// handle each region and constituent areas

		List<Square> squaresOnBoard = new ArrayList<Square>();

		NeutralArea start = new NeutralArea("World Climate Change Summit");
		start.setBoardDisplayName(" World Climate.X. Change Summit");
		start.setAreaFunction("Start:Gain $200M");
		start.setBoardDisplayName("      <---.X.    This way!");
		squaresOnBoard.add(start);

		// call with default constructor
		Region northAmerica = new Region();

		northAmerica.setName("North America");

		// name, development level at game start, purchase cost, rent/dev level, owner
		Area rockyMountains = new Area("Rocky Mountains", STARTING_DEVELOPMENT, 100, List.of(6, 30, 90, 270, 400, 550),
				null, northAmerica);
		rockyMountains.setBoardDisplayName("Rocky Mountains");

		Area grandCanyon = new Area("Grand Canyon", STARTING_DEVELOPMENT, 100, List.of(6, 30, 90, 270, 400, 550), null,
				northAmerica);
		grandCanyon.setBoardDisplayName("Grand Canyon");

		Area yellowStoneNationalPark = new Area("Yellowstone National Park", STARTING_DEVELOPMENT, 120,
				List.of(8, 40, 100, 300, 450, 600), null, northAmerica);
		yellowStoneNationalPark.setBoardDisplayName("Yellowstone");

		northAmerica.setAreasWithinRegion(List.of(rockyMountains, grandCanyon, yellowStoneNationalPark));
		northAmerica.setOwner(null);

		squaresOnBoard.add(rockyMountains);
		squaresOnBoard.add(grandCanyon);
		squaresOnBoard.add(yellowStoneNationalPark);

		// commenting out other regions to test develop area feature

		// next region - south america

		Region southAmerica = new Region();

		southAmerica.setName("South America");

		Area galapagos = new Area("Galapagos Islands", STARTING_DEVELOPMENT, 140, List.of(10, 50, 150, 450, 625, 750),
				null, southAmerica);
		galapagos.setBoardDisplayName("Galapagos");

		Area amazon = new Area("Amazon Rainforest", STARTING_DEVELOPMENT, 160, List.of(12, 60, 180, 500, 700, 900),
				null, southAmerica);
		amazon.setBoardDisplayName("Amazon");

		southAmerica.setAreasWithinRegion(List.of(galapagos, amazon));
		southAmerica.setOwner(null);

		squaresOnBoard.add(galapagos);
		squaresOnBoard.add(amazon);

		// next region - 1 location region - "do nothing square"

		NeutralArea justStopOil = new NeutralArea("Just Stop Oil Protest");
		justStopOil.setBoardDisplayName(" Just Stop.X. Oil Protest");
		justStopOil.setAreaFunction("Take a break...");
		squaresOnBoard.add(justStopOil);

		// next region - africa

		Region africa = new Region();

		africa.setName("Africa");

		Area serengeti = new Area("Serengeti National Park", STARTING_DEVELOPMENT, 220,
				List.of(18, 90, 250, 700, 875, 1050), null, africa);
		serengeti.setBoardDisplayName("Serengeti");

		Area kalahari = new Area("Kalahari Desert", STARTING_DEVELOPMENT, 220, List.of(18, 90, 250, 700, 875, 1050),
				null, africa);
		kalahari.setBoardDisplayName("Kalahari");

		Area congo = new Area("Congo Basin Rainforest", STARTING_DEVELOPMENT, 240,
				List.of(20, 100, 300, 750, 925, 1100), null, africa);
		congo.setBoardDisplayName("Congo Basin");

		africa.setAreasWithinRegion(List.of(serengeti, kalahari, congo));
		africa.setOwner(null);

		squaresOnBoard.add(serengeti);
		squaresOnBoard.add(kalahari);
		squaresOnBoard.add(congo);

		// next region - europe

		Region europe = new Region();

		europe.setName("Europe");

		Area scottishHigh = new Area("Scottish Highlands", STARTING_DEVELOPMENT, 260,
				List.of(22, 110, 330, 800, 975, 1150), null, europe);
		scottishHigh.setBoardDisplayName("Scot Highlands");

		Area spanishPyrenees = new Area("Spanish Pyrenees", STARTING_DEVELOPMENT, 280,
				List.of(24, 120, 360, 850, 1025, 1200), null, europe);
		spanishPyrenees.setBoardDisplayName("Spanish Pyrenees");

		europe.setAreasWithinRegion(List.of(scottishHigh, spanishPyrenees));
		europe.setOwner(null);

		squaresOnBoard.add(scottishHigh);
		squaresOnBoard.add(spanishPyrenees);

		// add regions to list
		List<Region> regions = new ArrayList<Region>();
		regions.add(northAmerica);
		regions.add(southAmerica);
		regions.add(africa);
		regions.add(europe);

		Board board = new Board(squaresOnBoard, regions);
		// creates board with just one region for testing develop area feature

		this.setGameBoard(board);
//		this.gameBoard.displayOrderedAreaList();
	}

	/**
	 * Plays game
	 * 
	 * @param sc
	 */
	public void playGame(Scanner sc) {
		System.out.println();
		System.out.println("Starting game...");
		System.out.println();

		// hard code for now
		boolean gameEndConditionReached = false;

		// turns counter
		int turnsTaken = 0;

		do {

			// calculate current round
			int currentRound = (turnsTaken / this.getPlayerList().size()) + 1;

			// handle round limit
			if (currentRound > ROUND_LIMIT) {
				System.out.println("Round limit reached!");
				break;
			}

			System.out.println("Current Round: " + currentRound);

			Player currentPlayerObject = this.getPlayerList().get(currentPlayer);
			System.out.println("Current player : " + currentPlayerObject.getName());

			// display current position
			System.out.print("Current Square: ");
			Square currentSquare = displayPositionOfPlayer(currentPlayerObject);
			System.out.println(currentSquare.getName());
			
			System.out.print("Current Balance: ");
			int currentBalance = currentPlayerObject.getBalance();
			System.out.println("$"+ currentBalance +"M");


			// handle burndown of unsustainable devs
			progressBurnDowns(currentPlayerObject);
			
			
			
			
			
			
			System.out.println();
			displayScoreboard();
			System.out.println();
			
			System.out.println();
			this.displayBoard();
			
			

			// Calling prompt to roll

			boolean doesPlayerWantToRoll = promptUserDiceRoll(sc);

			if (!doesPlayerWantToRoll) {
				triggerBankruptcy(currentPlayerObject);
				break;
			}

			// roll dice implemented
			int diceRoll = rollDice();

			// move player
			movePlayer(currentPlayerObject, diceRoll);

			// get current area

			currentSquare = getCurrentSquare(currentPlayerObject);

			// handle displaying position of current player - to implement
			// placeholder here
			System.out.println("New Square:\t\t" + currentSquare.getName());

			if (currentSquare.getClass() == NeutralArea.class) {
				System.out.println("Square Type:\t\tNeutral");
			} else {
				System.out.println("Square Type:\t\tArea");
				Area a = (Area) currentSquare;

				String ownerName = "not owned";
				if (a.getOwner() != null) {
					ownerName = a.getOwner().getName();
				}

				int devLevel = a.getDevelopmentLevel();
				String susType = a.getSustainableDevelopmentType().toString();
				String region = a.getRegion().getName();

				System.out.println("Owner :\t\t\t" + ownerName);
				System.out.println("Development Level :\t" + devLevel);
				System.out.println("Sustainability Type :\t" + susType);
				System.out.println("Region :\t\t" + region);
				System.out.println("Rent :\t\t\t$" + a.getCurrentRent() + "M");
				System.out.println();

			}

			// check if area neutral

			boolean areaIsNeutral = false;
			if (currentSquare.getClass() == NeutralArea.class) {
				areaIsNeutral = true;
			}

			// check ownership status

			Player owner = getOwnerOfPlayerCurrentLocation(currentPlayerObject);

			// if neutral - dont interact with purchasing options
			// if purchasable - interact with purchasing options

			// handle logic for dealing with location
			if (areaIsNeutral) {
				System.out.println("This is a neutral area - take a breather here!");
				System.out.println();
			} else if (owner == null) {
				System.out.println("No player owns this location...");
				System.out.println();

				boolean purchaseAccepted = offerPurchase(currentPlayerObject, sc);

				if (purchaseAccepted) {
					purchaseArea(currentPlayerObject, (Area) currentSquare);
				} else {
					triggerAuction((Area) currentSquare, currentPlayerObject, sc);
					System.out.println("Ending auction...Returning to current player's turn...");
					System.out.println(currentPlayerObject.getName());
				}

			} else if (owner.equals(currentPlayerObject)) {
				System.out.println("You own this square so you don't have to pay rent!");
				Area pa = (Area) currentSquare;
				printDevelopmentLevelStrings(pa);
				System.out.println();
			} else {
				System.out.println(
						"This square is owned by : " + owner.getName() + ", that means you have to pay rent...");
				System.out.println();
				Area purchasableCurrentArea = (Area) currentSquare;
				printDevelopmentLevelStrings(purchasableCurrentArea);
				boolean paymentAccepted = payPlayer(owner, currentPlayerObject, purchasableCurrentArea);

				// bankruptcy trigger testing

				// comment and uncomment out this code to immediately trigger bankruptcy
				// on first turn

				if (!paymentAccepted) {
					triggerBankruptcy(currentPlayerObject);
					// trigger bankruptcy - break out of loop
					break;
				}

			}

			// offer development
			// check for regions owned by current player
			List<Region> regionsOwnedByCurrentPlayer = getOwnedRegions(currentPlayerObject);
			List<Area> areasOwnedByCurrentPlayer = getOwnedAreas(currentPlayerObject);

			if (areasOwnedByCurrentPlayer.size() == 0) {
				System.out.println("You do not own any areas - Development unavailable");

			} else if (regionsOwnedByCurrentPlayer.size() == 0) {
				System.out.println("No full regions owned - minor development only");
				offerUserDevelopmentMenu(currentPlayerObject, sc);

			} else {
				System.out.println(
						"Full region owned - allowing major developments in that region and minor in any other owned areas");
				offerUserDevelopmentMenu(currentPlayerObject, sc);
			}

			// end turn
			System.out.println();
			System.out.println("Press enter to advance to the next turn!");
			sc.nextLine();

			changeTurnToNextPlayer();
			turnsTaken++;

		} while (!gameEndConditionReached);

		// note conditions to trigger game end not yet implemented!
		triggerGameEnd();

	}

	private void triggerAuction(Area currentArea, Player playerNotInAuction, Scanner sc) {
		System.out.println("Triggering auction at :");
		System.out.println(currentArea.toString());

		// base price for auction

		// increment level for each round of auction
		int auctionIncrement = AUCTION_INCREMENT;
		int currentBid = 0;

		currentBid = auctionIncrement + currentBid;

		// create a copy
		List<Player> playersInAuction = new ArrayList<>(this.getPlayerList());

		// remove current player
		playersInAuction.remove(playerNotInAuction);

		Iterator<Player> auctionIterator = playersInAuction.iterator();

		// set before first round
		Player playerInAuction = auctionIterator.next();

		boolean playerHasBid = false;

		while (playersInAuction.size() > 1) {
			// next player in auction

			System.out.println("Current Player in Auction : ");
			System.out.println(playerInAuction.getName());
			
			
			System.out.println("Current Bid: $" + (currentBid-auctionIncrement) + "M");
			
			System.out.println("Your Balance: $" + playerInAuction.getBalance() + "M");
			
			
			
			
			if(playerInAuction.getBalance() < currentBid) {
				System.out.println("Bid to stay in the auction : $" + (currentBid) + "M" );
				System.out.println("Insufficient balance, moving to next player");
				// forgot to remove this player from the auction!
				auctionIterator.remove();
			} else {
				System.out.println("Bid to stay in the auction : $" + (currentBid) + "M");
				System.out.println("Would you like to bid? y for yes - any other input for no");

				String userChoice = sc.nextLine();

				if (userChoice.equals("y")) {
					System.out.println("Accepted bid");
					currentBid = currentBid + auctionIncrement;
					playerHasBid = true;
				} else {
					System.out.println("No bid - removing you from bidding war!");
					auctionIterator.remove();
				}
			}

			System.out.println();

			// moving to next position
			if (auctionIterator.hasNext()) {
				// move iterator along
				playerInAuction = auctionIterator.next();
			} else {
				// reset the iterator
				auctionIterator = playersInAuction.iterator();
				playerInAuction = auctionIterator.next();
			}

		}

		if (playerHasBid) {
			System.out.println("You win the auction " + playerInAuction.getName() + "!");
			System.out.println("Executing transaction and giving ownership of area...");
			chargePlayer(playerInAuction, (currentBid-auctionIncrement) );
			currentArea.setOwner(playerInAuction);
			// handle region ownership change as well!!
			Region currentRegion = currentArea.getRegion();
			
			// also display the area
			System.out.println(currentArea.toString());

			boolean regionTakenOver = true;
			for (Area area : currentRegion.getAreasWithinRegion()) {
				if (area.getOwner() == null || !area.getOwner().equals(playerInAuction)) {
					regionTakenOver = false;

				}
			}

			if (regionTakenOver) {
				currentRegion.setOwner(playerInAuction);
				System.out.println("Taking ownership of region: " + currentRegion.getName());

			}

			System.out.println();
		} else {
			System.out.println("No player has bid...");
			System.out.println("Offering to last player in auction at base price...");
			System.out.println();

			// offer bid to last player
			System.out.println("Current Player in Auction : ");
			System.out.println(playerInAuction.getName());

			System.out.println("Current Bid: $" + (currentBid - 50) + "M");

			System.out.println("Your Balance: $" + playerInAuction.getBalance() + "M");
			
			
			
			if(playerInAuction.getBalance() < currentBid) {
				System.out.println("Insufficient balance, ending auction");
			} else {
				System.out.println("Bid to stay in the auction : $" + currentBid + "M");
				System.out.println("Would you like to bid? y for yes - any other input for no");

				String userChoice = sc.nextLine();

				if (userChoice.equals("y")) {
					System.out.println("You win the auction " + playerInAuction.getName() + "!");
					System.out.println("Spending balance and giving ownership of area...");
					
					chargePlayer(playerInAuction, currentBid);
					currentArea.setOwner(playerInAuction);
					// also display the area
					System.out.println(currentArea.toString());
					
					// handle region ownership change as well!!
					Region currentRegion = currentArea.getRegion();

					boolean regionTakenOver = true;
					for (Area area : currentRegion.getAreasWithinRegion()) {
						if (area.getOwner() == null || !area.getOwner().equals(playerInAuction)) {
							regionTakenOver = false;

						}
					}

					if (regionTakenOver) {
						currentRegion.setOwner(playerInAuction);
						System.out.println("Taking ownership of region: " + currentRegion.getName());
					}

					System.out.println();
				} else {
					System.out.println("No bid - removing you from bidding war and ending auction!");
					auctionIterator.remove();
				}

			}

		}

	}

	private void printDevelopmentLevelStrings(Area pa) {

		System.out.println("Developments on " + pa.getName() + " :");

		List<String> developments = pa.getDevelopmentLevelStrings();

		if (developments.size() > 0) {
			for (String development : developments) {
				System.out.println(development);
			}
		} else {
			System.out.println("No developments in this area");
		}

	}

	/**
	 * method which reduces the number of turns an unsustainable development can be
	 * used by 1
	 * 
	 * @param currentPlayerObject
	 */
	private void progressBurnDowns(Player currentPlayerObject) {

		List<Area> areas = this.getOwnedAreas(currentPlayerObject);

		for (Area area : areas) {

			Area pa = (Area) area;
			if (pa.getSustainableDevelopmentType() == SustainabilityType.UNSUSTAINABLE) {

				if (pa.progressBurnDown()) {
					System.out.println();
					System.out.println("Development at : " + pa.toString() + "deteriorated...");
					System.out.println("Now " + pa.getBurnDown() + " turns remaining");
					if (pa.getBurnDown() == 0) {
						System.out.println("Now this area no longer charges rent!");
					}
					System.out.println();
				}

			}

		}

	}

	/**
	 * Method which returns all squares on the board owned by a player.
	 * 
	 * @param currentPlayerObject
	 * @return - list of squares on board with owner equal to currentPlayerObject
	 */
	private List<Area> getOwnedAreas(Player currentPlayerObject) {
		List<Area> ownedAreas = new ArrayList<Area>();

		List<Square> boardSquares = gameBoard.getSquaresOnBoard();

		for (Square boardArea : boardSquares) {

			if (boardArea.getClass() == Area.class) {

				Area currentArea = (Area) boardArea;

				if (currentArea.getOwner() != null && currentArea.getOwner().equals(currentPlayerObject)) {
					ownedAreas.add(currentArea);
				}

			}
		}

		return ownedAreas;
	}

	/**
	 * Displays position of player on the board
	 * 
	 * @param currentPlayer
	 */
	private Square displayPositionOfPlayer(Player currentPlayer) {

		Square currentSquare = getCurrentSquare(currentPlayer);
		return currentSquare;

	}

	/**
	 * Gets the square the current player has landed on.
	 * 
	 * @param currentPlayerObject
	 * @return square as Square object
	 */
	private Square getCurrentSquare(Player currentPlayerObject) {

		int currentLocationIndex = currentPlayerObject.getCurrentSquare();

		Square currentSquare = gameBoard.getSquaresOnBoard().get(currentLocationIndex);

		return currentSquare;
	}

	/**
	 * Prompts current player to roll the dice.
	 * 
	 * @return true if player wants to roll the dice — otherwise false
	 */
	private boolean promptUserDiceRoll(Scanner sc) {
		String userInput;
		boolean userResponse;

		while (true) {

			System.out.println("Would you like to roll dice? - y or n");

			userInput = sc.nextLine();

			if (userInput.equalsIgnoreCase("y")) {
				userResponse = true;
				break;
			} else if (userInput.equalsIgnoreCase("n")) {

				// Double check if the user would not like to roll - TERMINATES GAME!
				while (true) {

					System.out.println(
							"Are you sure? This will terminate the game! y to continue playing - n to quit game"); // Only
					// print
					// y
					// or
					// n
					// in
					// their
					// colours
					// not
					// sentence
					userInput = sc.nextLine();
					if (userInput.equalsIgnoreCase("y")) {
						userResponse = true;
						break;
					} else if (userInput.equalsIgnoreCase("n")) {
						userResponse = false;
						break;
					} else {
						continue;
					}

				}

				if (userResponse == true) {
					continue;
				} else {
					break;
				}

			} else {
				continue;
			}
		}
		return userResponse;

	}

	/**
	 * Bankrupts current player and sets balance to —1000.
	 * 
	 * @param currentPlayerObject
	 */
	private void triggerBankruptcy(Player currentPlayerObject) {
		System.out.println("PLAYER : " + currentPlayerObject.getName() + " BANKRUPT!");
		currentPlayerObject.setBalance(-1000);

	}

	/**
	 * Ends the game, displaying the winner and results for each player.
	 */
	private void triggerGameEnd() {
		System.out.println();
		System.out.println("Ending game...");
		System.out.println();

		// sort list according to player balance comparator

		// to implement - tie breaks?
		Collections.sort(this.getPlayerList(), PLAYER_BALANCE_COMPARATOR);
		System.out.println(this.getPlayerList().get(0).getName() + " IS THE WINNER!!!");

		System.out.println();

		System.out.println("Full scoreboard: ");
		System.out.println();

		for (int i = 0; i < playerList.size(); i++) {
			Player currentPlayer = playerList.get(i);
			System.out.println((i + 1) + ". " + currentPlayer.endGameToString());
		}

	}
	
	/**
	 * display game scoreboard without sorting playerList
	 * @param playerList
	 */
	private void displayScoreboard() {
		// java and its pass by reference nightmares...
		
		// this gives reference to duplicate player list for sorting
		List<Player> dupePlayerList = new ArrayList<Player>(this.playerList);
		
		Collections.sort(dupePlayerList, PLAYER_BALANCE_COMPARATOR);
		System.out.println("Full scoreboard: ");
		System.out.println();

		
		for (int i = 0; i < dupePlayerList.size(); i++) {
			Player currentPlayer = dupePlayerList.get(i);
			System.out.println((i + 1) + ". " + currentPlayer.endGameToString());
		}
	}

	/**
	 * Rolls dice.
	 * 
	 * @return result of dice roll
	 */
	public int rollDice() {
		int roll1 = this.dice1.roll();
		int roll2 = this.dice2.roll();
		System.out.println();
		System.out.println("Rolling dice...");
		
		
		
		showNumberAsDice(roll1);
		showNumberAsDice(roll2);
		

		System.out.println("Dice 1 : " + "you rolled : " + roll1);
		System.out.println("Dice 2 : " + "you rolled : " + roll2);
		System.out.println("For a total of " + (roll1 + roll2));
		System.out.println();

		return (roll1 + roll2);
	}

	private void showNumberAsDice(int roll) {
		switch (roll) {
		case 1:
			System.out.print("┌─────────┐\n" + "│         │\n" + "│    ●    │\n" + "│         │\n" + "└─────────┘\n");
			break;
		case 2:
			System.out.print("┌─────────┐\n" + "│  ●      │\n" + "│         │\n" + "│      ●  │\n" + "└─────────┘\n");
			break;

		case 3:
			System.out.println("┌─────────┐\n" + "│  ●      │\n" + "│    ●    │\n" + "│      ●  │\n" + "└─────────┘\n");
			break;

		case 4:
			System.out.println("┌─────────┐\n" + "│  ●   ●  │\n" + "│         │\n" + "│  ●   ●  │\n" + "└─────────┘\n");

		}
		
	}

	/**
	 * Moves the player to a square determined by the result of the dice roll.
	 * 
	 * @param currentPlayerObject
	 * @param diceRoll
	 */
	private void movePlayer(Player currentPlayerObject, int diceRoll) {
		int maxBoardIndex = this.getGameBoard().getSquaresOnBoard().size() - 1;

		int adjustedPositionInt = diceRoll + currentPlayerObject.getCurrentSquare();

		if (adjustedPositionInt > maxBoardIndex) {
			adjustedPositionInt -= (maxBoardIndex + 1);

			System.out.println("You passed start!");
			payPlayer(currentPlayerObject, PASS_GO_PAY);
		}

		currentPlayerObject.setCurrentSquare(adjustedPositionInt);
		System.out.println("Moving you " + diceRoll + " spaces...");
		System.out.println();
	}

	/**
	 * Checks if an area is owned by a player.
	 * 
	 * @param currentPlayerObject
	 * @return Player that owns the area or null if square is not owned.
	 */
	private Player getOwnerOfPlayerCurrentLocation(Player currentPlayerObject) {

		// not sure if i agree with parameter required here - might not be necessary

		Square currentSquare = getCurrentSquare(currentPlayerObject);

		if (currentSquare.getClass() == Area.class) {
			Area currentAreaPurch = (Area) currentSquare;
			Player owner = currentAreaPurch.getOwner();
			return owner;
		}

		return null;
	}

	/**
	 * Offers the current player to buy the area they landed on.
	 * 
	 * @param currentPlayerObject
	 * @return True if the player wants to buy; otherwise false.
	 */
	private boolean offerPurchase(Player currentPlayerObject, Scanner sc) {
		// not sure i like parameterising, but it definitely makes the programming a bit
		// easier
		Area currentArea = (Area) getCurrentSquare(currentPlayerObject);

		int purchaseCost = currentArea.getPurchasePrice();

		if (purchaseCost > currentPlayerObject.getBalance()) {
			System.out.println("Whoops - you cannot currently afford this area!");
			return false;
		}

		while (true) {
			System.out.println(
					"Would you like to purchase the current location for $" + purchaseCost + "M price? - y or n");

			String userInput = sc.nextLine();

			if (userInput.equalsIgnoreCase("y")) {
				return true;
			} else if (userInput.equalsIgnoreCase("n")) {
				return false;
			} else {
				continue;
			}

		}

	}

	/**
	 * Decreases resources of the current player and sets them as the owner of the
	 * area they agreed to buy.
	 * 
	 * @param currentPlayerObject
	 * @param currentSquare
	 */
	private void purchaseArea(Player currentPlayerObject, Area currentArea) {
		chargePlayer(currentPlayerObject, currentArea.getPurchasePrice());
		currentArea.setOwner(currentPlayerObject);
		System.out.println(
				"You successfully purchased " + currentArea.getName() + " for $" + currentArea.getPurchasePrice() + "M");

		// increment owned area count
		currentPlayerObject.incrementOwnedAreas();

		Region regionOfPurchase = currentArea.getRegion();

		List<Area> areasWithinRegion = regionOfPurchase.getAreasWithinRegion();

		for (Area area : areasWithinRegion) {
			Area pa = (Area) area;

			if (pa.getOwner() == null || !pa.getOwner().equals(currentPlayerObject)) {
				return;
			}

		}

		regionOfPurchase.setOwner(currentPlayerObject);
		// increment owned region count
		currentPlayerObject.incrementOwnedRegions();
		System.out.println("You have taken control over the region : " + regionOfPurchase);

	}

	/**
	 * Checks if the current player has enough resources to pay rent to the owner of
	 * the area they landed on.
	 * 
	 * @param recipient
	 * @param payer
	 * @param amount
	 * @return True if player has enough resources; otherwise false.
	 */
	private boolean payPlayer(Player recipient, Player payer, Area purchasableCurrentArea) {
		int amount = purchasableCurrentArea.getCurrentRent();

		if (!chargePlayer(payer, amount)) {
			return false;
		}

		payPlayer(recipient, amount);
		return true;
	}

	/**
	 * Checks for bankruptcy and decreases rent payment from the current player's
	 * balance if they have enough.
	 * 
	 * @param playerToCharge
	 * @param amount
	 * @return
	 */
	private boolean chargePlayer(Player playerToCharge, int amount) {
		int balanceToSet = playerToCharge.getBalance() - amount;
		System.out.println();
		System.out.println("Attempting to charge " + playerToCharge.getName() + " $" + amount + "M");

		if (amount == 0) {
			System.out.println("This area has deteriorated. Not charging rent for this location.");
			System.out.println();
			return true;
		}

		if (balanceToSet < 0) {
			System.out.println("BANKRUPTCY TRIGGERED FOR " + playerToCharge.getName());
			System.out.println();
			return false;
		}

		playerToCharge.setBalance(balanceToSet);
		System.out.println("New balance set at $" + balanceToSet + "M for " + playerToCharge.getName());
		System.out.println();
		return true;

	}

	/**
	 * Increases the balance of the player who owns the area that the current player
	 * landed on.
	 * 
	 * @param recipient
	 * @param amount
	 */
	private void payPlayer(Player recipient, int amount) {
		int balanceToSet = recipient.getBalance() + amount;
		System.out.println("Paying " + recipient.getName() + " $" + amount + "M");
		System.out.println("New balance : $" + balanceToSet + "M");
		recipient.setBalance(balanceToSet);
	}

	/**
	 * Checks for regions owned by the current player.
	 * 
	 * @param currentPlayerObject
	 * @return list of regions owned by the current player.
	 */
	private List<Region> getOwnedRegions(Player currentPlayerObject) {

		List<Region> allRegions = this.gameBoard.getRegionList();
		List<Region> ownedRegions = new ArrayList<Region>();

		for (Region region : allRegions) {
			if (region.getOwner() != null && region.getOwner().equals(currentPlayerObject)) {
				ownedRegions.add(region);
			}

		}
		return ownedRegions;
	}

	/**
	 * Offers current player the opportunity to develop an area, provided they own
	 * all the areas within the same region, and checks if they have enough
	 * resources to do so.
	 * 
	 * @param regionsOwnedByCurrentPlayer
	 * @param currentPlayerObject
	 */
	private void offerUserDevelopmentMenu(Player currentPlayerObject, Scanner sc) {
//		Scanner sc = new Scanner(System.in);

		// get owned areas
		List<Area> ownedAreaList = getOwnedAreas(currentPlayerObject);

		// get developableAreas

		List<Area> developableAreas = new ArrayList<Area>();

		for (Area purchasableArea : ownedAreaList) {

			// nested ifs an absolute ton of conditions to check here!!
			if (purchasableArea.getDevelopmentLevel() < MAX_REGIONLESS_DEV
					|| ((purchasableArea.getRegion().getOwner() != null)
							&& purchasableArea.getRegion().getOwner().equals(currentPlayerObject)
							&& purchasableArea.getDevelopmentLevel() < MAX_REGION_OWNED_DEV)) {
				if (currentPlayerObject.getBalance() > Area.getUpgradeCosts()
						.get(purchasableArea.getDevelopmentLevel())) {

					// add condition here for if area has burned down - cannot develop areas which have deteriorated after deving unsustainably
					// nested ifs im so sorry please forgive me <3
					if(purchasableArea.getBurnDown() != 0) {
						developableAreas.add(purchasableArea);
					}
				}
			}

		}

		// if no areas developable - print that and return from function
		if (developableAreas.size() == 0) {
			System.out.println("No areas developable!");
			return;
		}

		System.out.println("Development Menu:");

		// give option to develop other areas within region, even though not positioned
		// on those areas
		// only one area at a time
		int userInputInt;
		do {
			System.out.println("Select an area to develop:");
			System.out.println();
			for (int i = 0; i < developableAreas.size(); i++) {
				Area currentArea = developableAreas.get(i);
				int option = i + 1;
				StringBuilder sb = new StringBuilder();
				// separate counter instead of index?
				sb.append(option);
				sb.append(". ");
				sb.append(currentArea.getName());
				sb.append("\n");

				sb.append("Current development :\t");
				sb.append(currentArea.getDevelopmentLevel());
				sb.append("\n");
				// is this cost to develop or the penalty fee for other players landing on the
				// area?

				sb.append("Sustainability Type :\t");
				sb.append(currentArea.getSustainableDevelopmentType());
				sb.append("\n");

				// this is development cost charged - maybe we should include new rent here as
				// well?
				sb.append("Development Cost :\t$");
				sb.append(Area.getUpgradeCosts().get(currentArea.getDevelopmentLevel()));
				sb.append("M \n");

				sb.append("Current Rent :\t\t$");
				sb.append(currentArea.getCurrentRent());
				sb.append("M \n");

				sb.append("Rent if upgraded :\t$");
				sb.append(currentArea.getRentDevLevel().get(currentArea.getDevelopmentLevel() + 1));
				sb.append("M \n");

				System.out.println(sb.toString());
			}
			System.out.println("n. Skip development on this turn");
			String userInput = sc.nextLine();
			if (userInput.equals("n")) {
				System.out.println("You have chosen not to develop an area - progressing to next step");
				userInputInt = -1;
				break;
			}

			try {
				userInputInt = Integer.valueOf(userInput); // -1 if starting from 1 above?
			} catch (Exception e) {
				userInputInt = -1000;
				continue;
			}

			// fix
			int selectionInt = userInputInt - 1;
			try {
				System.out.println(
						"You selected : " + userInputInt + " - " + developableAreas.get(selectionInt).getName());

			} catch (Exception e) {
				System.out.println("Invalid selection!");
				userInputInt = -1000;
				continue;
			}

			Area areaToDevelop = developableAreas.get(selectionInt);

			if (currentPlayerObject.getBalance() < areaToDevelop.getRentDevLevel()
					.get(areaToDevelop.getDevelopmentLevel())) {
				// too little balance to develop
				System.out.println("Insufficient balance to afford development");
				System.out.println("Your balance : " + currentPlayerObject.getBalance() + " Cost : "
						+ areaToDevelop.getRentDevLevel().get(areaToDevelop.getDevelopmentLevel()));
				userInputInt = -1000;
				continue;
			}

			// call method to charge player and change development level of area
			developArea(areaToDevelop, sc);

		} while (userInputInt == -1000);

	}

	/**
	 * Gets cost to develop an area, charges the player, then increments development
	 * level of area. Also takes input from the user to set the sustainability type
	 * of the area.
	 * 
	 * @param areaToDevelop
	 */
	private void developArea(Area areaToDevelop, Scanner sc) {
		// handle setting development type from user
		if (areaToDevelop.getDevelopmentLevel() == 0) {

			int userInputInt = -1000;
			while (userInputInt == -1000) {

				System.out.println("Would you like to develop this area sustainably or unsustainbly?");
				System.out.println("1 for sustainably, 2 for unsustainably...");
				try {

					userInputInt = Integer.valueOf(sc.nextLine());
				} catch (Exception e) {
					userInputInt = -1000;
				}

				if (userInputInt != 1 && userInputInt != 2) {
					userInputInt = -1000;
				}

				if (userInputInt == -1000) {
					System.out.println("Invalid input!");
				}
			}

			if (userInputInt == 1) {
				// set development type to sustainable
				areaToDevelop.setSustainableDevelopmentType(SustainabilityType.SUSTAINABLE);
			} else if (userInputInt == 2) {
				// set development type to unsustainable
				areaToDevelop.setSustainableDevelopmentType(SustainabilityType.UNSUSTAINABLE);
			}

			System.out.println("Sustainability type set to : " + areaToDevelop.getSustainableDevelopmentType());

		}

		// fetch area owner
		Player owner = areaToDevelop.getOwner();

		int currentDevCost = areaToDevelop.getRentDevLevel().get(areaToDevelop.getDevelopmentLevel());

		chargePlayer(owner, currentDevCost);

		int currentDevelopmentLevel = areaToDevelop.getDevelopmentLevel();

		areaToDevelop.setDevelopmentLevel(currentDevelopmentLevel + 1);

		System.out.println("Successfully increased development level from  " + currentDevelopmentLevel + " to "
				+ (currentDevelopmentLevel + 1));
		System.out.println();

		// this should be encapsulated but i am at the end of my tether friends

		System.out.println("New Area details following development :");
		Area a = (Area) areaToDevelop;

		String ownerName = "not owned";
		if (a.getOwner() != null) {
			ownerName = a.getOwner().getName();
		}

		int devLevel = a.getDevelopmentLevel();
		String susType = a.getSustainableDevelopmentType().toString();
		String region = a.getRegion().getName();
		System.out.println("Area : \t\t\t" + a.getName());
		System.out.println("Owner :\t\t\t" + ownerName);
		System.out.println("Development Level :\t" + devLevel);
		System.out.println("Sustainability Type :\t" + susType);
		System.out.println("Region :\t\t" + region);
		System.out.println("Rent :\t\t\t$" + a.getCurrentRent() + "M");
		System.out.println();
		printDevelopmentLevelStrings(areaToDevelop);
	}

	/**
	 * Changes turn to next player
	 */
	private void changeTurnToNextPlayer() {
		System.out.println("Starting next player turn...");
		System.out.println();
		currentPlayer++;

		if (currentPlayer >= playerList.size()) {
			currentPlayer = 0;
		}

	}

	/**
	 * Registers players to test program.
	 *
	 */
	protected void gamePlayersConfigFast() {

		List<String> availableColors = initializeColorList();
		System.out.println("Welcome to Sustainopoly!");

		int numberOfPlayers = 3;
		System.out.println("You have selected " + numberOfPlayers + " players");

		for (int i = 1; i <= numberOfPlayers; i++) {
			String playerName = "name " + i;
			String chosenColor = chooseColour(i, availableColors);
			Player playerToAdd = new Player(playerName, chosenColor, STARTING_RESOURCES, STARTING_POSITION);
			playerList.add(playerToAdd);
		}

	}

	protected void displayBoard() {
		
		System.out.println("\nDisplaying board...\n");
		GameBoardDisplay gbd = new GameBoardDisplay();
		String[] stringsToPopulate = new String[12];
		List<Square> baList = this.getGameBoard().getSquaresOnBoard();
		for (int i = 0 ; i < baList.size(); i++) {
			
			// handle square details
			Square ba = baList.get(i);
			String toPopulate = ba.getBoardDetails();
			
			// handle players at that location
			List<Player> playerList = this.getPlayerList();
			List<Player> playersInArea = new ArrayList<Player>();
			
			for(Player player : playerList) {
				if(player.getCurrentSquare() == i) {
					playersInArea.add(player);
				}
			}
			String characterIndicator = "\u25CF";
			// start with separator
			String coloursInArea = ",";
			
			for(int j = 0; j < playersInArea.size(); j++) {
				// get colour!
				Player player = playersInArea.get(j);
				String colour = player.getColour();
				
				coloursInArea += " ";
				coloursInArea += colour;
				coloursInArea += characterIndicator;
				coloursInArea += "\u001B[30m";
				
				// skip last plaace
				if(j != playersInArea.size() - 1) {
					// additional separator
//					coloursInArea += " ";
				}
			}
			
			toPopulate += coloursInArea;
			stringsToPopulate[i] = toPopulate;
			
			
		}

		gbd.populateBoard(stringsToPopulate);
		gbd.showBoard();
	}

}
