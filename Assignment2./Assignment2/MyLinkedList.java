public class MyLinkedList implements ListInterface {
	protected  int size = 0; 
	protected  int index = 0; 
	protected Node head; 
	protected Object stuff;  

	protected class Node{
  protected  Object stuff; 
		protected Node next;
		protected Node (Object stuff){
			this.stuff = stuff; 
			next = null;
		}
	}
public boolean isEmpty(){
return (size == 0);
}
public int size (){
	return size; 
}
public void add(int index, Object value)throws ListIndexOutOfBoundsException {
if(index>=0&&index<size+1) {   
        if(head == null && index == 0) {
              head = new Node(value);
        }
        else if (head != null && index >= 0)  {
				Node current = head; 
				while(current.next != null){
					current = current.next;
				}
				current.next = new Node(value);	
			}
         else if (head != null && index == 0) {
            Node headNode = new Node(value);
            headNode.next = head;
            head = headNode;
        }
          size++;
	}
	}
	public void remove (int index)throws ListIndexOutOfBoundsException {
		Node current = head; 
		if (index>=0&&index<size){
			if (index == 0){
				head = head.next;
				size--;
			}
			else if(index>0) {
				for(int i=0; i<index-1; i++); 
			    current = current.next; 
			}
			current.next = current.next.next; 
			size--;
		}
		else {
			throw new ListIndexOutOfBoundsException("Error: Remove"); 
		}
     }
 public void removeAll(){ 
 	head = null; 
 	size = 0; 
 }
     public Object get(int index){
      if(index>=0&&index<=size) {
        Node current = head;
        int count = 0;
 
        while (count < size) {
            if(count == index) {
                return current.stuff;
            }
            count++;
            current = current.next;
        }
        }
        return -1;
    }
public int find(Object o){
	  int index = 0;
    Node find = head;
    while (find != null) {
        if (find.stuff==o) {
            return index;
        }
        index++;
        find = find.next;
    }
    return -1;
}
  @Override
    public String toString() {
    	Node current = head;
    	String value ="";
    	int i = 0;
    	while(current.next != null){
    		if (i<size){
    		value = value+" "+current.stuff;
    		current=current.next;
    		i++;
    	}
    }
       
       return value ;
    }
    /* 
 @Override
    public String toString() {
     Node current = head;
      String s = "";
       for (int i=0; i<size;i++){
      	  s = s+current.stuff; 
      	    current = current.next; 
      }
         return s.toString(); 
          

    }


    */

}
