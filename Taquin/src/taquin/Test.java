package taquin;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		//Scanner sc = new Scanner(System.in);
		final Taquin t = new Taquin(3);
		Fenetre f= new Fenetre();
		f.addKeyListener(new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				try{
				if(e.getKeyCode()==KeyEvent.VK_UP) t.deplacement(1);
				if(e.getKeyCode()==KeyEvent.VK_DOWN) t.deplacement(0);
				if(e.getKeyCode()==KeyEvent.VK_LEFT) t.deplacement(3);
				if(e.getKeyCode()==KeyEvent.VK_RIGHT) t.deplacement(2);
				System.out.println(t);
				}catch(ImpossibleMoveException err){System.out.print("Impossible.\n");};
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		System.out.println(t);
		/*while(!t.estResolu()) {
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
		}*/
		//System.out.print("Bravo !");
		//sc.close();
	}
}
