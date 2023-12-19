package languageapplication.com.main.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import languageapplication.com.main.mastermind.config.Constains;
import languageapplication.com.main.mastermind.database.DatabaseManager;
import languageapplication.com.main.mastermind.databinding.ReviewWordLayoutBinding;
import languageapplication.com.main.mastermind.models.Folder;
import languageapplication.com.main.mastermind.models.Word;

public class ReviewWordActivity extends AppCompatActivity {

    private ReviewWordLayoutBinding reviewWordLayoutBinding;
    private DatabaseManager manager;
    private Folder folder;
    private int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        reviewWordLayoutBinding = ReviewWordLayoutBinding.inflate(getLayoutInflater());
        setContentView(reviewWordLayoutBinding.getRoot());

        manager = new DatabaseManager(this);
        manager.open();

        Intent intent = getIntent();
        int folderID = intent.getIntExtra("id",0);
        index = intent.getIntExtra("index",0);

        folder = Constains.getFolders().get(folderID);

        reviewWordLayoutBinding.txtFolderName.setText(folder.getName());
        updateWord();

        reviewWordLayoutBinding.btnChooseFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reviewWordLayoutBinding.btnChooseFav.getTag().equals("0")) {
                    reviewWordLayoutBinding.btnChooseFav.setImageResource(R.drawable.baseline_star_24);
                    reviewWordLayoutBinding.btnChooseFav.setTag("1");

                    manager.setFavourite(folder.getWords().get(index).getId());

                } else {
                    reviewWordLayoutBinding.btnChooseFav.setImageResource(R.drawable.baseline_star_outline_24);
                    reviewWordLayoutBinding.btnChooseFav.setTag("0");

                    manager.deleteFavourite(folder.getWords().get(index).getId());
                }
            }
        });


        reviewWordLayoutBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReviewWordActivity.this, ViewFolderActivity.class);

                if(folder.getId() != 0) {
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
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

                    manager.setFavourite(folder.getWords().get(index).getId());
                } else {
                    reviewWordLayoutBinding.btnChooseFav.setImageResource(R.drawable.baseline_star_outline_24);
                    reviewWordLayoutBinding.btnChooseFav.setTag("0");

                    manager.deleteFavourite(folder.getWords().get(index).getId());
                }
            }
        });
    }

    private void updateWord() {
        Word word = folder.getWords().get(index);

        if(word == null) {
            return;
        }

        if(manager.isFavourite(word.getId())) {
            reviewWordLayoutBinding.btnChooseFav.setImageResource(R.drawable.baseline_star_24);
            reviewWordLayoutBinding.btnChooseFav.setTag("1");
        }

        reviewWordLayoutBinding.txtWord.setText(word.getWord());
        reviewWordLayoutBinding.txtFurigana.setText(word.getFurigana());
        reviewWordLayoutBinding.txtRomaji.setText(word.getRomaji());

        switch (word.getLevel()) {
            case 1:
                reviewWordLayoutBinding.imgLevel.setImageResource(R.drawable.n1);
                break;
            case 2:
                reviewWordLayoutBinding.imgLevel.setImageResource(R.drawable.n2);
                break;
            case 3:
                reviewWordLayoutBinding.imgLevel.setImageResource(R.drawable.n3);
                break;
            case 4:
                reviewWordLayoutBinding.imgLevel.setImageResource(R.drawable.n4);
                break;
            case 5:
                reviewWordLayoutBinding.imgLevel.setImageResource(R.drawable.n5);
                break;
        }

        reviewWordLayoutBinding.txtMeaning.setText(word.getMeaning());
    }
}