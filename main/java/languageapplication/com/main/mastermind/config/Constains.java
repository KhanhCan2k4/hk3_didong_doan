package languageapplication.com.main.mastermind.config;

import android.app.Activity;

import java.util.ArrayList;

import languageapplication.com.main.mastermind.R;
import languageapplication.com.main.mastermind.dao.FolderDAO;
import languageapplication.com.main.mastermind.models.Folder;

public class Constains {
    public static final int COUNT_DOWN_TIME = 3000;
    public static final String MEANING_CHAR_KEY = " * ";


    public static ArrayList<Folder> getFolders() {
        ArrayList<Folder> folders = new ArrayList<>();

        Folder folder0 = new Folder(0,"My favorite");
        Folder folder5 = new Folder(5,"N5 - Basic");
        Folder folder4 = new Folder(4,"N4 - Pre-Intermidiate");
        Folder folder3 = new Folder(3,"N3 - Intermidiate");
        Folder folder2 = new Folder(2,"N2 - Pre-Advanced");
        Folder folder1 = new Folder(1,"N1 - Advanced");

        folders.add(folder0);
        folders.add(folder1);
        folders.add(folder2);
        folders.add(folder3);
        folders.add(folder4);
        folders.add(folder5);

        return folders;
    }


}
