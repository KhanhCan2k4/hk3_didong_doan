package languageapplication.com.main.mastermind.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = Folder.NAME_TABLE)
public class Folder {
    @Ignore
    public final static String NAME_TABLE = "favourites";
    @Ignore
    public final static String ID = "word_id";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    private int id;

    @Ignore
    private String name;
    @Ignore
    private ArrayList<Word> words;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }

    public void setWords(Word... words) {
        for (Word word: words) {
            this.words.add(word);
        }
    }

    public Folder(int id, String name) {
        this.id = id;
        this.name = name;
        this.words = new ArrayList<>();
    }

    public Folder() {
        this.id = -1;
        this.name = "";
        this.words = new ArrayList<>();
    }
}
