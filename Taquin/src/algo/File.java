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
		return !file.isEmpty();
	}

	@Override
	public Sommet prend() {
		return file.remove(0);
	}

	@Override
	public boolean appartient(Sommet p) {
		return file.contains(p);
	}

	@Override
	public boolean ajout(Sommet p) {
		return file.add(p);
	}

}
