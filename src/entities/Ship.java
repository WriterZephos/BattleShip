package entities;

import guiUI.BattleShipButton;

import javax.swing.ImageIcon;

public abstract class Ship {
	
	public abstract int getLength();
	
	public abstract ImageIcon getImage(int index);

	public abstract void setHorizontal(boolean hor);
	
	public abstract void setPlaced(boolean b);
	
	public abstract boolean isPlaced();
	
	public abstract BattleShipButton[] getOccupiedButtons();
	
	public abstract void addOccupiedButton(BattleShipButton b, int index);
	
	public abstract void resetOccupiedButtons();
	
	public String toString(){
		return this.getClass().getSimpleName();
	}

	public abstract void hit();

	public abstract int getHits();
	
	public abstract boolean isSunk();

	public abstract ImageIcon getImageHit(int index);
}
