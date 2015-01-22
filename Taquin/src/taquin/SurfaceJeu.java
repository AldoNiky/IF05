package taquin;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class SurfaceJeu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void paintComponent(Graphics g){
		g.drawLine(this.getWidth()/3, 0, this.getWidth()/3, this.getHeight());
		g.drawLine(2*this.getWidth()/3, 0, 2*this.getWidth()/3, 2*this.getHeight());
		g.drawLine(0, this.getHeight()/3, this.getWidth(), this.getHeight()/3);
		g.drawLine(0, 2*this.getHeight()/3, this.getWidth(), 2*this.getHeight()/3);
	}
	public void ecritLesNombres(ArrayList<Integer> tArray){
		
	}
}
