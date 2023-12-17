package languageapplication.com.main.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import languageapplication.com.main.mastermind.dao.FolderDAO;
import languageapplication.com.main.mastermind.dao.WordDAO;
import languageapplication.com.main.mastermind.databinding.ReviewWordLayoutBinding;
import languageapplication.com.main.mastermind.models.Folder;
import languageapplication.com.main.mastermind.models.Word;

public class ReviewWordActivity extends AppCompatActivity {

    private ReviewWordLayoutBinding reviewWordLayoutBinding;
    private int index;
    private Folder folder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        reviewWordLayoutBinding = ReviewWordLayoutBinding.inflate(getLayoutInflater());
        setContentView(reviewWordLayoutBinding.getRoot());

        Intent intent = getIntent();
        folder = FolderDAO.getFolderById(intent.getIntExtra("id", -1));
        index = intent.getIntExtra("index", 0);

        updateWord();

        if(FolderDAO.containsWordWithId(0, folder.getWords().get(index).getId())) {
            reviewWordLayoutBinding.btnChooseFav.setImageResource(R.drawable.baseline_star_24);
            reviewWordLayoutBinding.btnChooseFav.setTag("1");
        }

        reviewWordLayoutBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReviewWordActivity.this, ViewFolderActivity.class);

                if(folder.getId() != 0) {
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                }

                //lưu lại trạng thái lưu từ vựng yêu thích
                if (reviewWordLayoutBinding.btnChooseFav.getTag().equals("1")) {
                    FolderDAO.getFolderById(0).setWords(folder.getWords().get(index));
                } else {
                    FolderDAO.getFolderById(0).getWords().remove(folder.getWords().get(index));
                }

                startActivity(intent);

                finish();
            }
        });

        reviewWordLayoutBinding.btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index--;
                if(index == -1) {
                    index = folder.getWords().size() -1;
                }
                updateWord();
            }
        });

        reviewWordLayoutBinding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index++;
                if(index == folder.getWords().size()) {
                    index = 0;
                }
                updateWord();
            }
        });

        reviewWordLayoutBinding.btnChooseFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reviewWordLayoutBinding.btnChooseFav.getTag().equals("0")) {
                    reviewWordLayoutBinding.btnChooseFav.setImageResource(R.drawable.baseline_star_24);
                    reviewWordLayoutBinding.btnChooseFav.setTag("1");
                } else {
                    reviewWordLayoutBinding.btnChooseFav.setImageResource(R.drawable.baseline_star_outline_24);
                    reviewWordLayoutBinding.btnChooseFav.setTag("0");
                }
            }
        });


    }

    private void updateWord() {
        Word word = folder.getWords().get(index);

        if(word == null) {
            return;
        }

        reviewWordLayoutBinding.txtFolderName.setText(folder.getName());
        reviewWordLayoutBinding.txtWord.setText(word.getWord());
    }
}