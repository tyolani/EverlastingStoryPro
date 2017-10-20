
package com.example.tyolani.everlastingstorypro;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    TextView titleView;
    TextView genreView;
    TextView authorCount;
    ImageView bookButton;
    int delay = 1500;

    ArrayList<Book> archivedBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        archivedBooks = new ArrayList<Book>();
        final Book activeBook = new Book("activeBook");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
            titleView = (TextView) findViewById(R.id.tv_home_bookTitle);
            genreView = (TextView) findViewById(R.id.tv_home_bookGenre);
            authorCount = (TextView) findViewById(R.id.tv_home_numberContributors);

            titleView.setText(activeBook.getTitle());
            genreView.setText(activeBook.getGenre());
            authorCount.setText(String.valueOf(activeBook.getAuthorCount()));

            }
        }, delay);


        bookButton = findViewById(R.id.iv_home_imageButton);
        bookButton.setImageResource(R.mipmap.ic_book);
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, OverviewActivity.class);
                intent.putExtra("book", activeBook);
                startActivity(intent);
            }
        });

    }

    //ONLY FOR TESTING PURPOSES!
    public Book initSomeBook(){
        Book tempBook = new Book("Action adventure", "Endless dreams");
        tempBook.createNewChapter("A distant dream..","Here goes all text for chapter 1", "Kevin",false);
        tempBook.getmChapters().get(0).addContribution(new Contribution("dfghijoklpsdfguio","Kevin", false));
        tempBook.getmChapters().get(0).addContribution(new Contribution("dfghijoklpsdfguio","Kevin", false));
        tempBook.getmChapters().get(0).addContribution(new Contribution("dfghijoklpsdfguio","Kevin", false));
        tempBook.getmChapters().get(0).addContribution(new Contribution("dfghijoklpsdfguio","Kevin", false));
        tempBook.getmChapters().get(0).addContribution(new Contribution("dfghijoklpsdfguio","Kevin", false));
        tempBook.getmChapters().get(0).closeChapter();

        tempBook.createNewChapter("Nobody expects..","Here goes all text for chapter 3", "Kalle", false);
        tempBook.getmChapters().get(1).addContribution(new Contribution("dfghijoklpsdfguio","Kalle", false));
        tempBook.getmChapters().get(1).addContribution(new Contribution("dfghijoklpsdfguio","Kalle", false));
        tempBook.getmChapters().get(1).addContribution(new Contribution("dfghijoklpsdfguio","Kalle", false));
        tempBook.getmChapters().get(1).addContribution(new Contribution("dfghijoklpsdfguio","Kalle", false));
        tempBook.getmChapters().get(1).addContribution(new Contribution("dfghijoklpsdfguio","Kalle", false));
        tempBook.getmChapters().get(1).closeChapter();

        tempBook.createNewChapter("..The spanish inquisition!","Here goes all text for chapter 3", "Adam", false);
        tempBook.getmChapters().get(2).addContribution(new Contribution("dfghijoklpsdfguio","Adam", false));
        tempBook.getmChapters().get(2).addContribution(new Contribution("dfghijoklpsdfguio","Adam", false));
        tempBook.getmChapters().get(2).addContribution(new Contribution("dfghijoklpsdfguio","Adam", false));
        tempBook.getmChapters().get(2).addContribution(new Contribution("dfghijoklpsdfguio","Adam", false));
        tempBook.getmChapters().get(2).addContribution(new Contribution("dfghijoklpsdfguio","Adam", false));
        tempBook.getmChapters().get(2).addContribution(new Contribution("dfghijoklpsdfguio","Adam", false));

        tempBook.createNewChapter("Eller?","Here goes all text for chapter 4", "Gustaf", false);
        tempBook.getmChapters().get(3).addContribution(new Contribution("dfghijoklpsdfguio","Gustaf", false));
        tempBook.getmChapters().get(3).addContribution(new Contribution("dfghijoklpsdfguio","Gustaf", false));
        tempBook.getmChapters().get(3).addContribution(new Contribution("dfghijoklpsdfguio","Gustaf", false));
        tempBook.getmChapters().get(3).addContribution(new Contribution("dfghijoklpsdfguio","Gustaf", false));
        tempBook.getmChapters().get(3).addContribution(new Contribution("dfghijoklpsdfguio","Gustaf", false));
        tempBook.getmChapters().get(3).addContribution(new Contribution("dfghijoklpsdfguio","Gustaf", false));
        return tempBook;
    }
}
