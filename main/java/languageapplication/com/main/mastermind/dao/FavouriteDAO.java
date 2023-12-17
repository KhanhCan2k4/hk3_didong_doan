package languageapplication.com.main.mastermind.dao;

import languageapplication.com.main.mastermind.database.Database;
import languageapplication.com.main.mastermind.models.Favourite;

public class FavouriteDAO {

    private static int id = 0;
    public static void insert(Integer wordId) {
        id++;
        Favourite favourite = new Favourite(id, wordId);

        Database.setFavourite(favourite);
    }
}
