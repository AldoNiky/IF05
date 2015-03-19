package algo;

import java.util.*;

import comparateurs.ComparatorManhattan;

import jeu.Damier;
import jeu.Sommet;

public class Tas implements EnsembleATraiter {
	private PriorityQueue<Damier> file;
	
	public Tas(){
		file=new PriorityQueue<Damier>(new ComparatorManhattan());
	}
	
	public boolean nonVide() {
		return file.isEmpty();
	}

	@Override
	public Sommet prend() {
		return null;
	}

	@Override
	public boolean appartient(Sommet p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ajout(Sommet p) {
		// TODO Auto-generated method stub
		return false;
	}

}
