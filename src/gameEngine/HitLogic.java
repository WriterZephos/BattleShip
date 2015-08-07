package gameEngine;

import audio.AudioManager;
import guiUI.BattleShipButton;
import guiUI.GameButton;

public class HitLogic {
	
	static boolean hit = false;
	static boolean gameOver = false;

	public static boolean hit(BattleShipButton button){
		hit = false;
		GameManager.shotCalled();
		if(GameManager.getPlayerTurn()==1){
			StatTracker.plusPlayer1Shots();
			GameButton[][] p2Buttons = GameManager.getGameWindow().getBoardArea().getPanel4().getButtons();
			
			int p2CoordinateX = button.getCoordinateX() - 1;
			int p2CoordinateY = button.getCoordinateY();
			if(p2Buttons[p2CoordinateY][p2CoordinateX].isOccupied()){
				p2Buttons[p2CoordinateY][p2CoordinateX].setHit();
				AudioManager.setSound(0);
				button.setHit();	
				p2Buttons[p2CoordinateY][p2CoordinateX].getOccupyingShip().hit();

				hit = true;
				GameManager.getGameWindow().getBoardArea().getPanel5().setShotLabelAText("Hit!");
				StatTracker.plusPlayer1Hits();
				evaluateGame();
			} else {
				AudioManager.setSound(7);
				p2Buttons[p2CoordinateY][p2CoordinateX].setMiss();
				button.setMiss();
				GameManager.getGameWindow().getBoardArea().getPanel5().setShotLabelAText("Miss!");
				hit = false;
			}
		} else if (GameManager.getPlayerTurn() == 2){
				StatTracker.plusPlayer2Shots();
				GameButton[][] p1Buttons = GameManager.getGameWindow().getBoardArea().getPanel2().getButtons();
				
				int p1CoordinateX = button.getCoordinateX() + 1;
				int p1CoordinateY = button.getCoordinateY();
				if(p1Buttons[p1CoordinateY][p1CoordinateX].isOccupied()){
					p1Buttons[p1CoordinateY][p1CoordinateX].setHit();
					AudioManager.setSound(0);
					button.setHit();	
					p1Buttons[p1CoordinateY][p1CoordinateX].getOccupyingShip().hit();
					hit = true;
					StatTracker.plusPlayer2Hits();
					GameManager.getGameWindow().getBoardArea().getPanel5().setShotLabelBText("Hit!");
					evaluateGame();
				} else {
					AudioManager.setSound(7);
					p1Buttons[p1CoordinateY][p1CoordinateX].setMiss();
					button.setMiss();
					hit = false;
					GameManager.getGameWindow().getBoardArea().getPanel5().setShotLabelBText("Miss!");	
				}
		}
		StatTracker.updateGuiStats();
		return hit;
	}
	
	public static void evaluateGame(){
		if(StatTracker.remainingFleet1() == 0){
			gameOver = true;
			GameManager.getGameWindow().getBoardArea().endGame();
			StatTracker.setWinner(GameManager.getPlayer2());
			StatTracker.setLoser(GameManager.getPlayer1());
			StatTracker.updatePlayer1Scores();
			StatTracker.updatePlayer2Scores();
			GameManager.savePlayers();
			
		} else if (StatTracker.remainingFleet2() == 0){
			gameOver = true;
			GameManager.getGameWindow().getBoardArea().endGame();
			StatTracker.setWinner(GameManager.getPlayer1());
			StatTracker.setLoser(GameManager.getPlayer2());
			StatTracker.updatePlayer1Scores();
			StatTracker.updatePlayer2Scores();
			GameManager.savePlayers();
		}
	
	}
	
	public static boolean isGameOver(){
		return gameOver;
	}
	
	public static void reset(){
		gameOver = false;
		hit = false;
	}
	
}
