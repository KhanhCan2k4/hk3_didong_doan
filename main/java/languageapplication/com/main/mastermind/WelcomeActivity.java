package languageapplication.com.main.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import languageapplication.com.main.mastermind.config.Constains;
import languageapplication.com.main.mastermind.database.MyDatabaseAPIs;
import languageapplication.com.main.mastermind.databinding.WelcomeLayoutBinding;
import languageapplication.com.main.mastermind.models.Word;

public class WelcomeActivity extends AppCompatActivity {

    private WelcomeLayoutBinding welcomeLayoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        welcomeLayoutBinding = WelcomeLayoutBinding.inflate(getLayoutInflater());
        setContentView(welcomeLayoutBinding.getRoot());

        //chạy 3s để chuyển đến Main Activity
//        new CountDownTimer(Constains.COUNT_DOWN_TIME, 1000){
//            @Override
//            public void onTick(long l) {
//
//            }
//
//            @Override
//            public void onFinish() {
//                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
//                finish();
//            }
//        }.start();

        //build database
        MyDatabaseAPIs databaseAPIs = new MyDatabaseAPIs(this);
        Log.d("TAG", "onCreate: " + databaseAPIs.toString());

        Word word = new Word();
        word.setId(123);
        databaseAPIs.getWordById(word);

        Log.d("TAG", "onCreate: " + word.toString());

    }
}