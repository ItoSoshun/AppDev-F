package jp.ac.meijou.android.s241205017;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Optional;

import jp.ac.meijou.android.s241205017.databinding.ActivityMain4Binding;
import jp.ac.meijou.android.s241205017.databinding.ActivityMain5Binding;
import jp.ac.meijou.android.s241205017.databinding.ActivityMainBinding;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity5 extends AppCompatActivity {

    private ActivityMain5Binding binding;

    private final OkHttpClient okHttpClient = new OkHttpClient();
    //private final Moshi moshi = new Moshi.Builder().build();private final JsonAdapter<Gist> gistJsonAdapter = moshi.adapter(Gist.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMain5Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        var request = new Request.Builder()
                .url("https://placehold.jp/256x256.png")
                .build();


        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                var bitmap = BitmapFactory.decodeStream(response.body().byteStream());




                // UIスレッド以外で更新するとクラッシュするので、UIスレッド上で実行させる
                runOnUiThread(() -> binding.IdImageviewPlacehold.setImageBitmap(bitmap));
            }
        });

        binding.IdButtonPlacehold.setOnClickListener(view -> {
            var text = binding.IdEdittextPlacehold.getText().toString();

            // textパラメータをつけたURLの作成
            var url = Uri.parse("https://placehold.jp/ff0000/ffffff/256x256.png?text=%E4%BD%95%E3%81%8B%E5%85%A5%E5%8A%9B%E3%81%97%E3%81%A6%E3%81%8F%E3%81%A0%E3%81%95%E3%81%84")
                    .buildUpon()
                    .appendQueryParameter("text", text)
                    .build()
                    .toString();
            getImages(url);
        });
    }

    private void getImages(String url) {
        // リクエスト先に画像URLを指定
        var request = new Request.Builder()
                .url(url)
                .build();

        // 非同期通信でリクエスト
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // 通信に失敗した時に呼ばれる
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                // InputStreamをBitmapに変換
                var bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                // UIスレッド以外で更新するとクラッシュするので、UIスレッド上で実行させる
                runOnUiThread(() -> binding.IdImageviewPlacehold.setImageBitmap(bitmap));
            }
        });
    }
}