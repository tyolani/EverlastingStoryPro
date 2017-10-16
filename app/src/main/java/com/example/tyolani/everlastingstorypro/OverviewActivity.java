package com.example.tyolani.everlastingstorypro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class OverviewActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> chapterArray = new ArrayList<>();

    String testChapter1 = "First chapter";
    String testChapter2 = "Second chapter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        chapterArray.add(testChapter1);
        chapterArray.add(testChapter2);
        listView = findViewById(R.id.lv_table_of_contents);
        listView.setAdapter(new OverviewAdapter(this, chapterArray));
    }
}
