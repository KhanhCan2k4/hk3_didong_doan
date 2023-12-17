package languageapplication.com.main.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import languageapplication.com.main.mastermind.dao.WordDAO;
import languageapplication.com.main.mastermind.database.Database;
import languageapplication.com.main.mastermind.databinding.SearchLayoutBinding;
import languageapplication.com.main.mastermind.models.Word;

public class SearchActivity extends AppCompatActivity {

    private SearchLayoutBinding searchLayoutBinding;
    private ArrayAdapter<Word> wordArrayAdapter;
    private ArrayList<Word> words;

    private static class Temp {
        public static boolean isEntered;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        searchLayoutBinding = SearchLayoutBinding.inflate(getLayoutInflater());
        setContentView(searchLayoutBinding.getRoot());

        words = Database.getWords();

        wordArrayAdapter = new ArrayAdapter<Word>(this, android.R.layout.simple_list_item_1, words);
        searchLayoutBinding.lvSearchWords.setAdapter(wordArrayAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        searchLayoutBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                startActivity(intent);
            }
        });

        searchLayoutBinding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence enteredKey, int i, int i1, int i2) {
                words = WordDAO.getWordsByKey(enteredKey.toString());

                Log.d("TAG", "onTextChanged: " + words.size());

                wordArrayAdapter = new ArrayAdapter<>(SearchActivity.this, android.R.layout.simple_list_item_1, words);
                searchLayoutBinding.lvSearchWords.setAdapter(wordArrayAdapter);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        searchLayoutBinding.edtSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                String enteredKey = searchLayoutBinding.edtSearch.getText().toString();
                int id = WordDAO.getIdByKey(enteredKey);

                if (keyCode == KeyEvent.KEYCODE_ENTER) {

                    Intent intent = new Intent(SearchActivity.this, WordActivity.class);

                    intent.putExtra("key", enteredKey);
                    intent.putExtra("id", id);

                    Log.d("TAG", "onKey: enter");

                    startActivity(intent);

                    return true;
                }

                return false;
            }
        });

        searchLayoutBinding.lvSearchWords.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SearchActivity.this, WordActivity.class);

                intent.putExtra("key", words.get(i).getWord());
                intent.putExtra("id", words.get(i).getId());

                startActivity(intent);
            }
        });
    }
}