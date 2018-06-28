public interface StackInterface {
//Determine if stack is empty 
	public boolean isEmpty();
//Most recently added is returned if stack is not empty. 
	public void push(Object o);
// removes the top of the stack 
	public Object pop(); 
// Is on the top of the stack 
	public Object peek();
// Remove all from stack
	public void popAll();
}