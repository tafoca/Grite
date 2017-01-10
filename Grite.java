package uds.grite.Itemset;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
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
	private int nbtransaction = 9;
	/** the list of current itemsets */
	public ArrayList<float[]> itemsets = new ArrayList<>();
	/** the name of the transcation file */
	private String transaFile;
	static ArrayList<int[][]> allContengent= new ArrayList<>();
	float[][] dataset;
	float[] item;
	int taille= 9;
	int a = 3;
	private String transafile = "transa.dat"; // default transaction file
	private String configfile = "config.dat"; // default configuration file

	/*
	 * 1. Génération des 1-itemsets graduels : pour chaque item i de la base DB,
	 * l’item graduel i ≥ est construit en ordonnant les tO [i] selon la
	 * relation d’ordre ≥,
	 */

	public Grite() throws IOException {
		super();
		//construct db
		getconfig();
		this.itemsets = getDataSet();
		//end of construction db
		this.item= null;
		this.dataset = Grite.duplique(itemsets);
		//Grite.affiche(dataset);
		allContengent = createGradualsItemsetsOfSize1(dataset, item, a, taille);
		Grite.affiche(allContengent.get(0));
		grite_execution();
		
		
	}
	
	/**
	 * initialisation parameter of grite: number of item , number
	 * of transaction
	 * 
	 * @throws IOException 
	 */
	public void getconfig() throws IOException {
		FileInputStream file_in;
		BufferedReader data_in;
		String oneLine = "";
		// open the config file and load the values
		try {
			file_in = new FileInputStream(configfile);
			data_in = new BufferedReader(new InputStreamReader(file_in));

			// number of transactions
			oneLine = data_in.readLine();
			nbtransaction = Integer.valueOf(oneLine).intValue();

			// number of items
			oneLine = data_in.readLine();
			nbitems = Integer.valueOf(oneLine).intValue();
			
			// output configuration of the user
			System.out.print("\nInput configuration: " + nbitems + " items,and  " + nbtransaction + " transactions. ");
			System.out.println();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * 
	 * @return Dataset into transaction Data Source
	 * @throws IOException
	 */
	public  ArrayList<float[]> getDataSet() throws IOException {
		BufferedReader data_in;
		String oneLine = "";

		data_in = new BufferedReader(new InputStreamReader(new FileInputStream(transafile)));
		for (int i = 0; i < nbtransaction; i++) {
			float[] tmp = new float[nbitems];
			oneLine = data_in.readLine(); // one transaction
			StringTokenizer transaction = new StringTokenizer(oneLine, " "); 
			float val;
			int index = 0;
			while (transaction.hasMoreElements()) {
				Object object = (Object) transaction.nextElement();
				val = Float.parseFloat((String) object);
				tmp[index] = (val);
				index ++;
			}
			itemsets.add(tmp);

		}
		data_in.close();
		return itemsets;

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
