package guiUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import entities.Ship;

public class ShipIconButton extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5101370921051275768L;
	private boolean placed = false;
	private Ship tied;
	private String shipTypeKey;
	private ImageIcon image;
	

	public ShipIconButton(int width, int height, ImageIcon img, Color c, Color c2,String type){
		setAlignmentX(Component.CENTER_ALIGNMENT);
		setMaximumSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width,height));
		setFont(new Font(Font.SERIF,Font.BOLD,18));
		setIcon(img);
		setBorder(new LineBorder(c2));
		setFocusable(false);
		setOpaque(true);
		setForeground(c);
		setFocusPainted(false);
		setBackground(Color.BLACK);
		
		shipTypeKey = type;
		image = img;
		
		addMouseListener(new MouseAdapter(){

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		
			@Override
			public void mousePressed(MouseEvent e) {
				setBorder(new BevelBorder(BevelBorder.LOWERED, c, c));
				setBackground(Color.BLACK);
				
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				setBorder(new LineBorder(Color.WHITE));	
			}});

	}

	public boolean isPlaced(){
		return placed;
	}
	
	public void setPlaced(boolean b){
		placed = b;
		
		if (b){
			setIcon(null);
			setText("Remove " + shipTypeKey);
		} else {
			setIcon(image);
			setText("");
		}
		
	}
	
	public void setTiedShip(Ship s){
		tied = s;
	}
	
	public Ship getTiedShip(){
		return tied;
	}
	
	public String getShipTypeKey(){
		return shipTypeKey;
	}
	

}
