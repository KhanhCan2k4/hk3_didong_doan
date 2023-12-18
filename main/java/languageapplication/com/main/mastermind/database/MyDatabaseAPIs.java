package languageapplication.com.main.mastermind.database;

import android.content.Context;

import languageapplication.com.main.mastermind.dao.FolderDAO;
import languageapplication.com.main.mastermind.dao.WordDAO;
import languageapplication.com.main.mastermind.models.Word;

public class MyDatabaseAPIs {
    public final int GET_WORD_BY_ID_DONE = 1;
    private FolderDAO folderDAO;
    private WordDAO wordDAO;

    private CompleteListener completeListener;

    public interface  CompleteListener {
        public void notifyToActivity(int notifcationID);
    }

    public void setCompleteListener(CompleteListener completeListener) {
        this.completeListener = completeListener;
    }

    public MyDatabaseAPIs(Context context) {
        folderDAO = MyDatabase.getDatabase(context).getFolderDAO();
        wordDAO = MyDatabase.getDatabase(context).getWordDAO();
    }

    public void getWordById(Word word) {
        final int id = word.getId();
        MyDatabase.dbReadWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
               Word myWord = wordDAO.getWordById(id);
                if (myWord != null) {
                    word.setWord(myWord);
                    if (completeListener != null) {
                        completeListener.notifyToActivity(GET_WORD_BY_ID_DONE);
                    }
                }
            }
        });
    }
}
