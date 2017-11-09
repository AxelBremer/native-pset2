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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WordsActivity extends AppCompatActivity {

    public Story story;
    Button wordButton;
    TextView wordTextView;
    EditText wordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        Intent intent = getIntent();

        String title = intent.getStringExtra("Title");

        InputStream is = null;
        try {
            is = getAssets().open(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
        story = new Story(is);

        wordButton = findViewById(R.id.wordButton);
        wordTextView = findViewById(R.id.wordTextView);
        wordField = findViewById(R.id.wordField);

        nextWord();
    }

    private void nextWord() {
        String text;
        if(!story.isFilledIn()) {
            Integer num = story.getPlaceholderRemainingCount();
            text = num.toString() + " to go. ";
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

    /*
     * This function saves the state of the story in an outState bundle.
     */
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("Story", story);
    }

    /*
     * This function restores the state of the story from an inState bundle.
     */
    public void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);

        story = (Story) inState.getSerializable("Story");
        nextWord();
    }
}
