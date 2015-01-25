package taquin;

import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Taquin t = new Taquin(3);
		System.out.println(t);
		while(!t.estResolu()){
				String sc=s.next();
				char c=sc.charAt(0);
			try{
				if(c=='h') t.deplacement(0);
				if(c=='b') t.deplacement(1);
				if(c=='g') t.deplacement(2);
				if(c=='d') t.deplacement(3);
				System.out.println(t);
			}catch(ImpossibleMoveException err){System.out.print("Impossible.\n");}
		}
		System.out.println(t);
		s.close();
	}
}
