package languageapplication.com.main.mastermind.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

import languageapplication.com.main.mastermind.config.Constains;

@Entity(tableName = Word.NAME_TABLE)
public class Word implements Serializable {
    @Ignore
    public final static String NAME_TABLE = "words";
    @Ignore
    public final static String ID = "id";
    @Ignore
    public final static String WORD = "word";
    @Ignore
    public final static String MEANING = "meaning";
    @Ignore
    public final static String FURIGANA = "furigana";
    @Ignore
    public final static String ROMAJI = "romaji";
    @Ignore
    public final static String LEVEL = "level";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    private int id;
    @ColumnInfo(name = WORD)
    private String word;
    @ColumnInfo(name = MEANING)
    private String meaning;
    @Ignore
    private ArrayList<String> meanings;
    @ColumnInfo(name = FURIGANA)
    private String furigana;
    @ColumnInfo(name = ROMAJI)
    private String romaji;
    @ColumnInfo(name = LEVEL)
    private int level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public ArrayList<String> getMeanings() {
        return meanings;
    }

    public String getMeaningsString() {
        String result = "";
        for (String meaning: meanings) {
            result += Constains.MEANING_CHAR_KEY + meaning +"\n";
        }

        return result;
    }

    public void setMeanings(String... meanings) {
        this.meanings.clear();
        for (String meaning : meanings) {
            this.meanings.add(meaning);
        }
    }

    public String getFurigana() {
        return furigana;
    }

    public void setFurigana(String furigana) {
        this.furigana = furigana;
    }

    public String getRomaji() {
        return romaji;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Word(int id, String word, String furigana, String romaji, int level) {
        this.id = id;
        this.word = word;
        this.meanings = new ArrayList<>();
        this.furigana = furigana;
        this.romaji = romaji;
        this.level = level;
    }

    public Word() {
        this.id = 0;
        this.word = "";
        this.meanings = new ArrayList<>();
        this.furigana = "";
        this.romaji = "";
        this.level = 0;
    }

    public void setWord(Word word) {
        this.id = word.id;
        this.word = word.word;
        this.meaning = word.meaning;
        this.furigana = word.furigana;
        this.romaji = word.romaji;
        this.level = word.level;
    }

    public String toString() {
        return word + Constains.MEANING_CHAR_KEY + furigana + Constains.MEANING_CHAR_KEY + romaji;
    }
}
