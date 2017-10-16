package com.example.tyolani.everlastingstorypro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class OverviewActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Chapter> chapterArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        Book book = (Book) getIntent().getExtras().getSerializable("book");

        for (Chapter c : book.getChapters()) {
            chapterArray.add(c);
        }

        listView = findViewById(R.id.lv_table_of_contents);
        listView.setAdapter(new OverviewAdapter(this, chapterArray));
    }

}
