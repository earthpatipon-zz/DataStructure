
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class QueueArray {
    static class QuArray{
        // You are not to delete any data member. You may add additional data member as you see fit.
        public Long[] data; // Array that holds the data in the queue
        public int size; // the current number of elements in the queue;
        public int Front; // the front of the queue
        public int End; // the end of the queue
        
        /*
        You are to implement.
        QuArray(int max) := This is a constructor for QuArray class. "max" is the maximum number of elements that
                        can be in the queue;
        boolean enqueue(long value) := this function enqueues "value" into the queue if and only if
                                    there is a space left on data array. If the enqueue successful,
                                    return true. Otherwise, return false.
        Long dequeue() := this function dequeues an object from the queue, and return that object. 
                        But, if the queue is empty, return null.
        Long peek() := this function peek an object from the front of the queue, and return that object
                        without removing it. But, if the queue is empty, return null.
        */
        public QuArray(int max){
        	data = new Long[max];
        	size = 0;
        	Front = data.length-1;
        	End = data.length-1;
        }
        
        public boolean isEmpty(){
        	return size==0;
        }
        
        public boolean isFull(){
        	return size==data.length;
        }
        
        
        public boolean enqueue(long value){
        	if(isFull())
        		return false;
        	else{
        		data[End] = value;
        		End--;
        		size++;
        		if(End<0) End = data.length -1;
        		return true;
        	}
        	
        }
        
        public Long dequeue(){
        	if(isEmpty())
        		return null;
        	else{
        		Long temp = new Long(data[Front]);
        		/*for(int i=Front; i>End+1; i--){
        			data[i] = data[i-1];
        		}*/
        		data[Front] = null;
        		Front--;
        		size--;
        		if(Front<0) Front = data.length -1;
        		return temp;
        	}
        }
        
        public Long peek(){
        	if(isEmpty())
        		return null;
        	else{
        		Long temp = new Long(data[Front]);
        		return temp;
        	}
        }
        
        /*
        You are not to edit code below this point. If you find any bug, please report to me.
        * Otherwise, we will not be able to test your program and you will get 0 point
        */
        
        public String toString(){
            int i,j;
            String out = "";
            if(size > 0){
                for(j=0,i=Front; j < size; j++){
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
    int ndex, cNum, rNum, ldex, cdex;
    long rdex;
    Long fin;
    QuArray test_array;
    boolean test_bool;
    for(ndex = 0; ndex < numTestCase; ndex++){
        line = br.readLine();
        tokens = new StringTokenizer(line, " \t\r\n");
        rNum = Integer.parseInt(tokens.nextToken());
        cNum = Integer.parseInt(tokens.nextToken());
        test_array = new QuArray(rNum);
        for(cdex = 0; cdex < cNum; cdex++){
            line = br.readLine();
            tokens = new StringTokenizer(line, " \t\r\n");
            ldex = Integer.parseInt(tokens.nextToken());                        
            switch(ldex){
                case 0: rdex = Long.parseLong(tokens.nextToken()); // enqueue
                    test_bool = test_array.enqueue(rdex);
                    if(test_bool){
                        System.out.println("enqueue " + rdex + " success");
                    }else{
                        System.out.println("enqueue " + rdex + " fail");
                    }
                    break;
                case 1: fin = test_array.dequeue(); // dequeue
                    if(fin == null){
                        System.out.println("the queue is empty");
                    }else{
                        System.out.println("get " + fin.toString() + " from dequeue");
                    }
                    break;
                case 2: fin = test_array.peek(); // peek
                    if(fin == null){
                        System.out.println("the queue is empty");
                    }else{
                        System.out.println(fin.toString() + " is at the front of queue");
                    }
                    break;
                default:    // print
                    System.out.println(test_array.toString());
            }
        }        
    }    
  }
    
}
