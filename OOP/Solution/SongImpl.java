package OOP.Solution;

import OOP.Provided.Song;
import OOP.Provided.User;

import java.util.*;

public class SongImpl implements OOP.Provided.Song {

    private final Map<Integer, Set<User>> ratingsMap = new HashMap<>();
    private final Set<User> raters = new HashSet<>();

    private final int songID;
    private final String songName;
    private final int length;
    private final String singerName;

public SongImpl(int songID, String songName, int length, String singerName) {
    this.songID = songID;
    this.songName = songName;
    this.length = length;
    this.singerName = singerName;
}


    @Override
    public int getID() {
        return songID;
    }

    @Override
    public String getName() {
        return songName;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public String getSingerName() {
        return singerName;
    }

    @Override
    public void rateSong(User user, int rate) throws User.IllegalRateValue,User.SongAlreadyRated{
    if (rate < 0 || rate > 10) {
            throw new OOP.Provided.User.IllegalRateValue();
        }

        if (raters.contains(user)) {
            throw new OOP.Provided.User.SongAlreadyRated();
        }

        raters.add(user);
        ratingsMap.putIfAbsent(rate, new HashSet<>());
        ratingsMap.get(rate).add(user);
    }

    /**
     *
     * @return a collection of users that rated this song ordered as follows:
     *  1- by rating ( from higher to lower )
     *  2- by age ( from lower to higher)
     *  3- by id ( from higher to lower)
     */
    public Collection<User> getRaters() {
        List<User> sortedUsers = new ArrayList<>();
        ratingsMap.entrySet()
                .stream()
                .sorted((e1, e2) -> Integer.compare(e2.getKey(), e1.getKey())) // Rating: higher to lower
                .forEachOrdered(entry -> {
                    List<User> users = new ArrayList<>(entry.getValue());
                    users.sort((u1, u2) -> {
                        int ageComparison = Integer.compare(u1.getAge(), u2.getAge());
                        if (ageComparison != 0) {
                            return ageComparison; // Age: lower to higher
                        }
                        return Integer.compare(u2.getID(), u1.getID()); // ID: higher to lower
                    });
                    sortedUsers.addAll(users);
                });
        return sortedUsers;
    }

    @Override
    /**
     *
     * @return a map where keys are the ratings that this song has and each key's value is
     * a Set of Users ( order doesn't matter ) that rated this song with value that equals
     * the key.
     */
    public Map<Integer, Set<User>> getRatings() {
        Map<Integer, Set<User>> copy = new HashMap<>();
        for (Map.Entry<Integer, Set<User>> entry : ratingsMap.entrySet()) {
            copy.put(entry.getKey(), new HashSet<>(entry.getValue()));
        }
        return copy;
    }

    @Override
    public double getAverageRating() {
        int totalScore = 0;
        int totalRaters = 0;
        for (Map.Entry<Integer, Set<User>> entry : ratingsMap.entrySet()) {
            totalScore += entry.getKey() * entry.getValue().size();
            totalRaters += entry.getValue().size();
        }
        return totalRaters != 0 ? (double) totalScore / totalRaters : 0;
    }
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SongImpl song = (SongImpl) o;
    return songID == song.songID;
}

@Override
public int compareTo(Song o) {
  
    return Integer.compare(this.songID, o.getID());
}
}
