package taquin;

import java.util.ArrayList;

import javax.swing.JFrame;

public class Fenetre extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Fenetre (ArrayList<Integer> tArray){
		setVisible(true);
		this.setSize(300, 300);
		this.setTitle("Jeu de Taquin");
		SurfaceJeu pan = new SurfaceJeu();
		this.setContentPane(pan);
		pan.paintComponent(getGraphics());
		pan.ecritLesNombres(tArray, getGraphics());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
