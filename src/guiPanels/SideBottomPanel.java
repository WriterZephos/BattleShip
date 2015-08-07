package guiPanels;

import entities.Ship;
import gameEngine.GameManager;
import gameEngine.ShipPlacer;
import guiUI.ShipIconButton;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import audio.AudioManager;

public class SideBottomPanel extends ControlPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3760925171750781543L;
	JPanel sideStartPanel1;
	JPanel sideStartPanel2;
	boolean switched = false;
	ShipIconButton selected1;
	ShipIconButton selected2;
	Boolean horizontal = false;
	Map<String,Ship> activeShips;
	boolean donePlacing = false;
	JPanel radarPanel;
	ImageIcon radar;

	SideBottomPanel(int xv, int yv, Image img, ImageIcon rad) {
		super(xv, yv, img);
		GameManager.resetShips();
		GameManager.activatePlayer1Ships();
		sideStartPanel1 = createSideStartPanel(1);
		sideStartPanel2 = createSideStartPanel(2);
		add(sideStartPanel1);
		radar = rad;
		
	}
	
	private JPanel createSideStartPanel(int player){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setOpaque(false);
		
		ShipIconButton carrierButton = new ShipIconButton(250,59,GameManager.getGameWindow().getTextures()[5 + ((player == 1) ? 0 : 1)],(player == 1) ? Color.RED : Color.BLUE,(player == 1) ? Color.BLUE : Color.RED, "Carrier");
		initializeButton(carrierButton, "Carrier", player);
		ShipIconButton ship2Button = new ShipIconButton(225,61,GameManager.getGameWindow().getTextures()[7 + ((player == 1) ? 0 : 1)],(player == 1) ? Color.RED : Color.BLUE,(player == 1) ? Color.BLUE : Color.RED,"Battleship");
		initializeButton(ship2Button, "Battleship", player);
		ShipIconButton ship3Button = new ShipIconButton(200,51,GameManager.getGameWindow().getTextures()[9 + ((player == 1) ? 0 : 1)],(player == 1) ? Color.RED : Color.BLUE,(player == 1) ? Color.BLUE : Color.RED,"Destroyer");
		initializeButton(ship3Button, "Destroyer", player);
		ShipIconButton ship4Button = new ShipIconButton(200,51,GameManager.getGameWindow().getTextures()[13 + ((player == 1) ? 0 : 1)],(player == 1) ? Color.RED : Color.BLUE,(player == 1) ? Color.BLUE : Color.RED,"Submarine");
		initializeButton(ship4Button, "Submarine", player);
		ShipIconButton ship5Button = new ShipIconButton(175,46,GameManager.getGameWindow().getTextures()[11 + ((player == 1) ? 0 : 1)],(player == 1) ? Color.RED : Color.BLUE,(player == 1) ? Color.BLUE : Color.RED,"Patrol Boat");
		initializeButton(ship5Button, "Patrol Boat", player);
		
		panel.add(ship5Button);
		panel.add(Box.createRigidArea(new Dimension(330,10)));
		
		panel.add(ship4Button);
		panel.add(Box.createRigidArea(new Dimension(330,10)));
		
		panel.add(ship3Button);
		panel.add(Box.createRigidArea(new Dimension(330,10)));
		
		panel.add(ship2Button);
		panel.add(Box.createRigidArea(new Dimension(330,10)));
		
		panel.add(carrierButton);
		panel.add(Box.createRigidArea(new Dimension(330,10)));
		
		panel.setVisible(true);
		return panel;
	}
	
	private JPanel createSideGamePanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		JLabel label = new JLabel();
		label.setIcon(radar);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(Box.createRigidArea(new Dimension(330,40)));
		panel.add(label);
		
		panel.setOpaque(false);
		panel.setVisible(true);
		return panel;
	}
	
	private JButton initializeButton(ShipIconButton button, String key, int player){
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				boolean playSound = true;
				if(button.isPlaced()){
					button.setPlaced(false);
					playSound = false;
					ShipPlacer.removeShip(button.getTiedShip());
					button.setTiedShip(null);
				}
			
				if(!switched){
					if (selected1 != null && selected1 != button){
						ShipPlacer.setActiveShip(GameManager.getActiveShips().get(key));
						ShipPlacer.setPlacing(true);
						selected1.setBorder(new LineBorder((player == 1) ? Color.BLUE : Color.RED));
						selected1 = button;
						if(playSound) AudioManager.setSound(3);
						button.setBorder(new LineBorder(Color.WHITE));
						GameManager.getGameWindow().getBoardArea().getPanel5().setShipLabel1Text(key + " selected.");
					} else {
						ShipPlacer.setActiveShip(GameManager.getActiveShips().get(key));
						ShipPlacer.setPlacing(true);
						selected1 = button;
						if(playSound) AudioManager.setSound(3);
						button.setBorder(new LineBorder(Color.WHITE));
						GameManager.getGameWindow().getBoardArea().getPanel5().setShipLabel1Text(key + " selected.");
					}
				} else {
					if (selected2 != null && selected2 != button){
						ShipPlacer.setActiveShip(GameManager.getActiveShips().get(key));
						//ShipPlacer.setActiveShip(new DemoShip());
						ShipPlacer.setPlacing(true);
						selected2.setBorder(new LineBorder((player == 1) ? Color.BLUE : Color.RED));
						selected2 = button;
						if(playSound) AudioManager.setSound(3);
						button.setBorder(new LineBorder(Color.WHITE));
						GameManager.getGameWindow().getBoardArea().getPanel5().setShipLabel2Text(key + " selected.");
					} else {
						ShipPlacer.setActiveShip(GameManager.getActiveShips().get(key));
						//ShipPlacer.setActiveShip(new DemoShip());
						ShipPlacer.setPlacing(true);
						selected2 = button;
						if(playSound) AudioManager.setSound(3);
						button.setBorder(new LineBorder(Color.WHITE));
						GameManager.getGameWindow().getBoardArea().getPanel5().setShipLabel2Text(key + " selected.");
					}
				}
			}
		});
		
		button.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseEntered(MouseEvent e) {
				AudioManager.setSound(5);
				button.setBorder(new LineBorder(Color.WHITE));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if ((player == 1 ? selected1 : selected2) == button){
					button.setBorder(new LineBorder(Color.WHITE));
				} else {
					button.setBorder(new LineBorder(player == 1 ? Color.BLUE : Color.RED));
				}	
			}
		
			@Override
			public void mousePressed(MouseEvent e) {
		
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}});
		
		
		return button;
	}
	
	public void setButtonPlaced(){
		if(switched){
			selected2.setPlaced(true);
			selected2.setTiedShip(ShipPlacer.getActiveShip());
		} else {
			selected1.setPlaced(true);
			selected1.setTiedShip(ShipPlacer.getActiveShip());
		}
	}
	
	public void switchPlayers(){
		if (switched){
			switched = false;
			ShipPlacer.reset();
			GameManager.activatePlayer1Ships();
			removeAll();
			if(donePlacing){
				add(radarPanel);
			}else {
				add(sideStartPanel1);
			}
			revalidate();
			repaint();
		} else {
			switched = true;
			ShipPlacer.reset();
			GameManager.activatePlayer2Ships();
			removeAll();
			if(donePlacing){
				add(radarPanel);
			}else {
				add(sideStartPanel2);
			}
			revalidate();
			repaint();
		}	
	}
	
	public void donePlacing(){
		if(radarPanel == null){
			radarPanel = createSideGamePanel();
		}
		donePlacing = true;
	}
}



