package taquin;

import java.util.ArrayList;

public class Taquin {
	private ArrayList<Integer> damier = new ArrayList<Integer>();
	/**
	 * Constructeur d'un Taquin
	 * @param taille
	 * <p>
	 * taille de la matrice
	 * </p>
	 */
	public Taquin(int taille) {
		for(int i = 0; i < Math.pow(taille, 2); i++) damier.add(i);
		melanger();
	}
	// 0 : Haut
	// 1 : Bas
	// 2 : Gauche
	// 3 : Droite
	/**
	 * Cette méthode déplace la case vide selon la direction donnée en paramètre
	 * @param direction
	 * @throws ImpossibleMoveException
	 */
	public void deplacement(int direction) throws ImpossibleMoveException {
		if(estAuBord(direction)) throw new ImpossibleMoveException();
		int taille = (int) Math.sqrt(damier.size()), vide = damier.indexOf(0);
		if(direction == 0) inverser(vide, vide - taille);
		else if(direction == 1) inverser(vide, vide + taille);
		else if(direction == 2) inverser(vide, vide - 1);
		else inverser(vide, vide + 1);
	}
	public boolean estAuBord(int direction) {
		int nbCarreaux = damier.size(), taille = (int) Math.sqrt(damier.size()), vide = damier.indexOf(0);
		if(direction == 0) return vide < taille;
		else if(direction == 1) return vide >= nbCarreaux - taille;
		else if(direction == 2) return vide % taille == 0;
		else if(direction == 3) return (vide + 1) % taille == 0;
		return true;
	}
	public boolean estResolu() {
		int nbCarreaux = damier.size();
		for(int i = 0; i < nbCarreaux - 1; i++) if(damier.get(i) != damier.get(i + 1) - 1) return false;
		return true;
	}
	public void inverser(int index1, int index2) {
		int carreau1 = damier.get(index1), carreau2 = damier.get(index2);
		damier.remove(index1);
		damier.add(index1, carreau2);
		damier.remove(index2);
		damier.add(index2, carreau1);
	}
	public void melanger() {
		int n, nbCarreaux = damier.size();
		for(int i = 0; i < nbCarreaux; i++) {
			do {
				n = (int) (Math.random() * nbCarreaux);
			} while(i == n);
			inverser(i, n);
		}
	}
	public String toString() {
		int nbCarreaux = damier.size(), taille = (int) Math.sqrt(damier.size());
		String s = "";
		for(int i = 0; i < nbCarreaux; i++) {
			int n = damier.get(i);
			if(n == 0) s += "   ";
			else if(n < 10) s += " " + n + " ";
			else s += n + " ";
			if((i + 1) % taille == 0) s += "\n";
		}
		return s;
	}
}
