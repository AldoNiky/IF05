package taquin;

import java.awt.Event;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
	
	
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		//Taquin t = new Taquin(Integer.parseInt(args[0]));
		ArrayList<Integer> d=new ArrayList<Integer>();
		d.add(3);d.add(5);d.add(7);d.add(4);d.add(2);d.add(6);d.add(8);d.add(1);d.add(0);
		Taquin t1=new Taquin(3, d);
		try {
			System.out.println("La solution la plus optimale est :"+t1.methode2Resolution());
		}catch (NoCombinaisonException e) {
			
		}
		PrintStream pSortie = System.out;
		jouer(t1,s,pSortie);
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
				pSortie.print("Impossible.\n");
			}
		}
		pSortie.println((char) Event.ESCAPE + "8");
		pSortie.println(pJeu);
		pSortie.println("Bravo vous avez gagner");
		pSortie.println("Voici la liste des mouvements effectues : " + deplacements);
	}
	
}
