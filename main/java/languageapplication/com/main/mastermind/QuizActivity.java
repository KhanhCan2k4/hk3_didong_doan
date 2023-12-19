package languageapplication.com.main.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import languageapplication.com.main.mastermind.config.Constains;
import languageapplication.com.main.mastermind.database.DatabaseManager;
import languageapplication.com.main.mastermind.databinding.QuizLayoutBinding;
import languageapplication.com.main.mastermind.models.Folder;

public class QuizActivity extends AppCompatActivity {

    private int index = 0;
    private Button correctButton;
    private int correctAmount = 0;

    private int correctAnswerIndex = 0;

    private Folder folder = Constains.getFolders().get(0);
    private ArrayList<Integer> questions;

    private DatabaseManager manager;

    private QuizLayoutBinding quizLayoutBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        quizLayoutBinding = QuizLayoutBinding.inflate(getLayoutInflater());
        setContentView(quizLayoutBinding.getRoot());

        manager = new DatabaseManager(this);
        manager.open();

        folder.setWords(manager.getWordsByFolderId(0));
        updateQuestion();

        quizLayoutBinding.txtFolderName.setText(folder.getName());

        quizLayoutBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizActivity.this, ViewFolderActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                startActivity(intent);
            }
        });

        quizLayoutBinding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index++;

                if(index == folder.getWords().size()) {
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);

                    intent.putExtra("correct", correctAmount);
                    intent.putExtra("total", folder.getWords().size());

                    startActivity(intent);
                    finish();
                } else {
                    updateQuestion();
                }

            }
        });

        chooseAnswer(quizLayoutBinding.btnA);
        chooseAnswer(quizLayoutBinding.btnB);
        chooseAnswer(quizLayoutBinding.btnC);
        chooseAnswer(quizLayoutBinding.btnD);
    }

    private void updateQuestion() {
        questions = new ArrayList<>();
        questions.add(0);
        questions.add(0);
        questions.add(0);
        questions.add(0);

        correctAnswerIndex = (int) (Math.random()* 4);
        questions.set(correctAnswerIndex, index);

        switch (correctAnswerIndex) {
            case 0:
                correctButton = quizLayoutBinding.btnA;
                break;
            case 1:
                correctButton = quizLayoutBinding.btnB;
                break;
            case 2:
                correctButton = quizLayoutBinding.btnC;
                break;
            case 3:
                correctButton = quizLayoutBinding.btnD;
                break;
        }

        int temp;
        for (int i = 0; i < 4; i++) {
            if (i != correctAnswerIndex) {
                do {
                    temp = (int)(Math.random()* folder.getWords().size());
                } while (questions.contains(temp));

                questions.set(i, temp);
            }
        }

        quizLayoutBinding.txtWord.setText(folder.getWords().get(index).getWord());
        quizLayoutBinding.btnA.setEnabled(true);
        quizLayoutBinding.btnB.setEnabled(true);
        quizLayoutBinding.btnC.setEnabled(true);
        quizLayoutBinding.btnD.setEnabled(true);
        quizLayoutBinding.btnA.setText(folder.getWords().get(questions.get(0)).getMeaning());
        quizLayoutBinding.btnB.setText(folder.getWords().get(questions.get(1)).getMeaning());
        quizLayoutBinding.btnC.setText(folder.getWords().get(questions.get(2)).getMeaning());
        quizLayoutBinding.btnD.setText(folder.getWords().get(questions.get(3)).getMeaning());
    }

    private void chooseAnswer(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(button == correctButton) {
                    correctAmount++;
                }

                quizLayoutBinding.btnA.setEnabled(false);
                quizLayoutBinding.btnB.setEnabled(false);
                quizLayoutBinding.btnC.setEnabled(false);
                quizLayoutBinding.btnD.setEnabled(false);

                correctButton.setEnabled(true);
            }
        });
    }

}