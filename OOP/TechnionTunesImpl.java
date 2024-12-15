package OOP;

import OOP.Provided.Song;
import OOP.Provided.User;
import OOP.Provided.TechnionTunes;
import java.util.*;
import java.util.stream.Collectors;
public class TechnionTunesImpl implements OOP.Provided.TechnionTunes {
    private Map<OOP.Provided.Song, Integer> songs=new HashMap<>();
    private Map<OOP.Provided.User, Integer> userMap=new HashMap<>();
    public TechnionTunesImpl()
    {
    }
    void AddUser(int userID , String userName , int userAge) throws UserAlreadyExists
    {
        if(userMap.containsKey(userID))
        {
            throw new UserAlreadyExists();
        }
        OOP.Provided.User newuser(userID,userName,userAge);
        userMap.put(newuser,userID);

    }
    OOP.Provided.User getUser(int id) throws UserDoesntExist
    {
        if (!userMap.containsKey(id)) {
            throw new UserDoesntExist();
        }
        OOP.Provided.User newuser = userMap.get(id);

        return newuser;
    }
    void makeFriends(int id1, int id2) throws  UserDoesntExist , AlreadyFriends , SamePerson;
    {
        if ((!userMap.containsKey(id1))||(!userMap.containsKey(id2))) {
            throw new UserDoesntExist();
        }

        OOP.Provided.User user1=userMap.get(id1);
        OOP.Provided.User user2=userMap.get(id2);
        user1.Addfriend(user2);
        user2.Addfriend(user1);
        return;
    }
    void addSong(int SongID , String songName , int length ,String SingerName) throws SongAlreadyExists
    {
        if(songs.containsKey(songID))
        {
            throw new SongAlreadyExists();
        }
        OOP.Provided.Song song=new OOP.Provided.Song(songID,songName,length,SingerName);
        songs.put(song,songID);
        return;

    }
    OOP.Provided.Song getSong(int id) throws SongDoesntExist
    {
        if(!songs.containsKey(id))
        {
            throw new SongDoesntExist();
        }
        OOP.Provided.Song song=songs.get(id);
        return song;
    }
    void rateSong(int userId, int songId, int rate)throws UserDoesntExist,SongDoesntExist,IllegalRateValue,SongAlreadyRated
    {
        if(!userMap.containsKey(userId))
        {
            throw new UserDoesntExist();
        }
        if(!songs.containsKey(songId))
        {
            throw new SongDoesntExist();
        }
        OOP.provided.User rater= userMap.get(userId);
        OOP.provided.Song rated=songs.get(songId);
        rater.rateSong(rated,rate);
        return;
    }
    Set<OOP.provided.Song> getIntersection(int IDs[]) throws UserDoesntExist
    {
        Set<OOP.provided.Song> ratedSongs=new Set<>();
        for(int i=0;i<IDs.length;i++)
        {
            OOP.provided.User rater= this.getUser(IDs[i]);
            ratedSongs.addAll(rater.getRatedSongs());
        }
        return ratedSongs;
    }
    Collection<Song> sortSongs(Comparator<Song> comp)
    {
        return songs.entrySet()
                .stream()
                .sorted(Comparator.comparingInt((Map.Entry<Song, Integer> e) -> e.getValue()).reversed()
                        .thenComparingInt(e -> e.getKey().getLength())
                        .thenComparing(e -> e.getKey().getID(), Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
    Collection<Song> getHighestRatedSongs(int num)
    {
        return songs.entrySet()
                .stream()
                .sorted(Comparator.comparingInt((Map.Entry<Song, Integer> e) -> e.getKey().getAverageRating().reversed()
                        .thenComparingInt(e -> e.getKey().getLength())
                        .thenComparing(e -> e.getKey().getID(), Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                        .limit(num)
                .collect(Collectors.toList());
    }
    Collection<Song> getMostRatedSongs(int num)
    {
        return songs.entrySet()
                .stream()
                .sorted(Comparator.comparingInt((Map.Entry<Song, Integer> e) -> e.getKey().getRaters().size().reversed()
                                .thenComparingInt(e -> e.getKey().getLength())
                                .thenComparing(e -> e.getKey().getID(), Comparator.reverseOrder()))
                        .map(Map.Entry::getKey)
                        .limit(num)
                        .collect(Collectors.toList());
    }
    Collection<User> getTopLikers(int num)
    {
        return userMap.entrySet()
                .stream()
                .sorted(Comparator.comparingInt((Map.Entry<User, Integer> e) -> e.getKey().getAverageRating().reversed()
                                .thenComparingInt(e -> e.getKey().getAge().reversed()))
                                .thenComparing(e -> e.getKey().getID(), Comparator.reverseOrder()))
                        .map(Map.Entry::getKey)
                        .limit(num)
                        .collect(Collectors.toList());
    }

    boolean canGetAlong(int userId1, int userId2) throws UserDoesntExist
    {
        if ((!userMap.containsKey(id1))||(!userMap.containsKey(id2))) {
            throw new UserDoesntExist();
        }
        if(userId1 == userId2)
        {
            return true;
        }
        OOP.Provided.User user1=userMap.get(id1);
        OOP.Provided.User user2=userMap.get(id2);
        if(user1.favoriteSongInCommon(user2))
        {
            return true;
        }
        if()
    }


}