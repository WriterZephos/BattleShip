package gameEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

import audio.AudioManager;
import entities.AircraftCarrier;
import entities.Battleship;
import entities.Destroyer;
import entities.PatrolBoat;
import entities.Player;
import entities.Ship;
import entities.Submarine;
import guiPanels.BattleShipGui;

public class GameManager {
	
	private static BattleShipGui gameWindow;
	private static Player player1;
	private static Player player2;
	private static ArrayList<Player> players = new ArrayList<>();
	static Map<String,Ship> player1Ships;
	static Map<String,Ship> player2Ships;
	static Map<String,Ship> activeShips;
	private static boolean shotCalled = false;
	private static boolean gameStarted = false;
	private static int playerTurn = 0;
   
	static{
		
		initializePlayers();
		
		player1Ships = new HashMap<>();
		player2Ships = new HashMap<>();
	}

	public static void initializePlayers(){
		
		File f = new File("PlayerList.csv");
		if(!f.exists()){
			try {
				Formatter format = new Formatter("PlayerList.csv");
				format.flush();
				format.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
			players = new ArrayList<Player>();
			Scanner input;
			try {
				input = new Scanner(new BufferedReader(new FileReader("PlayerList.csv")));
				while(input.hasNextLine()){
					String line = input.nextLine();
					String[] info = line.split(",");
					String name = info[0];
					Long hs = Long.parseLong(info[1]);
					Long os = Long.parseLong(info[2]);
					players.add(new Player(name,hs,os));
					//input.nextLine();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void savePlayers(){
		
		try {
			Formatter f = new Formatter("PlayerList.csv");
			for (Player p : players){
				f.format(p.toString() + "%n");
				
			}
			f.flush();
			f.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static BattleShipGui getGameWindow() {
		return gameWindow;
	}

	public static void setGameWindow(BattleShipGui gameWindow) {
		GameManager.gameWindow = gameWindow;
	}

	public static Player getPlayer1() {
		return player1;
	}

	public static void setPlayer1(Player p1) {
		GameManager.player1 = p1;
	}

	public static Player getPlayer2() {
		return player2;
	}

	public static void setPlayer2(Player p2) {
		GameManager.player2 = p2;
	}

	public static ArrayList<Player> getPlayers() {
		return players;
	}

	public static void addPlayers(Player p) {
		players.add(p);
	}

	public static Map<String, Ship> getPlayer1Ships() {
		return player1Ships;
	}

	public static void setPlayer1Ships(Map<String, Ship> ships) {
		player1Ships = ships;
	}

	public static Map<String, Ship> getPlayer2Ships() {
		return player2Ships;
	}

	public static void setPlayer2Ships(Map<String, Ship> ships) {
		player2Ships = ships;
	}

	public static void resetShips(){
		player1Ships = initializeShips();
		player2Ships = initializeShips();
		activeShips = null;
		
		ShipPlacer.reset();
		ShipPlacer.setHorizontal(false);
	}

	public static void resetGame(){
		resetShips();
		player1 = null;
		player2 = null;
		shotCalled = false;
		gameStarted = false;
		playerTurn = 0;
		StatTracker.resetStats();
		HitLogic.reset();
		GameManager.getGameWindow().reset();
		AudioManager.reset();
	}
	
	public static void activatePlayer1Ships(){
		activeShips = player1Ships;
	}
	
	public static void activatePlayer2Ships(){
		activeShips = player2Ships;
	}
	
	public static Map<String,Ship> getActiveShips(){
		return activeShips;
	}
	
	public static int getPlayerTurn(){
		return playerTurn;
	}
	
	public static void nextTurn(){
		shotCalled = false;
		playerTurn++;
		if (playerTurn > 2) playerTurn = 1;
	}
	
	public static Map<String,Ship> initializeShips(){
		
		Map<String,Ship> ships = new HashMap<String,Ship>();
		
		ships.put("Carrier", new AircraftCarrier());
		ships.put("Battleship", new Battleship());
		ships.put("Destroyer", new Destroyer());
		ships.put("Patrol Boat", new PatrolBoat());
		ships.put("Submarine", new Submarine());
		
		return ships;
		
		
	}
	
	public static void shotCalled(){
		shotCalled = true;
	}
	
	public static boolean isShotCalled(){
		return shotCalled;
	}

	public static boolean isGameStarted(){
		return gameStarted;
	}
	
	public static void startGame(){
		gameStarted = true;
	}
}
