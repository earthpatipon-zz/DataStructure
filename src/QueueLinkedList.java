
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
public class QueueLinkedList {
    static class FNode {
        public Float input;
        public FNode next;
        public FNode prev;
        
        public FNode(float in){
            input = new Float(in);
            next = null;
            prev = null;
        }
        
        public FNode(FNode in){
            input = in.input;
            if(in.next != null){
                next = in.next;
            }else{
                next = null;
            }
        }
        
        public FNode(){
            input = null;
            next = null;
            prev = null;
        }
        
        public String toString(){
            return input.toString();
        }
    }
    
    static class QuLinkedList{
        /* you must not edit the class members below
         *  However, you may add as you see fits
         */
        
        public FNode First; // pointer to the front object on the list
        public FNode Last; // pointer to the last object on the list
        public int MAX_SIZE;
        public int size;

        /*
        You are to implement.
        QuLinkedList(int max) := This is a constructor for QuArray class. "s" is the maximum number of elements that
                        can be in the queue;
        boolean enqueue(float value) := this function enqueues "value" into the queue if and only if
                                    there is a space left on queue. If the enqueue successful,
                                    return true. Otherwise, return false.
        Float dequeue() := this function dequeues an object from the queue, and return that object. 
                        But, if the queue is empty, return null.
        Float peek() := this function peek an object from the front of the queue, and return that object
                        without removing it. But, if the queue is empty, return null.
        */
        
        public QuLinkedList(int max){
        	First = null;
        	Last = null;
        	size = 0;
        	MAX_SIZE = max;
        }
        
        public boolean isEmpty(){
        	return size==0;
        }
        
        public boolean isFull(){
        	return size==MAX_SIZE;
        }
        
        
        public boolean enqueue(float value){
        	FNode node = new FNode(value);
        	if(isEmpty()){
        		First = node;
        		Last = node;
        		size++;
        		return true;
        	}
        	else if(isFull())
        		return false;
        	else{
        		node.prev = Last;
        		Last.next = node;
        		Last = node;
        		size++;
        		return true;
        	}
        }
        
        
        public Float dequeue(){
        	if(isEmpty())
        		return null;
        	else{
        		Float temp = new Float(First.input);
        		if(First.next == null){
        			First = null;
        			Last = null;
        			size = 0;
        		}
        		else{
        		First.next.prev = null;
        		First = First.next;
        		size--;
        		}
        		return temp;
        	}
        }
        
        public Float peek(){
        	if(this.isEmpty())
        		return null;
        	else{
        		Float temp = new Float(First.input);
        		return temp;
        	}
        }
        
    /* You are not to edit code below this point. If you find any bug, please report to me.
     * Otherwise, we will not be able to test your program and you will get 0 point
     */        
        public String toString(){
            int i = 0;
            String out = "";
            FNode curr = First;
            while(curr != null){
                out = out + "(" + i + "," + curr.toString() + ") ";
                curr = curr.next;
                i++;
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
    float rdex;
    Float fin;
    QuLinkedList test_list;
    boolean test_bool;
    for(ndex = 0; ndex < numTestCase; ndex++){
        line = br.readLine();
        tokens = new StringTokenizer(line, " \t\r\n");
        rNum = Integer.parseInt(tokens.nextToken());
        cNum = Integer.parseInt(tokens.nextToken());
        test_list = new QuLinkedList(rNum);
        for(cdex = 0; cdex < cNum; cdex++){
            line = br.readLine();
            tokens = new StringTokenizer(line, " \t\r\n");
            ldex = Integer.parseInt(tokens.nextToken());                        
            switch(ldex){
                case 0: rdex = Float.parseFloat(tokens.nextToken()); // enqueue
                    test_bool = test_list.enqueue(rdex);
                    if(test_bool){
                        System.out.println("enqueue " + rdex + " success");
                    }else{
                        System.out.println("enqueue " + rdex + " fail");
                    }
                    break;
                case 1: fin = test_list.dequeue(); // dequeue
                    if(fin == null){
                        System.out.println("the queue is empty");
                    }else{
                        System.out.println("get " + fin.toString() + " from dequeue");
                    }
                    break;
                case 2: fin = test_list.peek(); // peek
                    if(fin == null){
                        System.out.println("the queue is empty");
                    }else{
                        System.out.println(fin.toString() + " is at the front of queue");
                    }
                    break;
                default: // print
                    System.out.println(test_list.toString());
            }
        }        
    }    
  }    
}
