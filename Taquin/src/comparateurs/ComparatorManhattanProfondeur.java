package comparateurs;

import java.util.Comparator;

import jeu.*;

public class ComparatorManhattanProfondeur implements Comparator<Sommet> {
	
	public int compare(Sommet s1, Sommet s2) {
			return s1.getManhattan()+s1.getProfondeur()<s2.getManhattan()+s2.getProfondeur()?1:0;
	}
}
