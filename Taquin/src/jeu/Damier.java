package jeu;

import java.util.HashMap;

public class Damier{
	public int[][] grilleJeu;
	public HashMap<Integer, Integer[]> damierFin = new HashMap<Integer, Integer[]>();

	public Damier(int pHauteur, int pLongueur){
		this.grilleJeu= new int[pLongueur][pHauteur];
		int numero=1;
		for(int i=0; i<pLongueur;i++){
			for(int j=0; j<pHauteur; j++){
				int indiceFin=0;
				if(i!=pLongueur-1 || j!=pHauteur-1){
					indiceFin=numero;
					grilleJeu[i][j]=numero;
				}else grilleJeu[i][j]=0;
				Integer[]t={i,j};
				damierFin.put(indiceFin, t);
				numero++;
			}
		}

		for(int i=0; i<90; i++)
			melanger();

	}
	
	/**
	 * Melange la grille de jeu
	 * 
	 * @return Retourne la grille de jeu, permet de melanger successivement
	 */
	public int[][] melanger() {
		try{
			int entier=(int) (Math.random() * 4);
			deplacement(entier);
		}catch(IndexOutOfBoundsException e){}
		return this.grilleJeu;
	}

	/**
	 * Retourne les coordonnees d'une case particuliere
	 * @return
	 * Un tableau d'entier avec abscisse et ordonnee
	 */
	private int[] indexOf(int nb){
		int[] t=new int[2];
		for(int i=0; i<grilleJeu.length;i++){
			for(int j=0; j<grilleJeu[0].length; j++){
				if (grilleJeu[i][j]==nb){
					t[0]=i;t[1]=j;
					return t;
				}
			}
		}
		return null;
	}
	

	/**
	 * Methode qui determine la distance entre la position initiale
	 * de la case vide que l'on nommera ini et sa position finale
	 * pour que le taquin puisse pretendre etre resolu dont on 
	 * nommera fin : Dman=|Xfin-Xini|+|Yfin-Yini|
	 * @return un entier qui sera la distance dite de Mannathan
	 */
	public int distanceManhattan() {
		int[]pos0=this.indexOf(0);
		double Yini=pos0[0], Xini=pos0[1], Yfin=longueur-1, Xfin=hauteur-1;
		return (int)(Math.sqrt(Math.pow(Xfin-Xini, 2))+Math.sqrt(Math.pow(Yfin-Yini, 2)));
	}

	public int nbPermutFin(){
		Damier ini=(Damier) this.clone();
		int indice=0,permut=0;
		while(!this.estResolu()){
			int [] debut=indexOf(indice);
			Integer [] fin=damierFin.get(indice);
			if(debut[0]!=fin[0] || debut[1]!=fin[1]){
				int nb=damier[fin[0]][fin[1]];
				damier[fin[0]][fin[1]]=damier[debut[0]][debut[1]];
				damier[debut[0]][debut[1]]=nb;
				permut++;
			}
			indice++;
		}
		this.setDamier(ini);
		return permut;
	}
	
	public Damier deplacement(int direction) throws IndexOutOfBoundsException{
		int temp[]=indexOf(0);
		int valADeplacer, x=temp[0],y=temp[1];
		switch(direction){
		case 0:
			x-=1;
			break;
		case 1:
			x+=1;
			break;
		case 2:
			y-=1;
			break;
		case 3:
			y+=1;
			break;
		}
		valADeplacer=damier[x][y];
		damier[x][y]=0;
		damier[temp[0]][temp[1]]=valADeplacer;
		if(direction%2==0){
			if(direction==0) deplacementEffectue+="H";
			deplacementEffectue+="G";
		}else{
			if(direction==1) deplacementEffectue+="B";
			deplacementEffectue+="D";			
		}

	}
	return this;
}
