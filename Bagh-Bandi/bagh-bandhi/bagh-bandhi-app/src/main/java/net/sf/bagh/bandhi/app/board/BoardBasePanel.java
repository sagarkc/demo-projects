/**
 * 
 */
package net.sf.bagh.bandhi.app.board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.Border;

import net.sf.bagh.bandhi.core.model.Board;
import net.sf.bagh.bandhi.core.model.Box;
import net.sf.bagh.bandhi.core.model.Goat;
import net.sf.bagh.bandhi.core.model.Human;
import net.sf.bagh.bandhi.core.model.Tiger;
import net.sf.bagh.bandhi.core.model.Animal.AnimalType;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public class BoardBasePanel extends JPanel implements MouseMotionListener, MouseListener{

	private final Border boardBorder = BorderFactory.createCompoundBorder(BorderFactory
			.createLineBorder(new Color(0, 102, 102), 2), BorderFactory
			.createCompoundBorder(BorderFactory.createMatteBorder(10, 10,
					10, 10, new ImageIcon(getClass().getResource("/images/wood-plank-small.jpg"))), BorderFactory
					.createLineBorder(new Color(0, 153, 51))));
	private final Dimension boardSize = new Dimension(690, 690);
	private Color boardBGColor = Color.BLACK;
	
	private static final int LEFT_MARGIN = 20;
	private static final int RIGHT_MARGIN = 20;
	private static final int MAX_HEIGHT = 650;
	private static final int MAX_WIDTH = 650;
	private static final int BOX_WIDTH = 130;
	// Margin  
	
	private UIBoard gameBoard ;
	
	private UiBox previousSelectedBox;
	/**
	 * 
	 */
	public BoardBasePanel() {
		addMouseListener(this);
		addMouseMotionListener(this);
		initComponents();
		initGame();
	}

	/**
	 * 
	 * 
	 */
	private void initGame() {
		Human firstPlayer = new Human("A", AnimalType.TIGER);
		Human secondPlayer = new Human("B", AnimalType.GOAT);
		UITiger[] tigers = new UITiger[2];
		tigers[0] = new UITiger("T1", 1);
		tigers[1] = new UITiger("T2", 2);
		
		UIGoat[] goats = new UIGoat[20];
		for (int i = 0; i < goats.length; i++) {
			goats[i] = new UIGoat("G", i+1);
		}
		gameBoard = new UIBoard(tigers, goats);
		
	}

	private void initComponents() {
		setBackground(boardBGColor);
		setMaximumSize(boardSize);
		setMinimumSize(boardSize);
		setPreferredSize(boardSize);
		setLayout(null);
		setBorder(boardBorder);
	}
	
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(new ImageIcon(getClass().getResource(
				"/images/wood-plank.jpg")).getImage(), 20, 20, 650, 650, null);
		drawGrid(g2d);
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
		x2 = x1 + MAX_WIDTH;
		y2 = y1;
		g.setColor(Color.GREEN);
		for (int i = 0; i <= 5; i++) {
			g.drawLine(x1, y1+(i * BOX_WIDTH), x2, y2+(i * BOX_WIDTH));
		}
		
		x1 = x2 = LEFT_MARGIN;
		y1 = x1 ;
		y2 = y1 + MAX_WIDTH;
		for (int j = 0; j <= 5; j++) {
			g.drawLine(x1+(j * BOX_WIDTH), y1, x2+(j * BOX_WIDTH), y2);
		}
		
		g.setColor(Color.PINK);
		g.drawRect(690, 20, 100, 650);
		
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
		if(x >= 20 && x <= 670 && y >= 20 && y <= 670){
			repaint();
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(x >= 20 && x <= 670 && y >= 20 && y <= 670){
			paintLocation(e.getX(), e.getY());
		}
		
		final UiBox box = gameBoard.getSelectedBox(x, y);
		if(null != box && box.hasAnimal()){
			if(null != previousSelectedBox){
				previousSelectedBox.setBgImage(new ImageIcon(getClass().getResource(
						"/images/box_grey-128x128.png")) );
				List<Box> previousNeighbours = previousSelectedBox.getEmptyNeighbours();
				for (Box neighbour : previousNeighbours) {
					((UiBox)neighbour).setBgImage(new ImageIcon(getClass().getResource(
							"/images/box_grey-128x128.png")) );
					((UiBox)neighbour).draw(getGraphics());
				}
				previousSelectedBox.draw(getGraphics());
			}
			box.setBgImage(new ImageIcon(getClass().getResource(
					"/images/box_orange-128x128.png")) );
			List<Box> selectedNeighbours = box.getEmptyNeighbours();
			if(AnimalType.GOAT == box.getAnimalType()){
				for (Box neighbour : selectedNeighbours) {
					((UiBox)neighbour).setBgImage(new ImageIcon(getClass().getResource(
							"/images/box_green-128x128.png")) );
					((UiBox)neighbour).draw(getGraphics());
				}
				
				/*box.setBgImage(new ImageIcon(getClass().getResource(
						"/images/box_green-128x128.png")) );*/
			} else if(AnimalType.TIGER == box.getAnimalType()){
				for (Box neighbour : selectedNeighbours) {
					((UiBox)neighbour).setBgImage(new ImageIcon(getClass().getResource(
							"/images/box_red-128x128.png")) );
					((UiBox)neighbour).draw(getGraphics());
				}
				/*box.setBgImage(new ImageIcon(getClass().getResource(
						"/images/box_orange-128x128.png")) );*/
			} 
			box.draw(getGraphics());
			previousSelectedBox = box;
		}
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
