public interface QueueInterface {
	public boolean isEmpty();

	public void enqueue(Object item); // adding last 

	public Object dequeue(); // removing front 

	public void dequeueAll(); // remove everything 

	public Object peek(); // looks at stuff 
}