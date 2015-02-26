package test;

import static org.junit.Assert.*;
import jeu.Taquin;

import org.junit.Test;

public class TestTaquin {
	public static int nbAlea(){
		return (int) (Math.random() * 10);
	}
	
	@Test
	public void testResolvable() {
		//On teste plusieurs fois sur des taquins de tailles differentes
		//for (int i=0; i<1000;i++){
			Taquin t = new Taquin(nbAlea(), nbAlea());
			System.out.println("On a tester 1 fois");
			assertTrue("doit etre vraie",t.estResolvable());			
		//}
	}

	
	//@Test
	/*public void testInverser(){
		//On repete le test 100 fois aleatoirement pour tester plusieurs 
		//situation
		for(int i=0; i<100; i++){
			//On creer deux positions
			int pos1[]={nbAlea(),nbAlea()};
			int pos2[]={nbAlea(),nbAlea()};
			//On les inverse est on recupere leurs coordonnees
			int retour[][]=Taquin.inverser(pos1,pos2);
			//On verifie que les positions on bien ete inversees
			assertTrue("doit etre vrai",retour[0]==pos2 && retour[1]==pos1);
		}
	}*/
}
