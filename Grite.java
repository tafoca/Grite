package uds.grite.Itemset;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * 
 * @author 
 * The class encapsulates an implementation of the Grite algorithm
 * to compute Gradual frequent itemsets.
 * @author tabueu  laurent, University of DSCHANG, 2017
 * @copyright GNU General Public License v3
 * No reproduction in whole or part without maintaining this copyright notice
 * and imposing this condition on any subsequent users.
 *
 */
public class Grite {
	private int nbitems = 3;
	private int nbtransaction = 8;
	/** the list of current itemsets */
	public ArrayList<float[]> itemsets = new ArrayList<>();
	/** the name of the transcation file */
	private String transaFile;
	 static ArrayList<int[][]> allContengent= new ArrayList<>();
	float[][] dataset;
	float[] item;
	int taille= 9;
	int a = 3;

	/*
	 * 1. Génération des 1-itemsets graduels : pour chaque item i de la base DB,
	 * l’item graduel i ≥ est construit en ordonnant les t O [i] selon la
	 * relation d’ordre ≥,
	 */

	public Grite() {
		super();
		//construct db
		float[] cand1 = {-2,(float) -1.5,2};
		float[] cand2 = {(float) -0.5,-1,2};
		float[] cand3 = {-1 , (float) -1.5 ,1};
		float[] cand4 = {(float) 0.5,(float) -0.5,2};
		float[] cand5 = {1 , (float) -0.8 ,2};
		float[] cand6 = {(float) 1.5 , 1  ,2};
		float[] cand7 = {3 , 1  ,3};
		float[] cand8 = {(float) 1.2 , 3  ,3};
		itemsets.add(cand1);
		itemsets.add(cand2);
		itemsets.add(cand3);
		itemsets.add(cand4);
		itemsets.add(cand5);
		itemsets.add(cand5);
		itemsets.add(cand6);
		itemsets.add(cand7);
		itemsets.add(cand8);
		//end of construction db
		this.item= null;
		this.dataset = Grite.duplique(itemsets);
		//Grite.affiche(dataset);
		allContengent = createGradualsItemsetsOfSize1(dataset, item, a, taille);
		Grite.affiche(allContengent.get(0));
		grite_execution();
		
		
	}

	public static float[][] duplique(ArrayList<float[]> mat) {
		float[][] res = new float[mat.size()][];
		for (int i = 0; i < mat.size(); i++) {
			res[i] = new float[mat.get(i).length];
			for (int j = 0; j < mat.get(i).length; j++)
				res[i][j] = mat.get(i)[j];
		}
		return res;
	}

	public static void affiche(int[][] tab) {
		for (int[] d : tab) {
			System.out.println();
			for (int v : d)
				System.out.print(v + " ");
		}
	}

	public static void affiche(float[][] tab) {
		for (float[] d : tab) {
			System.out.println();
			for (float v : d)
				System.out.print(v + " ");
		}
	}

	// a is item number a
	public static float[] getDataColByCol(float[][] dataset, float[] item, int a, int taille) {
		taille = 9;
		item = new float[taille];
		int l = 0;
		for (int i = 0; i < dataset.length; i++) {

			for (int k = 0; k < 3; k++) {
				if (l == i && k == a) {
					item[i] = dataset[i][k];
					// System.out.println(item[i]+ " ");
				}
			}
			// res.add(item);
			l++;
		}

		return item;
	}

	public static void getAllColum(float[][] dataset, float[] item, int a, int taille) {
		for (a = 0; a < 3; a++) {
			float[] rescol = Grite.getDataColByCol(dataset, item, a, taille);
			for (int i = 0; i < rescol.length; i++) {
				System.out.println(rescol[i] + "  ");
			}
			System.out.println();
		}
	}

	private static ArrayList<int[][]> createGradualsItemsetsOfSize1( float[][] dataset,  float[] item, int a,
			 int taille) {
		ArrayList<int[][]> allContengent = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			float[] rescol = Grite.getDataColByCol(dataset, item, i, taille);

			// gestion objets croissant X> et creation matrice contigence
			// associe
			int[][] Contengence1 = new int[taille][taille];

			for (int j = 0; j < taille; j++) {
				for (int j2 = j + 1; j2 < taille; j2++) {
					if (rescol[j] < rescol[j2]) {
						Contengence1[j][j2] = 1;
					}else{
					Contengence1[j][j2] = 0;
					}
				}
			}
			allContengent.add(Contengence1);
			// gestion objets decroissant X< et creation matrice contigence
			// associe

			int[][] Contengence2 = new int[taille][taille];

			for (int j = 0; j < taille; j++) {
				for (int j2 = j + 1; j2 < taille; j2++) {
					if (rescol[j] > rescol[j2]) {
						Contengence2[j][j2] = 1;
					}
					Contengence2[j][j2] = 0;
				}
			}
			allContengent.add(Contengence2);
		}
		return allContengent;

	}

	public void grite_execution() {
		// createGradualsItemsetsOfSize1();
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		float[] item =new float[9];
		Grite ap = new Grite();
		// ap.getconfig();
		ArrayList<float[]> itemsets = ap.itemsets;

		/*
		 * for (ArrayList<Integer> arrayList : itemsets) { for (Integer integer
		 * : arrayList) { System.out.println("< "+integer+ " />"); } }
		 */
		float[][] dataset = ap.dataset;//Grite.duplique(itemsets);
		Grite.affiche(ap.dataset);
		System.out.println();
		int a = 0;
		//float[] item = null;
		int taille = 9;
		Grite.getAllColum(dataset, item, a, taille);
		ArrayList<int[][]> allContengent =ap.createGradualsItemsetsOfSize1(dataset, item, 3, taille);
		//ap.createGradualsItemsetsOfSize1(ap.dataset, item, 3,taille);
		System.out.println("Grite.main() " + ap.allContengent.size());
		//ArrayList<int[][]> allContengent = ap.allContengent;
		System.out.println();
		for (@SuppressWarnings("rawtypes")
		Iterator iterator = allContengent.iterator(); iterator.hasNext();) {
			int[][] is = (int[][]) iterator.next();
			affiche(is);
			System.out.println();
			System.out.println("-----------------");

		}
	}
}
