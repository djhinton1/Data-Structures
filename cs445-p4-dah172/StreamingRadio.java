/**
 * This abstract data type represents the backend for a streaming radio service.
 * It stores the songs, stations, and users in the system, as well as the
 * ratings that users assign to songs.
 */
public interface StreamingRadio {

	/*
	 * The abstract methods below are declared as void methods with no
	 * parameters. You need to expand each declaration to specify a return type
	 * and parameters, as necessary.
	 *
	 * You also need to include a detailed comment for each abstract method describing
	 * its effect, its return value, any corner cases that the client may need to
	 * consider, any exceptions the method may throw (including a description of the
	 * circumstances under which this will happen), and so on.
	 *
	 * You should include enough details that a client could use this data structure
	 * without ever being surprised or not knowing what will happen, even though they
	 * haven't read the implementation.
	 */

	/**
	 * Adds a new song to the system.
	 *
	 * If the requested song to add is not null, and the system does not already
	 * have the song, then the song will be added to the system.
	 * This method will return true if the song was sucessfully added to the
	 * system; false if the system already has the song.
	 * If the song to be added does not exist, then an IllegalArgumentException
	 * will be thrown.
	 *
	 * @param theSong the song to be added.
	 * @throws NullPointerException if the song to be added is null.
	 * @throws IllegalArgumentException if the song to be added does not exist.
	 * @return true if the song was successfully added to the system; will return
	 * false if the system already has the song.
	 */
	boolean addSong(Song theSong)
		throws NullPointerException, IllegalArgumentException;

	/**
	 * Removes an existing song from the system.
	 *
	 * If the song to be removed exists within the system (has been added and
	 * has NOT been removed already), then it will be removed from the system and
	 * the method will return true. If the song requested to be removed does not
	 * exist within the system, then the method will return false. If the
	 * requested song to be removed is null, then a NullPointerException will
	 * be thrown.
	 *
	 * @param theSong song to be removed.
	 * @throws NullPointerException if the requested song to be removed is null.
	 * @return true if the song was sucessfully removed; false if the song could
	 * not be found within the system.
	 */
	boolean removeSong(Song theSong) throws NullPointerException;

	/**
	 * Adds an existing song to the playlist for an existing radio station.
	 *
	 * The playlist should accept multiples of the same song.
	 * If the song to be added exists within the system, then the song will be
	 * added to the playlist and the method will return true. The method will
	 * return false if the song does not exist within the system.
	 * If the song or the station is null, then a NullPointerException will
	 * be thrown. If the station does not exist, then an IllegalArgumentException
	 * will be thrown.
	 *
	 * @param theSong song to be added to the station.
	 * @param theStation station to which the song will be added.
	 * @throws NullPointerException if the added song is null.
	 * @throws IllegalArgumentException if the station does not exist.
	 * @return true if the song was sucessfully added to the playlist; false
	 * if the song does not exist within the system.
	 */
	boolean addToStation(Song theSong, Station theStation)
		throws NullPointerException, IllegalArgumentException;

	/**
	 * Removes a song from the playlist for a radio station.
	 *
	 * If the song to be removed exists within the playlist (has been added and
	 * has NOT been removed already), then the song is removed from the playlist
	 * and the method will return true. If the requested song does not exist
	 * within the playlist, then the method will return false. If the requested
	 * song or station is null, then a NullPointerException will be thrown.
	 * If the station does not exist, then an IllegalArgumentException will
	 * be thrown.
	 *
	 * @param theSong song to be removed.
	 * @param theStation station from which the song will be removed.
	 * @throws NullPointerException if the requested song or the station is null.
	 * @throws IllegalArgumentException if the station does not exist.
	 * @return true if the song was sucessfully removed; false if the song could
	 * not be found within the playlist.
	 */
	boolean removeFromStation(Song theSong, Station theStation)
		throws NullPointerException, IllegalArgumentException;

	/**
	 * Sets a user's rating for a song, as a number of stars from 1 to 5.
	 *
	 * If the user has already rated the song, this method should be able to
	 * override the current rating and will return true if a rating was assigned
	 * to a specific song under a specific user. This method will return false
	 * if the song does not exist within the system. An IllegalArgumentException will
	 * be thrown if the rating is less than 0 or greater than 5. It will also throw a
	 * NullPointerException if either the user, song, or rating is null.
	 *
	 * @param theUser user from which the rating originates.
	 * @param theSong song to be rated.
	 * @param theRating rating value of the song.
	 * @throws IllegalArgumentException if the rating is greater than 5 or less than 0.
	 * @throws NullPointerException if the user, song, or rating null.
	 * @return true if the rating was sucessfully set, false if the song does
	 * not exist within the system.
	 */
	boolean rateSong(User theUser, Song theSong, int theRating)
		throws NullPointerException, IllegalArgumentException;

	/**
	 * Clears a user's rating on a song. If this user has rated this song and
	 * the rating has not already been cleared, then the rating is cleared and
	 * the state will appear as if the rating was never made. If there is no
	 * such rating on record (either because this user has not rated this song,
	 * or because the rating has already been cleared), then this method will
	 * throw an IllegalArgumentException.
	 *
	 * @param theUser user whose rating should be cleared
	 * @param theSong song from which the user's rating should be cleared
	 * @throws IllegalArgumentException if the user does not currently have a
	 * rating on record for the song
	 * @throws NullPointerException if either the user or the song is null
	 */
	public void clearRating(User theUser, Song theSong)
		throws IllegalArgumentException, NullPointerException;

	/**
	 * Predicts the rating a user will assign to a song that they have not yet
	 * rated, as a number of stars from 1 to 5.
	 *
	 * Upon sucessful rating prediction, this method will return the value of the
	 * predicted rating. If the user has already rated the song, this method will
	 * return -1. It will throw a NullPointerException if either the user
	 * the song is null. It will throw a IllegalArgumentException if the song
	 * does not exist within the system.
	 *
	 * @param theUser user for whom a rating will be predicted.
	 * @param theSong song to which that rating will be assigned.
	 * @throws NullPointerException if either the user or the song is null.
	 * @throws IllegalArgumentException if the song does not exist within
	 * the system.
	 * @return integer value of the rating.
	 */
	int predictRating(User theUser, Song theSong)
		throws NullPointerException, IllegalArgumentException;

	/**
	 * Suggests a song for a user that they are predicted to like.
	 *
	 * If the predicted user rating of a song in the system is greater than 3,
	 * then this method will return that song. If there is no song in the System
	 * for which the users predicted rating is greater than 3, then this method will
	 * return null. If the user is null, then a NullPointerException will be
	 * thrown.
	 *
	 * @param theUser user to which a song will be suggested.
	 * @throws NullPointerException if the user is null.
	 * @return suggested song.
	 */
	Song suggestSong(User theUser) throws NullPointerException;
}
