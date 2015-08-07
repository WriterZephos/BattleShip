package guiPanels;

import guiUI.BattleShipButton;
import guiUI.GameButton;
import guiUI.LabelButton;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameBoard extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1660572626209881322L;
	boolean mainBoard;
	ImageIcon[] textures;
	GameButton[][] buttons;
	int player;
	
	
	GameBoard(char[] topLabels, char[] leftLabels, ImageIcon[] tex, boolean main, int player){
		
		textures = tex;
		mainBoard = main;
		buttons = new GameButton[11][11];
		
		setPreferredSize(new Dimension(330,330));
		setLayout(new GridLayout(11, 11, 0, 0));
		
		int labelSide = (player == 1) ? 0 : 10;
		int addZero = (player == 1) ? 10 : 9;
		int blankIndex = (player == 1) ? 0 : 1;
		
			for (int i = 0; i<11; i++){
				
				for (int j = 0; j < 11; j++){
					
					GameButton button;
					
					if (i==0){
						if(j == labelSide){
							button = new LabelButton("" + topLabels[0],textures[3]);
						} else{
							button = new LabelButton("" + topLabels[j + blankIndex] + (j == addZero ? "0" : ""),textures[1]);
						}
						buttons[i][j] = button;
						add((LabelButton) button);
						
					} else if (j==labelSide){
						button = new LabelButton("" + leftLabels[i],textures[1]);
						buttons[i][j] = button;
						add((LabelButton) button);
					} else {
						button = new BattleShipButton(textures[0],j,i,this,labelSide);
						buttons[i][j] = button;
						add((BattleShipButton) button);
					}	
				}	
			} 
	}
	
	public boolean isMainBoard(){
		return mainBoard;
	}
	
	public GameButton[][] getButtons(){
		return buttons;
	}
}
