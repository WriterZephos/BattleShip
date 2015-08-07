package gameEngine;

import java.math.BigDecimal;

import entities.Player;

public class StatTracker {
	
	static int rounds = 0;
	static int player1Shots = 0;
	static int player2Shots = 0;
	static int player1Hits = 0;
	static int player2Hits = 0;
	static Player winner;
	static Player loser;
	
	public static void resetStats(){
		rounds = 0;
		player1Shots = 0;
		player2Shots = 0;
		player1Hits = 0;
		player2Hits = 0;
		winner = null;
		loser = null;
	}
	
	public static void plusRound(){
		rounds++;
	}
	
	public static void plusPlayer1Shots(){
		player1Shots++;
	}
	
	public static void plusPlayer2Shots(){
		player2Shots++;
	}
	
	public static void plusPlayer1Hits(){
		player1Hits++;
	}
	
	public static void plusPlayer2Hits(){
		player2Hits++;
	}
	
	public static void setWinner(Player p){
		winner = p;
	}
	
	public static void setLoser(Player p){
		loser = p;
	}
	
	public static long calculatePlayer1Score(){
		if(winner == null || loser == null || rounds == 0) return 0L;
		
		long gameScore = 0;
		
		BigDecimal score = new BigDecimal(0);
		BigDecimal ph = new BigDecimal(player1Hits);
		BigDecimal ps = new BigDecimal(player1Shots);
		BigDecimal r = new BigDecimal(rounds);
		BigDecimal base = new BigDecimal(100000);
		BigDecimal base2 = new BigDecimal(10000);
		BigDecimal hitPercentage = ph.divide(ps, 5, BigDecimal.ROUND_HALF_UP);
		
		if (winner == GameManager.getPlayer1()){
			score = score.add(base.divide(r,5,BigDecimal.ROUND_HALF_UP));
			score = score.add(base.multiply(hitPercentage));
			
			if (player1Hits == player1Shots){
				score = score.add(new BigDecimal(100000));
			} else if (player1Shots < 25){
				score = score.add(new BigDecimal(75000));
			} else if (player1Shots < 30){
				score = score.add(new BigDecimal(70000));
			} else if (player1Shots < 35){
				score = score.add(new BigDecimal(65000));
			} else if (player1Shots < 40){
				score = score.add(new BigDecimal(60000));
			} else if (player1Shots < 45){
				score = score.add(new BigDecimal(55000));
			} else if (player1Shots < 50){
				score = score.add(new BigDecimal(50000));
			}
			
			gameScore = score.longValue();
			
		} else if(loser == GameManager.getPlayer1()){
			score = score.add(base2.divide(r,5,BigDecimal.ROUND_HALF_UP));
			score = score.add(base2.multiply(hitPercentage));
			score = score.add(new BigDecimal(player2Shots * 1000));
			gameScore = score.longValue();
		}
		
		return gameScore;	
	}
	
	public static long calculatePlayer2Score(){
		if(winner == null || loser == null || rounds == 0) return 0L;
		
		long gameScore = 0;
		
		BigDecimal score = new BigDecimal(0);
		BigDecimal ph = new BigDecimal(player2Hits);
		BigDecimal ps = new BigDecimal(player2Shots);
		BigDecimal r = new BigDecimal(rounds);
		BigDecimal base = new BigDecimal(100000);
		BigDecimal base2 = new BigDecimal(10000);
		BigDecimal hitPercentage = ph.divide(ps, 5, BigDecimal.ROUND_HALF_UP);
		
		if (winner == GameManager.getPlayer2()){
			score = score.add(base.divide(r,5,BigDecimal.ROUND_HALF_UP));
			score = score.add(base.multiply(hitPercentage));
			
			if (player2Hits == player2Shots){
				score = score.add(new BigDecimal(100000));
			} else if (player2Shots < 25){
				score = score.add(new BigDecimal(75000));
			} else if (player2Shots < 30){
				score = score.add(new BigDecimal(70000));
			} else if (player2Shots < 35){
				score = score.add(new BigDecimal(65000));
			} else if (player2Shots < 40){
				score = score.add(new BigDecimal(60000));
			} else if (player2Shots < 45){
				score = score.add(new BigDecimal(55000));
			} else if (player2Shots < 50){
				score = score.add(new BigDecimal(50000));
			}
			
			gameScore = score.longValue();
			
		} else if(loser == GameManager.getPlayer2()){
			score = score.add(base2.divide(r,5,BigDecimal.ROUND_HALF_UP));
			score = score.add(base2.multiply(hitPercentage));
			score = score.add(new BigDecimal(player2Shots * 1000));
			gameScore = score.longValue();
		}
		
		return gameScore;		
	}
	
	public static void updatePlayer1Scores(){
		Long gameScore = calculatePlayer1Score();
		Player player = GameManager.getPlayer1();
		
		player.setOverallScore(player.getOverallScore() + gameScore);
		
		if (player.getHighScore() < gameScore){
			player.setHighScore(gameScore);
		}
	}
	
	public static void updatePlayer2Scores(){
		Long gameScore = calculatePlayer2Score();
		Player player = GameManager.getPlayer2();
		
		player.setOverallScore(player.getOverallScore() + gameScore);
		
		if (player.getHighScore() < gameScore){
			player.setHighScore(gameScore);
		}	
	}
	
	public static int remainingFleet1(){
		int remaining = 17;
		
		remaining -= GameManager.player1Ships.get("Carrier").getHits();
		remaining -= GameManager.player1Ships.get("Battleship").getHits();
		remaining -= GameManager.player1Ships.get("Destroyer").getHits();
		remaining -= GameManager.player1Ships.get("Patrol Boat").getHits();
		remaining -= GameManager.player1Ships.get("Submarine").getHits();
		
		return remaining;
	}
	
	public static int remainingFleet2(){
		int remaining = 17;
		
		remaining -= GameManager.player2Ships.get("Carrier").getHits();
		remaining -= GameManager.player2Ships.get("Battleship").getHits();
		remaining -= GameManager.player2Ships.get("Destroyer").getHits();
		remaining -= GameManager.player2Ships.get("Patrol Boat").getHits();
		remaining -= GameManager.player2Ships.get("Submarine").getHits();
		
		return remaining;
	}	
		
	public static void updateGuiStats(){
		if (!GameManager.isGameStarted()) return;
		GameManager.getGameWindow().getBottom().setRounds(rounds);
		GameManager.getGameWindow().getBottom().setPlayer1Shots(player1Shots, player1Hits);
		GameManager.getGameWindow().getBottom().setPlayer2Shots(player2Shots, player2Hits);
		GameManager.getGameWindow().getBoardArea().getPanel5().fleetLabelAText(remainingFleet1());
		GameManager.getGameWindow().getBoardArea().getPanel5().fleetLabelBText(remainingFleet2());	
	}
	
	public static int getWinner(){
		if(winner == null || loser == null || rounds == 0) return 0;
		
		int winnerNumber = 0;
		
		if (winner == GameManager.getPlayer1()){
			
			winnerNumber = 1;
			
		} else if(winner == GameManager.getPlayer2()){
			
			winnerNumber = 2;
			
		}
		
		return winnerNumber;
	}
}
