/**
 * A MovieShelf is a representation of a shelf of movies. It assumes you are
 * not a collector and therefore only have one copy of each movie. It also
 * assumes you are not very organized and don't alphabetize your movies.
 * It uses the Set data structure to store a collection of Movies.
 */
public interface MovieShelfInterface {

    /**
     * Adds a movie to the shelf, if possible.
     *
     * @param item the movie to add
     * @return true if the movie was added, and false if it's already on the shelf.
     */
    public boolean addItem(Movie item);

    /**
     * Removes a movie from the shelf, if it exists.
     *
     * @param item the movie to remove
     * @return true if the movie was removed, and false if it wasn't on the shelf.
     */
    public boolean removeItem(Movie item);

    /**
     * Watches a movie. If a movie with the same description is on the shelf,
     * then its watch count is incremented, and the new watch count is returned.
     * If the movie is not on the shelf, this method makes no changes to the
     * shelf and returns -1.
     *
     * @param item the movie to watch
     * @return the new watch count, or -1 if the movie was not found
     */
    public int watchMovie(Movie item);

    /**
     * Prints all movies. Prints each item on a separate line. Each item should
     * show the title, year, rating, and watch count.
     */
    public void printAll();

    /**
     * Borrows a movie from another movie shelf, if possible.
     *
     * If the given movie is not on the other shelf, this method does not
     * modify either shelf and returns false.
     *
     * If the given movie is already on this shelf, this method does not
     * modify either shelf and returns false.
     *
     * Otherwise, it will remove the movie from the other shelf, and
     * add it to this shelf, and return true.
     *
     * @param other the shelf to borrow a movie from.
     * @param item the movie to borrow.
     * @return true if it was borrowed successfully, false otherwise.
     */
    public boolean borrowMovie(MovieShelfInterface other, Movie item);
}