package algo;

import jeu.Sommet;

public class EnsembleIncomplet implements EnsembleMarque {
	private Sommet[] ensemble;
	
	public EnsembleIncomplet(int taille){
		ensemble=new Sommet[taille];
	}
	
	@Override
	public void ajout(Sommet pSommmet) {
		Sommet som=pSommet.pere().succ()
		int pos=pSommet.hasCode()%ensemble.length;
		if(ensemble[pos]==null){
			
		}

	}

	@Override
	public boolean appartient(Sommet pATester) {
		// TODO Auto-generated method stub
		return false;
	}

}
