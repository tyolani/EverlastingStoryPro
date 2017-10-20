
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


        activeBook = initSomeBook();
        //activeBook = testBok;
        archivedBooks = new ArrayList<Book>();

        titleView = (TextView) findViewById(R.id.bookTitle);
        genreView = (TextView) findViewById(R.id.bookGenre);
        authorCount = (TextView) findViewById(R.id.numberContributors);

        titleView.setText(activeBook.getTitle());
        genreView.setText(activeBook.getGenre());
        authorCount.setText(Integer.toString(activeBook.getAuthorCount()));

        activeBook.saveBookToFirebase("activeBook");


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
        ArrayList<Contribution> contch1 = new ArrayList<Contribution>();
        contch1.add(new Contribution("dfghijoklpsdfguio","Kevin", false));
        contch1.add(new Contribution("dfghijoklpsdfguio","Kevin", false));
        contch1.add(new Contribution("dfghijoklpsdfguio","Kevin", false));
        contch1.add(new Contribution("dfghijoklpsdfguio","Kevin", false));
        contch1.add(new Contribution("dfghijoklpsdfguio","Kevin", false));
        Chapter ch1 = new Chapter("A distant deam..", true, contch1);
        ch1.closeChapter();
        tempBook.addNewChapter(ch1);

        ArrayList<Contribution> contch2 = new ArrayList<Contribution>();
        contch2.add(new Contribution("dfghijoklpsdfguio","Lars", false));
        contch2.add(new Contribution("dfghijoklpsdfguio","Lars", false));
        contch2.add(new Contribution("dfghijoklpsdfguio","Lars", false));
        contch2.add(new Contribution("dfghijoklpsdfguio","Lars", false));
        Chapter ch2 = new Chapter("Nobody expects..", true, contch2);
        ch2.closeChapter();
        tempBook.addNewChapter(ch2);

        ArrayList<Contribution> contch3 = new ArrayList<Contribution>();
        contch3.add(new Contribution("dfghijoklpsdfguio","Lasse", false));
        contch3.add(new Contribution("dfghijoklpsdfguio","Lasse", false));
        contch3.add(new Contribution("dfghijoklpsdfguio","Lasse", false));
        contch3.add(new Contribution("dfghijoklpsdfguio","Lasse", false));
        contch3.add(new Contribution("dfghijoklpsdfguio","Lasse", false));
        contch3.add(new Contribution("dfghijoklpsdfguio","Lasse", false));
        contch3.add(new Contribution("dfghijoklpsdfguio","Lasse", false));
        contch3.add(new Contribution("dfghijoklpsdfguio","Lasse", false));
        contch3.add(new Contribution("dfghijoklpsdfguio","Lasse", false));
        contch3.add(new Contribution("dfghijoklpsdfguio","Lasse", false));
        contch3.add(new Contribution("dfghijoklpsdfguio","Lasse", false));
        contch3.add(new Contribution("dfghijoklpsdfguio","Lasse", false));
        contch3.add(new Contribution("dfghijoklpsdfguio","Lasse", false));
        contch3.add(new Contribution("dfghijoklpsdfguio","Lasse", false));
        Chapter ch3 = new Chapter(".. The Spanish Inquisition!", true, contch3);
        ch3.closeChapter();
        tempBook.addNewChapter(ch3);

        return tempBook;
    }
}
