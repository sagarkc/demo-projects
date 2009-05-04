

public class Board {
	// instance variables declared below:

	private char[][] theBoard = null;
	private int ROWS, COLUMNS;
	private char init_char;
/*
	// no arg constructor -- not used in this project
	// but it is a good practice to include one
	public Board() {
		this(3,3);
	}
*/
	public Board(int rows, int cols) {
		this(rows, cols, BoardManager.BLANK);
	}

	
	// multiple arg constructor for this project
	public Board(int rows, int cols, char init_board) {
		if(rows != cols){
			throw new IllegalArgumentException("The Tic-Tac_Toe game board should be a square matrix." +
					"\n ROWS = " + rows + " " +
							"COLS = " + cols);
		}
		theBoard = new char[rows][cols];
		this.ROWS = rows;
		this.COLUMNS = cols;
		this.init_char = init_board;
	}

	// return the array board
	public char[][] getTheBoard() {
		return theBoard;
	}

	// initialize the board array used to
	// hold the players symbols
	// Hint: you can use 2 for-loops (nested) to
	// do this -- use the ROWS,COLUMNS and init_char
	public void initTheBoard() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				theBoard[i][j] = init_char;
			}
		}
	}

	// set the player_symbol value at the row and column
	// position indicated by the method paraments
	public boolean set_APosition(int[] x_y_position, char symbol) {
		int row = x_y_position[0];
		int column = x_y_position[1];
		boolean found = false;
		
		if(theBoard[row][column] == BoardManager.BLANK){
			theBoard[row][column] = symbol;
			found = true;
		}
		
		return found;
	}
	
	public boolean hasOpenPosition(){
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if(theBoard[i][j] == init_char){
					return true;
				}
			}
		}
		return false;
	}
	
	public String toString(){
		String results = "";
		
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if(j != COLUMNS-1){
					results += theBoard[i][j] + " | ";
				}else{
					results += theBoard[i][j];
				}
			}
			results += "\n";
		}
		
		return results;
	}
}
