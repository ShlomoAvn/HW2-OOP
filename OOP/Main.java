package OOP;

import OOP.Provided.User;
import OOP.SongImpl;
import OOP.UserImpl;

public class Main {
    public static void main(String[] args) throws User.SamePerson, User.AlreadyFriends, User.IllegalRateValue, User.SongAlreadyRated {
        SongImpl song = new SongImpl(1234, "Artist Name", 123, "Song Name");
        UserImpl user = new UserImpl(11, "User Name", 11);
        
        System.out.println("Created Song: " + song.getName());
        System.out.println("Artist Name: " + song.getSingerName());
        System.out.println("Song ID: " + song.getID());
        System.out.println("Duration: " + song.getLength());
        
        System.out.println("Created User: " + user.getName());
        System.out.println("User ID: " + user.getID());
        System.out.println("Age: " + user.getAge());
        
        // Testing UserImpl methods
        UserImpl friend = new UserImpl(12, "Friend Name", 25);
        user.AddFriend(friend);
        friend.rateSong(song, 5);
        System.out.println("Added Friend: " + friend.getName());
        System.out.println("List of Friends: " + user.getFriends().get(friend));
        
        // Testing SongImpl methods (if applicable)
        //song.play();  // Example placeholder method if SongImpl has a playback feature
    }
}
