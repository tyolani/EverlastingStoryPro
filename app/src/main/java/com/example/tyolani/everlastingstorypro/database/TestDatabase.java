package com.example.tyolani.everlastingstorypro.database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.tyolani.everlastingstorypro.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;



//https://everlasting-story.firebaseio.com/

public class TestDatabase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initilize database
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Book");

        // Write to the database
        //mDatabase.child("Name").setValue("Hardcoded key and value");

        //mDatabase.push().setValue("RandomValue with RandomKey");//use push method for getting  a random key


        //Write object to database
        HashMap<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("Chapters", "11");
        dataMap.put("Contributions", "31");
        dataMap.put("Title", "I Was Told There'd Be Cake");
        dataMap.put("Pages", "197");
        dataMap.put("Writers", "5");
        //mDatabase.setValue(dataMap);

        // Read from the database child of a child
        DatabaseReference book_01Ref = mDatabase.child("Book_01");
        DatabaseReference book_01TitleRef = book_01Ref.child("Title");
        book_01TitleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("Book_01 Title: ", dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("onCancelled", databaseError.toException());
            }
        });

    }
}




