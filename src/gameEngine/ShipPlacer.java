package gameEngine;

import audio.AudioManager;
import entities.Ship;
import guiPanels.GameBoard;
import guiUI.BattleShipButton;
import guiUI.GameButton;

public class ShipPlacer {
	
	private static boolean placingShip = false;
	private static boolean horizontal = false;
	private static Ship activeShip;
	
	public static void setPlacing(boolean p){
		placingShip = p;
	}
	
	public static boolean isPlacingShip(){
		return placingShip;
	}
	
	
	public static void setHorizontal(boolean b){
		horizontal = b;
		
		if (activeShip != null && !activeShip.isPlaced()){
			activeShip.setHorizontal(b);
		}
	}
	
	public static Ship getActiveShip(){
		return activeShip;
	}
	
	public static void setActiveShip(Ship s){
		s.setHorizontal(horizontal);
		activeShip = s;
	}
	
	public static void reset(){
		activeShip = null;
		placingShip = false;
	}
	
	public static boolean allPlaced(int player){
		
		boolean allPlaced = true;
		
		if(player ==1){
			if (!GameManager.getPlayer1Ships().get("Carrier").isPlaced()) allPlaced = false;
			if (!GameManager.getPlayer1Ships().get("Battleship").isPlaced()) allPlaced = false;
			if (!GameManager.getPlayer1Ships().get("Destroyer").isPlaced()) allPlaced = false;
			if (!GameManager.getPlayer1Ships().get("Submarine").isPlaced()) allPlaced = false;
			if (!GameManager.getPlayer1Ships().get("Patrol Boat").isPlaced()) allPlaced = false;
			
		} else if (player == 2){
			if (!GameManager.getPlayer2Ships().get("Carrier").isPlaced()) allPlaced = false;
			if (!GameManager.getPlayer2Ships().get("Battleship").isPlaced()) allPlaced = false;
			if (!GameManager.getPlayer2Ships().get("Destroyer").isPlaced()) allPlaced = false;
			if (!GameManager.getPlayer2Ships().get("Submarine").isPlaced()) allPlaced = false;
			if (!GameManager.getPlayer2Ships().get("Patrol Boat").isPlaced()) allPlaced = false;
			
		}
		return allPlaced;
		
	}
	
	public static boolean canPlaceShip(int row, int column, GameBoard grid){
		
		boolean canPlace = true;
		
		if(activeShip.isPlaced()){
			canPlace = false;
		}
		
		if(grid.isMainBoard()){
		
			if(horizontal){
				
				for(int i = 0; i < activeShip.getLength(); i++){
					if (column + i > 10){
						canPlace =  false;
					} else if (grid.getButtons()[row][column + i].isOccupied()){
						canPlace =  false;
					}	
				}
			} else {
				
				for(int i = 0; i < activeShip.getLength(); i++){
					if (row + i > 10){
						canPlace =  false;
					} else if (grid.getButtons()[row + i][column].isOccupied()){
						canPlace =  false;
					}	
				}
			}
		} else {
			canPlace = false;
		}
		return canPlace;
	}
	
	public static void hoverOver(int row, int column, GameBoard grid){
		
		boolean possible = canPlaceShip(row,column,grid);
		
		if(grid.isMainBoard()){
		
			if(horizontal){
				
				for(int i = 0; i < activeShip.getLength(); i++){
					if (column + i > 10){
						//do nothing
					} else {
						grid.getButtons()[row][column + i].hover(possible);
					}	
				}
			} else {
				
				for(int i = 0; i < activeShip.getLength(); i++){
					if (row + i > 10){
						//do nothing
					} else {
						grid.getButtons()[row + i][column].hover(possible);
					}	
				}
			}
		} else {
			grid.getButtons()[row][column].hover(possible);
		}
		
	}
	
	public static void reset(int row, int column, GameBoard grid){
		
		if(grid.isMainBoard()){
		
			if(horizontal){
				
				for(int i = 0; i < activeShip.getLength(); i++){
					if (column + i > 10){
						//do nothing
					} else {
						grid.getButtons()[row][column + i].reset();
	
					}	
				}
			} else {
				
				for(int i = 0; i < activeShip.getLength(); i++){
					if (row + i > 10){
						//do nothing
					} else {
						grid.getButtons()[row + i][column].reset();
	
					}	
				}
			}
		} else {
			grid.getButtons()[row][column].reset();
		}
	}
	
	
	public static void placeShip(int row, int column, GameBoard grid){
		
		if(canPlaceShip(row,column,grid)){
			
			GameManager.getGameWindow().getBoardArea().getPanel6().setButtonPlaced();
			
			if(horizontal){
				
				for(int i = 0; i < activeShip.getLength(); i++){
					if (column + i > 10){
						//do nothing
					} else {
						BattleShipButton btn = (BattleShipButton) grid.getButtons()[row][column + i];
						btn.setOccupyingShip(activeShip,i);
						activeShip.setPlaced(true);
						activeShip.addOccupiedButton(btn,i);
					}	
				}
			} else {
				
				for(int i = 0; i < activeShip.getLength(); i++){
					if (row + i > 10){
						//do nothing
					} else {
						BattleShipButton btn = (BattleShipButton) grid.getButtons()[row + i][column];
						btn.setOccupyingShip(activeShip,i);
						activeShip.setPlaced(true);
						activeShip.addOccupiedButton(btn,i);
					}	
				}
			}
			AudioManager.setSound(4);
		}	
	}
	
	
	public static void removeShip(Ship s){
		
		AudioManager.setSound(8);
		for (BattleShipButton b: s.getOccupiedButtons()){
			b.removeOccupyingShip();
		}
		s.resetOccupiedButtons();
		s.setPlaced(false);
	}
	
}
