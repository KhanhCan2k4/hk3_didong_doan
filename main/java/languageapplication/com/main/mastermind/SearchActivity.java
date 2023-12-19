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

import java.util.ArrayList;

import languageapplication.com.main.mastermind.database.DatabaseManager;
import languageapplication.com.main.mastermind.databinding.SearchLayoutBinding;
import languageapplication.com.main.mastermind.models.Word;

public class SearchActivity extends AppCompatActivity {

    private SearchLayoutBinding searchLayoutBinding;
    private ArrayAdapter<Word> wordArrayAdapter;
    private ArrayList<Word> words;
    private DatabaseManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        searchLayoutBinding = SearchLayoutBinding.inflate(getLayoutInflater());
        setContentView(searchLayoutBinding.getRoot());

        manager = new DatabaseManager(this);
        manager.open();

        words = manager.getWordsByFolderId(5);

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

                words = manager.getWordsByKey(enteredKey.toString());

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
                Log.d("TAG", "onKey: "+enteredKey);
                int id = 0;
                if(words.size() > 0){
                    id = words.get(0).getId();
                }

                if (keyCode == KeyEvent.KEYCODE_ENTER) {

                    Intent intent = new Intent(SearchActivity.this, WordActivity.class);

                    intent.putExtra("key", enteredKey);
                    intent.putExtra("id", id);

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