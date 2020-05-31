/**
 * Movie represents a movie (imagine that!). Its title and year cannot
 * be changed after creation, but you can watch a movie to increment its
 * watch count, and you can rate it.
 */
public class Movie {

    private String _title;
    private int _year;
    private int _watchCount = 0;
    private int _rating     = 0;

    /**
     * Initialize a Movie with the given name, year, watch count, and rating.
     *
     * @param title the title of the movie
     * @param year the year it was released (to disambiguate movies of the same name)
     * @param watchCount how many times you watched the movie
     * @param rating how good you thought the movie was
     * @throws NullPointerException if title is null
     * @throws IllegalArgumentException if title is empty, or if year is before 1906 (the
     * year of the earliest feature film), or the watch count is negative, or the rating
     * is out of the range [0 .. 5].
     */
    public Movie(String title, int year, int watchCount, int rating)
            throws NullPointerException, IllegalArgumentException {
        if (title == null) throw new NullPointerException("Null title");
        if (title.length() == 0)
            throw new IllegalArgumentException("Empty title");
        if (year < 1906)
            throw new IllegalArgumentException("Invalid year");
        if(watchCount < 0) throw new IllegalArgumentException("Invalid watch count");

        _title = title;
        _year = year;
        _watchCount = watchCount;
        this.setRating(rating);
    }

    /**
     * Initialize a Movie with the given name and year. Its watch count and rating
     * are set to 0.
     *
     * @param title the title of the movie
     * @param year the year it was released (to disambiguate movies of the same name)
     * @throws NullPointerException if title is null
     * @throws IllegalArgumentException if title is empty, or if year is before 1906 (the
     * year of the earliest feature film).
     */
    public Movie(String title, int year)
            throws NullPointerException, IllegalArgumentException {
        this(title, year, 0, 0);
    }

    /**
     * Gets this movie's title.
     * @return  the title
     */
    public String getTitle() {
        return _title;
    }

    /**
     * Gets this movie's year.
     * @return  the year
     */
    public int getYear() {
        return _year;
    }

    /**
     * Gets this movie's watch count.
     * @return  the watch count
     */
    public int getWatchCount() {
        return _watchCount;
    }

    /**
     * Gets this movie's rating.
     * @return  the rating
     */
    public int getRating() {
        return _rating;
    }

    /**
     * Rates a movie on a scale of 0 to 5.
     *
     * @param rating the new rating.
     * @throws IllegalArgumentException if q is negative or above 5.
     */
    public void setRating(int rating) throws IllegalArgumentException {
        if (rating < 0 || rating > 5) throw new IllegalArgumentException("Invalid rating");
        _rating = rating;
    }

    /**
     * Increments the movie's watch count.
     */
    public void watch() {
        _watchCount++;
    }

    /**
     * Converts this movie into a readable string.
     * @return  the string representation of this movie
     */
    @Override
    public String toString() {
        return String.format("%s (%d)", _title, _year);
    }

    /**
     * Determines whether some other object is a Movie that is equal to
     * this Movie. This method considers Movie objects to be equal if their
     * title and year are the same. Two movies can have the same title but
     * different years. Thanks, Hollywood.
     */
    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == null) return false;
        if (!(otherObject instanceof Movie)) return false;
        Movie other = (Movie)otherObject;

        // Note: because _title cannot be set to null, this is safe
        return _title.equals(other._title) && (_year == other._year);
    }
}

