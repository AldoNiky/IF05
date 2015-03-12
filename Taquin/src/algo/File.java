package algo;

import java.util.ArrayList;

import jeu.Sommet;

public class File implements EnsembleATraiter{
	private ArrayList<Sommet> file;
	
	public File(){
		file= new ArrayList<Sommet>();
	}
	
	
	@Override
	public boolean nonVide() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Sommet prend() {
		// TODO Auto-generated method stub
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
