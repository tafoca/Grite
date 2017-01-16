package uds.grite.Itemset;

import java.util.ArrayList;

public class Tools {
	int i;

	public boolean lexicalComparaison(Character[] pattern1, Character[] pattern2) {

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

	public Character[] lexicalFusion(Character[] pattern1, Character[] pattern2) {
		Character[] fusion = null;
		int i;
		if (lexicalComparaison(pattern1, pattern2)) {
			fusion = new Character[pattern1.length + 2];
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

	public String printGrad_Itemset(Character[] setGrd) {
		String character, res = "";
		for (int i = 0; i < setGrd.length; i++) {

			if (i % 2 != 0) {
				character = setGrd[i].toString() + " ";
			} else {
				character = setGrd[i].toString();
			}
			res += character;
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
			if (boolMatrix[item][i]) {
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
	public int sizeMaxWay(boolean[][] boolMatrix, Character[] pattern, int[] memory, int item) {
		// intialisation of memory table
		for (int i = 0; i < memory.length; i++) {
			memory[i] = -1;
		}

		ArrayList<Integer> sons = getSonNode(boolMatrix,
				item); /* tous les o ′ de valeur 1 à la ligne o */
		if (sons.size() == 0) {
			memory[item] = 1;
		} else {
			for (Object element : sons) {
				int son = (int) element;
				if (memory[son] == -1) {
					sizeMaxWay(boolMatrix, pattern, memory, son);
				}
			}

			for (Object element : sons) {
				int son = (int) element;
				memory[item] = max(memory[item], memory[son] + 1);
			}
		}
		return memory[item];

	}

	private int max(int j, int k) {
		if (j > k) {
			return j;
		} else {
			return k;
		}
	}

	public float supportCalculation(int nb, int total) {
		return nb / total;
	}
	
	 public boolean Apartient(ArrayList<Integer> tampon, int val){
		 for (int i = 0; i < tampon.size(); i++) {
			if (tampon.get(i) == val) {
				return true;
			}
		}
		return false;
		 
	 }
}