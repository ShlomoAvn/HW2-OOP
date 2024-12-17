package OOP.Solution;

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
    
    public UserImpl(int userID, String userName, int userAge) {
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







    @Override
    public User rateSong(Song song, int rate) throws IllegalRateValue, SongAlreadyRated  {
        if (rate < 0 || rate > 10) {
            throw new IllegalRateValue();
        }
        if (ratedSongs.containsKey(song)) {
            throw new SongAlreadyRated();
        }
        ratedSongs.put(song, rate);
        song.rateSong(this, rate);
        return this;
    }


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


public int getPlaylistLength() {
    return ratedSongs.keySet()
            .stream()
            .mapToInt(Song::getLength)
            .sum();
}


    public Collection<Song> getRatedSongs() {
    return ratedSongs.entrySet()
            .stream()
            .sorted(Comparator.comparingInt((Map.Entry<Song, Integer> e) -> e.getValue()).reversed()
                    .thenComparingInt(e -> e.getKey().getLength())
                    .thenComparing(e -> e.getKey().getID(), Comparator.reverseOrder()))
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
}


public List<Song> getFavoriteSongs() {
    return ratedSongs.entrySet()
            .stream()
            .filter(entry -> entry.getValue() >= 8)
            .sorted(Comparator.comparingInt(e -> e.getKey().getID()))
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
}






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

public boolean favoriteSongInCommon(User user) {
    if (!friends.contains(user)) {
        return false;
    }
    Set<Song> commonFavorites = new HashSet<>(this.getFavoriteSongs());
    commonFavorites.retainAll(user.getFavoriteSongs());
    return !commonFavorites.isEmpty();
}


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
