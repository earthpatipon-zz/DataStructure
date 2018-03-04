
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
public class DELinkedList {
        /* you must not delete the functions nor the members of the class iNode below
         *  However, you may add class members and constructors as you see fits
         */
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
    
    static class DLinkedList{
        /* you must not edit the class members below
         *  However, you may add as you see fits
         */
        
        public DNode First; // pointer to the front of the list
        public DNode Last; // pointer to the end of the list
        
        /*
        You should complete the following function
        DELinkedList() == This is the constructor for DELinkedList class;
        addFirst(double value) == This function add "value" to the first object on the list
        removeFirst() == This function remove the first object in the list;
                            if the list is empty return null;
        addLast(double value) == This function add "value" to the last object on the list
        removeLast() == This function remove the last object in the list;
                            if the list is empty return null;
        findObject(double value) == return the DNode object that contains value. if there is none
                            at that position return null
        removeObject(double value) == remove and return the double object that contains value, if object is not found
        	return null
        
        */
        public DLinkedList(){
        	First = null;
        	Last = null;
        }
        
        public void addFirst(double value){
        	DNode node = new DNode(value);
        	if(First!=null){
        		node.next = First;
        		First = node;
        	}
        	else{
        		First = node;
        		Last = node;
        	}
        }
        
        public void addLast(double value){
        	DNode node = new DNode(value);
        	if(First!=null){
        		Last.next = node;
        		Last = node;
        	}
        	else{
        		First = node;
        		Last = node;
        	}
        }
        
        
        public Double removeFirst(){
        	if(First!=null){
        		Double temp = new Double(First.input);
        		if(First.next!=null)
        			First = First.next;
        		else{
        			First = null;
        			Last = null;
        		}
        		return temp;
        	}
        	else
        		return null;
        }
        
        public Double removeLast(){
        	if(Last!=null){
        		Double temp = new Double(Last.input);
        		DNode curr;
        		for(curr=First;curr!=null;curr=curr.next){
        			if(curr.next==Last){
        				curr.next = null;
        				Last = curr;
        			}
        		}
        		return temp;
        	}
        	else
        		return null;
        }
        
        public Double removeObject(double value){
        	DNode tar = findObject(value);
        	DNode curr;
        	Double temp = new Double(value);
        	if(tar == First)
        		return this.removeFirst();
        	else if(tar == Last){
        		return this.removeLast();
        	}
        	else if(First!=null){
				for(curr=First; curr!=null; curr=curr.next){
					if(curr.next==tar){
						if(tar.next == null)
							curr.next = null;
						else
							curr.next = tar.next;
						return temp;
					}
				}
				return null;
        	}
        	return temp;
        }
        
        public DNode findObject(double value){
        	DNode curr;
        	if(First!=null){
				for(curr=First; curr!=null; curr=curr.next){
					if(curr.input==value)
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
            DNode curr = First;
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
    double fin;
    DLinkedList test_list;
    DNode dN;
    Double dV;
    for(ndex = 0; ndex < numTestCase; ndex++){
        line = br.readLine();
        tokens = new StringTokenizer(line, " \t\r\n");
        cNum = Integer.parseInt(tokens.nextToken());
        test_list = new DLinkedList();
        for(cdex = 0; cdex < cNum; cdex++){
            line = br.readLine();
            tokens = new StringTokenizer(line, " \t\r\n");
            ldex = Integer.parseInt(tokens.nextToken());                        
            switch(ldex){
                case 0: fin = Double.parseDouble(tokens.nextToken());
                    test_list.addFirst(fin);
                    break;
                case 1: dV = test_list.removeFirst();
                    if(dV != null){
                        fin = dV.doubleValue();
                        System.out.println("remove from first is " + fin);
                    }else{
                        System.out.println("the list is empty");
                    }
                    break;
                case 2: fin = Double.parseDouble(tokens.nextToken());
                    DNode tF = test_list.findObject(fin);
                    if(tF == null){
                        System.out.println(fin + " is not on the list");
                    }else{
                        System.out.println(tF.toString() + " is found on the list");
                    }
                    break;
                case 3: fin = Double.parseDouble(tokens.nextToken());
                    test_list.addLast(fin);
                    break;
                case 4: dV = test_list.removeLast();
                    if(dV == null){
                        System.out.println("the list is empty");
                    }else{
                        fin = dV.doubleValue();
                        System.out.println("remove from last is " + fin);
                    }
                    break;
                case 5: fin = Double.parseDouble(tokens.nextToken());
                    Double tFin = test_list.removeObject(fin);
                    if(tFin == null){
                        System.out.println(fin + " is not found on the list");
                    }else{
                        System.out.println(tFin.doubleValue() + " is removed from the list");                    
                    }
                    break;
                default:
                    System.out.println(test_list.toString());
            }
        }        
    }
  }            
}
