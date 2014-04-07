/**
 * 
 */
package com.gs.games.mysticsquare.core.model;

import com.gs.games.mysticsquare.core.ValueProvider;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Board<T> {

	public final int M;
	public final int N;
	public final int BOX_COUNT;
	
	private final ValueProvider<T> valueProvider;
	private final Box<T>[][] boxes;
	
	public Board(final ValueProvider<T> valueProvider) {
		this(4, 4, valueProvider);
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Board(final int m, final int n, final ValueProvider<T> valueProvider) {
		this.M = m;
		this.N = n;
		this.BOX_COUNT = M * N;
		this.boxes = new Box[M][N];
		this.valueProvider = valueProvider;
		initBoard();
	}

	/**
	 * 
	 */
	private void initBoard() {
		for(int i = 0; i < M; i++){
			for(int j = 0; i < N; j++){
				Box<T> box = new Box<T>(i, j);
				if(i != M-1 && j != N-1){
					Square<T> square = new Square<T>(i, j);
					square.setValue(valueProvider.getValueAt(i * j));
					square.setContainerBox(box);
					box.setSquare(square);
				}
				boxes[i][j] = box;
			}
		}
		for(int i = 0; i < M; i++){
			for(int j = 0; i < N; j++){
				Box<T> box = boxes[i][j];
				addNaighbour(box);
			}
		}
	}

	/**
	 * @return the valueProvider
	 */
	public ValueProvider<T> getValueProvider() {
		return valueProvider;
	}

	/**
	 * @return the m
	 */
	public int getM() {
		return M;
	}

	/**
	 * @return the n
	 */
	public int getN() {
		return N;
	}

	/**
	 * @return the bOX_COUNT
	 */
	public int getBOX_COUNT() {
		return BOX_COUNT;
	}

	/**
	 * @return the boxes
	 */
	public Box<T>[][] getBoxes() {
		return boxes;
	}

	protected void addNaighbour(final Box<T> box){
		if(null == box){
			return;
		}
		
		if(box.getX() == 0 && box.getY() == 0){
			box.setRightBox(boxes[box.getX()+1][box.getY()]);
			box.setBottomBox(boxes[box.getX()][box.getY()+1]);
		} 
		if(box.getX() == 0 && box.getY() > 0 && box.getY() < 4){
			box.setTopBox(boxes[box.getX()][box.getY()-1]);
			box.setRightBox(boxes[box.getX()+1][box.getY()]);
			box.setBottomBox(boxes[box.getX()][box.getY()+1]);
		} 
		if(box.getX() == 0 && box.getY() == 4){
			box.setTopBox(boxes[box.getX()][box.getY()-1]);
			box.setRightBox(boxes[box.getX()+1][box.getY()]);
		}
		
		
	}
	
}
