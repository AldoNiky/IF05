package jeu;

import java.util.*;



public interface Jeu {
	/**
	 * Effectuer un deplacement
	 * @param direction
	 * L'entier correspondant au deplacement
	 * @throws ImpossibleMoveException
	 * Une exception est leve si le deplacement est impossible
	 */
	public void deplacement(int direction) throws IndexOutOfBoundsException;
	/**
	 * Affiche le jeu
	 * @return
	 * Un string
	 */
	public String toString();
	/**
	 * Permet de savoir si un jeu est resolu
	 * @return
	 * Un boolean true si le jeu est resolue et false sinon
	 */
	public boolean estResolu();
	/**
	 * Getteurs sur le tableau de correspondance des touches et des deplacements
	 * @return
	 * Retourne une map
	 */
	public TreeMap<Character, Integer> getTabCorrespondance();
	/**
	 * Estime le nombre de coups pour arriver a une solution
	 * @return
	 * Renvoie un entier estimant le nombre de coups minimal a effectuer avant d'arriver a une solution
	 */
	public int distanceManhattan();
}
