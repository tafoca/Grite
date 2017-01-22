package uds.Grite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Test {

	private static String[] ts;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String[] t1 = { "0", "+", "2", "-" };
		String[] t2 = { "0", "+", "3", "-" };
		String[] t3 = { "2", "-" };
		String[] t4 = { "2", "+" };
		Tools outils = new Tools();
		boolean b = outils.lexicalComparaison(t1, t2);
		boolean b1 = outils.lexicalComparaison(t1, t3);
		boolean b2 = outils.lexicalComparaison(t3, t2);
		ts = new String[8];
		ts = outils.lexicalFusion(t1, t2);
		boolean[][] mat = { { false, true, true, false, false, false, false, false, false, false },
				{ false, false, false, true, false, false, false, false, false, false },
				{ false, false, false, true, false, false, false, false, false, false },
				{ false, false, false, false, true, true, false, false, false, false },
				{ false, false, false, false, false, false, false, false, true, false },
				{ false, false, false, false, false, false, true, false, false, false },
				{ false, false, false, false, false, false, false, true, false, false },
				{ false, false, false, false, false, false, false, false, true, false },
				{ false, false, false, false, false, false, false, false, false, false },
				{ false, false, false, false, false, false, true, false, false, false } };

		Grite gt = new Grite();
		gt.affiche(mat);

		System.out.println("Test.main()");
		ArrayList<Object> m = outils.getRoots(mat);
		/*
		 * boolean[] t = new boolean[10]; boolean[] t11 =
		 * outils.getDataColByCol(mat, t,7); for (int i = 0; i < t11.length;
		 * i++) { System.out.println(t11[i] ? 1 : 0+" "); }
		 */
		/*
		 * System.out.println("TestList :"+m);
		 * System.out.println("Test.main()---test1 =" + outils.sizeMaxWay(mat,
		 * 9)); System.out.println("Test.main()---test2 =" +
		 * outils.getSonNode(mat, 9));
		 */
		outils.initMemory();
		int[] memory = outils.memory;
		int max = outils.maximumSupport(mat, t1, memory);
		System.out.println("Test.main()---test3 = " + outils.supportCalculation(max, 10) + "( " + max + " )");

		/*
		 * System.out.println("****** " + outils.printGrad_Itemset(ts));
		 * 
		 */
		int nbitems = 100;
		String[] attrList = attributenames(nbitems);
		for (int j = 0; j < attrList.length; j++) {
			System.out.println("mes caracteres : " + attrList[j]);
		}
	}

	public static String[] attributenames(int nbitem) {
		String[] attrList = new String[nbitem];
		String c = "1";
		for (int i = 0; i < attrList.length; i++) {
			attrList[i] = c;
			c = "" + (Integer.parseInt(c) + 1);
		}
		return attrList;
	}
}
