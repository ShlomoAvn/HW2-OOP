package OOP.Solution;

import OOP.Provided.Song;
import OOP.Provided.User;

import java.util.*;
import java.util.stream.Collectors;
public class TechnionTunesImpl implements OOP.Provided.TechnionTunes {
    public static final int minGoodSongRating = 8;
    public static final int maxGoodSongRating = 10;
    private Map<Integer, OOP.Provided.Song> songs=new HashMap<>();
    private Map<Integer, OOP.Provided.User > userMap=new HashMap<>();
    public TechnionTunesImpl() {
    }
    public void addUser(int userID , String userName , int userAge) throws UserAlreadyExists
    {
        if(userMap.containsKey(userID))
        {
            throw new UserAlreadyExists();
        }
        OOP.Provided.User newuser = new UserImpl(userID,userName,userAge);
        userMap.put(userID, newuser);

    }
    public OOP.Provided.User getUser(int id) throws UserDoesntExist
    {
        if (!userMap.containsKey(id)) {
            throw new UserDoesntExist();
        }
        OOP.Provided.User newuser = userMap.get(id);

        return newuser;
    }
    public void makeFriends(int id1, int id2) throws  UserDoesntExist , User.AlreadyFriends, User.SamePerson
    {
        if ((!userMap.containsKey(id1))||(!userMap.containsKey(id2))) {
            throw new UserDoesntExist();
        }

        OOP.Provided.User user1=userMap.get(id1);
        OOP.Provided.User user2=userMap.get(id2);
        user1.AddFriend(user2);
        user2.AddFriend(user1);
    }
    public void addSong(int SongID , String songName , int length ,String SingerName) throws SongAlreadyExists
    {
        if(songs.containsKey(SongID))
        {
            throw new SongAlreadyExists();
        }
        OOP.Provided.Song song=new SongImpl(SongID,songName,length,SingerName);
        songs.put(SongID, song);
        return;

    }
    public OOP.Provided.Song getSong(int id) throws SongDoesntExist
    {
        if(!songs.containsKey(id))
        {
            throw new SongDoesntExist();
        }
       return songs.get(id);
    }


    public void rateSong(int userId, int songId, int rate)throws UserDoesntExist,SongDoesntExist, User.IllegalRateValue, User.SongAlreadyRated
    {
        if(!userMap.containsKey(userId))
        {
            throw new UserDoesntExist();
        }
        if(!songs.containsKey(songId))
        {
            throw new SongDoesntExist();
        }
        OOP.Provided.User rater= userMap.get(userId);
        OOP.Provided.Song rated=songs.get(songId);
        rater.rateSong(rated,rate);
        return;
    }


    public Set<OOP.Provided.Song> getIntersection(int IDs[]) throws UserDoesntExist
    {
        Set<OOP.Provided.Song> intersection = null;
        for (int id : IDs) {
            User rater = this.getUser(id);
            if (intersection == null) {
                intersection = new HashSet<>(rater.getRatedSongs());
            } else {
                intersection.retainAll(rater.getRatedSongs());
            }
        }
        return intersection == null ? new HashSet<>() : intersection;
    }


    public Collection<Song> sortSongs(Comparator<Song> comp) {
        return songs.values()
                .stream()
                .sorted(comp)
                .collect(Collectors.toList());
    }

    public Collection<Song> getHighestRatedSongs(int num) {
        return songs.values()
                .stream()
                .sorted(Comparator.comparingDouble(Song::getAverageRating)
                        .thenComparingInt(Song::getLength).reversed()
                        .thenComparing(Song::getID))
                .limit(num)
                .collect(Collectors.toList());
    }

    public Collection<Song> getMostRatedSongs(int num) {
        return songs.values()
                .stream()
                .sorted(Comparator.comparingInt((Song s) -> s.getRaters().size()).reversed()
                        .thenComparingInt(Song::getLength)
                        .thenComparing(Song::getID, Comparator.reverseOrder()))
                .limit(num)
                .collect(Collectors.toList());
    }


    public Collection<User> getTopLikers(int num) {
        return userMap.values()
                .stream()
                .sorted(Comparator.comparingDouble((User u) -> u.getAverageRating())
                        .thenComparingInt(User::getAge).reversed()
                        .thenComparing(User::getID))
                .limit(num)
                .collect(Collectors.toList());
    }


    public boolean canGetAlong(int userId1, int userId2) throws UserDoesntExist {
        User user1 = userMap.get(userId1); // Assuming this method gets a user by their ID
        User user2 = userMap.get(userId2);

        if (user1 == null || user2 == null) {
            throw new UserDoesntExist();
        }

        // Rule 1: Every user can get along with themselves
        if (userId1 == userId2) {
            return true;
        }
       // Map<User,Integer> ms = user1.getFriends();
        // Rule 2: Two friends can get along if they have rated at least one song 8 or higher
        if (user1.getFriends().containsKey(user2)) { // Assuming this method checks if two users are friends
            return canGetAlongDirectly(user1, user2);
        }

        // Rule 3: Check the friendship graph path
        return canGetAlongViaPath(user1, user2);
    }



    private boolean canGetAlongViaPath(User user1, User user2) {
        if (user1.equals(user2)) {
            return true;
        }
    
        Set<User> visited = new HashSet<>();
        Map<User, User> parentMap = new HashMap<>();
    
        return dfs(user1, user2, visited, parentMap);
    }
    
    private boolean dfs(User current, User target, Set<User> visited, Map<User, User> parentMap) {
        visited.add(current);
    
        for (User friend : current.getFriends().keySet()) { // Assuming getFriends(User user) returns the list of friends
            if (!visited.contains(friend) && canGetAlongDirectly(current, friend)) {
                parentMap.put(friend, current);
    
                if (friend.equals(target)) {
                    if (canGetAlongPath(target, parentMap)) {
                        return true;
                    }
                    parentMap.remove(friend);
                } else if (dfs(friend, target, visited, parentMap)) {
                    return true;
                }
            }
        }
    
        return false;
    }

    private boolean canGetAlongPath(User user, Map<User, User> parentMap) {
        User currentUser = user;
        while (parentMap.get(currentUser) != null) {
            User parent = parentMap.get(currentUser);
            if (!canGetAlongDirectly(currentUser, parent)) {
                return false;
            }
            currentUser = parent;
        }
        return true;
    }

    private boolean canGetAlongDirectly(User user1, User user2) {
        // Check if the two users can get along directly by looking at their song ratings
        for (Song song : songs.values()) {
            if (song.getRaters().contains(user1) && song.getRaters().contains(user2)) {
                for (int rating1 = minGoodSongRating; rating1 <= maxGoodSongRating; rating1++) {
                    Set<User> raters1 = song.getRatings().get(rating1);
                    if (raters1 == null || !raters1.contains(user1)) continue;

                    for (int rating2 = minGoodSongRating; rating2 <= maxGoodSongRating; rating2++) {
                        Set<User> raters2 = song.getRatings().get(rating2);
                        if (raters2 == null || !raters2.contains(user2)) continue;

                        if (raters1.contains(user1) && raters2.contains(user2)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    private class SongIterator implements Iterator<Song> {
        private final Iterator<Song> sortedIterator;
    
        public SongIterator() {
            this.sortedIterator = songs.values()
                .stream()
                .sorted(Comparator.comparingInt(Song::getLength).thenComparing(Song::getID))
                .iterator();
        }
    
        @Override
        public boolean hasNext() {
            return sortedIterator.hasNext();
        }
    
        @Override
        public Song next() {
            return sortedIterator.next();
        }
    }
    
    @Override
    public Iterator<Song> iterator() {
        return new SongIterator();
    }
}