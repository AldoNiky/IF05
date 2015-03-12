package algo;

import java.util.*;

import jeu.Sommet;

public class Tas implements EnsembleATraiter {
	private ArrayList<Sommet> tas;
	
	public Tas(){
		tas= new ArrayList<Sommet>();
	}
	
	@Override
	public boolean nonVide() {
		return tas.isEmpty();
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
