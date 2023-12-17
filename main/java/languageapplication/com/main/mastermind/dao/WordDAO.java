package languageapplication.com.main.mastermind.dao;

import android.util.Log;

import java.util.ArrayList;

import languageapplication.com.main.mastermind.database.Database;
import languageapplication.com.main.mastermind.models.Word;

public class WordDAO {

    public static Word getWordById(int id) {
        for (Word word: Database.getWords()) {
            if(word.getId() == id) {
                return word;
            }
        }

        return null;
    }

    public static Word getWordByKey(String key) {
        for (Word word: Database.getWords()) {
            if(word.getWord().equalsIgnoreCase(key) || word.getFurigana().equalsIgnoreCase(key) || word.getMeanings().contains(key) || word.getRomaji().equalsIgnoreCase(key)) {
                return word;
            }
        }

        return null;
    }

    public static ArrayList<Word> getWordsByKey(String key) {
        ArrayList<Word> words = new ArrayList<>();
        for (Word word: Database.getWords()) {
            if(word.getWord().equalsIgnoreCase(key) || word.getFurigana().equalsIgnoreCase(key) || word.getMeanings().contains(key) || word.getRomaji().equalsIgnoreCase(key)) {
                words.add(word);
            }
        }

        return words;
    }

    public static int getIdByKey(String key) {
        for (Word word: Database.getWords()) {
            if(word.getWord().equalsIgnoreCase(key) || word.getFurigana().equalsIgnoreCase(key) || word.getMeanings().contains(key) || word.getRomaji().equalsIgnoreCase(key)) {
                return word.getId();
            }
        }

        return -1;
    }

    public static ArrayList<Word> getWordsByLevel(int level) {
        ArrayList<Word> words = new ArrayList<>();

        if (level >= 1 && level <= 5) {
            for (Word word: Database.getWords()) {
                if(word.getLevel() == level) {
                    words.add(word);
                }
            }
        }

        return words;
    }
}
