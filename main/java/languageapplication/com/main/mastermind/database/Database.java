package languageapplication.com.main.mastermind.database;

import java.util.ArrayList;

import languageapplication.com.main.mastermind.config.Constains;
import languageapplication.com.main.mastermind.dao.WordDAO;
import languageapplication.com.main.mastermind.models.Favourite;
import languageapplication.com.main.mastermind.models.Folder;
import languageapplication.com.main.mastermind.models.Word;

public class Database {
    private static ArrayList<Word> words;

    private static ArrayList<Favourite> favourites;

    private static ArrayList<Folder> folders;

    public static ArrayList<Folder> getFolders() {
        if(folders == null) {
            folders = Constains.getFolders();
        }

        return folders;
    }

    public static ArrayList<Word> getWords() {
        if (words == null) {
            words = new ArrayList<>();

            Word word1 = new Word(1, "鞄", "かばん", "kaban",5);
            word1.setMeanings("Tui", "Cap");
            words.add(word1);

            Word word2 = new Word(2, "漢字", "かんじ", "kanji",2);
            word2.setMeanings("Han tu");
            words.add(word2);

            Word word3 = new Word(3, "家", "いえ", "ie",4);
            word3.setMeanings("Nha cua");
            words.add(word3);

            Word word4 = new Word(4, "私", "わたし", "watashi",1);
            word4.setMeanings("Toi", "Tao", "To", "Minh");
            words.add(word4);

            Word word5 = new Word(5, "夫", "おっと", "otto",3);
            word5.setMeanings("Chong", "Phu quan");
            words.add(word5);

            Word word6 = new Word(6, "夫", "おっと", "otto",3);
            word6.setMeanings("Chong", "Phu quan");
            words.add(word6);
        }

        return words;
    }

    public static ArrayList<Favourite> getFavourites() {
        if (favourites == null) {
            //get from database
            favourites = new ArrayList<>();
        }

        return favourites;
    }

    public static ArrayList<Word> getFavouriteWords() {
        ArrayList<Word> favouriteWords = new ArrayList<>();
        if (favourites != null) {
            //get from database
            for (Favourite favourite: favourites) {
                Word word = WordDAO.getWordById(favourite.getWordId());
                favouriteWords.add(word);
            }
        }

        return  favouriteWords;
    }

    public static void setFavourite(Favourite favourite) {
        if (favourites == null) {
            favourites = new ArrayList<>();
        }

        favourites.add(favourite);
    }
}
