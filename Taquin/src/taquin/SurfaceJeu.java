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
	public void ecritLesNombres(ArrayList<Integer> tArray, Graphics g){
		g.drawString(tArray.get(0).toString(), this.getWidth()/6, this.getHeight()/6);
		g.drawString(tArray.get(1).toString(), 3*this.getWidth()/6, this.getHeight()/6);
		g.drawString(tArray.get(2).toString(), 5*this.getWidth()/6, this.getHeight()/6);
		g.drawString(tArray.get(3).toString(), this.getWidth()/6, 3*this.getHeight()/6);
		g.drawString(tArray.get(4).toString(), 3*this.getWidth()/6, 3*this.getHeight()/6);
		g.drawString(tArray.get(5).toString(), 5*this.getWidth()/6, 3*this.getHeight()/6);
		g.drawString(tArray.get(6).toString(), this.getWidth()/6, 5*this.getHeight()/6);
		g.drawString(tArray.get(7).toString(), 3*this.getWidth()/6, 5*this.getHeight()/6);
		g.drawString(tArray.get(8).toString(), 5*this.getWidth()/6, 5*this.getHeight()/6);
	}
}
