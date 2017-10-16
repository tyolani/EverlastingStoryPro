package com.example.tyolani.everlastingstorypro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class BookView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_view);

        //initiating menu_bookView
        Toolbar menu_bookView = (Toolbar) findViewById(R.id.menu_bookView);
        setSupportActionBar(menu_bookView);

        //making the last textview scrollable
        TextView bookViewTextview= (TextView) findViewById(R.id.bookView_mockup_textView3);
        bookViewTextview.setMovementMethod(new ScrollingMovementMethod());

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

