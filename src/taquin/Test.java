package taquin;

import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String deplacements = "";
		Taquin t = new Taquin(2, 3);
		System.out.println(t);
		while(!t.estResolu()) {
				String s = sc.next();
				char c = s.charAt(0);
			try {
				switch(c) {
				case 'h': t.deplacement(0); deplacements += "H"; break;
				case 'b': t.deplacement(1); deplacements += "B"; break;
				case 'g': t.deplacement(2); deplacements += "G"; break;
				case 'd': t.deplacement(3); deplacements += "D"; break;
				}
				System.out.println(t);
			} catch(ImpossibleMoveException e) {
				System.out.print("Impossible.\n" + t);
			}
		}
		System.out.println("\nBravo ! Voici la liste des mouvements effectues : " + deplacements);
		sc.close();
	}
}
