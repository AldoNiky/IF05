package taquin;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		//Scanner sc = new Scanner(System.in);
		final Taquin t = new Taquin(3);
		try{
			if(e.getKeyCode()==KeyEvent.VK_UP) t.deplacement(1);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) t.deplacement(0);
			if(e.getKeyCode()==KeyEvent.VK_LEFT) t.deplacement(3);
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) t.deplacement(2);
			System.out.println(t);
		}catch(ImpossibleMoveException err){System.out.print("Impossible.\n");}
		System.out.println(t);
		//sc.close();
	}
}
