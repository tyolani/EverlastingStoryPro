package com.example.tyolani.everlastingstorypro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

        Book book = (Book) getIntent().getExtras().getSerializable("book");

        for (Chapter c : book.getChapters()) {
            chapterArray.add(c);
        }

        TextView tvPageCount = findViewById(R.id.tv_overview_page_count);
        TextView tvContributorCount = findViewById(R.id.tv_overview_contributor_count);
        TextView tvAboutBook = findViewById(R.id.tv_overview_about_book);
        TextView tvBookTitle = findViewById(R.id.tv_overview_title);

        tvPageCount.setText(book.getPageCount());
        tvContributorCount.setText(book.getAuthorCount());
        tvAboutBook.setText(book.getBookOverview());
        tvBookTitle.setText(book.getBookTitle());

        lvTableOfContents = findViewById(R.id.lv_table_of_contents);
        lvTableOfContents.setAdapter(new OverviewAdapter(this, chapterArray));
    }

}
