package languageapplication.com.main.mastermind.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import languageapplication.com.main.mastermind.models.Word;

public class DatabaseManager {
    public DatabaseHelper dbHelper;

    private Context context;

    public SQLiteDatabase database;

    public DatabaseManager(Context c) {
        context = c;
    }

    public DatabaseManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public ArrayList<Word> getWordsByFolderId(int id) {
        ArrayList<Word> words = new ArrayList<>();

        if (id == 0) {
            String sql = "SELECT * FROM " + DatabaseHelper.TB_2_NAME + " LIMIT 0, 30";
            ArrayList<Integer> ids = new ArrayList<>();

            Cursor cursor = database.rawQuery(sql, null);

            if (cursor != null) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Word word = getWordById(cursor.getInt(0));

                    words.add(word);
                    cursor.moveToNext();
                }
            }

            return  words;
        }

        String sql = "SELECT * FROM " + DatabaseHelper.TB_1_NAME + " WHERE " + DatabaseHelper.COL_6 +" = " + id;

        Cursor cursor = database.rawQuery(sql, null);

        Log.d("TAG", "getWordsByFolderId: query completed");

        int i =0;
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                i++;
                if(i > 30) {
                    break;
                }
                Word word = new Word();
                word.setId(cursor.getInt(0));
                word.setWord(cursor.getString(1));
                word.setMeaning(cursor.getString(2));
                word.setFurigana(cursor.getString(3));
                word.setRomaji(cursor.getString(4));
                word.setLevel(cursor.getInt(5));

                words.add(word);
                cursor.moveToNext();
            }
        }

        return words;
    }

    public Word getWordById(int id) {
        Word word = new Word();
        String sql = "SELECT * FROM " + DatabaseHelper.TB_1_NAME + " WHERE id = " + id;

        Cursor cursor = database.rawQuery(sql, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                word.setId(cursor.getInt(0));
                word.setWord(cursor.getString(1));
                word.setMeaning(cursor.getString(2));
                word.setFurigana(cursor.getString(3));
                word.setRomaji(cursor.getString(4));
                word.setLevel(cursor.getInt(5));
            }
        }

        return  word;
    }

    public ArrayList<Word> getWordsByKey(String key){
        ArrayList<Word> words = new ArrayList<>();
        String sql = "SELECT * FROM "+DatabaseHelper.TB_1_NAME+" WHERE "+DatabaseHelper.COL_2+" LIKE '%"+key+"%' OR "+DatabaseHelper.COL_3+" LIKE '%"+key+"%' OR "+DatabaseHelper.COL_4+" LIKE '%"+key+"%' OR "+DatabaseHelper.COL_5+" LIKE '%"+key+"%' LIMIT 0,30";

        Cursor cursor = database.rawQuery(sql, null);

        int i =0;
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                i++;
                if(i > 30) {
                    break;
                }
                Word word = new Word();
                word.setId(cursor.getInt(0));
                word.setWord(cursor.getString(1));
                word.setMeaning(cursor.getString(2));
                word.setFurigana(cursor.getString(3));
                word.setRomaji(cursor.getString(4));
                word.setLevel(cursor.getInt(5));

                words.add(word);
                cursor.moveToNext();
            }
        }


        return  words;
    }

    public boolean isFavourite(int id){
        String sql = "SELECT * FROM "+DatabaseHelper.TB_2_NAME+" WHERE "+DatabaseHelper.COL_1+" = " +id;

        Cursor cursor = database.rawQuery(sql,null);
        return cursor != null && cursor.moveToFirst();
    }

    public void setFavourite(int id) {
        String sql = "INSERT INTO " + DatabaseHelper.TB_2_NAME + " VALUES (" + id + ")";

        try {
            database.execSQL(sql);
        } catch (SQLException e) {
            //ignore
        }
    }

    public void deleteFavourite(int id) {
        String sql = "DELETE FROM " + DatabaseHelper.TB_2_NAME + " WHERE " + DatabaseHelper.COL_1 + " = " + id;

        try {
            database.execSQL(sql);
        } catch (SQLException e) {
            //ignore
        }
    }

    public int getFavouritesTotal() {
        try {
            String sql = "SELECT COUNT(*) FROM " + DatabaseHelper.TB_2_NAME;

            Cursor cursor = database.rawQuery(sql, null);

            if(cursor != null) {
                if(cursor.moveToFirst()) {
                    return cursor.getInt(0);
                }
            }
        }catch (SQLException e) {
            return 0;
        }
        return 0;
    }
}
