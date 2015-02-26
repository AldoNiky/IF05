package jeu;

import java.util.Stack;
import java.util.TreeMap;

import exception.ImpossibleMoveException;


public interface Jeu {
	/**
	 * Effectuer un deplacement
	 * @param direction
	 * L'entier correspondant au deplacement
	 * @throws ImpossibleMoveException
	 * Une exception est leve si le deplacement est impossible
	 */
	public void deplacement(int direction) throws ImpossibleMoveException;
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
	 * Permet d'excuter une serie de coups
	 * <p>
	 * Cette methode execute une serie de coups sur un jeu puis renvoie la situation obtenue, les coups effectuer sont 
	 * ensuite annule
	 * </p>
	 * @param pSerieCoups
	 * La serie de coups a executer
	 */
	public void executeSerieCoups(Stack<Integer> pSerieCoups);
	/**
	 * Execute la serie de coups inverse de celle qui est passe en parametre
	 * <p>
	 * Permet d'annuler une serie de coups
	 * </p>
	 * @param pSerieCoups
	 * La serie de coups a annule
	 */
	public void executeSerieCoupsInverse(Stack<Integer> pSerieCoups);
	/**
	 * Estime le nombre de coups pour arriver a une solution
	 * @return
	 * Renvoie un entier estimant le nombre de coups minimal a effectuer avant d'arriver a une solution
	 */
	public int distanceManhattan();
}
