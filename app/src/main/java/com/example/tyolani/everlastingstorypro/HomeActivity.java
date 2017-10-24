
package com.example.tyolani.everlastingstorypro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.goodiebag.carouselpicker.CarouselPicker;

public class HomeActivity extends AppCompatActivity {

    TextView titleView;
    TextView genreView;
    TextView authorCount;
    ImageView bookButton;

    ArrayList<Book> archivedBooks;

    CarouselPicker carouselPicker1,carouselPicker2,carouselPicker3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        archivedBooks = new ArrayList<Book>();
        final Book book = (Book) getIntent().getExtras().getSerializable("book");

        titleView = (TextView) findViewById(R.id.tv_home_bookTitle);
        genreView = (TextView) findViewById(R.id.tv_home_bookGenre);
        authorCount = (TextView) findViewById(R.id.tv_home_numberContributors);
        titleView.setText(book.getTitle());
        genreView.setText(book.getGenre());
        authorCount.setText(String.valueOf(book.getAuthorCount()));


        bookButton = findViewById(R.id.iv_home_imageButton);
        bookButton.setImageResource(R.mipmap.ic_book);

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, OverviewActivity.class);
                intent.putExtra("book", book);
                startActivity(intent);
            }
        });

        carouselPicker1 = (CarouselPicker)findViewById(R.id.carouselPicker1);

        //Carousel 2 with image and text
        List<CarouselPicker.PickerItem> mixItems = new ArrayList<>();
        mixItems.add(new CarouselPicker.DrawableItem(R.drawable.bookcover1));
        mixItems.add(new CarouselPicker.DrawableItem(R.drawable.bookcover2));
        mixItems.add(new CarouselPicker.DrawableItem(R.drawable.bookcover3));
        mixItems.add(new CarouselPicker.DrawableItem(R.drawable.bookcover4));
        CarouselPicker.CarouselViewAdapter mixAdapter = new CarouselPicker.CarouselViewAdapter(this,mixItems,0);
        carouselPicker1.setAdapter(mixAdapter);
    }


    //activeBook.saveBookToFirebase("activeBook");
    //ONLY FOR TESTING PURPOSES!
    public Book initSomeBook(){
        Book tempBook = new Book("Action adventure", "Endless dreams");
        tempBook.setOverview("Some overview depicturing the contents of the book.");

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
    public Chapter initSomeChapter(){

        ArrayList<Contribution> contch2 = new ArrayList<Contribution>();
        contch2.add(new Contribution("dfghijoklpsdfguio","Pelle", false));
        contch2.add(new Contribution("dfghijoklpsdfguio","Pelle", false));
        contch2.add(new Contribution("dfghijoklpsdfguio","Pelle", false));
        contch2.add(new Contribution("dfghijoklpsdfguio","Pelle", false));
        Chapter testChapter = new Chapter("FromTestChapterFunction", true, contch2);
        testChapter.closeChapter();

        return testChapter;
    }
}
