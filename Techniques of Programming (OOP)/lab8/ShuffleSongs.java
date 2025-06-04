//**************************************************************************
// ShuffleSongs.java
// Author: Connor Dailey
// Date: 10/28/2021
// Class: CSCI 131
// Purpose: Shuffle the songs in a playlist.
//**************************************************************************

public class ShuffleSongs {

    public static void main(String[] args) {
        String[] songs = Playlist.readFromFile("songs.txt");

        Playlist.print(songs);
    }
}
