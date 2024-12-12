package OOP;

import OOP.Provided.Song;
import OOP.Provided.User;

import java.util.*;
import java.util.stream.Collectors;


public class UserImpl implements OOP.Provided.User {
private int userID;
private String userName;
private int userAge;
private Map<OOP.Provided.Song, Integer> ratedSongs = new HashMap<>();
private Set<User> friends = new HashSet<>();
    
    public UserImpl(int userID, String userName, int userAge) throws SamePerson, AlreadyFriends {
        this.userID = userID;
        this.userName = userName;
        this.userAge = userAge;
    }

    public int getID() {
        return userID;
    }

    public String getName() {
        return userName;
    }

    public int getAge() {
        return userAge;
    }






        /**
         * if the user already rated the song before then SongAlreadyRated exception should be thrown.
         * @param song that the user wants to rate
         * @param rate should be between 0 and 10 (included) , otherwise IllegalRateValue exception should be thrown.
         * @return the user himself
         */
    @Override
    public User rateSong(Song song, int rate) throws IllegalRateValue, SongAlreadyRated  {
        if (rate < 0 || rate > 10) {
            throw new IllegalRateValue();
        }
        if (ratedSongs.containsKey(song)) {
            throw new SongAlreadyRated();
        }
        ratedSongs.put(song, rate);
        return this;
    }

/**
 * Calculates and returns the average rating of all songs rated by the user.
 *
 * @return the average song rating, or 0 if no songs have been rated
 */
public double getAverageRating() {
    if (ratedSongs.isEmpty()) {
        return 0;
    }
    return ratedSongs.values()
            .stream()
            .mapToInt(Integer::intValue)
            .average()
            .orElse(0);
}

/**
 * Calculates and returns the total length (in seconds) of all songs rated by the user.
 *
 * @return the total playlist length, or 0 if no songs have been rated
 */
public int getPlaylistLength() {
    return ratedSongs.keySet()
            .stream()
            .mapToInt(Song::getLength)
            .sum();
}

    /**
     *
     * @return a collection of songs that have been rated by the user .
     * the collection should be ordered as follows:
     *  1- by rating ( from higher to lower )
     *  2- by length ( from lower to higher)
     *  3- by id ( from higher to lower)
     */
    public Collection<Song> getRatedSongs() {
    return ratedSongs.entrySet()
            .stream()
            .sorted(Comparator.comparingInt((Map.Entry<Song, Integer> e) -> e.getValue()).reversed()
                    .thenComparingInt(e -> e.getKey().getLength())
                    .thenComparing(e -> e.getKey().getID(), Comparator.reverseOrder()))
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
}

/**
 * Returns a collection of songs rated by the user with a score of 8 or above,
 * sorted by their ID in ascending order.
 *
 * @return the sorted list of favorite songs
 */
public List<Song> getFavoriteSongs() {
    return ratedSongs.entrySet()
            .stream()
            .filter(entry -> entry.getValue() >= 8)
            .sorted(Comparator.comparingInt(e -> e.getKey().getID()))
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
}





    /**
     * Create a new frinedship.
     * if they already friends before AlreadyFriends exception should be thrown.
     * @param friend
     * @throws SamePerson .
     * @return
     */
    public User AddFriend(User friend) throws AlreadyFriends , SamePerson{
    if (friend.equals(this)) {
        throw new SamePerson();
    }
    if (friends.contains(friend)) {
        throw new AlreadyFriends();
    }
    friends.add(friend);
    return null;
}

    /**
     *
     * @param user
     * @return true if the two users are friends and have at least one song that they both
     * rated 8+ (included)
     */
public boolean favoriteSongInCommon(User user) {
    if (!friends.contains(user)) {
        return false;
    }
    Set<Song> commonFavorites = new HashSet<>(this.getFavoriteSongs());
    commonFavorites.retainAll(user.getFavoriteSongs());
    return !commonFavorites.isEmpty();
}

    /**
     *
     * @return a map of the user's friends where <key,value> = <friend ,
     *  the number of friend's rated songs >
     */
public Map<User,Integer> getFriends() {
    return friends.stream()
            .collect(Collectors.toMap(
                    friend -> friend,
                    friend -> friend.getRatedSongs().size()
            ));
}

    @Override
    public int compareTo(User o) {
        int idComparison = Integer.compare(this.userID, o.getID());
            return idComparison;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserImpl user = (UserImpl) obj;
        return userID == user.userID;
    }
}
