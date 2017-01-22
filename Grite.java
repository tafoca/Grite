package uds.grite.Itemset;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 
 * @author The class encapsulates an implementation of the Grite algorithm to
 *         compute Gradual frequent itemsets.
 * @author tabueu laurent, University of DSCHANG, 2017
 * @copyright GNU General Public License v3 No reproduction in whole or part
 *            without maintaining this copyright notice and imposing this
 *            condition on any subsequent users.
 *
 */
public class Grite {

	private Tools myTools;
	private static int nbitems = 3;//10;//3;
	private int nbtransaction = 9;//100;//9;
	private float threshold = (float) 0.1; //default value
	/** the list of current itemsets */
	public ArrayList<float[]> itemsets = new ArrayList<>();
	// static Hashtable<String, int[][]> allContengent = new Hashtable<>();
	/** the name of the transcation file */
	private String transaFile;
	static ArrayList<boolean[][]> allContengent = new ArrayList<>();
	static ArrayList<boolean[][]> computeAllContengent = new ArrayList<>();
	private SolutionMap GrdItem;
	float[][] dataset;
	float[] item;
	private static int taille = 9;//100;// 9; 
	int a = 3; 
	private String transafile = "transa.dat";// "test.dat";//"gri/I4408.dat";// default transaction file
	private String configfile = "config.dat"; // default configuration file
	private String outputfile = "ouput.dat";
	private static String[] attrList;
	private static ArrayList<String[]> semantique = new ArrayList<>();
	// private static ArrayList<Integer> mySupports = new ArrayList<>();
	/*
	 * 1. Génération des 1-itemsets graduels : pour chaque item i de la base DB,
	 * l’item graduel i ≥ est construit en ordonnant les tO [i] selon la
	 * relation d’ordre ≥,
	 */
	ArrayList<Integer> removedindex;
	private int niveau = 0;
	private int numberPatterns = 0;

	public Grite() throws IOException {
		super();
		myTools = new Tools();
		GrdItem = new SolutionMap();
		// construct db
		getconfig();
		this.itemsets = getDataSet();
		// end of construction db
		this.item = null;
		this.dataset = Grite.duplique(itemsets);
		// Grite.affiche(dataset);
		exec();

	}

	public void exec() {
		allContengent = createGradualsItemsetsOfSize1(dataset, item, a, taille);
		//myTools.setSizeMat(allContengent.get(0).length);
		//myTools.initMemory();
		/*int[] memory0 = myTools.memory;
		System.out.println("sons elt 1: "+myTools.getRoots(allContengent.get(0)));
		System.out.println("sons elt 1: "+myTools.maximumSupport(allContengent.get(0), semantique.get(0), memory0) );
		*/
		//affiche(allContengent.get(0));
		GrdItem.put("level " + getNiveau(), semantique);
		System.out.println("level " + getNiveau() + "-------");
		int i = 0;
		for (Iterator<boolean[][]> iterator = (allContengent).iterator(); iterator.hasNext();) {
			boolean[][] is = (boolean[][]) iterator.next();
			myTools.setSizeMat(is.length);
			myTools.initMemory();
			int[] memory = myTools.memory;
			System.out.println(" -------> " + myTools.printGrad_Itemset(semantique.get(i)) + "( "
					+ myTools.maximumSupport(is, semantique.get(i), memory) + " )" + " <----------- ");
			//affiche(is);
			System.out.println();
			System.out.println("--------------------------------- size ("+is.length +" )");
			i++;

		}
		for (int m = 1; m < attrList.length; m++) {
			allContengent = grite_execution();
			GrdItem.put("level " + getNiveau(), semantique);
			System.out.println("level " + getNiveau() + "-------");
			// Grite.affiche(allContengent.get(0));
			// System.out.println("---- Grite.Grite()---- " +
			// allContengent.size() + "***" + semantique.size());
			// System.out.println("***" + GrdItem.toString());

			int i1 = 0;
			for (Iterator<boolean[][]> iterator = (allContengent).iterator(); iterator.hasNext();) {
				boolean[][] is1 = (boolean[][]) iterator.next();
				myTools.setSizeMat(is1.length);
				myTools.initMemory();
				int[] memory1 = myTools.memory;
				System.out.println(" -------> " + myTools.printGrad_Itemset(semantique.get(i1)) + "( "
						+ myTools.maximumSupport(is1, semantique.get(i1), memory1) + " )" + " <----------- ");
				//affiche(is1);
				System.out.println();
				System.out.println("---------------------------------");
				i1++;

			}

		}

		System.out.println(
				"Grite.exec(), nombre total de motif extrait est de :" + getNumberPatterns() + GrdItem.get("level 1"));
	}

	/**
	 * initialisation parameter of grite: number of item , number of transaction
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
			attrList = Tools.attributenames(nbitems);// new String[nbitems];
			
			// output configuration of the user
			System.out.print("\nInput configuration: " + nbitems + " items,and  " + nbtransaction + " transactions,mes caracteres : ");
			for (int j = 0; j < attrList.length; j++) {
				System.out.print( attrList[j] +" ,");
			}System.out.println();
			System.out.println();
			for (int i = 0; i < semantique.size(); i++) {
				System.out.println(semantique.get(i) + "  ");
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * 
	 * @return Dataset into transaction Data Source
	 * @throws IOException
	 */
	public ArrayList<float[]> getDataSet() throws IOException {
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
				index++;
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

	public static void affiche(boolean[][] tab) {
		for (boolean[] d : tab) {
			System.out.println();
			for (boolean v : d)
				System.out.print(" " + (v ? 1 : 0) + " ");
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
		taille = Grite.taille;
		item = new float[taille];
		int l = 0;
		for (int i = 0; i < dataset.length; i++) {

			for (int k = 0; k < Grite.nbitems; k++) {
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
		for (a = 0; a < Grite.nbitems; a++) {
			float[] rescol = Grite.getDataColByCol(dataset, item, a, taille);
			for (int i = 0; i < rescol.length; i++) {
				System.out.println(rescol[i] + "  ");
			}
			System.out.println();
		}
	}

	private ArrayList<boolean[][]> createGradualsItemsetsOfSize1(float[][] dataset, float[] item, int a, int taille) {
		ArrayList<boolean[][]> allContengent = new ArrayList<>();
		ArrayList<String[]> semantique = new ArrayList<>();
		// ArrayList<Integer> mySupports = new ArrayList<>();
		for (int i = 0; i < nbitems; i++) {
			float[] rescol = Grite.getDataColByCol(dataset, item, i, taille);
			String[] attr = new String[2];
			attr[0] = attrList[i];
			attr[1] = "+";
			// gestion objets croissant X> et creation matrice contigence
			// associe
			boolean[][] Contengence1 = new boolean[taille][taille];

			for (int j = 0; j < taille; j++) {
				for (int j2 = j + 1; j2 < taille; j2++) {
					if (rescol[j] < rescol[j2]) {
						Contengence1[j][j2] = true;
					} else {
						Contengence1[j][j2] = false;
					}
				}
			}
			myTools.setSizeMat(Contengence1.length);
			myTools.initMemory();
			int[] memory = myTools.memory;
			int cpt = myTools.maximumSupport(Contengence1, attr, memory);
			float support = myTools.supportCalculation(cpt, Contengence1.length);
			if (support > this.threshold) {
				allContengent.add(Contengence1);
				semantique.add(attr);
				// mySupports.add(cpt);
			}

			// gestion objets decroissant X< et creation matrice contigence
			// associe
			String[] attr1 = new String[2];
			attr1[0] = attrList[i];
			attr1[1] = "-";
			boolean[][] Contengence2 = new boolean[taille][taille];

			for (int j = 0; j < taille; j++) {
				for (int j2 = j + 1; j2 < taille; j2++) {
					if (rescol[j] > rescol[j2]) {
						Contengence2[j][j2] = true;
					} else {
						Contengence2[j][j2] = false;
					}
				}
			}
			myTools.setSizeMat(Contengence2.length);
			myTools.initMemory();
			int[] memory1 = myTools.memory;
			int cpt1 = myTools.maximumSupport(Contengence2, attr1, memory1);
			float support1 = myTools.supportCalculation(cpt1, Contengence2.length);
			if (support1 > this.threshold) {
				allContengent.add(Contengence2);
				semantique.add(attr1);
				// mySupports.add(cpt1);
			}

		}
		setNiveau(getNiveau() + 1);
		GrdItem.put("level" + getNiveau(), semantique);
		setNumberPatterns(getNumberPatterns() + semantique.size());
		Grite.semantique = semantique;
		return allContengent;

	}

	public static boolean[][] jointure(boolean[][] m1, boolean[][] m2) {
		boolean[][] res = new boolean[m1.length][m1.length];

		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res.length; j++) {
				res[i][j] = (m1[i][j] & m2[i][j]);
			}
		}

		return res;
	}

	private boolean IsItIsolateItem(boolean[][] m, int objet) {
		boolean isremoved = false;
		int i = 0;
		for (; i < m.length; i++) {
			if (!m[objet][i]) {
				continue;
			} else {
				break;
			}
		}
		if (i == m.length)
			isremoved = true;

		for (i = 0; i < m.length; i++) {
			if (!m[i][objet]) {
				continue;
			} else {
				break;
			}
		}

		if (i == m.length)
			isremoved &= true;
		return isremoved;

	}
	
	/**
	 * @return the threshold
	 */
	public float getThreshold() {
		return threshold;
	}

	/**
	 * @param threshold the threshold to set
	 */
	public void setThreshold(float threshold) {
		this.threshold = threshold;
	}

	/**
	 * @return the taille
	 */
	public int getTaille() {
		return taille;
	}

	/**
	 * @param taille the taille to set
	 */
	public void setTaille(int taille) {
		this.taille = taille;
	}

	/**
	 * @return the attrList
	 */
	public static String[] getAttrList() {
		return attrList;
	}

	/**
	 * @param attrList the attrList to set
	 */
	public static void setAttrList(String[] attrList) {
		Grite.attrList = attrList;
	}

	private boolean[][] allIsolateItem(boolean[][] m) {
		ArrayList<Integer> objectremovable = new ArrayList<>();
		for (int i = 0; i < m.length; i++) {
			if (IsItIsolateItem(m, i)) {
				objectremovable.add(i);
			}
		}

		boolean[][] result = new boolean[m.length - objectremovable.size()][m.length - objectremovable.size()];
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < m.length; j++) {

			}
		}
		return m;

	}

	public ArrayList<boolean[][]> grite_execution() {
		// createGradualsItemsetsOfSize1();
		ArrayList<boolean[][]> computeAllContengent = new ArrayList<>();
		ArrayList<String[]> semantiques = new ArrayList<>();
		// ArrayList<Integer> mySupport = new ArrayList<>();
		String[] tmp1;
		boolean[][] tmp2;
		int cpt;
		float support;
		for (int i = 0; i < allContengent.size(); i++) {
			for (int j = i + 1; j < allContengent.size(); j++) {
				if (myTools.lexicalComparaison(semantique.get(i), semantique.get(j))) {
					tmp1 = myTools.lexicalFusion(semantique.get(i), semantique.get(j));
					tmp2 = Grite.jointure(allContengent.get(i), allContengent.get(j));
					myTools.setSizeMat(tmp2.length);
					// myTools.initMemory();
					int[] memory = myTools.memory;
					cpt = myTools.maximumSupport(tmp2, tmp1, memory);
					support = myTools.supportCalculation(cpt, tmp2.length);
					if (support > this.threshold) {
						computeAllContengent.add(tmp2);
						semantiques.add(tmp1);
						// mySupport.add(cpt);
					}

				}
			}
		}
		Grite.allContengent.clear();
		Grite.semantique.clear();
		// Grite.mySupports.clear();
		// determination frequent itemset
		Grite.allContengent = computeAllContengent;
		Grite.semantique = semantiques;
		// Grite.mySupports = mySupport;
		setNiveau(niveau + 1);
		GrdItem.put("level" + getNiveau(), semantiques);
		setNumberPatterns(getNumberPatterns() + semantiques.size());

		return computeAllContengent;
	}

	/**
	 * @return the niveau
	 */
	public int getNiveau() {
		return niveau;
	}

	/**
	 * @param niveau
	 *            the niveau to set
	 */
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	/**
	 * @return the numberPatterns
	 */
	public int getNumberPatterns() {
		return numberPatterns;
	}

	/**
	 * @param numberPatterns
	 *            the numberPatterns to set
	 */
	public void setNumberPatterns(int numberPatterns) {
		this.numberPatterns = numberPatterns;
	}

	private class SolutionMap {
		// contains graduals pattern of all level
		HashMap<String, ArrayList<String[]>> graduelSet;

		public SolutionMap() {
			super();
			graduelSet = new HashMap<>();
		}

		public HashMap<String, ArrayList<String[]>> getGraduelSet() {
			return graduelSet;
		}

		public void setGraduelSet(HashMap<String, ArrayList<String[]>> graduelSet) {
			this.graduelSet = graduelSet;
		}

		/**
		 * 
		 * @see java.util.HashMap#clear()
		 */
		public void clear() {
			graduelSet.clear();
		}

		/**
		 * @param arg0
		 * @return
		 * @see java.util.HashMap#containsKey(java.lang.Object)
		 */
		public boolean containsKey(Object arg0) {
			return graduelSet.containsKey(arg0);
		}

		/**
		 * @param arg0
		 * @return
		 * @see java.util.HashMap#get(java.lang.Object)
		 */
		public ArrayList<String[]> get(Object arg0) {
			return graduelSet.get(arg0);
		}

		/**
		 * @return
		 * @see java.util.HashMap#isEmpty()
		 */
		public boolean isEmpty() {
			return graduelSet.isEmpty();
		}

		/**
		 * @return
		 * @see java.util.HashMap#keySet()
		 */
		public Set<String> keySet() {
			return graduelSet.keySet();
		}

		/**
		 * @param arg0
		 * @param arg1
		 * @return
		 * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
		 */
		public ArrayList<String[]> put(String arg0, ArrayList<String[]> arg1) {
			return graduelSet.put(arg0, arg1);
		}

		/**
		 * @param arg0
		 * @see java.util.HashMap#putAll(java.util.Map)
		 */
		public void putAll(Map<? extends String, ? extends ArrayList<String[]>> arg0) {
			graduelSet.putAll(arg0);
		}

		/**
		 * @return
		 * @see java.util.HashMap#size()
		 */
		public int size() {
			return graduelSet.size();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "SolutionMap [graduelSet=" + graduelSet + ", getGraduelSet()=" + getGraduelSet() + ", isEmpty()="
					+ isEmpty() + ", keySet()=" + keySet() + ", size()=" + size() + ", getClass()=" + getClass()
					+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//float[] item = new float[9];
		Grite ap = new Grite();
		
		/*ap.getconfig();
		ArrayList<float[]> itemsets = ap.itemsets;
		 */
		 /* for (ArrayList<Integer> arrayList : itemsets) { for (Integer integer
		  : arrayList) { System.out.println("< "+integer+ " />"); } }*/
		  
		  /*float[][] dataset = ap.dataset;// Grite.duplique(itemsets); //
		  Grite.affiche(ap.dataset); System.out.println();
		  int a = 0; //
		  float[] item = null; 
		  int taille = 100; 
		  Grite.getAllColum(dataset, item,
		  a, taille); ap.grite_execution(); ArrayList<boolean[][]>
		  allContengent = ap.createGradualsItemsetsOfSize1(dataset, item, 10,
		  taille); 
		  // ap.createGradualsItemsetsOfSize1(ap.dataset, item, 3,taille); 
		  System.out.println( "Grite.main() " + allContengent.size()
		  + " nombre de regle graduel semantique :" + semantique.size());
		  System.out.println();
		 */

	}
}
