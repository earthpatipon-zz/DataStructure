
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author apirakhoonlor
 */
public class SArray {
	static class LArray {
		public Long[] data; // data
		public int size; // size of the array

		/*
		 * You should complete the following function LArray() == This is the
		 * constructor for LArray class; addFirst(long tar) == This function add
		 * "tar" to the first object on the list. If the array is full return
		 * false. Otherwise, return true removeFirst() == This function remove
		 * the first object in the list; if the list is empty return null;
		 * findObject(long tar) == return the index of the object at has the
		 * tar. if there is none at that position return null
		 * 
		 */
		public LArray(int length) {
			data = new Long[length];
			size = 0;
		}

		public boolean addFirst(long value) {
			if (size < data.length) {
				if (size == 0) {
					data[0] = value;
					size++;
					return true;
				} else {
					for (int i = size; i > 0; i--) {
						data[i] = data[i - 1];
					}
					data[0] = value;
					size++;
					return true;
				}
			} else
				return false;
		}

		public Long removeFirst() {
			if (size != 0) {
				Long temp = new Long(data[0]);
				for (int i = 0; i < this.size-1; i++) {
					data[i] = data[i + 1];
				}
				size--;
				return temp;
			} else
				return null;
		}

		public int findObject(long target) {
			if (size != 0) {
				for (int i = 0; i < size; i++) {
					if (data[i] == target)
						return i;
				}
				return -1;
			} else
				return -1;
		}

		/*
		 * You must not edit the toString function below. Otherwise, we will not
		 * be able to test your program and you will get 0 point
		 */
		public String toString() {
			int j = 0;
			String out = "There are " + size + " objects in the array";
			return out;
		}

	}

	/*
	 * You must not edit the main function below. Otherwise, we will not be able
	 * to test your program and you will get 0 point
	 */
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		StringTokenizer tokens;
		int numTestCase = Integer.parseInt(line);
		int ndex, cNum, rNum, ldex, rdex, cdex;
		long fin;
		LArray test_array;
		for (ndex = 0; ndex < numTestCase; ndex++) {
			line = br.readLine();
			tokens = new StringTokenizer(line, " \t\r\n");
			rNum = Integer.parseInt(tokens.nextToken());
			cNum = Integer.parseInt(tokens.nextToken());
			test_array = new LArray(rNum);
			for (cdex = 0; cdex < cNum; cdex++) {
				line = br.readLine();
				tokens = new StringTokenizer(line, " \t\r\n");
				ldex = Integer.parseInt(tokens.nextToken());
				switch (ldex) {
				case 0:
					fin = Long.parseLong(tokens.nextToken());
					test_array.addFirst(fin);
					break;
				case 1:
					Long tF = test_array.removeFirst();
					if (tF == null) {
						System.out.println("The list is empty");
					} else {
						System.out.println(tF.longValue() + " is removed ");
					}
					break;
				case 2:
					fin = Long.parseLong(tokens.nextToken());
					rdex = test_array.findObject(fin);
					if (rdex >= 0) {
						System.out.println(fin + " is found");
					} else {
						System.out.println(fin + " is not found");
					}
					break;
				default:
					System.out.println(test_array.toString());
				}
			}
		}
	}
}
