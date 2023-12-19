package languageapplication.com.main.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import languageapplication.com.main.mastermind.config.Constains;
import languageapplication.com.main.mastermind.database.DatabaseManager;
import languageapplication.com.main.mastermind.databinding.ViewFolderLayoutBinding;
import languageapplication.com.main.mastermind.models.Folder;
import languageapplication.com.main.mastermind.models.Word;

public class ViewFolderActivity extends AppCompatActivity {

    private ViewFolderLayoutBinding viewFolderLayoutBinding;

    private ArrayAdapter<Word> wordArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewFolderLayoutBinding = ViewFolderLayoutBinding.inflate(getLayoutInflater());
        setContentView(viewFolderLayoutBinding.getRoot());

        DatabaseManager manager = new DatabaseManager(this);
        manager.open();

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        Folder folder = Constains.getFolders().get(id);
        folder.setWords( manager.getWordsByFolderId(id));

        viewFolderLayoutBinding.txtFolderName.setText(folder.getName());

        if (id == 0 && folder.getWords().size() >= 5) {
            viewFolderLayoutBinding.btnQuiz.setVisibility(View.VISIBLE);
        } else {
            viewFolderLayoutBinding.btnQuiz.setVisibility(View.GONE);
        }

        wordArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, folder.getWords());
        viewFolderLayoutBinding.lvFolders.setAdapter(wordArrayAdapter);

        viewFolderLayoutBinding.lvFolders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ViewFolderActivity.this, ReviewWordActivity.class);
                intent.putExtra("id", folder.getId());
                intent.putExtra("index", i);

                startActivity(intent);
            }
        });

        viewFolderLayoutBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewFolderActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                startActivity(intent);

                finish();
            }
        });

        viewFolderLayoutBinding.btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewFolderActivity.this, QuizActivity.class);

                startActivity(intent);
            }
        });

    }

}