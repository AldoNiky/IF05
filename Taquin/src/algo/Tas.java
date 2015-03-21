package algo;

import java.util.*;

import comparateurs.*;

import jeu.*;

public class Tas implements EnsembleATraiter {
	private PriorityQueue<Sommet> file;
	
	public Tas(int pComp){
		Comparator c;
		switch(pComp){
		case 1:
			c = new ComparatorManhattan();
			break;
		case 2:
			c = new ComparatorManhattanProfondeur();
		default :
			c = new ComparatorManhattan();
			break;
		}
		file=new PriorityQueue<Sommet>(c);
	}
	
	public boolean nonVide() {
		return file.isEmpty();
	}

	public Sommet prend() {
		return file.peek();
	}

	public boolean appartient(Sommet p) {
		return file.contains(p);
	}

	public boolean ajout(Sommet p) {
		return file.add(p);
	}

}
