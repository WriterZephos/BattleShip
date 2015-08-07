package guiPanels;

import gameEngine.GameManager;
import gameEngine.ShipPlacer;
import gameEngine.StatTracker;
import guiUI.ComponentFactory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import audio.AudioManager;

public class SideTopPanel extends ControlPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4437897461735499673L;
	JPanel sideStartPanel1;
	JPanel sideStartPanel2;
	boolean switched = false;
	boolean horizontal = false;
	JButton button1;
	JButton button2;
	JLabel shipLabel1;
	JLabel shipLabel2;
	JLabel orientationLabel1;
	JLabel orientationLabel2;
	JLabel warningLabel1;
	JLabel warningLabel2;
	boolean donePlacing;
	JPanel gamePanelA;
	JPanel gamePanelB;
	JLabel warningLabelA;
	JLabel warningLabelB;
	JLabel shotLabelA;
	JLabel shotLabelB;
	JLabel fleetLabelA;
	JLabel fleetLabelB;

	//Map<String, Ship> activeShips;
	
	SideTopPanel(int xv, int yv, Image img) {
		super(xv, yv, img);
		sideStartPanel1 = createSideStartPanel(1);
		sideStartPanel2 = createSideStartPanel(2);
		add(sideStartPanel1);
	}
	
	
	private JPanel createSideStartPanel(int player){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setOpaque(false);
		
		JLabel label = ComponentFactory.createHeaderLabel("No ship selected", (player == 1) ? Color.RED : Color.BLUE);
		label.setForeground(Color.WHITE);
		
		JLabel wlabel = ComponentFactory.createHeaderLabel(" ", (player == 1) ? Color.RED : Color.BLUE);
		wlabel.setForeground(Color.WHITE);
		
		JLabel orientationLabel = ComponentFactory.createBasicLabel("Orientation: Vertical",(player == 1) ? Color.RED : Color.BLUE);
		orientationLabel.setForeground(Color.WHITE);
		
		JButton button = ComponentFactory.createJButton("Horizontal",100,(player == 1) ? Color.RED : Color.BLUE);
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(horizontal){
					ShipPlacer.setHorizontal(false);
					button.setText("Horizontal");
					AudioManager.setSound(3);
					orientationLabel1.setText("Orientation: Vertical");
					orientationLabel2.setText("Orientation: Vertical");
					horizontal = false;
				} else {
					ShipPlacer.setHorizontal(true);
					button.setText("Vertical");
					AudioManager.setSound(3);
					orientationLabel1.setText("Orientation: Horizontal");
					orientationLabel2.setText("Orientation: Horizontal");
					horizontal = true;
				}
				
			}
			
		});
		
		JButton next = ComponentFactory.createJButton("Continue", 100, (player == 1) ? Color.RED : Color.BLUE);
		next.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(ShipPlacer.allPlaced(player)){
					AudioManager.setSound(3);
					GameManager.getGameWindow().getBoardArea().next();
				} else if (player == 1){
					warningLabel1.setText("Not all ships are placed.");
					AudioManager.setSound(1);
					revalidate();
					repaint();
				} else if (player == 2){
					warningLabel2.setText("Not all ships are placed.");
					AudioManager.setSound(1);
					revalidate();
					repaint();
				}
					
				
			}});
		
		if(player == 1){
			button1 = button;
			shipLabel1 = label;
			orientationLabel1 = orientationLabel;
			warningLabel1 = wlabel;
		} else {
			button2 = button;
			shipLabel2 = label;
			orientationLabel2 = orientationLabel;
			warningLabel2 = wlabel;
		}
		
		panel.add(Box.createRigidArea(new Dimension(330,15)));
		panel.add(ComponentFactory.createHeaderLabel("PLayer " + player,(player == 1) ? Color.RED : Color.BLUE));
		panel.add(Box.createRigidArea(new Dimension(330,15)));
		panel.add(ComponentFactory.createBasicLabel("Place your ships",(player == 1) ? Color.RED : Color.BLUE));
		panel.add(Box.createRigidArea(new Dimension(330,15)));
		panel.add(label);
		panel.add(wlabel);
		panel.add(Box.createRigidArea(new Dimension(330,15)));
		panel.add(orientationLabel);
		panel.add(Box.createRigidArea(new Dimension(330,15)));
		panel.add(button);
		panel.add(Box.createRigidArea(new Dimension(330,15)));
		panel.add(next);
		panel.setVisible(true);
		wlabel.setText("");
		return panel;
	}
	
	private JPanel createSideGamePanel(int player){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setOpaque(false);
		
		JLabel label = ComponentFactory.createBigHeaderLabel("Call your shot." + player, Color.WHITE);
		
		JLabel wlabel = ComponentFactory.createHeaderLabel(" ", Color.WHITE);
		
		JLabel fleetLabel = ComponentFactory.createBasicLabel("Fleet status: 17/17" + player, Color.WHITE);
		
		JButton next = ComponentFactory.createJButton("Continue", 100, (player == 1) ? Color.RED : Color.BLUE);
		next.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(GameManager.isShotCalled()){
					AudioManager.setSound(1);
					GameManager.getGameWindow().getBoardArea().next();
				} else if (player == 1){
					AudioManager.setSound(3);
					warningLabelA.setText("Please choose a square.");
					AudioManager.setSound(1);
					revalidate();
					repaint();
				} else if (player == 2){
					AudioManager.setSound(1);
					warningLabelB.setText("Please choose a square.");
					AudioManager.setSound(1);
					revalidate();
					repaint();
				}
			}});
		
		if(player == 1){
			shotLabelA = label;
			fleetLabelA = fleetLabel;
			warningLabelA = wlabel;
		} else {
			shotLabelB = label;
			fleetLabelB = fleetLabel;
			warningLabelB = wlabel;
		}
		
		panel.add(Box.createRigidArea(new Dimension(330,15)));
		panel.add(ComponentFactory.createHeaderLabel("PLayer " + player,(player == 1) ? Color.RED : Color.BLUE));
		panel.add(Box.createRigidArea(new Dimension(330,30)));
		panel.add(label);
		panel.add(Box.createRigidArea(new Dimension(330,30)));
		panel.add(wlabel);
		panel.add(Box.createRigidArea(new Dimension(330,30)));
		panel.add(next);
		panel.add(Box.createRigidArea(new Dimension(330,30)));
		panel.add(fleetLabel);
		
		return panel;
	}

	private JPanel createEndGamePanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setOpaque(false);
		
		JLabel label = ComponentFactory.createBigHeaderLabel("Game Over.", (StatTracker.getWinner() == 1) ? Color.RED : Color.BLUE);
		
		JLabel scoreLabel1 = ComponentFactory.createBasicLabel("PLayer 1 score: " + StatTracker.calculatePlayer1Score(), Color.RED);
		JLabel scoreLabel2 = ComponentFactory.createBasicLabel("PLayer 2 score: " + StatTracker.calculatePlayer2Score(), Color.BLUE);
		
		JButton next = ComponentFactory.createJButton("Continue", 100, (StatTracker.getWinner() == 1) ? Color.RED : Color.BLUE);
		next.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
			
				AudioManager.setSound(3);
				GameManager.getGameWindow().getBoardArea().next();
				
			}});
		
		panel.add(Box.createRigidArea(new Dimension(330,15)));
		panel.add(label);
		panel.add(Box.createRigidArea(new Dimension(330,30)));
		panel.add(scoreLabel1);
		panel.add(Box.createRigidArea(new Dimension(330,30)));
		panel.add(scoreLabel2);
		panel.add(Box.createRigidArea(new Dimension(330,30)));
		panel.add(next);
		
		return panel;
	}
	
	
	
	
	public void setShotLabelAText(String s){
		shotLabelA.setText(s);
		revalidate();
		repaint();
	}
	
	public void setShotLabelBText(String s){
		shotLabelB.setText(s);
		revalidate();
		repaint();
	}
	
	public void fleetLabelAText(int fleet){
		fleetLabelA.setText("Fleet status: " + fleet + "/17 ");
		revalidate();
		repaint();
	}
	
	public void fleetLabelBText(int fleet){
		fleetLabelB.setText("Fleet status: " + fleet + "/17 ");
		revalidate();
		repaint();
	}
	
	public void setShipLabel1Text(String s){
		shipLabel1.setText(s);
		warningLabel1.setText("");
		revalidate();
		repaint();
	}
	
	public void setShipLabel2Text(String s){
		shipLabel2.setText(s);
		warningLabel2.setText("");
		revalidate();
		repaint();
	}
	
	public void donePlacing(){
		if(gamePanelA == null || gamePanelB == null){
			gamePanelA = createSideGamePanel(1);
			gamePanelB= createSideGamePanel(2);
		}
		donePlacing = true;
		horizontal = false;
		ShipPlacer.setHorizontal(false);
		sideStartPanel1 = null;
		sideStartPanel2 = null;
		button1 = null;
		button2 = null;
		shipLabel1 = null;
		shipLabel2 = null;
		orientationLabel1 = null;
		orientationLabel2 = null;
		warningLabel1 = null;
		warningLabel2 = null;
	}
	
	public void switchPlayers(){
		if(!donePlacing){
			if (switched){
				switched = false;
				horizontal = false;
				ShipPlacer.setHorizontal(false);
				button1.setText("Horizontal");
				button2.setText("Horizontal");
				shipLabel1.setText("No ship selected");
				shipLabel2.setText("No ship selected");
				orientationLabel1.setText("Orientation: Vertical");
				orientationLabel2.setText("Orientation: Vertical");
				removeAll();
				add(sideStartPanel1);
				revalidate();
				repaint();
			} else {
				switched = true;
				horizontal = false;
				ShipPlacer.setHorizontal(false);
				button1.setText("Horizontal");
				button2.setText("Horizontal");
				shipLabel1.setText("No ship selected");
				shipLabel2.setText("No ship selected");
				orientationLabel1.setText("Orientation: Vertical");
				orientationLabel2.setText("Orientation: Vertical");
				removeAll();
				add(sideStartPanel2);
				revalidate();
				repaint();
			}	
		} else {
			if(switched){
				switched = false;
				shotLabelA.setText("Call your shot.");
				shotLabelB.setText("Call your shot.");
				removeAll();
				add(gamePanelA);
				revalidate();
				repaint();
			} else {
				switched = true;
				
				shotLabelA.setText("Call your shot.");
				shotLabelB.setText("Call your shot.");
				removeAll();
				add(gamePanelB);
				revalidate();
				repaint();
			}	
		}
	}

	public void showEndScreen(){
		removeAll();
		add(createEndGamePanel());
		revalidate();
		repaint();
	}

}
