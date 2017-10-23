package com.example.tyolani.everlastingstorypro;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
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


public class BookView extends AppCompatActivity implements AbsListView.OnScrollListener {

    private ArrayList<Chapter> chapters = new ArrayList<>();
    private ListView lvBookContent;
    private int firstVisibleRow;
    int delay = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_view);

        Toolbar menu = findViewById(R.id.menu_bookView);
        setSupportActionBar(menu);
        getSupportActionBar().setTitle("");


        final Book activeBook = new Book("activeBook");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                findViewById(R.id.bookview_loadingPanel).setVisibility(View.VISIBLE);
                for (Chapter c : activeBook.getChapters()) {
                    chapters.add(c);
                }
                inflateAdapter();
            }
        }, delay);

    }

    public void inflateAdapter(){
        lvBookContent = findViewById(R.id.lv_bookview_content);
        lvBookContent.setAdapter(new BookViewAdapter(this, chapters));
        lvBookContent.setOnScrollListener(BookView.this);
        findViewById(R.id.bookview_loadingPanel).setVisibility(View.INVISIBLE);
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
                final Dialog dialogChapter = new Dialog(BookView.this);
                if(chapters.get(firstVisibleRow).isFinished()){
                    dialogChapter.setContentView(R.layout.bookview_dialog_isfinished);
                    dialogChapter.show();
                }else {
                    dialogChapter.setContentView(R.layout.bookview_dialog);
                    //set textview
                    TextView tvBookviewDialog = (TextView) dialogChapter.findViewById(R.id.tv_bookview_dialog);
                    tvBookviewDialog.setText(getString(bookView_dialog_tv) + (firstVisibleRow+1) +": " + chapters.get(firstVisibleRow).getName());
                    dialogChapter.show();

                    Button beforeBtn = (Button) dialogChapter.findViewById(R.id.btn_bookview_dialog_before);
                    // if before btn is clicked
                    beforeBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Close dialog
                            dialogChapter.dismiss();
                            Intent contributionIntent = new Intent(getApplicationContext(),ContributionActivity.class);
                            contributionIntent.putExtra("addChapter", "before");
                            contributionIntent.putExtra("position",firstVisibleRow);
                            startActivity(contributionIntent);
                        }
                    });

                    Button afterBtn = (Button) dialogChapter.findViewById(R.id.btn_bookview_dialog_after);
                    // if after btn is clicked
                    afterBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Close dialog
                            dialogChapter.dismiss();
                            Intent contributionIntent = new Intent(getApplicationContext(),ContributionActivity.class);
                            contributionIntent.putExtra("addChapter", "after");
                            contributionIntent.putExtra("position",firstVisibleRow);
                            startActivity(contributionIntent);
                        }
                    });
                }
                return true;
            case R.id.action_add_paragraph:
                // Create custom dialog object
                Dialog dialogParagraph = new Dialog(BookView.this);
                if(chapters.get(firstVisibleRow).isFinished()){
                    dialogParagraph.setContentView(R.layout.bookview_dialog_isfinished);
                    dialogParagraph.show();
                }else {
                    Intent contributionParagraphIntent = new Intent(getApplicationContext(), ContributionActivity.class);
                    contributionParagraphIntent.putExtra("addParagraph", firstVisibleRow);
                    contributionParagraphIntent.putExtra("addP", "paragraph");
                    contributionParagraphIntent.putExtra("chapters", chapters.get(firstVisibleRow));
                    startActivity(contributionParagraphIntent);
                }
                return true;
            case R.id.action_add_image:
                // Create custom dialog object
                Dialog dialogImage = new Dialog(BookView.this);
                if(chapters.get(firstVisibleRow).isFinished()){
                    dialogImage.setContentView(R.layout.bookview_dialog_isfinished);
                    dialogImage.show();
                }else {
                    Intent contributionImageIntent = new Intent(getApplicationContext(), ContributionActivity.class);
                    contributionImageIntent.putExtra("addImage", firstVisibleRow);
                    contributionImageIntent.putExtra("addI", "image");
                    startActivity(contributionImageIntent);
                }
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
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

}

