
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * You must implement DoubleHash class by implementing DataItem, and HashTable
 * classes following the DataItem and HashTable defined in the textbook page
 * 546-548. Note that they are parts of the hashDouble.java class.
 * The hash function 1 and 2 must be exactly the same as in the textbook on page
 * 547. Note that you must write all the functions in these pages to test with 
 * our test caeses.
 */

public class DoubleHash {
    
    static class DataItem{
        private int iData;
        
        public DataItem(int ii){
        	iData = ii;
        }
        
        public int getKey(){
        	return iData;
        }
    }
    
    static class HashTable{
        private DataItem[] hashArray;
        private int arraySize;
        private DataItem nonItem;
        
        public HashTable(int size){
        	arraySize = size;
        	hashArray = new DataItem[arraySize];
        	nonItem = new DataItem(-1);
        }
        
        public int hashFunc1(int key){
        	return key%arraySize;
        }
        
        public int hashFunc2(int key){
        	return 5 - key%5;
        }
        
        public void displayTable(){
        	System.out.print("Table: ");
        	for(int j=0; j<arraySize; j++){
        		if(hashArray[j]!=null)
        			System.out.print(hashArray[j].getKey() + " ");
        		else
        			System.out.print("");
        	}
        	System.out.println("");
        }
        
        public void insert(DataItem item){
        	int key = item.getKey();
        	int hashVal = hashFunc1(key);
        	int stepSize = hashFunc2(key);
        	while(hashArray[hashVal]!=null && hashArray[hashVal].getKey() != -1){
        		hashVal += stepSize;
        		hashVal %= arraySize;
        	}
        	hashArray[hashVal] = item;
        }
        
        public DataItem delete(int key){
        	int hashVal = hashFunc1(key);
        	int stepSize = hashFunc2(key);
        	while(hashArray[hashVal] != null){
        		if(hashArray[hashVal].getKey() == key){
        			DataItem temp = hashArray[hashVal];
        			hashArray[hashVal] = nonItem;
        			return temp;
        		}
        		hashVal += stepSize;
        		hashVal %= arraySize;
        	}
        	return null;
        }
        
        public DataItem find(int key){
        	int hashVal = hashFunc1(key);
        	int stepSize = hashFunc2(key);
        	while(hashArray[hashVal] != null){
        		if(hashArray[hashVal].getKey()== key)
        			return hashArray[hashVal];
        		hashVal += stepSize;
        		hashVal %= arraySize;
        	}
        	return null;
        }
    }
    /*
    * You must not edit anything below this line
    */
    
    public HashTable hTable;
    
    public DoubleHash(int size){
        hTable = new HashTable(size);
    }
    
    
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader (
                               new InputStreamReader(System.in));
      String line = br.readLine();
      int numTestCase = Integer.parseInt(line); 
      int ndex, tdex, tmax, ldex, key, value, size;
      StringTokenizer tokens;
      DoubleHash DHTest;
      DataItem dTest;
      for(ndex = 0; ndex < numTestCase; ndex++){
        line = br.readLine();
        tokens = new StringTokenizer(line, " \t\r\n");
        tmax = Integer.parseInt(tokens.nextToken());
        size = Integer.parseInt(tokens.nextToken());
        DHTest = new DoubleHash(size);
        for(tdex = 0; tdex < tmax; tdex++){
            line = br.readLine();
            tokens = new StringTokenizer(line, " \t\r\n");
            ldex = Integer.parseInt(tokens.nextToken());                        
            switch(ldex){
                case 0: // Test insert
                    key = Integer.parseInt(tokens.nextToken());
                    DHTest.hTable.insert(new DataItem(key)); // insert(key)
                    break;
                case 1: // Test delete
                    key = Integer.parseInt(tokens.nextToken()); // key
                    dTest = DHTest.hTable.delete(key); // delete(key)
                    if(dTest == null){
                        System.out.println("Deletion of " + key + " is not successful.");
                    }else{
                        System.out.println("Deletion of " + key + " is successful.");
                    }
                    break;
                case 2: // find key
                    key = Integer.parseInt(tokens.nextToken());// key
                    dTest = DHTest.hTable.find(key);
                    if(dTest == null){
                        System.out.println("Could not find " + key);
                    }else{
                        System.out.println("We found the key " + key);
                    }
                    break;
                default: // print
                    DHTest.hTable.displayTable();
            }            
        }
    }
    }
        
}
