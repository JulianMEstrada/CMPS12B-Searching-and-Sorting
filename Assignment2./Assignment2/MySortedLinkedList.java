public class MySortedLinkedList extends MyLinkedList {
	/* TODO 
	   define the method
	   public void add(Comparable c)
	   that, given a Comparable (an interface type for all Object subclasses that define a compareTo() method), adds it to the 
	   list in sorted order.
	*/
	public void add(Comparable c){
        Node newNode = new Node(c);
      if(head == null) {
            
            head = newNode;
            size++;
        } else if(head != null && (c).compareTo((String)head.stuff) < 0) {
            newNode.next = head;
            head = newNode;
            size++;
        } else {
            Node lastN = head;
            Node xNode = head.next;
            while(xNode != null) {
                if((c).compareTo((String)xNode.stuff) < 0) {
                    break;
                }
                lastN = xNode;
                xNode = xNode.next;
            }
            newNode.next = lastN.next;
            lastN.next = newNode;
            size++;
        }
	}
	/* TODO
	   override the method
	   void add(int index, Object o)
	   so that it throws an UnsupportedOperationException with the message "Do not call add(int, Object) on MySortedLinkedList".
	   Directly adding objects at an index would mess up the sorted order.
	*/
	   public void add(int index, Object o){
	   		throw new UnsupportedOperationException("Do not call add(int, Object) on MySortedLinkedList");
	   }

}