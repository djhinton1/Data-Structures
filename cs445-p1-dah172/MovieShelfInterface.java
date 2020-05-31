/**
 * The numbers needed interfae
 */
public interface NumbersNeededInterface{

    /**
     * Removes an item from the list.
     *
     * @param item item to be removed
     * @return true if the item was removed, and false if it couldnt be found in the list
     */
    public boolean removeItem(int item);

    /**
     * Prints the items.
     */
    public void printAll();
}
