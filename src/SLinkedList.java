
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
public class SLinkedList {
        /* you must not delete the functions nor the members of the class iNode below
         *  However, you may add class members and constructors as you see fits
         */
    static class INode {
        public Integer input;
        public INode next;
        
        public INode(int in){
            input = new Integer(in);
            next = null;
        }
        
        public INode(INode in){
            input = in.input;
            if(in.next != null){
                next = in.next;
            }else{
                next = null;
            }
        }
        
        public INode(){
            input = null;
            next = null;
        }
        
        public String toString(){
            return input.toString();
        }
    }
    
    static class ILinkedList{
        /* you must not edit the class members below
         *  However, you may add as you see fits
         */
        
        public INode First; // pointer to the front of the list
        
        /*
        You should complete the following function
        ILinkedList() == This is the constructor for ILinkedList class;
        addFirst(int tar) == This function add "tar" to the first object on the list
        removeFirst() == This function remove the first object in the list;
                            if the list is empty return null;
        findObject(int tar) == return the object at the tar index. if there is none
                            at that position return null
        
        */
        public ILinkedList(){
        	First = null;
        }
        
        public void addFirst(int value){
        	INode node = new INode(value);
        	if(First!=null){
        		node.next = First;
        		First = node;
        	}
        	else
        		First = node;
        }
        
        public Integer removeFirst(){
        	if(First!=null){
        		Integer temp = First.input;
        		First = First.next;
        		return temp;
        	}
        	else
        		return null;
        }
        
        public INode findObject(int value){
        	INode curr;
        	for(curr=First; curr!=null; curr=curr.next){
        		if(curr.input == value){
        			return curr;
        		}
        	}
        	return null;
        }
        
    /* You must not edit the toString function below. 
     * Otherwise, we will not be able to test your program and you will get 0 point
     */        
        public String toString(){
            int i = 0;
            String out = "";
            INode curr;
            for(curr = First; curr != null; curr = curr.next, i++){                    
                out = out + "(" + i + "," + curr.toString() + ") ";
            }           
            return out;
        }
        
    }
    
    /*You must not edit the main function below. 
     * Otherwise, we will not be able to test your program and you will get 0 point
     */
  public static void main (String arg[])throws IOException {
    BufferedReader br = new BufferedReader (
			   new InputStreamReader(System.in));
    String line = br.readLine();
    StringTokenizer tokens;
    int numTestCase = Integer.parseInt(line);
    int ndex, cNum, rNum, ldex, rdex, cdex;
    ILinkedList test_list;
    Integer rOut;
    INode nOut;
    for(ndex = 0; ndex < numTestCase; ndex++){
        line = br.readLine();
        tokens = new StringTokenizer(line, " \t\r\n");
        cNum = Integer.parseInt(tokens.nextToken());
        test_list = new ILinkedList();
        for(cdex = 0; cdex < cNum; cdex++){
            line = br.readLine();
            tokens = new StringTokenizer(line, " \t\r\n");
            ldex = Integer.parseInt(tokens.nextToken());                        
            switch(ldex){
                case 0: rdex = Integer.parseInt(tokens.nextToken());
                    test_list.addFirst(rdex);
                    break;
                case 1: rOut = test_list.removeFirst();
                    if(rOut != null){
                        rdex = rOut.intValue();
                        System.out.println("remove from first is " + rdex);
                    }else{
                        System.out.println("the list is empty");
                    }
                    break;
                case 2: rdex = Integer.parseInt(tokens.nextToken());
                    nOut = test_list.findObject(rdex);
                    if(nOut != null)
                        System.out.println(rdex + " is found ");
                    else
                        System.out.println(rdex + " is not found");
                    break;
                default:
                    System.out.println(test_list.toString());
            }
        }        
    }
  }        
}
