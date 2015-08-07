package guiPanels;

import entities.Player;
import gameEngine.GameManager;
import guiUI.ComponentFactory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import audio.AudioManager;

public class PlayerSelectionPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2030312313721296301L;
	JTextField newPlayerNameField;
	Player selected;
	int player;
	JLabel label2;
	PlayerList playerMenu;
	StartPanel startPanel;
	
	PlayerSelectionPanel(int player, StartPanel sp){
		this.player = player;
		startPanel = sp;
		JLabel label1 = ComponentFactory.createHeaderLabel((player == 1) ? "Player 1: " : "Player 2: ",(player == 1) ? Color.RED : Color.BLUE );
		label2 = ComponentFactory.createBasicLabel("No player selected.",(player == 1) ? Color.RED : Color.BLUE);
		label2.setForeground(Color.WHITE);
		JLabel message = ComponentFactory.createBasicLabel("",(player == 1) ? Color.RED : Color.BLUE);
		message.setForeground(Color.WHITE);
		newPlayerNameField = ComponentFactory.createTextField("Enter name here",(player == 1) ? Color.RED : Color.BLUE);
		newPlayerNameField.addActionListener(new ActionListener(){

			@Override
            public void actionPerformed(ActionEvent e) {
                if(!newPlayerNameField.getText().equals("Enter name here") && newPlayerNameField.getText().trim().length() > 0){
                    boolean taken = false;
                    for (Player p : GameManager.getPlayers()){
                        if(p.getName().equals(newPlayerNameField.getText())){
                            taken = true;
                        }   
                    }
                    if(!taken){
                        Player wow = new Player(newPlayerNameField.getText(), 0, 0);
                        GameManager.addPlayers(wow);
                        GameManager.savePlayers();
      
                    
                        newPlayerNameField.setText("Enter name here");
                        newPlayerNameField.setFocusable(false);
                        newPlayerNameField.setFocusable(true);
                        AudioManager.setSound(3);
                        message.setText("");
                        playerMenu.refresh();
                    } else {
                        newPlayerNameField.setText("Enter name here");
                        newPlayerNameField.setFocusable(false);
                        newPlayerNameField.setFocusable(true);
                        AudioManager.setSound(1);
                        message.setText("PLayer name taken. Try again.");
                    }
                }
            }});
		JButton btn1 = ComponentFactory.createJButton("Create Player", 120,(player == 1) ? Color.RED : Color.BLUE);
		playerMenu = new PlayerList(this);
		btn1.addActionListener(new ActionListener(){

			@Override
            public void actionPerformed(ActionEvent e) {
                if(!newPlayerNameField.getText().equals("Enter name here") && newPlayerNameField.getText().trim().length() > 0){
                    boolean taken = false;
                    for (Player p : GameManager.getPlayers()){
                        if(p.getName().equals(newPlayerNameField.getText())){
                            taken = true;
                        }   
                    }
                    if(!taken){
                        Player wow = new Player(newPlayerNameField.getText(), 0, 0);
                        GameManager.addPlayers(wow);
                        GameManager.savePlayers();
                        newPlayerNameField.setText("Enter name here");
                        newPlayerNameField.setFocusable(false);
                        newPlayerNameField.setFocusable(true);
                        AudioManager.setSound(3);
                        message.setText("");
                        playerMenu.refresh();
                    } else {
                        newPlayerNameField.setText("Enter name here");
                        newPlayerNameField.setFocusable(false);
                        newPlayerNameField.setFocusable(true);
                        AudioManager.setSound(1);
                        message.setText("PLayer name taken. Try again.");
                    }
                }
            }});
		JButton btn3 = ComponentFactory.createJButton("Continue", 100,(player == 1) ? Color.RED : Color.BLUE);
		btn3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(selected != null && player == 1){
					GameManager.setPlayer1(selected);
					AudioManager.setSound(3);
					sp.next();

				} else if(selected != null && player == 2){
					GameManager.setPlayer2(selected);
					AudioManager.setSound(3);
					sp.next();
				} else {
					AudioManager.setSound(1);
				}
			}});
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(Box.createRigidArea(new Dimension(330,30)));
		add(ComponentFactory.createHeaderLabel((player == 1) ? "Player 1" : "Player 2",(player == 1) ? Color.RED : Color.BLUE));
		add(Box.createRigidArea(new Dimension(330,30)));
		add(ComponentFactory.createBasicLabel("Create New Player",(player == 1) ? Color.RED : Color.BLUE));
		add(newPlayerNameField);
		add(Box.createRigidArea(new Dimension(330,20)));
		add(btn1);
		add(Box.createRigidArea(new Dimension(330,20)));
		add(message);
		add(Box.createRigidArea(new Dimension(330,30)));
		add(ComponentFactory.createBasicLabel("Select Player",(player == 1) ? Color.RED : Color.BLUE));
		add(ComponentFactory.createScrollPane(playerMenu,(player == 1) ? Color.RED : Color.BLUE));
		add(Box.createRigidArea(new Dimension(330,20)));
		add(label1);
		add(Box.createRigidArea(new Dimension(330,10)));
		add(label2);
		add(Box.createRigidArea(new Dimension(330,30)));
		add(btn3);
		setOpaque(false);
		setVisible(true);
	}
	
	void select(Player p){
		selected = p;
		label2.setText(p.getName());
		label2.repaint();
		
		
	}
	
	public void updatePlayerMenu(){
		playerMenu.refresh();
	}

}
