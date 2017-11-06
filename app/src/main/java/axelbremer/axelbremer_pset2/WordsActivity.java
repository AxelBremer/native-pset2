package axelbremer.axelbremer_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class WordsActivity extends AppCompatActivity {

    Story story;
    Integer tot;
    Button wordButton;
    TextView wordTextView;
    EditText wordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        try {
            InputStream is = getAssets().open("madlib0_simple.txt");
            story = new Story(is);
            tot = story.getPlaceholderCount();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("STORY", "onCreate: " + story.toString());

        wordButton = findViewById(R.id.wordButton);
        wordTextView = findViewById(R.id.wordTextView);
        wordField = findViewById(R.id.wordField);

        nextWord();
    }

    private void nextWord() {
        String text;
        if(!story.isFilledIn()) {
            Integer num = story.getPlaceholderRemainingCount();
            text = num.toString() + " out of " + tot.toString() + " to go. ";
            text += "Please write a " + story.getNextPlaceholder() + ".";
            wordField.setText("");
        } else {
            text = "You're done! Press Next to see your story.";
            wordField.setVisibility(View.INVISIBLE);
        }

        wordTextView.setText(text);
    }

    public void wordButtonClicked(View view) {
        if(!story.isFilledIn()) {
            String word = wordField.getText().toString();

            story.fillInPlaceholder(word);

            nextWord();
        } else {
            Log.d("STORY", "finalStory: " + story.toString());
            Intent intent = new Intent(this, FinalActivity.class);
            intent.putExtra("Story", story.toString());
            startActivity(intent);
            finish();
        }
    }
}
