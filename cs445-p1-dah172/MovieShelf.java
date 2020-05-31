// David James Hinton II
//Thanks for your help!

public class MovieShelf implements MovieShelfInterface {
  private Set theShelf;

//------------------------------------------------------------------------------
//these are the constructors; note to self: CONSTRUCTORS DO NOT CONTAIN A RETURN TYPE LOL
  public MovieShelf(){
    theShelf = new Set<Movie>();
  }

  public MovieShelf(Movie[] movies) {
    this();
    for(int i = 0; i < movies.length; i++){
      theShelf.add(movies[i]);
    }
  }

//------------------------------------------------------------------------------
//adding an item
//here, we do not need to do any error checks as the add() method in the Set class does all of that for us
@Override
  public boolean addItem(Movie item){
    return theShelf.add(item);
  }

//------------------------------------------------------------------------------
//removing an item from the shelf
//this could be done in 1 step but the remove method does not return boolean
@Override
  public boolean removeItem(Movie item){
    if(theShelf.remove(item) != null)
      return true;
    else
      return false;
  }

//------------------------------------------------------------------------------
//incriments the watch count and then returns that count
//could be done in one step but the watch() method in the movie interface does not return anything
  @Override
  public int watchMovie(Movie item){
    if(theShelf.contains(item)){
      item.watch();
      return item.getWatchCount();
    }
    else
      return -1;
  }

//------------------------------------------------------------------------------
// print formating for printing the shelves
  @Override
  public void printAll(){
    System.out.println("------------------------------------------------------------------");
    System.out.printf("%-30s %-7s %-10s %-11s", "TITLE", "YEAR", "RATING", "WATCH COUNT");
    System.out.println();
    System.out.println("------------------------------------------------------------------");
    
    for(Object m:theShelf.toArray()){
      Movie k = (Movie) m;
      System.out.format("%-30s %-7s %-10s %-11s",k.getTitle(),k.getYear(),k.getRating()+"/5",k.getWatchCount());
      System.out.println();
    }
    return;
  }

//------------------------------------------------------------------------------
//borrowing a movie ("item") from shelf ("other")
  @Override
  public boolean borrowMovie(MovieShelfInterface other, Movie item){
    if(!theShelf.contains(item)) //if we dont already have the movie
        if(other.removeItem(item)) //would return false if the movie wasnt on the shelf
          return theShelf.add(item);
        else
          return false;
    else
      return false;
  }
}
