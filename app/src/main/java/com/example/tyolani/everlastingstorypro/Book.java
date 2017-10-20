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

                mPageCount = dataSnapshot.child("PageCount").getValue(Integer.class);
                mContributorCount = dataSnapshot.child("ContributorCount").getValue(Integer.class);
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

/*    public boolean createNewChapter(String name, Image initialImage, String author){
        if(getNumberOfOpenChapters() <= 3){
            Contribution tempContribution = new Contribution(initialImage, author);
            Chapter tempChapter = new Chapter(tempContribution,name);
            return mChapters.add(tempChapter);
        }
        else {
            return false;
        }
    }*/

    public boolean addNewChapter(Chapter chapterToAdd, int index){
        if(getNumberOfOpenChapters() <= 3){
            mChapters.add(index, chapterToAdd);
            return true;
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

/*    public boolean createNewChapter(String name, Image initialImage, String author, int index){
        if(getNumberOfOpenChapters() <= 3){
            Contribution tempContribution = new Contribution(initialImage, author);
            Chapter tempChapter = new Chapter(tempContribution,name);
            mChapters.add(index, tempChapter);
            return true;
        }
        else {
            return false;
        }
    }*/

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
