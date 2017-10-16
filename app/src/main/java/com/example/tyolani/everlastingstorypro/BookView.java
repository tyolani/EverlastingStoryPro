package com.example.tyolani.everlastingstorypro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.tyolani.everlastingstorypro.R.string.bookView_mockup_bookText;

public class BookView extends AppCompatActivity {

    private   ArrayList<Chapter> mockupchapters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_view);

        Toolbar menu = (Toolbar) findViewById(R.id.menu_bookView);
        setSupportActionBar(menu);


        //Mockup for listview adapter, later retrieve chapters from book
        String test = getString(bookView_mockup_bookText);
        Contribution contrib1 = new Contribution(test,"Elsa Mjoll");
        Chapter chap1 = new Chapter(contrib1,"Beyond the wall");
        Chapter chap2 = new Chapter(contrib1,"Aftermath");
        Chapter chap3 = new Chapter(contrib1,"Orange County");

        mockupchapters.add(chap1);
        mockupchapters.add(chap2);
        mockupchapters.add(chap3);

        ListView lvBookContent = findViewById(R.id.lv_bookview_content);
        lvBookContent.setAdapter(new BookViewAdapter(this, mockupchapters));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bookview_items, menu);
        return true;
    }

    //Handling actions from the menu_bookView toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_chapter:
                return true;
            case R.id.action_add_paragraph:
                return true;
            case R.id.action_add_image:
                return true;
            default:
                // the user's action was not recognized
                return super.onOptionsItemSelected(item);
        }
    }
}

