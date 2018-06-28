public class MyStack implements StackInterface {
	/* 
	* TODO 1: Implement "MyStack"
	*/
	private ListInterface slist;
	public MyStack(){
slist = new MyLinkedList(); // new linked list 
}
public boolean isEmpty(){
	return slist.isEmpty(); // empty
}
public void push (Object newItem){ // add
	slist.add(0, newItem); 
}
public void popAll(){
	slist.removeAll();
}
public Object pop() throws StackException{ // remove
	if(!slist.isEmpty()){
		Object temp = slist.get(0);
		slist.remove(0);
		return temp; 

	}
	else{
		throw new StackException("StackException on POP");
	}
}
public Object peek() throws StackException{ // find
	if(!isEmpty()){
		return slist.get(0);
	}
	else{
		throw new StackException("StackException on peek");
	}
}
@Override
    public String toString() { // toString meathod keep overide

    	String result = "";
    	for (int scan = 0; scan < slist.size(); scan++)
    		result = result + slist.get(scan).toString();

    	
    	return result;
    }
}

	/* 
	* TODO 1: Implement "MyStack"
	*/
