package taquin;

import java.awt.Event;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Stack;

import exception.ImpossibleMoveException;

import jeu.Jeu;
import jeu.Taquin;

public class Test {
	public static void main(String[] args) {
		// on initialise un scanner d'entree
		Scanner s = new Scanner(System.in);
		// Un jeu
		Taquin t = new Taquin(Integer.parseInt(args[0]),
				Integer.parseInt(args[1]));
		// Un flux de sortie
		PrintStream pSortie = System.out;
		// On lance la partie
		jouer(t, s, pSortie);
	}

	/**
	 * Permet de jouer a un jeu quelconque sur un flux d'entree et de sortie
	 * parametrable
	 * 
	 * @param pJeu
	 *            Le jeu a jouer
	 * @param pScan
	 *            Le flux d'entree
	 * @param pSortie
	 *            Le flux de sortie
	 */
	public static void jouer(Jeu pJeu, Scanner pScan, PrintStream pSortie) {
		// Un string pour enregistrer les deplacements
		String deplacements = "";
		// On enregistre la posistion du curseur au debut du programme, pour y
		// revenir apres
		pSortie.println((char) Event.ESCAPE + "7");
		pSortie.println(pJeu);
		// On boucle tant que le jeu n'est pas resolu
		while (!pJeu.estResolu()) {
			// Lecture du caractere tappe au clavier
			String sc = pScan.next();
			char c = sc.charAt(0);
			// On essaie d'effectuer le deplacement voulue
			try {
				// On recupere la correspondance touche | deplacement
				// C'est la methode deplacement qui peut retourner une erreur
				pJeu.deplacement(pJeu.getTabCorrespondance().get(c));
				// On ajoute les deplacements au deplacement effectuer
				deplacements += c;
				// On revient a la position de depart
				pSortie.println((char) Event.ESCAPE + "8");
				// Et on reecrit le jeu par dessus l'ancien
				pSortie.println(pJeu);
			} catch (ImpossibleMoveException err) {
				pSortie.print(err.getMessage());
			}
		}
		// On revient au debut
		pSortie.println((char) Event.ESCAPE + "8");
		pSortie.println(pJeu);
		pSortie.println("Bravo vous avez gagne");
		pSortie.println("Voici la liste des mouvements effectues : " + deplacements);
	}

	/**
	 * Algorithme de resolution d'un jeu quelconque
	 * <p>
	 * Cette algorithme fais un parcours a approfondissement progressif
	 * largeur/profondeur
	 * </p>
	 * 
	 * @param pJeu
	 * Le jeu a resoudre
	 */
	public static void algoSurChemin(Jeu pJeu) {
		// Initialisations de quelques variables
		Stack<Integer> cheminDeRecherche = new Stack<Integer>();
		int profondeur = 0;
		int nbtest = 4;

		// Algorithme de parcours
		while (!pJeu.estResolu()) {
			profondeur++;
			for (int i = 0; i < nbtest; i++) {

			}
			nbtest = nbtest * 4;
		}
	}
	
	public static Taquin parseur(File ){
		
	}
}
