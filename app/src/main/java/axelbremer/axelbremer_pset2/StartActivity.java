package axelbremer.axelbremer_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void goToNextActivity(View view) {
        String[] assetList = {"madlib0_simple.txt", "madlib1_tarzan.txt", "madlib2_university.txt", "madlib3_clothes.txt", "madlib4_dance.txt"};
        Random rn = new Random();


        Integer i = rn.nextInt(5);

        Intent intent = new Intent(this, WordsActivity.class);
        intent.putExtra("Title", assetList[i]);
        startActivity(intent);
    }
}
