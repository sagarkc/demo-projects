/**
 * 
 */
package net.sf.bagh.bandhi.app.board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import net.sf.bagh.bandhi.AppConstants;
import net.sf.bagh.bandhi.GameStatusEnum;
import net.sf.bagh.bandhi.app.AnimalSizeEnum;
import net.sf.bagh.bandhi.app.BoxSizeEnum;
import net.sf.bagh.bandhi.app.GameStatusChangeEvent;
import net.sf.bagh.bandhi.app.GameStatusChangeEventManager;
import net.sf.bagh.bandhi.app.GreenBoxImageEnum;
import net.sf.bagh.bandhi.app.GreyBoxImageEnum;
import net.sf.bagh.bandhi.app.GridSizeEnum;
import net.sf.bagh.bandhi.app.OrangeBoxImageEnum;
import net.sf.bagh.bandhi.app.RedBoxImageEnum;
import net.sf.bagh.bandhi.core.GameEngine;
import net.sf.bagh.bandhi.core.activity.Captureable;
import net.sf.bagh.bandhi.core.model.Animal.AnimalType;
import net.sf.bagh.bandhi.core.model.Box;
import net.sf.bagh.bandhi.core.model.PathOfMove;
import net.sf.bagh.bandhi.core.model.Tiger;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public class BoardBasePanel extends JPanel implements MouseMotionListener, MouseListener{

	/**
	 * serialVersionUID = -3821322838306025737L;
	 */
	private static final long serialVersionUID = -3821322838306025737L;
	
	private static final GameEngine gameEngine = GameEngine.getEngine();
	
	private static final int LEFT_MARGIN = 20;
	private static final int RIGHT_MARGIN = 20;
	
	private static final int BOARD_WIDTH = 820;
	private static final int BOARD_HEIGHT = 690;
	
	//private static final int GRID_HEIGHT = 650;
	//private static final int GRID_WIDTH = 650;
	
	private static final int INFO_BOX_WIDTH = 100;
	private static final int INFO_BOX_HEIGHT = 650;
	private static final int INFO_BOX_X = 690;
	private static final int INFO_BOX_Y = 20;
	private static final int TEXT_HEIGHT = 20;
	
	private final Border boardBorder = BorderFactory.createCompoundBorder(BorderFactory
			.createLineBorder(new Color(0, 102, 102), 2), BorderFactory
			.createCompoundBorder(BorderFactory.createMatteBorder(10, 10,
					10, 10, new ImageIcon(getClass().getResource("/images/wood-plank-small.jpg"))), BorderFactory
					.createLineBorder(new Color(0, 153, 51))));
	public static final Dimension boardSize = new Dimension(
			GridSizeEnum.getValue(UIBoard.sizeFactorEnum).getWidth()+LEFT_MARGIN*2, 
			GridSizeEnum.getValue(UIBoard.sizeFactorEnum).getHeight()+LEFT_MARGIN*2);
	private static GameStatusChangeEventManager statusChangeEventManager = GameStatusChangeEventManager.getInstance();
	
	private Color boardBGColor = Color.BLACK;
	private UIBoard gameBoard ;
	private UiBox previousSelectedBox;
	private boolean canBeMoved = false;
	
	
	
	
	/**
	 * 
	 */
	public BoardBasePanel() {
		addMouseListener(this);
		addMouseMotionListener(this);
		initComponents();
	}


	private void initComponents() {
		setBackground(boardBGColor);
		setMaximumSize(boardSize);
		setMinimumSize(boardSize);
		setPreferredSize(boardSize);
		setLayout(null);
		setOpaque(true);
		setDoubleBuffered(true);
		setBorder(boardBorder);
	}
	
	/**
	 * 
	 */
	public void updateSize() {
		boardSize.width = GridSizeEnum.getValue(UIBoard.sizeFactorEnum).getWidth()+LEFT_MARGIN*2;
		boardSize.height = GridSizeEnum.getValue(UIBoard.sizeFactorEnum).getHeight()+LEFT_MARGIN*2;
		setMaximumSize(boardSize);
		setMinimumSize(boardSize);
		setPreferredSize(boardSize);
	}
	
	/**
	 * @return the gameBoard
	 */
	public UIBoard getGameBoard() {
		return gameBoard;
	}


	/**
	 * @param gameBoard the gameBoard to set
	 */
	public void setGameBoard(UIBoard gameBoard) {
		this.gameBoard = gameBoard;
	}


	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		RenderingHints hints = new RenderingHints(
			    RenderingHints.KEY_ANTIALIASING,
			    RenderingHints.VALUE_ANTIALIAS_ON);
			hints.add(new RenderingHints(
			            RenderingHints.KEY_RENDERING, 
			RenderingHints.VALUE_RENDER_QUALITY));
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHints(hints);
		super.paint(g2d);
		g2d.drawImage(new ImageIcon(getClass().getResource(
				"/images/wood-plank.jpg")).getImage(), LEFT_MARGIN, RIGHT_MARGIN, 
				GridSizeEnum.getValue(UIBoard.sizeFactorEnum).getWidth(), 
				GridSizeEnum.getValue(UIBoard.sizeFactorEnum).getHeight(), null);
		//drawGrid(g2d);
		drawBoard(g2d);
	}
	
	/**
	 * @param g2d
	 */
	private void drawBoard(Graphics2D g2d) {
		if(null == gameBoard)
			return;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				final UiBox box = (UiBox) gameBoard.getBoxAt(i, j);
				if(null != box){
					box.draw(g2d);
				}
			}
		}
	}

	private void drawGrid(Graphics2D g){
		int x1, y1, x2, y2;
		x1 = y1 = LEFT_MARGIN;
		x2 = x1 + GridSizeEnum.getValue(UIBoard.sizeFactorEnum).getWidth();
		y2 = y1;
		g.setColor(Color.BLACK);
		//g.setColor(new Color(0, 153, 51));
		for (int i = 0; i <= 5; i++) {
			g.drawLine(x1, y1+(i * BoxSizeEnum.getValue(UIBoard.sizeFactorEnum).getHeight()), x2, 
					y2+(i * BoxSizeEnum.getValue(UIBoard.sizeFactorEnum).getHeight()));
		}
		
		x1 = x2 = LEFT_MARGIN;
		y1 = x1 ;
		y2 = y1 + GridSizeEnum.getValue(UIBoard.sizeFactorEnum).getWidth();
		for (int j = 0; j <= 5; j++) {
			g.drawLine(x1+(j * BoxSizeEnum.getValue(UIBoard.sizeFactorEnum).getWidth()), y1, 
					x2+(j * BoxSizeEnum.getValue(UIBoard.sizeFactorEnum).getWidth()), y2);
		}
		
		g.setColor(Color.PINK);
		g.drawRect(INFO_BOX_X, INFO_BOX_Y, INFO_BOX_WIDTH, INFO_BOX_HEIGHT);
		
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Tigers Blocked", INFO_BOX_X+5, INFO_BOX_Y+TEXT_HEIGHT );
		g.drawRect(INFO_BOX_X+30, INFO_BOX_Y+(int)(TEXT_HEIGHT*1.5)+2, INFO_BOX_WIDTH-35, TEXT_HEIGHT+10);
		g.drawString("Goats Killed", INFO_BOX_X+5, INFO_BOX_Y+(int)(TEXT_HEIGHT*4)+2);
		g.drawRect(INFO_BOX_X+30, INFO_BOX_Y+(int)(TEXT_HEIGHT*4.5)+2, INFO_BOX_WIDTH-35, TEXT_HEIGHT+10);
		
		g.setColor(Color.PINK);
		g.drawLine(INFO_BOX_X, INFO_BOX_Y+(int)(TEXT_HEIGHT*6)+5, 
				INFO_BOX_X+INFO_BOX_WIDTH, INFO_BOX_Y+(int)(TEXT_HEIGHT*6)+5
				);
	}
	
	void paintLocation(int x, int y){
		Graphics2D g = (Graphics2D) getGraphics();
		g.setColor(Color.WHITE);
		g.drawString("X: " + x, 700, 40);
		g.drawString("Y: " + y, 700, 60);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent e) {
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		/*if(x >= 20 && x <= 670 && y >= 20 && y <= 670){
			repaint();
		}*/
		//repaint();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(MouseEvent.BUTTON1 == e.getButton()){
			makeMove(x, y);
		} else if(MouseEvent.BUTTON3 == e.getButton()){
			// remove selection
		}
	}


	/**
	 * @param x
	 * @param y
	 */
	public void makeMove(int x, int y) {
		final UiBox box = gameBoard.getSelectedBox(x, y);
		// select the box
		if(null != box && (box.hasAnimal() && box.getAnimalType() == gameEngine.getCurrentPlayer().getAnimalType())){
			selectBox(box);
			return;
		}
		// clicked on an empty box 
		else if(null != box && null != previousSelectedBox
				&& box.isEmpty() && !box.equals(previousSelectedBox)){
			AnimalType winer = gameBoard.evalute();
			// if the animal can be moved to this box
			if(gameBoard.canBeMoved(previousSelectedBox, box) && AnimalType.NONE == winer){
				// do move
				boolean moved = gameBoard.move(previousSelectedBox, box);
				if(moved){
					firePropertyChange("MOVE_SUCCESS", previousSelectedBox, box);
					firePropertyChange("GOAT_COUNT", null, 5%3);
					winer = gameBoard.evalute();
					previousSelectedBox.draw(getGraphics());
					previousSelectedBox.setBgImage(GreyBoxImageEnum.getValue(UIBoard.sizeFactorEnum).getImage());
					previousSelectedBox.setDefaultBackgroundColor();
					box.setBgImage(GreyBoxImageEnum.getValue(UIBoard.sizeFactorEnum).getImage());
					box.setDefaultBackgroundColor();
					if(null != previousSelectedBox){
						List<Box> previousNeighbours = previousSelectedBox.getEmptyNeighbours();
						for (Box neighbour : previousNeighbours) {
							((UiBox)neighbour).setBgImage(GreyBoxImageEnum.getValue(UIBoard.sizeFactorEnum).getImage());
							((UiBox)neighbour).setDefaultBackgroundColor();
							((UiBox)neighbour).draw(getGraphics());
						}
					}
					previousSelectedBox.draw(getGraphics());
					box.draw(getGraphics());
					
					previousSelectedBox = null;
					boolean hasNextMove = false;
					if(AnimalType.TIGER == box.getAnimalType()){
						Tiger tiger = (Tiger) box.getAnimal();
						PathOfMove lastMove = gameEngine.getLastMove(tiger);
						if(null != lastMove && null != lastMove.getCapturedAnimal() 
								&& tiger.hasCaptureAnyGoat(box, lastMove) ){
							hasNextMove = true;
						} else {
							hasNextMove = false;
						}
					} else {
						hasNextMove = false;
					}
					if(!hasNextMove){
						gameEngine.shiftNextPlayer();
					}
					GameStatusChangeEvent event = new GameStatusChangeEvent(
							this, GameStatusEnum.ANIMAL_MOVED, gameEngine.getLastMove());
					statusChangeEventManager.fireGameStatusChangeEvent(event);
					//drawCapturedAnimals(gameBoard.getCapturedGoats());
					if(AnimalType.NONE != winer)
						JOptionPane.showMessageDialog(this, "Winer is : " + winer);
				} else {
					JOptionPane.showMessageDialog(this, "Move Failed");
				}
			} else {
				if(AnimalType.NONE != winer)
					JOptionPane.showMessageDialog(this, "Winer is : " + winer);
				else 
					JOptionPane.showMessageDialog(this, "invalid move");
			}
		}
		// clicked on board
		else if(box == null ) {
			if(null != previousSelectedBox){
				previousSelectedBox.setBgImage(GreyBoxImageEnum.getValue(UIBoard.sizeFactorEnum).getImage());
				List<Box> previousNeighbours = previousSelectedBox.getEmptyNeighbours();
				for (Box neighbour : previousNeighbours) {
					((UiBox)neighbour).setBgImage(GreyBoxImageEnum.getValue(UIBoard.sizeFactorEnum).getImage());
					((UiBox)neighbour).draw(getGraphics());
				}
				previousSelectedBox.draw(getGraphics());
			}
			previousSelectedBox = null;
		}
	}


	/**
	 * @param box
	 */
	public void selectBox(final UiBox box) {
		if(null != previousSelectedBox){
			previousSelectedBox.setBgImage(
				GreyBoxImageEnum.getValue(UIBoard.sizeFactorEnum).getImage()	
			);
			previousSelectedBox.setDefaultBackgroundColor();
			List<Box> previousNeighbours = previousSelectedBox.getEmptyNeighbours();
			for (Box neighbour : previousNeighbours) {
				((UiBox)neighbour).setBgImage(GreyBoxImageEnum.getValue(UIBoard.sizeFactorEnum).getImage());
				((UiBox)neighbour).setDefaultBackgroundColor();
				((UiBox)neighbour).draw(getGraphics());
			}
			previousSelectedBox.draw(getGraphics());
		}
		box.setBgImage(OrangeBoxImageEnum.getValue(UIBoard.sizeFactorEnum).getImage());
		box.setBackgroundColor(AppConstants.SELECTED_BACKGROUND);
		List<Box> selectedNeighbours = box.getEmptyNeighbours();
		if(AnimalType.GOAT == box.getAnimalType()){
			for (Box neighbour : selectedNeighbours) {
				((UiBox)neighbour).setBgImage(GreenBoxImageEnum.getValue(UIBoard.sizeFactorEnum).getImage());
				((UiBox)neighbour).setBackgroundColor(AppConstants.GOAT_MOVABLE_BACKGROUND);
				((UiBox)neighbour).draw(getGraphics());
			}
		} else if(AnimalType.TIGER == box.getAnimalType()){
			for (Box neighbour : selectedNeighbours) {
				((UiBox)neighbour).setBgImage(RedBoxImageEnum.getValue(UIBoard.sizeFactorEnum).getImage());
				((UiBox)neighbour).setBackgroundColor(AppConstants.TIGER_MOVABLE_BACKGROUND);
				((UiBox)neighbour).draw(getGraphics());
			}
		} 
		box.draw(getGraphics());
		previousSelectedBox = box;
	}

	private void drawCapturedAnimals(List<Captureable> capturedGoats) {
		Graphics2D g = (Graphics2D) getGraphics();
		List<Captureable> animals = gameBoard.getCapturedGoats();
		if(null != animals){
			for (int i = 0; i < animals.size(); i++) {
				if(null != animals.get(i) && animals.get(i) instanceof UIGoat){
					UIGoat goat = (UIGoat) animals.get(i);
					goat.setX(INFO_BOX_X + (INFO_BOX_WIDTH / 2) - AnimalSizeEnum.getValue(UIBoard.sizeFactorEnum).getWidth());
					goat.setY(INFO_BOX_Y + (INFO_BOX_HEIGHT - ((i+1) * AnimalSizeEnum.getValue(UIBoard.sizeFactorEnum).getHeight()) ) - 25);
					goat.draw(g);
				}
			}
			g.setColor(Color.BLACK);
			g.fillRect(INFO_BOX_X+30+1, INFO_BOX_Y+(int)(TEXT_HEIGHT*4.5)+3, INFO_BOX_WIDTH-36, TEXT_HEIGHT+9);
			g.setColor(Color.BLUE);
			g.drawString("" + animals.size(), INFO_BOX_X+35, INFO_BOX_Y+(int)(TEXT_HEIGHT*5.5)+3 );
		}
		
		
		g.setColor(Color.BLACK);
		g.fillRect(INFO_BOX_X+30+1, INFO_BOX_Y+(int)(TEXT_HEIGHT*1.5)+3, INFO_BOX_WIDTH-36, TEXT_HEIGHT+9);
		g.setColor(Color.BLUE);
		g.drawString("0", INFO_BOX_X+35, INFO_BOX_Y+(int)(TEXT_HEIGHT*2.5)+3 );
		
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	public void mouseEntered(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(x >= 20 && x <= 670 && y >= 20 && y <= 670){
			
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	public void mouseMoved(MouseEvent e) {
		
	}


	
	
	
}
