package guiPanels;

import gameEngine.GameManager;
import guiUI.ComponentFactory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import audio.AudioManager;

public class StartPanel extends ControlPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8612444964249285245L;
	JPanel start;
	ImageIcon logo;
	ImageIcon banner;
	JPanel panel1;
	JPanel panel2;

	Font headerFont;
	Font basicFont;
	
	int stage;
	
	//JButton startGame;

	StartPanel(int xv, int yv, Image img, ImageIcon logo, ImageIcon banner) {
		super(xv, yv, img);
		this.logo = logo;
		this.banner = banner;
		stage = 0;
		
		//setting layout
		
		setLayout(new BorderLayout());
		
		//initializing common assets
		headerFont = new Font(Font.SERIF,Font.BOLD,24);
		basicFont = new Font(Font.SERIF,Font.BOLD,18);

		//creating panels
		start = createStartScreen();
		panel1 = new PlayerSelectionPanel(1,this);
		panel2 = new PlayerSelectionPanel(2,this);
		
		add(start);
	}
	
	private JPanel createStartScreen(){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		JButton btn = ComponentFactory.createJButton("Start Game", 100,Color.RED);
		btn.setBackground(Color.BLACK);
		btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				AudioManager.setSound(3);
				((StartPanel) GameManager.getGameWindow().getStartScreen()).next();
			}});
		
		JLabel label = new JLabel();
		label.setAlignmentX(CENTER_ALIGNMENT);
		label.setIcon(logo);
		
		JLabel label2 = new JLabel();
		label2.setAlignmentX(CENTER_ALIGNMENT);
		label2.setIcon(banner);
		
		JLabel label3 = ComponentFactory.createBasicLabel("Created by Bryant Morrill and Chandler Broadwater", Color.WHITE);
		label3.setAlignmentX(CENTER_ALIGNMENT);
		JLabel label4 = ComponentFactory.createBasicLabel("CSIS 1410, fall 2014", Color.WHITE);
		label3.setAlignmentX(CENTER_ALIGNMENT);
		
		panel.add(Box.createRigidArea(new Dimension(660,30)));
		panel.add(label);
		panel.add(Box.createRigidArea(new Dimension(660,10)));
		panel.add(label2);
		panel.add(Box.createRigidArea(new Dimension(660,75)));
		panel.add(btn);
		panel.add(Box.createRigidArea(new Dimension(660,75)));
		panel.add(label3);
		panel.add(Box.createRigidArea(new Dimension(660,15)));
		panel.add(label4);
		
		panel.setOpaque(false);
		panel.setVisible(true);
		return panel;
		
	}
	
	public void next(){
		switch(stage){
		
		case 0:
			removeAll();
			((PlayerSelectionPanel) panel1).updatePlayerMenu();
			add(panel1);
			revalidate();
			repaint();
			stage++;
			break;
		case 1:
			removeAll();
			((PlayerSelectionPanel) panel2).updatePlayerMenu();
			add(panel2);
			revalidate();
			repaint();
			stage++;
			break;
		case 2:
			if(GameManager.getPlayer1() != null && GameManager.getPlayer2() != null){
				GameManager.getGameWindow().showGameScreen();
			}
			break;
		}
	}
	
	public void back(){
		switch(stage){
		
		case 0:
			//do something
			break;
		case 1:
			removeAll();
			add(start);
			revalidate();
			repaint();
			stage--;
			break;
		case 2:
			removeAll();
			((PlayerSelectionPanel) panel1).updatePlayerMenu();
			add(panel1);
			revalidate();
			repaint();
			stage++;
			break;
		}
	}	
	
}
	
