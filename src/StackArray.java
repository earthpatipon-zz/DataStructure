
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
public class StackArray {
    static class StArray{
        public Integer[] data; // Array that hold data in the stack
        public int size; // current number of elements in the stack
        
        /*
        You are to implement.
        StArray(int max) := This is a constructor for StArray class. "max" is the maximum number of elements that
                        can be in the stack;
        boolean push(int value) := this function pushes "value" on the stack if and only if
                                    there is a space left on data array. If the push successful,
                                    return true. Otherwise, return false.
        Integer pop() := this function pops an object from the stack, and return that object. 
                        But, if the stack is empty, return null.
        Integer peek() := this function peek an object from the top of the stack, and return that object
                        without removing it. But, if the stack is empty, return null.
        */
        
        public StArray(int max){
        	data = new Integer[max];
        	size = 0;
        }
        
        public boolean isEmpty(){
        	return size==0;
        }
        
        public boolean isFull(){
        	return size == data.length;
        }
        
        public boolean push(int value){
        	if(isEmpty()){
        		data[0] = value;
        		size++;
        		return true;
        	}
        	else if(isFull())
        		return false;
        	else{
        		data[size] = value;
        		size++;
        		return true;
        	}
        }
        
        public Integer pop(){
        	if(isEmpty())
        		return null;
        	else{
        		Integer temp = new Integer(data[size-1]);
        		data[size-1] = null;
        		size--;
        		return temp;
        	}
        }
        
        public Integer peek(){
        	if(isEmpty())
        		return null;
        	else{
        		Integer temp = new Integer(data[size-1]);
        		return temp;
        	}
        }
        
        /*
        You are not to edit code below this point. If you find any bug, please report to me.
        * Otherwise, we will not be able to test your program and you will get 0 point
        */
        
        public String toString(){
            String out = "";
            int i;
            for(i = 0; i < size; i++){
                out = out+" "+data[i].toString();
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
    StArray test_array;
    boolean test_bool;
    for(ndex = 0; ndex < numTestCase; ndex++){
        line = br.readLine();
        tokens = new StringTokenizer(line, " \t\r\n");
        rNum = Integer.parseInt(tokens.nextToken());
        cNum = Integer.parseInt(tokens.nextToken());
        test_array = new StArray(rNum);
        for(cdex = 0; cdex < cNum; cdex++){
            line = br.readLine();
            tokens = new StringTokenizer(line, " \t\r\n");
            ldex = Integer.parseInt(tokens.nextToken());                        
            switch(ldex){
                case 0: rdex = Integer.parseInt(tokens.nextToken()); // push
                    test_bool = test_array.push(rdex);
                    if(test_bool){
                        System.out.println("push " + rdex + " success");
                    }else{
                        System.out.println("push " + rdex + " fail");
                    }
                    break;
                case 1: fin = test_array.pop(); // pop
                    if(fin == null){
                        System.out.println("the stack is empty");
                    }else{
                        System.out.println("pop from first is " + fin.toString());
                    }
                    break;
                case 2: fin = test_array.peek(); // peek
                    if(fin == null){
                        System.out.println("the stack is empty");
                    }else{
                        System.out.println(fin.toString() + " is on top of the stack");
                    }
                    break;
                default:    // print
                    System.out.println(test_array.toString());
            }
        }        
    }    
  }
    
}
