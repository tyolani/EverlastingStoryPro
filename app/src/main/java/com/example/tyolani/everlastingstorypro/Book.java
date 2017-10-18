package com.example.tyolani.everlastingstorypro;

import android.content.Intent;
import android.media.Image;
import android.provider.ContactsContract;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by hedholm on 2017-10-12.
 */

public class Book implements Serializable {

    private int mContributorCount;
    private int mPageCount;
    private String mGenre;
    private String mOverview;
    private String mTitle;
    private ArrayList<Chapter> mChapters;

    public Book(String overview, String genre, String title) {
        mGenre = genre;
        mOverview = overview;
        mTitle = title;
        mChapters = new ArrayList<Chapter>();
        mPageCount = 0;
        mContributorCount = 0;
    }
    public Book(String genre, String title){
        mGenre = genre;
        mTitle = title;
        mChapters = new ArrayList<Chapter>();
        mPageCount = 0;
        mContributorCount = 0;
        newContributor();
    }
<<<<<<< HEAD
    public Book(String id){
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

                mPageCount = dataSnapshot.child("PageCount").getValue(Integer.class);
                mContributorCount = dataSnapshot.child("ContributorCount").getValue(Integer.class);
                Log.d("DEBUG", mTitle);
                Log.d("DEBUG", mGenre);
                Log.d("DEBUG", mOverview);
                Log.d("DEBUG", String.valueOf(mPageCount));
                Log.d("DEBUG", String.valueOf(mContributorCount));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("onCancelled", databaseError.toException());
            }
        });
        book_chaptersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Log.d("DEBUG",child.getValue().toString());

                    mChapters.add(new Chapter("Test test test"));
                    mChapters.add(new Chapter("Test 2 test 2 test 2"));

                    //---------------------------------------------------------------------------------------------------------------
                    //HERE SHOULD BE SOME CONVERSION FROM JSON TO ACTUAL DATA
                    //WE NEED TO CREATE CHAPTERS BASED ON THE CHILDS, ADD THEM TO THE BOOK'S CHAPTER ARRAY
                    //ALSO NEED TO CREATE CONTRIBUTIONS BASED ON THE DATA OVER IN THE CHAPTER CONSTRUCTOR -->

                    // Chapter(String newname, boolean done, ArrayList<Contribution> newcontributions)
                    //---------------------------------------------------------------------------------------------------------------
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("onCancelled", databaseError.toException());
            }
        });



=======
    public Book(String title){
        mTitle = title;
        mPageCount = 0;
        mContributorCount = 0;
>>>>>>> 883496ddd5e15f6f5d27c18104f91e29d6d4a2dc
    }
    public Book(){
        // Just an empty book
        mChapters = new ArrayList<Chapter>();
        mPageCount = 0;
        mContributorCount = 0;
    }

    /**
     * Increases the contributor count by 1
     */
    public void newContributor() {
        this.mContributorCount += 1;
    }

    public boolean addNewChapter(Chapter chapterToAdd){
        if(getNumberOfOpenChapters() <= 3){
            return mChapters.add(chapterToAdd);
        }
        else {
            return false;
        }
    }

    public boolean createNewChapter(String name, String initialText, String author){
        if(getNumberOfOpenChapters() <= 3){
            Contribution tempContribution = new Contribution(initialText, author);
            Chapter tempChapter = new Chapter(tempContribution,name);
            return mChapters.add(tempChapter);
        }
        else {
            return false;
        }
    }

    public boolean createNewChapter(String name, Image initialImage, String author){
        if(getNumberOfOpenChapters() <= 3){
            Contribution tempContribution = new Contribution(initialImage, author);
            Chapter tempChapter = new Chapter(tempContribution,name);
            return mChapters.add(tempChapter);
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

    public boolean createNewChapter(String name, String initialText, String author, int index){
        if(getNumberOfOpenChapters() <= 3){
            Contribution tempContribution = new Contribution(initialText, author);
            Chapter tempChapter = new Chapter(tempContribution,name);
            mChapters.add(index, tempChapter);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean createNewChapter(String name, Image initialImage, String author, int index){
        if(getNumberOfOpenChapters() <= 3){
            Contribution tempContribution = new Contribution(initialImage, author);
            Chapter tempChapter = new Chapter(tempContribution,name);
            mChapters.add(index, tempChapter);
            return true;
        }
        else {
            return false;
        }
    }

    public int getChapterIndex(String chapterName){
        for(int i = 0; i < mChapters.size(); i++){
            if(mChapters.get(i).getName().equals(chapterName)){
                return i;
            }
        }
        return -1;
    }
    //Returns the number of pages of all chapters combined
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
    public int getNumberOfAuthors() {
        int totalAuthors = 0;
        for (int i = 0; i < mChapters.size(); i++) {
            totalAuthors += mChapters.get(i).getNumberOfAuthors();
        }
        return totalAuthors;
    }
    public String getBookOverview() {
        return this.mOverview;
    }
    public String getBookTitle() {
        return this.mTitle;
    }

    public ArrayList<Chapter> getChapters() {
        return this.mChapters;
    }

    public ArrayList<Chapter> getmChapters(){
        return mChapters;
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
}
