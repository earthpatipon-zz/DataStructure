
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
public class DLinkedList {
        /* you must not delete the functions nor the members of the class iNode below
         *  However, you may add class members and constructors as you see fits
         */
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
    
    static class FLinkedList{
        /* you must not edit the class members below
         *  However, you may add as you see fits
         */
        
        public FNode First; // pointer to the front object on the list
        public FNode Last; // pointer to the last object on the list
        
        /*
        You should complete the following function
        FLinkedList() == This is the constructor for FLinkedList class;
        addFirst(float value) == This function add "value" to the first object on the list
        removeFirst() == This function remove the first object in the list;
                            if the list is empty return null;
        addLast(float value) == This function add "value" to the last object on the list
        removeLast() == This function remove the last object in the list;
                            if the list is empty return null;
        findObject(float value) == return the FNode object that contains value. if there is none
                            at that position return null
        removeObject(float value) == remove and return the FNode object that contains value, 
        	if the object is not on the list return null
        
        */
        public FLinkedList(){
        	First = null;
        	Last = null;
        }
        
        public void addFirst(float value){
        	FNode node = new FNode(value);
        	if(First!=null){
        		First.prev = node;
        		node.next = First;
        		First = node;
        	}
        	else{
        		First = node;
        		Last = node;
        	}
        }
        
        public void addLast(float value){
        	FNode node = new FNode(value);
        	if(First!=null){
        		Last.next = node;
        		node.prev = Last;
        		Last = node;
        	}
        	else{
        		First = node;
        		Last= node;
        	}
        }
        
        
        public Float removeFirst(){
        	if(First!=null){
        		Float temp = new Float(First.input);
        		if(First.next!= null){
        			First.next.prev = null;
        			First = First.next;
        		}
        		else{
        			First = null;
        			Last = null;
        		}
        		return temp;
        	}
        	else
        		return null;
        }
        
        public Float removeLast(){
        	if(First!=null){
        		Float temp = new Float(Last.input);
        		if(Last.prev!= null){
        			Last.prev.next = null;
            		Last = Last.prev;
        		}
        		else{
        			First = null;
        			Last = null;
        		}
        		return temp;
        	}
        	else
        		return null;
        }
        
        public Float removeObject(float value){
        	FNode tar = findObject(value);
        	Float temp = new Float(value);
        	if(tar==null)
        		return null;
        	else if(tar==First){
        		return this.removeFirst();
        	}
        	else if(tar==Last){
        		return this.removeLast();
        	}
        	else{
        		tar.next.prev = tar.prev;
            	tar.prev.next = tar.next;
        	}
        	return temp;
        }
        
        
        public FNode findObject(float tar){
        	FNode curr;
        	if(First!=null){
        		for(curr=First;curr!=null;curr=curr.next){
        			if(curr.input==tar)
        				return curr;
        		}
        		return null;
        	}
        	else
        		return null;
        }
        
    /*You must not edit the toString function below. 
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
    float fin;
    FLinkedList test_list;
    FNode cN;
    Float tF;
    for(ndex = 0; ndex < numTestCase; ndex++){
        line = br.readLine();
        tokens = new StringTokenizer(line, " \t\r\n");
        cNum = Integer.parseInt(tokens.nextToken());
        test_list = new FLinkedList();
        for(cdex = 0; cdex < cNum; cdex++){
            line = br.readLine();
            tokens = new StringTokenizer(line, " \t\r\n");
            ldex = Integer.parseInt(tokens.nextToken());                        
            switch(ldex){
                case 0: fin = Float.parseFloat(tokens.nextToken());
                    test_list.addFirst(fin);
                    break;
                case 1: tF = test_list.removeFirst();
                    if(tF == null){
                        System.out.println("the list is empty");
                    }else{
                        fin = tF.floatValue();
                        System.out.println("remove from first is " + fin);
                    }
                    break;
                case 2: fin = Float.parseFloat(tokens.nextToken());
                    cN = test_list.findObject(fin);                  
                    if(cN != null){
                        System.out.println("we found " + cN.toString());
                    }else{
                        System.out.println("we cannot find " + fin);
                    }
                    break;
                case 3: fin = Float.parseFloat(tokens.nextToken());
                    test_list.addLast(fin);
                    break;
                case 4: 
                    tF = test_list.removeLast();
                    if(tF == null){
                        System.out.println("the list is empty");
                    }else{
                        System.out.println("removed from last is " + tF.toString());
                    }
                    break;
                case 5: fin = Float.parseFloat(tokens.nextToken());
                    tF = test_list.removeObject(fin);
                    if(tF == null){
                        System.out.println(fin + " is not on the list for removal");                        
                    }else{
                        System.out.println(tF.floatValue() + " is removed");
                    }
                    break;
                default:
                    System.out.println(test_list.toString());
            }
        }        
    }
  }        
}