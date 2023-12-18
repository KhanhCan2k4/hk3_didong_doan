package languageapplication.com.main.mastermind.dao;

import androidx.room.Dao;
import androidx.room.Query;

import languageapplication.com.main.mastermind.models.Word;

@Dao
public interface WordDAO {
    @Query("SELECT * FROM " + Word.NAME_TABLE + " WHERE :id")
    public Word getWordById(int id);
}
