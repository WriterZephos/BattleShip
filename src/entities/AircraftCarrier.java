package entities;

import guiUI.BattleShipButton;

import javax.swing.ImageIcon;

public class AircraftCarrier extends Ship{
	
	private static ImageIcon[] icons;
	private static ImageIcon[] rotatedIcons;
	private static ImageIcon[] iconsHits;
	private static ImageIcon[] rotatedIconsHits;
	private boolean horizontal = false;
	private boolean placed = false;
	private BattleShipButton[] buttons = new BattleShipButton[5];
	private int hits = 0;
	private boolean sunk = false;
	
	static {
		
		icons = new ImageIcon[]{
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Aircraft Carrier Horizontal 1.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Aircraft Carrier Horizontal 2.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Aircraft Carrier Horizontal 3.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Aircraft Carrier Horizontal 4.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Aircraft Carrier Horizontal 5.png")),
		};
		
		rotatedIcons = new ImageIcon[]{
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Aircraft Carrier Vertical 1.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Aircraft Carrier Vertical 2.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Aircraft Carrier Vertical 3.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Aircraft Carrier Vertical 4.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Aircraft Carrier Vertical 5.png"))
		};
		
		iconsHits = new ImageIcon[]{
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Aircraft Carrier Horizontal 1 hit.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Aircraft Carrier Horizontal 2 hit.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Aircraft Carrier Horizontal 3 hit.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Aircraft Carrier Horizontal 4 hit.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Aircraft Carrier Horizontal 5 hit.png")),
		};
		
		rotatedIconsHits = new ImageIcon[]{
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Aircraft Carrier Vertical 1 hit.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Aircraft Carrier Vertical 2 hit.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Aircraft Carrier Vertical 3 hit.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Aircraft Carrier Vertical 4 hit.png")),
				new ImageIcon(AircraftCarrier.class.getClass().getResource("/Aircraft Carrier Vertical 5 hit.png"))
		};
	}
	
	public AircraftCarrier(){
		
	}

	public int getLength(){
		return 5;
	}
	
	public void setHorizontal(boolean hor){
		horizontal = hor;
		System.out.println(horizontal);
	}
	
	public void setPlaced(boolean b){
		placed = b;
	}
	
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
		buttons = new BattleShipButton[5];
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
	public void hit() {
		hits++;
		if (hits >= 5){
			sunk = true;
		}
	}
	
	@Override
	public boolean isSunk(){
		return sunk;
	}

	
	@Override
	public int getHits() {
		return hits;
		
	}

	
}
