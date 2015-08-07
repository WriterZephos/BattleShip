package guiPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class ControlPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2111334859559821641L;
	int x;
	int y;
	Image image;
	
	
	ControlPanel(int xv, int yv, Image img){
		
		setOpaque(true);
		setVisible(true);
		setBackground(Color.RED);
		setPreferredSize(new Dimension(xv, yv));
	
		x = xv;
		y = yv;
		image = img;
	}
	
	
	
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(image, 0,0,x, y,null);
		
	}
	
	
}
