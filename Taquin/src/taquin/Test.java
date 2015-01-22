package taquin;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Taquin t = new Taquin(3);
		while(!t.estResolu()){
				String sc=s.next();
				char c=sc.charAt(0);
			try{
				if(c=='o') t.deplacement(1);
				if(c=='l') t.deplacement(0);
				if(c=='k') t.deplacement(3);
				if(c=='m') t.deplacement(2);
				System.out.println(t);
			}catch(ImpossibleMoveException err){System.out.print("Impossible.\n");}
		}
		System.out.println(t);
		s.close();
	}
}
