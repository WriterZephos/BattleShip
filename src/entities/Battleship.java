package entities;

import guiUI.BattleShipButton;

import javax.swing.ImageIcon;

public class Battleship extends Ship{
	
	private static ImageIcon[] icons;
	private static ImageIcon[] rotatedIcons;
	private static ImageIcon[] iconsHits;
	private static ImageIcon[] rotatedIconsHits;
	private boolean horizontal = false;
	private boolean placed = false;
	private BattleShipButton[] buttons = new BattleShipButton[4];
	private int hits = 0;
	private boolean sunk = false;
	
	static {
		
		icons = new ImageIcon[]{
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Battleship Horizontal 1.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Battleship Horizontal 2.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Battleship Horizontal 3.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Battleship Horizontal 4.png")),
				
		};
		
		rotatedIcons = new ImageIcon[]{
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Battleship Vertical 1.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Battleship Vertical 2.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Battleship Vertical 3.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Battleship Vertical 4.png")),
		};
		
		iconsHits = new ImageIcon[]{
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Battleship Horizontal 1 hit.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Battleship Horizontal 2 hit.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Battleship Horizontal 3 hit.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Battleship Horizontal 4 hit.png")),
				
		};
		
		rotatedIconsHits = new ImageIcon[]{
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Battleship Vertical 1 hit.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Battleship Vertical 2 hit.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Battleship Vertical 3 hit.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Battleship Vertical 4 hit.png")),
		};
		
		
	}
	
	public Battleship(){
		
	}
	
	public void setHorizontal(boolean hor){
		horizontal = hor;
	}
	
	public void setPlaced(boolean b){
		
		placed = b;
		
	}
	
	public boolean isPlaced(){
		return placed;
	}

	public int getLength(){
		return 4;
	}

	public BattleShipButton[] getOccupiedButtons(){
		return buttons;
	}
	
	public void addOccupiedButton(BattleShipButton b, int index){
		buttons[index] = b;
	}
	
	public void resetOccupiedButtons(){
		buttons = new BattleShipButton[4];
	}
	
	@Override
	public ImageIcon getImageHit(int index) {
		return horizontal ? iconsHits[index] : rotatedIconsHits[index];
	}

	
	@Override
	public ImageIcon getImage(int index) {		
		return horizontal ? icons[index] : rotatedIcons[index];
	}

	@Override
	public void hit() {
		hits++;
		if (hits >= 4){
			sunk = true;
		}
	}
	
	public boolean isSunk(){
		return sunk;
	}

	@Override
	public int getHits() {
		return hits;
		
	}
}
