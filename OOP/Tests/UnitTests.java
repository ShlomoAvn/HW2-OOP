package OOP.Tests;

import OOP.Provided.*;
import OOP.Solution.*;
import org.junit.*;
import java.util.*;

public class UnitTests {

    @Test(expected = TechnionTunes.UserDoesntExist.class)
    public void basicGetUserTest() throws TechnionTunes.UserAlreadyExists, TechnionTunes.UserDoesntExist {
        TechnionTunes tt = new TechnionTunesImpl();
        tt.addUser(18,"Lama",20);
        Assert.assertEquals("Lama",tt.getUser(18).getName());
        tt.getUser(15);
    }

    @Test(expected = TechnionTunes.UserAlreadyExists.class)
    public void basicAddUserTest() throws TechnionTunes.UserAlreadyExists, TechnionTunes.UserDoesntExist {
        TechnionTunes tt = new TechnionTunesImpl();
        tt.addUser(18,"Lama",20);
        Assert.assertEquals("Lama",tt.getUser(18).getName());
        tt.addUser(18,"Ward",20);
    }

    @Test(expected = TechnionTunes.UserDoesntExist.class)
    public void firstMakeFriendsTest() throws TechnionTunes.UserAlreadyExists, TechnionTunes.UserDoesntExist, User.AlreadyFriends, User.SamePerson {
        TechnionTunes tt = new TechnionTunesImpl();
        tt.addUser(18,"Lama",21);
        tt.addUser(1,"Ward",22);
        tt.makeFriends(1,200);
    }

    @Test(expected = User.AlreadyFriends.class)
    public void secondMakeFriendsTest() throws TechnionTunes.UserAlreadyExists, TechnionTunes.UserDoesntExist, User.AlreadyFriends, User.SamePerson {
        TechnionTunes tt = new TechnionTunesImpl();
        tt.addUser(18,"Lama",21);
        tt.addUser(1,"Ward",22);
        tt.makeFriends(1,18);
        tt.addUser(5, "Raneem", 70);
        tt.makeFriends(18,1);
    }

    @Test(expected = User.SamePerson.class)
    public void thirdMakeFriendsTest() throws TechnionTunes.UserAlreadyExists, TechnionTunes.UserDoesntExist, User.AlreadyFriends, User.SamePerson {
        TechnionTunes tt = new TechnionTunesImpl();
        tt.addUser(18,"Lama",21);
        tt.addUser(1,"Ward",22);
        tt.makeFriends(1,1);
    }

    @Test(expected = TechnionTunes.SongAlreadyExists.class)
    public void basicAddSongTest() throws TechnionTunes.SongAlreadyExists, TechnionTunes.SongDoesntExist {
        TechnionTunes tt = new TechnionTunesImpl();
        tt.addSong(1000,"Weeew",5, "WOOOOW");
        Assert.assertEquals("Weeew",tt.getSong(1000).getName());
        tt.addSong(1000,"WOOOW",5, "Weeew");
    }

    @Test(expected = TechnionTunes.SongDoesntExist.class)
    public void basicGetSongTest() throws TechnionTunes.SongAlreadyExists, TechnionTunes.SongDoesntExist {
        TechnionTunes tt = new TechnionTunesImpl();
        tt.addSong(1000,"Weeew",5, "WOOOOW");
        tt.getSong(10);
    }

    @Test(expected = TechnionTunes.UserDoesntExist.class)
    public void firstExceptionRateSongTest() throws TechnionTunes.UserDoesntExist, TechnionTunes.SongDoesntExist, User.SongAlreadyRated, User.IllegalRateValue {
        TechnionTunes tt = new TechnionTunesImpl();
        tt.rateSong(1, 2, -5);
    }

    @Test(expected = TechnionTunes.SongDoesntExist.class)
    public void secondExceptionRateSongTest() throws TechnionTunes.UserDoesntExist, TechnionTunes.SongDoesntExist, User.SongAlreadyRated, User.IllegalRateValue, TechnionTunes.UserAlreadyExists {
        TechnionTunes tt = new TechnionTunesImpl();
        tt.addUser(1,"Ward",22);
        tt.rateSong(1, 2, -5);
    }

    @Test(expected = User.IllegalRateValue.class)
    public void thirdExceptionRateSongTest() throws TechnionTunes.UserDoesntExist, TechnionTunes.SongDoesntExist, User.SongAlreadyRated, User.IllegalRateValue, TechnionTunes.UserAlreadyExists, TechnionTunes.SongAlreadyExists {
        TechnionTunes tt = new TechnionTunesImpl();
        tt.addUser(1,"Ward",22);
        tt.addSong(2,"Weeew",5, "WOOOOW");
        tt.rateSong(1, 2, -5);
    }

    @Test(expected = User.SongAlreadyRated.class)
    public void fourthExceptionRateSongTest() throws TechnionTunes.UserDoesntExist, TechnionTunes.SongDoesntExist, User.SongAlreadyRated, User.IllegalRateValue, TechnionTunes.UserAlreadyExists, TechnionTunes.SongAlreadyExists {
        TechnionTunes tt = new TechnionTunesImpl();
        tt.addUser(1,"Ward",22);
        tt.addSong(2,"Weeew",5, "WOOOOW");
        tt.rateSong(1, 2, 3);
        tt.rateSong(1, 2, 8);
    }

    private void initSongs(TechnionTunes tt) throws TechnionTunes.SongAlreadyExists {
        tt.addSong(17,"Bayen Habeyt",100, "Amro");
        tt.addSong(3,"Easy on me",5, "Adele");
        tt.addSong(8,"Hello",5, "Adele");
        tt.addSong(10,"All I ask",5, "Adele");
        tt.addSong(22,"Another love",5, "Tom");
        tt.addSong(1000,"Awl marra",4, "Hamza");
        tt.addSong(5,"Perfect",10, "Ed");
        tt.addSong(25,"Let her go",5, "Passenger");
        tt.addSong(37,"Just dream",3, "Shireen");
        tt.addSong(31,"Roo7",4, "Fadel");
        tt.addSong(13,"Lovely",5, "Billie");
        tt.addSong(18,"Ya 2amar",19, "Zuhair");
        tt.addSong(16,"Mrayti",5, "Rasha");
        tt.addSong(26,"Betmoon",55, "Marwan");
        tt.addSong(6,"A7la w7de",90, "Azeez");
        tt.addSong(7,"Kefak enta",6, "Fairoz");
        tt.addSong(170,"Hiya Hiya",5, "Shama");
    }

    @Test
    public void sortSongsTest() throws TechnionTunes.SongAlreadyExists, TechnionTunes.SongDoesntExist {
        TechnionTunes tt = new TechnionTunesImpl();

        initSongs(tt);
        List<Song> expectedSortedSongs = Arrays.asList(
                tt.getSong(3),
                tt.getSong(5),
                tt.getSong(6),
                tt.getSong(7),
                tt.getSong(8),
                tt.getSong(10),
                tt.getSong(13),
                tt.getSong(16),
                tt.getSong(17),
                tt.getSong(18),
                tt.getSong(22),
                tt.getSong(25),
                tt.getSong(26),
                tt.getSong(31),
                tt.getSong(37),
                tt.getSong(170),
                tt.getSong(1000)
        );
        Assert.assertEquals(expectedSortedSongs, tt.sortSongs(Song::compareTo));
    }

    private void initUsers(TechnionTunes tt) throws TechnionTunes.UserAlreadyExists {
        tt.addUser(1,"Ward",22);
        tt.addUser(18,"Lama",21);
        tt.addUser(11,"Raneem",18);
        tt.addUser(3,"Lana",22);
        tt.addUser(31,"Yazan",22);
        tt.addUser(100,"Rama",15);
        tt.addUser(55,"Mohammad",8);
        tt.addUser(72,"Bashar",70);
        tt.addUser(19,"Samiya",21);
        tt.addUser(1000,"Waed",27);
    }

    @Test(expected = TechnionTunes.UserDoesntExist.class)
    public void getIntersectionTest() throws TechnionTunes.UserAlreadyExists, TechnionTunes.SongAlreadyExists, TechnionTunes.UserDoesntExist, User.SongAlreadyRated, User.IllegalRateValue, TechnionTunes.SongDoesntExist {
        TechnionTunes tt = new TechnionTunesImpl();

        initUsers(tt);
        initSongs(tt);

        Set<Song> expectedIntersection = new HashSet<Song>();

        int IDsTest1[] = {1000, 19, 72};
        Assert.assertEquals(expectedIntersection, tt.getIntersection(IDsTest1));

        int IDsTest2[] = {};

        Assert.assertEquals(expectedIntersection, tt.getIntersection(IDsTest2));

        tt.rateSong(1, 17, 8);
        tt.rateSong(1, 37, 8);
        tt.rateSong(1, 31, 8);
        tt.rateSong(1, 1000, 8);
        tt.rateSong(1, 5, 8);

        tt.rateSong(18, 17, 8);
        tt.rateSong(18, 37, 8);
        tt.rateSong(18, 31, 8);
        tt.rateSong(18, 170, 8);
        tt.rateSong(18, 7, 8);


        tt.rateSong(11, 17, 8);
        tt.rateSong(11, 37, 8);
        tt.rateSong(11, 31, 8);
        tt.rateSong(11, 170, 8);

        tt.rateSong(3, 17, 8);
        tt.rateSong(3, 37, 8);
        tt.rateSong(3, 31, 8);
        tt.rateSong(3, 7, 8);

        tt.rateSong(31, 17, 8);
        tt.rateSong(31, 37, 8);
        tt.rateSong(31, 31, 8);

        int IDsTest3[] = {1, 18, 11, 31, 3};

        expectedIntersection.add(tt.getSong(17));
        expectedIntersection.add(tt.getSong(31));
        expectedIntersection.add(tt.getSong(37));

        Assert.assertEquals(expectedIntersection, tt.getIntersection(IDsTest3));

        int IDsTest4[] = {1, 18, 500, 31};

        tt.getIntersection(IDsTest4);
    }

    @Test
    public void getHighestRatedSongsTest() throws TechnionTunes.UserAlreadyExists, TechnionTunes.SongAlreadyExists, TechnionTunes.UserDoesntExist, User.SongAlreadyRated, User.IllegalRateValue, TechnionTunes.SongDoesntExist {
        TechnionTunes tt = new TechnionTunesImpl();

        Collection<Song> expectedCollection1 = new Vector<Song>();

        Assert.assertEquals(expectedCollection1, tt.getHighestRatedSongs(8));

        initUsers(tt);
        initSongs(tt);

        expectedCollection1.add(tt.getSong(17));
        expectedCollection1.add(tt.getSong(6));
        expectedCollection1.add(tt.getSong(26));
        expectedCollection1.add(tt.getSong(18));
        expectedCollection1.add(tt.getSong(5));
        expectedCollection1.add(tt.getSong(7));
        expectedCollection1.add(tt.getSong(3));
        expectedCollection1.add(tt.getSong(8));

        Assert.assertEquals(expectedCollection1, tt.getHighestRatedSongs(8));

        Collection<Song> expectedCollection2 = new Vector<Song>();

        tt.rateSong(1, 5, 10);
        tt.rateSong(1, 170, 8);
        tt.rateSong(1, 31, 8);
        tt.rateSong(1, 17, 0);

        tt.rateSong(18, 5, 10);
        tt.rateSong(18, 170, 8);
        tt.rateSong(18, 31, 8);

        tt.rateSong(11, 5, 10);
        tt.rateSong(11, 170, 8);
        tt.rateSong(11, 7, 8);
        tt.rateSong(11, 1000, 9);
        tt.rateSong(11, 37, 10);

        tt.rateSong(3, 5, 10);
        tt.rateSong(3, 7, 8);
        tt.rateSong(3, 1000, 6);
        tt.rateSong(3, 31, 6);
        tt.rateSong(3, 37, 10);

        tt.rateSong(31, 5, 10);
        tt.rateSong(31, 7, 8);
        tt.rateSong(31, 1000, 7);
        tt.rateSong(31, 37, 10);

        expectedCollection2.add(tt.getSong(5));
        expectedCollection2.add(tt.getSong(37));
        expectedCollection2.add(tt.getSong(7));
        expectedCollection2.add(tt.getSong(170));
        double a = tt.getSong(5).getAverageRating();
        Collection<Song> ls = tt.getHighestRatedSongs(4);
        Assert.assertEquals(expectedCollection2, tt.getHighestRatedSongs(4));

        expectedCollection2.add(tt.getSong(31));
        expectedCollection2.add(tt.getSong(1000));

        Assert.assertEquals(expectedCollection2, tt.getHighestRatedSongs(6));

        expectedCollection2.add(tt.getSong(17));
        expectedCollection2.add(tt.getSong(6));
        expectedCollection2.add(tt.getSong(26));
        expectedCollection2.add(tt.getSong(18));

        Assert.assertEquals(expectedCollection2, tt.getHighestRatedSongs(10));
    }

    @Test
    public void getMostRatedSongs() throws TechnionTunes.UserAlreadyExists, TechnionTunes.SongAlreadyExists, TechnionTunes.UserDoesntExist, User.SongAlreadyRated, User.IllegalRateValue, TechnionTunes.SongDoesntExist {
        TechnionTunes tt = new TechnionTunesImpl();

        Collection<Song> expectedCollection1 = new Vector<Song>();

        Assert.assertEquals(expectedCollection1, tt.getMostRatedSongs(8));

        initUsers(tt);
        initSongs(tt);

        expectedCollection1.add(tt.getSong(37));
        expectedCollection1.add(tt.getSong(1000));
        expectedCollection1.add(tt.getSong(31));
        expectedCollection1.add(tt.getSong(170));
        expectedCollection1.add(tt.getSong(25));
        expectedCollection1.add(tt.getSong(22));
        expectedCollection1.add(tt.getSong(16));
        expectedCollection1.add(tt.getSong(13));

        Assert.assertEquals(expectedCollection1, tt.getMostRatedSongs(8));

        Collection<Song> expectedCollection2 = new Vector<Song>();



        tt.rateSong(1, 5, 10);
        tt.rateSong(1, 170, 8);
        tt.rateSong(1, 31, 8);
        tt.rateSong(1, 17, 0);
        tt.rateSong(1, 7, 0);


        tt.rateSong(18, 5, 10);
        tt.rateSong(18, 170, 8);
        tt.rateSong(18, 31, 8);

        tt.rateSong(11, 5, 10);
        tt.rateSong(11, 170, 8);
        tt.rateSong(11, 7, 8);
        tt.rateSong(11, 1000, 9);
        tt.rateSong(11, 37, 10);

        tt.rateSong(3, 5, 10);
        tt.rateSong(3, 7, 8);
        tt.rateSong(3, 1000, 6);
        tt.rateSong(3, 31, 6);
        tt.rateSong(3, 37, 10);
        tt.rateSong(3, 170, 10);

        tt.rateSong(31, 5, 10);
        tt.rateSong(31, 7, 8);
        tt.rateSong(31, 1000, 7);
        tt.rateSong(31, 37, 10);

        expectedCollection2.add(tt.getSong(5));
        expectedCollection2.add(tt.getSong(170));
        expectedCollection2.add(tt.getSong(7));
        expectedCollection2.add(tt.getSong(37));

        Assert.assertEquals(expectedCollection2, tt.getMostRatedSongs(4));

        expectedCollection2.add(tt.getSong(1000));
        expectedCollection2.add(tt.getSong(31));

        Assert.assertEquals(expectedCollection2, tt.getMostRatedSongs(6));

        expectedCollection2.add(tt.getSong(17));
        expectedCollection2.add(tt.getSong(25));
        expectedCollection2.add(tt.getSong(22));
        expectedCollection2.add(tt.getSong(16));

        Assert.assertEquals(expectedCollection2, tt.getMostRatedSongs(10));
    }

    @Test
    public void getTopLikers() throws TechnionTunes.UserAlreadyExists, TechnionTunes.SongAlreadyExists, TechnionTunes.UserDoesntExist, User.SongAlreadyRated, User.IllegalRateValue, TechnionTunes.SongDoesntExist {
        TechnionTunes tt = new TechnionTunesImpl();

        Collection<User> expectedCollection1 = new Vector<User>();

        Assert.assertEquals(expectedCollection1, tt.getTopLikers(8));

        initUsers(tt);
        initSongs(tt);

        expectedCollection1.add(tt.getUser(72));
        expectedCollection1.add(tt.getUser(1000));
        expectedCollection1.add(tt.getUser(1));
        expectedCollection1.add(tt.getUser(3));
        expectedCollection1.add(tt.getUser(31));
        expectedCollection1.add(tt.getUser(18));
        expectedCollection1.add(tt.getUser(19));
        expectedCollection1.add(tt.getUser(11));

        Assert.assertEquals(expectedCollection1, tt.getTopLikers(8));

        Collection<User> expectedCollection2 = new Vector<User>();

        tt.rateSong(1, 5, 10);
        tt.rateSong(1, 170, 8);
        tt.rateSong(1, 31, 8);
        tt.rateSong(1, 17, 10);
        tt.rateSong(1, 7, 10);


        tt.rateSong(18, 5, 10);
        tt.rateSong(18, 170, 10);
        tt.rateSong(18, 31, 10);
        tt.rateSong(18, 17, 10);
        tt.rateSong(18, 7, 10);

        tt.rateSong(11, 5, 10);
        tt.rateSong(11, 170, 8);
        tt.rateSong(11, 7, 8);
        tt.rateSong(11, 1000, 10);
        tt.rateSong(11, 37, 10);

        tt.rateSong(3, 5, 2);
        tt.rateSong(3, 7, 2);
        tt.rateSong(3, 1000, 2);
        tt.rateSong(3, 31, 2);

        tt.rateSong(31, 5, 2);
        tt.rateSong(31, 7, 2);
        tt.rateSong(31, 1000, 2);
        tt.rateSong(31, 37, 2);

        expectedCollection2.add(tt.getUser(18));
        expectedCollection2.add(tt.getUser(1));
        expectedCollection2.add(tt.getUser(11));
        Collection<User> expectedSh = tt.getTopLikers(3);
        Assert.assertEquals(expectedCollection2, tt.getTopLikers(3));

        expectedCollection2.add(tt.getUser(3));
        expectedCollection2.add(tt.getUser(31));

        Assert.assertEquals(expectedCollection2, tt.getTopLikers(5));

        expectedCollection2.add(tt.getUser(72));
        expectedCollection2.add(tt.getUser(1000));

        Assert.assertEquals(expectedCollection2, tt.getTopLikers(7));
    }

    @Test(expected = TechnionTunes.UserDoesntExist.class)
    public void canGetAlongExceptionTest() throws TechnionTunes.UserDoesntExist {
        TechnionTunes tt = new TechnionTunesImpl();

        tt.canGetAlong(1, 18);
    }

    @Test
    public void canGetAlongTest() throws TechnionTunes.UserDoesntExist, TechnionTunes.UserAlreadyExists, TechnionTunes.SongAlreadyExists, User.AlreadyFriends, User.SamePerson, User.IllegalRateValue, User.SongAlreadyRated, TechnionTunes.SongDoesntExist, InterruptedException {
        TechnionTunes tt = new TechnionTunesImpl();

        initUsers(tt);
        initSongs(tt);

        tt.makeFriends(31, 100);
        tt.makeFriends(11, 18);
        tt.makeFriends(1, 18);
        tt.makeFriends(3, 18);
        tt.makeFriends(11, 72);
        tt.makeFriends(72, 55);
        tt.makeFriends(55, 3);
        tt.makeFriends(100, 19);

        tt.rateSong(1, 5, 9);
        tt.rateSong(18, 5, 9);
        tt.rateSong(18, 7, 10);
        tt.rateSong(11, 7, 9);
        tt.rateSong(11, 170, 9);
        tt.rateSong(72, 170, 9);
        tt.rateSong(55, 170, 9);
        tt.rateSong(55, 25, 9);
        tt.rateSong(3, 25, 9);
        tt.rateSong(31, 13, 2);
        tt.rateSong(100, 13, 8);
        tt.rateSong(19, 13, 9);


        Assert.assertTrue(tt.canGetAlong(18, 55));
        Assert.assertFalse(tt.canGetAlong(18, 3));
        Assert.assertTrue(tt.canGetAlong(1, 3));
        Assert.assertTrue(tt.canGetAlong(18, 18));
        Assert.assertTrue(tt.canGetAlong(100, 19));
        Assert.assertFalse(tt.canGetAlong(18, 100));
        Assert.assertFalse(tt.canGetAlong(31, 19));
        Assert.assertFalse(tt.canGetAlong(31, 100));

        tt.rateSong(31, 7, 8);
        tt.rateSong(100, 7, 8);

        Assert.assertTrue(tt.canGetAlong(31, 100));
        Assert.assertTrue(tt.canGetAlong(31, 19));
    }

    @Test
    public void iteratorTest() throws Exception {
        TechnionTunes tt = new TechnionTunesImpl();

        initSongs(tt);
        initUsers(tt);

        for (Song song : tt) {
            tt.rateSong(18, song.getID(), 10);
        }

        Collection<Song> songResult = tt.sortSongs(Song::compareTo);

        songResult.forEach(song -> Assert.assertTrue(song.getAverageRating() == 10));
    }
}

