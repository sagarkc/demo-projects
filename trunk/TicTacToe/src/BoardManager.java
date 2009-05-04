import javax.swing.JOptionPane;

public class BoardManager {
	// instance variables and object references
	// declared below:
	private Board gameBoard = null;
	private Player xPlayer = null;
	private Player oPlayer = null;

	private boolean isBoardFull = false;
	private boolean hasXWon = false;
	private boolean hasOWon = false;

	public static final char BLANK = ' ';
	public static final char O = 'O';
	public static final char X = 'X';
	public static final int ROWS = 3;
	public static final int COLUMNS = 3;

	// no argument constructor
	public BoardManager() {
		// create the the Board object --
		// see Board constructor for required args
		try{
			gameBoard = new Board(ROWS, COLUMNS, BLANK);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception ...",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		// create a X and an O player

		xPlayer = new Player(X);
		oPlayer = new Player(O);
		
	}

	public static void main(String[] args) {
		BoardManager boardmanager = new BoardManager();
		boardmanager.play();
		System.exit(0);
	}

	// need to complete this method
	private void setXHasWon(boolean result) {
		hasXWon = result;
	}

	// need to complete this method
	private void setOHasWon(boolean result) {
		hasOWon = result;
	}

	// need to complete this method
	private void setBoardIsFull(boolean result) {
		isBoardFull = result;
	}

	// need to complete this method
	public void initTheGame() {
		// setBoardIsFull to false
		setBoardIsFull(false);

		// setOHasWon to false
		setOHasWon(false);

		// setXHasWon to false
		setXHasWon(false);

		// initTheBoard
		// Hint: you need to use a gameBoard
		// method to do this
		gameBoard.initTheBoard();
	}

	// need to complete this method
	public void play() {
		// you need to initialize the game;
		// you have a method to do this
		initTheGame();

		// create an array to hold the players board selection
		int[] x_y_position = new int[2];

		// loop until 1 of 3 things happens:
		// 1. O wins
		// 2. X wins
		// 3. The board is full -- a tie
		while (true) {
			boolean finished = false;
			while (true) {
				// ask o to select a position
				x_y_position = oPlayer.request_Board_Position();
				// check if position is open
				boolean isPosOpen = gameBoard.set_APosition(x_y_position, oPlayer.getPlayerSymbol());
				// break if position is open
				if(isPosOpen)
					break;
			}

			// check to see if the game is finished
			if(hasOWon())
				finished = true;
			else if(hasXWon())
				finished = true;
			else if(isBoardFull())
				finished = true;
			// if so, break out of the loop
			if(finished)
				break;
			
			while (true) {
				// ask x to select a position
				x_y_position = xPlayer.request_Board_Position();
				// check if position is open
				boolean isPosOpen = gameBoard.set_APosition(x_y_position, xPlayer.getPlayerSymbol());
				// break if position is open
				if(isPosOpen)
					break;

			}
			// check to see if the game is finished
			if(hasOWon())
				finished = true;
			else if(hasXWon())
				finished = true;
			else if(isBoardFull())
				finished = true;
			// if so, break out of the loop
			if(finished)
				break;
		}

		// show results of the game;
		// you have a method to do this
		showResults();
	}

	// determine if the board is full
	public boolean isBoardFull() {
		boolean board_is_full = false;

		// you need access to the board array;
		// the gameBoard reference has a method
		// that can return the board array
		// Hint: you will probably want to create
		// a local copy of the the game board array
		// to examine the contents.
		// !!!!Remember to setBoardIsFull to true if it is!!
		if(gameBoard.hasOpenPosition() == false){
			// call setBoardIsFull
			setBoardIsFull(true);
			// set board_is_full
			board_is_full = true;
		}else{
			// call setBoardIsFull
			setBoardIsFull(false);
			// set board_is_full
			board_is_full = false;
		}
		return board_is_full;
	}

	// determine if O has won
	public boolean hasOWon() {
		boolean O_has_won = evaluateForPlayer(oPlayer);

		// you need access to the board array
		// the gameBoard reference has a method
		// that can return the board array
		// Hint: you will probably want to create
		// a local copy of the the game board array
		// to examine the contents.
		// !!!!Remember to setOHasWon to true if O won !!

		// call setOHasWon
		// set O_has_won

		return O_has_won;
	}

	// determine if X won
	public boolean hasXWon() {
		boolean X_has_Won = evaluateForPlayer(xPlayer);

		// you need access to the board array
		// the gameBoard reference has a method
		// that can return the board array
		// Hint: you will probably want to create
		// a local copy of the the game board array
		// to examine the contents.
		// !!!!Remember to setXHasWon to true if X won !!

		// call setXHasWon
		// set X_has_won

		return X_has_Won;
	}

	// show the results of the game
	public void showResults() {
		String results = gameBoard.toString();

		// set the result String to identify the
		// the results of the game

		// you need access to the board array
		// the gameBoard reference has a method
		// that can return the board array
		// Hint: you will probably want to create
		// a local copy of the the game board array
		// to examine the contents.

		// display the contents of the board
		// Hint: you can use 2 for-loops (nested) to
		// do this
		
		if(hasOWon())
			results += "\n O has won";
		else if(hasXWon())
			results += "\n X has won";
		else if(isBoardFull())
			results += "\n Tie";
		
		JOptionPane.showMessageDialog(null, results, "Results of the Game",
				JOptionPane.INFORMATION_MESSAGE);

	}
	
	private boolean evaluateForPlayer(Player player) {
		int i,j;
		char symbol = player.getPlayerSymbol();
		int dimension = ROWS;
		char[][] board = gameBoard.getTheBoard();
		for (i = 0; i < dimension; ++i) {
			for (j = 0; j < dimension; ++j)
				if (board[i][j] != symbol)
					break;
			if (j == dimension)
				return (true);
			for (j = 0; j < dimension; ++j)
				if (board[j][i] != symbol)
					break;
			if (j == dimension)
				return (true);
		}
		// Now check diagnols
		for (i = 0; i < dimension; ++i)
			if (board[i][i] != symbol)
				break;
		if (i == dimension)
			return (true);
		for (i = 0; i < dimension; ++i)
			if (board[i][dimension - i - 1] != symbol)
				break;
		if (i == dimension)
			return (true);
		return (false);

	}
}
