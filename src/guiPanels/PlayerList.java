package guiPanels;

import entities.Player;
import gameEngine.GameManager;
import guiUI.ComponentFactory;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import audio.AudioManager;

public class PlayerList extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 358734581821115347L;
	Font basicFont;
	PlayerSelectionPanel playerSelect;
	JButton highlighted;
	ArrayList<JButton> buttons;
	int selectedButtonIndex;
	
	
	PlayerList(PlayerSelectionPanel psp){
		
		playerSelect = psp;
		basicFont = new Font(Font.MONOSPACED,Font.PLAIN,14);
		setForeground(Color.WHITE);
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		setBackground(Color.BLACK);
		setFont(basicFont);
		setPreferredSize(new Dimension(450,(GameManager.getPlayers().size() + 1)*30));
		add(createContents());
		setVisible(true);
	}
	
	private JPanel createContents(){
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		ArrayList<Player> players = GameManager.getPlayers();
		panel.setLayout(new GridLayout(players.size() + 1,1,0,0));
		String header = String.format(" %-25s%-18s%s","Name","High Score","Overall Score");
		JLabel label = ComponentFactory.createBasicLabel(header, Color.WHITE);
		label.setFont(basicFont);
		panel.add(label);
		buttons = new ArrayList<>();
		
		
		for (int i = 0; i < players.size(); i++){
			JButton button = createButton(players.get(i),i);
			buttons.add(button);
			panel.add(button);
		}
		
		return panel;
		
	}
	
	private JButton createButton(Player p, int i){
		JButton button = new JButton(p.toStringNoComma());
		button.setAlignmentX(Component.LEFT_ALIGNMENT);
		button.setMinimumSize(new Dimension(450, 25));
		button.setMaximumSize(new Dimension(450, 25));
		button.setPreferredSize(new Dimension(450,25));
		button.setVerticalAlignment(JButton.BOTTOM);
		button.setHorizontalAlignment(JButton.LEFT);
		button.setFocusPainted(false);
		button.setBorder(null);
		button.setOpaque(true);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setForeground(Color.WHITE);
		button.setFont(basicFont);
		button.setBackground(Color.BLACK);
		if (GameManager.getPlayer1() == p || GameManager.getPlayer2() == p){
			button.setForeground(Color.DARK_GRAY);
		}
		button.addActionListener(new ActionListener(){
		
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(GameManager.getPlayer1() == p) && !(GameManager.getPlayer2() == p)){
					playerSelect.select(p);
					highlight(button);
					selectedButtonIndex = i;
					AudioManager.setSound(3);
				}
			}});
		
		return button;
	}
	
	private void highlight(JButton button){
		if(highlighted == null){
			highlighted = button;
			button.setBorder(new LineBorder(Color.WHITE));
		} else {
			highlighted.setBorder(null);
			highlighted = button;
			button.setBorder(new LineBorder(Color.WHITE));
		}
		
		
	}
		
	public void refresh(){
		removeAll();
		setPreferredSize(new Dimension(450,(GameManager.getPlayers().size() + 1)*30));
		add(createContents());
		revalidate();
		repaint();
		
	}

}
