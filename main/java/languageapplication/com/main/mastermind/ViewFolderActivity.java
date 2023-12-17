package languageapplication.com.main.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import languageapplication.com.main.mastermind.dao.FolderDAO;
import languageapplication.com.main.mastermind.database.Database;
import languageapplication.com.main.mastermind.databinding.ViewFolderLayoutBinding;
import languageapplication.com.main.mastermind.models.Favourite;
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

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        Folder folder = FolderDAO.getFolderById(id);

        viewFolderLayoutBinding.txtFolderName.setText(folder.getName());
        folder.setWords(FolderDAO.getWordsByFolderId(folder.getId()));

        wordArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, folder.getWords());
        viewFolderLayoutBinding.lvFolders.setAdapter(wordArrayAdapter);

        viewFolderLayoutBinding.lvFolders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ViewFolderActivity.this, ReviewWordActivity.class);
                intent.putExtra("index", i);
                intent.putExtra("id", folder.getId());

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
    }

}