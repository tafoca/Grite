package uds.Grite;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	private static FileWriter fw;

	public static void main(String[] args) throws IOException {

		try {
			fw = new FileWriter(new File("output.dat"));
			for (int j = 0; j < 12; j++) {
				fw.write(12 +j + " " + 16 +j  +" " + 15.47+j +" "+ 200  +j+"\n");
				fw.flush();
			}
			fw.close();
		} finally {
		}
	}
}