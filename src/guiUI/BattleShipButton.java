package guiUI;

import entities.Ship;
import gameEngine.GameManager;
import gameEngine.HitLogic;
import gameEngine.ShipPlacer;
import guiPanels.GameBoard;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import audio.AudioManager;

public class BattleShipButton extends JButton implements GameButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8483745725905665333L;
	ImageIcon defaultImage;
	int coordinateX;
	int coordinateY;
	GameButton[][] buttonGroup;
	GameBoard board;
	Ship occupyingShip;
	int imageIndex;
	boolean occupied;
	BattleShipButton thisButton;
	boolean hit;
	int sideIndex;
	boolean targeted;
	
	
	public BattleShipButton(ImageIcon dImage,int coordx, int coordy, GameBoard board, int side){
		setMinimumSize(new Dimension(30,30));
		setPreferredSize(new Dimension(30,30));
		setMinimumSize(new Dimension(30,30));
		setMargin(new Insets(0,0,0,0));
		setBorderPainted(false);
		setOpaque(false);
		setContentAreaFilled(false);
		setVisible(true);
		defaultImage = dImage;
		setIcon(defaultImage);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		coordinateX = coordx;
		coordinateY = coordy;
		this.board = board;
		buttonGroup = board.getButtons();
		sideIndex = side;
		thisButton = this;
		targeted = false;
		
		addActionListener(new clickListener());
		addMouseListener(new mouseListener());
	}
	
	public void changeIcon(ImageIcon img){
		setIcon(img);
	}

	@Override
	public boolean isOccupied() {
		return occupied;
	}
	
	public void setOccupyingShip(Ship ship, int index){
		occupyingShip = ship;
		imageIndex = index;
		setIcon(ship.getImage(index));
		occupied = true;
	}
	
	@Override
	public Ship getOccupyingShip() {
		return occupyingShip;
	}

	public void removeOccupyingShip(){
		occupyingShip = null;
		imageIndex = 0;
		setIcon(defaultImage);
		occupied = false;
	}
	
	public void hover(boolean possible){
		Border border = new LineBorder(possible ? Color.GREEN : Color.RED);
		setBorder(border);
		setBorderPainted(true);
		
		((JButton) buttonGroup[0][coordinateX]).setBorder(new LineBorder(possible ? Color.GREEN : Color.RED));
		((JButton) buttonGroup[coordinateY][sideIndex]).setBorder(new LineBorder(possible ? Color.GREEN : Color.RED));
	}
	
	public void reset(){
		setBorder(null);
		setBorderPainted(false);
		((JButton) buttonGroup[0][coordinateX]).setBorder(new LineBorder(Color.DARK_GRAY));
		((JButton) buttonGroup[coordinateY][sideIndex]).setBorder(new LineBorder(Color.DARK_GRAY));
	}
	
	private class clickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(ShipPlacer.isPlacingShip()){
				ShipPlacer.placeShip(coordinateY, coordinateX, board);
			} else if (!board.isMainBoard() && GameManager.isGameStarted() && !targeted && !GameManager.isShotCalled()){
				targeted = true;
				HitLogic.hit(thisButton);
			} else {
				//do nothing
			}
		}
	}

	private class mouseListener extends MouseAdapter{

	
		@Override
		public void mouseEntered(MouseEvent e) {
			
			AudioManager.setSound(6);
			
			if(ShipPlacer.isPlacingShip()){
				ShipPlacer.hoverOver(coordinateY, coordinateX, board);
				
			} else {
				Border border = new LineBorder(Color.WHITE);
				((JButton) buttonGroup[0][coordinateX]).setBorder(new LineBorder(Color.WHITE));
				((JButton) buttonGroup[coordinateY][sideIndex]).setBorder(new LineBorder(Color.WHITE));
				setBorder(border);
				setBorderPainted(true);
			}
		}
	
		@Override
		public void mouseExited(MouseEvent e) {
			
			if(ShipPlacer.isPlacingShip()){
				ShipPlacer.reset(coordinateY, coordinateX, board);
			} else {
				setBorder(null);
				((JButton) buttonGroup[0][coordinateX]).setBorder(new LineBorder(Color.DARK_GRAY));
				((JButton) buttonGroup[coordinateY][sideIndex]).setBorder(new LineBorder(Color.DARK_GRAY));
				setBorderPainted(false);
			}
			
		}

	}

	public int getCoordinateX() {
		return coordinateX;
	}

	public int getCoordinateY() {
		return coordinateY;
	}

	@Override
	public void setHit() {
		hit = true;
		if(board.isMainBoard()){
			if(occupyingShip == null){
				return;
			} else {
				setIcon(occupyingShip.getImageHit(imageIndex));
				revalidate();
				repaint();
			}
		} else {
			setIcon(GameManager.getGameWindow().getTextures()[18]);
			revalidate();
			repaint();
		}
	}

	@Override
	public void setMiss() {
		setIcon(GameManager.getGameWindow().getTextures()[17]);
		revalidate();
		repaint();
	}
}