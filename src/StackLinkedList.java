
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
public class StackLinkedList {
    
        static class DNode {
        public Double input;
        public DNode next;
        
        public DNode(double in){
            input = new Double(in);
            next = null;
        }
        
        public DNode(DNode in){
            input = in.input;
            if(in.next != null){
                next = in.next;
            }else{
                next = null;
            }
        }
        
        public DNode(){
            input = null;
            next = null;
        }
        
        public String toString(){
            return input.toString();
        }
    }
        
    static class StLinkedList{
        public DNode First; // Top of the stack
        public int size; // current numbers of elements in the stack
        public int MAX_Stack; // maximum size of stack
        
        /*
        You are to implement.
        StLinkedList(int max) := This is a constructor for StLinkedList class. "max" is the maximum number of elements that
                        can be in the stack;
        boolean push(int value) := this function pushes "value" on the stack if and only if
                                    there is a space left on the stack. If the push successful,
                                    return true. Otherwise, return false.
        Integer pop() := this function pops an object from the stack, and return that object. 
                        But, if the stack is empty, return null.
        Integer peek() := this function peek an object from the top of the stack, and return that object
                        without removing it. But, if the stack is empty, return null.
        */

        public StLinkedList(int max){
        	First = null;
        	size = 0;
        	MAX_Stack = max;
        }
        
        public boolean isEmpty(){
        	return size==0;
        }
        
        public boolean isFull(){
        	return size==MAX_Stack;
        }
        
        public boolean push(double value){
        	DNode node = new DNode(value);
        	if(isEmpty()){
        		First = node;
        		size++;
        		return true;
        	}
        	else if(isFull())
        		return false;
        	else{
        		node.next = First;
        		First = node;
        		size++;
        		return true;
        	}
        }
        
        public Double pop(){
        	if(isEmpty())
        		return null;
        	else{
        		Double temp = new Double(First.input);
        		First = First.next;
        		size--;
        		return temp;
        	}
        }
        
        public Double peek(){
        	if(isEmpty())
        		return null;
        	else{
        		Double temp = new Double(First.input);
        		return temp;
        	}
        }
        
        /*
        You are not to edit code below this point. If you find any bug, please report to me.
        * Otherwise, we will not be able to test your program and you will get 0 point
        */
        public String toString(){
            int i = 0;
            String out = "";
            DNode curr;
            for(curr = First; curr != null; curr = curr.next, i++){                    
                out = out + "(" + i + "," + curr.toString() + ") ";
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
    Double fin;
    double dFin;
    StLinkedList test_list;
    boolean test_bool;
    for(ndex = 0; ndex < numTestCase; ndex++){
        line = br.readLine();
        tokens = new StringTokenizer(line, " \t\r\n");
        rNum = Integer.parseInt(tokens.nextToken());
        cNum = Integer.parseInt(tokens.nextToken());
        test_list = new StLinkedList(rNum);
        for(cdex = 0; cdex < cNum; cdex++){
            line = br.readLine();
            tokens = new StringTokenizer(line, " \t\r\n");
            ldex = Integer.parseInt(tokens.nextToken());                        
            switch(ldex){
                case 0: dFin = Double.parseDouble(tokens.nextToken()); // push
                    test_bool = test_list.push(dFin);
                    if(test_bool){
                        System.out.println("push " + dFin + " success");
                    }else{
                        System.out.println("push " + dFin + " fail");
                    }
                    break;
                case 1: fin = test_list.pop(); // pop
                    if(fin == null){
                        System.out.println("the stack is empty");
                    }else{
                        System.out.println("pop from first is " + fin.toString());
                    }
                    break;
                case 2: fin = test_list.peek(); // peek
                    if(fin == null){
                        System.out.println("the stack is empty");
                    }else{
                        System.out.println(fin.toString() + " is on top of the stack");
                    }
                    break;
                default: // print
                    System.out.println(test_list.toString());
            }
        }        
    }    
  }
    
}
