package guiUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import audio.AudioManager;

public class ComponentFactory {

	private static Font headerFont = new Font(Font.SERIF,Font.BOLD,24);
	private static Font bigFont = new Font(Font.SERIF,Font.BOLD,38);
	private static Font basicFont = new Font(Font.SERIF,Font.BOLD,18);
	private static Font weakFont = new Font(Font.SERIF,Font.PLAIN,18);
	
	public static JLabel createHeaderLabel(String text, Color c){
		JLabel label = new JLabel(text);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setForeground(c);
		label.setFont(headerFont);
		return label;
	}
	
	public static JLabel createBigHeaderLabel(String text, Color c){
		JLabel label = new JLabel(text);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setForeground(c);
		label.setFont(bigFont);
		return label;
	}
	
	public static JLabel createBasicLabel(String text, Color c){
		JLabel label = new JLabel(text);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setForeground(c);
		label.setFont(basicFont);
		return label;
	}
	
	public static JScrollPane createScrollPane(Component client, Color c){
		JScrollPane pane = new JScrollPane(client);
		pane.setAlignmentX(Component.CENTER_ALIGNMENT);
		pane.setMaximumSize(new Dimension(500,200));
		pane.setPreferredSize(new Dimension(500,200));
		pane.setForeground(c);
		pane.setFont(basicFont);
		pane.setBackground(Color.BLACK);
		pane.setBorder(new LineBorder(c));
		JPanel corner = new JPanel();
		corner.setBackground(Color.BLACK);
		corner.setBorder(new LineBorder(c));
		pane.getHorizontalScrollBar().setUI(new CoolScrollBarUI(c));
		pane.getVerticalScrollBar().setUI(new CoolScrollBarUI(c));
		pane.setCorner(JScrollPane.LOWER_RIGHT_CORNER, corner);
		return pane;
	}
	
	public static JButton createJButton(String text, int width, Color c){
		JButton button = new JButton(text);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setMaximumSize(new Dimension(width, 30));
		button.setPreferredSize(new Dimension(width,30));
		button.setVerticalAlignment(SwingConstants.BOTTOM);
		button.setBorder(new LineBorder(c));
		button.setFocusable(false);
		button.setOpaque(true);
		button.setForeground(c);
		button.setFocusPainted(false);
		button.setFont(basicFont);
		button.setBackground(Color.BLACK);
		button.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseEntered(MouseEvent e) {
				AudioManager.setSound(5);
				button.setBorder(new LineBorder(Color.WHITE));
				button.setBackground(Color.BLACK);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button.setBorder(new LineBorder(c));
				
			}
		
			@Override
			public void mousePressed(MouseEvent e) {
				button.setBorder(new BevelBorder(BevelBorder.LOWERED, c, c));
				button.setBackground(Color.BLACK);
				button.setForeground(c);
				
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				button.setBorder(new LineBorder(Color.WHITE));
				button.setForeground(c);
				
			}});
			
		return button;
	}
	
	public static JTextField createTextField(String s, Color c){
		JTextField field = new JTextField(s,25);
		field.setAlignmentX(Component.CENTER_ALIGNMENT);
		field.setMaximumSize(new Dimension(330, 30));
		field.setPreferredSize(new Dimension(330,30));
		field.setBorder(new LineBorder(c));
		field.setForeground(Color.WHITE);
		field.setFont(weakFont);
		field.setBackground(Color.BLACK);
		field.setCaretColor(Color.WHITE);
		field.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				if(field.getText().equals(s)){
					field.setText("");
					field.setFont(basicFont);
					field.setCaretPosition(0);
				}
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(field.getText().isEmpty()){
					field.setFont(weakFont);
					field.setText(s);
				}
				
			}
			
		});
		return field;
	}
	
	public static JButton createIconButton(int width, int height, ImageIcon img, Color c, Color c2){
		JButton button = new JButton();
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setMaximumSize(new Dimension(width, height));
		button.setPreferredSize(new Dimension(width,height));
		button.setIcon(img);
		button.setBorder(new LineBorder(c2));
		button.setFocusable(false);
		button.setOpaque(true);
		button.setForeground(c);
		button.setFocusPainted(false);
		button.setBackground(Color.BLACK);
		button.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseEntered(MouseEvent e) {
				AudioManager.setSound(5);
				button.setBorder(new LineBorder(c));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button.setBorder(new LineBorder(c2));
			}
		
			@Override
			public void mousePressed(MouseEvent e) {
		
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}});
			
		return button;

	}
}
