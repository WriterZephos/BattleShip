package entities;

import guiUI.BattleShipButton;

import javax.swing.ImageIcon;

public class PatrolBoat extends Ship{
	
	private static ImageIcon[] icons;
	private static ImageIcon[] rotatedIcons;
	private static ImageIcon[] iconsHits;
	private static ImageIcon[] rotatedIconsHits;
	private boolean horizontal = false;
	private boolean placed = false;
	private BattleShipButton[] buttons = new BattleShipButton[2];
	private int hits = 0;
	private boolean sunk = false;
	
	static {
		
		icons = new ImageIcon[]{
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Patrol Boat Horizontal 1.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Patrol Boat Horizontal 2.png")),
				
		};
		
		rotatedIcons = new ImageIcon[]{
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Patrol Boat Vertical 1.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Patrol Boat Vertical 2.png")),
				
		};
		
		iconsHits = new ImageIcon[]{
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Patrol Boat Horizontal 1 hit.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Patrol Boat Horizontal 2 hit.png")),
				
		};
		
		rotatedIconsHits = new ImageIcon[]{
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Patrol Boat Vertical 1 hit.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Patrol Boat Vertical 2 hit.png")),
				
		};
		
		
	}
	
	public PatrolBoat(){
		
	}

	public int getLength(){
		
		return 2;
	}

	public void setHorizontal(boolean hor){
		horizontal = hor;
		System.out.println(horizontal);
	}
	@Override
	public boolean isPlaced(){
		return placed;
	}
	
	public BattleShipButton[] getOccupiedButtons(){
		return buttons;
	}
	
	public void addOccupiedButton(BattleShipButton b, int index){
		buttons[index] = b;
	}
	
	public void resetOccupiedButtons(){
		buttons = new BattleShipButton[2];
	}

	@Override
	public ImageIcon getImage(int index) {		
		return horizontal ? icons[index] : rotatedIcons[index];
	}

	@Override
	public ImageIcon getImageHit(int index) {
		return horizontal ? iconsHits[index] : rotatedIconsHits[index];
	}

	
	@Override
	public void setPlaced(boolean b) {
		placed = b;
		
	}

	@Override
	public void hit() {
		hits++;
		if (hits >= 2){
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
