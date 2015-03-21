package comparateurs;

import java.util.Comparator;

import jeu.*;

public class ComparatorManhattan implements Comparator<Sommet> {
	
	public int compare(Sommet s1, Sommet s2) {
		return s1.getManhattan()<s2.getManhattan()?1:0;
	}
}
