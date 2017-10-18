package com.example.tyolani.everlastingstorypro;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;


import static com.example.tyolani.everlastingstorypro.R.string.bookView_dialog_tv;
import static com.example.tyolani.everlastingstorypro.R.string.bookView_mockup_bookText;


public class BookView extends AppCompatActivity implements AbsListView.OnScrollListener {

    public    ArrayList<Chapter> mockupchapters = new ArrayList<>();
    private   ListView lvBookContent;
    private int firstVisibleRow;
    private int lastVisibleRow;

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

        lvBookContent = findViewById(R.id.lv_bookview_content);
        lvBookContent.setAdapter(new BookViewAdapter(this, mockupchapters));
        lvBookContent.setOnScrollListener(BookView.this);
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

                // Create custom dialog object
                final Dialog dialog = new Dialog(BookView.this);
                dialog.setContentView(R.layout.bookview_dialog);

                //set textview
                TextView tvBookviewDialog = (TextView) dialog.findViewById(R.id.tv_bookview_dialog);
                tvBookviewDialog.setText(getString(bookView_dialog_tv) + (firstVisibleRow+1) +": " + mockupchapters.get(firstVisibleRow).getName()); //+concat chapter no
                dialog.show();

                Button beforeBtn = (Button) dialog.findViewById(R.id.btn_bookview_dialog_before);
                // if before btn is clicked
                beforeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog
                        dialog.dismiss();
                        Intent contributionIntent = new Intent(getApplicationContext(),ContributionActivity.class);
                        contributionIntent.putExtra("addChapter", "before");
                        contributionIntent.putExtra("position",firstVisibleRow);
                        startActivity(contributionIntent);
                    }
                });

                Button afterBtn = (Button) dialog.findViewById(R.id.btn_bookview_dialog_after);
                // if after btn is clicked
                afterBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog
                        dialog.dismiss();
                        Intent contributionIntent = new Intent(getApplicationContext(),ContributionActivity.class);
                        contributionIntent.putExtra("addChapter", "after");
                        contributionIntent.putExtra("position",firstVisibleRow);
                        startActivity(contributionIntent);
                    }
                });
                return true;
            case R.id.action_add_paragraph:
                Intent contributionParagraphIntent = new Intent(getApplicationContext(),ContributionActivity.class);
                contributionParagraphIntent.putExtra("addParagraph", firstVisibleRow);
                contributionParagraphIntent.putExtra("addP", "paragraph");
                startActivity(contributionParagraphIntent);
                return true;
            case R.id.action_add_image:
                Intent contributionImageIntent = new Intent(getApplicationContext(),ContributionActivity.class);
                contributionImageIntent.putExtra("addImage", firstVisibleRow);
                contributionImageIntent.putExtra("addI", "image");
                startActivity(contributionImageIntent);
                return true;
            default:
                // the user's action was not recognized
                return super.onOptionsItemSelected(item);
        }
    }

    /*Now we can see the first and last visible chapters in the view, and then we
    can easily send the "right" chapter to the dialog and the to the contributionActivity */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
        firstVisibleRow = lvBookContent.getFirstVisiblePosition();
        lastVisibleRow = lvBookContent.getLastVisiblePosition();
        for(int i = firstVisibleRow; i <= lastVisibleRow; i++) {
            //Log.d("onScroll()..Position", i+"");
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {


    }

}

