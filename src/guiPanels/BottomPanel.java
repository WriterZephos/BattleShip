package guiPanels;

import guiUI.ComponentFactory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BottomPanel extends ControlPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8004533164116157848L;
	JPanel logoPanel;
	ImageIcon gameLogo;
	JPanel player1Info;
	JPanel player2Info;
	JLabel player1shots;
	JLabel player2shots;
	JLabel player1rounds;
	JLabel player2rounds;
	JLabel player1hits;
	JLabel player2hits;
	boolean switched = false;

	BottomPanel(int xv, int yv, Image img, ImageIcon logo) {
		super(xv, yv, img);
		setLayout(new GridLayout(0,2,0,0));
		
		gameLogo = logo;
	}
	
	
	private JPanel createLogoPanel(ImageIcon logo){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setOpaque(false);
		JLabel label = new JLabel();
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setIcon(logo);
		panel.add(Box.createRigidArea(new Dimension(350,10)));
		panel.add(label);
		panel.setVisible(true);
		return panel;	
	}
	
	private JPanel createInfoPanel(int player){
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new GridLayout(2,2,0,0));
		JLabel label1 = ComponentFactory.createBasicLabel("                Player " + player, Color.WHITE);
		label1.setAlignmentY(Component.CENTER_ALIGNMENT);
		JLabel label2 = ComponentFactory.createBasicLabel("    Round: 1", Color.WHITE);
		label2.setAlignmentY(Component.CENTER_ALIGNMENT);
		JLabel label3 = ComponentFactory.createBasicLabel("                Shots: 0", Color.WHITE);
		label2.setAlignmentY(Component.CENTER_ALIGNMENT);
		JLabel label4 = ComponentFactory.createBasicLabel("     Hits: 0/0", Color.WHITE);
		label4.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		if(player == 1){
			player1shots = label3;
			player1hits = label4;
			player1rounds = label2;
		} else {
			player2shots = label3;
			player2hits = label4;
			player2rounds = label2;
		}
		
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.add(label4);
		
		return panel;
	}
	
	public void takeDown(){
		removeAll();
		revalidate();
		repaint();
	}
	
	public void setUp(){
		if (logoPanel == null){
			logoPanel = createLogoPanel(gameLogo);
		}
		if (player1Info == null){
			player1Info = createInfoPanel(1);
		}
		if (player2Info == null){
			player2Info = createInfoPanel(2);
		}
		removeAll();
		add(logoPanel,BorderLayout.WEST);
		add(player1Info, BorderLayout.EAST);
		switched = false;
		revalidate();
		repaint();

	}
	
	public void switchPlayers(){
		if(!switched){
			removeAll();
			add(player1Info, BorderLayout.WEST);
			add(logoPanel,BorderLayout.EAST);
			switched = true;
			revalidate();
			repaint();
		} else {
			removeAll();
			add(logoPanel,BorderLayout.WEST);
			add(player1Info, BorderLayout.EAST);
			switched = false;
			revalidate();
			repaint();
		}
	}
	
	public void setPlayer1Shots(int shots, int hits){
		player1shots.setText("                Shots: " + shots);
		player1hits.setText("    Hits: " + hits + "/" + shots);
		revalidate();
		repaint();
	}
	
	public void setPlayer2Shots(int shots, int hits){
		player2shots.setText("                Shots: " + shots);
		player2hits.setText("    Hits: " + hits + "/" + shots);
		revalidate();
		repaint();
	}
	
	public void setRounds(int rounds){
		player1rounds.setText("    Round: " + rounds) ;
		player2rounds.setText("    Round: " + rounds);
		revalidate();
		repaint();
	}
	
	
	public void reset(){
		removeAll();
		logoPanel = null;
		player1Info = null;
		player2Info = null;
		player1shots = null;
		player2shots = null;
		switched = false;
		revalidate();
		repaint();
	}
	
	
}
