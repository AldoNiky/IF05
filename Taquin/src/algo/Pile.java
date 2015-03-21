package algo;

import jeu.Sommet;
import java.util.*;

public class Pile implements EnsembleATraiter{
	private Stack<Sommet> pile;
	
	public Pile(){
		pile=new Stack<Sommet>();
	}

	/**
	 * Permet de savoir si la pile est vide ou non
	 */
	public boolean nonVide() {
		return !pile.isEmpty();
	}

	/**
	 * Renvoie le sommet de la pile
	 */
	public Sommet prend() {
		return pile.pop();
	}

	/**
	 * Teste l'appartenance de p à la pile, renvoie un boolean
	 */
	public boolean appartient(Sommet p) {
		return pile.contains(p);
	}

	/**
	 * Ajoute un sommet à la pile
	 */
	public boolean ajout(Sommet p) {
		return pile.add(p);
	}

}
