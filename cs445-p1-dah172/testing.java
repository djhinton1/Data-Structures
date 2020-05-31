// DAVID JAMES HINTON II
//this test driver was mostly used to test the Set class as that was the backbone of the project; it has been edited many-a-time; its kind of a mess actually

public class testing{
  public static void main(String[] args) throws SetFullException {
    Set<Integer> theSet = new Set();

    //Testing the add method
    for(int i = 0; i< 11; i++){
      theSet.add(i);
    }
    System.out.println(theSet.add(2));
    printSize(theSet);


    //Testing the remove method:
    int removed = theSet.remove();
    System.out.println("You removed: " + removed);
    printSize(theSet);
    System.out.println(theSet.remove(0));
    printSize(theSet);
    System.out.println(theSet.remove(4));
    printSize(theSet);


    //Testing the contains method
    System.out.println("The set contains the specified number: " + theSet.contains(4));


    //Testing the clear METHOD
    theSet.clear();
    printSize(theSet);


  }

  public static void printSize(Set thing){
    System.out.println("The size of the set is: " + thing.getSize());
  }
}
