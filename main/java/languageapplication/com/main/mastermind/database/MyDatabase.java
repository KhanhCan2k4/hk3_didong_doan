package languageapplication.com.main.mastermind.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import languageapplication.com.main.mastermind.dao.FolderDAO;
import languageapplication.com.main.mastermind.dao.WordDAO;
import languageapplication.com.main.mastermind.models.Folder;
import languageapplication.com.main.mastermind.models.Word;

@Database(entities = {Word.class, Folder.class}, version = MyDatabase.DB_VERSION)
public abstract class MyDatabase extends RoomDatabase {
    public final static String DB_NAME = "jlptmastermind_db";
    public final static int DB_VERSION = 1;

    private static volatile MyDatabase database;

    private static final int THREAD_NUMBER = 10;

    public static final ExecutorService dbReadWriteExecutor = Executors.newFixedThreadPool(THREAD_NUMBER);

    public abstract FolderDAO getFolderDAO();
    public abstract WordDAO getWordDAO();

    public static MyDatabase getDatabase(Context context) {
        if(database == null){
            synchronized (MyDatabase.class){
                database = Room
                        .databaseBuilder(context, MyDatabase.class, DB_NAME)
                        .createFromAsset(MyDatabase.DB_NAME)
                        .build();
            }
        }
        return database;
    }
}
