package jp.ac.meijou.android.s241205017;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s241205017.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.IdButtonButtonA.setOnClickListener(v -> {

            var intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            binding.IdTextviewNO1.setText("ボタンAが押されました");
        });

        binding.IdButtonButtonB.setOnClickListener(v -> {

            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://mahjongsoul.com/"));
            startActivity(intent);
            binding.IdTextviewNO1.setText("ボタンBが押されました");
        });

        binding.IdButtonSend.setOnClickListener(v -> {

            var send_message = binding.IdEditTextBlank.getText().toString();
            var intent = new Intent(this, MainActivity3.class);
            intent.putExtra("Message", send_message);
            startActivity(intent);
            binding.IdButtonSend.setText("送信済み");
        });


    }
}