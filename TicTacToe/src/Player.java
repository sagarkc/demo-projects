import java.security.SecureRandom;

public class Player {
	
	private char playerSymbol;

	// instance variables and object references
	// declared below:
	private SecureRandom X = new SecureRandom();
	private SecureRandom Y = new SecureRandom();

	// default constructor -- not used in this project
	// but it is a good practice to include one
	public Player(char symbol) {
		this.playerSymbol = symbol;
	}

	// need to complete this method
	public int[] request_Board_Position() {
		int[] x_y_position = new int[2];

		int row_position = X.nextInt(BoardManager.ROWS);
		int col_position = Y.nextInt(BoardManager.COLUMNS);

		x_y_position[0] = row_position;
		x_y_position[1] = col_position;

		return x_y_position;
	}

	public char getPlayerSymbol() {
		return playerSymbol;
	}


	
}