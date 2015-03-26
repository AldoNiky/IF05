package jeu;

import java.util.*;
import java.util.Map.Entry;

public class Taquin implements Jeu{

	private int[][] damier;
	public HashMap<Integer, Integer[]> damierFin;
	private HashMap<String, Integer[]> deplacement;
	/**
	 * Constructeur d'un Taquin
	 * 
	 * @param le nombre de lignes et de colonnes
	 * <p>
	 * Taille de la matrice
	 * </p>
	 */
	public Taquin(int nbL, int nbC, String N, String S, String E, String O) {
		//Initialisation d'un damier initial et final
		damierFin=new HashMap<Integer, Integer[]>();
		int numero=1;
		this.damier= new int[nbL][nbC];
		for(int i=0; i<nbL;i++){
			for(int j=0; j<nbC;j++){
				if(i==nbL-1 && j==nbC-1) numero=0;
				damier[i][j]=numero;
				Integer[] t=new Integer[2];
				t[0]=i;t[1]=j;
				damierFin.put(numero, t);
				numero++;
			}
		}
		//Initialisation des deplacements
		deplacement=new HashMap<String, Integer[]>();
		Integer[] t1=new Integer[2];
		t1[0]=-1;t1[1]=0;
		deplacement.put(N, t1);
		Integer[] t2=new Integer[2];
		t2[0]=1;t2[1]=0;
		deplacement.put(S, t2);
		Integer[] t3=new Integer[2];
		t3[0]=0;t3[1]=-1;
		deplacement.put(E, t3);
		Integer[] t4=new Integer[2];
		t4[0]=0;t4[1]=1;
		deplacement.put(O, t4);
		
		for(int i=0; i<90; i++)
			melanger();
	}
	/**
	 * Melange la grille de jeu
	 * 
	 * @return Retourne la grille de jeu, permet de melanger successivement
	 */
	public void melanger() {
		Object[] t=this.deplacement.keySet().toArray();
		try{
			int entier=(int) (Math.random() * 4);
			deplacement((String)t[entier]);
		}catch(IndexOutOfBoundsException e){
			
		}
	}

	/**
	 * Permet de retrouver les coordonnees d'une case en particulier
	 * @return
	 * Un tableau d'entier avec numero  de la ligne et celle de la colonne
	 */
	public int[] indexOf(int nb){
		int[] t=new int[2];
		for(int i=0; i<damier.length;i++){
			for(int j=0; j<damier[0].length; j++){
				if (damier[i][j]==nb){
					t[0]=i;t[1]=j;
					return t;
				}
			}
		}
		return null;
	}
	/**
	 * Permet de deplacer la case vide 
	 */
	public Taquin deplacement(String direction) throws IndexOutOfBoundsException{
		int[] pos0=this.indexOf(0);
		Integer[] posX=deplacement.get(direction);
		damier[pos0[0]][pos0[1]]=damier[pos0[0]+posX[0]][pos0[1]+posX[1]];
		damier[pos0[0]+posX[0]][pos0[1]+posX[1]]=0;
		return this.clone();
	}
	/**
	 * Methode qui determine la distance entre la position initiale
	 * de la case vide que l'on nommera ini et sa position finale
	 * pour que le taquin puisse pretendre etre resolu dont on 
	 * nommera fin : Dman=|Xfin-Xini|+|Yfin-Yini|
	 * @return un entier qui sera la distance dite de Mannathan
	 */
	public int distanceManhattan(int i) {
		int[]pos=this.indexOf(i);
		Integer[] posFin=this.damierFin.get(i);
		double Yini=pos[0], Xini=pos[1], Yfin=posFin[0], Xfin=posFin[1];
		return (int)(Math.sqrt(Math.pow(Xfin-Xini, 2))+Math.sqrt(Math.pow(Yfin-Yini, 2)));
	}
	/**
	 * Methode pour savoir si le jeu est resolu
	 * 
	 * @return Un boolean true si le jeu est resolue, false sinon
	 */
	public boolean estResolu() {
		Iterator<Entry<Integer,Integer[]>> it=damierFin.entrySet().iterator();
		while(it.hasNext()){
			if(this.distanceManhattan(it.next().getKey())!=0) return false;
		}
		return true;
	}
	/**
	 * 
	 * @return
	 */
	public int nbPermutFin(){
		int[][] ini=this.copieDamier();
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
	/**
	 * Methode qui permet de determiner si ce taquin
	 * est resolvable ou non en regardant si le nombre de 
	 * permutation permettant d avoir un taquin resolu est identique
	 * a la distance de Mannathan de 0
	 *  @return vrai si le taquin est resolvable, faux sinon
	 */
	public boolean estSoluble(){
		return this.distanceManhattan(0)%2==this.nbPermutFin()%2;
	}
	/**
	 * @return un clone du tableau nommer damier
	 */
	public int[][] copieDamier(){
		int[][]t=new int[damier.length][damier[0].length];
		for(int i=0;i<this.damier.length;i++){
			for(int j=0;j<this.damier[0].length;j++){
				t[i][j]=damier[i][j];
			}
		}
		return t;
	}
	/**
	 * 
	 * @param d
	 */
	public void setDamier(int[][] d){
		for(int i=0;i<d.length;i++){
			for(int j=0;j<d[0].length;j++){
				this.damier[i][j]=d[i][j];
			}
		}
	}
	/**
	 * Affichage du jeu
	 */
	public String toString() {
		String s="";
		for(int i=0; i<damier.length; i++){
			for(int j=0; j<damier[0].length;j++){
				s+=damier[i][j]+"\t";
			}
			s+="\n";
		}
		return s;
	}
	
	public int hashCode(){
		int prim=31;
		int hash=1;
		for(int i=0;i<this.damier.length;i++){
			for(int j=0;j<this.damier[0].length;j++){
				hash=hash*prim+damier[i][j];
			}
		}
		return hash;
	}
	
	public boolean equals(Object o){
		if(o instanceof Taquin){
			Taquin t =(Taquin)o;
			return t.hashCode()==this.hashCode();
		}
		else return false;
	}
	public int sommeManhattan(){
		int res=0;
		for(int i=0; i<damier.length; i++){
			for(int j=0; j<damier[0].length;j++)
				if(damier[i][j]!=0) res+=distanceManhattan(damier[i][j]);
		}
		return res;
	}
	
	public Taquin clone(){
		return this.clone();
	}
	@Override
	public Jeu deplacement(int direction) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public TreeMap<Character, Integer> getTabCorrespondance() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getDeplacementEFfectue() {
		// TODO Auto-generated method s
		return null;
	}
}
