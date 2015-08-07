package guiPanels;

import gameEngine.GameManager;
import gameEngine.StatTracker;
import guiUI.ComponentFactory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import audio.AudioManager;

public class BoardAreaPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2633152010914021564L;
	char[] leftLabels = {'\0','A','B','C','D','E','F','G','H','I','J'};
	char[] topLabels = {'\0','1','2','3','4','5','6','7','8','9','1'};
	
	private GameBoard panel1;
	private GameBoard panel2;
	private GameBoard panel3;
	private GameBoard panel4;
	private ControlPanel nextPlayer;
	private JLabel nextPlayerScreenLabel;
	private JLabel nextPlayerImageLabel;
	private JButton nextPlayerButton;
	private SideTopPanel panel5;
	private SideBottomPanel panel6;
	private int stage = 0;
	private boolean placingDone = false;
	private Component filler;
	private boolean scoresAdded = false;
	private Color playerColor = Color.RED;
	
	ImageIcon[] textures;
	
	private boolean switched = false;
	
	BoardAreaPanel(ImageIcon[] textures, Image[] backgrounds){
		setPreferredSize(new Dimension(330,330));
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		
		this.textures = textures;
		
			//create and add top board for player 1
		
				panel1 = new GameBoard(topLabels,leftLabels,textures, false,1);
				//add(panel1);
				
				//create and add top blank panel
				
				panel5 = new SideTopPanel(330,330,backgrounds[2]);
				//panel5.setPreferredSize(new Dimension(330,330));
			
				//add(panel5);
				
				//create and add bottom board for player 1
				
				panel2 = new GameBoard(topLabels,leftLabels,textures, true,1);
				//add(panel2);
				
				//create and add bottom blank panel.
				
				panel6 = new SideBottomPanel(330,330,backgrounds[2],textures[16]);
				//add(panel6);
				
				//create player 2 top board
				
				panel3 = new GameBoard(topLabels,leftLabels,textures,false,2);
				
				//create player 2 bottom board 
				
				panel4 = new GameBoard(topLabels,leftLabels,textures,true,2);
				
				nextPlayer = createNextPlayerPanel();
				
				add(nextPlayer,BorderLayout.CENTER);
	}
	
	private ControlPanel createNextPlayerPanel(){
		
		ControlPanel panel = new ControlPanel(660, 660, GameManager.getGameWindow().getBackgrounds()[3]);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		nextPlayerScreenLabel = ComponentFactory.createHeaderLabel("Player 1 Ship Placement", Color.RED);
		JButton button = ComponentFactory.createJButton("Continue", 100, Color.RED);
		JLabel label = new JLabel();
		label.setIcon(textures[2]);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		nextPlayerImageLabel = label;
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				AudioManager.setSound(3);
				next();
			}});
		button.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseEntered(MouseEvent e) {
				AudioManager.setSound(5);
				button.setBorder(new LineBorder(Color.WHITE));
				button.setBackground(Color.BLACK);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button.setBorder(new LineBorder(playerColor));
				
			}
		
			@Override
			public void mousePressed(MouseEvent e) {
				button.setBorder(new BevelBorder(BevelBorder.LOWERED, playerColor, playerColor));
				button.setBackground(Color.BLACK);
				button.setForeground(playerColor);
				
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				button.setBorder(new LineBorder(Color.WHITE));
				button.setForeground(playerColor);
				
			}});
		
		nextPlayerButton = button;
		
		filler = Box.createRigidArea(new Dimension(660,100));
		panel.add(filler);
		panel.add(Box.createRigidArea(new Dimension(660,48)));
		panel.add(label);
		panel.add(Box.createRigidArea(new Dimension(660,30)));
		panel.add(nextPlayerScreenLabel);
		panel.add(Box.createRigidArea(new Dimension(660,30)));
		panel.add(button);
		
		return panel;
	}
	
	public void next(){
		
		switch (stage){
		
			//starts at next player screen
		case 0:  
			//moves to player 1 placement
			removeAll();
			setLayout(new GridLayout(2,2,0,0));
			add(panel1);
			add(panel5);
			add(panel2);
			add(panel6);
			stage++;
			revalidate();
			repaint();
			break;
		case 1: //moves to next player screen
			nextPlayerScreenLabel.setText("Player 2 Ship Placement");
			nextPlayerScreenLabel.setForeground(Color.BLUE);
			playerColor = Color.BLUE;
			nextPlayerButton.setForeground(Color.BLUE);
			removeAll();
			setLayout(new BorderLayout());
			add(nextPlayer);
			stage++;
			revalidate();
			repaint();
			break;
		case 2: // moves to player 2 placement
			setLayout(new GridLayout(2,2,0,0));
			stage++;
			switchBoards();
			break;
		case 3: //moves to next player screen to player 1
			nextPlayerScreenLabel.setText("Player 1's Turn");
			nextPlayerScreenLabel.setForeground(Color.RED);
			playerColor = Color.RED;
			nextPlayerButton.setForeground(Color.RED);
			nextPlayer.remove(filler);
			nextPlayer.revalidate();
			nextPlayer.repaint();
			removeAll();
			setLayout(new BorderLayout());
			add(nextPlayer,BorderLayout.CENTER);
			if(!placingDone) { 
				AudioManager.setSound(2);
			} else {
				AudioManager.setSound(9);
			}
			
			StatTracker.plusRound();
			stage++;
			GameManager.getGameWindow().getBottom().takeDown();
			revalidate();
			repaint();
			break;
		case 4: //moves to player 1 inGame screen
			//need to change side board state
			setLayout(new GridLayout(2,2,0,0));
			GameManager.nextTurn();
			if(!placingDone) {
				GameManager.getGameWindow().getBottom().setUp();
				panel5.donePlacing();
				panel6.donePlacing();
				AudioManager.setBackgroundMusic(1);
				GameManager.startGame();
				placingDone = true;
			} else {
				GameManager.getGameWindow().getBottom().switchPlayers();
			}
			stage++;
			switchBoards();
			break;
		case 5: //next player screen to player 2
			nextPlayerScreenLabel.setText("Player 2's Turn");
			nextPlayerScreenLabel.setForeground(Color.BLUE);
			nextPlayerButton.setForeground(Color.BLUE);
			playerColor = Color.BLUE;
			removeAll();
			setLayout(new BorderLayout());
			add(nextPlayer,BorderLayout.CENTER);
			AudioManager.setSound(9);
			stage++;
			GameManager.getGameWindow().getBottom().takeDown();
			revalidate();
			repaint();
			break;
		case 6: //player 2 in Game screen
			//need to change side board state
			setLayout(new GridLayout(2,2,0,0));
			GameManager.nextTurn();
			GameManager.getGameWindow().getBottom().switchPlayers();
			stage = 3; // begin looping
			switchBoards();	
			break;
		case 7:
			nextPlayerScreenLabel.setText("Player " + StatTracker.getWinner() + " wins!");
			nextPlayerScreenLabel.setForeground(StatTracker.getWinner() == 1 ? Color.RED: Color.BLUE);
			nextPlayerButton.setForeground(StatTracker.getWinner() == 1 ? Color.RED: Color.BLUE);
			playerColor = StatTracker.getWinner() == 1 ? Color.RED: Color.BLUE;
			nextPlayerImageLabel.setIcon(textures[20]);
			
			if (!scoresAdded){
				JButton scores = ComponentFactory.createJButton("See Boards and Scores", 200, StatTracker.getWinner() == 1 ? Color.RED: Color.BLUE);
				scores.addActionListener(new ActionListener(){
	
					@Override
					public void actionPerformed(ActionEvent e) {
						setStage(9);
						next();
						
					}});
				nextPlayer.add(Box.createRigidArea(new Dimension(660,30)));
				nextPlayer.add(scores);
				scoresAdded = true;
			}
			removeAll();
			setLayout(new BorderLayout());
			AudioManager.setBackgroundMusic(3);
			add(nextPlayer,BorderLayout.CENTER);
			stage++;
			GameManager.getGameWindow().getBottom().takeDown();
			revalidate();
			repaint();
			break;
		case 8:
			stage = 0;
			GameManager.resetGame();
			break;
		case 9:
			panel5.showEndScreen();
			setLayout(new GridLayout(2,2,0,0));
			stage++;
			switched = true;
			switchBoards();
			panel5.showEndScreen();
			break;
		case 10:
			setLayout(new GridLayout(2,2,0,0));
			stage = 7;
			switchBoards();
			panel5.showEndScreen();
			break;
		}
	}
	
	public void setStage(int s){
		stage = s;
	}
	
	public void endGame(){
		stage = 7;
	}
	
	public SideBottomPanel getPanel6(){
		return panel6;
	}
	
	public SideTopPanel getPanel5(){
		return panel5;
	}

	public void switchBoards(){
		if (!switched){
			
			//removing all player1 and blank panels.
			removeAll();
			
			panel5.switchPlayers();
			panel6.switchPlayers();
			
			//adding blank panels and player 2 panels
			add(panel5);
			add(panel3);
			add(panel6);
			add(panel4);
			
			//refreshing screen
			revalidate();
			repaint();
			
			//updating GUI status
			switched = true;
			
		} else {
			
			//removing all blank panels and player 2 panels.
			removeAll();
			
			panel5.switchPlayers();
			panel6.switchPlayers();
			
			
			//adding player 1 panels and blank panels
			add(panel1);
			add(panel5);
			add(panel2);
			add(panel6);
			
			//refreshing screen
			revalidate();
			repaint();
			
			//updating GUI status
			switched = false;
		}	
	} 

	public GameBoard getPanel1() {
		return panel1;
	}

	public GameBoard getPanel2() {
		return panel2;
	}

	public GameBoard getPanel3() {
		return panel3;
	}

	public GameBoard getPanel4() {
		return panel4;
		
	} 
	
	
}
