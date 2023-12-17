package languageapplication.com.main.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import languageapplication.com.main.mastermind.databinding.MainLayoutBinding;

public class MainActivity extends AppCompatActivity {

    private MainLayoutBinding mainLayoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainLayoutBinding = MainLayoutBinding.inflate(getLayoutInflater());
        setContentView(mainLayoutBinding.getRoot());

        mainLayoutBinding.btnFFav.findViewById(R.id.btnFFav);
        mainLayoutBinding.btnFN1.findViewById(R.id.btnFN1);
        mainLayoutBinding.btnFN2.findViewById(R.id.btnFN2);
        mainLayoutBinding.btnFN3.findViewById(R.id.btnFN3);
        mainLayoutBinding.btnFN4.findViewById(R.id.btnFN4);
        mainLayoutBinding.btnFN5.findViewById(R.id.btnFN5);
    }

    @Override
    protected void onResume() {
        super.onResume();

        viewFolder(mainLayoutBinding.btnFFav, 0);
        viewFolder(mainLayoutBinding.btnFN1, 1);
        viewFolder(mainLayoutBinding.btnFN2, 2);
        viewFolder(mainLayoutBinding.btnFN3, 3);
        viewFolder(mainLayoutBinding.btnFN4, 4);
        viewFolder(mainLayoutBinding.btnFN5, 5);

        //nhấn nút tìm kiếm btnSearch để thực hiện tìm kiếm
        mainLayoutBinding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                startActivity(intent);
            }
        });
    }

    /**
     * đi đến folder tương ứng với button được nhấn
     * chuyển id tương ứng với thư mục
     * @param button
     * @param id
     */
    private void viewFolder(Button button, int id) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewFolderActivity.class);
                intent.putExtra("id", id);

                startActivity(intent);
            }
        });
    }
}