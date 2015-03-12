package algo;

import jeu.Sommet;

public interface EnsembleATraiter {
	public boolean nonVide();
	public Sommet prend();
	public boolean appartient(Sommet p);
	public boolean ajout(Sommet p);
}
