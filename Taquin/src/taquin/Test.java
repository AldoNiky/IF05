package taquin;

import java.awt.Event;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Stack;

public class Test {
	public static void main(String[] args) {
		//on initialise un scanner d'entrée
		Scanner s = new Scanner(System.in);
		//Un jeu
		Taquin t = new Taquin(Integer.parseInt(args[0]));
		//Un flux de sortie
		PrintStream pSortie = System.out;
		//On lance la partie
		jouer(t,s,pSortie);
	}
	/**
	 * Permet de jouer a un jeu quelconque sur un flux d'entrée et de sortie paramétrable
	 * @param pJeu
	 * Le jeu à jouer
	 * @param pScan
	 * Le flux d'entrée
	 * @param pSortie
	 * Le flux de sortie
	 */
	public static void jouer(Jeu pJeu, Scanner pScan, PrintStream pSortie){
		//Un string pour enregistrer les déplacements
		String deplacements = "";
		//On enregistre la posistion du curseur au début du programme, pour y revenir après
		pSortie.println((char) Event.ESCAPE + "7");
		pSortie.println(pJeu);
		//On boucle tant que le jeu n'est pas résolu
		while(!pJeu.estResolu()){
			//Lecture du caractère tappé au clavier
			String sc=pScan.next();
			char c=sc.charAt(0);
			//On essaie d'effectuer le déplacement voulue
			try{
				//On récupére la correspondance touche | déplacement
				//C'est la méthode déplacement qui peut retourner une erreur
				pJeu.deplacement(pJeu.getTabCorrespondance().get(c));
				//On ajoute les déplacement au déplacement effectuer
				deplacements += c;
				//On revient à la position de départ
				pSortie.println((char) Event.ESCAPE + "8");
				//Et on réecrit le jeu par dessus l'ancien 
				pSortie.println(pJeu);
			}catch(ImpossibleMoveException err){
				pSortie.print(err.getMessage());
			}
		}
		//On revient au début
		pSortie.println((char) Event.ESCAPE + "8");
		pSortie.println(pJeu);
		pSortie.println("Bravo vous avez gagné");
		pSortie.println("Voici la liste des mouvements effectues : " + deplacements);
	}
	/**
	 * Algorithlme de résolution d'un jeu quelconque
	 * <p>
	 * Cette algorithme fais un parcours a approfondissement progressif  largeur/profondeur
	 * </p>
	 * @param pJeu
	 * Le jeu à résoudre
	 */
	public static void algoSurChemin(Jeu pJeu){
		//Initialisations de quelques variables
		Stack<Integer> cheminDeRecherche= new Stack<Integer>();
		int profondeur=0;
		
		//Algorithme de parcours
		for(int j=0; j<profondeur; j++){
			for (int i=0; i<4; i++){
				if (!cheminDeRecherche.isEmpty())
					cheminDeRecherche.pop();
				cheminDeRecherche.push(i);
				pJeu.executeSerieCoups(cheminDeRecherche);
			}
		}
		
	}
}
