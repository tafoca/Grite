package uds.grite.Itemset;

/**
 * @author tabueu Fotso Laurent
 * @copyright GNU General Public License v3 No reproduction in whole or part
 *            without maintaining this copyright notice and imposing this
 *            condition on any subsequent users.
 */
import java.util.ArrayList;
import java.util.Iterator;

public class Tools {
	int i;
	int[] memory;
	int sizeMat = 10;

	public Tools() {
		super();
		initMemory();
	}

	public int getSizeMat() {
		return sizeMat;
	}

	public void setSizeMat(int sizeMat) {
		this.sizeMat = sizeMat;
	}

	public boolean lexicalComparaison(String[] pattern1, String[] pattern2) {

		// case of 1-gradualItemset
		if (pattern1.length == pattern2.length && pattern1.length == 2) {
			if (pattern1[0] == pattern2[0])
				return false;
			return true;
		}
		// case pattern of size 2
		if (pattern1.length != pattern2.length) {
			return false;
		} else {
			for (i = 0; i < pattern2.length - 3; i++) {
				if (pattern1[i] == pattern2[i]) {
					continue;
				} else {
					break;
				}
			}
			if (i == pattern2.length - 3 && pattern1[pattern1.length - 2] != pattern2[pattern1.length - 2]) {
				return true;
			} else {
				return false;
			}
		}
	}

	public String[] lexicalFusion(String[] pattern1, String[] pattern2) {
		String[] fusion = null;
		int i;
		if (lexicalComparaison(pattern1, pattern2)) {
			fusion = new String[pattern1.length + 2];
			// fusion = pattern1 +pattern2;
			for (i = 0; i < pattern1.length; i++) {

				fusion[i] = pattern1[i];
			}
			fusion[pattern1.length] = pattern2[pattern1.length - 2];
			fusion[pattern1.length + 1] = pattern2[pattern1.length - 1];
			return fusion;
		}
		return fusion;// null value
	}

	public String printGrad_Itemset(String[] setGrd) {
		String String, res = "";
		for (int i = 0; i < setGrd.length; i++) {

			if (i % 2 != 0) {
				String = setGrd[i].toString() + " ";
			} else {
				String = setGrd[i].toString();
			}
			res += String;
		}
		return res;
	}

	/**
	 * 
	 * @param boolMatrix
	 * @param item
	 *            variation between 0 to nbItems -1
	 * @return
	 */
	public ArrayList<Integer> getSonNode(boolean[][] boolMatrix, int item) {
		ArrayList<Integer> sons = new ArrayList<>();
		for (int i = 0; i < boolMatrix.length; i++) {
			if (boolMatrix[item][i] == true) {
				sons.add(i);
			}
		}
		return sons;

	}

	/**
	 * 
	 * @param boolMatrix
	 * @param pattern
	 * @param memory
	 * @param item
	 *            root node to determine size of different way
	 * @return
	 */
	public void initMemory() {
		memory = new int[sizeMat];
		for (int i = 0; i < memory.length; i++) {
			memory[i] = -1;
		}
	}

	public int sizeMaxWay(boolean[][] boolMatrix, /* String[] pattern, */int item) {
		// intialisation of memory table

		ArrayList<Integer> sons = getSonNode(boolMatrix,
				item); /* tous les o ′ de valeur 1 à la ligne o */
		if (sons.size() == 0) {
			memory[item] = 1;
		} else {
			for (Object element : sons) {
				int son = (int) element;
				if (memory[son] == -1) {// pattern,memory,
					sizeMaxWay(boolMatrix, son);
				}
			}

			for (Object element : sons) {
				int son = (int) element;
				memory[item] = max(memory[item], memory[son] + 1);
			}
		}
		return memory[item];

	}

	public ArrayList<Object> getColum(boolean[][] dataset, int col) {
		ArrayList<Object> item = new ArrayList<>();

		for (int i = 0; i < dataset.length; i++) {
			item.add(dataset[i][col]);
		}

		return item;
	}

	// return all root nodes of graph
	public ArrayList<Object> getRoots(boolean[][] src) {
		ArrayList<Object> res = new ArrayList<>();
		ArrayList<Object> out = new ArrayList<>();
		int j;

		for (int i = 0; i < src.length; i++) {
			res = getColum(src, i);
			boolean sentinnel = false;
			for (j = 0; j < res.size(); j++) {
				if ((boolean) res.get(j) == true) {
					sentinnel = true;
				}
			}
			if (!sentinnel) {
				out.add(i);
			}
		}
		return out;

	}

	private int max(int j, int k) {
		if (j > k) {
			return j;
		} else {
			return k;
		}
	}

	// computation of support of all contengence matrix boolmatrix
	public int maximumSupport(boolean[][] boolMatrix, String[] pattern, int[] memory) { // pattern,
																						// memory,
		int max = sizeMaxWay(boolMatrix, (int) getRoots(boolMatrix).get(0));
		for (int i = 0; i < getRoots(boolMatrix).size(); i++) {// , pattern,
																// memory,
			int tmp = sizeMaxWay(boolMatrix, i);
			if (max < tmp) {
				max = tmp;
			}
		}
		return max;
	}

	public float supportCalculation(int nb, int total) {
		return (float) nb / total;
	}

	public boolean Apartient(ArrayList<Integer> tampon, int val) {
		for (int i = 0; i < tampon.size(); i++) {
			if (tampon.get(i) == val) {
				return true;
			}
		}
		return false;

	}

	/**
	 * we changed Character[] by String[] due more than 9 attribut this
	 * sequential representation begin impossible
	 * 
	 * @param nbitem
	 * @return liste of items here represented by sequence :1,2,3,....
	 */
	public static String[] attributenames(int nbitem) {
		String[] attrList = new String[nbitem];
		String c = "1";
		for (int i = 0; i < attrList.length; i++) {
			attrList[i] = c;
			c = "" + (Integer.parseInt(c) + 1);
		}
		return attrList;
	}
	public void writeFileOut(ArrayList<String[]> semantique){
		
		for (Iterator iterator = semantique.iterator(); iterator.hasNext();) {
			String[] strings = (String[]) iterator.next();
			
		}
		
	}
}
