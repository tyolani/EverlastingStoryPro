package com.example.tyolani.everlastingstorypro;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;


import java.util.ArrayList;

import static com.example.tyolani.everlastingstorypro.R.string.bookView_mockup_bookText;

public class ContributionActivity extends AppCompatActivity {

    private String action;
    private String chapterAction;
    private int chapterPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String extra = getIntent().getExtras().getString("addChapter");
        int extra2 = getIntent().getExtras().getInt("position");

        if(extra != null){
            action = "addChapter";
            chapterAction = extra;
            chapterPosition = extra2;
            Log.d("BUNDLEEEE", extra + extra2);
        }

        if(action.equals("addChapter")) {
            setContentView(R.layout.contribution_add_chapter);

            //save new title
            EditText editTextChapter = (EditText) findViewById(R.id.et_contribution_add_chapter_title);
            editTextChapter.setTypeface(editTextChapter.getTypeface(), Typeface.BOLD);

            if(chapterAction.equals("after")){
                chapterPosition = chapterPosition + 1;
                Log.d("pos", chapterPosition + " ");
                editTextChapter.append("Chapter"+(chapterPosition+1)+ ": ");
            }else{
                editTextChapter.append("Chapter"+(chapterPosition+1)+ ": ");
            }

            EditText editTextContent = (EditText) findViewById(R.id.et_contribution_add_chapter_new_content);
            String content = "\n" + "Add content to your chapter";
            editTextContent.append(content);

        }else if(action.equals("addParagraph")) {
            //setContentView(R.layout.contribution_add_paragraph);
            //get chapters and load them

        }else if(action.equals("addImage")) {
            //setContentView(R.layout.contribution_add_image);
        }

    }

    //Add the submit button to the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contribution_items, menu);
        return true;
    }



}
