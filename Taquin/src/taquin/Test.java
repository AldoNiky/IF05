package taquin;

import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Taquin t = new Taquin(3);
		System.out.println(t);
		while(!t.estResolu()) {
			String s = sc.next();
			try {
				switch (s) {
				case "h": t.deplacement(0);break;
				case "b": t.deplacement(1);break;
				case "g": t.deplacement(2);break;
				case "d": t.deplacement(3);break;
				}
			} catch (ImpossibleMoveException e) {
				System.out.print("Impossible.\n");
			}
			System.out.println(t);
		}
		System.out.print("Bravo !");
		sc.close();
	}
}
