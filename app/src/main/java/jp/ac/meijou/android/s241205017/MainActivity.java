package jp.ac.meijou.android.s241205017;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;
import jp.ac.meijou.android.s241205017.databinding.ActivityMainBinding;
import android.text.Editable;
import android.text.TextWatcher;
import jp.ac.meijou.android.s241205017.PrefDataStore;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PrefDataStore prefDataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.button.setOnClickListener(v -> {
            var text2 = binding.editTextText.getText().toString();
            binding.id017.setText(text2);
        });



        prefDataStore = PrefDataStore.getInstance(this);

        //prefDataStore.getString("name").ifPresent(name -> binding.id017.setText(name));

        binding.button.setOnClickListener(v -> {
            var text2 = binding.editTextText.getText().toString();
            binding.id017.setText(text2);
        });

        binding.saveButton.setOnClickListener(v -> {
            var text2 = binding.editTextText.getText().toString();
            prefDataStore.setString("name", text2);
        });
        }

    protected void onStart() {
        super.onStart();
        prefDataStore.getString("name").ifPresent(name -> binding.id017.setText(name));
    }






}