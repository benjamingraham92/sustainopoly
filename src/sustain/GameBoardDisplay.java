package sustain;
 
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class GameBoardDisplay {
 
	private static final int WIDTH_AMOUNT = 19;
	private String[][][] board;

	private Map<Integer, String> indicesToCoords;
	public static void main(String[] args) {
		// y axis, x axis, z axis
		

		GameBoardDisplay mgb = new GameBoardDisplay();
//		mgb.testFunction();
		
		
		// representative board area
//		
//		String start = ""
		String areaString = "neutral, World Climate.X. Change Summit, Go - Collect $200";
		
		String[] stringsToPopulate = new String[12];
		
		for (int i = 0; i < stringsToPopulate.length; i++) {
			stringsToPopulate[i] = areaString;
		}
		
		mgb.populateBoard(stringsToPopulate);		
		
		
		
		mgb.showBoard();
	}
 
	protected void populateBoard(String[] stringsToPopulate) {
		for (int i = 0 ; i < stringsToPopulate.length ; i++) {
			String coordString = this.indicesToCoords.get(i);
			String[] splitCoord = coordString.split(",");

			int y = Integer.valueOf(splitCoord[0]);
			int x = Integer.valueOf(splitCoord[1]);
			this.populateSquareWithBoardInfo(stringsToPopulate[i],y ,x);
		}
	}
 
	public GameBoardDisplay() {
		super();
		String[][][] board = new String[4][4][12];
		this.board = board;
		Map<Integer, String> indicesToCoordsParam = new HashMap<Integer, String>();
		indicesToCoordsParam.put(0,"3,3");
		indicesToCoordsParam.put(1,"3,2");
		indicesToCoordsParam.put(2,"3,1");
		indicesToCoordsParam.put(3,"3,0");
		indicesToCoordsParam.put(4,"2,0");
		indicesToCoordsParam.put(5, "1,0");
		indicesToCoordsParam.put(6, "0,0");
		indicesToCoordsParam.put(7,"0,1");
		indicesToCoordsParam.put(8,"0,2");
		indicesToCoordsParam.put(9,"0,3");
		indicesToCoordsParam.put(10,"1,3");
		indicesToCoordsParam.put(11,"2,3");


		this.indicesToCoords = indicesToCoordsParam;
	}
	public void testFunction() {
		// 8 accross
		// Down square
				board[0][0][1] = "   GO   ";
				board[0][0][3] = "COLLECT ";
				board[0][0][4] = " 1500$  ";
				board[1][0][4] = " hey ";
	}
 
	//
	public void populateSquareWithBoardInfo(String areaString, int y, int x) {
		// 19 by 12
		String[] toPopulate = new String[12];

		// areaString
		// comma separated variable
		// area type = neutral or purchasable
		// if neutral
		// of area name 
		// what does this square do

		String [] splitAreaString = areaString.split(",");
		// type should conditionally render
		String type = splitAreaString[0];

		if(type.equals("neutral")) {
			// handle name splitting and printing
			String name = splitAreaString[1];
			String nameFirstLine;
			String nameSecondLine;
			if(name.length() > WIDTH_AMOUNT) {
				String[] splitName = name.split(".X.");
				nameFirstLine = splitName[0];
				nameSecondLine = splitName[1];
				nameFirstLine = sanitizeString(nameFirstLine, WIDTH_AMOUNT);
				nameSecondLine = sanitizeString(nameSecondLine, WIDTH_AMOUNT);
				toPopulate[4] = nameFirstLine;
				toPopulate[5] = nameSecondLine;
			} else {
				name = sanitizeString(name, WIDTH_AMOUNT);
				toPopulate[4] = name;
			}

			// handle functions splitting and pritning
			String functions = splitAreaString[2];
			functions = sanitizeString(functions, WIDTH_AMOUNT);
			toPopulate[7] = functions;
			
			
			// show players in the position
			
			// remember to handle this for the other case...
			
			if(splitAreaString.length > 3) {
				String playerColours = splitAreaString[3];
				
		        char targetChar = ' ';
		        
		        long count = playerColours.chars().filter(ch -> ch == targetChar).count();
				
				int numberOfPlayersInSquare = (int) (count);
				
				playerColours = sanitizeString(playerColours, (WIDTH_AMOUNT + 10*numberOfPlayersInSquare) );
				
				toPopulate[11] = playerColours;
			}
			

		} else {
			// handle name splitting and printing
						String name = splitAreaString[1];
						String nameFirstLine;
						String nameSecondLine;
						if(name.length() > WIDTH_AMOUNT) {
							String[] splitName = name.split(".X.");
							nameFirstLine = splitName[0];
							nameSecondLine = splitName[1];
							nameFirstLine = sanitizeString(nameFirstLine, WIDTH_AMOUNT);
							nameSecondLine = sanitizeString(nameSecondLine, WIDTH_AMOUNT);
							toPopulate[4] = nameFirstLine;
							toPopulate[5] = nameSecondLine;
						} else {
							name = sanitizeString(name, WIDTH_AMOUNT);
							toPopulate[4] = name;
						}

						// handle functions splitting and pritning
						String owner = splitAreaString[2];
						
						if(owner.contains("Not Owned")) {
							owner = sanitizeString(owner, WIDTH_AMOUNT);
						} else {
							owner = sanitizeString(owner, WIDTH_AMOUNT+9);
							
						}
						toPopulate[7] = owner;
						
						// current rent 
						String rent = splitAreaString[3];
						rent = sanitizeString(rent, WIDTH_AMOUNT);
						
						toPopulate[8] = rent;
						
						// development level
						
						String developmentLevel = splitAreaString[4];
						developmentLevel = sanitizeString(developmentLevel, WIDTH_AMOUNT);
						toPopulate[9] = developmentLevel;
						
						// purchase price
						String purchasePrice = splitAreaString[5];
						purchasePrice = sanitizeString(purchasePrice, WIDTH_AMOUNT);
						toPopulate[10] = purchasePrice;
						
						// show players in the position
						
						// remember to handle this for the other case...
						
						
						if(splitAreaString.length > 6) {
							String playerColours = splitAreaString[6];
							
					        char targetChar = ' ';
					        
					        long count = playerColours.chars().filter(ch -> ch == targetChar).count();

							
							int numberOfPlayersInSquare = (int) (count);
							
							playerColours = sanitizeString(playerColours, (WIDTH_AMOUNT + 10*numberOfPlayersInSquare) );
							
							toPopulate[11] = playerColours;
						}
						
						
						
		}
		
	
		
		// populate the position
		this.board[y][x] = toPopulate;
	}
 
	private String sanitizeString(String originalString, int desiredLength) {

		// If the original string is longer than the desired length, truncate it
        if (originalString.length() > desiredLength) {
            originalString = originalString.substring(0, desiredLength);
            return originalString;
}
 
        // Format the string to the desired length with additional spaces padded with white space
String formattedString = String.format("%-" + desiredLength + "s", originalString);
 
		
		return formattedString;
	}
 
	public void showBoard() {
		// 19 character width
		String boardWidthString = "                   ";
		String fullLineString = " ";

		for (int i = 0 ; i < 4 ; i++) {
			fullLineString += boardWidthString;
			fullLineString += " ";

		}
		fullLineString = fullLineString.replace(" ", "x");

        String fullLine = fullLineString;
//		String fullLine = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
		for (int i = 0; i < 4; i++) {
			System.out.println(fullLine);
				for (int j = 0; j < 12; j++) {
					for (int k = 0; k < 4; k++) {
					if (board[i][k][j] == null) {
						board[i][k][j] = boardWidthString;
//						board[i][k][j] = " ";
					}
 
					String s = board[i][k][j];
 
					System.out.print("x" + s + ""); // The issue is the downwards x's some of these need removed at
													// certain points
				}
				System.out.println("x");
			}
		}
		System.out.println(fullLine);
	}
 
}