class MyHashtable implements DictionaryInterface { // Create new table use protected for size and table
	protected int tableSize; 
	protected int size; 
	MyLinkedList [] table; 

	protected class Entry { // new Entry 
		
		String key ; 
		Object value; 


		protected Entry(String key, Object value){
			this.key = key;
			this.value = value; 
		}
	}// end Entry 

 public MyHashtable(int tableSize){ // Initalize hashtable 
 	this.tableSize = tableSize;  
 	table = new MyLinkedList[tableSize]; 

 }// end myHashtable

 public boolean isEmpty(){
 	if (size() == 0){
 		return true; 
 	}
 	else{
 		return false; 
 	}
 }

 public int size(){
 	return size; 
 }

	public Object put(String key, Object value){ // put for hashtable
   	// set hashCode from key 
		int​ hashCode = key.hashCode(); 
		int​ arrayIndex = Math.abs(hashCode) % tableSize;
    // if the list is null, make new linked list and add to front
		if (table[arrayIndex]==null){
                  table[arrayIndex] = new MyLinkedList(); // new
                  table[arrayIndex].add(0,new Entry(key,value)); // add to front
                  size++; 
              } 

    // if the list is not null, go through table and find matching key, set its *value* to the current matching key, also return the old value
              else if(table[arrayIndex] != null) { 
              	for(int i=0; i<table[arrayIndex].size();i++){
              		if(((Entry) table[arrayIndex].get(i)).key.equals(key)){
              			Object oldValue = value;
              			((Entry)table[arrayIndex].get(i)).value = value ; 
              			return oldValue;  
              		} // end of if
              	} // end of for loop
              	   // if key is not in bucket and list is not null add to front and return null     
              	table[arrayIndex].add(0,new Entry(key,value));
              	size++;
              	 } //end of else if


              	 return null;
}//end of object put 

public Object get(String key){
	// set hashCode from key 
	int​ hashCode = key.hashCode();
	int​ arrayIndex = Math.abs(hashCode) % tableSize;
    // if table is empty return null  
	if (table[arrayIndex] == null){
		return null; 
	}
    // if table is not empty find matching key and return its *value*
	if (table[arrayIndex] != null){
		for(int i=0; i<table[arrayIndex].size();i++){
			if(((Entry)table[arrayIndex].get(i)).key.equals(key)){
				return  ((Entry) table[arrayIndex].get(i)).value; 
			}
			
			
		}
	}
     // key not found return null 
	return null; 
 }// end get

 public void remove(String key){
 	// set hashCode from key 
 	int​ hashCode = key.hashCode();
 	int​ arrayIndex = Math.abs(hashCode) % tableSize; 
     //if null then key DNE do nothing 
 	if (table[arrayIndex] == null){
 		return;
 	}
 	if (table[arrayIndex] != null){
 		for(int i=0; i<table[arrayIndex].size();i++){
 			if(((Entry) table[arrayIndex].get(i)).key.equals(key)){
 				table[arrayIndex].remove(i); 
 				size = size-1; 
 			}
 		}
 	}
 }

 public void clear(){
 	MyLinkedList [] table;
 	size = 0;  
 }

 public String [] getKeys(){
 	int keyIndex = 0;
 	String [] keys = new String[size];
 	for(int i=0; i<tableSize; i++){
 		if (table[i] != null){
 			for(int j=0; j<table[i].size();j++){
 				keys[keyIndex]= ((Entry)table[i].get(j)).key;
 				keyIndex++;
 			}
 		}
 	}
 	return keys; 
 }

// Returns the size of the biggest bucket (most collisions) in the hashtable. 
 public int biggestBucket() {
 	int biggestBucket = 0; 
 	for(int i = 0; i < table.length; i++) {
			// Loop through the table looking for non-null locations. 
 		if (table[i] != null) {
				// If you find a non-null location, compare the bucket size against the largest
				// bucket size found so far. If the current bucket size is bigger, set biggestBucket
				// to this new size. 
 			MyLinkedList bucket = table[i];
 			if (biggestBucket < bucket.size())
 				biggestBucket = bucket.size();
 		}
 	}
		return biggestBucket; // Return the size of the biggest bucket found. 
	}

	// Returns the average bucket length. Gives a sense of how many collisions are happening overall.
	public float averageBucket() {
		float bucketCount = 0; // Number of buckets (non-null table locations)
		float bucketSizeSum = 0; // Sum of the size of all buckets
		for(int i = 0; i < table.length; i++) {
			// Loop through the table 
			if (table[i] != null) {
				// For a non-null location, increment the bucketCount and add to the bucketSizeSum
				MyLinkedList bucket = table[i];
				bucketSizeSum += bucket.size();
				bucketCount++;
			}
		}

		// Divide bucketSizeSum by the number of buckets to get an average bucket length. 
		return bucketSizeSum/bucketCount; 
	}

	public String toString() {
		String s = "";
		for(int tableIndex = 0; tableIndex < tableSize; tableIndex++) {
			if (table[tableIndex] != null) {
				MyLinkedList bucket = table[tableIndex];
				for(int listIndex = 0; listIndex < bucket.size(); listIndex++) {
					Entry e = (Entry)bucket.get(listIndex);
					s = s + "key: " + e.key + ", value: " + e.value + "\n";
				}
			}
		}

		return s; 
		
	}
	
}
