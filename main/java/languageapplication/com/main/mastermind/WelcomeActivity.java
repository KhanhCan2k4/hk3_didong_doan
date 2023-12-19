package languageapplication.com.main.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import languageapplication.com.main.mastermind.config.Constains;
import languageapplication.com.main.mastermind.database.DatabaseHelper;
import languageapplication.com.main.mastermind.database.DatabaseManager;
import languageapplication.com.main.mastermind.databinding.WelcomeLayoutBinding;

public class WelcomeActivity extends AppCompatActivity {

    private WelcomeLayoutBinding welcomeLayoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        welcomeLayoutBinding = WelcomeLayoutBinding.inflate(getLayoutInflater());
        setContentView(welcomeLayoutBinding.getRoot());

        DatabaseManager manager = new DatabaseManager(this);
        manager.open();

        CountDownTimer countDownTimer = new CountDownTimer(Constains.COUNT_DOWN_TIME, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
//                finish();
            }
        }.start();

    }
}