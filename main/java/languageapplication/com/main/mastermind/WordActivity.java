package languageapplication.com.main.mastermind;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import languageapplication.com.main.mastermind.config.Constains;
import languageapplication.com.main.mastermind.dao.FolderDAO;
import languageapplication.com.main.mastermind.dao.WordDAO;
import languageapplication.com.main.mastermind.database.Database;
import languageapplication.com.main.mastermind.databinding.FalseLayoutBinding;
import languageapplication.com.main.mastermind.databinding.WordLayoutBinding;
import languageapplication.com.main.mastermind.models.Word;

public class WordActivity extends AppCompatActivity {

    private WordLayoutBinding wordLayoutBinding;
    private FalseLayoutBinding falseLayoutBinding;
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        String key = intent.getStringExtra("key");
        int id = intent.getIntExtra("id", -1);
        Word word = WordDAO.getWordById(id);

        if(id == -1) {
            falseLayoutBinding = FalseLayoutBinding.inflate(getLayoutInflater());
            setContentView(falseLayoutBinding.getRoot());

            falseLayoutBinding.btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(WordActivity.this, SearchActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                    startActivity(intent);
                    finish();
                }
            });
        } else {
            wordLayoutBinding = WordLayoutBinding.inflate(getLayoutInflater());
            setContentView(wordLayoutBinding.getRoot());

            //gắn dữ liệu lên layout
            wordLayoutBinding.txtSearch.setText(key);

            Log.d("TAG", "onCreate: folders size" + Constains.getFolders().size());

            if(FolderDAO.containsWordWithId(0, id)) {
                wordLayoutBinding.btnChooseFav.setImageResource(R.drawable.baseline_star_24);
                wordLayoutBinding.btnChooseFav.setTag("1");
            }

            wordLayoutBinding.btnChooseFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (wordLayoutBinding.btnChooseFav.getTag().equals("0")) {
                        wordLayoutBinding.btnChooseFav.setImageResource(R.drawable.baseline_star_24);
                        wordLayoutBinding.btnChooseFav.setTag("1");
                    } else {
                        wordLayoutBinding.btnChooseFav.setImageResource(R.drawable.baseline_star_outline_24);
                        wordLayoutBinding.btnChooseFav.setTag("0");
                    }
                }
            });

            wordLayoutBinding.txtWord.setText(word.getWord());
            wordLayoutBinding.txtFurigana.setText(word.getFurigana());
            wordLayoutBinding.txtRomaji.setText(word.getRomaji());

            switch (word.getLevel()) {
                case 1:
                    wordLayoutBinding.imgLevel.setImageResource(R.drawable.n1);
                    break;
                case 2:
                    wordLayoutBinding.imgLevel.setImageResource(R.drawable.n2);
                    break;
                case 3:
                    wordLayoutBinding.imgLevel.setImageResource(R.drawable.n3);
                    break;
                case 4:
                    wordLayoutBinding.imgLevel.setImageResource(R.drawable.n4);
                    break;
                case 5:
                    wordLayoutBinding.imgLevel.setImageResource(R.drawable.n5);
                    break;
            }

            wordLayoutBinding.txtMeaning.setText(word.getMeaningsString());

            wordLayoutBinding.btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(WordActivity.this, SearchActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                    //lưu lại trạng thái lưu từ vựng yêu thích
                    if (wordLayoutBinding.btnChooseFav.getTag().equals("1")) {
                        FolderDAO.getFolderById(0).setWords(word);
                    }

                    startActivity(intent);
                }
            });


        }



    }
}