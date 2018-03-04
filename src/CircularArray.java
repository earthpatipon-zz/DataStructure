
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CircularArray {
    static class CArray{
        public Integer[] data; // Array containing the integer data
        public int front; // index pointing at the front
        public int end; // index pointing at the end
        public int size; // size of the current array
        
        /* You must not delete anything above this line
        * Note that the function must
        * You must complete the following functions.
        * 1) void addFirst(int value) ==> add function at the front index
        * 2) void addLast(int value) ==> add function at the end index
        * 3) Integer removeFirst() ==> remove the integer at the front index
        *                               unless it is empty in which case return null
        * 4) Integer removeLast() ==> remove the integer at the end index
        *                               unless it is empty in which case return null
        * 5) int findObject(int value) ==> find the index of the current object, 
        *                               if not found, -1 is returned
        * 6) Integer removeObject(int value) ==> remove the object that contains the value
        *                                       from the list, if value is not on, null is returned
        * 7) CArray(int length) ==> the constructor function where all values are initialized
        */
        public CArray(int length){
        	data = new Integer[length];
        	front = end = size = 0;
        }
        
        public boolean isFull(){
        	return size==data.length;
        }
        
        public boolean isEmpty(){
        	return size==0;
        }

        public void addFirst(int value){
        	if(!isEmpty() && !isFull()){
        		data[front++] = value;
        		size++;
        	}
        	else if(isEmpty()){
        		data[0] = value;
        		size++;
        	}
        	else if(isFull())
        		return;
        }
        
        public void addLast(int value){
        	if(!isEmpty() && !isFull()){
        		data[end--] = value;
        		size++;
        	}
        	else if(isEmpty()){
        		end = data.length-1;
        		data[end--] = value;
        	}
        	else if(isFull())
        		return;
        }
        
        
        public Integer removeFirst(){
        	if(!isEmpty()){
        		int temp = data[front];
        		data[front--] = null;
        		if(front==0)
        			front = data.length -1;
        		return temp;
        	}
        	else
        		return -1;
        }
        
        public Integer removeLast(){
        	if(!isEmpty()){
        		int temp = data[end];
        		data[end++] = null;
        		if(end==data.length)
        			end = 0;
        		return temp;
        	}
        	else
        		return -1;
        }
        
        public Integer removeObject(int value){
        	if(!isEmpty()){
        		int index = this.findObject(value);
        		int temp = data[index];
        		if(index==front)
        			return removeFirst();
        		else if(index==end)
        			return removeLast();
        		else{
	        		for(int i=front, j=0; j<size; j++){
	        			if(data[i] == value){
	        				if(end<=front){
		        				for(int k=i;k<front-1;k++){
		                            data[k] = data[k+1];
		                        }
		                        front--;
	        				}
	        				else{
	                            if(i < front){
	                            	for(int k=i;k<front;k++){
	                                    data[k] = data[k+1];
	                                }
	                            	data[end] = null;
	                                front--;
	                                if(front < 0) front = data.length - 1;
	                            }
	                            else{
	                            	for(int k=i;k>end;k--){
	                                    data[k] = data[k-1];
	                                }
	                            	data[end] = null;
	                                end++;
	                                if(end == data.length) end = 0;
	                            }
	                        }
	        				size--;
	                        return temp;
	        			}
	        			i--;
	        			if(i<0)
	        				i = data.length -1;
	        		}
	        		return -1;
        		}
        	}
        	return -1;
        }
        
        public int findObject(int value){
        	if(isEmpty()){
        		for(int i=front, j=0; j<size; j++){
        			if(data[i] == value)
        				return i;
        			i--;
        			if(i<0)
        				i = data.length -1;
        		}
        		return -1;
        	}
        	return -1;
        }
        
    /*You must not edit anything below this line. 
     * Otherwise, we will not be able to test your program and you will get 0 point
     */        
        public String toString(){
            int i,j;
            String out = "";
            if(size > 0){
                for(j=0,i=front; j < size; j++){
                    out = out + " (" + data[i].intValue() +") ";
                    i--;
                    if(i < 0){
                        i = data.length - 1;
                    }
                }
            }
            return out;
        }
        
    }        
    
  public static void main (String arg[])throws IOException {
    BufferedReader br = new BufferedReader (
			   new InputStreamReader(System.in));
    String line = br.readLine();
    StringTokenizer tokens;
    int numTestCase = Integer.parseInt(line);
    int ndex, cNum, rNum, ldex, rdex, cdex;
    Integer fin;
    CArray test_array;
    for(ndex = 0; ndex < numTestCase; ndex++){
        line = br.readLine();
        tokens = new StringTokenizer(line, " \t\r\n");
        rNum = Integer.parseInt(tokens.nextToken());
        cNum = Integer.parseInt(tokens.nextToken());
        test_array = new CArray(rNum);
        for(cdex = 0; cdex < cNum; cdex++){
            line = br.readLine();
            tokens = new StringTokenizer(line, " \t\r\n");
            ldex = Integer.parseInt(tokens.nextToken());                        
            switch(ldex){
                case 0: fin = Integer.parseInt(tokens.nextToken());
                    test_array.addFirst(fin);
                    break;
                case 1: fin = test_array.removeFirst();
                    if(fin == null){
                        System.out.println("the list is empty");
                    }else{
                        System.out.println("removed from first is " + fin.toString());
                    }
                    break;
                case 2: rdex = Integer.parseInt(tokens.nextToken());
                    fin = test_array.findObject(rdex);
                    if(fin < 0){
                        System.out.println(rdex + " is not on the array");
                    }else{
                        System.out.println(rdex + " is found on the array");
                    }
                    break;
                case 3: fin = Integer.parseInt(tokens.nextToken());
                    test_array.addLast(fin);
                    break;
                case 4: fin = test_array.removeLast();
                    if(fin == null){
                        System.out.println("the list is empty");
                    }else{
                        System.out.println("remove from last is " + fin.intValue());
                    }
                    break;
                case 5: rdex = Integer.parseInt(tokens.nextToken());
                    Integer tFin = test_array.removeObject(rdex);
                    if(tFin == null){
                        System.out.println(rdex + " is not on the array");
                    }else{
                        System.out.println(tFin.intValue() + " is removed from the array");                    
                    }
                    break;
                default:
                    System.out.println(test_array.toString());
            }
        }        
    }
  }            
}
