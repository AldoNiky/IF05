package algo;

import java.util.*;

import jeu.*;

public class EnsembleComplet implements EnsembleMarque {
	private ArrayList<Sommet> ensemble;

	public void ajout(Sommet pSommmet) {
		ensemble.add(pSommmet);		
	}

	public boolean appartient(Sommet pATester) {
		return ensemble.contains(pATester);
	}

}
