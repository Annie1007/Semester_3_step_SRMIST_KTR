import java.util.Arrays;

public class Playlist {
    private final String[] songs; // Private internal storage
    private int songCount;

    public Playlist(int maxCapacity) {
        this.songs = new String[maxCapacity];
        this.songCount = 0;
    }

    public void addSong(String title) {
        if (songCount >= songs.length) {
            System.out.println("Playlist full: cannot add " + title);
            return;
        }
        songs[songCount] = title;
        songCount++;
    }

    // Defensive Copying: Hand back a clone of the active sub-array
    public String[] getSongs() {
        return Arrays.copyOf(songs, songCount);
    }

    public int getSongCount() {
        return this.songCount;
    }

    public static void main(String[] args) {
        Playlist p = new Playlist(10);
        p.addSong("Song A");
        p.addSong("Song B");

        String[] copy = p.getSongs();
        copy[0] = "Hacked"; // Attempting to alter returned array

        // Verify that internal state remained protected
        System.out.println("Playlist song 0 original: " + p.getSongs()[0]); // Output: "Song A"
        System.out.println("Song count: " + p.getSongCount());              // Output: 2
    }
}