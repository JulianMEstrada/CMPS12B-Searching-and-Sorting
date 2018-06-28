public class MyQueue implements QueueInterface {
	/* 
	* TODO 2: Implement "MyQueue"
	*/
	private ListInterface alist;

	public MyQueue(){
	alist = new MyLinkedList(); // new linked list 
}
public boolean isEmpty(){ // empty
	return alist.isEmpty(); 
}
public void dequeueAll(){ // remove all
	alist.removeAll(); 
}
public void enqueue(Object newItem){ // add
	alist.add(alist.size(), newItem);
}
public Object dequeue() throws QueueException{ // remove
	if(!isEmpty()){
		Object queueFront = alist.get(0);
		alist.remove(0);
		return queueFront; 
	}
	else{
		throw new QueueException("QueueException on dequeue");
	}
}
public Object peek() throws QueueException{ // find 
	if(!isEmpty()){
		return alist.get(0); 
	}
	else{
		throw new QueueException("QueueException on peek");
	}
}
@Override
    public String toString() { // toString meathod keep overide

    	String result = "";
    	for (int scan = 0; scan < alist.size(); scan++)
    		result = result + alist.get(scan).toString();

    	
    	return result;
    }
}
	/* 
	* TODO 2: Implement "MyQueue"
	*/

	