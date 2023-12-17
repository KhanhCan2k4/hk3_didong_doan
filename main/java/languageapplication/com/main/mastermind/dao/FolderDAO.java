package languageapplication.com.main.mastermind.dao;

import java.util.ArrayList;

import languageapplication.com.main.mastermind.database.Database;
import languageapplication.com.main.mastermind.models.Folder;
import languageapplication.com.main.mastermind.models.Word;

public class FolderDAO {
    public static ArrayList<Word> getWordsByFolderId(int id) {
        ArrayList<Word> words = new ArrayList<>();
        if(id == 0) {
            words = getFolderById(id).getWords();
        } else if(id >= 1 && id <= 5) {
            words = WordDAO.getWordsByLevel(id);
        }

        return words;
    }

    public static Folder getFolderById(int id) {
        for (Folder folder: Database.getFolders()) {
            if (folder.getId() == id) {
                return folder;
            }
        }

        return null;
    }

    public static boolean containsWordWithId(int id, int wordId) {
        for (Word word: getFolderById(id).getWords()) {
            if (word.getId() == wordId) {
                return true;
            }
        }

        return false;
    }
}
