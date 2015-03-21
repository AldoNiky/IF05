package jeu;

import java.util.ArrayList;

public class Sommet {
	private Damier value;
	private Sommet pere;
	private int profondeur;
	private String chemin;
	private int manhattan;
	
	/**
	 * Constructueur pour le premier sommet du graphe
	 * @param pDam
	 * Le damier de depart
	 */
	public Sommet(Damier pDam){
		value=pDam;
		pere=null;
		profondeur=0;
		chemin=null;
		manhattan=value.distanceManhattan();
	}
	
	/**
	 * Constructeur pour les autres sommets
	 * @param pPere
	 * Le pere du sommet
	 * @param pAction
	 * L'action qui a mene au sommet
	 */
	public Sommet(Sommet pPere, int pAction){
		pere=pPere;
		chemin=pere.getChemin()+pAction;
		value=pere.getValue().deplacement(pAction);
		profondeur=pere.getProfondeur()+1;
	}
	
	public int getManhattan() {
		return manhattan;
	}

	public void setManhattan(int manhattan) {
		this.manhattan = manhattan;
	}

	public ArrayList<Sommet> succ(){
		ArrayList<Sommet> al = new ArrayList<>();
		for(int i=0; i<4; i++){
			al.add(new Sommet(this, i));
		}
		return al;
	}
	
	public char getAction(){
		return chemin.charAt(chemin.length());
	}

	public Damier getValue() {
		return value;
	}

	public void setValue(Damier value) {
		this.value = value;
	}

	public Sommet getPere() {
		return pere;
	}

	public void setPere(Sommet pere) {
		this.pere = pere;
	}

	public int getProfondeur() {
		return profondeur;
	}

	public void setProfondeur(int profondeur) {
		this.profondeur = profondeur;
	}

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}
}
