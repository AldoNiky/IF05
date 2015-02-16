package taquin;

import java.awt.Event;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Stack;

public class Test {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Taquin t = new Taquin(Integer.parseInt(args[0]));
		PrintStream pSortie = System.out;
		jouer(t,s,pSortie);
	}
	
	public static void jouer(Jeu pJeu, Scanner pScan, PrintStream pSortie){		
		String deplacements = "";
		pSortie.println((char) Event.ESCAPE + "7");
		pSortie.println(pJeu);
		while(!pJeu.estResolu()){
			String sc=pScan.next();
			char c=sc.charAt(0);
			try{
				pJeu.deplacement(pJeu.getTabCorrespondance().get(c));
				deplacements += c;
				pSortie.println((char) Event.ESCAPE + "8");
				pSortie.println(pJeu);
			}catch(ImpossibleMoveException err){
				pSortie.print(err.getMessage());
			}
		}
		pSortie.println((char) Event.ESCAPE + "8");
		pSortie.println(pJeu);
		pSortie.println("Bravo vous avez gagné");
		pSortie.println("Voici la liste des mouvements effectues : " + deplacements);
	}
	
	public static void algoSurChemin(Jeu pJeu){
		
		//Initialisations de quelque variables
		Stack<Integer> cheminDeRecherche= new Stack<Integer>();
		int profondeur=0;
		
		//Algorithme de parcours
		
		
	}
}
