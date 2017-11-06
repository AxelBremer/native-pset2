package axelbremer.axelbremer_pset2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;

public class WordsActivity extends AppCompatActivity {

    Story story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        try {
            InputStream is = getAssets().open("madlib0_simple.txt");
            story = new Story(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
