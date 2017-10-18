
package com.example.tyolani.everlastingstorypro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    TextView titleView;
    TextView genreView;
    TextView authorCount;
    ImageView bookButton;

    Book activeBook;
    ArrayList<Book> archivedBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Book testBok = new Book("Book_01");  // HERE IS THE CALL LOADIGN THINGS FROM FIREBASE

        //activeBook = initSomeBook();
        activeBook = testBok;
        archivedBooks = new ArrayList<Book>();

        titleView = (TextView) findViewById(R.id.bookTitle);
        genreView = (TextView) findViewById(R.id.bookGenre);
        authorCount = (TextView) findViewById(R.id.numberContributors);

        titleView.setText(activeBook.getTitle());
        genreView.setText(activeBook.getGenre());
        authorCount.setText(Integer.toString(activeBook.getNumberOfAuthors()));


        //NEED TO REFRESH THE UI HERE AFTER THE THINGS HAVE BEEN LOADED FROM FIREBASE!

        bookButton = findViewById(R.id.imageButton);
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
        tempBook.createNewChapter("A distant dream..","Here goes all text for chapter 1", "Kevin");
        tempBook.getmChapters().get(0).addContribution(new Contribution("dfghijoklpsdfguio","Kevin"));
        tempBook.getmChapters().get(0).addContribution(new Contribution("dfghijoklpsdfguio","Kevin"));
        tempBook.getmChapters().get(0).addContribution(new Contribution("dfghijoklpsdfguio","Kevin"));
        tempBook.getmChapters().get(0).addContribution(new Contribution("dfghijoklpsdfguio","Kevin"));
        tempBook.getmChapters().get(0).addContribution(new Contribution("dfghijoklpsdfguio","Kevin"));
        tempBook.getmChapters().get(0).closeChapter();

        tempBook.createNewChapter("Nobody expects..","Here goes all text for chapter 3", "Kalle");
        tempBook.getmChapters().get(1).addContribution(new Contribution("dfghijoklpsdfguio","Kalle"));
        tempBook.getmChapters().get(1).addContribution(new Contribution("dfghijoklpsdfguio","Kalle"));
        tempBook.getmChapters().get(1).addContribution(new Contribution("dfghijoklpsdfguio","Kalle"));
        tempBook.getmChapters().get(1).addContribution(new Contribution("dfghijoklpsdfguio","Kalle"));
        tempBook.getmChapters().get(1).addContribution(new Contribution("dfghijoklpsdfguio","Kalle"));
        tempBook.getmChapters().get(1).closeChapter();

        tempBook.createNewChapter("..The spanish inquisition!","Here goes all text for chapter 3", "Adam");
        tempBook.getmChapters().get(2).addContribution(new Contribution("dfghijoklpsdfguio","Adam"));
        tempBook.getmChapters().get(2).addContribution(new Contribution("dfghijoklpsdfguio","Adam"));
        tempBook.getmChapters().get(2).addContribution(new Contribution("dfghijoklpsdfguio","Adam"));
        tempBook.getmChapters().get(2).addContribution(new Contribution("dfghijoklpsdfguio","Adam"));
        tempBook.getmChapters().get(2).addContribution(new Contribution("dfghijoklpsdfguio","Adam"));
        tempBook.getmChapters().get(2).addContribution(new Contribution("dfghijoklpsdfguio","Adam"));

        tempBook.createNewChapter("Eller?","Here goes all text for chapter 4", "Gustaf");
        tempBook.getmChapters().get(3).addContribution(new Contribution("dfghijoklpsdfguio","Gustaf"));
        tempBook.getmChapters().get(3).addContribution(new Contribution("dfghijoklpsdfguio","Gustaf"));
        tempBook.getmChapters().get(3).addContribution(new Contribution("dfghijoklpsdfguio","Gustaf"));
        tempBook.getmChapters().get(3).addContribution(new Contribution("dfghijoklpsdfguio","Gustaf"));
        tempBook.getmChapters().get(3).addContribution(new Contribution("dfghijoklpsdfguio","Gustaf"));
        tempBook.getmChapters().get(3).addContribution(new Contribution("dfghijoklpsdfguio","Gustaf"));
        return tempBook;
    }
}
