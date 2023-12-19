package languageapplication.com.main.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import languageapplication.com.main.mastermind.databinding.ResultLayoutBinding;

public class ResultActivity extends AppCompatActivity {

    private ResultLayoutBinding resultLayoutBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        resultLayoutBinding = ResultLayoutBinding.inflate(getLayoutInflater());
        setContentView(resultLayoutBinding.getRoot());

        Intent intent = getIntent();
        int corrects = intent.getIntExtra("correct", 0);
        int total = intent.getIntExtra("total", 0);

        int point =corrects*100/total;
        String status = "Correct: " + corrects +"/" + total;

        resultLayoutBinding.txtPoint.setText(point+"");
        resultLayoutBinding.txtStatus.setText(status);
    }
}