package com.example.tyolani.everlastingstorypro;

import android.media.Image;
import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by hedholm on 2017-10-12.
 */

public class Book implements Serializable {

    private String mGenre;
    private String mOverview;
    private String mTitle;
    private ArrayList<Chapter> mChapters;

    public Book(String overview, String genre, String title) {
        mGenre = genre;
        mOverview = overview;
        mTitle = title;
        mChapters = new ArrayList<Chapter>();
    }
    public Book(String genre, String title){
        mGenre = genre;
        mTitle = title;
        mChapters = new ArrayList<Chapter>();
    }
    public Book(String id) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Book");
        DatabaseReference book_idRef = mDatabase.child(id);
        DatabaseReference book_chaptersRef = book_idRef.child("Chapters");
        mChapters = new ArrayList<Chapter>();

        book_idRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mTitle = dataSnapshot.child("Title").getValue(String.class);
                mGenre = dataSnapshot.child("Genre").getValue(String.class);
                mOverview = dataSnapshot.child("Overview").getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("onCancelled", databaseError.toException());
            }
        });
        book_chaptersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chapter chapterm = snapshot.getValue(Chapter.class);
                    addNewChapter(chapterm);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("onCancelled", databaseError.toException());
            }
        });
    }
    public Book(){
        // Just an empty book
        mChapters = new ArrayList<Chapter>();
    }

    public boolean createNewChapter(String name, String initialText, String author, boolean isImage){
        if(getNumberOfOpenChapters() <= 3){
            Contribution tempContribution = new Contribution(initialText, author, isImage);
            Chapter tempChapter = new Chapter(tempContribution,name);
            return mChapters.add(tempChapter);
        }
        else {
            return false;
        }
    }
    public boolean createNewChapter(String name, String initialText, String author, int index, boolean isImage){
        if(getNumberOfOpenChapters() <= 3){
            Contribution tempContribution = new Contribution(initialText, author, isImage);
            Chapter tempChapter = new Chapter(tempContribution,name);
            mChapters.add(index, tempChapter);
            return true;
        }
        else {
            return false;
        }
    }
    public boolean addNewChapter(Chapter chapterToAdd){
        if(getNumberOfOpenChapters() <= 3){
            return mChapters.add(chapterToAdd);
        }
        else {
            return false;
        }
    }
    public boolean addNewChapter(Chapter chapterToAdd, int index){
        if(getNumberOfOpenChapters() <= 3){
            mChapters.add(index, chapterToAdd);
            return true;
        }
        else {
            return false;
        }
    }


    public String getGenre(){
        return mGenre;
    }
    public String getTitle(){
        return mTitle;
    }
    public String getOverview(){
        return mOverview;
    }
    public void setTitle(String s){
        mTitle = s;
    }
    public void setOverview(String s){
        mOverview = s;
    }
    public void setGenre(String s){
        mGenre = s;
    }
    public int getChapterIndex(String chapterName){
        for(int i = 0; i < mChapters.size(); i++){
            if(mChapters.get(i).getName().equals(chapterName)){
                return i;
            }
        }
        return -1;
    }
    public int getPageCount(){
        int totalPages = 0;
        for(int i = 0; i < mChapters.size(); i++){
            totalPages += mChapters.get(i).getPageCount();
        }
        return totalPages;
    }
    public int getAuthorCount() {
        int totalAuthors = 0;

        for (Chapter c : mChapters) {
            totalAuthors += c.getNumberOfAuthors();
        }
        return totalAuthors;
    }
    public int getNumberOfOpenChapters(){
        int totalOpen = 0;
        for(int i = 0; i < mChapters.size(); i++){
            if(!mChapters.get(i).isFinished()){
                totalOpen++;
            }
        }
        return totalOpen;
    }
    public int getContributionCount(){
        int i = 0;
        for(Chapter c : getChapters()){
            i += c.getNumberOfContributions();
        }
        return i;
    }
    public ArrayList<Chapter> getChapters() {
        return mChapters;
    }

    public void saveBookToFirebase(String reference){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Book");
        DatabaseReference book_idRef = mDatabase.child(reference);
        DatabaseReference chapter_idRef = book_idRef.child("Chapters");

        book_idRef.child("Title").setValue(getTitle());
        book_idRef.child("Genre").setValue(getGenre());
        book_idRef.child("ContributionCount").setValue(String.valueOf(getContributionCount()));
        book_idRef.child("AuthorCount").setValue(String.valueOf(getAuthorCount()));
        book_idRef.child("NumberOfOpenChapters").setValue(String.valueOf(getNumberOfOpenChapters()));
        book_idRef.child("PageCount").setValue(String.valueOf(getPageCount()));
        book_idRef.child("Overview").setValue(getOverview());

        for(int i = 0; i < getChapters().size(); i ++){
            chapter_idRef.child(String.valueOf(i)).child("Name").setValue(getChapters().get(i).getName());
            chapter_idRef.child(String.valueOf(i)).child("isFinished").setValue(getChapters().get(i).isFinished());

            for(int j = 0; j < getChapters().get(i).getContributions().size(); j++){

                chapter_idRef.child(String.valueOf(i)).child("Contributions").child(String.valueOf(j)).child("author").setValue(getChapters().get(i).getContributions().get(j).getAuthor());
                chapter_idRef.child(String.valueOf(i)).child("Contributions").child(String.valueOf(j)).child("textContent").setValue(getChapters().get(i).getContributions().get(j).getTextContent());
                chapter_idRef.child(String.valueOf(i)).child("Contributions").child(String.valueOf(j)).child("imageContent").setValue(getChapters().get(i).getContributions().get(j).getImageContent());
                chapter_idRef.child(String.valueOf(i)).child("Contributions").child(String.valueOf(j)).child("containsImageContent").setValue(getChapters().get(i).getContributions().get(j).isImageContribution());
             }
        }
    }
}
