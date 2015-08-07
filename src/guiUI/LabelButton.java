package guiUI;

import entities.Ship;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class LabelButton extends JButton implements GameButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4022246874887880451L;

	public LabelButton(String text,ImageIcon img){
		
		setText(text);
		setMinimumSize(new Dimension(30,30));
		setPreferredSize(new Dimension(30,30));
		setMinimumSize(new Dimension(30,30));
		setMargin(new Insets(0,0,0,0));
		setBorder(new LineBorder(Color.DARK_GRAY));
		setFont(new Font(Font.SERIF,Font.BOLD, 14));
		setForeground(Color.WHITE);
		setOpaque(false);
		setBackground(Color.BLACK);
		setHorizontalTextPosition(JButton.CENTER);
		setVerticalTextPosition(JButton.CENTER);
		setIcon(img);
		setContentAreaFilled(true);
		setVisible(true);
		
		addMouseListener(new MouseListener());
	}

	@Override
	public boolean isOccupied() {
		return true;
	}
	
	private class MouseListener extends MouseAdapter{

		
		@Override
		public void mouseEntered(MouseEvent e) {
			

		}
	
		@Override
		public void mouseExited(MouseEvent e) {

			
		}

	}

	public void hover(boolean possible){
		Border border = new LineBorder(Color.RED);
		setBorder(border);
		setBorderPainted(true);
	}
	
	public void reset(){
		Border border = new LineBorder(Color.DARK_GRAY);
		setBorder(border);
		setBorderPainted(true);
	}

	
	@Override
	public void setHit() {

	}

	@Override
	public void setMiss() {

	}

	@Override
	public Ship getOccupyingShip() {
		return null;
	}
	

}
