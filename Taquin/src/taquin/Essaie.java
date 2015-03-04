package taquin;

import java.util.*;

public class Essaie {
	
	private ArrayList<Integer> liste;
	
	public Essaie(){
		liste=new ArrayList<Integer>();
	}
	
	public void ajouter(ArrayList<Integer> l,int n){
		for(int i=0;i<n;i++){
			l.add(i);
			System.out.println(l);
			ajouter(l,n-1);
		}
	}
	
	public static void main(String[] args) {
		Essaie e=new Essaie();
		e.ajouter(e.liste,2);
		System.out.println(e.liste);
	}

}
