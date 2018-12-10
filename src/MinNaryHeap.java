/**
 * 
 */

/**
 * @author alinasaiakhova
 *
 */

import java.util.Comparator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Scanner;


public class MinNaryHeap <E extends Comparable <E>> 
 {
	private  ArrayList <E> heap_data; // ArrayList storing heap data
    private int heap_size; // heap size
    private int num_children; //number of children per node
   
    public MinNaryHeap() {
    	
    	
    }
	//creates empty heap
    public MinNaryHeap( int k){
    	heap_data= new ArrayList <E>(); 
    	num_children=k; 
    	heap_size=0;
    	
    }
  //creates empty heap
    public MinNaryHeap(int num_elements, int k){
    	heap_data= new ArrayList <E>(); 
    	num_children=k; 
    	heap_size=0;
    	
    }
    
	//creates heap and populates it with user data
    public MinNaryHeap(ArrayList <E> array_list, int k){
    	heap_size=0;
    	heap_data= new  ArrayList<E>();
    	num_children=k;
    	//insert each element at the bottom of the heap,  then heapifyUp
        for (int i = 0; i < array_list.size(); i++) 
        	insertIntoHeap(array_list.get(i));
    	
    }
    
  public void insertIntoHeap (E element) {
	 
    heap_data.add( element); //insert at the bottom for now
  	heap_size++; 
  	heapifyUp(heap_size-1); //then heapifyUp until heap property is met
	  
  }
  
  public  void traverseHeap(int index) {
	  
	  int cur_level=0;
	  int prev_max=0;
	  
	  
	  while(index<heap_size) {
	  
	  int cur_max=prev_max+(int)Math.pow(num_children,cur_level); //max index at current level is a function of num_children^cur_level
	  
	  if(index<cur_max) { //if index is less than max index at current level, print 
		  
	  System.out.print(heap_data.get(index)+ " ");
	  }
	  else { //otherwise, increment current level and current max at current level
		  cur_level++;
		  prev_max=cur_max;
		  cur_max=prev_max+(int)Math.pow(num_children,cur_level);
		  System.out.println("");
		  System.out.print(heap_data.get(index)+ " ");

		  
		  
		  
	  }
	  index++;
	  }
	System.out.println("");
  }
  
  public void deleteFromHeap (int child_index) {
	  
	  if(heap_size ==0)           
		  throw new NoSuchElementException("Cannot delete from index "+child_index+" because the heap is empty");
	  if (child_index <0 || child_index > heap_size)
		  throw new NoSuchElementException("Child index "+child_index+" out of range");

	  //set element at index to last element in heap
	  heap_data.set(child_index, heap_data.get(heap_size-1));
	  heap_size--;
	  heap_data.remove(heap_size-1);

	  heapifyDown(child_index);
	  
  }
  
  public void heapifyDown (int cur_index) {
	  
	  if(cur_index*num_children+1  < heap_size) { //loop until we get to the 2nd to last level
			//get min value child of node at cur_index
			  int minChild_index=getMinChildIndex(cur_index);
			
			if(compare(cur_index, minChild_index) > 0) {//if min child is smaller, swap child and parent
				swapElements(cur_index,minChild_index);
			}else {
				//do nothing
				}
			cur_index=minChild_index; // after exchanging current node with its smallest child, we place it where the smallest child used to be and heapifyDown from there
	       heapifyDown(cur_index);
	  }
	  
  }
  
  
  public int getMinChildIndex(int parent_index) {
	  
	  //current minimum node is the left most node
	  int cur_min_index=parent_index*num_children+1;
//go through the rest of the child nodes to determine the actual minimum node
	  for(int i=num_children*parent_index+2;i<=num_children*parent_index+num_children; i++ ) 
		  if (i < (heap_size-1)) 
		  if (compare(i,cur_min_index)<0) 
		      cur_min_index=i;
	  
	  return cur_min_index;
	  
	  
  }
  
  public void heapifyUp (int child_index) {
	  
	  //compare child and parent node, swap if necessary; child becomes new parent if swap occurred; 
	  if(child_index >0 && compare(child_index,  getParentIndex(child_index))<0){
	  
		  int parent_index=getParentIndex(child_index);
		  swapElements(child_index,parent_index); 
		  child_index=parent_index;
		  heapifyUp(child_index);
		  
	  }
  }
  
  public void swapElements(int index1, int index2) {
	  
	E nodeAtindex1=heap_data.get(index1);
	heap_data.set(index1, heap_data.get(index2));
	heap_data.set(index2, nodeAtindex1);
	  
	  
  }
  
  public int getParentIndex(int child_index) {
	  
	  return (child_index-1)/num_children;
	  
  }
  
	  
  public int compare(int indexOfelement1, int indexOfelement2) 
  { 
	  E element1=heap_data.get(indexOfelement1);
	  E element2=heap_data.get(indexOfelement2);
      return ((Comparable<E>) element1).compareTo(element2);
  } 
	
  public boolean isEmpty() {
	  
	  if (heap_size ==0) return true;
	  else return false;
  }
public E extractMin() {
	  
	  if(heap_size ==0)           
		  throw new NoSuchElementException("Cannot extract min because the heap is empty");
	//extract the top element, then heapifyDown
	  E extracted=heap_data.get(0);
	  heap_data.set(0, heap_data.get(heap_size-1));
	  heap_data.remove(heap_size-1);
	  heap_size--;
	  heapifyDown(0);
	  return extracted;
	 
	  
  }
	
	public static void main(String[] args) {
		
		
		Scanner scanner = new Scanner(System.in);
		int numChildren=2;
		System.out.println("Min N-ary Heap Test\n");
        System.out.println("Enter max number of children per node:");
        
        try {
            numChildren =scanner.nextInt();
             //create empty heap with user-provided number of children

            }catch (Exception e)
            {
                System.out.println(e.getMessage() );
            }

         MinNaryHeap   min_heap = new MinNaryHeap(numChildren );
		
		while(true) {
		
        
        
		//print options:
        System.out.println("Min N-ary Heap operations :");
        System.out.println("1. Populate heap with comma-delimited list of values");
        System.out.println("2. Delete element at index");
        System.out.println("3. Insert new element");
        System.out.println("4. Print heap");
        System.out.println("5. Exit");
        System.out.println ("Pick an operation:");
        
       int option=scanner.nextInt();
		
        
        switch (option)
        {
        case 1 : 
            try
            {
            
                System.out.println("Enter comma delimited list of numbers or characters:");
                
            String  elements_to_insert=scanner.next();
            String [] elements_to_insert_arr=elements_to_insert.split(",");
            boolean isNumeric=true;
            
            
            //if isNumeric stays true, then entire array is numeric
            for (int i=0; i<elements_to_insert_arr.length; i++) {	
            	try {
            		
            		double elem=Double.parseDouble(elements_to_insert_arr[i]);
            		
            	}catch (Exception e) {
            		
            		isNumeric=false;
            	}
            	
            }
            
            for (int i=0; i<elements_to_insert_arr.length; i++) {	
            	if (isNumeric==true)
            		//if numeric, insert into heap as double
            	min_heap.insertIntoHeap(Double.parseDouble(elements_to_insert_arr[i]));
            	else
            		//otherwise, insert as string and sort lexigraphically
                	min_heap.insertIntoHeap(elements_to_insert_arr[i]);

            	
            }
            break;
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage() );
                System.exit(0);
            }
        case 2:
        	try {
            System.out.println("Enter index of element to delete:");
         int idx=scanner.nextInt();
         min_heap.deleteFromHeap(idx);
         
         break;
        	}catch(Exception e) {
        		
        		 System.out.println(e.getMessage() );
        		 System.exit(0);
        		
        	}
        case 3:
        	try {
                System.out.println("Enter element to insert:");
             min_heap.insertIntoHeap(scanner.nextLine());
             break;
             
            	}catch(Exception e) {
            		
            		 System.out.println(e.getMessage() );
            		 System.exit(0);
            		
            	}
        case 4:
        	try {
                min_heap.traverseHeap(0);
                break;
             
            	}catch(Exception e) {
            		
            		 System.out.println(e.getMessage() );
            		 System.exit(0);
            		
            	}
      
        case 5:
        	try {
               System.exit(0);
             
            	}catch(Exception e) {
            		
            		 System.out.println(e.getMessage() );
                     break;
            		
            	}
        }
		

     // MinNaryHeap heap = new MinNaryHeap( 2 );
        //heap.insertIntoHeap(5.0);
       // heap.insertIntoHeap(2.0);
     //  heap.insertIntoHeap(1.0);


        //heap.traverseHeap(0);
	     //  heap.extractMin();
	      // heap.traverseHeap(0);
	     //  heap.insertIntoHeap(1.0);
	      // heap.traverseHeap(0);

	      // heap.traverseHeap(1);
//System.out.print(heap.heap_size+","+my_list.size());
//	}
	//*/
	}}
	}


