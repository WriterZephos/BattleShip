package guiPanels;

import gameEngine.GameManager;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import audio.AudioManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

public class BattleShipGui {

	private JFrame frame;
	private StartPanel startScreen;
	private BoardAreaPanel boardArea;
	private BottomPanel bottom;
	private ImageIcon[] textures;
	private Image[] backgrounds;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BattleShipGui game = new BattleShipGui();
					GameManager.setGameWindow(game);
					setCustomUISetting();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BattleShipGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//load up images
		
		initializeTextures();
		initializeBackgrounds();
		
		//Create JFrame
		
		frame = new JFrame("BattleShip");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//create start screen, add it to frame.
		
		startScreen = createStartScreen();
		frame.getContentPane().add(startScreen, BorderLayout.CENTER);
		
		//create and add bottom control panel
		
		bottom = new BottomPanel(700,60,backgrounds[0],textures[15]);
		frame.getContentPane().add(bottom, BorderLayout.SOUTH);
		
		//create and add margin panels around window;
		
		JPanel leftSide = new ControlPanel(20,660,backgrounds[1]);
		JPanel rightSide = new ControlPanel(20,660,backgrounds[1]);
		JPanel topSide = new ControlPanel(700,20,backgrounds[4]);
		frame.getContentPane().add(leftSide, BorderLayout.WEST);
		frame.getContentPane().add(rightSide, BorderLayout.EAST);
		frame.getContentPane().add(topSide, BorderLayout.NORTH);

		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		AudioManager.setBackgroundMusic(0);
		AudioManager.loopMusic();
	}

	public void showStartScreen(){
		if(startScreen == null){
			startScreen = new StartPanel(660,660,backgrounds[3],textures[4],textures[19]);
			
		}
		
		frame.getContentPane().remove(boardArea);
		bottom.reset();
		frame.add(startScreen);
		AudioManager.setBackgroundMusic(0);
		frame.revalidate();
		frame.repaint();
	}
	
	public void showGameScreen(){
		if (boardArea == null){
			boardArea = new BoardAreaPanel(textures, backgrounds);
		}
		frame.getContentPane().remove(startScreen);
		startScreen = null;
		frame.add(boardArea);
		frame.revalidate();
		frame.repaint();
	}
	
	private StartPanel createStartScreen(){
		
		StartPanel panel = new StartPanel(660,660,backgrounds[3],textures[4],textures[19]);
		return panel;
	}
	
	private void initializeTextures(){
		textures = new ImageIcon[]{
				new ImageIcon(this.getClass().getResource("/water.png")),
				new ImageIcon(this.getClass().getResource("/GunMetal.png")),
				new ImageIcon(this.getClass().getResource("/antennaMedium.gif")),
				new ImageIcon(this.getClass().getResource("/antennaSmall.gif")),
				new ImageIcon(this.getClass().getResource("/logo.png")),
				new ImageIcon(this.getClass().getResource("/carrierProfile1.png")),
				new ImageIcon(this.getClass().getResource("/carrierProfile2.png")),
				new ImageIcon(this.getClass().getResource("/ship2Profile1.png")),
				new ImageIcon(this.getClass().getResource("/ship2Profile2.png")),
				new ImageIcon(this.getClass().getResource("/ship3Profile1.png")),
				new ImageIcon(this.getClass().getResource("/ship3Profile2.png")),
				new ImageIcon(this.getClass().getResource("/ship4Profile1.png")),
				new ImageIcon(this.getClass().getResource("/ship4Profile2.png")),
				new ImageIcon(this.getClass().getResource("/ship5Profile1.png")),
				new ImageIcon(this.getClass().getResource("/ship5Profile2.png")),
				new ImageIcon(this.getClass().getResource("/logo2.png")),
				new ImageIcon(this.getClass().getResource("/radar5t.gif")),
				new ImageIcon(this.getClass().getResource("/BoardMiss.png")),
				new ImageIcon(this.getClass().getResource("/BoardHit.png")),
				new ImageIcon(this.getClass().getResource("/navalBanner.jpg")),
				new ImageIcon(this.getClass().getResource("/BattleshipFiring.gif")),
		};
	}
	
	private void initializeBackgrounds(){
		backgrounds = new Image[5];
		try {
		    backgrounds[0] = ImageIO.read(this.getClass().getResource("/bottom.png"));
		    backgrounds[1] = ImageIO.read(this.getClass().getResource("/side.png"));
		    backgrounds[2] = ImageIO.read(this.getClass().getResource("/emptyBoard.png"));
		    backgrounds[3] = ImageIO.read(this.getClass().getResource("/start.png"));
		    backgrounds[4] = ImageIO.read(this.getClass().getResource("/top.png"));
		} catch (IOException e) {
		}
	}
	
	public StartPanel getStartScreen(){
		
		return startScreen;
	}
	
	public BoardAreaPanel getBoardArea(){
		if(boardArea == null){
			boardArea = new BoardAreaPanel(textures, backgrounds);
		}
		return boardArea;
	}
	
	public BottomPanel getBottom(){
		if(bottom == null){
			bottom = new BottomPanel(700,60,backgrounds[0],textures[4]);
		}
		return bottom;
	}
	
	public ImageIcon[] getTextures(){
		return textures;
	}
	
	public Image[] getBackgrounds(){
		return backgrounds;
	}

	private static void setCustomUISetting(){
		UIManager.put("Button.select", Color.BLACK);
		UIManager.put("button.background", Color.BLACK);
		UIManager.put("Button.foreground", Color.WHITE);
		UIManager.put("windowBorder", Color.BLACK);
		UIManager.put("window", Color.BLACK);
		UIManager.put("windowText", Color.RED);
	}

	public void reset(){
		bottom.reset();
		showStartScreen();
		boardArea = null;
		frame.revalidate();
		frame.repaint();
	}
	
	
	
}
