package com.example.tyolani.everlastingstorypro;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class OverviewActivity extends AppCompatActivity {

    ListView lvTableOfContents;
    ArrayList<Chapter> chapterArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        final Book book = new Book("activeBook");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                findViewById(R.id.overview_loadingPanel).setVisibility(View.VISIBLE);
                for (Chapter c : book.getChapters()) {
                    chapterArray.add(c);
                }

            TextView tvBookTitle = findViewById(R.id.tv_overview_title);
            TextView tvAboutBook = findViewById(R.id.tv_overview_about_book);
            TextView tvPageCount = findViewById(R.id.tv_overview_page_count);
            TextView tvContributorCount = findViewById(R.id.tv_overview_contributor_count);

            tvPageCount.setText(String.valueOf(book.getPageCount()));
            tvContributorCount.setText(String.valueOf(book.getAuthorCount()));
            tvAboutBook.setText(book.getOverview());
            tvBookTitle.setText(book.getTitle());

            inflatelist();
            }
        }, 2000);


        Button readBook  = (Button) findViewById(R.id.btn_overview_read_book);
            readBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent overviewIntent = new Intent(getApplicationContext(),BookView.class);
                startActivity(overviewIntent);
            }
        });

    }

    public void inflatelist(){
        lvTableOfContents = findViewById(R.id.lv_table_of_contents);
        lvTableOfContents.setAdapter(new OverviewAdapter(this, chapterArray));
        findViewById(R.id.overview_loadingPanel).setVisibility(View.INVISIBLE);
    }


}
