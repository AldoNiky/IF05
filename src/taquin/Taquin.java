package taquin;

import java.util.ArrayList;

public class Taquin {
	private ArrayList<Integer> damier = new ArrayList<Integer>();
	private int hauteur, largeur, nbCarreaux, vide;
	/**
	 * Cree un jeu de taquin melange et soluble
	 * @param h
	 * Hauteur du jeu de taquin
	 * @param l
	 * Largeur du jeu de taquin
	 */
	public Taquin(int h, int l) {
		hauteur = h;
		largeur = l;
		nbCarreaux = h * l - 1;
		for(int i = 0; i < hauteur * largeur; i++) damier.add(i);
		do {
			melanger();
			vide = damier.indexOf(nbCarreaux);
		}
		while(!estSoluble());
	}
	/**
	 * Deplace la case vide selon la direction indiquee en parametre :
	 *   - vers le haut pour 0
	 *   - vers le bas pour 1
	 *   - vers la gauche pour 2
	 *   - vers la droite pour 3
	 * @throws ImpossibleMoveException
	 * Jette une exception si le deplacement est impossible
	 */
	public void deplacement(int direction) throws ImpossibleMoveException {
		int n = voisin(direction);
		if(n == -1) throw new ImpossibleMoveException();
		else {
			inverser(damier, vide, n);
			vide = n;
		}
	}
	/**
	 * @return TRUE si le jeu est resolu, FALSE sinon
	 */
	public boolean estResolu() {
		for(int i = 0; i < nbCarreaux; i++) if(damier.get(i) != damier.get(i + 1) - 1) return false;
		return true;
	}
	/**
	 * @return TRUE s'il existe une solution au jeu de taquin, FALSE sinon
	 */
	public boolean estSoluble() {
		return nbPermutations() % 2 == manhattan() % 2;
	}
	/**
	 * Pour une ArrayList donnee, echange les valeurs des entiers aux positions indice1 et indice2
	 */
	public void inverser(ArrayList<Integer> al, int indice1, int indice2) {
		int carreau1 = al.get(indice1), carreau2 = al.get(indice2);
		al.remove(indice1);
		al.add(indice1, carreau2);
		al.remove(indice2);
		al.add(indice2, carreau1);
	}
	/**
	 * @return La distance de Manhattan entre la position actuelle de la case vide et sa position ideale, a savoir le coin inferieur droit du jeu de taquin
	 */
	public int manhattan() {
		return ((hauteur - 1) - (vide / largeur)) + ((largeur - 1) - (vide % largeur));
	}
	/**
	 * Melange aleatoirement un jeu de taquin de maniere a ce que chaque case soit deplacee
	 */
	public void melanger() {
		int n;
		for(int i = 0; i <= nbCarreaux; i++) {
			do {
				n = (int) (Math.random() * (nbCarreaux + 1));
			} while(i == n);
			inverser(damier, i, n);
		}
	}
	/**
	 * @return Le nombre de permutations de deux cases permettant d'obtenir un jeu de taquin resolu
	 */
	public int nbPermutations() {
		ArrayList<Integer> al = new ArrayList<Integer>(damier);
		int n = 0;
		for(int i = 0; i < nbCarreaux; i++) {
			if(al.get(i) != i) {
				inverser(al, al.indexOf(i), i);
				n++;
			}
		}
		return n;
	}
	public String toString() {
		String s = "";
		for(int i = 0; i <= nbCarreaux; i++) {
			int n = damier.get(i);
			if(n == nbCarreaux) s += "   ";
			else if(n < 10) s += " " + n + " ";
			else s += n + " ";
			if((i + 1) % largeur == 0) s += "\n";
		}
		return s;
	}
	/**
	 * Determine la position de l'un des voisins de la case vide, selon la direction indiquee en parametre :
	 *   - le voisin du haut pour 0
	 *   - le voisin du bas pour 1
	 *   - le voisin de gauche pour 2
	 *   - le voisin de droite pour 3
	 * @return l'indice du voisin souhaite, -1 si ce dernier n'existe pas
	 */
	public int voisin(int direction) {
		int n;
		switch(direction) {
		case 0: n = vide - largeur; if(n < 0) return -1; return n;
		case 1: n = vide + largeur; if(n > nbCarreaux) return -1; return n;
		case 2: n = vide - 1; if(vide % largeur == 0) return -1; return n;
		case 3: n = vide + 1; if((vide + 1) % largeur == 0) return -1; return n;
		}
		assert(false);
		return -1;
	}
}
