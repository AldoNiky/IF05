package jeu;

import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeMap;

import exception.ImpossibleMoveException;



public class Taquin implements Jeu {
	private ArrayList<Integer> damier = new ArrayList<Integer>();
	private TreeMap<Character, Integer> tabCorrespondance = new TreeMap<Character, Integer>();
	private int hauteur;
	private int largeur;

	/**
	 * Constructeur d'un Taquin
	 * 
	 * @param taille
	 *            <p>
	 *            taille de la matrice
	 *            </p>
	 */
	public Taquin(int pHauteur, int pLargeur) {
		this.hauteur = pHauteur;
		this.largeur = pLargeur;

		int nbElem = this.hauteur * this.largeur;
		for (int i = 0; i < nbElem; i++)
			damier.add(i);
		do
			melanger();
		while (!estResolvable());

		// On initialise le tableau des correspondance
		tabCorrespondance.put(new Character('z'), new Integer(1));
		tabCorrespondance.put(new Character('s'), new Integer(0));
		tabCorrespondance.put(new Character('q'), new Integer(3));
		tabCorrespondance.put(new Character('d'), new Integer(2));

	}

	public TreeMap<Character, Integer> getTabCorrespondance() {
		return tabCorrespondance;
	}

	/**
	 * Cette methode deplace la case vide selon la direction donnee en parametre
	 * 0 : Haut 1 : Bas 2 : Gauche 3 : Droite
	 * 
	 * @param direction
	 *            L'entier determinant la direction
	 * @throws ImpossibleMoveException
	 *             Une erreur est renvoyee si le deplacement n'est pas possible
	 */
	public void deplacement(int direction) throws ImpossibleMoveException {
		// Si la case sort de la grille avec le deplacement on retourne une
		// erreur
		if (estAuBord(direction))
			throw new ImpossibleMoveException();
		// On recupere la position de la case vide
		int vide = damier.indexOf(0);
		switch (direction) {
		case 0:
			inverser(vide, vide - hauteur);
			break;
		case 1:
			inverser(vide, vide + hauteur);
			break;
		case 2:
			inverser(vide, vide - 1);
			break;
		case 3:
			inverser(vide, vide + 1);
			break;
		}
	}

	/**
	 * Permet de savoir si la case est au bord de la grille
	 * 
	 * @param direction
	 *            Determine la direction voulue
	 * @return Un boolean true si la case blanche est au bord, false sinon
	 */
	public boolean estAuBord(int direction) {
		int nbCarreaux = damier.size(), vide = damier.indexOf(0);
		switch (direction) {
		case 0:
			return vide < hauteur;
		case 1:
			return vide >= nbCarreaux - hauteur;
		case 2:
			return vide % hauteur == 0;
		case 3:
			return (vide + 1) % hauteur == 0;
		default:
			return true;
		}
	}

	/**
	 * Methode pour savoir si le jeu est resolu
	 * 
	 * @return Un boolean true si le jeu est resolue, false sinon
	 */
	public boolean estResolu() {
		int nbCarreaux = damier.size();
		for (int i = 0; i < nbCarreaux - 1; i++)
			if (damier.get(i) != damier.get(i + 1) - 1)
				return false;
		return true;
	}

	/**
	 * Methode pour savoir si le jeu est resoluble
	 * 
	 * @return Un bolean true si il est et false sinon
	 */
	public boolean estResolvable() {
		Object[] t = damier.toArray();
		int permutation = 0;
		for (int i = 0; i < t.length; i++) {
			if ((int) t[i] != i) {
				for (int j = i + 1; j < t.length; j++) {
					if ((int) t[j] == i) {
						t[j] = t[i];
						t[i] = i;
						permutation++;
						break;
					}
				}
			}
		}
		return permutation % 2 == 0;
	}

	/**
	 * Permute deux cases
	 * 
	 * @param index1
	 *            L'indice de la case vide
	 * @param index2
	 *            L'indice de l'autre case
	 */
	public void inverser(int index1, int index2) {
		int carreau1 = damier.get(index1), carreau2 = damier.get(index2);
		damier.remove(index1);
		damier.add(index1, carreau2);
		damier.remove(index2);
		damier.add(index2, carreau1);
	}

	/**
	 * Melange la grille de jeu
	 * 
	 * @return Retourne la grille de jeu, permet de melanger successivement
	 */
	public ArrayList<Integer> melanger() {
		int n, nbCarreaux = damier.size();
		for (int i = 0; i < nbCarreaux; i++) {
			do {
				n = (int) (Math.random() * nbCarreaux);
			} while (i == n);
			inverser(i, n);
		}
		return this.damier;
	}

	/**
	 * Affiche du jeu
	 */
	public String toString() {
		int nbCarreaux = damier.size();
		String s = "";
		for (int i = 0; i < nbCarreaux; i++) {
			int n = damier.get(i);
			if (n == 0)
				s += "   ";
			else if (n < 10)
				s += " " + n + " ";
			else
				s += n + " ";
			if ((i + 1) % hauteur == 0)
				s += "\n";
		}
		return s;
	}

	/**
	 * Getteur de la grille de jeu
	 * 
	 * @return On retourne la grille de jeu
	 */
	public ArrayList<Integer> getDamier() {
		return damier;
	}

	public int getHauteur() {
		return hauteur;
	}

	/**
	 * Execute une serie de coups
	 * 
	 * @param pSerieCoups
	 *            La serie de coups a executer
	 */
	public void executeSerieCoups(Stack<Integer> pSerieCoups) {
		for (int i = 0; i < pSerieCoups.size(); i++) {
			try {
				deplacement(pSerieCoups.pop());
			} catch (ImpossibleMoveException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
