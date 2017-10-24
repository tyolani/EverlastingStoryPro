
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
        //carouselPicker2 = (CarouselPicker)findViewById(R.id.carouselPicker2);
        //carouselPicker3 = (CarouselPicker)findViewById(R.id.carouselPicker3);

        //Carousel 1 with all images
        List<CarouselPicker.PickerItem> itemsImages = new ArrayList<>();
        itemsImages.add(new CarouselPicker.DrawableItem(R.mipmap.kingdom));
        itemsImages.add(new CarouselPicker.DrawableItem(R.mipmap.ic_launcher_round));
        itemsImages.add(new CarouselPicker.DrawableItem(R.mipmap.kingdom));
        CarouselPicker.CarouselViewAdapter imageAdapter = new CarouselPicker.CarouselViewAdapter(this,itemsImages,0);
        carouselPicker1.setAdapter(imageAdapter);

        //Carousel 2 with all text
        /*List<CarouselPicker.PickerItem> textItems = new ArrayList<>();
        textItems.add(new CarouselPicker.TextItem("One",20)); // 20 is text size (sp)
        textItems.add(new CarouselPicker.TextItem("Two",20)); // 20 is text size (sp)
        textItems.add(new CarouselPicker.TextItem("Three",20)); // 20 is text size (sp)
        CarouselPicker.CarouselViewAdapter textAdapter = new CarouselPicker.CarouselViewAdapter(this,textItems,0);
        carouselPicker2.setAdapter(textAdapter);*/

        //Carousel 2 with image and text
        /*List<CarouselPicker.PickerItem> mixItems = new ArrayList<>();
        mixItems.add(new CarouselPicker.TextItem("One",20)); // 20 is text size (sp)
        mixItems.add(new CarouselPicker.DrawableItem(R.mipmap.ic_launcher_round));
        mixItems.add(new CarouselPicker.TextItem("Three",20)); // 20 is text size (sp)
        mixItems.add(new CarouselPicker.DrawableItem(R.mipmap.ic_launcher));
        CarouselPicker.CarouselViewAdapter mixAdapter = new CarouselPicker.CarouselViewAdapter(this,mixItems,0);
        carouselPicker3.setAdapter(mixAdapter);*/
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
