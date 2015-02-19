package jeu;

import java.util.Stack;
import java.util.TreeMap;

import exception.ImpossibleMoveException;


public interface Jeu {
	public void deplacement(int direction) throws ImpossibleMoveException;

	public String toString();

	public boolean estResolu();

	public TreeMap<Character, Integer> getTabCorrespondance();

	public void executeSerieCoups(Stack<Integer> pSerieCoups);
}
