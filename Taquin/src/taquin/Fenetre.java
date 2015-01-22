package taquin;

import javax.swing.JFrame;

public class Fenetre extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Fenetre (){
		setVisible(true);
		this.setSize(300, 300);
		this.setTitle("Jeu de Taquin");
		SurfaceJeu pan = new SurfaceJeu();
		this.setContentPane(pan);
		//pan.dessineGrilleJeu(getGraphics());
	}
	
}
