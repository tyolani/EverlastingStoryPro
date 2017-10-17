package com.example.tyolani.everlastingstorypro;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.tyolani.everlastingstorypro.R.string.bookView_mockup_bookText;
import static com.example.tyolani.everlastingstorypro.R.string.contribution_addTextChapter;
import static com.example.tyolani.everlastingstorypro.R.string.contribution_addTextParagraph;


public class ContributionActivity extends AppCompatActivity {

    private String action;
    private String chapterBeforeOrAfter;
    private int chapterPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String extraChapter = getIntent().getExtras().getString("addChapter");
        int extraChapter2 = getIntent().getExtras().getInt("position");
        int extraParagraph = getIntent().getExtras().getInt("addParagraph");

        if (extraChapter != null) {
            action = "addChapter";
            chapterBeforeOrAfter = extraChapter;
            chapterPosition = extraChapter2;
        }else if (extraParagraph >= 0) {
            action = "addParagraph";
            chapterPosition = extraParagraph;
        }

        if(action.equals("addChapter")) {
            setContentView(R.layout.contribution_add_chapter);


            EditText editTextChapter = (EditText) findViewById(R.id.et_contribution_add_chapter_title);
            editTextChapter.setTypeface(editTextChapter.getTypeface(), Typeface.BOLD);

            if (chapterBeforeOrAfter.equals("after")) {
                chapterPosition = chapterPosition + 1;
                editTextChapter.append("Chapter" + (chapterPosition + 1) + ": ");
            } else {
                editTextChapter.append("Chapter" + (chapterPosition + 1) + ": ");
            }

            EditText editTextContent = (EditText) findViewById(R.id.et_contribution_add_chapter_new_content);
            editTextContent.append(getString(contribution_addTextChapter));

        }else if(action.equals("addParagraph")) {
            setContentView(R.layout.contribution_add_paragraph);

            //get right chapter and load it
            TextView textViewParagraphTitle = (TextView) findViewById(R.id.tv_contribution_add_paragraph_title);
            //todo retrieve chapter title from database according to position
            textViewParagraphTitle.setText("Chapter" + (chapterPosition + 1) + ": ");
            textViewParagraphTitle.setTypeface(textViewParagraphTitle.getTypeface(), Typeface.BOLD);

            //todo retrieve chapter content from database according to position
            TextView textViewParagraphContent = (TextView) findViewById(R.id.tv_contribution_add_paragraph_content);
            textViewParagraphContent.setText(getString(bookView_mockup_bookText));
            //textViewParagraphContent.setText("all the paragraphs shown here");
            //Todo long text in  textViewParagraphContent is causing edittext below not to show up (just the keyboard)...
            textViewParagraphContent.setMovementMethod(new ScrollingMovementMethod());


            //new paragraph
            EditText editTextNewParagraph = (EditText) findViewById(R.id.et_contribution_add_paragraph_new_content);
            editTextNewParagraph.append(getString(contribution_addTextParagraph));
            editTextNewParagraph.setMovementMethod(new ScrollingMovementMethod());


        }else if (action.equals("addImage")) {
            //setContentView(R.layout.contribution_add_image);
        }

    }
}
