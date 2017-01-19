package uds.grite.Itemset;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Test {

	private static Character[] ts;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Character[] t1 = { '0', '+', '2', '-' };
		Character[] t2 = { '0', '+', '3', '-' };
		Character[] t3 = { '2', '-' };
		Character[] t4 = { '2', '+' };
		Tools outils = new Tools();
		boolean b = outils.lexicalComparaison(t1, t2);
		boolean b1 = outils.lexicalComparaison(t1, t3);
		boolean b2 = outils.lexicalComparaison(t3, t2);
		ts = new Character[8];
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
				{ false, false, false, false, false, false, true, false, false, false }};
		
		Grite gt = new Grite();
		gt.affiche(mat);
		
		System.out.println("Test.main()");
		ArrayList<Object> m = outils.getRoots(mat);
		/*boolean[] t = new boolean[10];
		boolean[] t11 = outils.getDataColByCol(mat, t,7);
		for (int i = 0; i < t11.length; i++) {
			System.out.println(t11[i] ? 1 : 0+" ");
		}*/
		/*System.out.println("TestList :"+m);
		System.out.println("Test.main()---test1 =" + outils.sizeMaxWay(mat, 9));
		System.out.println("Test.main()---test2 =" + outils.getSonNode(mat, 9));*/
		int max = outils.maximumSupport(mat, t1, outils.memory);
		System.out.println("Test.main()---test3 = " +outils.supportCalculation(max, 10) );
/*		System.out.println("****** " + outils.printGrad_Itemset(ts));
*/	}

}
