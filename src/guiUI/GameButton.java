package guiUI;

import entities.Ship;

public interface GameButton {
	
	public abstract boolean isOccupied();
	
	public abstract void hover(boolean possible);
	
	public abstract void reset();

	public abstract void setHit();

	public abstract void setMiss();

	public abstract Ship getOccupyingShip();

}
